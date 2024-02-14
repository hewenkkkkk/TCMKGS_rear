package com.hewen.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.RelationshipsQuery;
import com.hewen.entity.po.Relationships;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.RelationshipsMapper;
import com.hewen.service.RelationshipsService;
import com.hewen.utils.StringTools;


/**
 *  业务接口实现
 */
@Service("relationshipsService")
public class RelationshipsServiceImpl implements RelationshipsService {

	@Resource
	private RelationshipsMapper<Relationships, RelationshipsQuery> relationshipsMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Relationships> findListByParam(RelationshipsQuery param) {
		return this.relationshipsMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(RelationshipsQuery param) {
		return this.relationshipsMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Relationships> findListByPage(RelationshipsQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Relationships> list = this.findListByParam(param);
		PaginationResultVO<Relationships> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Relationships bean) {
		return this.relationshipsMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Relationships> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.relationshipsMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Relationships> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.relationshipsMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(Relationships bean, RelationshipsQuery param) {
		StringTools.checkParam(param);
		return this.relationshipsMapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(RelationshipsQuery param) {
		StringTools.checkParam(param);
		return this.relationshipsMapper.deleteByParam(param);
	}

	/**
	 * 根据Id获取对象
	 */
	@Override
	public Relationships getRelationshipsById(Integer id) {
		return this.relationshipsMapper.selectById(id);
	}

	/**
	 * 根据Id修改
	 */
	@Override
	public Integer updateRelationshipsById(Relationships bean, Integer id) {
		return this.relationshipsMapper.updateById(bean, id);
	}

	/**
	 * 根据Id删除
	 */
	@Override
	public Integer deleteRelationshipsById(Integer id) {
		return this.relationshipsMapper.deleteById(id);
	}

	@Override
	public Long countRelationships() {
		return this.relationshipsMapper.countRelationships();
	}

	@Override
	public List<Map<String, Long>> countRelationshipType() {
		return this.relationshipsMapper.countRelationshipType();
	}
}