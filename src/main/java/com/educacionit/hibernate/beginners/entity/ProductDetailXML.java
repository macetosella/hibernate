package com.educacionit.hibernate.beginners.entity;

import java.util.Date;


public class ProductDetailXML {
	private Long id;
    private Long tax;
    private Date in;
    private Date out;
    private String observation;
    private ProductAnnotation product;


    public ProductDetailXML () {
        super();
    }

    public ProductDetailXML (Long tax, Date in, String observation) {
        super();

        this.tax = tax;
        this.in  = in;
        this.observation = observation;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTax() {
		return tax;
	}

	public void setTax(Long tax) {
		this.tax = tax;
	}

	public Date getIn() {
		return in;
	}

	public void setIn(Date in) {
		this.in = in;
	}

	public Date getOut() {
		return out;
	}

	public void setOut(Date out) {
		this.out = out;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public ProductAnnotation getProduct() {
		return product;
	}

	public void setProduct(ProductAnnotation product) {
		this.product = product;
	}
    
    
}
