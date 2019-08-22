
package com.educacionit.hibernate.beginners.test;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.entity.Person2Annotation;
import com.educacionit.hibernate.beginners.entity.Employee2Annotation;
import com.educacionit.hibernate.beginners.entity.OwnerAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateHierarchy2AnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateHierarchy2AnnotationTest.class);


    public HibernateHierarchy2AnnotationTest () {

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
    @DisplayName ("Create New Objects [Person2, Employee2, Owner]")
    public void m1 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info("Creating new person 2...");
            Person2Annotation person2 = new Person2Annotation ("Homer", "Simpson");
            session.save (person2);

            logger.info ("Creating new employee 2...");
            Employee2Annotation employee2 = new Employee2Annotation ("Bart", "Simpson", "IT",
                                                                     new Date ());
            session.save (employee2);

            logger.info ("Creating new owner...");
            OwnerAnnotation owner = new OwnerAnnotation ("Lisa", "Simpson", 1,
                                                         1);
            session.save (owner);

            tx.commit ();

            Assertions.assertTrue (person2.getPersonId () > 0, String.format ("Problems creating the new person 2 %s", person2.getFirstName ()));
            Assertions.assertTrue (employee2.getPersonId () > 0, String.format ("Problems creating the new employee 2 %s", employee2.getFirstName ()));
            Assertions.assertTrue (owner.getPersonId () > 0, String.format ("Problems creating the new owner %s", owner.getFirstName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all persons 2")
    public void m2 () {

        Session session = null;
        List<Person2Annotation> persons;

        try {

            logger.info ("Executing select all persons 2.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            persons = (List)session.createCriteria (Person2Annotation.class).list ();

            Assertions.assertFalse (persons.isEmpty (), "There are not persons 2 found!!!");

            logger.info ("Print all persons 2 info.");
            persons.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all employees 2")
    public void m3 () {

        Session session = null;
        List<Employee2Annotation> employlees2;

        try {

            logger.info ("Executing select all employees 2.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            employlees2 = (List)session.createCriteria (Employee2Annotation.class).list ();

            Assertions.assertFalse (employlees2.isEmpty (), "There are not employees 2 found!!!");

            logger.info ("Print all employees 2 info.");
            employlees2.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all owners")
    public void m4 () {

        Session session = null;
        List<OwnerAnnotation> owners;

        try {

            logger.info ("Executing select all owners.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            owners = (List)session.createCriteria (OwnerAnnotation.class).list ();

            Assertions.assertFalse (owners.isEmpty (), "There are not owners found!!!");

            logger.info ("Print all owners info.");
            owners.forEach ( e -> logger.info (e.getFirstName ()));

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Delete all owners")
    public void m5 () {

        final Session session;
        Transaction tx = null;
        List<OwnerAnnotation> values;

        try {

            logger.debug ("Delete all owners.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Owner").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Owner").list ();
            Assertions.assertTrue (values.isEmpty (), "There are owners found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all employees 2")
    public void m6 () {

        final Session session;
        Transaction tx = null;
        List<Employee2Annotation> values;

        try {

            logger.debug ("Delete all employees 2.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Employee2").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Employee2").list ();
            Assertions.assertTrue (values.isEmpty (), "There are employees 2 found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all persons 2")
    public void m7 () {

        final Session session;
        Transaction tx = null;
        List<Person2Annotation> values;

        try {

            logger.debug ("Delete all persons 2.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Person2").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Person2").list ();
            Assertions.assertTrue (values.isEmpty (), "There are persons 2 found!!!");

        } catch (Exception e) {

            tx.rollback ();
            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}