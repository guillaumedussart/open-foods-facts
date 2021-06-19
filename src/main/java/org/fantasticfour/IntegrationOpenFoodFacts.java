package org.fantasticfour;

import org.fantasticfour.bll.AppService;
import org.fantasticfour.bll.ProductService;
import org.fantasticfour.bo.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class IntegrationOpenFoodFacts {


//  private static final AppService appService = AppService.getSingle();
	private static final ProductService prodService = ProductService.getSingle();


    public static void main(String[] args) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
//        appService.insertFromCsvToDataBase();
//        appService.updateDataBase();

        String choixMenu="0";
        
        do {


            afficherMenu();


            choixMenu = scanner.nextLine();
            
            List<Product> listProducts = prodService.findByNameLike(choixMenu);
            
            for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				System.out.println("|---------------------------------------------------------------------");
				System.out.println("| "+product.getName()+" | "+product.getNutri_score()+"");
				
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
