package com.hewen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hewen.entity.vo.que.UserVo;
import com.hewen.utils.AvatarGenerator;
import com.hewen.utils.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import com.hewen.entity.enums.PageSize;
import com.hewen.entity.query.UsersQuery;
import com.hewen.entity.po.Users;
import com.hewen.entity.vo.PaginationResultVO;
import com.hewen.entity.query.SimplePage;
import com.hewen.mappers.UsersMapper;
import com.hewen.service.UsersService;


/**
 * 
 * 用户信息表 业务接口实现
 * 
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Resource
	private UsersMapper<Users,UsersQuery> usersMapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<Users> findListByParam(UsersQuery param) {
		return this.usersMapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UsersQuery param) {
		return this.usersMapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<Users> findListByPage(UsersQuery param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<Users> list = this.findListByParam(param);
		PaginationResultVO<Users> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(),page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(Users bean){
		return this.usersMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<Users> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.usersMapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<Users> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.usersMapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public UserVo getUsersByUserId(String userId){
		return this.usersMapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUsersByUserId(Users bean,String userId){
		return this.usersMapper.updateByUserId(bean,userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUsersByUserId(String userId){
		return this.usersMapper.deleteByUserId(userId);
	}

	@Override
	public String addQuestionUserInfo(String username,String userId) {

		//生成提问用户头像
		String avaPath = AvatarGenerator.generateAvatar(username,userId);
		Users users = new Users();
		users.setUserId(userId);
		users.setUsername(username);
		users.setAvatar(avaPath);
		this.usersMapper.insert(users);
//		if (this.usersMapper.insert(users)==0){
//			return "error";
//		}else return "success";
		return null;


	}
}