package nl.stije.daointerfaces;

import nl.stije.domain.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDAO {
    boolean save(Reiziger reiziger) throws SQLException;
    boolean update(Reiziger reiziger);
    boolean delete(Reiziger reiziger);
    Reiziger findById(Long id);
    List<Reiziger> findAll();
}
