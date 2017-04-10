package com.epam.dao.impl;

import com.epam.dao.NewsDAO;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Tag;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */
public class NewsDAOTest extends AbstractTest {

    private NewsDAO newsDAO = (NewsDAO) context.getBean("newsDAO");

    @Test
    public void testUpdateNews() throws Exception {

        News news = returnNewsFromTable();

        newsDAO.updateNews(news, news.getId());
        News updateNews = newsDAO.getNewsById((news.getId()));

        assertEquals(news, updateNews);
    }

    @Test
    public void testAddNews() throws Exception {

        News news = returnNewsFromTable();

        long newsId = newsDAO.createNews(news);
        News expNews = newsDAO.getNewsById(newsId);

        assertEquals(news, expNews);
    }

    @Test
    public void testGetNews() throws Exception {

        News news = returnNewsFromTable();
        News expNews = newsDAO.getNewsById(news.getId());

        assertEquals(news, expNews);
    }

    @Test
    public void testDeleteNews() throws Exception {

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/expectedDataSource.xml"));
        ITable expectedTable = expectedDataSet.getTable("news");
        String newsId = (String) expectedTable.getValue(0, "news_id");

        newsDAO.deleteNews(Long.parseLong(newsId));

        assertNull(newsDAO.getNewsById(Long.parseLong(newsId)));
    }

    @Test
    public void testSearchBySearchCriteriaWithAuthor() throws Exception {

        News news = returnNewsFromTable();
        List<News> newsList = new ArrayList<News>();
        newsList.add(news);

        SearchCriteria searchCriteriaWithAuthor = new SearchCriteria();

        Author author = new Author();
        author.setId(1);
        searchCriteriaWithAuthor.setAuthor(author);
        List<News> expNewsList = newsDAO.searchBySearchCriteria(searchCriteriaWithAuthor);
        assertEquals(newsList, expNewsList);
    }

    @Test
    public void testSearchBySearchCriteriaWithTags() throws Exception {

        News news = returnNewsFromTable();
        List<News> newsList = new ArrayList<News>();
        newsList.add(news);

        SearchCriteria searchCriteriaWithTags = new SearchCriteria();
        List<Tag> tags = new ArrayList<Tag>();
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();

        tag1.setId(1);
        tag2.setId(2);

        tags.add(tag1);
        tags.add(tag2);

        searchCriteriaWithTags.setTags(tags);

        List<News> expNewsList = newsDAO.searchBySearchCriteria(searchCriteriaWithTags);

        assertEquals(newsList, expNewsList);
    }

    @Test
    public void testSearchBySearchCriteriaWithTagsAndYuthor() throws Exception {

        News news = returnNewsFromTable();
        List<News> newsList = new ArrayList<News>();
        newsList.add(news);
        SearchCriteria searchCriteriaWithTagsAndAuthor = new SearchCriteria();
        Author author = new Author();

        author.setId(1);

        List<Tag> tags = new ArrayList<Tag>();
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();

        tag1.setId(1);
        tag2.setId(2);

        tags.add(tag1);
        tags.add(tag2);

        searchCriteriaWithTagsAndAuthor.setTags(tags);
        searchCriteriaWithTagsAndAuthor.setAuthor(author);

        List<News> expNewsList = newsDAO.searchBySearchCriteria(searchCriteriaWithTagsAndAuthor);

        assertEquals(newsList, expNewsList);
    }

    private News returnNewsFromTable() throws Exception {

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/expectedDataSource.xml"));
        ITable expectedTable = expectedDataSet.getTable("news");
        String expId = (String) expectedTable.getValue(0, "news_id");
        String expTitle = (String) expectedTable.getValue(0, "title");
        String expShortText = (String) expectedTable.getValue(0, "short_text");
        String expFullText = (String) expectedTable.getValue(0, "full_text");

        News news = new News();

        news.setId(Long.parseLong(expId));
        news.setTitle(expTitle);
        news.setShortText(expShortText);
        news.setFullText(expFullText);

        return news;
    }
}
