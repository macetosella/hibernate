
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.entity.CountryAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class AnnotationNamedQueryHibernateTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (AnnotationNamedQueryHibernateTest.class);


    public AnnotationNamedQueryHibernateTest () {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionAnnotationFactory ();
    }

    @AfterAll
    public static void destroy () {

        sessionFactory.close ();
    }


    @Test
    @DisplayName ("Find countries by name")
    public void m1 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Finding All countries by name %y%...");
            Query query = session.getNamedQuery("findCountryByName").setString ("name", "%y%");
            List<CountryAnnotation> list = query.list ();

            Assertions.assertFalse (list.isEmpty (), "There are not countries!");

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
    @DisplayName ("Find countries by name [Native Query]")
    public void m2 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Finding All countries by name %A...");
            Query query = session.getNamedQuery("findCountryByNameNative").setString ("name", "A%");
            List<CountryAnnotation> list = query.list ();

            Assertions.assertFalse (list.isEmpty (), "There are not countries!");

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