package me.lym.entity;

import java.util.Date;

public class Message {
    public static final String STATUS_UNREAD = "unread";
    public static final String STATUS_READ = "read";
    private int id;
    private String title;
    private Date createTime;
    private String content;
    private int forUserId;
    private String status;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getForUserId() {
        return forUserId;
    }

    public void setForUserId(int forUserId) {
        this.forUserId = forUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", title=" + title + ", createTime=" + createTime + ", content=" + content
                + ", forUserId=" + forUserId + ", status=" + status + ", category=" + category + "]";
    }

}
