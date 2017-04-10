package com.epam.controller;


import com.epam.command.Command;
import com.epam.manager.CommandManager;
import com.epam.manager.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page;

        CommandManager commandManager = CommandManager.getInstance();

        try {
            Command command = commandManager.getCommand(request);

            page = command.execute(request);
            gotoPage(request, response, page);

        } catch (ServletException e) {

            request.setAttribute("errorMessage", "errorMessage");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            gotoPage(request, response, page);

        } catch (IOException e) {

            request.setAttribute("errorMessage", "errorMessage");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
            gotoPage(request, response, page);
        }
    }

    private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

