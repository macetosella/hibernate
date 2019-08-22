
package com.educacionit.hibernate.beginners.test;


import com.educacionit.hibernate.beginners.entity.ProductH2Annotation;
import com.educacionit.hibernate.beginners.entity.ProductDetailH2Annotation;
import com.educacionit.hibernate.beginners.entity.ProductTypeH2Annotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;


public class HibernateRelationshipAnnotationProductH2Test {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateRelationshipAnnotationProductH2Test.class);


    public HibernateRelationshipAnnotationProductH2Test() {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionAnnotationFactoryH2();
    }

    public static void destroy () {

        sessionFactory.close ();
    }


    @Test
    @DisplayName ("Create new product type")
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
            ProductTypeH2Annotation pt = new ProductTypeH2Annotation ("Technology");

            // Save the data.
            logger.info (String.format ("Saving value %s", pt.getName ()));
            session.save (pt);
            logger.info (String.format ("Value %s saved!", pt.getName ()));

            tx.commit ();
            Assertions.assertTrue (pt.getId () > 0, String.format ("Problems creating the new product type %s", pt.getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Create new products")
    public void m2 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info("Getting a session...");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeH2Annotation pt = (ProductTypeH2Annotation)session.createQuery ("from ProductType pt where pt.name = 'Technology'").uniqueResult ();
            Assertions.assertNotNull (pt, "There is not product type by name \"Technology\"");

            logger.info ("Creating values to insert...");
            ProductH2Annotation        p1 = new ProductH2Annotation ("Apple TV", "Streaming device", Long.valueOf (100));
            ProductDetailH2Annotation pd1 = new ProductDetailH2Annotation (Long.valueOf (10), new Date (), "Only EEUU Enabled");
            p1.setDetail (pd1);
            p1.setType (pt);
            pd1.setProduct (p1);

            ProductH2Annotation        p2 = new ProductH2Annotation ("Google Chrome Cast", "Streaming device", Long.valueOf (30));
            ProductDetailH2Annotation pd2 = new ProductDetailH2Annotation (Long.valueOf (4), new Date (), "Only EEUU Enabled");
            p2.setDetail (pd2);
            p2.setType (pt);
            pd2.setProduct (p2);

            ProductH2Annotation        p3 = new ProductH2Annotation ("Amazon Echo", "Streaming device", Long.valueOf (199));
            ProductDetailH2Annotation pd3 = new ProductDetailH2Annotation (Long.valueOf (6), new Date (), "Only EEUU Enabled");
            p3.setDetail (pd3);
            p3.setType (pt);
            pd3.setProduct (p3);

            ProductH2Annotation[] values = new ProductH2Annotation[] { p1, p2, p3};

            // Save the data.
            for (ProductH2Annotation e : values) {

                logger.info (String.format ("Saving value %s", e.getName ()));
                session.save(e);
                logger.info (String.format ("Value %s saved!", e.getName ()));
            }
            tx.commit ();
            Assertions.assertTrue (values[0].getId () > 0, String.format ("Problems creating the new product %s", values[0].getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());
            tx.rollback ();
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Finding all products with price and tax")
    public void m3 () {

        Session session = null;
        List<ProductH2Annotation> products;

        try {

            logger.info ("Executing select all products in H2.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            products = (List)session.createQuery ("from Product").list ();

            Assertions.assertFalse (products.isEmpty (), "There are not products found!!!");

            logger.info ("Print all products info.");
            products.forEach ( e -> logger.info (String.format ("Product %s price %d, tax %d", e.getName (), e.getPrice (), e.getDetail ().getTax ())) );

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Finding all products with type")
    public void m4 () {

        Session session = null;
        List<ProductH2Annotation> products;

        try {

            logger.info ("Executing select all products in H2.");
            logger.info("Getting a session...");
            session = sessionFactory.openSession ();

            products = (List)session.createQuery ("from Product").list ();

            Assertions.assertFalse (products.isEmpty (), "There are not products found!!!");

            logger.info ("Print all products info.");
            products.forEach ( e -> logger.info (String.format ("Product %s Type ==> %s",
                    e.getName (), e.getType ().getName ()))
            );

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close(); }
    }

    @Test
    @DisplayName ("Delete all products")
    public void m5 () {

        final Session session;
        Transaction tx;
        List<ProductH2Annotation> values;

        try {

            logger.debug ("Delete all products from H2.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From Product").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not products found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From Product").list ();
            Assertions.assertTrue (values.isEmpty (), "There are products found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }

    @Test
    @DisplayName ("Delete all products type")
    public void m6 () {

        final Session session;
        Transaction tx;
        List<ProductTypeH2Annotation> values;

        try {

            logger.debug ("Delete all products type in H2.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createQuery ("From ProductType").list ();

            Assertions.assertFalse (values.isEmpty (), "There are not products type found!!!");

            values.forEach (e -> session.delete (e));
            tx.commit ();

            values = (List)session.createQuery("From ProductType").list ();
            Assertions.assertTrue (values.isEmpty (), "There are products type found!!!");

        } catch (Exception e) {

            logger.error (e.getMessage ());
            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");
        }
    }
}