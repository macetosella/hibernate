
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity (name = "Category")
@Table (name = "category")
public class CategoryMySqlAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "cat_id", unique = true, nullable = false)
    private Long id;

    @Column (name="cat_name", length = 48, nullable = false)
    private String name;

    @Column (name="cat_description", length = 200, nullable = false)
    private String description;

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn (name="cat_parent")
    private CategoryMySqlAnnotation category;

    @OneToMany (mappedBy = "category")
    private Set<CategoryMySqlAnnotation> categories = new HashSet<>();


    public CategoryMySqlAnnotation() {

        super ();
    }

    public CategoryMySqlAnnotation(String name, String description) {

        super ();

        this.name = name;
        this.description = description;
    }


    public Long getId () {

        return this.id;
    }

    public void setId (Long id) {

        this.id = id;
    }

    public String getName () {

        return this.name;
    }

    public void setName (String name) {

        this.name = name;
    }

    public String getDescription () {

        return this.description;
    }

    public void setDescription (String description) {

        this.description = description;
    }

    public CategoryMySqlAnnotation getCategory () {

        return this.category;
    }

    public void setCategory (CategoryMySqlAnnotation category) {

        this.category = category;
    }

    public Set<CategoryMySqlAnnotation> getCategories () {

        return this.categories;
    }

    public void setCategories (Set<CategoryMySqlAnnotation> categories) {

        this.categories = categories;
    }
}