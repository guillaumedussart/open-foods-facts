package org.fantasticfour.bll;

import org.fantasticfour.bo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.*;

public class AppService {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
    private static EntityManager em = emf.createEntityManager();


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
    public String allergen;
    public String additive;
    private static String PATH_FILE;


    private static IngredientService ingredientService = IngredientService.getSingle();
    private static ProductService productService = ProductService.getSingle();
    private static MarkService markService = MarkService.getSingle();
    private static CategoryService categoryService = CategoryService.getSingle();
    private static AllergenService allergenService = AllergenService.getSingle();
    private static AdditiveService additiveService = AdditiveService.getSingle();
    private static PurifyService purifyService = PurifyService.getSingle();

    private HashSet<String> deleteSameIngredients = new HashSet<>();
    private HashSet<String> deleteSameCategories = new HashSet<>();
    private HashSet<String> deleteSameMarks = new HashSet<>();
    private HashSet<String> deleteSameAdditive = new HashSet<>();
    private HashSet<String> deleteSameAllergen = new HashSet<>();

    private Set<Vitamine> setVitamine = new HashSet<>();

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
        allergen = part[28];
        additive = part[29];

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
        allergen = null;
        if (!part[28].isEmpty()) {
            allergen = part[28];
        }
        additive = null;
        if (!part[29].isEmpty()) {
            additive = part[29];
        }
    }

    /**
     * init file
     *
     * @return {@link List}
     * @throws IOException java.io. i o exception
     * @see List
     * @see String
     */
    private List<String> initFile() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        PATH_FILE = bundle.getString("path.file");
        Path paths = Paths.get(PATH_FILE + "src/main/resources/files/csv/open-food-facts.csv");
        List<String> lines = Files.readAllLines(paths, StandardCharsets.UTF_8);
        return lines;
    }

    /**
     * insert from csv to data base
     *
     * @throws IOException java.io. i o exception
     */
    public void insertFromCsvToDataBase() throws IOException {

        em.getTransaction().begin();
        List<String> lines = initFile();


        for (int i = 1; i < lines.size(); i++) {


            String line = lines.get(i);
            String[] part = purifyService.splitAndPurifyAllLines(line);

            if (part.length != 30) {
                continue;
            }

            initVariable(part);
            String name = this.name;
            String nutriGrade = this.nutriGrade;
            String ingredients = this.ingredients;
            double energie = this.energie;
            double fat = this.fat;
            double sugar = this.sugar;
            double fiber = this.fiber;
            double proteins = this.proteins;
            double salt = this.salt;
            double vitA = this.vitA;
            double vitD = this.vitD;
            double vitE = this.vitE;
            double vitK = this.vitK;
            double vitC = this.vitC;
            double vitB1 = this.vitB1;
            double vitB2 = this.vitB2;
            double vitPP = this.vitPP;
            double vitB6 = this.vitB6;
            double vitB9 = this.vitB9;
            double vitB12 = this.vitB12;
            double calcium = this.calcium;
            double magnesium = this.magnesium;
            double iron = this.iron;
            double fer = this.fer;
            double beta_carotene = this.beta_carotene;
            boolean palm_oil = this.palm_oil;
            String additive = this.additive;
            String allergen = this.allergen;


            insertMarks(deleteSameMarks, mark);

            insertCategory(deleteSameCategories, category);

            Product products = new Product(name, nutriGrade, energie, fat, sugar, fiber, proteins, salt, calcium, magnesium, iron, fer, beta_carotene, palm_oil);

            Vitamine vitamines = new Vitamine(vitA, vitD, vitE, vitK, vitC, vitB1, vitB2, vitPP, vitB6, vitB9, vitB12);
            em.persist(vitamines);
            products.addVitamine(vitamines);
            em.persist(products);

            String replaceEnderscoreIngredients = purifyService.pruifyIngredientCsv(ingredients);


            List<String> blockIngredient = new ArrayList<String>(asList(replaceEnderscoreIngredients.trim().split(",")));
            for (int j = 0; j < blockIngredient.size(); j++) {
                if (deleteSameIngredients.add(blockIngredient.get(j).trim())) {
                    System.out.println(blockIngredient.get(j).trim());
                    Ingredient ingredientsDB = new Ingredient(blockIngredient.get(j).trim());
                    em.persist(ingredientsDB);
                }
            }


            if (null != allergen) {
                List<String> listAllergen = new ArrayList<String>(asList(allergen.trim().split(",")));
                for (int k = 0; k < listAllergen.size(); k++) {
                    if (deleteSameAllergen.add(listAllergen.get(k))) {
                        System.out.println(listAllergen.get(k));
                        Allergen allergenDB = new Allergen(listAllergen.get(k));
                        em.persist(allergenDB);

                    }
                }
            }

            List<String> listAdditive = new ArrayList<String>(asList(additive.trim().split("-")));


            for (int l = 0; l < listAdditive.size(); l++) {

                if (deleteSameAdditive.add(listAdditive.get(l))) {
                    System.out.println(listAdditive.get(l));
                    Additive additiveDB = new Additive(listAdditive.get(l));
                    em.persist(additiveDB);
                }
            }
        }
        em.getTransaction().commit();

    }

    /**
     * update data base
     *
     * @throws IOException  java.io. i o exception
     * @throws SQLException java.sql. s q l exception
     */
    public void updateDataBase() throws IOException, SQLException {

        List<String> lines = initFile();
        Set<Ingredient> setIngredient = new HashSet<>();

        for (int i = 1; i < lines.size(); i++) {


            String line = lines.get(i);
            String[] part = purifyService.splitAndPurifyAllLines(line);
            if (part.length != 30) {
                continue;
            }
            String category = part[0];
            String name = part[2];
            String ingredients = part[4];
            String marks = part[1];
            String allergen = part[28];
            String additive = part[29];
            System.out.println("produit----------------------------------------------");
            em.getTransaction().begin();
            Product products = productService.findByName(em, name);

            System.out.println(products.getName());

            Mark mark = markService.findByName(em, marks);
            products.setMark(mark);


            Category caterory = categoryService.findByName(em, category);
            products.setCategorie(caterory);
            String replaceEnderscoreIngredients = purifyService.pruifyIngredientCsv(ingredients);


            List<String> blockIngredient = new ArrayList<String>(asList(replaceEnderscoreIngredients.trim().split(",")));

            System.out.println("block ingredient--------------------------------------");

            for (int j = 0; j < blockIngredient.size(); j++) {
                Ingredient ingredientF = ingredientService.findByName(em, blockIngredient.get(j).trim());
                products.addIngredient(ingredientF);
            }


            if (null != allergen) {
                List<String> listAllergen = new ArrayList<String>(asList(allergen.trim().split(",")));
                for (int k = 0; k < listAllergen.size(); k++) {
                    Allergen allergen1 = allergenService.findByName(em, listAllergen.get(k));
                    products.addAllergen(allergen1);
                }
            }

            List<String> listAdditive = new ArrayList<String>(asList(additive.trim().split("-")));


            for (int l = 0; l < listAdditive.size(); l++) {

                Additive additive1 = additiveService.findByName(em, listAdditive.get(l));
                products.addAdditive(additive1);
            }
            em.persist(products);
            em.getTransaction().commit();
        }
    }



    /**
     * insert marks
     *
     * @param deleteSameMarks deleteSameMarks
     * @param mark            mark
     */
    public void insertMarks(HashSet<String> deleteSameMarks, String mark) {
        if (deleteSameMarks.add(mark)) {
            Mark marks = new Mark(mark);
            em.persist(marks);
        }
    }

    /**
     * insert category
     *
     * @param deleteSameCategories deleteSameCategories
     * @param category             category
     */
    public void insertCategory(HashSet<String> deleteSameCategories, String category) {
        if (deleteSameCategories.add(category)) {
            Category categories = new Category(category);
            em.persist(categories);
        }
    }


}
