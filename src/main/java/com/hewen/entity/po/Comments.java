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
 * 评论信息表
 * 
 */
public class Comments implements Serializable {


	/**
	 * 评论的唯一标识
	 */
	private String commentId;

	/**
	 * 所评论的问题ID
	 */
	private String questionId;

	/**
	 * 评论者的用户ID
	 */
	private String userId;

	/**
	 * 评论内容
	 */
	private String content;



	/**
	 * 提问者的邮箱地址
	 */
	private String email;


	/**
	 * 评论创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	/**
	 * 父评论ID，用于二级评论
	 */
	private String parentId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return this.commentId;
	}

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

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getParentId(){
		return this.parentId;
	}

	@Override
	public String toString() {
		return "Comments{" +
				"commentId='" + commentId + '\'' +
				", questionId='" + questionId + '\'' +
				", userId='" + userId + '\'' +
				", content='" + content + '\'' +
				", email='" + email + '\'' +
				", createdAt=" + createdAt +
				", parentId='" + parentId + '\'' +
				'}';
	}
}