package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 * 问题图片表
 * 
 */
public class Questionimages implements Serializable {


	/**
	 * 图片的唯一标识
	 */
	private String imageId;

	/**
	 * 关联的问题ID
	 */
	private String questionId;

	/**
	 * 图片链接
	 */
	private String imageUrl;


	public void setImageId(String imageId){
		this.imageId = imageId;
	}

	public String getImageId(){
		return this.imageId;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return this.questionId;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return this.imageUrl;
	}

	@Override
	public String toString (){
		return "图片的唯一标识:"+(imageId == null ? "空" : imageId)+"，关联的问题ID:"+(questionId == null ? "空" : questionId)+"，图片链接:"+(imageUrl == null ? "空" : imageUrl);
	}
}
