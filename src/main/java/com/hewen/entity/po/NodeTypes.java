package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;


/**
 * 
 */
public class NodeTypes implements Serializable {


	/**
	 * 
	 */
	private Integer typeId;

	/**
	 * 
	 */
	private String typeName;

	public NodeTypes(String nodeType) {
		this.typeName = nodeType;
	}


    public void setTypeId(Integer typeId){
		this.typeId = typeId;
	}

	public Integer getTypeId(){
		return this.typeId;
	}

	public void setTypeName(String typeName){
		this.typeName = typeName;
	}

	public String getTypeName(){
		return this.typeName;
	}

	@Override
	public String toString (){
		return "typeId:"+(typeId == null ? "空" : typeId)+"，typeName:"+(typeName == null ? "空" : typeName);
	}
}
