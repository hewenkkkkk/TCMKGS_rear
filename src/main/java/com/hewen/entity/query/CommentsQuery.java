package com.hewen.entity.query;

import java.util.Date;


/**
 * 
 * 评论信息表参数
 * 
 */
public class CommentsQuery extends BaseParam {


	/**
	 * 评论的唯一标识
	 */
	private String commentId;

	private String commentIdFuzzy;

	/**
	 * 所评论的问题ID
	 */
	private String questionId;

	private String questionIdFuzzy;

	/**
	 * 评论者的用户ID
	 */
	private String userId;

	private String userIdFuzzy;

	/**
	 * 评论内容
	 */
	private String content;

	private String contentFuzzy;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailFuzzy() {
		return emailFuzzy;
	}

	public void setEmailFuzzy(String emailFuzzy) {
		this.emailFuzzy = emailFuzzy;
	}

	/**
	 * 提问者的邮箱地址
	 */
	private String email;

	private String emailFuzzy;

	/**
	 * 评论创建时间
	 */
	private String createdAt;

	private String createdAtStart;

	private String createdAtEnd;

	/**
	 * 父评论ID，用于二级评论
	 */
	private String parentId;

	private String parentIdFuzzy;


	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return this.commentId;
	}

	public void setCommentIdFuzzy(String commentIdFuzzy){
		this.commentIdFuzzy = commentIdFuzzy;
	}

	public String getCommentIdFuzzy(){
		return this.commentIdFuzzy;
	}

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

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getParentId(){
		return this.parentId;
	}

	public void setParentIdFuzzy(String parentIdFuzzy){
		this.parentIdFuzzy = parentIdFuzzy;
	}

	public String getParentIdFuzzy(){
		return this.parentIdFuzzy;
	}

}
