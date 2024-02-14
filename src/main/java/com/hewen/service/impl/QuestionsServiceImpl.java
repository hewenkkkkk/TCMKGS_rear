package com.hewen.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hewen.entity.po.Questionimages;
import com.hewen.entity.vo.NewQuestions;
import com.hewen.entity.vo.que.*;
import com.hewen.mappers.UsersMapper;
import com.hewen.service.QuestionimagesService;
import com.hewen.service.UsersService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hewen.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.QuestionsQuery;
import com.hewen.entity.po.Questions;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.QuestionsMapper;
import com.hewen.service.QuestionsService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * 问题信息表 业务接口实现
 * 
 */
@Service("questionsService")
public class QuestionsServiceImpl implements QuestionsService {

	@Resource
	private QuestionsMapper<Questions,QuestionsQuery> questionsMapper;

	@Resource
	private UsersMapper usersMapper;

	@Resource
	private UsersService usersService;


	@Resource
	private QuestionimagesService questionimagesService;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Questions> findListByParam(QuestionsQuery param) {
		return this.questionsMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(QuestionsQuery param) {
		return this.questionsMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Questions> findListByPage(QuestionsQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Questions> list = this.findListByParam(param);
		PaginationResultVO<Questions> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Questions bean){
		return this.questionsMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Questions> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.questionsMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Questions> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.questionsMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据QuestionId获取对象
	 */
	@Override
	public Questions getQuestionsByQuestionId(String questionId){
		return this.questionsMapper.selectByQuestionId(questionId);
	}

	/**
	 * 根据QuestionId修改
	 */
	@Override
	public Integer updateQuestionsByQuestionId(Questions bean,String questionId){
		return this.questionsMapper.updateByQuestionId(bean,questionId);
	}

	/**
	 * 根据QuestionId删除
	 */
	@Override
	public Integer deleteQuestionsByQuestionId(String questionId){
		return this.questionsMapper.deleteByQuestionId(questionId);
	}

	/**
	 * 添加问题

	 {
	 "userName": "123",
	 "email": "user@example.com",
	 "content": "这里是用户提出的问题内容",
	 "imageUrl": [
	 "http://example.com/image1.jpg",
	 "http://example.com/image2.jpg"
	 ]
	 }

	 * @param jsonData
	 * @return
	 */
	@Override
	@Transactional
	public String addQuestion(String jsonData) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonDatas;

		try {
			// 尝试解析JSON数据
			jsonDatas = objectMapper.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
		} catch (JsonProcessingException e) {
			// 解析JSON失败
			return "Failed to parse JSON data: " + e.getMessage();
		}

		try {
			String userName = (String) jsonDatas.get("userName");
			String email = (String) jsonDatas.get("email");
			String questionContent = (String) jsonDatas.get("content");
			List<String> imageUrls = (List<String>) jsonDatas.get("imageUrl");

			// 生成用户ID和问题ID
			SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
			String userId = String.valueOf(idWorker.nextId());
			String questionId = String.valueOf(idWorker.nextId());

			// 保存用户信息
			usersService.addQuestionUserInfo(userName, userId);

			// 保存问题信息
			Questions questions = new Questions();
			questions.setQuestionId(questionId);
			questions.setUserId(userId);
			questions.setEmail(email);
			questions.setContent(questionContent);
			add(questions);

			// 保存图片信息
			for (String imageUrl : imageUrls) {
				String imageId = String.valueOf(idWorker.nextId());
				Questionimages questionImage = new Questionimages();
				questionImage.setImageId(imageId);
				questionImage.setQuestionId(questionId);
				questionImage.setImageUrl(imageUrl);
				questionimagesService.add(questionImage);
			}
		} catch (Exception e) {
			// 处理所有其他异常
			return "An error occurred: " + e.getMessage();
		}

		return "Question added successfully";
	}

	@Override
	public List<NewQuestions> getNewQuestions() {
		return this.questionsMapper.selectByQuestionIds();
	}

	@Override
	public QuestionVo getQuestionsAndComments(String JsonId) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonDatas;

		try {
			// 尝试解析JSON数据
			jsonDatas = objectMapper.readValue(JsonId, new TypeReference<Map<String, Object>>() {});
		} catch (JsonProcessingException e) {
			// 解析JSON失败
			return null;
		}

		String questionId = (String) jsonDatas.get("questionId");

		QuestionVo question = questionsMapper.selectQuestionById(questionId);
		if (question == null) {
			return null;
		}
		UserVo user =usersMapper.selectByUserId(question.getUser_id());
		question.setUser(user);
		List<QuestionImageVo> questionImages = questionsMapper.selectImagesByQuestionId(questionId);
		question.setImages(questionImages);

		List<CommentVo> comments = questionsMapper.selectCommentsByQuestionId(questionId);
		for (CommentVo comment : comments) {
			loadCommentDetails(comment);
		}
		question.setComments(comments);

		return question;
	}

	private void loadCommentDetails(CommentVo comment) {
		UserVo commentUser =usersMapper.selectByUserId(comment.getUser_id());
		comment.setUser(commentUser); // 设置评论的用户信息
		List<CommentImageVo> commentImages = questionsMapper.selectImagesByCommentId(comment.getComment_id());
		comment.setImages(commentImages);

		List<CommentVo> subComments = questionsMapper.selectSubCommentsByParentId(comment.getComment_id());
		for (CommentVo subComment : subComments) {
			loadCommentDetails(subComment); // 递归加载子评论
		}
		comment.setSubComments(subComments);
	}

}