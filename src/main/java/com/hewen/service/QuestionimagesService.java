package com.hewen.service;

import java.util.List;

import com.hewen.entity.query.QuestionimagesQuery;
import com.hewen.entity.po.Questionimages;
import com.hewen.entity.vo.PaginationResultVO;


/**
 * 
 * 问题图片表 业务接口
 * 
 */
public interface QuestionimagesService {

	/**
	 * 根据条件查询列表
	 */
	List<Questionimages> findListByParam(QuestionimagesQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(QuestionimagesQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Questionimages> findListByPage(QuestionimagesQuery param);

	/**
	 * 新增
	 */
	Integer add(Questionimages bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Questionimages> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Questionimages> listBean);

	/**
	 * 根据ImageId查询对象
	 */
	Questionimages getQuestionimagesByImageId(String imageId);


	/**
	 * 根据ImageId修改
	 */
	Integer updateQuestionimagesByImageId(Questionimages bean,String imageId);


	/**
	 * 根据ImageId删除
	 */
	Integer deleteQuestionimagesByImageId(String imageId);

}