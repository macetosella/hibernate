
package com.educacionit.hibernate.beginners.test;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.entity.CompanyAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class SearchListHibernateAnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (SearchListHibernateAnnotationTest.class);


    public SearchListHibernateAnnotationTest() {

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
    @DisplayName ("Finding companies by country (Using ILike)")
    public void m1 () {

        // Get a session.
        Session session = null;
        final String filter = "aRgenTIna";
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding companies by country %s using criteria object.", filter));
            List<CompanyAnnotation> list = (List<CompanyAnnotation>) session.createCriteria (CompanyAnnotation.class).
                    add (Restrictions.ilike ("country", filter)).list ();
            logger.info (String.format ("Company list by country %s using criteria object executed!", filter));

            Assertions.assertFalse (list.isEmpty (),
                    String.format ("Company list by name %s using criteria object are empty!", filter));

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
    @DisplayName ("Finding companies by country (Using Like)")
    public void m2 () {

        // Get a session.
        Session session = null;
        final String filter = "Venezuela";
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding companies by country %s using criteria object.", filter));
            List<CompanyAnnotation> list = (List<CompanyAnnotation>) session.createCriteria (CompanyAnnotation.class).
                    add (Restrictions.like ("country", filter)).list ();
            logger.info (String.format ("Company list by country %s using criteria object executed!", filter));

            Assertions.assertFalse (list.isEmpty (),
                    String.format ("Company list by country %s using criteria object is empty!", filter));

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
    @DisplayName ("Finding companies by country order by name desc (Using Like with wildcard)")
    public void m3 () {

        // Get a session.
        Session session = null;
        final String filter = "EE%";
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding companies by country %s using criteria object.", filter));
            List<CompanyAnnotation> list = (List<CompanyAnnotation>) session.createCriteria (CompanyAnnotation.class).
                    add (Restrictions.like ("country", filter)).
                    addOrder (Order.desc ("name")).list ();
            logger.info (String.format ("Company list by country %s using criteria object executed!", filter));

            Assertions.assertFalse (list.isEmpty (),
                    String.format ("Company list by country %s using criteria object are empty!", filter));

            logger.info ("Print all companies info.");
            list.forEach ( e -> logger.info (e.getName ()));

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
    @DisplayName ("Finding companies by country (Using Or)")
    public void m4 () {

        // Get a session.
        Session session = null;
        final String filter1 = "EEUU";
        final String filter2 = "Argentina";
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding companies by countries [%s, %s] using criteria object.", filter1, filter2));
            Criterion criterion1 = Restrictions.like ("country", filter1);
            Criterion criterion2 = Restrictions.like ("country", filter2);
            LogicalExpression orExp = Restrictions.or (criterion1, criterion2);

            List<CompanyAnnotation> list = (List<CompanyAnnotation>) session.createCriteria (CompanyAnnotation.class).
                    add (orExp).list ();
            logger.info (String.format ("Companies by countries [%s, %s] using criteria object executed!", filter1, filter2));

            Assertions.assertFalse (list.isEmpty (), String.format ("Companies by countries [%s, %s] using criteria object are empty!!!", filter1, filter2));

            logger.info ("Print all companies info.");
            list.forEach ( e -> logger.info (e.getName ()));

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
    @DisplayName ("Finding companies by country (Using And)")
    public void m5 () {

        // Get a session.
        Session session = null;
        final int    filter1 = 1;
        final String filter2 = "Argentina";
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info (String.format ("Finding companies by id and country [%d, %s] using criteria object.", filter1, filter2));
            Criterion criterion1 = Restrictions.gt ("id", filter1);
            Criterion criterion2 = Restrictions.like ("country", filter2);
            LogicalExpression andExp = Restrictions.and (criterion1, criterion2);

            List<CompanyAnnotation> list = (List<CompanyAnnotation>) session.createCriteria (CompanyAnnotation.class).
                    add (andExp).list ();
            logger.info (String.format ("Companies by id and country [%d, %s] using criteria object executed!", filter1, filter2));

            Assertions.assertFalse (list.isEmpty (), String.format ("Companies by id and country [%d, %s] using criteria object are empty!!!", filter1, filter2));

            logger.info ("Print all companies info.");
            list.forEach ( e -> logger.info (e.getName ()));

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