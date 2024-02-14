package com.hewen.mappers;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  数据库操作接口
 */
public interface RelationshipsMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据Id更新
	 */
	Integer updateById(@Param("bean") T t,@Param("id") Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteById(@Param("id") Integer id);


	/**
	 * 根据Id获取对象
	 */
	T selectById(@Param("id") Integer id);


    Long countRelationships();


	@MapKey("type")
	List<Map<String, Long>> countRelationshipType();
}
