package com.epam.command;

import com.epam.dao.impl.NewsDAOImpl;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.News;
import com.epam.manager.ConfigurationManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton_pus on 1/23/2016.
 */
public class GoToNextOrPreviousNewsCommand implements Command {

    public String execute(HttpServletRequest request) {

        int index = Integer.valueOf(request.getParameter("currentIndex"));

        HttpSession session = request.getSession();
        List<News> newsList = (List<News>) session.getAttribute("newsListForNextAndPreviousCommand");

        News nextNews = newsList.get(index);

        request.setAttribute("news", nextNews);
        request.setAttribute("currentIndex", index);

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.NEWS_VIEW_PAGE_PATH);
        return page;
    }
}
