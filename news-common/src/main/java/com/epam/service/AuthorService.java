package com.epam.service;

import com.epam.entity.Author;

import java.util.List;


public interface AuthorService {

    /**
     * Method calls the method createAuthor from AuthorDAO
     * @param author
     */
    public long createAuthor(Author author);

    /**
     * Method calls the method deleteAuthor from AuthorDAO
     * @param authorId
     */
    public void deleteAuthor(long authorId);

    /**
     * Method calls the method updateAuthor from AuthorDAO
     * @param author
     * @param authorId
     */
    public void updateAuthor(Author author, long authorId);

    /**
     * Method returns author from database by author id
     * @param authorId
     */
    public Author getAuthorById(long authorId);

    /**
     * Method returns author from database by news id
     * @param newsId
     */
    public Author getAuthorByNewsId(long newsId);


    public List<Author> getAllAuthors();
}
