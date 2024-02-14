package com.hewen.entity.vo.que;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

public class QuestionVo {

    private String question_id;
    private String user_id;
    private UserVo user; // 用户信息
    private String email;
    private String content;
    private Date created_at;
    private List<QuestionImageVo> images;
    private List<CommentVo> comments;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public List<QuestionImageVo> getImages() {
        return images;
    }

    public void setImages(List<QuestionImageVo> images) {
        this.images = images;
    }

    public List<CommentVo> getComments() {
        return comments;
    }

    public void setComments(List<CommentVo> comments) {
        this.comments = comments;
    }
}
