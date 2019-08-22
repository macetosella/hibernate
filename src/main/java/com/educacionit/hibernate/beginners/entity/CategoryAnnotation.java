
package com.educacionit.hibernate.beginners.entity;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@Entity (name = "Category")
@Table (name = "category")
public class CategoryAnnotation {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "category_seq")
    @SequenceGenerator (name="category_seq", sequenceName="category_seq")
    @Column(name = "cat_id", unique = true, nullable = false)
    private Long id;

    @Column (name="cat_name", length = 48, nullable = false)
    private String name;

    @Column (name="cat_description", length = 200, nullable = false)
    private String description;

    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn (name="cat_parent")
    private CategoryAnnotation category;

    @OneToMany (mappedBy = "category")
    private Set<CategoryAnnotation> categories = new HashSet<>();


    public CategoryAnnotation () {

        super ();
    }

    public CategoryAnnotation (String name, String description) {

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

    public CategoryAnnotation getCategory () {

        return this.category;
    }

    public void setCategory (CategoryAnnotation category) {

        this.category = category;
    }

    public Set<CategoryAnnotation> getCategories () {

        return this.categories;
    }

    public void setCategories (Set<CategoryAnnotation> categories) {

        this.categories = categories;
    }
}