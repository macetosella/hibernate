
package com.educacionit.hibernate.beginners.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity (name = "ProductType")
@Table (name = "product_type")
public class ProductTypeAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "product_type_seq")
    @SequenceGenerator (name = "product_type_seq", sequenceName = "product_type_seq")
    @Column(name = "prot_id")
    private Long id;

    @Column (name="prot_name", length = 48, nullable = false)
    private String name;

    @OneToMany (mappedBy = "type")
    private Set<ProductAnnotation> products = new HashSet<> ();


    public ProductTypeAnnotation () {

        super ();
    }

    public ProductTypeAnnotation (String name) {

        super ();

        this.name = name;
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

    public Set<ProductAnnotation> getProducts () {

        return this.products;
    }

    public void setProducts (Set<ProductAnnotation> products) {

        this.products = products;
    }
}