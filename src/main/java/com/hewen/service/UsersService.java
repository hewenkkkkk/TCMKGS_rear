package com.hewen.service;

import java.util.List;

import com.hewen.entity.query.UsersQuery;
import com.hewen.entity.po.Users;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.vo.que.UserVo;


/**
 * 
 * 用户信息表 业务接口
 * 
 */
public interface UsersService {

	/**
	 * 根据条件查询列表
	 */
	List<Users> findListByParam(UsersQuery param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UsersQuery param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<Users> findListByPage(UsersQuery param);

	/**
	 * 新增
	 */
	Integer add(Users bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<Users> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<Users> listBean);

	/**
	 * 根据UserId查询对象
	 */
	UserVo getUsersByUserId(String userId);


	/**
	 * 根据UserId修改
	 */
	Integer updateUsersByUserId(Users bean,String userId);


	/**
	 * 根据UserId删除
	 */
	Integer deleteUsersByUserId(String userId);

	String addQuestionUserInfo(String username,String userId);

}