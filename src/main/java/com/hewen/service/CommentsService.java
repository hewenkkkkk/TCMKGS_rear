package com.hewen.service;

import java.util.List;

import com.hewen.entity.po.SubComment;
import com.hewen.entity.query.CommentsQuery;
import com.hewen.entity.po.Comments;
import com.hewen.entity.vo.PaginationResultVO;


/**
 * 
 * 评论信息表 业务接口
 * 
 */
public interface CommentsService {

	/**
	 * 根据条件查询列表
	 */
	List<Comments> findListByParam(CommentsQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(CommentsQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Comments> findListByPage(CommentsQuery param);

	/**
	 * 新增
	 */
	Integer add(Comments bean);

	Integer addSubComments(SubComment bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Comments> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Comments> listBean);

	/**
	 * 根据CommentId查询对象
	 */
	Comments getCommentsByCommentId(String commentId);


	/**
	 * 根据CommentId修改
	 */
	Integer updateCommentsByCommentId(Comments bean,String commentId);


	/**
	 * 根据CommentId删除
	 */
	Integer deleteCommentsByCommentId(String commentId);

	String addQuestionComments(String jsonData);

	String addSubComments(String jsonData);

}