package nl.stije.domain;

import javax.persistence.*;

@Entity
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne()
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public Adres(String pc, String hn, String straat, String woonplaats, Reiziger reiziger) {
        this.postcode = pc;
        this.huisnummer = hn;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;
    }

    public Adres() {}

    public static Adres maakAdres(String pc, String hn, String straat, String woonplaats, Reiziger reiziger) {
        Adres adres = new Adres(pc, hn, straat, woonplaats, reiziger);

        reiziger.setAdres(adres);

        return adres;
    }

    public Long getAdres_id() {
        return id;
    }

    public void setAdres_id(Long adres_id) {
        this.id = adres_id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public String toString() {
        return "Op " + straat + " " + huisnummer + " " + postcode;
    }
}
