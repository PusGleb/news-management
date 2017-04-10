package com.epam.service.impl;

import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.Comment;
import com.epam.entity.News;
import com.epam.entity.Tag;

import com.epam.service.AuthorService;
import com.epam.service.CommentService;
import com.epam.service.NewsService;
import com.epam.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class NewsManagementServiceTest {

    @Mock
    private NewsService newsService;

    @Mock
    private AuthorService authorService;

    @Mock
    private TagService tagService;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private NewsManagementServiceImpl newsManagementService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteNews(){
        long newsId = 1;
        newsManagementService.deleteNews(newsId);
        verify(newsService).deleteNews(newsId);
    }
    @Test
    public void testGetAllNews() {
        newsManagementService.getAllNews();
        verify(newsService).getAllNews();
    }

    @Test
    public void testGetNewsById(){
        long newsId = 1;
        newsManagementService.getNewsById(newsId);
        verify(newsService).getNewsById(newsId);
    }

    @Test
    public void testCreateAuthor(){
        Author author = new Author();
        newsManagementService.createAuthor(author);
        verify(authorService).createAuthor(author);
    }

    @Test
    public void testSearchBySearchCriteria(){
        SearchCriteria searchCriteria = new SearchCriteria();
        newsManagementService.searchBySearchCriteria(searchCriteria);
        verify(newsService).searchNewsBySearchCriteria(searchCriteria);
    }

    @Test
    public void testAddTagForNews(){
        News news = new News();
        Tag tag = new Tag();
        newsManagementService.addTagForNews(tag, news);
        verify(newsService).addTagToNews(tag, news);
    }

    @Test
    public void testAddTagsForNews(){
        List<Tag> tags = new ArrayList<Tag>();
        Tag tag = new Tag();
        tags.add(tag);
        News news = new News();
        newsManagementService.addTagsForNews(tags, news);
        verify(newsService).addTagToNews(tag,news);
    }

    @Test
    public void testAddCommentForNews(){
        Comment comment = new Comment();
        News news = new News();
        newsManagementService.addCommentForNews(comment, news);
        verify(commentService).createComment(comment);
    }

    @Test
    public void testAddCommentsForNews(){
        News news = new News();
        List<Comment> comments = new ArrayList<Comment>();
        Comment comment = new Comment();
        comments.add(comment);
        newsManagementService.addCommentsForNews(comments, news);
        verify(commentService).createComment(comment);
    }

    @Test
    public void testDeleteComment(){
        long commentId = 1;
        newsManagementService.deleteComment(commentId);
        verify(commentService).deleteComment(commentId);
    }

    @Test
    public void testCountAllNews(){
        newsManagementService.countAllNews();
        verify(newsService).countAllNews();
    }

    @Test
    public void testEditNews(){
        long newsId = 1;
        long authorId = 1;
        Author author = new Author();
        author.setId(authorId);
        News news = new News();
        news.setId(newsId);
        news.setAuthor(author);
        Tag tag = new Tag();
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(tag);
        news.setTags(tags);
        newsManagementService.editNews(news, newsId);
        verify(newsService).updateNews(news, newsId);
        verify(authorService).updateAuthor(author, authorId);
        verify(newsService).addAuthorToNews(news, author);
        verify(tagService).createTag(tag);
        verify(newsService).addTagToNews(tag,news);
    }
}

