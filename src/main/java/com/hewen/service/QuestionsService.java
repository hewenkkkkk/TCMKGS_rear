package com.hewen.service;

import java.util.List;

import com.hewen.entity.query.QuestionsQuery;
import com.hewen.entity.po.Questions;
import com.hewen.entity.vo.NewQuestions;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.vo.que.QuestionVo;


/**
 * 
 * 问题信息表 业务接口
 * 
 */
public interface QuestionsService {

	/**
	 * 根据条件查询列表
	 */
	List<Questions> findListByParam(QuestionsQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(QuestionsQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Questions> findListByPage(QuestionsQuery param);

	/**
	 * 新增
	 */
	Integer add(Questions bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Questions> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Questions> listBean);

	/**
	 * 根据QuestionId查询对象
	 */
	Questions getQuestionsByQuestionId(String questionId);


	/**
	 * 根据QuestionId修改
	 */
	Integer updateQuestionsByQuestionId(Questions bean,String questionId);


	/**
	 * 根据QuestionId删除
	 */
	Integer deleteQuestionsByQuestionId(String questionId);

	String addQuestion(String jsonData);

	List<NewQuestions> getNewQuestions();


	QuestionVo getQuestionsAndComments(String userId);

}