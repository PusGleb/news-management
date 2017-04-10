package com.epam.entity;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Hleb_Pus on 11/10/2015.
 */
public class News extends Entity implements Comparator<News> {

    private String title;
    private String shortText;
    private String fullText;
    private Date creationData;
    private Date modificationData;
    private Author author;
    private List<Comment> comments;
    private List<Tag> tags;

    public News() {}

    public News(long id, String title, String shortText, String fullText, Date creationData, Date modificationData, Author author, List<Comment> comments, List<Tag> tags) {
        super(id);
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationData = creationData;
        this.modificationData = modificationData;
        this.author = author;
        this.comments = comments;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Date getCreationData() {
        return creationData;
    }

    public void setCreationData(Date creationData) {
        this.creationData = creationData;
    }

    public Date getModificationData() {
        return modificationData;
    }

    public void setModificationData(Date modificationData) {
        this.modificationData = modificationData;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int compare(News news1, News news2) {
        return news1.getComments().size() - news2.getComments().size();
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj){
            return true;
        }if(null == obj){
            return false;
        }if(getClass() !=obj.getClass()){
            return false;
        }
        News news = (News) obj;
        if( !getTitle().equals(news.getTitle()) | !getShortText().equals(news.getShortText()) | !getFullText().equals(news.getFullText())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31*getId() + title.hashCode()+shortText.hashCode()+fullText.hashCode());
    }
}