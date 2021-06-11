package org.fantasticfour.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="categories")

public class Category implements Serializable {

	@Id
    @GeneratedValue
	private int id;
	
    @Column(name = "name")
	private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
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
    
	
    
}
