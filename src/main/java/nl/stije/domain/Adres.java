package nl.stije.domain;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adres_id;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;

    public Adres(int adresId, String pc, String hn, String straat, String woonplaats, Reiziger reiziger) {
        this.adres_id = adresId;
        this.postcode = pc;
        this.huisnummer = hn;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger = reiziger;
    }

    public Adres() {}

    public static void maakAdres(int adresId, String pc, String hn, String straat, String woonplaats, Reiziger reiziger) {
        Adres adres = new Adres(adresId, pc, hn, straat, woonplaats, reiziger);

        reiziger.setAdres(adres);
    }

    public int getAdres_id() {
        return adres_id;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
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
