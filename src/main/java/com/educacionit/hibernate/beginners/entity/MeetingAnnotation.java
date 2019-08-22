
package com.educacionit.hibernate.beginners.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity (name="Meeting")
@Table (name = "meeting")
public class MeetingAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "meeting_seq")
    @SequenceGenerator (name = "meeting_seq", sequenceName = "meeting_seq")
    @Column (name = "mit_id")
    private Long id;

    @Column (name = "mit_subject")
    private String subject;

    @Column (name = "mit_date")
    private Date date;

    @ManyToMany (mappedBy="meetings")
    private Set<StudentAnnotation> students = new HashSet<> ();


    public MeetingAnnotation () {

        super ();
    }

    public MeetingAnnotation (String subject, Date date) {

        super ();

        this.subject = subject;
        this.date    = date;
    }


    public Long getId () {

        return this.id;
    }

    public void setId (Long id) {

        this.id = id;
    }

    public String getSubject () {

        return this.subject;
    }

    public void setSubject (String subject) {

        this.subject = subject;
    }

    public Date getDate () {

        return this.date;
    }

    public void setDate (Date date) {

        this.date = date;
    }

    public Set<StudentAnnotation> getStudents () {

        return this.students;
    }

    public void setStudents (Set<StudentAnnotation> students) {

        this.students = students;
    }
}