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
    private int kaart_nummer;
    private Date geldig_tot;
    private int klasse;
    private float saldo;

    @Column(name = "reiziger_id", insertable = false, updatable = false)
    private int reiziger_id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reiziger_id", foreignKey = @ForeignKey(name = "REIZIGER_ID_FK"))
    private Reiziger reiziger;

    @OneToMany(mappedBy = "ovChipkaart", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<OVChipkaartProduct> products = new ArrayList<>();

    public void addProduct(Product product) {
        OVChipkaartProduct ovChipkaartProduct = new OVChipkaartProduct(this, product);
        products.add(ovChipkaartProduct);
        product.getOvChipkaarts().add(ovChipkaartProduct);
    }

    public void removeProduct(Product product) {
        OVChipkaartProduct ovChipkaartProduct = new OVChipkaartProduct(this, product);
        product.getOvChipkaarts().remove(ovChipkaartProduct);
        products.remove(ovChipkaartProduct);
        ovChipkaartProduct.setProduct(null);
        ovChipkaartProduct.setOvChipkaart(null);
    }

    public List<OVChipkaartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OVChipkaartProduct> products) {
        this.products = products;
    }

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, float saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger.getReiziger_id();
        this.reiziger = reiziger;
    }

    public OVChipkaart() {}

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
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

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
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
        return "geldig_tot: " + geldig_tot + ", klasse: " + klasse + ", saldo: " + saldo + ", aantal producten: " + products.size();
    }
}
