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

    @Column(name = "vitA")
    private double vitA;

    @Column(name = "vitD")
    private double vitD;

    @Column(name = "vitE")
    private double vitE;

    @Column(name = "vitK")
    private double vitK;

    @Column(name = "vitC")
    private double vitC;

    @Column(name = "vitB1")
    private double vitB1;

    @Column(name = "vitB2")
    private double vitB2;

    @Column(name = "vitPP")
    private double vitPP;

    @Column(name = "vitB6")
    private double vitB6;

    @Column(name = "vitB9")
    private double vitB9;

    @Column(name = "vitB12")
    private double vitB12;


    @ManyToMany(mappedBy = "vitamines" ,cascade = CascadeType.PERSIST)
    private Set<Product> products;


    public Vitamine() {
    }

    public Vitamine(double vitA, double vitD, double vitE, double vitK, double vitC, double vitB1, double vitB2, double vitPP, double vitB6, double vitB9, double vitB12) {
        this.vitA = vitA;
        this.vitD = vitD;
        this.vitE = vitE;
        this.vitK = vitK;
        this.vitC = vitC;
        this.vitB1 = vitB1;
        this.vitB2 = vitB2;
        this.vitPP = vitPP;
        this.vitB6 = vitB6;
        this.vitB9 = vitB9;
        this.vitB12 = vitB12;
    }

    public Vitamine(double vitA, double vitD, double vitE, double vitK, double vitC, double vitB1, double vitB2, double vitPP, double vitB6, double vitB9, double vitB12, Set<Product> products) {
        this.vitA = vitA;
        this.vitD = vitD;
        this.vitE = vitE;
        this.vitK = vitK;
        this.vitC = vitC;
        this.vitB1 = vitB1;
        this.vitB2 = vitB2;
        this.vitPP = vitPP;
        this.vitB6 = vitB6;
        this.vitB9 = vitB9;
        this.vitB12 = vitB12;
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
     * get field @Column(name = "vitA")
     *
     * @return vitA @Column(name = "vitA")

     */
    public double getVitA() {
        return this.vitA;
    }

    /**
     * set field @Column(name = "vitA")
     *
     * @param vitA @Column(name = "vitA")

     */
    public void setVitA(double vitA) {
        this.vitA = vitA;
    }

    /**
     * get field @Column(name = "vitD")
     *
     * @return vitD @Column(name = "vitD")

     */
    public double getVitD() {
        return this.vitD;
    }

    /**
     * set field @Column(name = "vitD")
     *
     * @param vitD @Column(name = "vitD")

     */
    public void setVitD(double vitD) {
        this.vitD = vitD;
    }

    /**
     * get field @Column(name = "vitE")
     *
     * @return vitE @Column(name = "vitE")

     */
    public double getVitE() {
        return this.vitE;
    }

    /**
     * set field @Column(name = "vitE")
     *
     * @param vitE @Column(name = "vitE")

     */
    public void setVitE(double vitE) {
        this.vitE = vitE;
    }

    /**
     * get field @Column(name = "vitK")
     *
     * @return vitK @Column(name = "vitK")

     */
    public double getVitK() {
        return this.vitK;
    }

    /**
     * set field @Column(name = "vitK")
     *
     * @param vitK @Column(name = "vitK")

     */
    public void setVitK(double vitK) {
        this.vitK = vitK;
    }

    /**
     * get field @Column(name = "vitC")
     *
     * @return vitC @Column(name = "vitC")

     */
    public double getVitC() {
        return this.vitC;
    }

    /**
     * set field @Column(name = "vitC")
     *
     * @param vitC @Column(name = "vitC")

     */
    public void setVitC(double vitC) {
        this.vitC = vitC;
    }

    /**
     * get field @Column(name = "vitB1")
     *
     * @return vitB1 @Column(name = "vitB1")

     */
    public double getVitB1() {
        return this.vitB1;
    }

    /**
     * set field @Column(name = "vitB1")
     *
     * @param vitB1 @Column(name = "vitB1")

     */
    public void setVitB1(double vitB1) {
        this.vitB1 = vitB1;
    }

    /**
     * get field @Column(name = "vitB2")
     *
     * @return vitB2 @Column(name = "vitB2")

     */
    public double getVitB2() {
        return this.vitB2;
    }

    /**
     * set field @Column(name = "vitB2")
     *
     * @param vitB2 @Column(name = "vitB2")

     */
    public void setVitB2(double vitB2) {
        this.vitB2 = vitB2;
    }

    /**
     * get field @Column(name = "vitPP")
     *
     * @return vitPP @Column(name = "vitPP")

     */
    public double getVitPP() {
        return this.vitPP;
    }

    /**
     * set field @Column(name = "vitPP")
     *
     * @param vitPP @Column(name = "vitPP")

     */
    public void setVitPP(double vitPP) {
        this.vitPP = vitPP;
    }

    /**
     * get field @Column(name = "vitB6")
     *
     * @return vitB6 @Column(name = "vitB6")

     */
    public double getVitB6() {
        return this.vitB6;
    }

    /**
     * set field @Column(name = "vitB6")
     *
     * @param vitB6 @Column(name = "vitB6")

     */
    public void setVitB6(double vitB6) {
        this.vitB6 = vitB6;
    }

    /**
     * get field @Column(name = "vitB9")
     *
     * @return vitB9 @Column(name = "vitB9")

     */
    public double getVitB9() {
        return this.vitB9;
    }

    /**
     * set field @Column(name = "vitB9")
     *
     * @param vitB9 @Column(name = "vitB9")

     */
    public void setVitB9(double vitB9) {
        this.vitB9 = vitB9;
    }

    /**
     * get field @Column(name = "vitB12")
     *
     * @return vitB12 @Column(name = "vitB12")

     */
    public double getVitB12() {
        return this.vitB12;
    }

    /**
     * set field @Column(name = "vitB12")
     *
     * @param vitB12 @Column(name = "vitB12")

     */
    public void setVitB12(double vitB12) {
        this.vitB12 = vitB12;
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
