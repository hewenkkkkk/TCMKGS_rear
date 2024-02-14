package com.hewen.entity.query;

import java.util.Date;


/**
 * 
 * 问题信息表参数
 * 
 */
public class QuestionsQuery extends BaseParam {


	/**
	 * 问题的唯一标识
	 */
	private String questionId;

	private String questionIdFuzzy;

	/**
	 * 提问者的用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 提问者的邮箱地址
	 */
	private String email;

	private String emailFuzzy;

	/**
	 * 问题内容
	 */
	private String content;

	private String contentFuzzy;



	/**
	 * 提问创建时间
	 */
	private String createdAt;

	private String createdAtStart;

	private String createdAtEnd;


	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return this.questionId;
	}

	public void setQuestionIdFuzzy(String questionIdFuzzy){
		this.questionIdFuzzy = questionIdFuzzy;
	}

	public String getQuestionIdFuzzy(){
		return this.questionIdFuzzy;
	}

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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmailFuzzy(String emailFuzzy){
		this.emailFuzzy = emailFuzzy;
	}

	public String getEmailFuzzy(){
		return this.emailFuzzy;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setContentFuzzy(String contentFuzzy){
		this.contentFuzzy = contentFuzzy;
	}

	public String getContentFuzzy(){
		return this.contentFuzzy;
	}


	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return this.createdAt;
	}

	public void setCreatedAtStart(String createdAtStart){
		this.createdAtStart = createdAtStart;
	}

	public String getCreatedAtStart(){
		return this.createdAtStart;
	}
	public void setCreatedAtEnd(String createdAtEnd){
		this.createdAtEnd = createdAtEnd;
	}

	public String getCreatedAtEnd(){
		return this.createdAtEnd;
	}

}
