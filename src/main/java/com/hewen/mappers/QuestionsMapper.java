package com.hewen.mappers;

import com.hewen.entity.vo.NewQuestions;
import com.hewen.entity.vo.que.CommentImageVo;
import com.hewen.entity.vo.que.CommentVo;
import com.hewen.entity.vo.que.QuestionImageVo;
import com.hewen.entity.vo.que.QuestionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 问题信息表 数据库操作接口
 * 
 */
public interface QuestionsMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据QuestionId更新
	 */
	 Integer updateByQuestionId(@Param("bean") T t,@Param("questionId") String questionId);


	/**
	 * 根据QuestionId删除
	 */
	 Integer deleteByQuestionId(@Param("questionId") String questionId);


	/**
	 * 根据QuestionId获取对象
	 */
	 T selectByQuestionId(@Param("questionId") String questionId);


	 List<NewQuestions> selectByQuestionIds();


	QuestionVo selectQuestionById(String questionId);
	List<QuestionImageVo> selectImagesByQuestionId(String questionId);
	List<CommentVo> selectCommentsByQuestionId(String questionId);
	List<CommentImageVo> selectImagesByCommentId(String commentId);
	List<CommentVo> selectSubCommentsByParentId(String parentId);


}
