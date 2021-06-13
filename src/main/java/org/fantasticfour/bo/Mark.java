package org.fantasticfour.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "marks")
public class Mark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_mark")
    private Set<Product> products;

    public Mark() {
    }

    public Mark(String name, Set<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Mark(String name) {
        this.name = name;
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
     * get field @Column(name = "name")
     *
     * @return name @Column(name = "name")

     */
    public String getName() {
        return this.name;
    }

    /**
     * set field @Column(name = "name")
     *
     * @param name @Column(name = "name")

     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get field @OneToMany(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

      *
      * @return products @OneToMany(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

     */
    public Set<Product> getProducts() {
        return this.products;
    }

    /**
     * set field @OneToMany(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

      *
      * @param products @OneToMany(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

     */
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
