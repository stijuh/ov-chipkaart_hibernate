package nl.stije.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "OVChipkaartProducten")
@Table(name = "ov_chipkaart_product")
public class OVChipkaartProduct implements Serializable {
    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "kaart_nummer", foreignKey = @ForeignKey(name = "OVCHIPKAART_ID_FK"))
    private OVChipkaart ovChipkaart;

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_nummer", foreignKey = @ForeignKey(name = "PRODUCT_ID_FK"))
    private Product product;

    public OVChipkaartProduct() {}

    public OVChipkaartProduct(OVChipkaart ovChipkaart, Product product) {
        this.ovChipkaart = ovChipkaart;
        this.product = product;
    }

    public OVChipkaart getOvChipkaart() {
        return ovChipkaart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaart = ovChipkaart;
    }
}
