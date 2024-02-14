package com.hewen.entity.query;



/**
 * 参数
 */
public class NodeTypesQuery extends BaseParam {


	/**
	 * 
	 */
	private Integer typeId;

	/**
	 * 
	 */
	private String typeName;

	private String typeNameFuzzy;


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

	public void setTypeNameFuzzy(String typeNameFuzzy){
		this.typeNameFuzzy = typeNameFuzzy;
	}

	public String getTypeNameFuzzy(){
		return this.typeNameFuzzy;
	}

}
