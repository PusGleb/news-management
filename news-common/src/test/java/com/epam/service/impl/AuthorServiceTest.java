package com.epam.service.impl;

import com.epam.dao.AuthorDAO;
import com.epam.entity.Author;
import com.epam.service.impl.AuthorServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class AuthorServiceTest {

    @Mock
    private AuthorDAO authorDAO;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAuthorById(){

        long authorId = 1;
        authorService.getAuthorById(authorId);
        verify(authorDAO).getAuthorById(authorId);
    }

    @Test
    public void testAddAuthor(){

        Author author = new Author();
        authorService.createAuthor(author);
        verify(authorDAO).createAuthor(author);
    }

    @Test
    public void testGetAuthorByNewsId(){

        long authorId = 1;
        authorService.getAuthorByNewsId(authorId);
        verify(authorDAO).getAuthorByNewsId(authorId);
    }

    @Test
    public void testDeleteAuthor(){

        long authorId = 1;
        authorService.deleteAuthor(authorId);
        verify(authorDAO).deleteAuthor(authorId);
    }

    @Test
    public void testUpdateAuthor(){

        long authorId = 1;
        Author author = new Author();
        authorService.updateAuthor(author, authorId);
        verify(authorDAO).updateAuthor(author, authorId);
    }
}
