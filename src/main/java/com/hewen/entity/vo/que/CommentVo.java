package com.hewen.entity.vo.que;

import java.util.Date;
import java.util.List;

public class CommentVo {

    private String comment_id;
    private String question_id;
    private String user_id;
    private UserVo user; // 用户信息
    private String email;
    private String content;
    private Date created_at;
    private String parent_id;
    private List<CommentImageVo> images;
    private List<CommentVo> subComments;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
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



    public List<CommentImageVo> getImages() {
        return images;
    }

    public void setImages(List<CommentImageVo> images) {
        this.images = images;
    }

    public List<CommentVo> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<CommentVo> subComments) {
        this.subComments = subComments;
    }
}
