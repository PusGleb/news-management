package com.epam.dao;

import com.epam.entity.Comment;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public interface CommentDAO {

    /**
     * Method adds comment in database
     * @param comment
     */
    public long createCommment(Comment comment);

    /**
     * Method delete comment from database by comment id
     * @param commentId
     */
    public void deleteComment(long commentId);

    /**
     * Method delete comments from database by news id
     * @param newsId
     */
    public void deleteCommentsByNewsId(long newsId);

    /**
     * Method update comment in database
     * @param comment
     * @param commentId
     */
    public void updateComment(Comment comment, long commentId);

    /**
     * Method returns comment from database by comment id
     * @param commentId
     */
    public Comment getCommentById(long commentId);

    /**
     * Method returns comment from database by news id
     * @param newsId
     */
    public List<Comment> getCommentsByNewsId(long newsId);


}
