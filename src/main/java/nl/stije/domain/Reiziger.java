package nl.stije.domain;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
@Transactional
public class Reiziger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    @OneToOne(mappedBy = "reiziger", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "reiziger_id")
    private Adres adres;

    @OneToMany(mappedBy = "reiziger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OVChipkaart> OVChipkaarten = new ArrayList<OVChipkaart>();

    public Reiziger(String voorletters, String tussenvoegsel, String achternaam, Date geboortedatum) {
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public Reiziger() {}

    public Long getReiziger_id() {
        return id;
    }

    public void setReiziger_id(Long reiziger_id) {
        this.id = reiziger_id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public Adres getAdres() {
        if (this.adres != null) {
            adres.setReiziger(this);
            return adres;
        }
        return null;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOVChipkaarten() {
        return OVChipkaarten;
    }

    public void setOVChipkaarten(List<OVChipkaart> OVChipkaarten) {
        this.OVChipkaarten = (ArrayList<OVChipkaart>) OVChipkaarten;
    }

    public void addOVChipkaart(OVChipkaart ovChipkaart) {
        if (!OVChipkaarten.contains(ovChipkaart)) {
            this.OVChipkaarten.add(ovChipkaart);
        }
    }

    public String toString() {
        String str = voorletters + " " + tussenvoegsel + achternaam + ", geb. " + geboortedatum;

        if (this.adres != null) {
            str += ", Adres: " + adres.toString();
        }

        return str;
    }
}
