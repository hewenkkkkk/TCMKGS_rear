package com.hewen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.NodeTypesQuery;
import com.hewen.entity.po.NodeTypes;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.NodeTypesMapper;
import com.hewen.service.NodeTypesService;
import com.hewen.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("nodeTypesService")
public class NodeTypesServiceImpl implements NodeTypesService {

	@Resource
	private NodeTypesMapper<NodeTypes, NodeTypesQuery> nodeTypesMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<NodeTypes> findListByParam(NodeTypesQuery param) {
		return this.nodeTypesMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(NodeTypesQuery param) {
		return this.nodeTypesMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<NodeTypes> findListByPage(NodeTypesQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<NodeTypes> list = this.findListByParam(param);
		PaginationResultVO<NodeTypes> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(NodeTypes bean) {
		return this.nodeTypesMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<NodeTypes> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.nodeTypesMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<NodeTypes> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.nodeTypesMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(NodeTypes bean, NodeTypesQuery param) {
		StringTools.checkParam(param);
		return this.nodeTypesMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(NodeTypesQuery param) {
		StringTools.checkParam(param);
		return this.nodeTypesMapper.deleteByParam(param);
	}

	/**
	 * 根据TypeId获取对象
	 */
	@Override
	public NodeTypes getNodeTypesByTypeId(Integer typeId) {
		return this.nodeTypesMapper.selectByTypeId(typeId);
	}

	/**
	 * 根据TypeId修改
	 */
	@Override
	public Integer updateNodeTypesByTypeId(NodeTypes bean, Integer typeId) {
		return this.nodeTypesMapper.updateByTypeId(bean, typeId);
	}

	/**
	 * 根据TypeId删除
	 */
	@Override
	public Integer deleteNodeTypesByTypeId(Integer typeId) {
		return this.nodeTypesMapper.deleteByTypeId(typeId);
	}

	/**
	 * 根据TypeName获取对象
	 */
	@Override
	public NodeTypes getNodeTypesByTypeName(String typeName) {
		return this.nodeTypesMapper.selectByTypeName(typeName);
	}

	/**
	 * 根据TypeName修改
	 */
	@Override
	public Integer updateNodeTypesByTypeName(NodeTypes bean, String typeName) {
		return this.nodeTypesMapper.updateByTypeName(bean, typeName);
	}

	/**
	 * 根据TypeName删除
	 */
	@Override
	public Integer deleteNodeTypesByTypeName(String typeName) {
		return this.nodeTypesMapper.deleteByTypeName(typeName);
	}

	@Override
	public boolean exists(String nodeType) {
		return this.nodeTypesMapper.exists(nodeType);
	}

	public List<String> getAllTypes() {
		return nodeTypesMapper.selectAllTypes();
	}
}