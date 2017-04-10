package com.epam.dao.impl;

import com.epam.dao.AuthorDAO;
import com.epam.dao.CommentDAO;
import com.epam.dao.NewsDAO;
import com.epam.dao.TagDAO;
import com.epam.dao.search.SearchCriteria;
import com.epam.dao.search.SearchUtils;
import com.epam.entity.*;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hleb_Pus on 11/10/2015.
 */
public class NewsDAOImpl extends AbstractDAO implements NewsDAO {

    private static Logger logger = Logger.getLogger(NewsDAOImpl.class);

    public static final String NEWS_ID = "news_id";
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_SHORT_TEXT = "short_text";
    public static final String NEWS_FULL_TEXT = "full_text";
    public static final String NEWS_CREATION_DATE = "creation_date";
    public static final String NEWS_MODIFICATION_DATE = "modification_date";

    private TagDAO tagDAO;
    private AuthorDAO authorDAO;
    private CommentDAO commentDAO;
    private SearchUtils searchUtils;

    private final static String SQL_CREATE_NEWS = "INSERT "
            + "INTO news(news_id, title, short_text, full_text,creation_date, modification_date ) VALUES(news_seq.nextval,?,?,?,?,?)";

    private static final String SQL_DELETE_NEWS = "DELETE "
            + "FROM news "
            + "WHERE news_id = PARAM";

    private static final String SQL_DELETE_NEWS_FROM_NEWS_AUTHOR = "DELETE "
            + "FROM news_author "
            + "WHERE news_id = PARAM";

    private static final String SQL_DELETE_NEWS_FROM_NEWS_TAG = "DELETE "
            + "FROM news_tag "
            + "WHERE news_id = PARAM";

    private static final String SQL_UPDATE_NEWS = "UPDATE news "
            + "SET title=?, short_text=?, full_text=?, creation_date=?, modification_date=? "
            + "WHERE news_id = PARAM";

    private final static String SQL_FIND_NEWS_BY_ID = "SELECT * "
            + "FROM news "
            + "WHERE news_id = PARAM ";

    private final static String SQL_FIND_ALL_NEWS = "SELECT * "
            + "FROM news ";

    public final static String SQL_JOIN_AUTHOR_AND_NEWS = "INSERT "
            + "INTO news_author( author_id, news_id) VALUES(PARAM1,PARAM2)";

    public final static String SQL_JOIN_TAG_AND_NEWS = "INSERT "
            + "INTO news_( author_id, news_id) VALUES(PARAM1,PARAM2)";


    public static final String SQL_FIND_NEWS_FOR_PAGE = "SELECT * " +
            "FROM( SELECT rownum rnum, a.* from (INNER_SELECT) a " +
            "WHERE rownum <= PARAM1 ) where rnum > PARAM2";

    public static final String SQL_NESTED_SELECT_SORT_NEWS = "SELECT news.news_id, news.TITLE,news.SHORT_TEXT,news.FULL_TEXT, news.CREATION_DATE,news.MODIFICATION_DATE, count(comments.news_id) as com " +
            "FROM news " +
            "LEFT JOIN comments ON news.news_id = comments.news_id " +
            "group by news.news_id, news.TITLE,news.SHORT_TEXT,news.FULL_TEXT, news.CREATION_DATE,news.MODIFICATION_DATE " +
            "order by com desc";

