package com.epam.service;

import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.entity.Tag;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ControllerService {

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    public int returnNumberOfPages(List<News> newsList, int recordPerPages) {
        int numberOfAllNews = newsList.size();
        return (int) Math.ceil((double) numberOfAllNews / recordPerPages);
    }

    public SearchCriteria returnSearchCriteriaByRequest(Long authorId, long[] tagsIds){
        SearchCriteria criteria = null;
        if (authorId != null) {
            Author author = service.getAuthorById(authorId);
            criteria = new SearchCriteria();
            criteria.setAuthor(author);

            if (tagsIds != null) {
                List<Tag> tags = getTagsBySelect(tagsIds);
                criteria.setTags(tags);
            }
        } if (tagsIds != null & authorId == null) {
            criteria = new SearchCriteria();
            List<Tag> tags = getTagsBySelect(tagsIds);

            criteria.setTags(tags);
        }
        return criteria;
    }

    public List<Tag> getTagsBySelect(long[] tagsIds){
        List<Tag> tags = new ArrayList<Tag>();
        for (int i = 0; i < tagsIds.length; i++) {
            Tag tag = service.getTagById(tagsIds[i]);
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
