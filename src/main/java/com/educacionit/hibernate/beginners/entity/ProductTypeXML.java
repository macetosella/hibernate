package com.educacionit.hibernate.beginners.entity;

import java.util.HashSet;
import java.util.Set;


public class ProductTypeXML {
	private Long id;	    
	private String name;
	private Set<ProductAnnotation> products = new HashSet<> ();
	
	public ProductTypeXML () {
        super();
    }

    public ProductTypeXML (String name) {
        super();

        this.name = name;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductAnnotation> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductAnnotation> products) {
		this.products = products;
	}
    
    
}
