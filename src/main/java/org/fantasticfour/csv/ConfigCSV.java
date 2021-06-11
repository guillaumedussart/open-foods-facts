package org.fantasticfour.csv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ConfigCSV {


    public static void main(String[] args) throws IOException {


        Path paths = Paths.get("/workspace/www/traitement-fichier-jpa-off/src/main/resources/open-food-facts.csv");


        List<String> lines = Files.readAllLines(paths, StandardCharsets.UTF_8);

        Iterator<String> fileIte = lines.iterator();
        HashSet<String> deleteSame = new HashSet<>();

        List<Category> allCategories = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] part = line.split("\\|");
            String categorie = part[0];
            if (deleteSame.add(categorie)) {
                Category categories = new Category(categorie);
                System.out.println(categories.getName());
                allCategories.add(categories);
            }
        }
        System.out.println(allCategories);
 /*       while (fileIte.hasNext()) {
            String[] getIngredient = fileIte.next().split("\\|");
            String ingredient = getIngredient[0];
            String replaceEnderscore = ingredient.replace("_","");
            //System.out.println(replaceEnderscore);
            if (deleteSame.add(ingredient)) {
                System.out.println(ingredient);
            }
            *//*List<String> blockI = Arrays.asList(replaceEnderscore.split(","));
            System.out.println("---------------------------------");
            System.out.println(getIngredient[0]);
            System.out.println(getIngredient[1]);
            System.out.println(getIngredient[2]);
            System.out.println("________________________________");
            for (int i =0;i<blockI.size();i++){
                System.out.println(blockI.get(i));
            }*//*
        }
*/

    }

}
