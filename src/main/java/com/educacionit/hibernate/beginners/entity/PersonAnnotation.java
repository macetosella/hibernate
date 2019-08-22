
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@Entity (name="Person")
@Table (name = "entity")
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name="ENT_DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue (value="P")
public class PersonAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "sq_entity")
    @SequenceGenerator (name="sq_entity", sequenceName="sq_entity")
    @Column (name = "ent_id")
    private Long personId;

    @Column (name = "ent_first_name")
    private String firstName;

    @Column (name = "ent_last_name")
    private String lastName;


    public PersonAnnotation () {

        super ();
    }

    public PersonAnnotation (String firstName, String lastName) {

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