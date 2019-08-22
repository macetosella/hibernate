
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.entity.CategoryAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateRelationship3AnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateRelationship3AnnotationTest.class);


    public HibernateRelationship3AnnotationTest () {

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
            CategoryAnnotation c = new CategoryAnnotation ("Technology", "Technology");

            CategoryAnnotation c1 = new CategoryAnnotation ("Hardware", "Hardware");
            c1.setCategory (c);

            CategoryAnnotation c2 = new CategoryAnnotation ("Software", "Software");
            c2.setCategory (c);

            CategoryAnnotation c3 = new CategoryAnnotation ("Books", "Books");
            c3.setCategory (c);

            CategoryAnnotation c4 = new CategoryAnnotation ("Games", "Games");
            c4.setCategory (c);

            CategoryAnnotation c5 = new CategoryAnnotation ("Action Games", "Action Games");
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
        List<CategoryAnnotation> categories;

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
        List<CategoryAnnotation> values;

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