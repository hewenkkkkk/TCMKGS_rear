package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import com.hewen.entity.enums.DateTimePatternEnum;
import com.hewen.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 
 * 问题信息表
 * 
 */
public class Questions implements Serializable {


	/**
	 * 问题的唯一标识
	 */
	private String questionId;

	/**
	 * 提问者的用户ID
	 */
	private String userId;

	/**
	 * 提问者的邮箱地址
	 */
	private String email;

	/**
	 * 问题内容
	 */
	private String content;


	/**
	 * 提问创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;


	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return this.questionId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}


	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getCreatedAt(){
		return this.createdAt;
	}

	@Override
	public String toString (){
		return "问题的唯一标识:"+(questionId == null ? "空" : questionId)+"，提问者的用户ID:"+(userId == null ? "空" : userId)+"，提问者的邮箱地址:"+(email == null ? "空" : email)+"，问题内容:"+(content == null ? "空" : content)+"，提问时上传的图片链接:"+"，提问创建时间:"+(createdAt == null ? "空" : DateUtil.format(createdAt, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()));
	}
}
