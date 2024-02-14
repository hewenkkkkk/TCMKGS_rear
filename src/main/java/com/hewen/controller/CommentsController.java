package com.hewen.controller;

import java.util.List;

import com.hewen.entity.query.CommentsQuery;
import com.hewen.entity.po.Comments;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.CommentsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 
 * 评论信息表 Controller
 * 
 */
@RestController("commentsController")
@RequestMapping("comments")
public class CommentsController extends ABaseController{

	@Resource
	private CommentsService commentsService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(CommentsQuery query){
		return getSuccessResponseVO(commentsService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Comments bean) {
		commentsService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Comments> listBean) {
		commentsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Comments> listBean) {
		commentsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据CommentId查询对象
	 */
	@RequestMapping("getCommentsByCommentId")
	public ResponseVO getCommentsByCommentId(String commentId) {
		return getSuccessResponseVO(commentsService.getCommentsByCommentId(commentId));
	}

	/**
	 * 根据CommentId修改对象
	 */
	@RequestMapping("updateCommentsByCommentId")
	public ResponseVO updateCommentsByCommentId(Comments bean,String commentId) {
		commentsService.updateCommentsByCommentId(bean,commentId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据CommentId删除
	 */
	@RequestMapping("deleteCommentsByCommentId")
	public ResponseVO deleteCommentsByCommentId(String commentId) {
		commentsService.deleteCommentsByCommentId(commentId);
		return getSuccessResponseVO(null);
	}

     /**
	 * 添加子评论
	 */
	@RequestMapping("addSubComments")
	public ResponseVO addSubComments(@RequestBody String jsonData) {
		String is = commentsService.addSubComments(jsonData);
		return getSuccessResponseVO(is);
	}

}