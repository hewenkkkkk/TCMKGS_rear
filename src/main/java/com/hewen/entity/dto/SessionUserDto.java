package com.hewen.entity.dto;

public class SessionUserDto {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
