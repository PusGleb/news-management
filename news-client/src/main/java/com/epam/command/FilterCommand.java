package com.epam.command;

import com.epam.constant.Constant;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.News;
import com.epam.entity.Tag;
import com.epam.manager.ConfigurationManager;
import com.epam.service.CommandService;
import com.epam.service.NewsManagementService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class FilterCommand implements Command {

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    private CommandService commandService = new CommandService();

    public String execute(HttpServletRequest request) {

        int currentPage = 1;

        HttpSession session = request.getSession();

        SearchCriteria criteria = null;
        List<News> newsList = null;
        List<News> allNews = null;

        if (request.getParameter("filterByAuthor") == null & request.getParameter("filterByTags") == null) {
            newsList = service.getNewsForPage(currentPage, Constant.NUMBER_OF_NEWS_PER_PAGE);
            allNews = service.getAllNews();

        } else {
            criteria = commandService.returnSearchCriteriaByRequest(request);
            newsList = service.getNewsBySearchCriteriaForPage(criteria, currentPage, Constant.NUMBER_OF_NEWS_PER_PAGE);
            allNews = service.getNewsBySearchCriteriaWithSort(criteria);
        }

        int numberOfPages = commandService.returnNumberOfPages(allNews, Constant.NUMBER_OF_NEWS_PER_PAGE);

        session.setAttribute("searchCriteria",criteria);
        session.setAttribute("currentPage", currentPage);

        request.setAttribute("newsList", newsList);
        request.setAttribute("numberOfPages", numberOfPages);

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.NEWS_LIST_PAGE_PATH);
        return page;
    }
}
