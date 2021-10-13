package nl.stije.hibernate;

import nl.stije.daointerfaces.ReizigerDAO;
import nl.stije.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private Session session;

    public ReizigerDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(reiziger);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(Long id) {
        try {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Reiziger R where R.id = :reiziger_id", Reiziger.class);
            query.setParameter("reiziger_id", id);
            transaction.commit();
            return (Reiziger) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            Transaction transaction = session.beginTransaction();
            List<Reiziger> reizigers = session.createQuery("from Reiziger", Reiziger.class).list();
            transaction.commit();
            return reizigers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
