
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@NamedQueries ({

    @NamedQuery(
        name  = "findCountryByName",
        query = "from Country c where c.name like :name"
    )
})
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "findCountryByNameNative",
        query = "select c.* from country c where c.cou_name ilike :name",
        resultClass = CountryAnnotation.class
    )
})
@Entity (name =  "Country") // si no pongo este nombre toma el de a clase ej: CountryAnnotation
@Table (name = "country")
public class CountryAnnotation {


    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column (name="cou_id", nullable=false, unique=true)
    private int id;

    @Column(name="cou_code", length=10, nullable=false)
    private String code;

    @Column(name="cou_name", length=128, nullable=false)
    private String name;


    public CountryAnnotation () {

        super ();
    }

    public CountryAnnotation (String name, String code) {

        super ();

        this.name = name;
        this.code = code;
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

    public String getCode () {

        return this.code;
    }

    public void setCode (String code) {

        this.code = code;
    }
}