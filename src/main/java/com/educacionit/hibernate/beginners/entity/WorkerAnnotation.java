
package com.educacionit.hibernate.beginners.entity;


import java.util.Date;

import javax.persistence.*;


@Entity (name="Worker")
@Table (name = "entity")
@DiscriminatorValue ("W")
public class WorkerAnnotation extends PersonAnnotation {


    @Column (name="ENT_JOINING_DATE")
    private Date joiningDate;

    @Column (name="ENT_DEPARTMENT_NAME")
    private String departmentName;


    public WorkerAnnotation () {

        super ();
    }

    public WorkerAnnotation (String firstname, String lastname, Date joiningDate,
                             String departmentName) {

        super (firstname, lastname);

        this.joiningDate    = joiningDate;
        this.departmentName = departmentName;
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