
package com.educacionit.hibernate.beginners.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity (name="Student")
@Table (name = "student")
public class StudentAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "student_seq")
    @SequenceGenerator (name="student_seq", sequenceName="student_seq")
    @Column (name = "st_id")
    private Long personId;

    @Column (name = "st_name")
    private String firstName;

    @Column (name = "st_last_name")
    private String lastName;

    @ManyToMany (cascade = {CascadeType.MERGE})
    @JoinTable (name = "student_meeting",
            joinColumns = {@JoinColumn (name = "st_id")},
            inverseJoinColumns = {@JoinColumn (name = "mit_id")})
    private Set<MeetingAnnotation> meetings = new HashSet<> ();


    public StudentAnnotation () {

        super ();
    }

    public StudentAnnotation (String firstName, String lastName) {

        super ();

        this.firstName = firstName;
        this.lastName  = lastName;
    }


    public Long getId () {

        return this.personId;
    }

    public void setId (Long personId) {

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

    public Set<MeetingAnnotation> getMeetings () {

        return this.meetings;
    }

    public void setMeetings (Set<MeetingAnnotation> meetings) {

        this.meetings = meetings;
    }
}