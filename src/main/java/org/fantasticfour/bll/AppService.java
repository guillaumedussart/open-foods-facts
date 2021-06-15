package org.fantasticfour.bll;

import org.fantasticfour.bo.Category;
import org.fantasticfour.bo.Mark;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;

public class AppService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
    private EntityManager em = emf.createEntityManager();


    private static AppService single;
    public String category;
    public String mark;
    public String name;
    public String nutriGrade;
    public String ingredients;
    public double energie;
    public double fat;
    public double sugar;
    public double fiber;
    public double proteins;
    public double salt;
    public double vitA;
    public double vitD;
    public double vitE;
    public double vitK;
    public double vitC;
    public double vitB1;
    public double vitB2;
    public double vitPP;
    public double vitB6;
    public double vitB9;
    public double vitB12;
    public double calcium;
    public double magnesium;
    public double iron;
    public double fer;
    public double beta_carotene;
    public boolean palm_oil;

    private AppService() {
    }//Prevent initialization


    public static AppService getSingle() {

        if (null == single) {
            single = new AppService();
        }
        return single;
    }

    public void initVariable(String[] part) {


        category = part[0];
        mark = part[1];
        name = part[2];
        nutriGrade = part[3];
        ingredients = part[4];

        energie = 0;
        if (!part[5].isEmpty()) {
            energie = Double.parseDouble(part[5]);
        }
        fat = 0;
        if (!part[6].isEmpty()) {
            fat = Double.parseDouble(part[6]);
        }

        sugar = 0;
        if (!part[7].isEmpty()) {
            sugar = Double.parseDouble(part[7]);
        }


        fiber = 0;
        if (!part[8].isEmpty()) {
            fiber = Double.parseDouble(part[8]);
        }
        proteins = 0;
        if (!part[9].isEmpty()) {
            proteins = Double.parseDouble(part[9]);
        }
        salt = 0;
        if (!part[10].isEmpty()) {
            salt = Double.parseDouble(part[10]);
        }
        vitA = 0;
        if (!part[11].isEmpty()) {
            vitA = Double.parseDouble(part[11]);
        }
        vitD = 0;
        if (!part[12].isEmpty()) {
            vitD = Double.parseDouble(part[12]);
        }
        vitE = 0;
        if (!part[13].isEmpty()) {
            vitE = Double.parseDouble(part[13]);
        }
        vitK = 0;
        if (!part[14].isEmpty()) {
            vitK = Double.parseDouble(part[14]);
        }
        vitC = 0;
        if (!part[15].isEmpty()) {
            vitC = Double.parseDouble(part[15]);
        }
        vitB1 = 0;
        if (!part[16].isEmpty()) {
            vitB1 = Double.parseDouble(part[16]);
        }
        vitB2 = 0;
        if (!part[17].isEmpty()) {
            vitB2 = Double.parseDouble(part[17]);
        }
        vitPP = 0;
        if (!part[18].isEmpty()) {
            vitPP = Double.parseDouble(part[18]);
        }
        vitB6 = 0;
        if (!part[19].isEmpty()) {
            vitB6 = Double.parseDouble(part[19]);
        }
        vitB9 = 0;
        if (!part[20].isEmpty()) {
            vitB9 = Double.parseDouble(part[20]);
        }
        vitB12 = 0;
        if (!part[21].isEmpty()) {
            vitB12 = Double.parseDouble(part[21]);
        }
        calcium = 0;
        if (!part[22].isEmpty()) {
            calcium = Double.parseDouble(part[22]);
        }
        magnesium = 0;
        if (!part[23].isEmpty()) {
            magnesium = Double.parseDouble(part[23]);
        }
        iron = 0;
        if (!part[24].isEmpty()) {
            iron = Double.parseDouble(part[24]);
        }
        fer = 0;
        if (!part[25].isEmpty()) {
            fer = Double.parseDouble(part[25]);
        }
        beta_carotene = 0;
        if (!part[26].isEmpty()) {
            beta_carotene = Double.parseDouble(part[26]);
        }
        palm_oil = false;
        if (!part[27].isEmpty()) {
            palm_oil = Boolean.parseBoolean(part[27]);
        }
    }

    public void insertCategories(String category) {
        HashSet<String> deleteSameCategories = new HashSet<>();
        if (deleteSameCategories.add(category)) {
            em.getTransaction().begin();
            Category categories = new Category(category);
            em.persist(categories);
            em.getTransaction().commit();
        }
    }

    public void insertMarks(String mark) {
        HashSet<String> deleteSameMarks = new HashSet<>();
        if (deleteSameMarks.add(mark)) {
            em.getTransaction().begin();
            Mark marks = new Mark(mark);
            em.persist(marks);
            em.getTransaction().commit();
        }
    }
}
