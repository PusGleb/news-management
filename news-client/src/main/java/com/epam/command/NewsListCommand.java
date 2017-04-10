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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class NewsListCommand implements Command {

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    private CommandService commandService = new CommandService();

    public String execute(HttpServletRequest request) {

        int currentPage = 1;

        if (request.getParameter(Constant.PARAM_NAME_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(Constant.PARAM_NAME_PAGE));
        }

        HttpSession session = request.getSession();
        SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute(Constant.PARAM_NAME_SEARCH_CRITERIA);

        List<News> newsList = null;
        List<News> allNews = null;

        if (searchCriteria == null) {
            newsList = service.getNewsForPage(currentPage, Constant.NUMBER_OF_NEWS_PER_PAGE);
            allNews = service.getAllNews();
        }
        else if (searchCriteria != null) {
            newsList = service.getNewsBySearchCriteriaForPage(searchCriteria, currentPage, Constant.NUMBER_OF_NEWS_PER_PAGE);
            allNews = service.getNewsBySearchCriteriaWithSort(searchCriteria);
        }

        int numberOfPages = commandService.returnNumberOfPages(allNews, Constant.NUMBER_OF_NEWS_PER_PAGE);

        List<Tag> allTags = service.getAllTags();
        List<Author> allAuthors = service.getAllAuthors();

        request.setAttribute(Constant.PARAM_NAME_NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Constant.PARAM_NAME_NEWS_LIST, newsList);

        session.setAttribute(Constant.PARAM_NAME_CURRENT_PAGE, currentPage);
        session.setAttribute(Constant.PARAM_NAME_ALL_TAGS, allTags);
        session.setAttribute(Constant.PARAM_NAME_ALL_AUTHORS, allAuthors);

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.NEWS_LIST_PAGE_PATH);
        return page;
    }
}
