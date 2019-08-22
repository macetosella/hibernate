
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@Entity (name = "Product")
@Table (name = "product")
public class ProductAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "product_seq")
    @SequenceGenerator (name="product_seq", sequenceName="product_seq")
    @Column(name = "pro_id", unique = true, nullable = false)
    private Long id;

    @Column (name="pro_name", length = 48, nullable = false)
    private String name;

    @Column (name="pro_description", length = 200, nullable = false)
    private String description;

    @Column (name="pro_price", nullable = false)
    private Long price;

    @OneToOne (mappedBy="product", cascade=CascadeType.ALL)
    private ProductDetailAnnotation detail;

    @ManyToOne
    @JoinColumn (name="prot_id")
    private ProductTypeAnnotation type;


    public ProductAnnotation () {

        super ();
    }

    public ProductAnnotation (String name, String description, Long price) {

        super ();

        this.name = name;
        this.description = description;
        this.price  = price;
    }

    public ProductAnnotation (String name, String description, Long price,
                              ProductTypeAnnotation type) {

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

    public ProductDetailAnnotation getDetail () {

        return this.detail;
    }

    public void setDetail (ProductDetailAnnotation detail) {

        this.detail = detail;
    }

    public ProductTypeAnnotation getType () {

        return this.type;
    }

    public void setType (ProductTypeAnnotation type) {

        this.type = type;
    }
}