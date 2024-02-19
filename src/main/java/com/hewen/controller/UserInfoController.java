package com.hewen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hewen.entity.component.RedisUtils;
import com.hewen.entity.po.UserInfo;
import com.hewen.entity.query.UserInfoQuery;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.entity.vo.UserInfoVo;
import com.hewen.service.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * 
 * 用户信息 Controller
 * 
 */
@RestController("userInfoController")
@RequestMapping("userInfo")
public class UserInfoController extends ABaseController{

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private RedisUtils<UserInfo> redisUtils;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("loadDataList")
	public ResponseVO loadDataList(UserInfoQuery query){
		return getSuccessResponseVO(userInfoService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(UserInfo bean) {
		userInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<UserInfo> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<UserInfo> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId查询对象
	 */
	@RequestMapping("getUserInfoByUserId")
	public ResponseVO getUserInfoByUserId(String userId) {
		return getSuccessResponseVO(userInfoService.getUserInfoByUserId(userId));
	}

	/**
	 * 根据UserId修改对象
	 */
	@RequestMapping("updateUserInfoByUserId")
	public ResponseVO updateUserInfoByUserId(UserInfo bean,String userId) {
		userInfoService.updateUserInfoByUserId(bean,userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId删除
	 */
	@RequestMapping("deleteUserInfoByUserId")
	public ResponseVO deleteUserInfoByUserId(String userId) {
		userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Email查询对象
	 */
	@RequestMapping("getUserInfoByEmail")
	public ResponseVO getUserInfoByEmail(String email) {
		return getSuccessResponseVO(userInfoService.getUserInfoByEmail(email));
	}

	/**
	 * 根据Email修改对象
	 */
	@RequestMapping("updateUserInfoByEmail")
	public ResponseVO updateUserInfoByEmail(UserInfo bean,String email) {
		userInfoService.updateUserInfoByEmail(bean,email);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Email删除
	 */
	@RequestMapping("deleteUserInfoByEmail")
	public ResponseVO deleteUserInfoByEmail(String email) {
		userInfoService.deleteUserInfoByEmail(email);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据NickName查询对象
	 */
	@RequestMapping("getUserInfoByNickName")
	public ResponseVO getUserInfoByNickName(String nickName) {
		return getSuccessResponseVO(userInfoService.getUserInfoByNickName(nickName));
	}

	/**
	 * 根据NickName修改对象
	 */
	@RequestMapping("updateUserInfoByNickName")
	public ResponseVO updateUserInfoByNickName(UserInfo bean,String nickName) {
		userInfoService.updateUserInfoByNickName(bean,nickName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据NickName删除
	 */
	@RequestMapping("deleteUserInfoByNickName")
	public ResponseVO deleteUserInfoByNickName(String nickName) {
		userInfoService.deleteUserInfoByNickName(nickName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据QqOpenId查询对象
	 */
	@RequestMapping("getUserInfoByQqOpenId")
	public ResponseVO getUserInfoByQqOpenId(String qqOpenId) {
		return getSuccessResponseVO(userInfoService.getUserInfoByQqOpenId(qqOpenId));
	}

	/**
	 * 根据QqOpenId修改对象
	 */
	@RequestMapping("updateUserInfoByQqOpenId")
	public ResponseVO updateUserInfoByQqOpenId(UserInfo bean,String qqOpenId) {
		userInfoService.updateUserInfoByQqOpenId(bean,qqOpenId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据QqOpenId删除
	 */
	@RequestMapping("deleteUserInfoByQqOpenId")
	public ResponseVO deleteUserInfoByQqOpenId(String qqOpenId) {
		userInfoService.deleteUserInfoByQqOpenId(qqOpenId);
		return getSuccessResponseVO(null);
	}



	/**
	 * 注册
	 {
	 "nickName": "string",
	 "email": "string",
	 "phone": "string",
	 "password": "string",
	 "code": "string",
	 }
	 */
	@RequestMapping("register")
	public ResponseVO register(@RequestBody String userInfo) throws JsonProcessingException {
		String register = userInfoService.register(userInfo);
		return getSuccessResponseVO(register);
	}

	/**
	 * 登录
	 {
	 "nickName": "string",
	 "password": "string",
	 rememberMe: true
	 }
	 */
	@PostMapping("/login")
	public ResponseVO loginUser(@RequestParam String username, @RequestParam String password,
								@RequestParam(required = false) boolean rememberMe,
								HttpServletRequest request, HttpServletResponse response) {

		// 验证用户名和密码
		Boolean aBoolean = userInfoService.verifyUser(username, password);
		if (!aBoolean) {
			return getFailResponseVO("用户名或密码错误`");
		}
		UserInfo userInfo = userInfoService.getUserInfoByNickName(username);
		String sessionId = UUID.randomUUID().toString();
		// 保存用户信息到redis
		redisUtils.setex(sessionId, userInfo, 30 * 60);	// 30分钟
		// 保存sessionId到cookie
		Cookie cookie = new Cookie("sessionId", sessionId);
		response.addCookie(cookie);

		// 处理记住我功能
		if (rememberMe) {
			String rememberToken = UUID.randomUUID().toString();
			redisUtils.setex("rememberMe:" + rememberToken, userInfo, 60 * 60 * 24 * 30); // 30天
			Cookie rememberMeCookie = new Cookie("REMEMBERME", rememberToken);
			rememberMeCookie.setMaxAge(60 * 60 * 24 * 30); // 30天
			response.addCookie(rememberMeCookie);
		}
		ModelMapper modelMapper = new ModelMapper();
		UserInfoVo userInfoVo = modelMapper.map(userInfo, UserInfoVo.class);
		return getSuccessResponseVO(userInfoVo);
	}

}