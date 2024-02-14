package com.hewen.entity.query;



/**
 * 参数
 */
public class RelationshipsQuery extends BaseParam {


	/**
	 *
	 */
	private Integer id;

	/**
	 *
	 */
	private String source;

	private String sourceFuzzy;

	/**
	 *
	 */
	private String target;

	private String targetFuzzy;

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

	public void setSourceFuzzy(String sourceFuzzy){
		this.sourceFuzzy = sourceFuzzy;
	}

	public String getSourceFuzzy(){
		return this.sourceFuzzy;
	}

	public void setTarget(String target){
		this.target = target;
	}

	public String getTarget(){
		return this.target;
	}

	public void setTargetFuzzy(String targetFuzzy){
		this.targetFuzzy = targetFuzzy;
	}

	public String getTargetFuzzy(){
		return this.targetFuzzy;
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
