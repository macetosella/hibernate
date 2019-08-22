
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.entity.CompanyAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class ProjectionsHibernateAnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (ProjectionsHibernateAnnotationTest.class);


    public ProjectionsHibernateAnnotationTest () {

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
                        new CompanyAnnotation ("PDVSA", "Venezuela", "info@pdvsa.gob.ve"),
                        new CompanyAnnotation ("Ferrari", "Italia", "info@ferrari.com"),
                        new CompanyAnnotation ("Nokia", "Finlandia", "info@nokia.com"),
                        new CompanyAnnotation ("Toyota", "Japon", "info@toyota.com"),
                        new CompanyAnnotation ("Renault", "Francia", "info@renault.com"),
                        new CompanyAnnotation ("Zara", "España", "info@zara.com"),
                        new CompanyAnnotation ("AIRBNB", "UK", "info@airbnb.com")
                };

            // Save the data.
            for (CompanyAnnotation c : values) {

                logger.info (String.format ("Saving value %s", c.getName ()));
                session.save (c);
                logger.info (String.format ("Value %s saved!", c.getName ()));
            }
            tx.commit ();
            logger.info ("Test values created...");

        } finally {

            logger.info ("Closing the session...");
            session.close ();
        }
    }

    @AfterAll
    public static void destroy () {

        final Session session;
        Transaction tx;
        List<CompanyAnnotation> values;

        try {

            logger.debug ("Deleting data test.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Company").list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();
            logger.debug ("Data test deleted.");

        } catch (Exception e) {

            logger.error (e.getMessage ());
        }

        sessionFactory.close ();
    }

    @Test
    @DisplayName ("Counting companies")
    public void m1 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Counting companies."));
            Long result = (Long) session.createCriteria (CompanyAnnotation.class).
                    setProjection (Projections.rowCount ()).uniqueResult ();

            Assertions.assertNotNull (result, String.format ("There are not companies!!!"));
            Assertions.assertTrue (result > 0, String.format ("There are not companies!!!"));
            logger.info (String.format ("Companies found (%d)", result));

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
    @DisplayName ("Max Id Company")
    public void m2 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Getting max id company."));
            Integer result = (Integer) session.createCriteria (CompanyAnnotation.class).
                    setProjection (Projections.max ("id")).uniqueResult ();

            Assertions.assertNotNull (result, String.format ("There is not company!!!"));
            Assertions.assertTrue (result > 0, String.format ("There is not company!!!"));
            logger.info (String.format ("Id company found (%d)", result));

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
    @DisplayName ("Min Id Company")
    public void m3 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Getting min id company."));
            Integer result = (Integer) session.createCriteria (CompanyAnnotation.class).
                    setProjection (Projections.min ("id")).uniqueResult ();

            Assertions.assertNotNull (result, String.format ("There is not company!!!"));
            Assertions.assertTrue (result > 0, String.format ("There is not company!!!"));
            logger.info (String.format ("Id company found (%d)", result));

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
    @DisplayName ("County Different Companies")
    public void m4 () {

        // Get a session.
        Session session = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Counting companies."));
            Long result = (Long) session.createCriteria (CompanyAnnotation.class).
                    setProjection (Projections.countDistinct ("country")).uniqueResult ();

            Assertions.assertNotNull (result, String.format ("There are not companies!!!"));
            Assertions.assertTrue (result > 0, String.format ("There are not companies!!!"));
            logger.info (String.format ("Companies found (%d)", result));

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