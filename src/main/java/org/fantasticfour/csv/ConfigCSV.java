package org.fantasticfour.csv;

import org.fantasticfour.bo.Category;
import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.bo.Mark;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConfigCSV {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) throws IOException {


        Path paths = Paths.get("C:/Users/dimit/OneDrive/Bureau/projet wika/open-food-facts.csv");


        List<String> lines = Files.readAllLines(paths, StandardCharsets.UTF_8);


        ArrayList<String> infotab = new ArrayList<>();

        for (int i = 1; i <= lines.size() - 1; i++) {
            String ligne = lines.get(i);

            String[] morceau = ligne.split("\\|");
            System.out.println(morceau[5]);

            String table = morceau[4];
            String ingredient = table.replace(";", ",")
                    .replace("_", "")
                    .replace("-", ",")
                    .replace("—", ",")
                    .replace("*", "");

            List<String> blockI = Arrays.asList(ingredient.split(","));

            for (int i1 = 0; i1 < blockI.size(); i1++) {
                //System.out.println(blockI.get(i1));
            }






            /*List<String> ingredient = Arrays.asList(morceau[4].split(","));


            for (int i1 = 1; i1 <= ingredient.size() - 1; i1++) {
                String ing = ingredient.get(i1);
                System.out.println("-------------------------");
                System.out.println(ing);

            }*/


            //String popTotal = morceau[9].trim().replaceAll(" ", "");
            //int popTotales = Integer.parseInt(popTotal);

        }

    }

}
