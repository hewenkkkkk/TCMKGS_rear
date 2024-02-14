package com.hewen.entity.query;



/**
 * 参数
 */
public class NodesQuery extends BaseParam {


	/**
	 *
	 */
	private String id;

	private String idFuzzy;

	/**
	 *
	 */
	private String name;

	private String nameFuzzy;

	/**
	 *
	 */
	private String imgurl;

	private String imgurlFuzzy;

	/**
	 *
	 */
	private String type;

	private String typeFuzzy;

	/**
	 *
	 */
	private String attributes;

	private String attributesFuzzy;


	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setIdFuzzy(String idFuzzy){
		this.idFuzzy = idFuzzy;
	}

	public String getIdFuzzy(){
		return this.idFuzzy;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setNameFuzzy(String nameFuzzy){
		this.nameFuzzy = nameFuzzy;
	}

	public String getNameFuzzy(){
		return this.nameFuzzy;
	}

	public void setImgurl(String imgurl){
		this.imgurl = imgurl;
	}

	public String getImgurl(){
		return this.imgurl;
	}

	public void setImgurlFuzzy(String imgurlFuzzy){
		this.imgurlFuzzy = imgurlFuzzy;
	}

	public String getImgurlFuzzy(){
		return this.imgurlFuzzy;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setTypeFuzzy(String typeFuzzy){
		this.typeFuzzy = typeFuzzy;
	}

	public String getTypeFuzzy(){
		return this.typeFuzzy;
	}

	public void setAttributes(String attributes){
		this.attributes = attributes;
	}

	public String getAttributes(){
		return this.attributes;
	}

	public void setAttributesFuzzy(String attributesFuzzy){
		this.attributesFuzzy = attributesFuzzy;
	}

	public String getAttributesFuzzy(){
		return this.attributesFuzzy;
	}

}
