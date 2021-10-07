package nl.stije.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "product_nummer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_nummer;

    @Column(name = "naam")
    private String naam;

    @Column(name = "beschrijving")
    private String beschrijving;

    @Column(name = "prijs")
    private float prijs;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<OVChipkaartProduct> ovChipkaarts = new ArrayList<>();

    public List<OVChipkaartProduct> getOvChipkaarts() {
        return ovChipkaarts;
    }

    public Product() {}

    public Product(int product_nummer, String naam, String beschrijving, float prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public float getPrijs() {
        return prijs;
    }

    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Naam: " + naam + ", beschrijving: " + beschrijving + ", prijs: " + prijs;
    }
}
