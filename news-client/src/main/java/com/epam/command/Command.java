package com.epam.command;

import javax.servlet.http.HttpServletRequest;


public interface Command {

    /**
     * @param request
     * @return concrete jsp page
     */
    String execute(HttpServletRequest request);
}
