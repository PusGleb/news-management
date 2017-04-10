package com.epam.dao.impl;

import javax.sql.DataSource;

/**
 *  This class contains dataSource and getter and setters methods for him
 *  AbstractDAO makes the code cleaner for com.epam.dao class
 *
 * @author Hleb_Pus
 */
public class AbstractDAO {

    protected DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
