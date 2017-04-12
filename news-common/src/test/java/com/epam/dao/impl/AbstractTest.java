package com.epam.dao.impl;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;

/**
 * Created by Hleb_Pus on 11/23/2015.
 */

@Ignore
public class AbstractTest extends DatabaseTestCase {

    protected ApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
    protected IDataSet loadedDataSet;

    protected IDataSet getDataSet() throws Exception {
        loadedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/main/resources/DataSource.xml"));
        return loadedDataSet;
    }

    protected IDatabaseConnection getConnection() throws Exception {

        DataSource dataSource = (DataSource) context.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        return new DatabaseConnection(connection);
    }

    @Test
    public void testDataSet() throws Exception {

        assertNotNull(loadedDataSet);
    }
}
