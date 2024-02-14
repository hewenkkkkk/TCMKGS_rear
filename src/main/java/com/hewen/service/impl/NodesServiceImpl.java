package com.hewen.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.NodesQuery;
import com.hewen.entity.po.Nodes;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.NodesMapper;
import com.hewen.service.NodesService;
import com.hewen.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("nodesService")
public class NodesServiceImpl implements NodesService {

	@Resource
	private NodesMapper<Nodes, NodesQuery> nodesMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Nodes> findListByParam(NodesQuery param) {
		return this.nodesMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(NodesQuery param) {
		return this.nodesMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Nodes> findListByPage(NodesQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Nodes> list = this.findListByParam(param);
		PaginationResultVO<Nodes> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Nodes bean) {
		return this.nodesMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Nodes> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.nodesMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Nodes> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.nodesMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Nodes bean, NodesQuery param) {
		StringTools.checkParam(param);
		return this.nodesMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(NodesQuery param) {
		StringTools.checkParam(param);
		return this.nodesMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Nodes getNodesById(String id) {
		return this.nodesMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateNodesById(Nodes bean, String id) {
		return this.nodesMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteNodesById(String id) {
		return this.nodesMapper.deleteById(id);
	}

	@Override
	public List<Map<String, Long>> countNodesByType() {
		return this.nodesMapper.countNodesByType();
	}

	@Override
	public Long countNodes() {
		return this.nodesMapper.countNodes();
	}

	@Override
	public List<String> findAllType() {
		return this.nodesMapper.findAllType();
	}
}