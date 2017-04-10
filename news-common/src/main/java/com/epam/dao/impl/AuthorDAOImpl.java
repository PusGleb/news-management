package com.epam.dao.impl;

import com.epam.dao.AuthorDAO;
import com.epam.entity.Author;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hleb_Pus on 12/7/2015.
 */
public class AuthorDAOImpl extends AbstractDAO implements AuthorDAO {

    private static Logger logger = Logger.getLogger(AuthorDAOImpl.class);

    public static final String AUTHOR_ID = "author_id";
    public static final String AUTHOR_NAME = "author_name";
    public static final String AUTHOR_EXPIRED = "expired";

    private final static String SQL_CREATE_AUTHOR = "INSERT "
            + "INTO author(author_id, author_name, expired ) VALUES(author_seq.nextval,?,?)";

    private static final String SQL_DELETE_AUTHOR = "DELETE "
            + "FROM author "
            + "WHERE author_id = PARAM";

    private static final String SQL_UPDATE_AUTHOR = "UPDATE author "
            + "SET author_name=?, expired =?"
            + "WHERE author_id = PARAM";

    private final static String SQL_FIND_AUTHOR_BY_NEWS_ID = "SELECT * "
            + "FROM author "
            + "JOIN news_author ON author.author_id = news_author.author_id"
            + " WHERE news_author.news_id = PARAM";

    private final static String SQL_FIND_AUTHOR_BY_ID = "SELECT * "
            + "FROM author "
            + " WHERE author_id = PARAM";

    private final static String SQL_FIND_ALL_AUTHORS = "SELECT * "
            + "FROM author ";

    /**
     * {@inheritDoc}
     */
    public long createAuthor(Author author) {
        long authorId = 0L;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_CREATE_AUTHOR, new String[]{AUTHOR_ID});

            preparedStatement.setString(1, author.getName());
            preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

            int executeUpdate = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                authorId = resultSet.getLong(1);
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
        return authorId;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteAuthor(long authorId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_AUTHOR.replace("PARAM", String.valueOf(authorId)));
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
    public void updateAuthor(Author author, long authorId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_AUTHOR.replace("PARAM", String.valueOf(authorId)));

            preparedStatement.setString(1, author.getName());
            preparedStatement.setTimestamp(2, author.getExpired());

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
    public Author getAuthorById(long authorId) {
        Author author = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(SQL_FIND_AUTHOR_BY_ID.replace("PARAM", String.valueOf(authorId)));
            if (resultSet.next()) {
                author = new Author();

                author.setId(resultSet.getLong(AUTHOR_ID));
                author.setName(resultSet.getString(AUTHOR_NAME));
                author.setExpired(resultSet.getTimestamp(AUTHOR_EXPIRED));
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
        return author;
    }

    /**
     * {@inheritDoc}
     */
    public Author getAuthorByNewsId(long newsId) {
        Author author = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(SQL_FIND_AUTHOR_BY_NEWS_ID.replace("PARAM", String.valueOf(newsId)));
            if (resultSet.next()) {
                author = new Author();

                author.setId(resultSet.getLong(AUTHOR_ID));
                author.setName(resultSet.getString(AUTHOR_NAME));
                author.setExpired(resultSet.getTimestamp(AUTHOR_EXPIRED));
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
        return author;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<Author>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_AUTHORS);

            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong(AUTHOR_ID));
                author.setName(resultSet.getString(AUTHOR_NAME));
                author.setExpired(resultSet.getTimestamp(AUTHOR_EXPIRED));

                authors.add(author);
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
        return authors;
    }
}
