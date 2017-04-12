package com.epam.dao.impl;

import com.epam.dao.CommentDAO;
import com.epam.entity.Comment;
import com.epam.entity.News;
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
public class CommentDAOTest extends AbstractTest {

    private CommentDAO commentDAO = (CommentDAO) context.getBean("commentDAO");

    @Test
    public void testCreateComment() throws Exception {

        Comment comment = returnCommentFromTable();
        long commentId = commentDAO.createCommment(comment);
        Comment expComment = commentDAO.getCommentById(commentId);
        assertEquals(comment, expComment);
    }

    @Test
    public void testGetCommentById() throws Exception {

        Comment comment = returnCommentFromTable();
        Comment expComment = commentDAO.getCommentById(comment.getId());
        assertEquals(comment, expComment);
    }

    @Test
    public void testDeleteComment() throws Exception {

        ITable expectedTable = loadedDataSet.getTable("comments");
        String commentId = (String) expectedTable.getValue(0, "comment_id");

        commentDAO.deleteComment(Long.parseLong(commentId));

        assertNull(commentDAO.getCommentById(Long.parseLong(commentId)));
    }

    @Test
    public void testUpdateComment() throws Exception {

        Comment comment = returnCommentFromTable();

        commentDAO.updateComment(comment, comment.getId());
        Comment updateComment = commentDAO.getCommentById(comment.getId());
        assertEquals(comment, updateComment);
    }

    public Comment returnCommentFromTable() throws Exception {

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/expectedDataSource.xml"));
        ITable expectedTable = expectedDataSet.getTable("comments");

        News news = new News();
        news.setId(Long.parseLong((String) expectedTable.getValue(0, "news_id")));

        Comment comment = new Comment();
        comment.setId(Long.parseLong((String) expectedTable.getValue(0, "comment_id")));
        comment.setNews(news);
        comment.setCommentText((String) expectedTable.getValue(0, "comment_text"));
        comment.setCreationDate(Timestamp.valueOf((String) expectedTable.getValue(0, "creation_date")));

        return comment;
    }
}
