
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@Entity (name =  "Company")
@Table (name = "company")
public class CompanyAnnotation {


	@Id
	@GeneratedValue (strategy = GenerationType.AUTO, generator = "company_seq")
	@SequenceGenerator (name="company_seq", sequenceName="company_seq")
	@Column (name="com_id", nullable=false, unique=true)
	private int id;

	@Column(name="com_name", length=56, nullable=false)
	private String name;

	@Column(name="com_country", length=56, nullable=false)
	private String country;

	@Column(name="com_email", length=128, nullable=false)
	private String email;


    public CompanyAnnotation () {

        super ();
    }

    public CompanyAnnotation (String name, String country, String email) {

        super ();

        this.name = name;
        this.country = country;
        this.email = email;
    }


	public int getId () {

		return this.id;
	}

	public void setId (int id) {

		this.id = id;
	}

	public String getName () {

		return this.name;
	}

	public void setName (String name) {

	    this.name = name;
	}

	public String getCountry () {

		return this.country;
	}

	public void setCountry (String country) {

	    this.country = country;
	}

	public String getEmail () {

		return this.email;
	}

	public void setEmail (String email) {

		this.email = email;
	}
}