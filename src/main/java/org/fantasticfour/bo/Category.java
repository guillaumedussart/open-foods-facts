package org.fantasticfour.bo;

import java.io.Serializable;

import javax.persistence.*;

/**
 *  article
 *
 */

@Entity
@Table(name="categories")
public class Category implements Serializable {

	@Id
    @GeneratedValue
	private int id;
	
    @Column(name = "NOM")
	private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Product product;
}
