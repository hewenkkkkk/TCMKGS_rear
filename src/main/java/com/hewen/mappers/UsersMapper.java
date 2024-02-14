package com.hewen.mappers;

import com.hewen.entity.vo.que.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 用户信息表 数据库操作接口
 * 
 */
public interface UsersMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 根据UserId更新
	 */
	 Integer updateByUserId(@Param("bean") T t,@Param("userId") String userId);


	/**
	 * 根据UserId删除
	 */
	 Integer deleteByUserId(@Param("userId") String userId);


	/**
	 * 根据UserId获取对象
	 */
	 UserVo selectByUserId(@Param("userId") String userId);


}
