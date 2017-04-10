package com.epam.service.impl;

import com.epam.dao.impl.CommentDAOImpl;
import com.epam.entity.Comment;
import com.epam.service.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class CommentServiceTest {

    @Mock
    private CommentDAOImpl commentDAOImpl;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddComment() {

        Comment comment = new Comment();
        commentService.createComment(comment);
        verify(commentDAOImpl).createCommment(comment);
    }

    @Test
    public void testDeleteComment() {

        long commentId =1;
        commentService.deleteComment(commentId);
        verify(commentDAOImpl).deleteComment(commentId);
    }

    @Test
    public void testGetCommentById() {

        long commentId =1;
        commentService.getCommentById(commentId);
        verify(commentDAOImpl).getCommentById(commentId);
    }

    @Test
    public void testGetCommentByNewsId() {

        long newsId =1;
        commentService.getCommentsByNewsId(newsId);
        verify(commentDAOImpl).getCommentsByNewsId(newsId);
    }

    @Test
    public void testUpdateComment() {

        long commentId = 1;
        Comment comment = new Comment();
        commentService.updateComment(comment,commentId);
        verify(commentDAOImpl).updateComment(comment,commentId);
    }
}
