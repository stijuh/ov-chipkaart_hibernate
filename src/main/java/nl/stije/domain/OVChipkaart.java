package nl.stije.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "OVChipkaart")
@Table(name = "ov_chipkaart")
public class OVChipkaart implements Serializable {

    @Id
    @Column(name = "kaart_nummer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date geldig_tot;
    private int klasse;
    private float saldo;

    @Column(name = "reiziger_id", insertable = false, updatable = false)
    private Long reiziger_id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reiziger_id", foreignKey = @ForeignKey(name = "REIZIGER_ID_FK"))
    private Reiziger reiziger;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "ov_chipkaart_product", joinColumns = {@JoinColumn(name = "kaart_nummer")}, inverseJoinColumns = { @JoinColumn(name = "product_nummer")})
    private List<Product> producten = new ArrayList<>();

    public void addProduct(Product product) {
        producten.add(product);
        product.getOvChipkaarten().add(this);
    }

    public void removeProduct(Product product) {
        producten.remove(product);
        product.getOvChipkaarten().remove(this);
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducts(List<Product> producten) {
        this.producten = producten;
    }

    public OVChipkaart(Date geldig_tot, int klasse, float saldo, Reiziger reiziger) {
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger.getReiziger_id();
        this.reiziger = reiziger;
    }

    public OVChipkaart() {}

    public Long getKaart_nummer() {
        return id;
    }

    public void setKaart_nummer(Long kaart_nummer) {
        this.id = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Long getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(Long reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "geldig_tot: " + geldig_tot + ", klasse: " + klasse + ", saldo: " + saldo + ", aantal producten: " + producten.size();
    }
}
