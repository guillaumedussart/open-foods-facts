package org.fantasticfour;

import org.fantasticfour.bo.Category;
import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.bo.Mark;
import org.fantasticfour.bo.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class IntegrationOpenFoodFacts {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) throws IOException {


        Path paths = Paths.get("/workspace/www/traitement-fichier-jpa-off/src/main/resources/open-food-facts.csv");

        File newFile = new File("/workspace/www/traitement-fichier-jpa-off/src/main/resources/files/recensement-copy.csv");
        FileWriter myWriter = new FileWriter("/workspace/www/traitement-fichier-jpa-off/src/main/resources/files/recensement-copy.csv");
        List<String> lines = Files.readAllLines(paths, StandardCharsets.UTF_8);

        Iterator<String> fileIte = lines.iterator();
        HashSet<String> deleteSameCategories = new HashSet<>();
        HashSet<String> deleteSameMarks = new HashSet<>();
        HashSet<String> deleteSameIngredients = new HashSet<>();
        HashSet<String> deleteSameIngredients2 = new HashSet<>();

        List<Category> allCategories = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] part = line.replace("|’", "l'")
                    .replace("—|a", " a")
                    .replace("ty|ate de sodium", "tyate de sodium")
                    .replace(" conservateur |antioxydant: levure", " conservateur antioxydant: levure")
                    .replace("|% [maltodextrine de blé", ",maltodextrine de blé")
                    .replace("Filets de colin d’A|aska 72%qualité sans arête*", "Filets de colin d’Aaska 72% qualité sans arête")
                    .split("\\|");

            String categorie = part[0];
            String mark = part[1];
            String name = part[2];
            String nutriGrade = part[3];
            String ingredients = part[4];

            double energie = 0;
            if (!part[5].isEmpty()) {
                energie = Double.parseDouble(part[5]);
            }
            double fat = 0;
            if (!part[6].isEmpty()) {
                fat = Double.parseDouble(part[6]);
            }

            double sugar = 0;
            if (!part[7].isEmpty()) {
                sugar = Double.parseDouble(part[7]);
            }


            double fiber = 0;
            if (!part[8].isEmpty()) {
                fiber = Double.parseDouble(part[8]);
            }
            double proteins = 0;
            if (!part[9].isEmpty()) {
                proteins = Double.parseDouble(part[9]);
            }
            double salt = 0;
            if (!part[10].isEmpty()) {
                salt = Double.parseDouble(part[10]);
            }
            double vitA = 0;
            if (!part[11].isEmpty()) {
                vitA = Double.parseDouble(part[11]);
            }
            double vitD = 0;
            if (!part[12].isEmpty()) {
                vitD = Double.parseDouble(part[12]);
            }
            double vitE = 0;
            if (!part[13].isEmpty()) {
                vitE = Double.parseDouble(part[13]);
            }
            double vitK = 0;
            if (!part[14].isEmpty()) {
                vitK = Double.parseDouble(part[14]);
            }
            double vitC = 0;
            if (!part[15].isEmpty()) {
                vitC = Double.parseDouble(part[15]);
            }
            double vitB1 = 0;
            if (!part[16].isEmpty()) {
                vitB1 = Double.parseDouble(part[16]);
            }
            double vitB2 = 0;
            if (!part[17].isEmpty()) {
                vitB2 = Double.parseDouble(part[17]);
            }
            double vitPP = 0;
            if (!part[18].isEmpty()) {
                vitPP = Double.parseDouble(part[18]);
            }
            double vitB6 = 0;
            if (!part[19].isEmpty()) {
                vitB6 = Double.parseDouble(part[19]);
            }
            double vitB9 = 0;
            if (!part[20].isEmpty()) {
                vitB9 = Double.parseDouble(part[20]);
            }
            double vitB12 = 0;
            if (!part[21].isEmpty()) {
                vitB12 = Double.parseDouble(part[21]);
            }
            double calcium = 0;
            if (!part[22].isEmpty()) {
                calcium = Double.parseDouble(part[22]);
            }
            double magnesium = 0;
            if (!part[23].isEmpty()) {
                magnesium = Double.parseDouble(part[23]);
            }
            double iron = 0;
            if (!part[24].isEmpty()) {
                iron = Double.parseDouble(part[24]);
            }
            double fer = 0;
            if (!part[25].isEmpty()) {
                fer = Double.parseDouble(part[25]);
            }
            double beta_carotene = 0;
            if (!part[26].isEmpty()) {
                beta_carotene = Double.parseDouble(part[26]);
            }
            boolean palm_oil = false;
            if (!part[27].isEmpty()) {
                palm_oil = Boolean.parseBoolean(part[27]);
            }
            /*if (deleteSameCategories.add(categorie)) {
                em.getTransaction().begin();
                Category categories = new Category(categorie);
                em.persist(categories);
                em.getTransaction().commit();
            }

            if (deleteSameMarks.add(mark)) {
                em.getTransaction().begin();
                Mark marks = new Mark(mark);
                em.persist(marks);
                em.getTransaction().commit();
            }*/

            /*System.out.println("nomProduit----------------------------------");
            System.out.println(name);
            System.out.println("------------------------------------------------");*/


            Product products = new Product(name, nutriGrade, energie, fat, sugar, fiber, proteins, salt, calcium, magnesium, iron, fer, beta_carotene, palm_oil);


            String replaceEnderscoreIngredients = ingredients
                    .replace("vitamines:","")
                    .replace("_", "")
                    .replace("]", "")
                    .replace(")", "")
                    .replace(" .", "")
                    .replace("[%","")
                    .replace("*", "")
                    .replace(".   ", "")
                    .replace("-", ",")
                    .replace(" : ", ": ")
                    .replace(";", ",")
                    .replace("marmelade de citrons confiture d’ananas et de fruits de la passion", "marmelade de citrons confiture d’ananas et de fruits de la passion,")
                    .replace("confiture de framboises et de litchis", "confiture de framboises et de litchis,")
                    .replace("confiture de pamplemousses et de fruits du dragon", "confiture de pamplemousses et de fruits du dragon,")
                    .replace("plein air, issus de l'agriculture", "plein air issus de l'agriculture")
                    .replace("confiture de mangues et de pêches","confiture de mangues et de pêches,")
                    .replace("confiture de poires et de mirabelles","confiture de poires et de mirabelles,")
                    .replace("confiture de fraises à la verveine","confiture de fraises à la verveine,")
                    .replace("confiture de framboises et de groseilles","confiture de framboises et de groseilles,")
                    .replace("confiture extra de cerises et de mûres","confiture extra de cerises et de mûres,")
                    .replace("marmelade d’oranges à la cannelle","marmelade d’oranges à la cannelle,")
                    .replace("confiture extra de 4 fruits","confiture extra de 4 fruits,")
                    .replace("marmelade d’oranges douces et de mandarines","marmelade d’oranges douces et de mandarines,")
                    .replace("confiture de fraises et de groseilles","confiture de fraises et de groseilles,")
                    .replace("confiture extra de rhubarbe","confiture extra de rhubarbe,")
                    .replace("confiture extra de fraises et de fraises des bois","confiture extra de fraises et de fraises des bois,")
                    .replace("marmelade de citrons confiture d’ananas et de fruits de la passion","marmelade de citrons confiture d’ananas et de fruits de la passion,")
                    .replace("confiture extra de reines","confiture extra de reines,")
                    .replace("oeclatatton nutritionnelle pour / nutrition declaration for / nahrwertdeklaration pto / voedingswaardever,melding per 100 g energie / energy / energie","")
                    .replace("anti,agglomérant","antiagglomérant")
                    .toLowerCase(Locale.ROOT);
            myWriter.write(replaceEnderscoreIngredients+"\n");
            List<String> blockIngredientj = new ArrayList<String>(Arrays.asList(replaceEnderscoreIngredients.trim().split(",")));
            for (int j = 0; j < blockIngredientj.size(); j++) {

                if (deleteSameIngredients.add(blockIngredientj.get(j).trim().toLowerCase(Locale.ROOT))) {
                    System.out.println(blockIngredientj.get(j));

                    /*em.getTransaction().begin();
                    Ingredient ingredientsDB = new Ingredient(blockIngredientj.get(j));
                    em.persist(ingredientsDB);
                    em.getTransaction().commit();*/
                }

            }
            /*for (int j = 1; j < blockIngredientj.size(); j++) {
                List<String> blockIngredientk = new ArrayList<String>(Arrays.asList(blockIngredientj.get(j).trim().split(";")));

                for (int k = 1; k < blockIngredientk.size(); k++) {
                    System.out.println(blockIngredientk.get(k));
                    //List<String> blockIngredientClean = new ArrayList<String>(Arrays.asList(blockIngredientk.get(j).trim().split(";")));
                    if (deleteSameIngredients.add(blockIngredientj.get(j).trim().toLowerCase(Locale.ROOT))) {
                        List<String> blockIngredientf = new ArrayList<String>(Arrays.asList(blockIngredientj.get(j).trim().split(";")));
                        for (int f = 1; f < blockIngredientf.size(); f++) {
                            if (deleteSameIngredients2.add(blockIngredientf.get(f).trim().toLowerCase())) {

                                System.out.println(blockIngredientf.get(f));
                                em.getTransaction().begin();
                                Ingredient ingredientsDB = new Ingredient(blockIngredientf.get(f));
                                em.persist(ingredientsDB);
                                em.getTransaction().commit();
                            }
                        }
                        *//*if(deleteSameIngredients.add(blockIngredientk.get(k))) {

                            System.out.println(blockIngredientj.get(j));
                            System.out.println("-----------------------------------------------------");
                            System.out.println(blockIngredientk.get(k));
                        }*//*
                    }
                    *//*em.getTransaction().begin();
                    Ingredient ingredient = new Ingredient(blockIngredient.get(j));
                    em.persist(ingredient);
                    em.getTransaction().commit();*//*
                }
            }*/
        }
        myWriter.close();
       /* while (fileIte.hasNext()) {
            fileIte.next();
            String[] getElem = fileIte.next().split("\\|");
            String category = getElem[0];
            String replaceEnderscore = category.replace("_","");
            //System.out.println(replaceEnderscore);
            if (deleteSame.add(category)) {
                System.out.println(category);
            }*/
            /*List<String> blockI = Arrays.asList(replaceEnderscore.split(","));
            System.out.println("---------------------------------");
            System.out.println(getIngredient[0]);
            System.out.println(getIngredient[1]);
            System.out.println(getIngredient[2]);
            System.out.println("________________________________");
            for (int i =0;i<blockI.size();i++){
                System.out.println(blockI.get(i));
            }}*/

        emf.close();
        em.close();
    }
}
