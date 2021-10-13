package nl.stije;

import nl.stije.domain.Adres;
import nl.stije.domain.OVChipkaart;
import nl.stije.domain.Product;
import nl.stije.domain.Reiziger;
import nl.stije.hibernate.AdresDAOHibernate;
import nl.stije.hibernate.OVChipkaartDAOHibernate;
import nl.stije.hibernate.ProductDAOHibernate;
import nl.stije.hibernate.ReizigerDAOHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.sql.SQLException;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();

        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate() {
        Session session = getSession();

        OVChipkaartDAOHibernate odao = new OVChipkaartDAOHibernate(session);
        AdresDAOHibernate adao = new AdresDAOHibernate(session);
        ProductDAOHibernate pdao = new ProductDAOHibernate(session);

        ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(session);

        // maak een reiziger aan om te testen
        Reiziger reiziger = new Reiziger("P.", "", "Voren", (java.sql.Date.valueOf("2021-10-10")));
        // maak een adres en zet hem in reiziger
        Adres adres = new Adres("2932AB", "98", "Straatsingelhoflaan", "Voorburg", reiziger);
        reiziger.setAdres(adres);


        // --------------- REIZIGER CRUD TESTS ----------------
        System.out.println("--------------- TEST REIZIGERDAO ----------------");

        // save reiziger (saved tevens het bijhorende adres)
        System.out.print("[Test] rdao.save(reiziger). Voor save zijn er: " + rdao.findAll().size() + " reizigers, daarna: ");
        rdao.save(reiziger);
        System.out.println(rdao.findAll().size());

        // Voor verdere tests
        Reiziger testReiziger = new Reiziger("B.", "", "Kemper", java.sql.Date.valueOf("2021-11-10"));
        rdao.save(testReiziger);

        // update reiziger
        System.out.println("[Test] rdao.update(reiziger). Voor update: [" + rdao.findById(1L) + "]");
        reiziger.setAchternaam("Landers");
        rdao.update(reiziger);
        System.out.println("daarna: " + rdao.findById(1L));

        // delete reiziger
        System.out.print("[Test] rdao.delete(reiziger). Voor delete zijn er: " + rdao.findAll().size() + " reizigers, daarna: ");
        rdao.delete(reiziger);
        System.out.println(rdao.findAll().size());




        // --------------- ADRES CRUD TESTS ----------------
        System.out.println();
        System.out.println("--------------- TEST ADRESDAO ----------------");

        Adres adres2 = new Adres("3811AB", "44", "Saenredamstraat", "Utrecht", testReiziger);

        // save adres (saved tevens het bijhorende adres)
        System.out.print("[Test] adao.save(adres). Voor save zijn er: " + adao.findAll().size() + " adressen, daarna: ");
        adao.save(adres2);
        System.out.println(adao.findAll().size());

        // update adres
        System.out.println("[Test] adao.update(adres). Voor update: [" + adao.findByReiziger(testReiziger) + "]");
        adres2.setStraat("Engelhuislaan");
        adao.update(adres2);
        System.out.println("daarna: " + adao.findByReiziger(testReiziger));

        // delete adres
        System.out.print("[Test] adao.delete(adres). Voor delete zijn er: " + adao.findAll().size() + " adressen, daarna: ");
        adao.delete(adres2);
        System.out.println(adao.findAll().size());



        // --------------- OVCHIPKAART CRUD TESTS ----------------
        System.out.println();
        System.out.println("--------------- TEST OVCHIPKAARTDAO ----------------");

        System.out.println(testReiziger);

        OVChipkaart ovChipkaart = new OVChipkaart(java.sql.Date.valueOf("2030-05-10"), 2, 55.10f, testReiziger);

        // save ov-chipkaart
        System.out.print("[Test] odao.save(ovChipkaart). Voor save zijn er: " + odao.findByReiziger(testReiziger).size() + " ov-chipkaarten, daarna: ");
        odao.save(ovChipkaart);
        System.out.println(odao.findByReiziger(testReiziger).size());

        // update ov-chipkaart
        System.out.println("[Test] odao.update(ovChipkaart). Voor update: [" + odao.findByReiziger(testReiziger).get(0) + "]");
        ovChipkaart.setSaldo(1000.22f);
        odao.update(ovChipkaart);
        System.out.println("daarna: " + odao.findByReiziger(testReiziger).get(0));

        // delete ovChipkaart
        System.out.print("[Test] odao.delete(ovChipkaart). Voor delete zijn er: " + odao.findByReiziger(testReiziger).size() + " ovChipkaarten, daarna: ");
        odao.delete(ovChipkaart);
        System.out.println(odao.findByReiziger(testReiziger).size());



        // --------------- PRODUCT CRUD TESTS ----------------
        System.out.println();
        System.out.println("--------------- TEST PRODUCTDAO ----------------");

        Product product1 = new Product("Korting", "heel veel korting", 33.00f);
        Product product2 = new Product("gratis reis", "een dag lang!", 44.00f);

        ovChipkaart.addProduct(product1);
        ovChipkaart.addProduct(product2);

        // save product
        System.out.print("[Test] pdao.save(product). Voor save zijn er: " + pdao.findAll().size() + " producten, daarna: ");
        pdao.save(product1);
        System.out.println(pdao.findAll().size());

        // update product
        System.out.println("[Test] pdao.update(adres). Voor update: [" + pdao.findByOvChipkaart(ovChipkaart).get(0) + "]");
        product1.setBeschrijving("geen enkele korting :)");
        pdao.update(product1);
        System.out.println("daarna: " + pdao.findByOvChipkaart(ovChipkaart).get(0));

        // delete product
        System.out.print("[Test] pdao.delete(product). Voor delete zijn er: " + pdao.findAll().size() + " producten, daarna: ");
        pdao.delete(product1);
        System.out.println(pdao.findAll().size());
    }
}