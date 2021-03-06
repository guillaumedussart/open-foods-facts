package org.fantasticfour.bo;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "produits")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "nutri_score")
    private String nutri_score;

    @Column(name = "calorie")
    private double energy;

    @Column(name = "graisse")
    private double fat;

    @Column(name = "sucre")
    private double sucre;

    @Column(name = "fibre")
    private double fiber;

    @Column(name = "proteine")
    private double proteins;

    @Column(name = "sel")
    private double salt;

    @Column(name = "calcium")
    private double calcium;

    @Column(name = "magnesium")
    private double magnesium;

    @Column(name = "iron")
    private double iron;

    @Column(name = "fer")
    private double fer;

    @Column(name = "beta_caroten")
    private double beta_carotene;

    @Column(name = "huile_de_palme")
    private boolean palm_oil;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "prod_add",
            joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_additive", referencedColumnName = "id"))
    private Set<Additive> additives;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "prod_ing",
            joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))
    private Set<Ingredient> ingredients;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "prod_vit",
            joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_vitamine", referencedColumnName = "id"))
    private Set<Vitamine> vitamines;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category categorie;

    @OneToMany
    @JoinColumn(name = "id_product")
    private Set<Allergen> allergenes;

    @ManyToOne
    @JoinColumn(name = "id_mark")
    private Mark mark;
    {
        this.ingredients = new HashSet<>();
        this.vitamines = new HashSet<>();
        this.allergenes = new HashSet<>();
        this.additives = new HashSet<>();
    }

    public Product() {
        super();
    }

    public Product(String name, String nutri_score, double energy, double fat, double sucre, double fiber,
                   double proteins, double salt, double calcium, double magnesium, double iron, double fer,
                   double beta_carotene, boolean palm_oil, Set<Additive> additives, Set<Ingredient> ingredients,
                   Category categorie, Set<Allergen> allergenes) {
        this.name = name;
        this.nutri_score = nutri_score;
        this.energy = energy;
        this.fat = fat;
        this.sucre = sucre;
        this.fiber = fiber;
        this.proteins = proteins;
        this.salt = salt;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.iron = iron;
        this.fer = fer;
        this.beta_carotene = beta_carotene;
        this.palm_oil = palm_oil;
        this.additives = additives;
        this.ingredients = ingredients;
        this.categorie = categorie;
        this.allergenes = allergenes;
    }

    public Product(String name, String nutri_score, double energy, double fat, double sucre, double fiber, double proteins, double salt, double calcium, double magnesium, double iron, double fer, double beta_carotene, boolean palm_oil) {
        this.name = name;
        this.nutri_score = nutri_score;
        this.energy = energy;
        this.fat = fat;
        this.sucre = sucre;
        this.fiber = fiber;
        this.proteins = proteins;
        this.salt = salt;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.iron = iron;
        this.fer = fer;
        this.beta_carotene = beta_carotene;
        this.palm_oil = palm_oil;
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

    public String getNutri_score() {
        return nutri_score;
    }

    public void setNutri_score(String nutri_score) {
        this.nutri_score = nutri_score;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSucre() {
        return sucre;
    }

    public void setSucre(double sucre) {
        this.sucre = sucre;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getFer() {
        return fer;
    }

    public void setFer(double fer) {
        this.fer = fer;
    }

    public double getBeta_carotene() {
        return beta_carotene;
    }

    public void setBeta_carotene(double beta_carotene) {
        this.beta_carotene = beta_carotene;
    }

    public boolean isPalm_oil() {
        return palm_oil;
    }

    public void setPalm_oil(boolean palm_oil) {
        this.palm_oil = palm_oil;
    }

    public Set<Additive> getAdditives() {
        return additives;
    }

    public void setAdditives(Set<Additive> additives) {
        this.additives = additives;
    }


    public void addAdditive(Additive additive) {
        if (additive != null) {
            additives.add(additive);
            additive.getProducts().add(this);
        }
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {

        this.ingredients = ingredients;

    }

    public void addIngredient(Ingredient ingredients1) {
        if (ingredients1 != null) {
            ingredients.add(ingredients1);
            ingredients1.getProducts().add(this);
        }
    }


    public Category getCategorie() {
        return categorie;
    }

    public void setCategorie(Category categorie) {

        this.categorie = categorie;


    }

    public Set<Allergen> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergen> allergenes) {
        this.allergenes = allergenes;
    }


    public void addAllergen(Allergen allergen) {
        if (allergen != null) {
            allergenes.add(allergen);
            allergen.setProduct(this);
        }
    }
    /**
     * get field @ManyToMany
     *
     * @return vitamines @ManyToMany
     * @JoinTable(name = "prod_vit",
     * joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
     * inverseJoinColumns = @JoinColumn(name = "id_vitamine", referencedColumnName = "id"))
     * @JoinTable(name = "prod_vit",
     * joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
     * inverseJoinColumns = @JoinColumn(name = "id_vitamine", referencedColumnName = "id"))
     */
    public Set<Vitamine> getVitamines() {
        return this.vitamines;
    }

    /**
     * set field @ManyToMany
     *
     * @param vitamines @ManyToMany
     * @JoinTable(name = "prod_vit",
     * joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
     * inverseJoinColumns = @JoinColumn(name = "id_vitamine", referencedColumnName = "id"))
     * @JoinTable(name = "prod_vit",
     * joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
     * inverseJoinColumns = @JoinColumn(name = "id_vitamine", referencedColumnName = "id"))
     */
    public void setVitamines(Set<Vitamine> vitamines) {
        this.vitamines = vitamines;
    }


    public void addVitamine(Vitamine vitamine) {
        if (vitamine != null) {
            vitamines.add(vitamine);
            vitamine.getProducts().add(this);
        }
    }
    /**
     * get field @ManyToOne(cascade = CascadeType.PERSIST)
     *
     * @return mark @ManyToOne(cascade = CascadeType.PERSIST)
     * @JoinColumn(name = "id_mark")
     * @JoinColumn(name = "id_mark")
     */
    public Mark getMark() {


        return this.mark;
    }

    /**
     * set field @ManyToOne(cascade = CascadeType.PERSIST)
     *
     * @param mark @ManyToOne(cascade = CascadeType.PERSIST)
     * @JoinColumn(name = "id_mark")
     * @JoinColumn(name = "id_mark")
     */
    public void setMark(Mark mark) {
        this.mark = mark;
    }


}
