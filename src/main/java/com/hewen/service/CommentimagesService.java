package com.hewen.service;

import java.util.List;

import com.hewen.entity.query.CommentimagesQuery;
import com.hewen.entity.po.Commentimages;
import com.hewen.entity.vo.PaginationResultVO;


/**
 * 
 * 评论图片表 业务接口
 * 
 */
public interface CommentimagesService {

	/**
	 * 根据条件查询列表
	 */
	List<Commentimages> findListByParam(CommentimagesQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(CommentimagesQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Commentimages> findListByPage(CommentimagesQuery param);

	/**
	 * 新增
	 */
	Integer add(Commentimages bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Commentimages> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Commentimages> listBean);

	/**
	 * 根据ImageId查询对象
	 */
	Commentimages getCommentimagesByImageId(String imageId);


	/**
	 * 根据ImageId修改
	 */
	Integer updateCommentimagesByImageId(Commentimages bean,String imageId);


	/**
	 * 根据ImageId删除
	 */
	Integer deleteCommentimagesByImageId(String imageId);

}