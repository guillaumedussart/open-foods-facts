package org.fantasticfour.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "vitamines")
public class Vitamine implements Serializable{


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "allowance_for_100g")
    private double alloanceFor100g;

    @ManyToMany(mappedBy = "vitamines" ,cascade = CascadeType.PERSIST)
    private Set<Product> products;

    public Vitamine() {
    }

    public Vitamine(String name, double alloanceFor100g) {
        this.name = name;
        this.alloanceFor100g = alloanceFor100g;
    }

    public Vitamine(String name, double alloanceFor100g, Set<Product> products) {
        this.name = name;
        this.alloanceFor100g = alloanceFor100g;
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
     * get field @Column(name = "name",length = 50)
     *
     * @return name @Column(name = "name",length = 50)

     */
    public String getName() {
        return this.name;
    }

    /**
     * set field @Column(name = "name",length = 50)
     *
     * @param name @Column(name = "name",length = 50)

     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get field @Column(name = "allowance_for_100g")
     *
     * @return alloanceFor100g @Column(name = "allowance_for_100g")

     */
    public double getAlloanceFor100g() {
        return this.alloanceFor100g;
    }

    /**
     * set field @Column(name = "allowance_for_100g")
     *
     * @param alloanceFor100g @Column(name = "allowance_for_100g")

     */
    public void setAlloanceFor100g(double alloanceFor100g) {
        this.alloanceFor100g = alloanceFor100g;
    }

    /**
     * get field @ManyToMany(mappedBy = "vitamines" ,cascade = CascadeType.PERSIST)
     *
     * @return products @ManyToMany(mappedBy = "vitamines" ,cascade = CascadeType.PERSIST)

     */
    public Set<Product> getProducts() {
        return this.products;
    }

    /**
     * set field @ManyToMany(mappedBy = "vitamines" ,cascade = CascadeType.PERSIST)
     *
     * @param products @ManyToMany(mappedBy = "vitamines" ,cascade = CascadeType.PERSIST)

     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
