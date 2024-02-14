package com.hewen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.CommentimagesQuery;
import com.hewen.entity.po.Commentimages;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.CommentimagesMapper;
import com.hewen.service.CommentimagesService;


/**
 * 
 * 评论图片表 业务接口实现
 * 
 */
@Service("commentimagesService")
public class CommentimagesServiceImpl implements CommentimagesService {

	@Resource
	private CommentimagesMapper<Commentimages,CommentimagesQuery> commentimagesMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Commentimages> findListByParam(CommentimagesQuery param) {
		return this.commentimagesMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(CommentimagesQuery param) {
		return this.commentimagesMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Commentimages> findListByPage(CommentimagesQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Commentimages> list = this.findListByParam(param);
		PaginationResultVO<Commentimages> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Commentimages bean){
		return this.commentimagesMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Commentimages> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.commentimagesMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Commentimages> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.commentimagesMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据ImageId获取对象
	 */
	@Override
	public Commentimages getCommentimagesByImageId(String imageId){
		return this.commentimagesMapper.selectByImageId(imageId);
	}

	/**
	 * 根据ImageId修改
	 */
	@Override
	public Integer updateCommentimagesByImageId(Commentimages bean,String imageId){
		return this.commentimagesMapper.updateByImageId(bean,imageId);
	}

	/**
	 * 根据ImageId删除
	 */
	@Override
	public Integer deleteCommentimagesByImageId(String imageId){
		return this.commentimagesMapper.deleteByImageId(imageId);
	}
}