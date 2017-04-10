package com.epam.service;

import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.Comment;
import com.epam.entity.News;
import com.epam.entity.Tag;


import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public interface NewsManagementService {

    /**
     * Method adds news, author, tags in database
     * join author and news in news_author table
     * join tags and news in news_tag table and go as one step
     * @param news
     */
    public void createNews(News news);

    /**
     * Method update news,news author,news tags in database
     * @param news
     * @param newsId
     */
    public void editNews(News news, long newsId);

    /**
     * Method calls the method deleteNews from NewsService
     * @param newsId
     */
    public void deleteNews(long newsId);

    /**
     * Method calls the method getAllNews from NewsService
     */
    public List<News> getAllNews();

    /**
     * Method calls the method getNewsById from NewsService
     * @param newsId
     */
    public News getNewsById(long newsId);

    /**
     * Method calls the method createAuthor from AuthorService
     * @param author
     */
    public void createAuthor(Author author);

    /**
     * Method calls the method searchBySearchCriteria from NewsService
     * @param searchCriteria
     */
    public List<News> searchBySearchCriteria(SearchCriteria searchCriteria);

    /**
     * Method calls the method addTagForNews from NewsService
     * @param tag
     * @param news
     */
    public void addTagForNews(Tag tag, News news);

    /**
     * Method join list tags and news in news_tag table
     * @param tags
     * @param news
     */
    public void addTagsForNews(List<Tag> tags, News news);

    /**
     * Method calls the method addCommentForNews from NewsService
     * @param comment
     * @param news
     */
    public void addCommentForNews(Comment comment, News news);

    /**
     * Method create list comments in database
     * @param comments
     * @param news
     */
    public void addCommentsForNews(List<Comment> comments, News news);

    /**
     * Method calls the method deleteComment from CommentService
     * @param commentId
     */
    public void deleteComment(long commentId);
    /**
     * Method calls the method countAllNews from NewsService
     * @return count all news
     */
    public int countAllNews();


    public List<News> getAllNewsWithSort();

    public List<News> getNewsBySearchCriteriaWithSort(SearchCriteria searchCriteria);

    public List<News> getNewsForPage(int currentPage, int numberOfNewsPerPage);

    public List<News> getNewsBySearchCriteriaForPage(SearchCriteria searchCriteria, int currentPage, int numberOfNewsPerPage);

    public List<Tag> getAllTags();

    public List<Author> getAllAuthors();

    public Author getAuthorById(long authorId);

    public Tag getTagById(long tagId);

}
