package com.epam.service.impl;

import com.epam.dao.impl.NewsDAOImpl;
import com.epam.entity.News;
import com.epam.dao.search.SearchCriteria;
import com.epam.service.impl.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class NewsServiceTest {

    @Mock
    private NewsDAOImpl newsDAOImpl;

    @InjectMocks
    private NewsServiceImpl newsService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNewsById(){
        long newsId = 1;
        newsService.getNewsById(newsId);
        verify(newsDAOImpl).getNewsById(newsId);
    }

    @Test
    public void testAddNews(){
        News news = new News();
        newsService.createNews(news);
        verify(newsDAOImpl).createNews(news);
    }

    @Test
    public void testUpdateNews(){

        long newsId = 1;
        News news = new News();
        newsService.updateNews(news, newsId);
        verify(newsDAOImpl).updateNews(news, newsId);
    }

    @Test
    public void testDeleteNews(){
        long newsId = 1;

        newsService.deleteNews(newsId);
        verify(newsDAOImpl).deleteNews(newsId);
    }

    @Test
    public void testCountAllNews(){

        List<News> newsList = new ArrayList<News>();
        newsList.add(new News());
        newsList.add(new News());

        when(newsDAOImpl.getAllNews()).thenReturn(newsList);
        long countAllNews = newsService.countAllNews();
        verify(newsDAOImpl).getAllNews();
        assertEquals(2,countAllNews);
    }

    @Test
    public void testSearchNewsBySearchCriteria(){

        SearchCriteria searchCriteria = new SearchCriteria();
        newsService.searchNewsBySearchCriteria(searchCriteria);
        verify(newsDAOImpl).searchBySearchCriteria(searchCriteria);
    }
}
