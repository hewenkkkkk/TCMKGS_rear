package com.hewen.service;

import java.util.List;
import java.util.Map;

import com.hewen.entity.query.NodesQuery;
import com.hewen.entity.po.Nodes;
import com.hewen.entity.vo.PaginationResultVO;


/**
 *  业务接口
 */
public interface NodesService {

	/**
	 * 根据条件查询列表
	 */
	List<Nodes> findListByParam(NodesQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(NodesQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Nodes> findListByPage(NodesQuery param);

	/**
	 * 新增
	 */
	Integer add(Nodes bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Nodes> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Nodes> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(Nodes bean,NodesQuery param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(NodesQuery param);

	/**
	 * 根据Id查询对象
	 */
	Nodes getNodesById(String id);


	/**
	 * 根据Id修改
	 */
	Integer updateNodesById(Nodes bean,String id);


	/**
	 * 根据Id删除
	 */
	Integer deleteNodesById(String id);


	/**
	 * 修改节点信息MySql
	 * @return
	 */
	List<Map<String, Long>> countNodesByType();

	/**
	 * 获取节点个数
	 */
	Long countNodes();

	/**
	 * 查询所有Type
	 */
	List<String> findAllType();
}