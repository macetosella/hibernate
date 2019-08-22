package com.educacionit.hibernate.beginners.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity (name = "ProductDetail")
@Table (name = "product_detail")
public class ProductDetailH2Annotation {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="product"))
    @Column(name = "pro_id", unique = true, nullable = false)
    private Long id;

    @Column (name="prod_tax", nullable = false)
    private Long tax;

    @Column (name="prod_in", nullable = false)
    private Date in;

    @Column (name="prod_out")
    private Date out;

    @Column (name="prod_observation", length = 200, nullable = false)
    private String observation;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ProductH2Annotation product;


    public ProductDetailH2Annotation () {

        super ();
    }

    public ProductDetailH2Annotation (Long tax, Date in, String observation) {

        super ();

        this.tax = tax;
        this.in  = in;
        this.observation = observation;
    }


    public Long getId () {

        return this.id;
    }

    public void setId (Long id) {

        this.id = id;
    }


    public Long getTax () {

        return this.tax;
    }

    public void setTax (Long tax) {

        this.tax = tax;
    }

    public Date getIn () {

        return this.in;
    }

    public void setIn (Date in) {

        this.in = in;
    }

    public Date getOut () {

        return this.out;
    }

    public void setOut (Date out) {

        this.out = out;
    }

    public String getObservation () {

        return this.observation;
    }

    public void setObservation (String observation) {

        this.observation = observation;
    }

    public ProductH2Annotation getProduct () {

        return this.product;
    }

    public void setProduct (ProductH2Annotation product) {

        this.product = product;
    }
}
