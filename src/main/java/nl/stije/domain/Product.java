package nl.stije.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {
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

    @ManyToMany(mappedBy = "producten", cascade = {CascadeType.ALL})
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

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

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaart.addProduct(this);
    }

    public void removeOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaarten.remove(ovChipkaart);
    }

    @Override
    public String toString() {
        return "Naam: " + naam + ", beschrijving: " + beschrijving + ", prijs: " + prijs;
    }
}
