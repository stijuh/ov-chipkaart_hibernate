package nl.stije.hibernate;

import nl.stije.daointerfaces.ProductDAO;
import nl.stije.domain.OVChipkaart;
import nl.stije.domain.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findByOvChipkaart(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = session.beginTransaction();
            List<Product> producten = session.createQuery("from Product", Product.class).getResultList();

            List<Product> productenInOVKaart = new ArrayList<>();
            for (Product product : producten) {
                if (product.getOvChipkaarten().contains(ovChipkaart)) {
                    productenInOVKaart.add(product);
                }
            }

            transaction.commit();
            return productenInOVKaart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            Transaction transaction = session.beginTransaction();
            List<Product> producten = session.createQuery("from Product", Product.class).getResultList();
            transaction.commit();
            return producten;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
