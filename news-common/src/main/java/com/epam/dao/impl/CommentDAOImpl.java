package com.epam.dao.impl;

import com.epam.dao.CommentDAO;
import com.epam.entity.Comment;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class CommentDAOImpl extends AbstractDAO implements CommentDAO {

    private static Logger logger = Logger.getLogger(NewsDAOImpl.class);

    public static final String COMMENT_ID = "comment_id";
    public static final String COMMENT_TEXT = "comment_text";
    public static final String COMMENT_CREATION_DATE = "creation_date";

    private final static String SQL_CREATE_COMMENT = "INSERT "
            + "INTO comments(comment_id, news_id, comment_text, creation_date) VALUES(comment_seq.nextval,?,?,?)";

    private static final String SQL_DELETE_COMMENT = "DELETE "
            + "FROM comments "
            + "WHERE comment_id = PARAM";

    private static final String SQL_DELETE_COMMENTS_BY_NEWS_ID = "DELETE "
            + "FROM comments "
            + "WHERE news_id = PARAM";

    private static final String SQL_UPDATE_COMMENT = "UPDATE comments "
            + "SET news_id=?, comment_text =?, creation_date =?"
            + "WHERE comment_id = PARAM";

    private final static String SQL_FIND_COMMENT_BY_ID = "SELECT * "
            + "FROM comments "
            + " WHERE comment_id = PARAM";

    private final static String SQL_FIND_COMMENTS_BY_NEWS_ID = "SELECT * "
            + "FROM comments "
            + "JOIN news ON comments.news_id = news.news_id"
            + " WHERE news.news_id = PARAM";



    /**
     * {@inheritDoc}
     */
    public long createCommment(Comment comment) {
        long commentId = 0L;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_CREATE_COMMENT, new String[]{COMMENT_ID});

            preparedStatement.setLong(1, comment.getNews().getId());
            preparedStatement.setString(2, comment.getCommentText());
            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            int executeUpdate = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                commentId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("SQL exception, request or table failed: " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL exception " + e);
            }
        }
        return commentId;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteComment(long commentId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_COMMENT.replace("PARAM", String.valueOf(commentId)));
        } catch (SQLException e) {
            logger.error("SQL exception, request or table failed: " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL exception " + e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteCommentsByNewsId(long newsId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_COMMENTS_BY_NEWS_ID.replace("PARAM", String.valueOf(newsId)));
        } catch (SQLException e) {
            logger.error("SQL exception, request or table failed: " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL exception " + e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateComment(Comment comment, long commentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_COMMENT.replace("PARAM", String.valueOf(commentId)));

            preparedStatement.setLong(1, comment.getNews().getId());
            preparedStatement.setString(2, comment.getCommentText());
            preparedStatement.setTimestamp(3, comment.getCreationDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL exception, request or table failed: " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL exception " + e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Comment getCommentById(long commentId) {
        Comment comment = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(SQL_FIND_COMMENT_BY_ID.replace("PARAM", String.valueOf(commentId)));
            if (resultSet.next()) {
                comment = new Comment();

                comment.setId(commentId);
                comment.setCommentText(resultSet.getString(COMMENT_TEXT));
                comment.setCreationDate(resultSet.getTimestamp(COMMENT_CREATION_DATE));
            }
        } catch (SQLException e) {
            logger.error("SQL exception, request or table failed: " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL exception " + e);
            }
        }
        return comment;
    }

    /**
     * {@inheritDoc}
     */
    public List<Comment> getCommentsByNewsId(long newsId) {
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_COMMENTS_BY_NEWS_ID.replace("PARAM", String.valueOf(newsId)));

            while (resultSet.next()) {
                Comment comment = new Comment();

                comment.setId(resultSet.getLong(COMMENT_ID));
                comment.setCommentText(resultSet.getString(COMMENT_TEXT));
                comment.setCreationDate(resultSet.getTimestamp(COMMENT_CREATION_DATE));
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            logger.error("SQL exception, request or table failed: " + e);

        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL exception " + e);
            }
        }
        return comments;
    }


}
