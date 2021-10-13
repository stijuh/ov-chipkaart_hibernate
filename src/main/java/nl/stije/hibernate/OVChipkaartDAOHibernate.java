package nl.stije.hibernate;

import nl.stije.daointerfaces.OVChipkaartDAO;
import nl.stije.domain.OVChipkaart;
import nl.stije.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private Session session;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(ovChipkaart);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            Transaction transaction = session.beginTransaction();
            List<OVChipkaart> ovChipkaarten = session.createQuery("from OVChipkaart where reiziger_id = " + reiziger.getReiziger_id(), OVChipkaart.class).getResultList();
            transaction.commit();
            return ovChipkaarten;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
