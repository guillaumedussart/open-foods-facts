package org.fantasticfour;

import org.fantasticfour.bll.AppService;
import org.fantasticfour.bll.ProductService;
import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.bo.Product;
import org.fantasticfour.bo.Vitamine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class IntegrationOpenFoodFacts {


<<<<<<< HEAD
    //  private static final AppService appService = AppService.getSingle();
    private static final ProductService prodService = ProductService.getSingle();
=======
//  private static final AppService appService = AppService.getSingle();
>>>>>>> 9f2324b95dc4737ca7370b3637f676ba07092c37


    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
//        appService.insertFromCsvToDataBase();
//        appService.updateDataBase();
<<<<<<< HEAD

        String choixMenu = "0";
=======
>>>>>>> 9f2324b95dc4737ca7370b3637f676ba07092c37

        do {


            afficherMenu();


            choixMenu = scanner.nextLine();

            List<Product> listProducts = prodService.findByNameLike(choixMenu);

            for (Iterator iterator = listProducts.iterator(); iterator.hasNext(); ) {
                Product product = (Product) iterator.next();
                System.out.println("|---------------------------------------------------------------------");
                System.out.println("| Produit : " + product.getName());
                System.out.println("| Nutri-score : " + product.getNutri_score());
                System.out.println("| Categorie : " + product.getCategorie().getName());
                System.out.println("| Marque : " + product.getMark().getName());
                System.out.println("| Alergenne(s) : " + product.getAllergenes());
                String palm;
                if (product.isPalm_oil()) {
                    palm = " presence";
                } else {
                    palm = " absence";
                }


                Set<Ingredient> ingredientSet = new HashSet<>(product.getIngredients());
                Iterator<Ingredient> it = ingredientSet.iterator();

                System.out.println("| Ingredients :");
                while (it.hasNext()) {
                    System.out.println("|  - " + it.next().getName());
                }

                Set<Vitamine> vitamineList = product.getVitamines();

                for (int i = 0; i < vitamineList.size(); i++) {
                    String vitamine = vitamineList.toString();
                    System.out.println("| Vitamines (apport pour 100g) : " + vitamine);
                }
                System.out.println("| Presence huile de palme : " + palm);
                System.out.println("|-----------------------------------------------------------------------");
            }


        } while (!choixMenu.equals("99"));

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
