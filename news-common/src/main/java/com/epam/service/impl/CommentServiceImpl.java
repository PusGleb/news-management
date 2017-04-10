package com.epam.service.impl;

import com.epam.dao.CommentDAO;
import com.epam.dao.impl.CommentDAOImpl;
import com.epam.entity.Comment;
import com.epam.service.CommentService;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class CommentServiceImpl implements CommentService{

    private CommentDAO commentDAO;

    public CommentServiceImpl(){}

    public CommentServiceImpl(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }

    /**
     * {@inheritDoc}
     */
    public void createComment(Comment comment) {
        commentDAO.createCommment(comment);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteComment(long commentId){
        commentDAO.deleteComment(commentId);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteCommentsByNewsId(long newsId){
        commentDAO.deleteCommentsByNewsId(newsId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateComment(Comment comment, long commentId){
        commentDAO.updateComment(comment, commentId);
    }

    /**
     * {@inheritDoc}
     */
    public Comment getCommentById(long commentId){
        Comment comment = commentDAO.getCommentById(commentId);
        return comment;
    }

    /**
     * {@inheritDoc}
     */
    public List<Comment> getCommentsByNewsId(long newsId){
        List<Comment> comments = commentDAO.getCommentsByNewsId(newsId);
        return comments;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
}


