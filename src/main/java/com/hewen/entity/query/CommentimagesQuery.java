package com.hewen.entity.query;



/**
 * 
 * 评论图片表参数
 * 
 */
public class CommentimagesQuery extends BaseParam {


	/**
	 * 图片的唯一标识
	 */
	private String imageId;

	private String imageIdFuzzy;

	/**
	 * 关联的评论ID
	 */
	private String commentId;

	private String commentIdFuzzy;

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
