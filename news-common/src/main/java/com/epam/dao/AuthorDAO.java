package com.epam.dao;

import com.epam.entity.Author;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public interface AuthorDAO {

    /**
     * Method adds author in database
     * @param author
     */
    public long createAuthor(Author author);

    /**
     * Method delete author from database by author id
     * @param authorId
     */
    public void deleteAuthor(long authorId);

    /**
     * Method update author in database
     * @param author
     * @param authorId
     */
    public void updateAuthor(Author author, long authorId);

    /**
     * Method returns author from database by news id
     * @param newsId
     */
    public Author getAuthorByNewsId(long newsId);

    /**
     * Method returns author from database by author id
     * @param authorId
     */
    public Author getAuthorById(long authorId);


    public List<Author> getAllAuthors();
}