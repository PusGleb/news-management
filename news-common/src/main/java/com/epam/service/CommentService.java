package com.epam.service;

import com.epam.entity.Comment;

import java.util.List;


public interface CommentService {

    /**
     * Method calls the method createComment from CommentDAO
     * @param comment
     */
    public void createComment(Comment comment);

    /**
     * Method calls the method deleteComment from CommentDAO
     * @param commentId
     */
    public void deleteComment(long commentId);

    /**
     * Method calls the method deleteCommentByNewsId from CommentDAO
     * @param newsId
     */
    public void deleteCommentsByNewsId(long newsId);

    /**
     * Method calls the method updateComment from CommentDAO
     * @param comment
     * @param commentId
     */
    public void updateComment(Comment comment, long commentId);

    /**
     * Method calls the method getCommentById from CommentDAO
     * @param commentId
     */
    public Comment getCommentById(long commentId);

    /**
     * Method calls the method getCommentById from CommentDAO
     * @param newsId
     */
    public List<Comment> getCommentsByNewsId(long newsId);
}