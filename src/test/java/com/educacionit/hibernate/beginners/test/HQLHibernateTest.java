
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.*;

import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educacionit.hibernate.beginners.entity.CountryAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;



public class HQLHibernateTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HQLHibernateTest.class);


    public HQLHibernateTest () {

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
    @DisplayName ("Find all countries")
    public void m1 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding All countries..."));
            Query query = session.createQuery("from Country");
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
    @DisplayName ("Find countries by code")
    public void m2 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding countries by code \"AR\"..."));
            Query query = session.createQuery("from Country where code=:code");
            query.setParameter ("code", "AR");
            List<CountryAnnotation> list = query.list ();

            Assertions.assertFalse (list.isEmpty (), "There are not countries by code \"AR\"!");

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
    @DisplayName ("Update countries by code")
    public void m3 () {

        // Get a session.
        Session session = null;
        Transaction  tx = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Opening a transaction...");
            tx = session.beginTransaction ();

            logger.info (String.format ("Updating countries by code \"AR\"..."));
            Query query = session.createQuery("update Country set name = :name where code = :code");
            query.setParameter ("name", "ANITNEGRA");
            query.setParameter ("code", "AR");
            int result = query.executeUpdate();

            logger.info (String.format ("(%d) Countries by code \"AR\" updated!", result));
            tx.commit ();

            Assertions.assertTrue (result > 0, "There are not countries by code \"AR\" updated!");

        } catch (Exception ex) {

            String m = String.format ("Problems executing test %s", ex.getMessage ());
            logger.error (m);
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, m);

        } finally {

            logger.info ("Closing session...");
            session.close ();
        }
    }

    @Test
    @DisplayName ("Delete countries by code")
    public void m4 () {

        // Get a session.
        Session session = null;
        Transaction  tx = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Opening a transaction...");
            tx = session.beginTransaction ();

            logger.info (String.format ("Deleting countries by code \"AR\"..."));
            Query query = session.createQuery("delete Country where code = :code");
            query.setParameter ("code", "AR");
            int result = query.executeUpdate();

            logger.info (String.format ("(%d) Countries by code \"AR\" deleted!", result));
            tx.commit ();

            Assertions.assertTrue (result > 0, "There are not countries by code \"AR\" deleted!");

        } catch (Exception ex) {

            String m = String.format ("Problems executing test %s", ex.getMessage ());
            logger.error (m);
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, m);

        } finally {

            logger.info ("Closing session...");
            session.close ();
        }
    }

    @Test
    @DisplayName ("Find first 10 countries")
    public void m5 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding the first 10 countries..."));
            Query query = session.createQuery("from Country");
            query.setFirstResult (0);
            query.setMaxResults (10);
            List<CountryAnnotation> list = query.list ();

            Assertions.assertFalse (list.isEmpty (), "There are not countries!");
            Assertions.assertTrue (list.size () == 10, "There are not 10 countries!");

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
    @DisplayName ("Find count countries")
    public void m6 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Counting countries..."));
            Query query = session.createQuery("select count(name) from Country");
            Long result = (Long)query.uniqueResult ();

            Assertions.assertTrue (result > 0, "There are not countries!");

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
    @DisplayName ("Find max id country")
    public void m7 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding the max id country..."));
            Query query = session.createQuery("select max(id) from Country");
            Integer result = (Integer)query.uniqueResult ();

            Assertions.assertTrue (result > 0, "There are not countries!");

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
    @DisplayName ("Find min id country")
    public void m8 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding the min id country..."));
            Query query = session.createQuery("select min(id) from Country");
            Integer result = (Integer)query.uniqueResult ();

            Assertions.assertTrue (result > 0, "There are not countries!");

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
    @DisplayName ("Find all countries order by name desc")
    public void m9 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding all countries order by name desc..."));
            Query query = session.createQuery("from Country as c order by c.name desc");
            List<CountryAnnotation> result = query.list ();

            Assertions.assertFalse (result.isEmpty (), "There are not countries!");

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