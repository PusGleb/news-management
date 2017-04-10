package com.epam.manager;

import com.epam.command.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by anton_pus on 1/6/2016.
 */
public class CommandManager {

    private static CommandManager instance;

    private HashMap<String, Command> commands = new HashMap<String, Command>();

    private CommandManager() {
        this.commands.put("newsList", new NewsListCommand());
        this.commands.put("newsView", new NewsViewCommand());
        this.commands.put("filter", new FilterCommand());
        this.commands.put("reset", new ResetCommand());
        this.commands.put("goToNextOrPreviousNews", new GoToNextOrPreviousNewsCommand());
        this.commands.put("addComment", new AddCommentCommand());
        this.commands.put("locale", new LocaleCommand());
    }

    public Command getCommand(HttpServletRequest request) {

        String action = request.getParameter("command");
        Command command = commands.get(action);

        if (command == null) {
            command = new NewsListCommand();
        }
        return command;
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }
}
