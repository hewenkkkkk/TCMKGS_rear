package com.hewen.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hewen.entity.constants.Constants;
import com.hewen.entity.dto.SessionUserDto;
import com.hewen.entity.enums.PageSize;
import com.hewen.entity.po.PhoneCode;
import com.hewen.entity.po.UserInfo;
import com.hewen.entity.query.PhoneCodeQuery;
import com.hewen.entity.query.SimplePage;
import com.hewen.entity.query.UserInfoQuery;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.mappers.PhoneCodeMapper;
import com.hewen.mappers.UserInfoMapper;
import com.hewen.service.UserInfoService;
import com.hewen.utils.AvatarGenerator;
import com.hewen.utils.SnowflakeIdWorker;
import com.hewen.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 用户信息 业务接口实现
 * 
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper<UserInfo,UserInfoQuery> userInfoMapper;

	@Resource
	private PhoneCodeMapper<PhoneCode, PhoneCodeQuery> phoneCodeMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserInfo> findListByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserInfoQuery param) {
		return this.userInfoMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserInfo> findListByPage(UserInfoQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserInfo> list = this.findListByParam(param);
		PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserInfo bean){
		return this.userInfoMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserInfo> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserInfo> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public UserInfo getUserInfoByUserId(String userId){
		return this.userInfoMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUserInfoByUserId(UserInfo bean,String userId){
		return this.userInfoMapper.updateByUserId(bean,userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUserInfoByUserId(String userId){
		return this.userInfoMapper.deleteByUserId(userId);
	}

	/**
	 * 根据Email获取对象
	 */
	@Override
	public UserInfo getUserInfoByEmail(String email){
		return this.userInfoMapper.selectByEmail(email);
	}

	/**
	 * 根据Email修改
	 */
	@Override
	public Integer updateUserInfoByEmail(UserInfo bean,String email){
		return this.userInfoMapper.updateByEmail(bean,email);
	}

	/**
	 * 根据Email删除
	 */
	@Override
	public Integer deleteUserInfoByEmail(String email){
		return this.userInfoMapper.deleteByEmail(email);
	}

	/**
	 * 根据NickName获取对象
	 */
	@Override
	public UserInfo getUserInfoByNickName(String nickName){
		return this.userInfoMapper.selectByNickName(nickName);
	}

	/**
	 * 根据NickName修改
	 */
	@Override
	public Integer updateUserInfoByNickName(UserInfo bean,String nickName){
		return this.userInfoMapper.updateByNickName(bean,nickName);
	}

	/**
	 * 根据NickName删除
	 */
	@Override
	public Integer deleteUserInfoByNickName(String nickName){
		return this.userInfoMapper.deleteByNickName(nickName);
	}

	/**
	 * 根据QqOpenId获取对象
	 */
	@Override
	public UserInfo getUserInfoByQqOpenId(String qqOpenId){
		return this.userInfoMapper.selectByQqOpenId(qqOpenId);
	}

	/**
	 * 根据QqOpenId修改
	 */
	@Override
	public Integer updateUserInfoByQqOpenId(UserInfo bean,String qqOpenId){
		return this.userInfoMapper.updateByQqOpenId(bean,qqOpenId);
	}

	/**
	 * 根据QqOpenId删除
	 */
	@Override
	public Integer deleteUserInfoByQqOpenId(String qqOpenId){
		return this.userInfoMapper.deleteByQqOpenId(qqOpenId);
	}

	@Override
	public String register(String bean){
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonDatas = null;
		try {
			jsonDatas = objectMapper.readValue(bean, new TypeReference<Map<String, Object>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		assert jsonDatas != null;
		String nickName = (String) jsonDatas.get("nickName");
		String email = (String) jsonDatas.get("email");
		String password = (String) jsonDatas.get("password");
		String phone = (String) jsonDatas.get("phone");
		String code = (String) jsonDatas.get("code");
		//验证nickName是否已经注册
		UserInfo userInfoByNickName = userInfoMapper.selectByNickName(nickName);
		if (userInfoByNickName != null) {
			return "昵称已被注册,请重新输入";
		}
		//验证短信验证码
		String s = phoneCodeMapper.selectCodeByPhone(phone);
		if (s.equals(code)) {
			SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 1);
			String userId = String.valueOf(idWorker.nextId());
			//生成用户默认头像
			String avaPath = AvatarGenerator.generateAvatar(nickName,"user"+userId);
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userId);
			userInfo.setNickName(nickName);
			userInfo.setEmail(email);
			userInfo.setQqAvatar(avaPath);
			userInfo.setPassword(StringTools.encodeByMD5(password));
			userInfo.setPhone(phone);
			userInfo.setJoinTime(new Date());
			userInfo.setStatus(Constants.USER_STATUS_1);
			userInfoMapper.insert(userInfo);
			//验证码状态设置为已使用
			phoneCodeMapper.updateStatusByCode(code);
			return "注册成功";
		} else {
			return "验证码错误";
		}

	}

	@Override
	public Boolean verifyUser(String nickName, String password) {
		String encodeByMD5 = StringTools.encodeByMD5(password);
		UserInfo userInfo = userInfoMapper.selectByNickName(nickName);
		if (userInfo != null && userInfo.getPassword().equals(encodeByMD5)) {
			return true;
		}	else {
			return false;
		}
	}


}