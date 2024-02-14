package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 * 评论图片表
 * 
 */
public class Commentimages implements Serializable {


	/**
	 * 图片的唯一标识
	 */
	private String imageId;

	/**
	 * 关联的评论ID
	 */
	private String commentId;

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

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return this.commentId;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return this.imageUrl;
	}

	@Override
	public String toString (){
		return "图片的唯一标识:"+(imageId == null ? "空" : imageId)+"，关联的评论ID:"+(commentId == null ? "空" : commentId)+"，图片链接:"+(imageUrl == null ? "空" : imageUrl);
	}
}
