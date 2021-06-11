package org.fantasticfour.csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class ConfigCSV  {





    public static void main(String[] args) throws IOException {



         Path paths = Paths.get("C:/Users/dimit/OneDrive/Bureau/projet wika/open-food-facts.csv");


      List<String> lines = Files.readAllLines(paths,StandardCharsets.UTF_8);





        ArrayList<String>infotab= new ArrayList<>();

        for (int i = 1; i <= lines.size() -1; i++){
            String ligne = lines.get(i);
            String lignes= ligne.trim().replaceAll("|",";");
            String[] morceau = lignes.split(";");



            System.out.println(morceau[2]);
            //String popTotal = morceau[9].trim().replaceAll(" ", "");
            //int popTotales = Integer.parseInt(popTotal);

        }
    }



}
