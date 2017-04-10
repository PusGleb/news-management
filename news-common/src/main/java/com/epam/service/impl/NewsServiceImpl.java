package com.epam.service.impl;

import com.epam.dao.NewsDAO;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Tag;
import com.epam.service.NewsService;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hleb_Pus on 11/11/2015.
 */
public class NewsServiceImpl implements NewsService {

    private NewsDAO newsDAO;

    public NewsServiceImpl() {
    }

    public NewsServiceImpl(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }

    /**
     * {@inheritDoc}
     */
    public void createNews(News news) {
        newsDAO.createNews(news);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteNews(long newsId) {

        newsDAO.deleteNews(newsId);
        newsDAO.deleteNewsFromNewsAuthor(newsId);
        newsDAO.deleteNewsFromNewsTag(newsId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateNews(News news, long newsId) {
        newsDAO.updateNews(news, newsId);
    }

    /**
     * {@inheritDoc}
     */
    public News getNewsById(long newsId) {
        News news = newsDAO.getNewsById(newsId);
        return news;
    }

    /**
     * {@inheritDoc}
     */
    public List<News> getAllNews() {
        List<News> newsList = newsDAO.getAllNews();
        Collections.sort(newsList, new News());
        return newsList;
    }

    /**
     * {@inheritDoc}
     */
    public void addAuthorToNews(News news, Author author) {
        newsDAO.addAuthorToNews(author, news);
    }

    /**
     * {@inheritDoc}
     */
    public void addTagToNews(Tag tag, News news) {
        newsDAO.addTagToNews(tag, news);
    }

    /**
     * {@inheritDoc}
     */
    public int countAllNews(){
        int countAllNews;
        List<News> news = newsDAO.getAllNews();
        countAllNews = news.size();
        return countAllNews;
    }

    /**
     * {@inheritDoc}
     */
    public List<News> searchNewsBySearchCriteria(SearchCriteria searchCriteria){
        List<News> newsList = newsDAO.searchBySearchCriteria(searchCriteria);
        return newsList;
    }


    public List<News> getAllNewsWithSort(){
        List<News> newsList = newsDAO.getAllNewsWithSort();
        return newsList;
    }

    public List<News> getNewsBySearchCriteriaWithSort(SearchCriteria searchCriteria){
        List<News> newsList = newsDAO.getNewsBySearchCriteriaWithSort(searchCriteria);
        return newsList;
    }

    public List<News> getNewsForPage(int currentPage, int numberOfNewsPerPage){
        List<News> newsList = newsDAO.getNewsForPage(numberOfNewsPerPage * currentPage, (currentPage - 1) * numberOfNewsPerPage);
        return newsList;
    }

    public List<News> getNewsBySearchCriteriaForPage(SearchCriteria searchCriteria, int currentPage, int numberOfNewsPerPage){
        List<News> newsList = newsDAO.getNewsBySearchCriteriaForPage(searchCriteria, numberOfNewsPerPage * currentPage, (currentPage - 1) * numberOfNewsPerPage);
        return newsList;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }

    public void setNewsDAO(NewsDAO newsDAO) {
        this.newsDAO = newsDAO;
    }
}
