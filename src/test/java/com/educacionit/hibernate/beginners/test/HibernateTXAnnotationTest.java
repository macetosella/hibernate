
package com.educacionit.hibernate.beginners.test;


import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.*;

import com.educacionit.hibernate.beginners.entity.ProductAnnotation;
import com.educacionit.hibernate.beginners.entity.ProductDetailAnnotation;
import com.educacionit.hibernate.beginners.entity.ProductTypeAnnotation;
import com.educacionit.hibernate.beginners.util.HibernateUtil;


public class HibernateTXAnnotationTest {


    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger (HibernateTXAnnotationTest.class);


    public HibernateTXAnnotationTest () {

        super ();
    }


    @BeforeAll
    public static void setup () {

        sessionFactory = HibernateUtil.getSessionAnnotationFactory ();
    }

    //@AfterAll
    public static void destroy () {

        final Session session;
        Transaction tx;
        List<ProductTypeAnnotation> values;

        try {

            logger.debug ("Delete all products type.");
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            values = (List)session.createCriteria (ProductTypeAnnotation.class).list ();

            values.forEach (e -> session.delete (e));
            tx.commit ();

        } catch (Exception e) {

            logger.error (e.getMessage ());
        }

        // Close the session factory.
        sessionFactory.close ();
    }

