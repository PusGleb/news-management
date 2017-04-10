package com.epam.command;

import com.epam.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by anton_pus on 1/23/2016.
 */
public class LocaleCommand implements Command {

    public String execute(HttpServletRequest request) {

        HttpSession session = request.getSession();

        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        return page;
    }
}
