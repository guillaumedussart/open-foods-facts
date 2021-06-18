package org.fantasticfour;

import org.fantasticfour.bll.AppService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class IntegrationOpenFoodFacts {


  private static final AppService appService = AppService.getSingle();


    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        appService.insertFromCsvToDataBase();
        appService.updateDataBase();

        int choix = 0;
        do {


            afficherMenu();


            String choixMenu = scanner.nextLine();





        } while (choix != 99);

        scanner.close();
    }



    /**
     * Affichage du menu
     */
    private static void afficherMenu() {
        System.out.println("***** Diet foods *****");
        System.out.println("Recheche de produits :");
        System.out.println("Veuillez selectionner un produit de votre choix : (renter 99 pour sortir)");
        System.out.println("99. Sortir");
    }
}
