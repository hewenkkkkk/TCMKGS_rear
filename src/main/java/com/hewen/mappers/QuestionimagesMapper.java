package com.hewen.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * 问题图片表 数据库操作接口
 * 
 */
public interface QuestionimagesMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据ImageId更新
	 */
	 Integer updateByImageId(@Param("bean") T t,@Param("imageId") String imageId);


	/**
	 * 根据ImageId删除
	 */
	 Integer deleteByImageId(@Param("imageId") String imageId);


	/**
	 * 根据ImageId获取对象
	 */
	 T selectByImageId(@Param("imageId") String imageId);


}
