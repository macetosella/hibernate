
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.entity.CompanyAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class SearchByPKHibernateAnnotationTest {


    private static int PK;

    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (SearchByPKHibernateAnnotationTest.class);


    public SearchByPKHibernateAnnotationTest() {

        super ();
    }


    @BeforeAll
    public static void setup () throws Exception {

        sessionFactory = HibernateUtil.getSessionAnnotationFactory ();

        // Get a session.
        Session session = null;
        Transaction tx;

        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Creating test values...");
            CompanyAnnotation[] values = new CompanyAnnotation[]{

                        new CompanyAnnotation ("Mercadolibre", "Argentina", "info@mercadolibre.com"),
                        new CompanyAnnotation ("Amazon", "EEUU", "info@amazon.com"),
                        new CompanyAnnotation ("Microsoft", "EEUU", "info@microsoft.com"),
                        new CompanyAnnotation ("Despegar", "Argentina", "info@despegar.com"),
                        new CompanyAnnotation ("AIRBNB", "UK", "info@airbnb.com")
                };

            // Save the data.
            for (CompanyAnnotation c : values) {

                logger.info (String.format ("Saving value %s", c.getName ()));
                session.save (c);
                logger.info (String.format ("Value %s saved!", c.getName ()));
            }
            tx.commit ();

            // Get a PK for execute the next test using find one by a PK existing.
            PK = values[0].getId ();
        }
        finally { session.close (); }
    }

    @AfterAll
    public static void destroy () {

        final Session session;
        Transaction tx;
        List<CompanyAnnotation> values;

        try {

            logger.debug ("Delete data test.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Company").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

        } catch (Exception e) {

            logger.error (e.getMessage ());
        }

        sessionFactory.close ();
    }


    @Test
    @DisplayName ("Find one using get (Found)")
    public void m1 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding one company by PK %d using get method.", PK));
            CompanyAnnotation c = (CompanyAnnotation) session.get (CompanyAnnotation.class, PK);
            logger.info (String.format ("Company by PK %d using get method found.", PK));

            Assertions.assertNotNull (c, String.format ("Company Id %d not found !!!", PK));

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
    @DisplayName ("Find one using get (Not Found)")
    public void m2 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding one company by PK %d using get method.", -1 * PK));
            CompanyAnnotation c = (CompanyAnnotation) session.get (CompanyAnnotation.class, -1 * PK);
            logger.info (String.format ("Company by PK %d using get method found.", -1 * PK));

            Assertions.assertNull (c, String.format ("Company Id %d found !!!", -1 * PK));

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
    @DisplayName ("Find one using load (Found)")
    public void m3 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding one company by PK %d using load method.", PK));
            CompanyAnnotation c = (CompanyAnnotation) session.load (CompanyAnnotation.class, PK);
            logger.info (String.format ("Company by PK %d using load method found.", PK));

            Assertions.assertNotNull (c, String.format ("Company Id %d found !!!", PK));

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
    @DisplayName ("Find one using load (Not Found)")
    public void m4 () {

        // Get a session.
        final Session session;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding one company by PK %d using load method.", -1 * PK));
            Assertions.assertThrows (ObjectNotFoundException.class, ()-> {

                CompanyAnnotation c = (CompanyAnnotation) session.load (CompanyAnnotation.class, -1 * PK);
                logger.info (String.format ("Company by PK %d using load method found, %s", -1 * PK, c));
            } );

        } catch (Exception ex) {

            String m = String.format ("Problems executing test %s", ex.getMessage ());
            logger.error (m);
            Assertions.assertFalse (Boolean.TRUE, m);

        }
    }

    @Test
    @DisplayName ("Find one using criteria (Found)")
    public void m5 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding one company by PK %d using criteria object.", PK));
            CompanyAnnotation c = (CompanyAnnotation) session.createCriteria (CompanyAnnotation.class).
                    add (Restrictions.eq ("id", PK)).uniqueResult ();
            logger.info (String.format ("Company by PK %d using criteria object found.", PK));

            Assertions.assertNotNull (c, String.format ("Company Id %d not found !!!", PK));

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