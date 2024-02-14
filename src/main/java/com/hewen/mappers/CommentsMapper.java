package com.hewen.mappers;

import com.hewen.entity.po.SubComment;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 评论信息表 数据库操作接口
 * 
 */
public interface CommentsMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据CommentId更新
	 */
	 Integer updateByCommentId(@Param("bean") T t,@Param("commentId") String commentId);


	/**
	 * 根据CommentId删除
	 */
	 Integer deleteByCommentId(@Param("commentId") String commentId);


	/**
	 * 根据CommentId获取对象
	 */
	 T selectByCommentId(@Param("commentId") String commentId);

	 Integer addSubComment(SubComment bean);

}
