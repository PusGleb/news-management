package com.epam.manager;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Created by anton_pus on 1/6/2016.
 */
public class ConfigurationManager {

    private static ConfigurationManager instance;

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";

    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";
    public static final String NEWS_LIST_PAGE_PATH = "NEWS_LIST_PAGE_PATH";
    public static final String NEWS_VIEW_PAGE_PATH = "NEWS_VIEW_PAGE_PATH";

    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = PropertyResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
