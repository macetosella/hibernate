
package com.educacionit.hibernate.beginners.test;


import com.educacionit.hibernate.beginners.entity.EmployeeXML;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.util.HibernateUtil;

import java.util.List;


public class XMLNamedQueryHibernateTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (XMLNamedQueryHibernateTest.class);


    public XMLNamedQueryHibernateTest () {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionFactory ();
    }

    @AfterAll
    public static void destroy () {

        sessionFactory.close ();
    }


    @Test
    @DisplayName ("Counting employees")
    public void m1 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Counting employees...");
            Query query = session.getNamedQuery("findCount");
            Long result = (Long)query.uniqueResult ();

            Assertions.assertTrue (result == 0, "There are employees!");

        } catch (Exception ex) {

            String m = String.format ("Problems executing test %s", ex.getMessage ());
            logger.error (m);
            Assertions.assertFalse (Boolean.TRUE, m);

        } finally {

            logger.info ("Closing session...");
            session.close ();
        }
    }

    @Test
    @DisplayName ("Counting employees [Native Query]")
    public void m2 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Counting employees...");
            List<EmployeeXML> result = session.getNamedQuery("findCountNative").list ();

            Assertions.assertTrue (result.isEmpty (), "There are employees!");

        } catch (Exception ex) {

            String m = String.format ("Problems executing test %s", ex.getMessage ());
            logger.error (m);
            Assertions.assertFalse (Boolean.TRUE, m);

        } finally {

            logger.info ("Closing session...");
            session.close ();
        }
    }
}