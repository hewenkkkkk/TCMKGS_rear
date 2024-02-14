package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 *
 */
public class Nodes implements Serializable {


	/**
	 *
	 */
	private String id;

	/**
	 *
	 */
	private String name;

	/**
	 *
	 */
	private String imgurl;

	/**
	 *
	 */
	private String type;

	/**
	 *
	 */
	private String attributes;


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setImgurl(String imgurl){
		this.imgurl = imgurl;
	}

	public String getImgurl(){
		return this.imgurl;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setAttributes(String attributes){
		this.attributes = attributes;
	}

	public String getAttributes(){
		return this.attributes;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，name:"+(name == null ? "空" : name)+"，imgurl:"+(imgurl == null ? "空" : imgurl)+"，type:"+(type == null ? "空" : type)+"，attributes:"+(attributes == null ? "空" : attributes);
	}
}
