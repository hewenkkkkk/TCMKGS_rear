package com.hewen.entity.query;



/**
 * 
 * 问题图片表参数
 * 
 */
public class QuestionimagesQuery extends BaseParam {


	/**
	 * 图片的唯一标识
	 */
	private String imageId;

	private String imageIdFuzzy;

	/**
	 * 关联的问题ID
	 */
	private String questionId;

	private String questionIdFuzzy;

	/**
	 * 图片链接
	 */
	private String imageUrl;

	private String imageUrlFuzzy;


	public void setImageId(String imageId){
		this.imageId = imageId;
	}

	public String getImageId(){
		return this.imageId;
	}

	public void setImageIdFuzzy(String imageIdFuzzy){
		this.imageIdFuzzy = imageIdFuzzy;
	}

	public String getImageIdFuzzy(){
		return this.imageIdFuzzy;
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

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return this.imageUrl;
	}

	public void setImageUrlFuzzy(String imageUrlFuzzy){
		this.imageUrlFuzzy = imageUrlFuzzy;
	}

	public String getImageUrlFuzzy(){
		return this.imageUrlFuzzy;
	}

}
