package org.fantasticfour.bo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="produits")
public class Product implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "NOM")
	private String name;
	
	@Column(name = "")
}
