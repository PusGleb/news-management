package com.epam.service.impl;

import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.Comment;
import com.epam.entity.News;
import com.epam.entity.Tag;
import com.epam.service.NewsManagementService;
import com.epam.service.AuthorService;
import com.epam.service.CommentService;
import com.epam.service.NewsService;
import com.epam.service.TagService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements a pattern com.epam.facade
 * Contains all the necessary business methods for NewsManagement application
 *
 * @author Hleb_Pus
 */
public class NewsManagementServiceImpl implements NewsManagementService {

    private NewsService newsService;
    private AuthorService authorService;
    private CommentService commentService;
    private TagService tagService;

    /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = Exception.class)
    public void createNews(News news) {
        newsService.createNews(news);
        authorService.createAuthor(news.getAuthor());
        newsService.addAuthorToNews(news, news.getAuthor());
        for (Tag tag : news.getTags()) {
            tagService.createTag(tag);
            newsService.addTagToNews(tag, news);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void editNews(News news, long newsId) {
        //news.setId(newsId);
        newsService.updateNews(news, newsId);
        authorService.updateAuthor(news.getAuthor(), news.getAuthor().getId());
        newsService.addAuthorToNews(news, news.getAuthor());
        for (Tag tag : news.getTags()) {
            tagService.createTag(tag);
            newsService.addTagToNews(tag, news);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteNews(long newsId) {

        newsService.deleteNews(newsId);
        commentService.deleteCommentsByNewsId(newsId);
    }

    /**
     * {@inheritDoc}
     */
    public List<News> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        return newsList;
    }

    /**
     * {@inheritDoc}
     */
    public News getNewsById(long newsId) {
        News news = newsService.getNewsById(newsId);
        return news;
    }

    /**
     * {@inheritDoc}
     */
    public void createAuthor(Author author) {
        authorService.createAuthor(author);
    }

    /**
     * {@inheritDoc}
     */
    public List<News> searchBySearchCriteria(SearchCriteria searchCriteria) {
        List<News> newsList = newsService.searchNewsBySearchCriteria(searchCriteria);
        return newsList;
    }

    /**
     * {@inheritDoc}
     */
    public void addTagForNews(Tag tag, News news) {
        newsService.addTagToNews(tag, news);
    }

    /**
     * {@inheritDoc}
     */
    public void addTagsForNews(List<Tag> tags, News news) {
        for (Tag tag : tags) {
            newsService.addTagToNews(tag, news);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addCommentForNews(Comment comment, News news) {
        comment.setNews(news);
        commentService.createComment(comment);
    }

    /**
     * {@inheritDoc}
     */
    public void addCommentsForNews(List<Comment> comments, News news) {
        for (Comment comment : comments) {
            comment.setNews(news);
            commentService.createComment(comment);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void deleteComment(long commentId) {
        commentService.deleteComment(commentId);
    }

    /**
     * {@inheritDoc}
     */
    public int countAllNews() {
        return newsService.countAllNews();
    }


    public List<News> getNewsForPage(int currentPage, int numberOfNewsPerPage){
        List<News> newsList = newsService.getNewsForPage(currentPage, numberOfNewsPerPage);
        return newsList;
    }

    public List<News> getNewsBySearchCriteriaForPage(SearchCriteria searchCriteria, int currentPage, int numberOfNewsPerPage){
        List<News> newsList = newsService.getNewsBySearchCriteriaForPage(searchCriteria, currentPage, numberOfNewsPerPage);
        return newsList;
    }

    public List<Tag> getAllTags(){
        List<Tag> allTags = tagService.getAllTags();
        return allTags;
    }

    public List<Author> getAllAuthors(){
        List<Author> allAuthors = authorService.getAllAuthors();
        return allAuthors;
    }

    public Author getAuthorById(long authorId){
        Author author = authorService.getAuthorById(authorId);
        return author;
    }

    public Tag getTagById(long tagId){
        Tag tag = tagService.getTagById(tagId);
        return tag;
    }

    public List<News> getAllNewsWithSort(){
        List<News> newsList = newsService.getAllNewsWithSort();
        return newsList;
    }

    public List<News> getNewsBySearchCriteriaWithSort(SearchCriteria searchCriteria){
        List<News> newsList = newsService.getNewsBySearchCriteriaWithSort(searchCriteria);
        return newsList;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public TagService getTagService() {
        return tagService;
    }

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }
}
