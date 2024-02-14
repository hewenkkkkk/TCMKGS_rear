package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 *
 */
public class Relationships implements Serializable {


	/**
	 *
	 */
	private Integer id;

	/**
	 *
	 */
	private String source;

	/**
	 *
	 */
	private String target;

	/**
	 *
	 */
	private String type;

	/**
	 *
	 */
	private String attributes;


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return this.source;
	}

	public void setTarget(String target){
		this.target = target;
	}

	public String getTarget(){
		return this.target;
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
		return "id:"+(id == null ? "空" : id)+"，source:"+(source == null ? "空" : source)+"，target:"+(target == null ? "空" : target)+"，type:"+(type == null ? "空" : type)+"，attributes:"+(attributes == null ? "空" : attributes);
	}
}
