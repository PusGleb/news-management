package com.epam.dao;

import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Tag;

import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public interface NewsDAO {

    /**
     * Method adds news in database
     * @param news
     */
    public long createNews(News news);

    /**
     * Method delete news from database
     * @param newsId
     */
    public void deleteNews(long newsId);

    /**
     * Method delete news_id from news_author table
     * @param newsId
     */
    public void deleteNewsFromNewsAuthor(long newsId);

    /**
     * Method delete news_id from news_tag table
     * @param newsId
     */
    public void deleteNewsFromNewsTag(long newsId);

    /**
     * Method update news in database
     * @param news
     * @param newsId
     */
    public void updateNews(News news, long newsId);

    /**
     * Method returns news from database by news id
     * @param newsId
     */
    public News getNewsById(long newsId);

    /**
     * Method returns list all news from database
     */
    public List<News> getAllNews();

    /**
     * Method join author and news in news_author table
     * @param author
     * @param news
     */
    public void addAuthorToNews(Author author, News news);

    /**
     * Method join tag and news in news_tag table
     * @param tag
     * @param news
     */
    public void addTagToNews(Tag tag, News news);

    /**
     * Method return news list by searchCriteria object
     * @param searchCriteria entity contains author and tags list
     */
    public List<News> searchBySearchCriteria(SearchCriteria searchCriteria);

    public List<News> getAllNewsWithSort();

    public List<News> getNewsBySearchCriteriaWithSort(SearchCriteria searchCriteria);

    public List<News> getNewsForPage(int currentPage, int numberOfNewsPerPage);

    public List<News> getNewsBySearchCriteriaForPage(SearchCriteria searchCriteria, int currentPage, int numberOfNewsPerPage);
}
