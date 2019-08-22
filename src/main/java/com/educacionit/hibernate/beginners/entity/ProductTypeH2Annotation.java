package com.educacionit.hibernate.beginners.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ProductType")
@Table(name = "product_type")
public class ProductTypeH2Annotation {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "product_type_seq")
    @SequenceGenerator (name = "product_type_seq", sequenceName = "generic_seq")
    @Column(name = "prot_id")
    private Long id;

    @Column (name="prot_name", length = 48, nullable = false)
    private String name;

    @OneToMany (mappedBy = "type")
    private Set<ProductH2Annotation> products = new HashSet<>();


    public ProductTypeH2Annotation () {

        super ();
    }

    public ProductTypeH2Annotation (String name) {

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

    public Set<ProductH2Annotation> getProducts () {

        return this.products;
    }

    public void setProducts (Set<ProductH2Annotation> products) {

        this.products = products;
    }
}