    @Test
    @DisplayName ("Create new product type [OK]")
    public void m1 () {


        // Get a session.
        Session session = null;
        Transaction  tx = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Starting new transaction...");
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Creating values to insert...");
            ProductTypeAnnotation pt1 = new ProductTypeAnnotation ("Technology");
            ProductTypeAnnotation pt2 = new ProductTypeAnnotation ("Games");
            ProductTypeAnnotation pt3 = new ProductTypeAnnotation ("Food");

            // Save the data.
            logger.info (String.format ("Saving value %s", pt1.getName ()));
            session.save (pt1);
            logger.info (String.format ("Value %s saved!", pt1.getName ()));

            logger.info (String.format ("Saving value %s", pt2.getName ()));
            session.save (pt2);
            logger.info (String.format ("Value %s saved!", pt2.getName ()));

            logger.info (String.format ("Saving value %s", pt3.getName ()));
            session.save (pt3);
            logger.info (String.format ("Value %s saved!", pt3.getName ()));

            logger.info ("Executing commit...");
            tx.commit ();

            Assertions.assertTrue (pt1.getId () > 0, String.format ("Problems creating the new product type %s", pt1.getName ()));
            Assertions.assertTrue (pt2.getId () > 0, String.format ("Problems creating the new product type %s", pt2.getName ()));
            Assertions.assertTrue (pt3.getId () > 0, String.format ("Problems creating the new product type %s", pt3.getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());

            logger.info ("Executing rollback...");
            tx.rollback ();

            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Create new product type [KO]")
    public void m2 () {


        // Get a session.
        Session session = null;
        Transaction  tx = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Starting new transaction...");
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Creating values to insert...");
            ProductTypeAnnotation pt1 = new ProductTypeAnnotation ("Technology");
            ProductTypeAnnotation pt2 = new ProductTypeAnnotation ("Games");
            ProductTypeAnnotation pt3 = new ProductTypeAnnotation ("Food");

            // Save the data.
            logger.info (String.format ("Saving value %s", pt1.getName ()));
            session.save (pt1);
            logger.info (String.format ("Value %s saved!", pt1.getName ()));

            logger.info (String.format ("Saving value %s", pt2.getName ()));
            session.save (pt2);
            logger.info (String.format ("Value %s saved!", pt2.getName ()));

            if (!pt2.getName().equals ("")) {

                throw new HibernateException ("Ohhh !!! I need cancel these operations...");
            }

            logger.info (String.format ("Saving value %s", pt3.getName ()));
            session.save (pt3);
            logger.info (String.format ("Value %s saved!", pt3.getName ()));

            logger.info ("Executing commit...");
            tx.commit ();

            Assertions.assertTrue (pt1.getId () > 0, String.format ("Problems creating the new product type %s", pt1.getName ()));
            Assertions.assertTrue (pt2.getId () > 0, String.format ("Problems creating the new product type %s", pt2.getName ()));
            Assertions.assertTrue (pt3.getId () > 0, String.format ("Problems creating the new product type %s", pt3.getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());

            if (tx.isActive ()) {

                logger.info ("Executing rollback...");
                tx.rollback ();
            }

            Assertions.assertNotNull (ex, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Create new products [OK], testing without Flush")
    public void m3 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Starting new transaction...");
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeAnnotation pt1 = (ProductTypeAnnotation)session.createQuery ("from ProductType pt where pt.name = 'Technology'").uniqueResult ();
            Assertions.assertNotNull (pt1, "There is not product type by name \"Technology\"");

            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeAnnotation pt2 = (ProductTypeAnnotation)session.createQuery ("from ProductType pt where pt.name = 'Games'").uniqueResult ();
            Assertions.assertNotNull (pt2, "There is not product type by name \"Games\"");

            logger.info ("Creating values to insert...");
            ProductAnnotation        p1 = new ProductAnnotation ("Apple TV", "Streaming device", Long.valueOf (100));
            ProductDetailAnnotation pd1 = new ProductDetailAnnotation (Long.valueOf (10), new Date (), "Only EEUU Enabled");
            p1.setDetail (pd1);
            p1.setType (pt1);
            pd1.setProduct (p1);

            ProductAnnotation        p2 = new ProductAnnotation ("Google Chrome Cast", "Streaming device", Long.valueOf (30));
            ProductDetailAnnotation pd2 = new ProductDetailAnnotation (Long.valueOf (4), new Date (), "Only EEUU Enabled");
            p2.setDetail (pd2);
            p2.setType (pt2);
            pd2.setProduct (p2);

            ProductAnnotation[] values = new ProductAnnotation[] { p1, p2 };

            // Save the data.
            for (ProductAnnotation e : values) {

                logger.info (String.format ("Saving value %s", e.getName ()));
                session.save(e);
                logger.info (String.format ("Value %s saved!", e.getName ()));
            }

            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeAnnotation pt3 = (ProductTypeAnnotation)session.createQuery ("from ProductType pt where pt.name = 'Technology'").uniqueResult ();
            Assertions.assertNotNull (pt3, "There is not product type by name \"Technology\"");
            Assertions.assertTrue (pt3.getProducts().isEmpty (), "There are items in product type by name \"Technology\"");

            // Execute the commit
            logger.info ("Executing commit...");
            tx.commit ();

            Assertions.assertTrue (p1.getId () > 0, String.format ("Problems creating the new product %s", p1.getName ()));
            Assertions.assertTrue (p2.getId () > 0, String.format ("Problems creating the new product %s", p2.getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());

            if (tx.isActive ()) {

                logger.info ("Executing rollback...");
                tx.rollback ();
            }

            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }

    @Test
    @DisplayName ("Create new products [OK], testing with Flush")
    public void m4 () {


        // Get a session.
        Session session = null;
        Transaction tx = null;
        try {

            logger.info ("Getting a session...");
            session = sessionFactory.openSession ();

            logger.info ("Starting new transaction...");
            tx = session.beginTransaction ();

            // Set the data to save.
            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeAnnotation pt1 = (ProductTypeAnnotation)session.createQuery ("from ProductType pt where pt.name = 'Technology'").uniqueResult ();
            Assertions.assertNotNull (pt1, "There is not product type by name \"Technology\"");

            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeAnnotation pt2 = (ProductTypeAnnotation)session.createQuery ("from ProductType pt where pt.name = 'Games'").uniqueResult ();
            Assertions.assertNotNull (pt2, "There is not product type by name \"Games\"");

            logger.info ("Creating values to insert...");
            ProductAnnotation        p1 = new ProductAnnotation ("Apple TV", "Streaming device", Long.valueOf (100));
            ProductDetailAnnotation pd1 = new ProductDetailAnnotation (Long.valueOf (10), new Date (), "Only EEUU Enabled");
            p1.setDetail (pd1);
            p1.setType (pt1);
            pd1.setProduct (p1);

            ProductAnnotation        p2 = new ProductAnnotation ("Google Chrome Cast", "Streaming device", Long.valueOf (30));
            ProductDetailAnnotation pd2 = new ProductDetailAnnotation (Long.valueOf (4), new Date (), "Only EEUU Enabled");
            p2.setDetail (pd2);
            p2.setType (pt2);
            pd2.setProduct (p2);

            ProductAnnotation[] values = new ProductAnnotation[] { p1, p2 };

            // Save the data.
            for (ProductAnnotation e : values) {

                logger.info (String.format ("Saving value %s", e.getName ()));
                session.save(e);
                logger.info (String.format ("Value %s saved!", e.getName ()));
            }

            logger.info ("Executing Flush...");
            session.flush ();

            logger.info ("Searching product type by name \"Technology\"");
            ProductTypeAnnotation pt3 = (ProductTypeAnnotation)session.createQuery ("from ProductType pt where pt.name = 'Technology'").uniqueResult ();
            Assertions.assertNotNull (pt3, "There is not product type by name \"Technology\"");
            Assertions.assertFalse (pt3.getProducts().isEmpty (), "There are not items in product type by name \"Technology\"");

            // Execute the commit
            logger.info ("Executing commit...");
            tx.commit ();

            Assertions.assertTrue (p1.getId () > 0, String.format ("Problems creating the new product %s", p1.getName ()));
            Assertions.assertTrue (p2.getId () > 0, String.format ("Problems creating the new product %s", p2.getName ()));

        } catch (Exception ex) {

            logger.error (ex.getMessage ());

            if (tx.isActive ()) {

                logger.info ("Executing rollback...");
                tx.rollback ();
            }

            Assertions.assertFalse (Boolean.TRUE, "Problems executing the test.");

        } finally { session.close (); }
    }
}