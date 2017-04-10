package com.epam.command;

import com.epam.dao.impl.NewsDAOImpl;
import com.epam.entity.News;
import com.epam.manager.ConfigurationManager;
import com.epam.service.CommandService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.enterprise.inject.New;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ResetCommand implements Command {

    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session != null) {
            session.removeAttribute("searchCriteria");
        }

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);

        return page;
    }
}
