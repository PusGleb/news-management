package com.epam.entity;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/12/2015.
 */
public class Tag extends Entity {

    private String name;
    private List<News> tagNews;

    public Tag(){};

    public Tag(long id, String name,List<News> tagNews){
        super(id);
        this.name = name;
        this.tagNews = tagNews;
    }

    public Tag(long id, String name){
        super(id);
        this.name = name;
    }

    public Tag(long id){
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getTagNews() {
        return tagNews;
    }

    public void setTagNews(List<News> tagNews) {
        this.tagNews = tagNews;
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
        Tag tag = (Tag) obj;
        if ( !getName().equals(tag.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (31 * getId() + name.hashCode());
    }
}
