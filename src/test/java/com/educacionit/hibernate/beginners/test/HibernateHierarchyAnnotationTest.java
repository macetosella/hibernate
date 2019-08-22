
package com.educacionit.hibernate.beginners.test;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.util.HibernateUtil;
import com.educacionit.hibernate.beginners.entity.PersonAnnotation;
import com.educacionit.hibernate.beginners.entity.WorkerAnnotation;


public class HibernateHierarchyAnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateHierarchyAnnotationTest.class);


    public HibernateHierarchyAnnotationTest () {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionHierarchyConfigFactory ();
    }

    @AfterAll
    public static void destroy () {

        sessionFactory.close ();
    }


    @Test
    @DisplayName ("Create new persons")
    public void m1 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info("Creating values to insert...");
            PersonAnnotation[] values = new PersonAnnotation[]{

                    new PersonAnnotation ("Homer", "Simpson"),
                    new PersonAnnotation ("Marge", "Simpson"),
                    new PersonAnnotation ("Bart", "Simpson"),
                    new PersonAnnotation ("Lisa", "Simpson"),
                    new PersonAnnotation ("Maggie", "Simpson")
            };

            // Save the data.
            for (PersonAnnotation p : values) {

                logger.info (String.format ("Saving value %s", p.getFirstName ()));
                session.save (p);
                logger.info (String.format ("Value %s saved!", p.getFirstName ()));
            }
            tx.commit ();
            Assertions.assertTrue (values[0].getPersonId () > 0, String.format ("Problems creating the new person %s", values[0].getFirstName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Create new workers")
    public void m2 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info("Creating values to insert...");
            WorkerAnnotation[] values = new WorkerAnnotation[]{

                    new WorkerAnnotation ("Patty", "Bouvier", new Date(), "IT"),
                    new WorkerAnnotation ("Selma", "Bouvier", new Date(), "IT"),
                    new WorkerAnnotation ("Kent", "Brockman", new Date(), "IT"),
                    new WorkerAnnotation ("Ned", "Flanders", new Date(), "IT"),
                    new WorkerAnnotation ("Todd", "Flanders", new Date(), "IT")
            };

            // Save the data.
            for (PersonAnnotation p : values) {

                logger.info (String.format ("Saving value %s", p.getFirstName ()));
                session.save (p);
                logger.info (String.format ("Value %s saved!", p.getFirstName ()));
            }
            tx.commit ();
            Assertions.assertTrue (values[0].getPersonId () > 0, String.format ("Problems creating the new worker %s", values[0].getFirstName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all persons")
    public void m3 () {

        Session session = null;
        List<PersonAnnotation> persons;

        try {

            logger.info ("Executing select all persons.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            persons = (List)session.createCriteria (PersonAnnotation.class).list ();

            Assertions.assertFalse (persons.isEmpty (), "There are not persons found!!!");

            logger.info ("Print all persons info.");
            persons.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all workers")
    public void m4 () {

        Session session = null;
        List<WorkerAnnotation> workers;

        try {

            logger.info ("Executing select all workers.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            workers = (List)session.createCriteria (WorkerAnnotation.class).list ();

            Assertions.assertFalse (workers.isEmpty (), "There are not workers found!!!");

            logger.info ("Print all workers info.");
            workers.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Delete all workers")
    public void m5 () {

        final Session session;
        Transaction tx = null;
        List<WorkerAnnotation> values;

        try {

            logger.debug ("Delete all workers.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Worker").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Worker").list ();
            Assertions.assertTrue (values.isEmpty (), "There are workers found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all persons")
    public void m6 () {

        final Session session;
        Transaction tx = null;
        List<PersonAnnotation> values;

        try {

            logger.debug ("Delete all persons.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Person").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Person").list ();
            Assertions.assertTrue (values.isEmpty (), "There are persons found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}