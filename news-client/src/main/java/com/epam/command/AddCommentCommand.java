package com.epam.command;

import com.epam.dao.impl.CommentDAOImpl;
import com.epam.dao.impl.NewsDAOImpl;
import com.epam.entity.Comment;
import com.epam.entity.News;
import com.epam.manager.ConfigurationManager;
import com.epam.service.NewsManagementService;
import com.epam.service.impl.NewsManagementServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class AddCommentCommand implements Command{

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    public String execute(HttpServletRequest request) {

        String commentText = request.getParameter("comment");

        int newsId = Integer.valueOf(request.getParameter("newsId"));
        News news = service.getNewsById(newsId);

        Comment comment = new Comment();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        long time = date.getTime();
        Timestamp currentTime = new Timestamp(time);

        comment.setCreationDate(currentTime);
        comment.setCommentText(commentText);

        service.addCommentForNews(comment, news);

        News updateNews = service.getNewsById(newsId);

        request.setAttribute("news", updateNews);
        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.NEWS_VIEW_PAGE_PATH);

        return page;
    }
}
