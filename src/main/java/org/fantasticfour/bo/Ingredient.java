package org.fantasticfour.bo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Product> products;

    public Ingredient() {
    }

    public Ingredient(String name,Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Ingredient(String name) {
        this.name = name;
    }

    /**
     * get field @Id
     *
     * @return id @Id
     * @GeneratedValue
     * @GeneratedValue
     */
    public Long getId() {
        return this.id;
    }

    /**
     * set field @Id
     *
     * @param id @Id
     * @GeneratedValue
     * @GeneratedValue
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get field @Column(name="name",length = 50)
     *
     * @return name @Column(name="name",length = 50)
     */
    public String getName() {
        return this.name;
    }

    /**
     * set field @Column(name="name",length = 50)
     *
     * @param name @Column(name="name",length = 50)
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * get field @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.PERSIST)
     *
     * @return products @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.PERSIST)

     */
    public Set<Product> getProducts() {
        return this.products;
    }

    /**
     * set field @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.PERSIST)
     *
     * @param products @ManyToMany(mappedBy = "ingredients", cascade = CascadeType.PERSIST)

     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
