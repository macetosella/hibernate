
package com.educacionit.hibernate.beginners.entity;


import java.util.Date;


public class EmployeeXML {

	private int id;
	private String name;
	private String role;
	private Date insertTime;


	public EmployeeXML () {

	    super ();
    }

    public EmployeeXML (String name, String role, Date insertTime) {

	    super ();

        this.id = id;
        this.name = name;
        this.role = role;
        this.insertTime = insertTime;
    }


    public int getId () {

		return this.id;
	}

	public void setId (int id) {

		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName (String name) {

		this.name = name;
	}

	public String getRole () {

		return this.role;
	}

	public void setRole (String role) {

		this.role = role;
	}

	public Date getInsertTime () {

		return this.insertTime;
	}

	public void setInsertTime (Date insertTime) {

		this.insertTime = insertTime;
	}
}