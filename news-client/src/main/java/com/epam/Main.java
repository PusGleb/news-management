package com.epam;

import com.epam.dao.AuthorDAO;
import com.epam.entity.Author;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Hleb_Pus on 3/2/2017.
 */
public class Main {


        public static void main(String[] args) {
            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

            AuthorDAO authorDAO = (AuthorDAO) context.getBean("authorDAO");

            Author author =  authorDAO.getAuthorById(6);
            System.out.println(author.getName() +""+ author.getExpired());
        }

}
