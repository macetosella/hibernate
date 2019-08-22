
package com.educacionit.hibernate.beginners.test;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.entity.Employee3Annotation;
import com.educacionit.hibernate.beginners.entity.Owner2Annotation;
import com.educacionit.hibernate.beginners.entity.Person3Annotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateHierarchy3AnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateHierarchy3AnnotationTest.class);


    public HibernateHierarchy3AnnotationTest () {

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
    @DisplayName ("Create New Objects [Person3, Employee3, Owner2]")
    public void m1 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info("Creating new person 3...");
            Person3Annotation person3 = new Person3Annotation ("Homer", "Simpson");
            session.save (person3);

            logger.info ("Creating new employee 3...");
            Employee3Annotation employee3 = new Employee3Annotation ("Bart", "Simpson", "IT",
                                                                     new Date ());
            session.save (employee3);

            logger.info ("Creating new owner 2...");
            Owner2Annotation owner2 = new Owner2Annotation ("Lisa", "Simpson", 1,
                                                            1);
            session.save (owner2);

            tx.commit ();

            Assertions.assertTrue (person3.getPersonId () > 0, String.format ("Problems creating the new person 3 %s", person3.getFirstName ()));
            Assertions.assertTrue (employee3.getPersonId () > 0, String.format ("Problems creating the new employee 3 %s", employee3.getFirstName ()));
            Assertions.assertTrue (owner2.getPersonId () > 0, String.format ("Problems creating the new owner 2 %s", owner2.getFirstName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all persons 3")
    public void m2 () {

        Session session = null;
        List<Person3Annotation> persons;

        try {

            logger.info ("Executing select all persons 3.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            persons = (List)session.createCriteria (Person3Annotation.class).list ();

            Assertions.assertFalse (persons.isEmpty (), "There are not persons 3 found!!!");

            logger.info ("Print all persons 3 info.");
            persons.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all employees 3")
    public void m3 () {

        Session session = null;
        List<Employee3Annotation> employlees2;

        try {

            logger.info ("Executing select all employees 3.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            employlees2 = (List)session.createCriteria (Employee3Annotation.class).list ();

            Assertions.assertFalse (employlees2.isEmpty (), "There are not employees 3 found!!!");

            logger.info ("Print all employees 3 info.");
            employlees2.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all owners 2")
    public void m4 () {

        Session session = null;
        List<Owner2Annotation> owners;

        try {

            logger.info ("Executing select all owners 2.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            owners = (List)session.createCriteria (Owner2Annotation.class).list ();

            Assertions.assertFalse (owners.isEmpty (), "There are not owners 2 found!!!");

            logger.info ("Print all owners 2 info.");
            owners.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Delete all owners 2")
    public void m5 () {

        final Session session;
        Transaction tx = null;
        List<Owner2Annotation> values;

        try {

            logger.debug ("Delete all owners 2.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Owner2").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Owner2").list ();
            Assertions.assertTrue (values.isEmpty (), "There are owners 2 found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all employees 3")
    public void m6 () {

        final Session session;
        Transaction tx = null;
        List<Employee3Annotation> values;

        try {

            logger.debug ("Delete all employees 3.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Employee3").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Employee3").list ();
            Assertions.assertTrue (values.isEmpty (), "There are employees 3 found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all persons 3")
    public void m7 () {

        final Session session;
        Transaction tx = null;
        List<Person3Annotation> values;

        try {

            logger.debug ("Delete all persons 3.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Person3").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Person3").list ();
            Assertions.assertTrue (values.isEmpty (), "There are persons 3 found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}