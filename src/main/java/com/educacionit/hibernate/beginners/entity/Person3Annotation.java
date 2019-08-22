
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@Entity (name = "Person3")
@Table (name = "person3")
@Inheritance (strategy=InheritanceType.JOINED)
public class Person3Annotation {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "sq_person3")
    @SequenceGenerator (name="sq_person3", sequenceName="sq_person3")
    @Column(name = "per_person_id")
    private Long personId;

    @Column(name = "per_first_name")
    private String firstName;

    @Column(name = "per_last_name")
    private String lastName;


    public Person3Annotation () {

        super ();

    }

    public Person3Annotation (String firstName, String lastName) {

        super ();

        this.firstName = firstName;
        this.lastName  = lastName;
    }


    public Long getPersonId () {

        return this.personId;
    }

    public void setPersonId (Long personId) {

        this.personId = personId;
    }

    public String getFirstName () {

        return this.firstName;
    }

    public void setFirstName (String firstName) {

        this.firstName = firstName;
    }

    public String getLastName () {

        return this.lastName;
    }

    public void setLastName (String lastName) {

        this.lastName = lastName;
    }
}