package com.epam.dao.search;

import com.epam.dao.impl.NewsDAOImpl;
import com.epam.entity.Tag;

import java.util.List;

/**
 * This class helps methods which works with object SearchCriteria
 *
 * @author Hleb_Pus
 */
public class SearchUtils {

    public static final String SQL_SEARCH_CRITERIA_BY_AUTHOR = "SELECT b.* FROM " +
            "("+ NewsDAOImpl.SQL_NESTED_SELECT_SORT_NEWS + ") b " +
            "JOIN news_author ON b.news_id = news_author.news_id " +
            "WHERE news_author.author_id = AUTHOR_ID";

    public static final String SQL_SEARCH_CRITERIA_BY_TAGS = "SELECT DISTINCT b.NEWS_ID, b.TITLE, b.SHORT_TEXT, b.FULL_TEXT, b.CREATION_DATE, b.MODIFICATION_DATE " +
            "FROM ("+ NewsDAOImpl.SQL_NESTED_SELECT_SORT_NEWS + ") b " +
            "JOIN news_tag ON b.news_id = news_tag.news_id ";

    public static final String SQL_SEARCH_CRITERIA_BY_TAGS_AND_AUTHOR = "SELECT DISTINCT b.NEWS_ID, b.TITLE, b.SHORT_TEXT, b.FULL_TEXT, b.CREATION_DATE, b.MODIFICATION_DATE " +
            "FROM (" + NewsDAOImpl.SQL_NESTED_SELECT_SORT_NEWS + ") b " +
            "JOIN news_author ON b.news_id = news_author.news_id " +
            "JOIN news_tag ON b.news_id = news_tag.news_id " +
            "WHERE news_author.author_id = AUTHOR_ID ";

    /**
     * Method builds SQL queries by object SearchCriteria
     * @param searchCriteria
     * @return SQL query
     */
    public String buildQuery(SearchCriteria searchCriteria) {
        StringBuilder sb = new StringBuilder();
        String query = null;

        if (searchCriteria.getTags() == null) {
            sb.append("SELECT * FROM news ");
            sb.append("JOIN news_author ON news.news_id = news_author.news_id ");
            sb.append("WHERE news_author.author_id = " + searchCriteria.getAuthor().getId());
        }
        if (searchCriteria.getAuthor() == null) {
            sb.append("SELECT DISTINCT NEWS.NEWS_ID, NEWS.TITLE,NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE,NEWS.MODIFICATION_DATE FROM news ");
            sb.append(" JOIN news_tag ON news.news_id = news_tag.news_id ");
            List<Tag> tagsList = searchCriteria.getTags();
            sb.append("WHERE news_tag.tag_id IN (");

            for (Tag tag : tagsList) {
                sb.append(tag.getId() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        } else if (searchCriteria.getAuthor() != null & searchCriteria.getTags() != null) {
            sb.append("SELECT DISTINCT NEWS.NEWS_ID, NEWS.TITLE,NEWS.SHORT_TEXT, NEWS.FULL_TEXT, NEWS.CREATION_DATE,NEWS.MODIFICATION_DATE FROM news ");
            sb.append("JOIN news_author ON news.news_id = news_author.news_id ");
            sb.append(" JOIN news_tag ON news.news_id = news_tag.news_id ");
            sb.append("WHERE news_author.author_id = " + searchCriteria.getAuthor().getId());
            sb.append(" AND news_tag.tag_id IN (");
            List<Tag> tagsList = searchCriteria.getTags();
            for (Tag tag : tagsList) {
                sb.append(tag.getId() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        query = sb.toString();
        return query;
    }

    public String buildQueryWithPaginationAndSort(SearchCriteria searchCriteria) {
        StringBuilder sb = new StringBuilder();
        String query = null;

        if (searchCriteria.getTags() == null) {
            sb.append(SQL_SEARCH_CRITERIA_BY_AUTHOR.replace("AUTHOR_ID", String.valueOf(searchCriteria.getAuthor().getId())));
        }
        if (searchCriteria.getAuthor() == null) {
            sb.append(SQL_SEARCH_CRITERIA_BY_TAGS);
            List<Tag> tagsList = searchCriteria.getTags();
            sb.append("WHERE news_tag.tag_id IN (");

            for (Tag tag : tagsList) {
                sb.append(tag.getId() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        } else if (searchCriteria.getAuthor() != null & searchCriteria.getTags() != null) {
            sb.append(SQL_SEARCH_CRITERIA_BY_TAGS_AND_AUTHOR.replace("AUTHOR_ID", String.valueOf(searchCriteria.getAuthor().getId())));
            sb.append(" AND news_tag.tag_id IN (");
            List<Tag> tagsList = searchCriteria.getTags();
            for (Tag tag : tagsList) {
                sb.append(tag.getId() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        }
        query = sb.toString();
        return query;
    }
}

