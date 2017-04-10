package com.epam.service;

import com.epam.dao.impl.AuthorDAOImpl;
import com.epam.dao.impl.NewsDAOImpl;
import com.epam.dao.impl.TagDAOImpl;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.entity.Tag;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton_pus on 1/23/2016.
 */
public class CommandService {

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    public int returnNumberOfPages(List<News> newsList, int recordPerPages) {
        int numberOfAllNews = newsList.size();
        return (int) Math.ceil((double) numberOfAllNews / recordPerPages);
    }

    public SearchCriteria returnSearchCriteriaByRequest(HttpServletRequest request){
        SearchCriteria criteria = null;
        if (request.getParameter("filterByAuthor") != null) {
            int authorId = Integer.valueOf(request.getParameter("filterByAuthor"));
            Author author = service.getAuthorById(authorId);
            criteria = new SearchCriteria();
            criteria.setAuthor(author);

            if (request.getParameter("filterByTags") != null) {
                String[] tagsId = request.getParameterValues("filterByTags");
                List<Tag> tags = getTagsBySelect(tagsId);
                criteria.setTags(tags);
            }
        } if (request.getParameter("filterByTags") != null & request.getParameter("filterByAuthor") == null) {
            criteria = new SearchCriteria();
            String[] tagsId = request.getParameterValues("filterByTags");
            List<Tag> tags = getTagsBySelect(tagsId);

            criteria.setTags(tags);
        }
        return criteria;
    }

    public List<Tag> getTagsBySelect(String[] tagsId){
        List<Tag> tags = new ArrayList<Tag>();
        for (int i = 0; i < tagsId.length; i++) {
            int tagId = Integer.valueOf(tagsId[i]);
            System.out.print(tagId);
            Tag tag = service.getTagById(tagId);
            tags.add(tag);
        }
        return tags;
    }

    public int getIndexByNewsId(List<News> newsList, int newsId){
        int index = 0;
        for (News news : newsList) {
            if (news.getId() == newsId) {
                index = newsList.indexOf(news);
            }
        }
        return index;
    }
}
