package com.example.pojo;

import java.io.Serializable;

public class Article implements Serializable {

    private String id;
    private String title;
    private String content;
    private String author;
    private String crtTime;
    private Integer clickNum;

    public Article() {
    }

    public Article(String id, String title, String content, String author, String crtTime, Integer clickNum) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.crtTime = crtTime;
        this.clickNum = clickNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(String crtTime) {
        this.crtTime = crtTime;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", crtTime='" + crtTime + '\'' +
                ", clickNum=" + clickNum +
                '}';
    }
}
