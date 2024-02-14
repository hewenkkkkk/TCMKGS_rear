package com.hewen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.QuestionimagesQuery;
import com.hewen.entity.po.Questionimages;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.QuestionimagesMapper;
import com.hewen.service.QuestionimagesService;


/**
 * 
 * 问题图片表 业务接口实现
 * 
 */
@Service("questionimagesService")
public class QuestionimagesServiceImpl implements QuestionimagesService {

	@Resource
	private QuestionimagesMapper<Questionimages,QuestionimagesQuery> questionimagesMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Questionimages> findListByParam(QuestionimagesQuery param) {
		return this.questionimagesMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(QuestionimagesQuery param) {
		return this.questionimagesMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Questionimages> findListByPage(QuestionimagesQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Questionimages> list = this.findListByParam(param);
		PaginationResultVO<Questionimages> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Questionimages bean){
		return this.questionimagesMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Questionimages> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.questionimagesMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Questionimages> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.questionimagesMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据ImageId获取对象
	 */
	@Override
	public Questionimages getQuestionimagesByImageId(String imageId){
		return this.questionimagesMapper.selectByImageId(imageId);
	}

	/**
	 * 根据ImageId修改
	 */
	@Override
	public Integer updateQuestionimagesByImageId(Questionimages bean,String imageId){
		return this.questionimagesMapper.updateByImageId(bean,imageId);
	}

	/**
	 * 根据ImageId删除
	 */
	@Override
	public Integer deleteQuestionimagesByImageId(String imageId){
		return this.questionimagesMapper.deleteByImageId(imageId);
	}
}