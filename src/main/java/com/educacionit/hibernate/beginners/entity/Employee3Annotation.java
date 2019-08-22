
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;
import java.util.Date;


@Entity (name = "Employee3")
@Table (name="employee3")
@PrimaryKeyJoinColumn (name="per_person_id")
public class Employee3Annotation extends Person3Annotation {


    @Column (name="joining_date")
    private Date joiningDate;

    @Column (name="department_name")
    private String departmentName;


    public Employee3Annotation () {

        super ();
    }

    public Employee3Annotation (String firstName, String lastName, String departmentName,
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