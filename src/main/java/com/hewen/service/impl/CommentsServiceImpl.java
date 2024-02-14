package com.hewen.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hewen.entity.po.*;
import com.hewen.service.CommentimagesService;
import com.hewen.service.UsersService;
import com.hewen.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.CommentsQuery;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.CommentsMapper;
import com.hewen.service.CommentsService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * 评论信息表 业务接口实现
 * 
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

	@Resource
	private CommentsMapper<Comments,CommentsQuery> commentsMapper;

	@Resource
	private UsersService usersService;

	@Resource
	CommentimagesService commentimagesService;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Comments> findListByParam(CommentsQuery param) {
		return this.commentsMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(CommentsQuery param) {
		return this.commentsMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Comments> findListByPage(CommentsQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Comments> list = this.findListByParam(param);
		PaginationResultVO<Comments> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Comments bean){
		return this.commentsMapper.insert(bean);
	}

	@Override
	public Integer addSubComments(SubComment bean) {
		return this.commentsMapper.addSubComment(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Comments> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.commentsMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Comments> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.commentsMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据CommentId获取对象
	 */
	@Override
	public Comments getCommentsByCommentId(String commentId){
		return this.commentsMapper.selectByCommentId(commentId);
	}

	/**
	 * 根据CommentId修改
	 */
	@Override
	public Integer updateCommentsByCommentId(Comments bean,String commentId){
		return this.commentsMapper.updateByCommentId(bean,commentId);
	}

	/**
	 * 根据CommentId删除

	 */
	@Override
	public Integer deleteCommentsByCommentId(String commentId){
		return this.commentsMapper.deleteByCommentId(commentId);
	}


	/**
	 *

	 {
	 "userName": "123",  //  评论者的用户名
	 "email": "user@example.com",    //  评论者的邮箱
	 "question_id": 789,            //  评论的问题ID
	 "content": "这是对问题的评论",
	 "image_url": [
	 "http://example.com/image1.jpg",
	 "http://example.com/image2.jpg"
	 ],
	 "parent_id": null  // 如果是二级评论，这里应为父评论的ID
	 }

	 * @param jsonData
	 * @return
	 */
	@Override
	@Transactional
	public String addQuestionComments(String jsonData) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonDatas;

		try {
			jsonDatas = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
		} catch (JsonProcessingException e) {
			return "Failed to parse JSON data: " + e.getMessage();
		}

		try {
			SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
			String userId = String.valueOf(idWorker.nextId());
			String commentsId = String.valueOf(idWorker.nextId());//本条评论的ID

			String questionId = (String) jsonDatas.get("question_id");
			String userName = (String) jsonDatas.get("userName");
			String email = (String) jsonDatas.get("email");
			String content = (String) jsonDatas.get("content");
			String parentId = (String) jsonDatas.get("parent_id");
			List<String> imageUrls = (List<String>) jsonDatas.get("image_url");

			// 生成用户头像,保存用户信息
			usersService.addQuestionUserInfo(userName, userId);


			if (parentId!=null && questionId!=null){
				//此为子评论，不需要保存问题ID
				questionId = null;
			}

			// 保存评论信息
			Comments comments = new Comments();
			comments.setCommentId(commentsId);
			comments.setUserId(userId);
			comments.setQuestionId(questionId);
			comments.setContent(content);
			comments.setParentId(parentId);
			comments.setEmail(email);
			System.out.println(comments);
			add(comments);

			// 处理 imageUrls 中的每个 URL
			for (String imageUrl : imageUrls) {
				String imageId = String.valueOf(idWorker.nextId());
				Commentimages commentImage = new Commentimages();
				commentImage.setImageId(imageId);
				commentImage.setCommentId(commentsId);
				commentImage.setImageUrl(imageUrl);
				commentimagesService.add(commentImage);
			}
		} catch (Exception e) {
			return "An error occurred: " + e.getMessage();
		}

		return "Comments added successfully";
	}


	/**
	 * 添加子评论
	 * @param jsonData
	 * @return

	{
	"userName": "123",  //  评论者的用户名
	"email": "user@example.com",    //  评论者的邮箱
	"content": "这是对问题的评论",
	"image_url": [
	"http://example.com/image1.jpg",
	"http://example.com/image2.jpg"
	],
	"parent_id": ""  // 如果是二级评论，这里应为父评论的ID
	}

	 */

	@Override
	public String addSubComments(String jsonData) {

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonDatas;

		try {
			jsonDatas = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
		} catch (JsonProcessingException e) {
			return "Failed to parse JSON data: " + e.getMessage();
		}

		try {
			SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
			String userId = String.valueOf(idWorker.nextId());
			String commentsId = String.valueOf(idWorker.nextId());//本条评论的ID
			String userName = (String) jsonDatas.get("userName");
			String email = (String) jsonDatas.get("email");
			String content = (String) jsonDatas.get("content");
			String parentId = (String) jsonDatas.get("parent_id");
			List<String> imageUrls = (List<String>) jsonDatas.get("image_url");

			// 生成用户头像,保存用户信息
			usersService.addQuestionUserInfo(userName, userId);
			String questionId = null;
			// 保存评论信息
			SubComment comments = new SubComment();
			comments.setCommentId(commentsId);
			//comments.setQuestionId(questionId);
			comments.setUserId(userId);
			comments.setContent(content);
			comments.setParentId(parentId);
			comments.setEmail(email);
			System.out.println(comments);
			addSubComments(comments);

			// 处理 imageUrls 中的每个 URL
			for (String imageUrl : imageUrls) {
				String imageId = String.valueOf(idWorker.nextId());
				Commentimages commentImage = new Commentimages();
				commentImage.setImageId(imageId);
				commentImage.setCommentId(commentsId);
				commentImage.setImageUrl(imageUrl);
				commentimagesService.add(commentImage);
			}
		} catch (Exception e) {
			return "An error occurred: " + e.getMessage();
		}

		return "Comments added successfully";
	}

}