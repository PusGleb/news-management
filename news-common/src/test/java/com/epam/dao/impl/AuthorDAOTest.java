package com.epam.dao.impl;

import com.epam.dao.AuthorDAO;
import com.epam.entity.Author;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Timestamp;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
@Ignore
public class AuthorDAOTest extends AbstractTest {

    private AuthorDAO authorDAO = (AuthorDAO) context.getBean("authorDAO");

    @Test
    public void testGetAuthorById() throws Exception {

        Author expAuthor = returnAuthorFromTable();

        Author author = authorDAO.getAuthorById(expAuthor.getId());

        assertEquals(expAuthor,author);
    }

    @Test
    public void testGetAuthorByNewsId() throws Exception {

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/expectedDataSource.xml"));
        ITable expectedTable = expectedDataSet.getTable("author");

        ITable newsAuthorTable = expectedDataSet.getTable("news_author");

        long newsId = Long.parseLong((String) newsAuthorTable.getValue(0, "news_id"));

        Author expAuthor = returnAuthorFromTable();

        Author author = authorDAO.getAuthorByNewsId(newsId);

        assertEquals(expAuthor,author);
    }

    @Test
    public void testCreateAuthor() throws Exception {

        Author author = returnAuthorFromTable();

        long authorId = authorDAO.createAuthor(author);
        Author expAuthor = authorDAO.getAuthorById(authorId);

        assertEquals(author, expAuthor);
    }

    @Test
    public void testDeleteAuthor() throws Exception {

        ITable expectedTable = loadedDataSet.getTable("author");
        long authorId =Long.parseLong((String) expectedTable.getValue(0, "author_id"));

        authorDAO.deleteAuthor(authorId);

        assertNull(authorDAO.getAuthorById(authorId));
    }

    @Test
    public void testUpdateAuthor() throws Exception {

        Author author = returnAuthorFromTable();

        authorDAO.updateAuthor(author,author.getId());
        Author updateAuthor = authorDAO.getAuthorById(author.getId());

        assertEquals(author, updateAuthor);
    }

    public Author returnAuthorFromTable() throws Exception{
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/expectedDataSource.xml"));
        ITable expectedTable = expectedDataSet.getTable("author");

        Author author = new Author();
        author.setId(Long.parseLong((String) expectedTable.getValue(0, "author_id")));
        author.setName((String) expectedTable.getValue(0, "author_name"));
        author.setExpired(Timestamp.valueOf((String) expectedTable.getValue(0, "expired")));

        return author;
    }
}
