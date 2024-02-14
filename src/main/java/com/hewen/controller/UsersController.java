package com.hewen.controller;

import java.util.List;

import com.hewen.entity.po.Users;
import com.hewen.entity.query.UsersQuery;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.UsersService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 
 * 用户信息表 Controller
 * 
 */
@RestController("usersController")
@RequestMapping("users")
public class UsersController extends ABaseController{

	@Resource
	private UsersService usersService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(UsersQuery query){
		return getSuccessResponseVO(usersService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(Users bean) {
		usersService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<Users> listBean) {
		usersService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Users> listBean) {
		usersService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId查询对象
	 */
	@RequestMapping("getUsersByUserId")
	public ResponseVO getUsersByUserId(String userId) {
		return getSuccessResponseVO(usersService.getUsersByUserId(userId));
	}

	/**
	 * 根据UserId修改对象
	 */
	@RequestMapping("updateUsersByUserId")
	public ResponseVO updateUsersByUserId(Users bean,String userId) {
		usersService.updateUsersByUserId(bean,userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId删除
	 */
	@RequestMapping("deleteUsersByUserId")
	public ResponseVO deleteUsersByUserId(String userId) {
		usersService.deleteUsersByUserId(userId);
		return getSuccessResponseVO(null);
	}
}