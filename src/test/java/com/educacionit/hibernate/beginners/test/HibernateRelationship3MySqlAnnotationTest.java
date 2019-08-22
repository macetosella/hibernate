
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.entity.CategoryMySqlAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateRelationship3MySqlAnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateRelationship3MySqlAnnotationTest.class);


    public HibernateRelationship3MySqlAnnotationTest () {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionAnnotationFactoryMySql ();
    }

    @AfterAll
    public static void destroy () {

        sessionFactory.close ();
    }


    @Test
    @DisplayName ("Create new categories")
    public void m1 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info("Creating value to insert...");
            CategoryMySqlAnnotation c = new CategoryMySqlAnnotation ("Technology", "Technology");

            CategoryMySqlAnnotation c1 = new CategoryMySqlAnnotation ("Hardware", "Hardware");
            c1.setCategory (c);

            CategoryMySqlAnnotation c2 = new CategoryMySqlAnnotation ("Software", "Software");
            c2.setCategory (c);

            CategoryMySqlAnnotation c3 = new CategoryMySqlAnnotation ("Books", "Books");
            c3.setCategory (c);

            CategoryMySqlAnnotation c4 = new CategoryMySqlAnnotation ("Games", "Games");
            c4.setCategory (c);

            CategoryMySqlAnnotation c5 = new CategoryMySqlAnnotation ("Action Games", "Action Games");
            c5.setCategory (c4);

            // Save the data.
            logger.info (String.format ("Saving values..."));
            session.save (c);
            session.save (c1);
            session.save (c2);
            session.save (c3);
            session.save (c4);
            session.save (c5);
            logger.info (String.format ("Value %s saved!", c.getName ()));

            tx.commit ();
            Assertions.assertTrue (c.getId () > 0, String.format ("Problems creating the new category %s", c.getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all categories")
    public void m2 () {

        Session session = null;
        List<CategoryMySqlAnnotation> categories;

        try {

            logger.info ("Executing select all categories.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            categories = (List)session.createQuery ("from Category").list ();

            Assertions.assertFalse (categories.isEmpty (), "There are not categories found!!!");

            logger.info ("Print all students info.");
            categories.forEach ( e -> logger.info (String.format ("Category %s has (%s) sub category",
                    e.getName (), (e.getCategory () != null ? e.getCategory ().getName () : "Without Parent!")))
            );

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Delete all categories")
    public void m3 () {

        final Session session;
        Transaction tx;
        List<CategoryMySqlAnnotation> values;

        try {

            logger.debug ("Delete all categories.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Category").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not categories found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Category").list ();
            Assertions.assertTrue (values.isEmpty (), "There are categories found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}