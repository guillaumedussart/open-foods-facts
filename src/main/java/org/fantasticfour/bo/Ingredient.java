package org.fantasticfour.bo;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name",length = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product")
    private Product product;

    public Ingredient() {
    }

    public Ingredient(String name, Product product) {
        this.name = name;
        this.product = product;
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
     * get field @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

      *
      * @return product @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * set field @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

      *
      * @param product @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_product")

     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
