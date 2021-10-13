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
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "beschrijving")
    private String beschrijving;

    @Column(name = "prijs")
    private float prijs;

    @ManyToMany(mappedBy = "producten")
    private List<OVChipkaart> ovChipkaarten = new ArrayList<>();

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaart.addProduct(this);
    }

    public void removeOvChipkaart(OVChipkaart ovChipkaart)  {
        this.ovChipkaarten.remove(ovChipkaart);
    }

    public Product() {}

    public Product(String naam, String beschrijving, float prijs) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Long getProduct_nummer() {
        return id;
    }

    public void setProduct_nummer(Long product_nummer) {
        this.id = product_nummer;
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
