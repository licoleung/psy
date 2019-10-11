package cn.pourfeelings.psy.pojo;

import java.util.Date;

public class Comment {
    private Integer cid;

    private String content;

    private Integer uid;

    private Integer eid;

    private Date cmTime;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Date getCmTime() {
        return cmTime;
    }

    public void setCmTime(Date cmTime) {
        this.cmTime = cmTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", eid=" + eid +
                ", cmTime=" + cmTime +
                ", user=" + user +
                '}';
    }
}