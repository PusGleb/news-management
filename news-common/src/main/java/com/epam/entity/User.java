package com.epam.entity;

/**
 * Created by Hleb_Pus on 11/10/2015.
 */
public class User extends Entity {

    private String name;
    private String login;
    private String password;

    public User(long id, String name, String login, String password) {
        super(id);
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
