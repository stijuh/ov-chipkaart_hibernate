package nl.stije.daointerfaces;

import nl.stije.domain.OVChipkaart;
import nl.stije.domain.Product;

import java.util.List;

public interface ProductDAO {
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(Product product);
    List<Product> findAll();
    List<Product> findByOvChipkaart(OVChipkaart ovChipkaart);
}
