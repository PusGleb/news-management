package com.epam.entity;



import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Hleb_Pus on 11/10/2015.
 */
public class Comment extends Entity {

    private String commentText;
    private Timestamp creationDate;
    private News news;

    public Comment(long id, String commentText, News news) {
        super(id);
        this.commentText = commentText;
        this.news = news;
    }

    public Comment(){};

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Comment comment = (Comment) obj;
        if ( !getCommentText().equals(comment.getCommentText())) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        return (int) (31 * getId() + commentText.hashCode());
    }
}
