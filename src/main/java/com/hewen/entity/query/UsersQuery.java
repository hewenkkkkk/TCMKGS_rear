package com.hewen.entity.query;



/**
 * 
 * 用户信息表参数
 * 
 */
public class UsersQuery extends BaseParam {


	/**
	 * 用户的唯一标识
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 随机生成的用户名
	 */
	private String username;

	private String usernameFuzzy;

	/**
	 * 基于用户名首字母生成的头像URL
	 */
	private String avatar;

	private String avatarFuzzy;


	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUserIdFuzzy(String userIdFuzzy){
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy(){
		return this.userIdFuzzy;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsernameFuzzy(String usernameFuzzy){
		this.usernameFuzzy = usernameFuzzy;
	}

	public String getUsernameFuzzy(){
		return this.usernameFuzzy;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return this.avatar;
	}

	public void setAvatarFuzzy(String avatarFuzzy){
		this.avatarFuzzy = avatarFuzzy;
	}

	public String getAvatarFuzzy(){
		return this.avatarFuzzy;
	}

}
