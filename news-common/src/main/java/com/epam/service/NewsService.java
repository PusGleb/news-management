package com.epam.service;

import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.entity.Tag;

import java.util.List;


public interface NewsService {

    /**
     * Method calls the method createNews from NewsDAO
     * @param news
     */
    public void createNews(News news);

    /**
     * Method calls the method deleteNews from NewsDAO
     * @param newsId
     */
    public void deleteNews(long newsId);

    /**
     * Method calls the method updateNews from NewsDAO
     * @param news
     * @param newsId
     */
    public void updateNews(News news, long newsId);

    /**
     * Method calls the method getNewsById from NewsDAO
     * @param newsId
     */
    public News getNewsById(long newsId);

    /**
     * Method calls the method getAllNews from NewsDAO
     */
    public List<News> getAllNews();

    /**
     * Method calls the method addAuthorToNews from NewsDAO
     * @param news
     * @param author
     */
    public void addAuthorToNews(News news, Author author);

    /**
     * Method calls the method addTagToNews from NewsDAO
     * @param tag
     * @param news
     */
    public void addTagToNews(Tag tag, News news);

    /**
     * Method calls the method countAllNews from NewsDAO
     */
    public int countAllNews();

    /**
     * Method calls the method searchNewsBySearchCriteria from NewsDAO
     * @param searchCriteria
     */
    public List<News> searchNewsBySearchCriteria(SearchCriteria searchCriteria);

    public List<News> getAllNewsWithSort();

    public List<News> getNewsBySearchCriteriaWithSort(SearchCriteria searchCriteria);

    public List<News> getNewsForPage(int currentPage, int numberOfNewsPerPage);

    public List<News> getNewsBySearchCriteriaForPage(SearchCriteria searchCriteria, int currentPage, int numberOfNewsPerPage);

}
