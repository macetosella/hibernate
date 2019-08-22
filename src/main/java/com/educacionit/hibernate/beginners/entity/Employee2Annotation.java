
package com.educacionit.hibernate.beginners.entity;


import java.util.Date;

import javax.persistence.*;


@Entity (name = "Employee2")
@Table (name="employee")
@AttributeOverrides ({
        @AttributeOverride (name = "firstName", column = @Column (name = "per_first_name")),
        @AttributeOverride (name = "lastName",  column = @Column (name = "per_last_name"))
})
public class Employee2Annotation extends Person2Annotation {


    @Column (name="joining_date")
    private Date joiningDate;

    @Column (name="department_name")
    private String departmentName;


    public Employee2Annotation () {

        super ();
    }

    public Employee2Annotation (String firstName, String lastName, String departmentName,
                                Date joiningDate) {

        super (firstName, lastName);

        this.departmentName = departmentName;
        this.joiningDate    = joiningDate;
    }


    public Date getJoiningDate () {

        return this.joiningDate;
    }

    public void setJoiningDate (Date joiningDate) {

        this.joiningDate = joiningDate;
    }

    public String getDepartmentName () {

        return this.departmentName;
    }

    public void setDepartmentName (String departmentName) {

        this.departmentName = departmentName;
    }
}