package com.educacionit.hibernate.beginners.entity;

import javax.persistence.*;

@Entity (name = "Product")
@Table (name = "product")
public class ProductH2Annotation {


    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator (name="product_seq", sequenceName="generic_seq")
    @Column(name = "pro_id", unique = true, nullable = false)
    private Long id;

    @Column (name="pro_name", length = 48, nullable = false)
    private String name;

    @Column (name="pro_description", length = 200, nullable = false)
    private String description;

    @Column (name="pro_price", nullable = false)
    private Long price;

    @OneToOne (mappedBy="product", cascade=CascadeType.ALL)
    private ProductDetailH2Annotation detail;

    @ManyToOne
    @JoinColumn (name="prot_id")
    private ProductTypeH2Annotation type;


    public ProductH2Annotation () {

        super ();
    }

    public ProductH2Annotation (String name, String description, Long price) {

        super ();

        this.name = name;
        this.description = description;
        this.price  = price;
    }

    public ProductH2Annotation (String name, String description, Long price,
                              ProductTypeH2Annotation type) {

        super ();

        this.name = name;
        this.description = description;
        this.price = price;
        this.type  = type;
    }


    public Long getId () {

        return this.id;
    }

    public void setId (Long id) {

        this.id = id;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public Long getPrice () {

        return this.price;
    }

    public void setPrice (Long price) {

        this.price = price;
    }

    public ProductDetailH2Annotation getDetail () {

        return this.detail;
    }

    public void setDetail (ProductDetailH2Annotation detail) {

        this.detail = detail;
    }

    public ProductTypeH2Annotation getType () {

        return this.type;
    }

    public void setType (ProductTypeH2Annotation type) {

        this.type = type;
    }
}
