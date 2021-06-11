package org.fantasticfour.csv;

public class CsvVitamine {
    private String nom;
    private String valeurNutitive;

    public CsvVitamine() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getValeurNutitive() {
        return valeurNutitive;
    }

    public void setValeurNutitive(String valeurNutitive) {
        this.valeurNutitive = valeurNutitive;
    }

    @Override
    public String toString() {
        return "CsvVitamine{" +
                "nom='" + nom + '\'' +
                ", valeurNutitive='" + valeurNutitive + '\'' +
                '}';
    }
}
