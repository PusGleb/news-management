package com.epam.controller;

import com.epam.constant.Constant;
import com.epam.dao.search.SearchCriteria;
import com.epam.entity.Author;
import com.epam.entity.Comment;
import com.epam.entity.News;
import com.epam.entity.Tag;
import com.epam.service.ControllerService;
import com.epam.service.NewsManagementService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private NewsManagementService service = (NewsManagementService) context.getBean("newsManagementService");

    private ControllerService commandService = new ControllerService();


    @RequestMapping(value = "/newsList", method = RequestMethod.GET)
    public String newsList(@RequestParam(value = Constant.PARAM_NAME_PAGE, required=false) String page, ModelMap model, HttpServletRequest request) {

        int currentPage = 1;

        if (page != null) {
            currentPage = Integer.parseInt(page);
        }

        HttpSession session = request.getSession();
        SearchCriteria searchCriteria = (SearchCriteria) request.getSession().getAttribute(Constant.PARAM_NAME_SEARCH_CRITERIA);

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

        model.addAttribute(Constant.PARAM_NAME_NUMBER_OF_PAGES, numberOfPages);
        model.addAttribute(Constant.PARAM_NAME_NEWS_LIST, newsList);

        model.addAttribute("activeNewsList", "true");

        session.setAttribute(Constant.PARAM_NAME_CURRENT_PAGE, currentPage);
        session.setAttribute(Constant.PARAM_NAME_ALL_TAGS, allTags);
        session.setAttribute(Constant.PARAM_NAME_ALL_AUTHORS, allAuthors);

        return "admin_news_list";
    }


    @RequestMapping(value = "/filterNews", method = RequestMethod.POST)
    public String filterNews(@RequestParam(value = "filterByAuthor", required=false) Long authorId, @RequestParam(value = "filterByTags", required=false) long[] tagsIds, ModelMap model, HttpServletRequest request) {

        int currentPage = 1;

        HttpSession session = request.getSession();

        SearchCriteria criteria = null;
        List<News> newsList = null;
        List<News> allNews = null;

        if (authorId == null & tagsIds == null) {
            newsList = service.getNewsForPage(currentPage, Constant.NUMBER_OF_NEWS_PER_PAGE);
            allNews = service.getAllNews();

        } else {
            criteria = commandService.returnSearchCriteriaByRequest(authorId, tagsIds);
            newsList = service.getNewsBySearchCriteriaForPage(criteria, currentPage, Constant.NUMBER_OF_NEWS_PER_PAGE);
            allNews = service.getNewsBySearchCriteriaWithSort(criteria);
        }

        int numberOfPages = commandService.returnNumberOfPages(allNews, Constant.NUMBER_OF_NEWS_PER_PAGE);

        session.setAttribute("searchCriteria", criteria);
        session.setAttribute("currentPage", currentPage);

        model.addAttribute("newsList", newsList);
        model.addAttribute("numberOfPages", numberOfPages);

        return "admin_news_list";
    }


    @RequestMapping(value = "/resetFilter", method = RequestMethod.POST)
    public String resetFilter(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session != null) {
            session.removeAttribute("searchCriteria");
        }

        return "redirect:newsList";
    }

    @RequestMapping(value = "/editNews", method = RequestMethod.GET)
    public String editNews(@RequestParam(value = "newsId") int newsId, ModelMap model, HttpServletRequest request) {

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

        model.addAttribute("news", news);
        model.addAttribute("currentIndex", index);

        session.setAttribute("sizeOfAllNews", sizeOfAllNews);
        session.setAttribute("newsListForNextAndPreviousCommand", newsList);

        return "admin_news_view";
    }

    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public String deleteNews(@RequestParam(value = "newsIds", required=false) long[] newsIds, ModelMap model) {

        if (newsIds == null) {
            return "redirect:newsList";
        }
        for(int i=0; i<newsIds.length; i++){
            service.deleteNews(newsIds[i]);
        }
        return "redirect:newsList";
    }

    @RequestMapping(value = "/goToNextOrPreviousNews", method = RequestMethod.GET)
    public String goToNextOrPreviousNews(@RequestParam(value = "currentIndex") int index, ModelMap model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<News> newsList = (List<News>) session.getAttribute("newsListForNextAndPreviousCommand");

        News nextNews = newsList.get(index);

        model.addAttribute("news", nextNews);
        model.addAttribute("currentIndex", index);

        return "admin_news_view";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@RequestParam(value = "comment", required=false) String commentText,@RequestParam(value = "newsId") int newsId, ModelMap model) {

        News news = service.getNewsById(newsId);

        Comment comment = new Comment();

        comment.setCommentText(commentText);

        long time = new Date().getTime();
        Timestamp currentTime = new Timestamp(time);
        comment.setCreationDate(currentTime);

        service.addCommentForNews(comment, news);

        News updateNews = service.getNewsById(newsId);

        model.addAttribute("news", updateNews);

        return "admin_news_view";
    }



    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public String deleteComment(@RequestParam(value = "commentId") int commentId,@RequestParam(value = "newsId") int newsId, ModelMap model) {

        service.deleteComment(commentId);
        model.addAttribute("newsId", newsId);

        return "redirect:editNews";
    }

    @RequestMapping(value = "/goToNewsList", method = RequestMethod.GET)
    public String goToNewsList(ModelMap model) {

        return "redirect:newsList";
    }

    @RequestMapping(value = "/goToAddNews", method = RequestMethod.GET)
    public String goToAddNews(ModelMap model) {

        model.addAttribute("activeAddNews" , "true");
        return "addNews";
    }
}
