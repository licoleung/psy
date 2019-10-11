package cn.pourfeelings.psy.pojo;

import java.util.Date;

public class Answer {
    private Integer aid;

    private Integer qid;

    private Integer uid;

    private Date anTime;

    private String content;

    private User user;

    private Question question;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getAnTime() {
        return anTime;
    }

    public void setAnTime(Date anTime) {
        this.anTime = anTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}