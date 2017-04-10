package com.epam.dao.impl;
import com.epam.dao.TagDAO;
import com.epam.entity.Tag;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class TagDAOImpl extends AbstractDAO implements TagDAO {

    private static Logger logger = Logger.getLogger(TagDAOImpl.class);

    public static final String TAG_ID = "tag_id";
    public static final String TAG_NAME = "tag_name";

    private final static String SQL_CREATE_TAG = "INSERT "
            + "INTO tag(tag_id, tag_name) VALUES(tag_seq.nextval,?)";

    private static final String SQL_DELETE_TAG = "DELETE "
            + "FROM tag "
            + "WHERE tag_id = PARAM";

    private static final String SQL_UPDATE_TAG = "UPDATE tag "
            + "SET tag_id=?, tag_name =? "
            + "WHERE tag_id = PARAM";

    private final static String SQL_FIND_TAG_BY_ID = "SELECT * "
            + "FROM tag "
            + "WHERE tag_id = PARAM ";

    private final static String SQL_FIND_TAGS_BY_NEWS_ID = "SELECT * "
            + "FROM tag "
            + "JOIN news_tag ON tag.tag_id = news_tag.tag_id"
            + " WHERE news_tag.news_id = PARAM";

    private final static String SQL_FIND_ALL_TAGS = "SELECT * "
            + "FROM tag ";

    /**
     * {@inheritDoc}
     */
    public long createTag(Tag tag) {
        long tagId = 0L;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_CREATE_TAG, new String[]{TAG_ID});

            preparedStatement.setString(1, tag.getName());

            int executeUpdate = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                tagId = resultSet.getLong(1);
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
        return tagId;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteTag(long tagId) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_TAG.replace("PARAM", String.valueOf(tagId)));
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
    public void updateTag(Tag tag, long tagId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_TAG.replace("PARAM", String.valueOf(tagId)));

            preparedStatement.setString(1, tag.getName());

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
    public Tag getTagById(long tagId) {
        Tag tag = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery(SQL_FIND_TAG_BY_ID.replace("PARAM", String.valueOf(tagId)));
            if (resultSet.next()) {
                tag = new Tag();

                tag.setId(tagId);
                tag.setName(resultSet.getString(TAG_NAME));
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
        return tag;
    }

    public List<Tag> getTagsByNewsId(long newsId) {
        List<Tag> tags = new ArrayList<Tag>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_TAGS_BY_NEWS_ID.replace("PARAM", String.valueOf(newsId)));

            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt(TAG_ID));
                tag.setName(resultSet.getString(TAG_NAME));

                tags.add(tag);
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
        return tags;
    }

    public List<Tag> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL_TAGS);

            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt(TAG_ID));
                tag.setName(resultSet.getString(TAG_NAME));

                tags.add(tag);
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
        return tags;
    }
}
