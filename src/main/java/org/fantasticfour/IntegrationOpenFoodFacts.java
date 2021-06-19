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
				System.out.println("produit : "+product.getName());
                System.out.println("nutri-score : "+product.getNutri_score());
                System.out.println("categorie : "+product.getCategorie().getName());
                System.out.println("marque: "+product.getMark().getName());
                System.out.println("alergenne: "+product.getAllergenes());
                System.out.println(product.getIngredients());

                Set<Ingredient> ingredientSet ;
                ingredientSet= new HashSet<>(product.getIngredients());
                Iterator<Ingredient> it = ingredientSet.iterator();

                while(it.hasNext()){
                    System.out.println(it.next());
                }

                Set<Vitamine> vitamineList= product.getVitamines();

                for (int i = 0;i< vitamineList.size();i++) {
                    String vitamine = vitamineList.toString();
                    System.out.println("vitamines : " + vitamine);
                }
				
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
