package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 * 用户信息表
 * 
 */
public class Users implements Serializable {


	/**
	 * 用户的唯一标识
	 */
	private String userId;

	/**
	 * 随机生成的用户名
	 */
	private String username;

	/**
	 * 基于用户名首字母生成的头像URL
	 */
	private String avatar;


	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return this.avatar;
	}

	@Override
	public String toString (){
		return "用户的唯一标识:"+(userId == null ? "空" : userId)+"，随机生成的用户名:"+(username == null ? "空" : username)+"，基于用户名首字母生成的头像URL:"+(avatar == null ? "空" : avatar);
	}
}
