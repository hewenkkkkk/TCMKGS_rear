package com.hewen.service;

import java.util.List;
import java.util.Map;

import com.hewen.entity.query.RelationshipsQuery;
import com.hewen.entity.po.Relationships;
import com.hewen.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface RelationshipsService {

	/**
	 * 根据条件查询列表
	 */
	List<Relationships> findListByParam(RelationshipsQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(RelationshipsQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Relationships> findListByPage(RelationshipsQuery param);

	/**
	 * 新增
	 */
	Integer add(Relationships bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Relationships> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Relationships> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Relationships bean,RelationshipsQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(RelationshipsQuery param);

	/**
	 * 根据Id查询对象
	 */
	Relationships getRelationshipsById(Integer id);


	/**
	 * 根据Id修改
	 */
	Integer updateRelationshipsById(Relationships bean,Integer id);


	/**
	 * 根据Id删除
	 */
	Integer deleteRelationshipsById(Integer id);

	/**
	 * 统计关系数量
	 * @return
	 */
	Long countRelationships();

	/**
	 * 统计关系type数量和种类
	 * @return
	 */
	List<Map<String, Long>> countRelationshipType();
}