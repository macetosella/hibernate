
package com.educacionit.hibernate.beginners.test;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.entity.StudentAnnotation;
import com.educacionit.hibernate.beginners.entity.MeetingAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateRelationship2AnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateRelationship2AnnotationTest.class);


    public HibernateRelationship2AnnotationTest () {

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
    @DisplayName ("Create new meeting")
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
            MeetingAnnotation m = new MeetingAnnotation ("Technology", new Date ());

            // Save the data.
            logger.info (String.format ("Saving value %s", m.getSubject ()));
            session.save (m);
            logger.info (String.format ("Value %s saved!", m.getSubject ()));

            tx.commit ();
            Assertions.assertTrue (m.getId () > 0, String.format ("Problems creating the new meeting type %s", m.getSubject ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Create new student and adding to meeting")
    public void m2 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Searching meeting by name \"Technology\"");
            MeetingAnnotation m = (MeetingAnnotation)session.createQuery ("from Meeting m where m.subject = 'Technology'").uniqueResult ();
            Assertions.assertNotNull (m, "There is not meeting by name \"Technology\"");

            logger.info ("Creating values to insert...");
            StudentAnnotation s1 = new StudentAnnotation ("Homer", "Simpson");
            s1.getMeetings ().add (m);

            StudentAnnotation s2 = new StudentAnnotation ("Lisa", "Simpson");
            s2.getMeetings ().add (m);

            StudentAnnotation s3 = new StudentAnnotation ("Bart", "Simpson");
            s3.getMeetings ().add (m);

            StudentAnnotation[] values = new StudentAnnotation[] { s1, s2, s3};

            // Save the data.
            for (StudentAnnotation e : values) {

                logger.info (String.format ("Saving value %s", e.getFirstName ()));
                session.save(e);
                logger.info (String.format ("Value %s saved!", e.getFirstName ()));
            }
            tx.commit ();
            Assertions.assertTrue (values[0].getId () > 0, String.format ("Problems creating the new student %s", values[0].getFirstName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all student and meetings")
    public void m3 () {

        Session session = null;
        List<StudentAnnotation> students;

        try {

            logger.info ("Executing select all students.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            students = (List)session.createQuery ("from Student").list ();

            Assertions.assertFalse (students.isEmpty (), "There are not students found!!!");

            logger.info ("Print all students info.");
            students.forEach ( e -> logger.info (String.format ("Student %s has (%d) meetings",
                    e.getFirstName (), e.getMeetings ().size ()))
            );

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all meetings and student")
    public void m4 () {

        Session session = null;
        List<MeetingAnnotation> meetings;

        try {

            logger.info ("Executing select all meetings.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            meetings = (List)session.createQuery ("from Meeting").list ();

            Assertions.assertFalse (meetings.isEmpty (), "There are not meetings found!!!");

            logger.info ("Print all meeting info.");
            meetings.forEach ( e -> logger.info (String.format ("Meeting %s has (%d) students",
                    e.getSubject (), e.getStudents ().size ()))
            );

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Delete all meetings")
    public void m5 () {

        final Session session;
        Transaction tx;
        List<MeetingAnnotation> values;

        try {

            logger.debug ("Delete all meetings.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Meeting").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not meetings found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Meeting").list ();
            Assertions.assertTrue (values.isEmpty (), "There are meetings found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all students")
    public void m6 () {

        final Session session;
        Transaction tx;
        List<StudentAnnotation> values;

        try {

            logger.debug ("Delete all students.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Student").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not students found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Student").list ();
            Assertions.assertTrue (values.isEmpty (), "There are students found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}