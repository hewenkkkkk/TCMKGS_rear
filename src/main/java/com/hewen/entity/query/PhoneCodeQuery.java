package com.hewen.entity.query;



/**
 *
 * 邮箱验证码参数
 *
 */
public class PhoneCodeQuery extends BaseParam {


    /**
     * 邮箱
     */
    private String phone;

    private String phoneFuzzy;

    /**
     * 编号
     */
    private String code;

    private String codeFuzzy;

    /**
     * 创建时间
     */
    private String createTime;

    private String createTimeStart;

    private String createTimeEnd;

    /**
     * 0:未使用  1:已使用
     */
    private Integer status;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneFuzzy() {
        return phoneFuzzy;
    }

    public void setPhoneFuzzy(String phoneFuzzy) {
        this.phoneFuzzy = phoneFuzzy;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setCodeFuzzy(String codeFuzzy){
        this.codeFuzzy = codeFuzzy;
    }

    public String getCodeFuzzy(){
        return this.codeFuzzy;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    public String getCreateTime(){
        return this.createTime;
    }

    public void setCreateTimeStart(String createTimeStart){
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeStart(){
        return this.createTimeStart;
    }
    public void setCreateTimeEnd(String createTimeEnd){
        this.createTimeEnd = createTimeEnd;
    }

    public String getCreateTimeEnd(){
        return this.createTimeEnd;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

}

