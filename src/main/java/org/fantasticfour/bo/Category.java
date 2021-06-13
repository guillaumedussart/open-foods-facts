package org.fantasticfour.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="categories")

public class Category implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column(name = "name")
	private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_category")
    private Set<Product> products;

	public Category(String name) {
		this.name = name;
	}

	public Category() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", product=" + products + "]";
	}


	/**
	 * get field @OneToMany(cascade = CascadeType.PERSIST)
	 @JoinColumn(name = "id_category")

	  *
	  * @return products @OneToMany(cascade = CascadeType.PERSIST)
	 @JoinColumn(name = "id_category")

	 */
	public Set<Product> getProducts() {
		return this.products;
	}

	/**
	 * set field @OneToMany(cascade = CascadeType.PERSIST)
	 @JoinColumn(name = "id_category")

	  *
	  * @param products @OneToMany(cascade = CascadeType.PERSIST)
	 @JoinColumn(name = "id_category")

	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
