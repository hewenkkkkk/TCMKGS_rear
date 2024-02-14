package com.hewen.service;

import java.util.List;

import com.hewen.entity.query.NodeTypesQuery;
import com.hewen.entity.po.NodeTypes;
import com.hewen.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface NodeTypesService {

	/**
	 * 根据条件查询列表
	 */
	List<NodeTypes> findListByParam(NodeTypesQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(NodeTypesQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<NodeTypes> findListByPage(NodeTypesQuery param);

	/**
	 * 新增
	 */
	Integer add(NodeTypes bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<NodeTypes> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<NodeTypes> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(NodeTypes bean,NodeTypesQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(NodeTypesQuery param);

	/**
	 * 根据TypeId查询对象
	 */
	NodeTypes getNodeTypesByTypeId(Integer typeId);


	/**
	 * 根据TypeId修改
	 */
	Integer updateNodeTypesByTypeId(NodeTypes bean,Integer typeId);


	/**
	 * 根据TypeId删除
	 */
	Integer deleteNodeTypesByTypeId(Integer typeId);


	/**
	 * 根据TypeName查询对象
	 */
	NodeTypes getNodeTypesByTypeName(String typeName);


	/**
	 * 根据TypeName修改
	 */
	Integer updateNodeTypesByTypeName(NodeTypes bean,String typeName);


	/**
	 * 根据TypeName删除
	 */
	Integer deleteNodeTypesByTypeName(String typeName);

	boolean exists(String nodeType);

	List<String> getAllTypes();
}