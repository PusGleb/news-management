package com.epam.command;

import com.epam.dao.impl.NewsDAOImpl;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.News;
import com.epam.manager.ConfigurationManager;
import com.epam.service.CommandService;
import com.epam.service.NewsManagementService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class NewsViewCommand implements Command {

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    private CommandService commandService = new CommandService();

    public String execute(HttpServletRequest request) {

        int newsId = Integer.valueOf(request.getParameter("newsId"));

        HttpSession session = request.getSession();
        List<News> newsList = new ArrayList<News>();

        if (session.getAttribute("searchCriteria") != null) {
            SearchCriteria criteria = (SearchCriteria) session.getAttribute("searchCriteria");
            newsList = service.getNewsBySearchCriteriaWithSort(criteria);
        } else if (session.getAttribute("searchCriteria") == null) {
            newsList = service.getAllNewsWithSort();
        }

        int index = commandService.getIndexByNewsId(newsList,newsId);

        int sizeOfAllNews = newsList.size() - 1;

        News news = newsList.get(index);

        request.setAttribute("news", news);
        request.setAttribute("currentIndex", index);

        session.setAttribute("sizeOfAllNews", sizeOfAllNews);
        session.setAttribute("newsListForNextAndPreviousCommand", newsList);

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.NEWS_VIEW_PAGE_PATH);
        return page;
    }
}
