
package com.educacionit.hibernate.beginners.test;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.Transaction;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.entity.EmployeeAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateAnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateAnnotationTest.class);


    public HibernateAnnotationTest () {

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
    @DisplayName ("Create New Records")
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
            EmployeeAnnotation[] values = new EmployeeAnnotation[]{

                    new EmployeeAnnotation ("Homer Simpson", "Test", new Date()),
                    new EmployeeAnnotation ("Marge Simpson", "Test", new Date()),
                    new EmployeeAnnotation ("Bart Simpson", "Test", new Date()),
                    new EmployeeAnnotation ("Lisa Simpson", "Test", new Date()),
                    new EmployeeAnnotation ("Maggie Simpson", "Test", new Date())
            };

            // Save the data.
            for (EmployeeAnnotation e : values) {

                logger.info (String.format ("Saving value %s", e.getName ()));
                session.save(e);
                logger.info (String.format ("Value %s saved!", e.getName ()));
            }
            tx.commit ();
            Assertions.assertTrue (values[0].getId () > 0, String.format ("Problems creating teh new employee %s", values[0].getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all Employees")
    public void m2 () {

        Session session = null;
        List<EmployeeAnnotation> employees;

        try {

            logger.info ("Executing select all employees.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            employees = (List)session.createCriteria (EmployeeAnnotation.class).list ();

            logger.info ("Print all employees info.");
            employees.forEach ( e -> logger.info (e.getName ()));

            Assertions.assertFalse (employees.isEmpty (), "There are not employees found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Update all Employees")
    public void m3 () {

        final Session session;
        Transaction tx = null;
        List<EmployeeAnnotation> employees;

        try {

            logger.info ("Updating all employees.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            employees = (List)session.createCriteria (EmployeeAnnotation.class).list ();

            logger.info ("Print all employees info.");
            employees.forEach (e -> {

                logger.info (String.format ("Updating %s ", e.getName ()));
                e.setName (e.getName ().toUpperCase ());
                session.save (e);
            });
            tx.commit ();

            Assertions.assertFalse (employees.isEmpty (), "There are not employees found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all employees")
    public void m4 () {

        final Session session;
        Transaction tx;
        List<EmployeeAnnotation> values;

        try {

            logger.debug ("Delete all employees.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Employee").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not employees found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Employee").list ();
            Assertions.assertTrue (values.isEmpty (), "There are employees found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}