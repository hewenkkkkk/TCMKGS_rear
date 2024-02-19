package com.hewen.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hewen.entity.enums.DateTimePatternEnum;
import com.hewen.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PhoneCode implements Serializable {


    /**
     * 手机号
     */
    private String phone;

    /**
     * 编号
     */
    private String code;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

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

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

    @Override
    public String toString (){
        return "邮箱:"+(phone == null ? "空" : phone)+"，编号:"+(code == null ? "空" : code)+"，创建时间:"+(createTime == null ? "空" : DateUtil.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，0:未使用  1:已使用:"+(status == null ? "空" : status);
    }
}

