package nl.stije.hibernate;

import nl.stije.daointerfaces.AdresDAO;
import nl.stije.domain.Adres;
import nl.stije.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(adres);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Query query = session.createQuery("from Adres A where A.reiziger.id = :reiziger_id");
        query.setParameter("reiziger_id", reiziger.getReiziger_id());

        return (Adres) query.getSingleResult();
    }

    @Override
    public List<Adres> findAll() {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<Adres> adressen = session.createQuery(" FROM Adres", Adres.class).getResultList();
            transaction.commit();
            return adressen;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