    /**
     * {@inheritDoc}
     */
    public long createNews(News news) {
        long newsId = 0L;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_CREATE_NEWS, new String[]{NEWS_ID});

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getShortText());
            preparedStatement.setString(3, news.getFullText());
            preparedStatement.setDate(4, null);
            preparedStatement.setDate(5, null);

            int executeUpdate = preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                newsId = resultSet.getLong(1);
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
        return newsId;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteNews(long newsId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_NEWS.replace("PARAM", String.valueOf(newsId)));
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
    public void deleteNewsFromNewsAuthor(long newsId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_NEWS_FROM_NEWS_AUTHOR.replace("PARAM", String.valueOf(newsId)));
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
    public void deleteNewsFromNewsTag(long newsId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_NEWS_FROM_NEWS_TAG.replace("PARAM", String.valueOf(newsId)));
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
    public void updateNews(News news, long newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_NEWS.replace("PARAM", String.valueOf(newsId)));

            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getShortText());
            preparedStatement.setString(3, news.getFullText());
            preparedStatement.setDate(4, null);
            preparedStatement.setDate(5, null);

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
    public News getNewsById(long newsId) {
        News news = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(SQL_FIND_NEWS_BY_ID.replace("PARAM", String.valueOf(newsId)));
            if (resultSet.next()) {
                news = new News();
                Author author = getAuthorDAO().getAuthorByNewsId(newsId);
                List<Comment> comments = getCommentDAO().getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);
                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);
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
        return news;
    }

    /**
     * {@inheritDoc}
     */
    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<News>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_NEWS);

            while (resultSet.next()) {
                News news = new News();
                long newsId = resultSet.getInt(NEWS_ID);

                Author author = authorDAO.getAuthorByNewsId(newsId);
                List<Comment> comments = commentDAO.getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);

                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);

                newsList.add(news);
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
        return newsList;
    }

    /**
     * {@inheritDoc}
     */
    public void addAuthorToNews(Author author, News news) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_JOIN_AUTHOR_AND_NEWS.replace("PARAM1", String.valueOf(author.getId()))
                    .replace("PARAM2", String.valueOf(news.getId())));

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
    public void addTagToNews(Tag tag, News news) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_JOIN_TAG_AND_NEWS.replace("PARAM1", String.valueOf(tag.getId()))
                    .replace("PARAM2", String.valueOf(news.getId())));

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
    }

    /**
     * {@inheritDoc}
     */
    public List<News> searchBySearchCriteria(SearchCriteria searchCriteria) {
        List<News> newsList = new ArrayList<News>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(searchUtils.buildQuery(searchCriteria));
            while (resultSet.next()) {
                News news = new News();
                int newsId = resultSet.getInt(NEWS_ID);
                Author author = getAuthorDAO().getAuthorByNewsId(newsId);
                List<Comment> comments = getCommentDAO().getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);

                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);

                newsList.add(news);
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
        return newsList;
    }


    public List<News> getAllNewsWithSort() {
        List<News> newsList = new ArrayList<News>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_NESTED_SELECT_SORT_NEWS);

            while (resultSet.next()) {
                News news = new News();
                long newsId = resultSet.getInt(NEWS_ID);

                Author author = authorDAO.getAuthorByNewsId(newsId);
                List<Comment> comments = commentDAO.getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);

                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);

                newsList.add(news);
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
        return newsList;
    }

    public List<News> getNewsBySearchCriteriaWithSort(SearchCriteria searchCriteria){
        List<News> newsList = new ArrayList<News>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(searchUtils.buildQueryWithPaginationAndSort(searchCriteria));

            while (resultSet.next()) {
                News news = new News();
                long newsId = resultSet.getInt(NEWS_ID);

                Author author = authorDAO.getAuthorByNewsId(newsId);
                List<Comment> comments = commentDAO.getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);

                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);

                newsList.add(news);
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
        return newsList;
    }

    public List<News> getNewsBySearchCriteriaForPage(SearchCriteria searchCriteria, int PARAM1, int PARAM2) {
        List<News> newsList = new ArrayList<News>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_NEWS_FOR_PAGE.replace("PARAM1", String.valueOf(PARAM1)).replace("PARAM2", String.valueOf(PARAM2)).replace("INNER_SELECT", searchUtils.buildQueryWithPaginationAndSort(searchCriteria)));

            while (resultSet.next()) {
                News news = new News();
                long newsId = resultSet.getInt(NEWS_ID);

                Author author = authorDAO.getAuthorByNewsId(newsId);
                List<Comment> comments = commentDAO.getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);

                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);

                newsList.add(news);
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
        return newsList;
    }

    public List<News> getNewsForPage(int PARAM1, int PARAM2) {

        List<News> newsList = new ArrayList<News>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_NEWS_FOR_PAGE.replace("PARAM1", String.valueOf(PARAM1)).replace("PARAM2", String.valueOf(PARAM2)).replace("INNER_SELECT", SQL_NESTED_SELECT_SORT_NEWS));

            while (resultSet.next()) {
                News news = new News();
                long newsId = resultSet.getInt(NEWS_ID);

                Author author = authorDAO.getAuthorByNewsId(newsId);
                List<Comment> comments = commentDAO.getCommentsByNewsId(newsId);
                List<Tag> tags = getTagDAO().getTagsByNewsId(newsId);

                news.setId(newsId);
                news.setTitle(resultSet.getString(NEWS_TITLE));
                news.setShortText(resultSet.getString(NEWS_SHORT_TEXT));
                news.setFullText(resultSet.getString(NEWS_FULL_TEXT));
                news.setCreationData(resultSet.getTimestamp(NEWS_CREATION_DATE));
                news.setModificationData(resultSet.getTimestamp(NEWS_MODIFICATION_DATE));
                news.setAuthor(author);
                news.setComments(comments);
                news.setTags(tags);

                newsList.add(news);
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
        return newsList;
    }

    public SearchUtils getSearchUtils() {
        return searchUtils;
    }

    public void setSearchUtils(SearchUtils searchUtils) {
        this.searchUtils = searchUtils;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

    public void setAuthorDAO(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public TagDAO getTagDAO() {
        return tagDAO;
    }

    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
}
