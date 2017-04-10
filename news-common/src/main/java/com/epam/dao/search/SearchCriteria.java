package com.epam.dao.search;

import com.epam.entity.Author;
import com.epam.entity.Tag;

import java.util.List;

/**
 * SearchCriteria uses for constructing SQL queries in DAO layer
 *
 * @author Hleb_Pus
 */
public class SearchCriteria {

    private Author author;
    private List<Tag> tags;

    public SearchCriteria(){}

    public SearchCriteria(Author author, List<Tag> tags){
        this.author = author;
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
