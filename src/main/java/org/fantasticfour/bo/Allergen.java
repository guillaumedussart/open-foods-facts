package org.fantasticfour.bo;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "allergens")
public class Allergen implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",length = 100,nullable = true)
    private String nom;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product")
    private Product product;

    public Allergen() {
    }
    
    

    public Allergen(String nom) {
		this.nom = nom;
	}



	public Allergen(String nom, Product product) {
        this.nom = nom;
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
     * get field @Column(name = "name",length = 50)
     *
     * @return nom @Column(name = "name",length = 50)

     */
    public String getNom() {
        return this.nom;
    }

    /**
     * set field @Column(name = "name",length = 50)
     *
     * @param nom @Column(name = "name",length = 50)

     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * get field @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_poduct")

      *
      * @return product @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_poduct")

     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * set field @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_poduct")

      *
      * @param product @ManyToOne(cascade = CascadeType.PERSIST)
     @JoinColumn(name = "id_poduct")

     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
