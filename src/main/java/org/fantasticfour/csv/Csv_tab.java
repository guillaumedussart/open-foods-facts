package org.fantasticfour.csv;

import java.util.List;

public class Csv_tab {
private String categorie;
private String marque;
private String nom;
private String nutriScore;
private String ingredient;
private String energie;
private String graisse;
private String surcre;
private String fibre;
private String proteine;
private String sel;
private List<CsvVitamine> listVitamine;
private boolean huile_palm;
private List<Additifs> listAdditifs;
private List<Allergens> allergensList;

    public Csv_tab() {
    }

    public Csv_tab(String categorie, String marque, String nom, String nutriScore, String ingredient, String energie, String graisse, String surcre, String fibre, String proteine, String sel, List<CsvVitamine> listVitamine, boolean huile_palm, List<Additifs> listAdditifs, List<Allergens> allergensList) {
        this.categorie = categorie;
        this.marque = marque;
        this.nom = nom;
        this.nutriScore = nutriScore;
        this.ingredient = ingredient;
        this.energie = energie;
        this.graisse = graisse;
        this.surcre = surcre;
        this.fibre = fibre;
        this.proteine = proteine;
        this.sel = sel;
        this.listVitamine = listVitamine;
        this.huile_palm = huile_palm;
        this.listAdditifs = listAdditifs;
        this.allergensList = allergensList;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNutriScore() {
        return nutriScore;
    }

    public void setNutriScore(String nutriScore) {
        this.nutriScore = nutriScore;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getEnergie() {
        return energie;
    }

    public void setEnergie(String energie) {
        this.energie = energie;
    }

    public String getGraisse() {
        return graisse;
    }

    public void setGraisse(String graisse) {
        this.graisse = graisse;
    }

    public String getSurcre() {
        return surcre;
    }

    public void setSurcre(String surcre) {
        this.surcre = surcre;
    }

    public String getFibre() {
        return fibre;
    }

    public void setFibre(String fibre) {
        this.fibre = fibre;
    }

    public String getProteine() {
        return proteine;
    }

    public void setProteine(String proteine) {
        this.proteine = proteine;
    }

    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    public List<CsvVitamine> getListVitamine() {
        return listVitamine;
    }

    public void setListVitamine(List<CsvVitamine> listVitamine) {
        this.listVitamine = listVitamine;
    }

    public boolean isHuile_palm() {
        return huile_palm;
    }

    public void setHuile_palm(boolean huile_palm) {
        this.huile_palm = huile_palm;
    }

    public List<Additifs> getListAdditifs() {
        return listAdditifs;
    }

    public void setListAdditifs(List<Additifs> listAdditifs) {
        this.listAdditifs = listAdditifs;
    }

    public List<Allergens> getAllergensList() {
        return allergensList;
    }

    public void setAllergensList(List<Allergens> allergensList) {
        this.allergensList = allergensList;
    }

    @Override
    public String toString() {
        return "Csv_tab{" +
                "categorie='" + categorie + '\'' +
                ", marque='" + marque + '\'' +
                ", nom='" + nom + '\'' +
                ", nutriScore='" + nutriScore + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", energie='" + energie + '\'' +
                ", graisse='" + graisse + '\'' +
                ", surcre='" + surcre + '\'' +
                ", fibre='" + fibre + '\'' +
                ", proteine='" + proteine + '\'' +
                ", sel='" + sel + '\'' +
                ", listVitamine=" + listVitamine +
                ", huile_palm=" + huile_palm +
                ", listAdditifs=" + listAdditifs +
                ", allergensList=" + allergensList +
                '}';
    }
}
