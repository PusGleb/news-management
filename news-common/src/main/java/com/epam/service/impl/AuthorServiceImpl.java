package com.epam.service.impl;

import com.epam.dao.AuthorDAO;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.service.AuthorService;
import com.epam.service.NewsManagementService;

import java.util.List;


/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class AuthorServiceImpl implements AuthorService {

    private AuthorDAO authorDAO;

    public AuthorServiceImpl(){}

    public AuthorServiceImpl(AuthorDAO authorDAO){
        this.authorDAO = authorDAO;
    }

    /**
     * {@inheritDoc}
     */

    public long createAuthor(Author author) {
        return authorDAO.createAuthor(author);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAuthor(long authorId){
        authorDAO.deleteAuthor(authorId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateAuthor(Author author, long authorId){
        authorDAO.updateAuthor(author, authorId);
    }

    /**
     * {@inheritDoc}
     */

    public Author getAuthorById(long authorId) {
        Author author = authorDAO.getAuthorById(authorId);
        return author;
    }

    /**
     * {@inheritDoc}
     */

    public Author getAuthorByNewsId(long authorId) {
        Author author = authorDAO.getAuthorByNewsId(authorId);
        return author;
    }


    public List<Author> getAllAuthors(){
        List<Author> authorList = authorDAO.getAllAuthors();
        return authorList;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

    public void setAuthorDAO(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }


}

