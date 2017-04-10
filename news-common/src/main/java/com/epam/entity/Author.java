package com.epam.entity;



import com.epam.service.NewsManagementService;
import com.epam.service.impl.NewsManagementServiceImpl;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by Hleb_Pus on 11/10/2015.
 */
public class Author extends Entity {

    private String name;
    private Timestamp expired;
    private List<News> newsList;

    public Author(long id, String name, Timestamp expired, List<News> newsList) {
        super(id);
        this.name = name;
        this.expired = expired;
        this.newsList = newsList;
    }

    public Author(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
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
        Author author = (Author) obj;
        if ( !getName().equals(author.getName())) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        return (int) (31 * getId() + name.hashCode());
    }
}