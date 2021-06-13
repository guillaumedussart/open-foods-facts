package org.fantasticfour.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "additives")
public class Additive implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @ManyToMany(mappedBy = "additives",cascade =CascadeType.PERSIST)
    private Set<Product> products;


    public Additive() {

    }

    public Additive(String name) {
        this.name = name;
    }

    public Additive(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    /**
     * get field @Id
     @GeneratedValue

      *
      * @return id @Id
     @GeneratedValue

     */
    public Long getId() {
        return this.id;
    }

    /**
     * set field @Id
     @GeneratedValue

      *
      * @param id @Id
     @GeneratedValue

     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get field @Column(name = "name",length = 100,nullable = false)
     *
     * @return name @Column(name = "name",length = 100,nullable = false)

     */
    public String getName() {
        return this.name;
    }

    /**
     * set field @Column(name = "name",length = 100,nullable = false)
     *
     * @param name @Column(name = "name",length = 100,nullable = false)

     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get field @ManyToMany(mappedBy = "additives",cascade =CascadeType.PERSIST)
     *
     * @return products @ManyToMany(mappedBy = "additives",cascade =CascadeType.PERSIST)

     */
    public Set<Product> getProducts() {
        return this.products;
    }

    /**
     * set field @ManyToMany(mappedBy = "additives",cascade =CascadeType.PERSIST)
     *
     * @param products @ManyToMany(mappedBy = "additives",cascade =CascadeType.PERSIST)

     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
