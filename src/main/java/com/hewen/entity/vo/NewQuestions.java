package com.hewen.entity.vo;

import java.util.Date;

public class NewQuestions {
    /**
     * 问题的唯一标识
     */
    private String question_id;

    /**
     * 问题内容
     */
    private String content;


    private Date created_at;

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
