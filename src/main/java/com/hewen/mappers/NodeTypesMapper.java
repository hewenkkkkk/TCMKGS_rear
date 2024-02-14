package com.hewen.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  数据库操作接口
 */
public interface NodeTypesMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据TypeId更新
	 */
	 Integer updateByTypeId(@Param("bean") T t,@Param("typeId") Integer typeId);


	/**
	 * 根据TypeId删除
	 */
	 Integer deleteByTypeId(@Param("typeId") Integer typeId);


	/**
	 * 根据TypeId获取对象
	 */
	 T selectByTypeId(@Param("typeId") Integer typeId);


	/**
	 * 根据TypeName更新
	 */
	 Integer updateByTypeName(@Param("bean") T t,@Param("typeName") String typeName);


	/**
	 * 根据TypeName删除
	 */
	 Integer deleteByTypeName(@Param("typeName") String typeName);


	/**
	 * 根据TypeName获取对象
	 */
	 T selectByTypeName(@Param("typeName") String typeName);

	Boolean exists(String nodeType);

    List<String> selectAllTypes();
}
