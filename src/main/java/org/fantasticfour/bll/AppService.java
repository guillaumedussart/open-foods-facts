package org.fantasticfour.bll;

import org.fantasticfour.bo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        Path paths = Paths.get(PATH_FILE + "src/main/resources/files/open-food-facts.csv");
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
            String[] part = splitAndPurifyAllLines(line);

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

            String replaceEnderscoreIngredients = this.pruifyIngredientCsv(ingredients);


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
            String[] part = splitAndPurifyAllLines(line);
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
            String replaceEnderscoreIngredients = pruifyIngredientCsv(ingredients);


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


    public String[] splitAndPurifyAllLines(String line) {
        String[] parts = line.replace("|’", "l'")
                .replace("—|a", " a")
                .replace("ty|ate de sodium", "tyate de sodium")
                .replace(" conservateur |antioxydant: levure", " conservateur antioxydant: levure")
                .replace("|% [maltodextrine de blé", ",maltodextrine de blé")
                .replace("Filets de colin d’A|aska 72%qualité sans arête*", "Filets de colin d’Aaska 72% qualité sans arête")
                .replace("Calin+ Fruits Pêche, Abricot, Fraise, Framboise)", "Calin+ Fruits Pêche, Abricot, Fraise, Framboise")
                .split("\\|");
        return parts;
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

    /**
     * pruify ingredient csv
     *
     * @param ingredients ingredients
     * @return {@link String}
     * @see String
     */
    public String pruifyIngredientCsv(String ingredients) {
        String replaceEnderscoreIngredients = ingredients.toLowerCase(Locale.ROOT).replace("_", "").replace("]", "")
                .replace("[", "").replace(")", "").replace(" .", "").replace("[%", "").replace("*", "").replace(
                        ".   ", "")
                .replace("-", ",").replace(" : ", ": ").replace(";", ",").replace(
                        "marmelade de citrons confiture d’ananas et de fruits de la passion",
                        "marmelade de citrons confiture d’ananas et de fruits de la passion,")
                .replace("confiture de framboises et de litchis", "confiture de framboises et de litchis,").replace(
                        "confiture de pamplemousses et de fruits du dragon",
                        "confiture de pamplemousses et de fruits du dragon,")
                .replace("plein air, issus de l'agriculture", "plein air issus de l'agriculture").replace(
                        "confiture de mangues et de pêches", "confiture de mangues et de pêches,")
                .replace("confiture de poires et de mirabelles", "confiture de poires et de mirabelles,").replace(
                        "confiture de fraises à la verveine", "confiture de fraises à la verveine,")
                .replace("confiture de framboises et de groseilles", "confiture de framboises et de groseilles,")
                .replace("confiture extra de cerises et de mûres", "confiture extra de cerises et de mûres,")
                .replace("marmelade d’oranges à la cannelle", "marmelade d’oranges à la cannelle,").replace(
                        "confiture extra de 4 fruits", "confiture extra de 4 fruits,")
                .replace("marmelade d’oranges douces et de mandarines",
                        "marmelade d’oranges douces et de mandarines,")
                .replace("confiture de fraises et de groseilles", "confiture de fraises et de groseilles,").replace(
                        "confiture extra de rhubarbe", "confiture extra de rhubarbe,")
                .replace("confiture extra de fraises et de fraises des bois",
                        "confiture extra de fraises et de fraises des bois,")
                .replace("marmelade de citrons confiture d’ananas et de fruits de la passion",
                        "marmelade de citrons confiture d’ananas et de fruits de la passion,")
                .replace("confiture extra de reines", "confiture extra de reines,").replace(
                        "oeclatatton nutritionnelle pour / nutrition declaration for / nahrwertdeklaration pto / voedingswaardever,melding per 100 g energie / energy / energie",
                        "")
                .replace("anti,agglomérant", "antiagglomérant").replace("/ ingrediénten:", "").replace("[eau",
                        ",eau")
                .replace("sirop de glucose de blé ou mai's", "sirop de glucose de blé ou maïs").replace(
                        "dextrose de ma`i`s", "dextrose de maïs")
                .replace("beurre. emmental râpé20%. levure", "beurre, emmental râpé20%, levure").replace(
                        "lait  écrémé en poudre. farine de malt d‘orge",
                        "lait  écrémé en poudre, farine de malt d‘orge")
                .replace("sucre ? crème fruits: fraise ou pêche ou ou framboise ou abricot",
                        "sucre , crème fruits: fraise ou pêche ou ou framboise ou abricot")
                .replace("poudre de écrémé ? jus de carotte", "poudre de écrémé , jus de carotte").replace(
                        "jus de potiron ? jus de betterave sureau ? arômes ?: : e330. e331 ferments sans",
                        "jus de potiron , jus de betterave sureau , arômes  e330 e331")

                /* =============Victoria 1-3_350====================================== */

                .replace("vitamines: b3. b5. b12. b6. b2. b1. b9. b8:", "").replace(
                        "agent de traitement de la farine: l,cystéine.",
                        "agent de traitement de la farine: lecystéine.")
                .replace("pre,cooked wholegrain basmati rice", "precooked wholegrain basmati rice").replace(
                        "conservateur: e202. colorant: e160c, arôme.",
                        "conservateur: e202. , colorant: e160c, arôme.")
                .replace(
                        "glucose syrup, sweetened condensed skim milk, sugar, water, salted modified starch, colouring: ordinary caramel, salt, flavour, preservative :, emulsifier: mono and diglycerides of fatty acids, acidifier: citric acid. 31 35 % ,fr , ingrédients: sirop de glucose, lait écrémé condensé sucré, sucre, eau, amidon modifié, colorant: caramel ordinaire, sel, arôme, conservateur., émulsifiant: mono et diglycérides d'acide gras, acidifiant: acide citrique caramel.' min 35 %. en'. production date and best before date: see below , to be stored in a clean, dry and cool placeopening. fr: date de production et a consommer de préférence avant le: voir ci,dessous , a conserver dans un endroit propre, 50 et250 ,a consommer de préférence 6 semaines après ouverture. yf±jfiifi pd 2clfl",
                        "sirop de glucose, lait écrémé condensé sucré, sucre, eau, amidon modifié, colorant: caramel ordinaire, sel, arôme, conservateur., émulsifiant: mono et diglycérides d'acide gras, acidifiant: acide citrique caramel. min 35 %")
                .replace(", vitamines: b1. b6 et b12.", "").replace(
                        "pulpes de fruits: pêche: pulpes de fruits 52.5%, mûre: pulpes de fruits 53%, abricot: pulpes de fruits 35%, framboise: pulpes de fruits 52.5%, fraise: pulpes de fruits 52.5%, poire: pulpes de fruits 52.5%",
                        "pulpes de fruits: pêche: 52.5% mûre:  53% abricot: 35% framboise: 52.5% fraise: 52.5% poire: 52.5%")
                .replace("jeunes pousses de laitue verte, de laitue rouge, d'épinard.",
                        "jeunes pousses de laitue verte de laitue rouge d'épinard.")
                .replace(
                        "ingredienw quinoa. issu de l'agriculture biologique. risques de traces de gluten, de fruits à coques, de sésame, de lait et d'oeuf.",
                        "quinoa issu de l'agriculture biologique risques de traces de gluten de fruits à coques de sésame de lait et d'oeuf.")
                .replace("tomates mi,séchées 68%,", "tomates mi-séchées 68%,").replace(
                        "émulsifiant: lécithine de tournesol,  issu de l'agriculture biologique",
                        "émulsifiant: lécithine de tournesol issu de l'agriculture biologique")
                .replace("arôme naturel de vanille.  ingrédients agricoles issus de l'agriculture biologique.",
                        "arôme naturel de vanille issus de l'agriculture biologique.")
                .replace("sirop de glucose/fructose,saccharose, conservateur: e202. acidifiant: e330.",
                        "sirop de glucose/fructose,saccharose, conservateur: e202. , acidifiant: e330.")
                .replace("correcteur d'acidité: e330. conservateur: anhydride sulfureux.",
                        "correcteur d'acidité: e330. , conservateur: anhydride sulfureux.")
                .replace("agent de traitement de la farine: l,cystéine, colorant: caroténoïdes.",
                        "agent de traitement de la farine: lecystéine, colorant: caroténoïdes.")
                .replace(
                        "pulpes de fruits: abricot: pulpes de fruits 53%, mûre: pulpes de fruits 53%, poire: pulpes de fruits 52.5%,",
                        "pulpes de fruits: abricot: 53% mûre: 53% poire: 52.5%,")
                .replace("légumes 60% :, eau, sel.", "légumes 60% , eau, sel.")
                /* 116 */
                .replace(" farine de blé  graisse et huile végétales•eau",
                        "farine de blé  graisse et huile végétales,eau")
                .replace(
                        "dans un saladier température 500c au four traditionnelou au micro ondes. sortir le saladier du micro ondes et râper du chocolat noir sur le chocolat chaud en homogénéisant le tout avec une spatule pour redescendre à 27 oc. tempérez à nouveau au micro ondes pour atteindre 31 oc/320c. vous pourrez tremper vos fruits confits en les retirant du chocolat avec une fourchette et les poser doucement sur du papier sulfurisé pour les laisser sécher. courbe de température à respecter :",
                        "fruits confits")
                .replace(
                        "farine de blé  graisse et huile végétales,eau , alcool éthylique ,sucre , sel , jus de citron concentré , colorant: caroténoïdes , agent de traitement de la farine: lecystéine.",
                        "farine de blé , graisse et huile végétales,eau , alcool éthylique ,sucre , sel , jus de citron concentré , colorant: caroténoïdes , agent de traitement de la farine: lecystéine.")
                .replace(
                        "abricot: pulpes de fruits 53 %. cassis: pulpes de fruits. fraise: pulpes de fruits 55 %. framboise: pulpes de fruits 52 %. orange: pulpes de fruits 50 %. prune: pulpes de fruits 50 %. sucre, sirop de glucose de blé, gélifiant: pectines, arômes naturelsavec autres arômes naturels, acidifiant: jus de citron.",
                        "abricot: pulpes de fruits 53 %. cassis: pulpes de fruits. fraise: pulpes de fruits 55 %. framboise: pulpes de fruits 52 %. orange: pulpes de fruits 50 %. prune: pulpes de fruits 50 %. sucre, sirop de glucose de blé, gélifiant: pectines, arômes naturels avec autres arômes naturels, acidifiant: jus de citron.")
                .replace(
                        "pulpe de pommes, sucre, sirop de glucose, gélifiant: pectines, acidifiant: acide citrique, arômes, colorants: e100 , e163 , e160c , e141.",
                        "pulpe de pommes, sucre, sirop de glucose, gélifiant: pectines, acidifiant: acide citrique, arômes, colorants: e100  e163  e160c  e141.")
                .replace(
                        "polenta 76.4%, légumes 19.9%, eau , huile de tournesol , estragon , sel de mer   ingrédients issus de l'agriculture biologique",
                        "polenta 76.4%, légumes 19.9%, eau , huile de tournesol , estragon , sel de mer ,  ingrédients issus de l'agriculture biologique")
                .replace(
                        "jus de soja 87%, huile de tournesol, émulsifiant: gomme arabique, épaississants: gomme xanthane, carraghénanes, sucre de canne non raffiné. soja sans ogm. ingrédients issus de l'agriculture biologique.",
                        "jus de soja 87%, huile de tournesol, émulsifiant: gomme arabique, épaississants: gomme xanthane, carraghénanes, sucre de canne non raffiné. soja sans ogm. , ingrédients issus de l'agriculture biologique.")
                .replace("agent de traitement de la farine l,cystéine.",
                        "agent de traitement de la farine lecystéine.")
                .replace("chocolat au lait 58%, riz 42% ingrédients issus de l'agriculture biologique.",
                        "chocolat au lait 58%, riz 42% ,ingrédients issus de l'agriculture biologique.")
                .replace("chocolat noir 57%, riz 48%. ingrédients issus de l'agriculture biologique.",
                        "chocolat noir 57%, riz 48%. , ingrédients issus de l'agriculture biologique.")
                .replace("conservateur: e202. e220 anhydride sulfureux, e330. e120. e150a, e133. e127.",
                        "conservateur: e202. e220 anhydride sulfureux e330. e120. e150a. e133. e127.")
                .replace("correcteur d'acidité e330. conservateur e220 anhydride sulfureux.",
                        "correcteur d'acidité e330, conservateur e220 anhydride sulfureux.")
                .replace("agent de traitement de la farine: l,cystéine , colorant: bêta,carotène.",
                        "agent de traitement de la farine: lecystéine , colorant: bêtacarotène.")
                .replace("herbes de provence. ingrédients agricoles issus de l'agriculture biologique.",
                        "herbes de provence., ingrédients agricoles issus de l'agriculture biologique.")
                .replace(
                        "préparation de protéines de soja et de ble: 44%, fromage fondu: 28%, panure: 28%. huile de tournesol",
                        "préparation de protéines de soja et de ble: 44%, fromage fondu: 28%, panure: 28%. , huile de tournesol")
                .replace("carmins, arôme naturel. contient des sulfites et anhydride sulfureux.",
                        "carmins, arôme naturel., contient des sulfites et anhydride sulfureux.")
                .replace(
                        "eau. fèves de soja, sel de nigari. sans conservateurproduits issus de l’agriculture biologique",
                        "eau., fèves de soja, sel de nigari. sans conservateur ,produits issus de l’agriculture biologique")
                .replace(
                        "farine de blé t80, beurre, eau, sucre de canne blond, sel de guérande, jus de citron concentré. ingrédients issus de l'agriculture biologique.",
                        "farine de blé t80, beurre, eau, sucre de canne blond, sel de guérande, jus de citron concentré., ingrédients issus de l'agriculture biologique.")
                .replace(
                        "jus de soja 89%, huile de tournesol, émulsifiant: lécithine de soja , épaississants: gomme xanthane, carraghénanes. ingrédients issus de l'agriculture biologique.",
                        "jus de soja 89%, huile de tournesol, émulsifiant: lécithine de soja , épaississants: gomme xanthane, carraghénanes. , ingrédients issus de l'agriculture biologique.")
                .replace(
                        "fabriqué en espagne dans un atelier qui utilise fruits à coque, moutarde, ceufs, produits laitiers, gluten, lupin et celeri",
                        "fabriqué en espagne dans un atelier qui utilise fruits à coque, moutarde, ceufs, produits laitiers, gluten, lupin , celeri")
                .replace(
                        "tofu 93%, sel, épices.  ingrédients d'origine agricole obtenus selon les règles de production biologique.",
                        "tofu 93%, sel, épices. , ingrédients d'origine agricole obtenus selon les règles de production biologique.")
                .replace(
                        "correcteur d'acidité: concentré lactique, conservateur: sorbate de potassium, arôme, colorant: béta,carotène, vitamine e.",
                        "correcteur d'acidité: concentré lactique, conservateur: sorbate de potassium, arôme, colorant: bétacarotène")
                .replace(
                        "crème de lait pasteurisée, sel 2% , ferments lactiques.  biologique. les informations en gras sont destinées aux personnes intolérantes ou allergiques.",
                        "crème de lait pasteurisée, sel 2% , ferments lactiques.  biologique.")
                .replace("beurrepasteurisé doux. 1: ingrédient issu de l'agriculture biologique.",
                        "beurre pasteurisé doux. ingrédient issu de l'agriculture biologique.")
                .replace("beurre.", "beurre ").replace(
                        "huiles et graisses végétales biologiques non hydrogénées1. eau, sel de mer 1.4%, émulsifiants: lécithine de soja biologique, arômes naturels, jus de citron concentré biologique. ingrédients d'origine végétale 1 ces huiles et graisses végétales donnent à ce produit primevère un profil riche en acides gras insaturés oméga 3.6.9formulé avec l'aide du service nutrition de l'institut pasteur de lille.",
                        "huiles et graisses végétales biologiques non hydrogénées., eau, sel de mer 1.4%, émulsifiants: lécithine de soja biologique, arômes naturels, jus de citron concentré biologique. ingrédients d'origine végétale")
                /* 439 */

                .replace("vitamines: b3. b5. b12. b6. b2. b1. b9. b8:", "").replace(
                        "agent de traitement de la farine: l,cystéine.",
                        "agent de traitement de la farine: lecystéine.")
                .replace("pre,cooked wholegrain basmati rice", "precooked wholegrain basmati rice").replace(
                        ". biologique.", "")
                .replace("matière grasse et huile de palme", "matière grasse , huile de palme").replace(
                        "biscuit sec / ingrédients / ingredientes cd", "")
                .replace(" biologiques.", "").replace("émulsifiant: e471.", "émulsifiant: e471,").replace(
                        "laits écrémé et entier en poudre", "laits écrémé , entier en poudre")
                .replace("carbonates d'ammonium et de sodium", "carbonates d'ammonium , de sodium").replace(
                        " lactose et protéines de lait", " lactose , protéines de lait")
                .replace("carbonates de sodium et d'ammonium", "carbonates de sodium , d'ammonium").replace(
                        "lactose et protéines de lait", "lactose , protéines de lait")
                .replace("carbonates de sodium et d'ammonium", "carbonates de sodium , d'ammonium").replace(
                        "l,cystéine,", "lécystéine,")
                .replace("lactose et protéines de lait", "lactose , protéines de lait").replace(
                        "carbonates d'ammonium et de sodium ", "carbonates d'ammonium , de sodium ")
                .replace(". peut contenir des traces d'oeuf. conservation:", "").replace(
                        "biscuit sec / ingrédients / ingredientes cd", "")
                .replace("céréales  farine de froment,", "céréales,  farine de froment,").replace(
                        "carbonates de sodium et d'ammonium", "carbonates de sodium, d'ammonium")
                .replace("lactose et protéines de lait,", "lactose, protéines de lait,").replace("l,cystéine",
                        "lécystéine")
                .replace("laits écrémé et entier en poudre", "laits écrémé , entier en poudre").replace(
                        " laits entiers et écrémé en poudre", " laits entiers , écrémé en poudre")
                .replace("vet,urs nutritionne!tes ënetye 2054 kj'", "").replace(
                        " +ingrédients issus de l'agriculture biologique + origine francetraces éventuelles d'amande et de sésame fabriqué en france",
                        "")
                .replace("ingrédients agricoles issus de l'agriculture biologique.", "").replace(
                        " ingrédients agricoles issus de l'agriculture biologique", "")
                .replace(". ingrédients agricoles issus de l'agriculture biologique.", "").replace(
                        "issus de l'agriculture biologique.", "")
                .replace(" almond powder, sugar, egg whites, flavour, salt, wheat flour.", "").replace(
                        " vitamines b1. b2. b6 et b9.", "")
                .replace("pépites de raisin rouge déshydraté 4.7 %", "pépites de raisin rouge déshydraté 4.7 %,")
                .replace("vitamines: pp, e, b6. b2. b1. b9.", "").replace("céréales 48%/ chocolat noir 29%/",
                        "céréales 48%, chocolat noir 29%,")
                .replace(
                        "antioxydant: extrait de romarin.  dont lait. traces éventuelles dlautres céréales contenant du gluten, oeufs, soja, arachide et fruits à coque.",
                        "antioxydant: extrait de romarin,lait. ")
                .replace("biscuit 35.4%: farine de blé 32.5%,", "biscuit 35.4%, farine de blé 32.5%,").replace(
                        "certains ingrédients ne sont pas français.  ingrédients  traces éventuelles d'autres céréales contenant du gluten, ceufs, arachides, soja et fruits à coque.",
                        "")
                .replace(
                        "ingrédients  fabriqué dans un atelier qui utilise des fruits à coque, du soja, de l'oeuf, du lait et du lupin.",
                        "")
                .replace("anti,oxygène", "antioxygène").replace(
                        "vitamine e. fabriqué dans un atelier qui utilise des fruits à coque, du soja, des oeufs et du lupin.",
                        "")
                .replace("  blé stocké sans pesticides de synthèse.", "").replace(" vitamines b6. b9. d et e.", "")
                .replace(", vitamines b1. b2. b6. b9. pp et e.", "").replace(" vitamines b1. b6. b9 et e, fer.", "")
                .replace("correcteur d'acidité. chocolat au lait 48 %:",
                        "correcteur d'acidité, chocolat au lait 48 %:")
                .replace(" :.", "").replace(". , ingrédients ", "").replace("farine de blé t 65.",
                        "farine de blé t 65,")
                .replace("du pivert bucliichil bio ganzilia& chocolade fabriqué en aveyron , france", ",chocolat,")
                .replace(".  ingrédients issus de l’agriculture ", "").replace("lngrédients:", "").replace(
                        "traces possible de fruits à coque, soja et sésame.", "")
                .replace("ingrédients issus de l’agriculture ", "").replace("(", ",").replace("et", ",").replace(
                        ",, lactoseet", ", lactoseet")
                .replace("de graines de sésame et d'arachides.", "de graines de sésame , d'arachides.").replace(
                        " (", "")
                .replace("poudres a lever (", "poudres a lever ,").replace("et arôme", "").replace(
                        "traces éventuelles d'autres fruits à coque et de graines de sésame", "")
                .replace("traces éventuelles d'oeufs et de fruits à coque.", "").replace(
                        "traces éventuelles de fruits à coque et d oeufs.", "")
                .replace("traces éventuelles d'oeufs et de fruits à coque.", "").replace(
                        "traces éventuelles de lait.", "")
                .replace("traces éventuelles d'autres fruits à coque et graines de sésame", "").replace(
                        "traces éventuelles d oeufs.", "")
                .replace("sel traces éventuelles de fruits à coque.", "sel,").replace(
                        " trace éventuelles de fruits à coque et de graines de sésame.", "")
                .replace(". traces éventuelles d'oeufs.", "").replace(
                        "traces éventuelles de lait, de soja , de fruits àoque.", "")
                .replace(
                        "farine de blé 50%, beurreoncentré 25%, sucre. oeufs. jaunes d'œufs, poudres à lever. selaramel.olorant:aroténoïdes.",
                        "farine de blé 50%, beurreoncentré 25%, sucre, oeufs, jaunes d'œufs, poudres à leve,")
                .replace("gélifiant:", ",").replace("nappage fraise 50%:", ",").replace(
                        " traces éventuelles de fruits àoque , de soja. tenir au sec, à l'abri de lahaleur , de la lumière 0% des aqr",
                        "")
                .replace("farine de blé, beurreoncentré28 %,olorant ",
                        "farine de blé, beurre concentré 28 %, colorant ")
                .replace("olorant:armins", "colorant: carmins").replace("oriandre", "coriandre")
                .replace("arotte 8.5%", "carotte 8.5%").replace("vitamine b1", "")
                .replace("itrate de sodium", "nitrate de sodium")
                .replace(", ingrédients issus de l'agriculture biologique", "")
                .replace("e330.onservateur: sorbate de potassium e202.",
                        "e330 , conservateur: sorbate de potassium e202.")
                .replace(", vitamines riboflavine, b12. d.", "")
                .replace("olorant:aramel e150a,", "colorant: caramel e150a ,").replace("eau.", "eau ,")
                .replace(" ingrédients ", "").replace("ingrédients ", "").replace("dearotte", "de carotte")
                .replace("deitron", "de citron").replace("deoncentré", "de concentré")
                .replace("olorant:oncentré", " colorant: concentré").replace("pommeoncentré", "pomme concentré")
                .replace("deanne", "de canne").replace("deacao", "de cacao").replace("alcium", "calcium")
                .replace("   100 % d'origine france.", "").replace("jusoncentré", "jus concentré")
                .replace(
                        "eau gazéifiée, sucre, jus deassis issu de jus deassisoncentré, sirop de glucose, arôme, acidifiant e330. taurine,orrecteur d'acidité e331. sucrearamélisé, extrait de guarana,aféine,onservateur e211. vitamines: niacine, acide pantothénique, vitamine b6. thiamine,olorants: e122. e131. e150d, e122.",
                        "eau gazéifiée, sucre, jus de cassis issu de jus de cassis concentré, sirop de glucose, arôme, acidifiant e330. taurine, correcteur d'acidité e331. sucrearamélisé, extrait de guarana,aféine,conservateur e211.  colorants: e122. e131. e150d, e122.")
                .replace(
                        "eau, jus de fruits à base deoncentrés: ananas, orange,itron, sirop de glucose,fructose, sucre, jus de de carotte à base deoncentré, stabilisants: farine de guar , pectine, arôme, vitamine, vitamine e, provitamine a.",
                        "eau, jus de fruits à base de concentrés: ananas, orange, citron, sirop de glucose,fructose, sucre, jus de de carotte à base de concentré")
                .replace(
                        "jus , purée de pomme 83.7 %, jus deoncombre 10 %, purée de panais 4 %, jus de citron 1.5 %, fibres d'acacia, arôme naturel deoncombre, arôme naturel de menthe,oncentré de spiruline ,arthame.",
                        "jus , purée de pomme 83.7 %, jus de concombre 10 %, purée de panais 4 %, jus de citron 1.5 %, fibres d'acacia, arôme naturel de concombre, arôme naturel de menthe,concentré de spiruline ,carthame.")
                .replace("deoncentré", "de concentré").replace("citron vert0.", "citron vert")
                .replace(" deacao, vitamines,", " de cacao, ").replace("deanne", "de canne")
                .replace("deoncentré", "de concentré").replace("vitamine.", "").replace(",,amomille,", ",amomille,")
                .replace(
                        " arôme naturel issu de l'agriculture   allergène:ontient de l'extrait d'amande amère.  100% desd'origine agricole sont issus de l'agriculture biologique , 96% duommerce équitable",
                        "")
                .replace("arôme,olorant", "arôme, colorant")
                .replace(",oncentré de jus de raisin ", ", concentré de jus de raisin ")
                .replace("olorant: e120.", "colorant: e120.")
                .replace(" protéines de lait. huiles végétales, saccharose,",
                        " protéines de lait, huiles végétales, ")
                .replace("minéraux.olorant,", "colorant,").replace("orrecteur d'acidité,", "correcteur d'acidité,")
                .replace(",olorant", ", colorant").replace("ascorbique,olorant", "ascorbique , colorant")
                .replace("jus de raisin deabern", "jus de raisin de cabern").replace(",orrecteur", ", correcteur")
                .replace("b,teraveoncentrée", "beteraveoncentrée").replace(",assis", ", cassis")
                .replace("gent d'enrobage:ire dearnauba,", "").replace("colorants:armin deochenille,", "")
                .replace(
                        "colorants: blanc: e171. turquoise: e131. e132. e171. fuschia: e120. e171.hocolat: e151. e155. e171. vert: e100. e133. e171. agents d'enrobage: e901. e903. e904. arôme naturel de vanille,",
                        "")
                .replace("agent d'enrobage:ire dearnauba,", "")
                .replace("denrées alimentairesolorantes:oncentrés de spiruline , dearthame. amidon de ble.", "")
                .replace(" colorant:aramel ordinaire. ", " colorant: caramel ordinaire. ")
                .replace("colorants:urcumine", "colorants: curcumine")
                .replace(",armins. traces éventuelles d'oeufs , de fruits àoques.", ", carmins.")
                .replace("traces éventuelles de lait , fruits àoque.", "")
                .replace("alimantaireolorante:oncentré ", "alimantaire colorante: concentré ")
                .replace("colorant:aramel ordinaire.", "colorant: caramel ordinaire.")
                .replace("amidon de pomme de terre. amidon de ble.", "amidon de pomme de terre , amidon de ble.")
                .replace(
                        "agent d'enrobage:ire dearnauba, colorants:urcumine , extrait de paprika , anthocyanes, denrées alimentairesolorantes:oncentrés de spiruline , dearthame, amidon de ble. mini sucre, sirop de glucose, eau, gélatine de porc , acidifiant: acideitrique, arôme naturel, colorant:urcumine, amidon de bleminirocodile: sirop de glucose, sucre, gélatine de porc, acidifiant: acideitrique, arômes naturels, arôme naturel d'orange, arôme naturel de citron avec autres arômes naturels, huile de tournesol, agent d'enrobage:ire dearnauba, colorants:urcumine , extrait de paprika , anthocyanes, denrées alimentairesolorantes:oncentrés de spiruline, dearthame , de pomme, amidon de ble.",
                        "")
                .replace(
                        "agent d'enrobage:ire dearnauba, colorants:urcumine ,armins ,aramel ordinaire , extrait de paprika , denrées alimentairesolorantes:oncentrés de spiruline, dearthame , de pomme. amidon de blé.",
                        "")
                .replace("préparés par le procédé de présaumurage.", "")
                .replace("préparé par le procédé de présaumurage.", "")
                .replace("42% bol,, 25%hampignon noir, 25% pleurote, 8%èpe", "champignons blancs de paris")
                .replace(
                        "fil, de poul,, sel, dextrose de maïs, jus concentrés deéleri , de b,terave jaune, extrait de levure, ail 0.1%, ferments, colorant:aramel ordinaire.",
                        "filet de poulet, sel, dextrose de maïs, jus concentrés de beterave jaune, extrait de levure, ail 0.1%, ferments,colorant : caramel ordinaire.")
                .replace(
                        "74 %uisse de poul,, eau, 6% fécule de pomme de terre, dextrose, protéines de soja, stabilisants: e452. e451 , e450. sel, exhausteur de goût: e621. , e407. sucre, arômes, antioxygène: e301 , sel nitrité, colorant: rouge de b,terave.",
                        "74 cuisse de poulet, eau, 6% fécule de pomme de terre , dextrose , protéines de soja , stabilisants: e452. e451. e450. , sel, exhausteur de goût: e621. e407, sucre, arômes, antioxygène: e301 , sel nitrité, colorant: rouge de beterave.")
                .replace("plantes aromatiques,onservateur:", "plantes aromatiques , conservateur:")
                .replace(" acidifiants: e262. e575. antioxydant: e301.",
                        " acidifiants: e262. e575 , antioxydant: e301.")
                .replace(" aromates,onservateur: ", " aromates , conservateur: ")
                .replace(",onservateur nitrite de sodiumolorantaramel ordinaire.",
                        ", conservateur nitrite de sodium, colorant caramel ordinaire.")
                .replace(" extrait d'épice. boyau naturel de porc.", " extrait d'épice, boyau naturel de porc.")
                .replace(",onservateur nitrite de sodiumolorantaramel ordinaire.", "")
                .replace("fumage au bois de hêtre.  ", "")
                .replace("e330.onservateur: e250.", "e330, conservateur: e250,").replace("porc, ,", "porc,")
                .replace("gras de porc,rème fraîche, ", "gras de porc , crème fraîche , ")
                .replace("onservateur: e250.olorant:aramel", "conservateur: e250, colorant: caramel")
                .replace("fumage au bois de hêtre. ", "").replace("sucre,onservateurs:", "sucre , conservateurs:")
                .replace(
                        " fruits àoque argopecten purpuratus , origine pérou,hlamys opercularis , origine royaume,uni: voir l,tre après le numéro de lot.",
                        " fruits à coque  ")
                .replace("onjonctif de porc,", " conjonctif de porc,").replace("ouenne de porc", " couenne de porc")
                .replace("onservateur: nitrite de sodium. peutontenir des traces d'oeuf , de soja.", "")
                .replace("delou de girofle.", "de clou de girofle.").replace(",onservateur: ", ", conservateur: ")
                .replace(" fumage au bois de hêtre", "").replace("traces de soja.", "")
                .replace(",onservateurs:", ", conservateurs:").replace(".onservateurs", ", conservateurs")
                .replace(".olorant:ochenille.", ", colorant: cochenille.")
                .replace("colorant:ochenille.", "colorant: cochenille.")
                .replace("foie de poul,,", "foie de poulet ,")
                .replace("antioxydants: e300 , e301.", "antioxydants: e300 . e301 ,")
                .replace(" sauge,iboul,te,", " sauge, ciboulete,")
                .replace("armagnac, porto. viandes de porc , deanard origine: france.",
                        "armagnac, porto, viandes de porc , de canard origine: france.")
                .replace(",onservateur.", ", conservateur.")
                .replace(" fabriqué en france à partir de viande de porc origine ue.", "").replace("di, ,", "")
                .replace(". décor:répine.", "")
                .replace("acidifiant: e325. boyau naturel de porc.", "acidifiant: e325, boyau naturel de porc.")
                .replace(" magr,s deanard", " magrés de canard").replace("nois,tes", "noisetes")
                .replace(",ognac, ", ", cognac, ")
                .replace(" antioxydant: e301.olorants: e101,e150a,e120. lactose.", "")
                .replace("porc,ouenne,", "porc, couenne,")
                .replace("antioxydants: e316 , e301.", "antioxydants: e316 . e301,")
                .replace("stabilisants: e450 , e452.", "stabilisants: e450 . e452,")
                .replace(",onservateur: e250. arômes.", ", conservateur: e250 , arômes.")
                .replace("acidifiant: e262.", "acidifiant: e262,").replace(",lous ", ", clous ")
                .replace(", ,", " , ").replace("e325. e,262,", "e325. e262 ,")
                .replace("conservateur: e250. arômes,colorant: e120.",
                        "conservateur: e250 ,  arômes , colorant: e120.")
                .replace("e250.", "e250 ,").replace("arômes,colorant", ", arômes , colorant:")
                .replace(
                        "fabriqué en france avec de la viande de porcs d'origine: union européenne issus d'élevages rigoureusement sélectionnés. sans gluten.",
                        "")
                .replace(".olorant:", ", colorant:")
                .replace("conservateurs: e250. e316. e202. stabilisants: e407. e508.",
                        "conservateurs: e250. e316. e202, stabilisants: e407. e508,")
                .replace("peau de poul,,", "peau de poulet ,").replace("viande deanard,", "viande de canard ,")
                .replace("andouill,te", "andouillete").replace("b,terave,", "beterave ,")
                .replace("deampagne", "de campagne").replace(",rème,", ", crème ,").replace("peutontenir œufs.", "")
                .replace("conservateurs: e250 , e252. ", "conservateurs: e250 . e252 , ")
                .replace("ferments.", "ferments ,").replace("fil,", "file").replace("/ou", "")
                .replace(",onservateur:", ", conservateur:").replace("peau de poul,.", "peau de poulet ,")
                .replace("tournesol•.", "tournesol").replace(", an,h,iboul,te", "").replace(".uisson", ", cuisson")
                .replace("colorant:urcumine.", "colorant: curcumine ,").replace("derev,te", "de crevete")
                .replace("deéréalesontenant", "de céréalesontenant")
                .replace("colorants:omplexesuivre,", "colorants , ")
                .replace("traces éventuelles d'autreséréalesontenant du gluten, lait,éleri, moutarde.", "")
                .replace(",éleri", ", céleri").replace("dehèvre,", "de chèvre ,")
                .replace("traces éventuelles gluten, moutarde", "")
                .replace("traces éventuelles lait, gluten, moutarde", "")
                .replace(". traces éventuelles lait, moutarde, gluten", "")
                .replace("  traces éventuelles de gluten.", "")
                .replace(
                        "badigeonnage rougecacao >51% dans lehocolat noiracao >31% dans lehocolat laitacao >26% dans lehocolat blanc",
                        "")
                .replace(".acao 30 % minimum dans lehocolat au lait.", "").replace(". ingredients ", "")
                .replace("8 %ranberries.", "8 % cranberries.").replace("°issus duommerce équitable.", "")
                .replace("extrait de vanille.acao: 55% minimum dans lehocolat noir.  produits ", "")
                .replace("éclats dearamel a", "éclats de caramel au").replace(",oncassées,", ", concassées ,")
                .replace("laitieroncentré,", " laitier concentré ,")
                .replace("peutontenir de l'œuf , d'autres fruits àoque.", "")
                .replace("peutontenir autres fruits àoque , blé.  ", "")
                .replace(". produit issu de l'agriculture", "").replace(" noix deoco.", " noix de coco ,")
                .replace(
                        " produits  ingrédientsonformes aux standards duommerce équitable fairtrade / max havelaar:acao, sucre.",
                        "")
                .replace("canneompl,,", "canne , ").replace("enacao", "en cacao")
                .replace("colorants: e120 ,e160c.", "colorants: e120 . e160c, ")
                .replace(". gomme arabique", ", gomme arabique").replace("desd'origine agricole sont", "")
                .replace("acao :37% minimum dans lehocolat au lait.", "")
                .replace("fèves de cacao provenant du programme transparenceacao.", "")
                .replace("fruits àoque,", "fruits à coque ,")
                .replace("traces éventuelles d'autres fruits àoque,éréalesontenant du gluten , d oeufs", "")
                .replace("lehocolat", "le chocolat")
                .replace(". traces éventuelles de fruits à coque , de céréalesontenant du gluten , d oeuf.", "")
                .replace("traces éventuelles d'autres fruits à coque ,éréalesontenant du gluten , d oeufs", "")
                .replace(" traces éventuelles d'autres fruits à coque ,éréalesontenant du gluten , oeufs.", "")
                .replace(",urcuma.", ", curcuma.")
                .replace("oseilleoupée.", "estragon 96 %, huiles de tournesol , deolza.")
                .replace(",iboul,te issus de l'agriculture biologique", "")
                .replace("esdee produit proviennent de diverses origines géographiques.", "")
                .replace(
                        "préparée avec 50g de fruits pour 100g. tere totale en sucres: 60g pour 1 oog aonsommer de préférere",
                        "")
                .replace(
                        "de litchis,onfiture de pamplemousses , de fruits du dragon,onfiture de mangues , de pêches,onfiture de poires , de mirabelles,onfit",
                        "")
                .replace(
                        "cideitriq préparée avec 59 g de fruitspour 100 g de prod it fini. teneur g totale en sucres 48 pour 100 g de produit peutontenir es traces de lait d",
                        "")
                .replace(
                        "acidifiant: acideitriq préparée avec 59 g de fruitspour 100 g de prod it fini. teneur g totale en sucres 48 pour 100 g de produit peutontenir es traces de lait d , d'autes fruits àoqbe.onserva inn",
                        "")
                .replace("pectines de fruits. préparée avec 50 g de fruitspour 100g de produit fini.", "")
                .replace(".ontient des écorces d'orange", "")
                .replace(" préparée avec 54 g de fruits pour 100 g de produit fini.", "")
                .replace(" préparée avec 50 g de fruitspour 100g de produit fini.", "")
                .replace("préparée avec 50 g de fruits pour 100 g.", "")
                .replace("préparée avec 41g de fruits pour 100 g de produit fini. teneur totale en sucres", "")
                .replace(
                        "acidifiant: acideitrique préparée avec 41g de fruits pour 100 g de produit fini. teneur totale en sucres 56 g pour 100 g de produit fini. traces de fruits àoque , de lait.",
                        "")
                .replace(". produits issus de l'agriculture biologique", "")
                .replace("produit élaboré dans un atelier qui utilise: lait, fruits à coque ,", "")
                .replace("préparée avec 50 g de fruits pour 100 g.", "")
                .replace("jus de citrononcentré.", "jus de citron concentré.")
                .replace(". préparée avec 50 g de fruits pour 100 g de produit fini.", "")
                .replace(
                        ". préparée avec 50g de fruits pour l00 g de produit fini. teneur totale  en sucres 1 62g pour 100 g de produit fini. ",
                        "")
                .replace(". traces éventuelles de fruits àoque , de lait.", "")
                .replace(". traces éventuelles de fruits àoque , de lait.", "")
                .replace(" teneur total en sucre environ 46.9g. sansonservateurs, sans aromes artificiels.", "")
                .replace(
                        " purée de tomates doubleoncentrée, sel, amidon modifié. oignon grillé en poudre, arôme naturel,  ",
                        " purée de tomates double concentrée , sel , amidon modifié , oignon grillé en poudre, arôme naturel,  ")
                .replace("présence d'd'origine animale: poule", "")
                .replace(". présence d'd'origine animale: poule", "")
                .replace(
                        "haricots vertsoupés 59%,arottes en rondelles 13%, dés d'aubergines grillées 11%, poivrons rouges en dés 9.3%, huile d'olive vierge extra, tomates séchées en dés 1.6%, persil, ail, eau, arômes naturels, jus de citrononcentré, sel, antioxydant: acide ascorbique, extrait naturel de romarin.",
                        "haricots vert , soupés 59% , carottes en rondelles 13% , dés d'aubergines grillées 11% , poivrons rouges en dés 9.3% , huile d'olive vierge extra , tomates séchées en dés 1.6% , persil , ail , eau , arômes naturels , jus de citron concentré , sel , antioxydant: acide ascorbique , extrait naturel de romarin.")
                .replace(" peutontenir des traces de gluten.", "")
                .replace("affermissant:hlorure decalcium ", "affermissant: chlorure de calcium ")
                .replace(" acideitrique", " acide citrique").replace(", viande de porc", "")
                .replace(". traces de moutarde.", "").replace("55 %:hou,", "55 %: hou ,").replace("s,,", ",")
                .replace("b,teraves rouges,", "beteraves rouges ,")
                .replace("p,its pois doux,", "petits pois doux ,").replace("p,its pois,", "petits pois ,")
                .replace(
                        "non raffiné de l'atlantique inqrédient d'origine aqricole obtenu selon res règles de production biologique",
                        "")
                .replace("épaississants:arraghénanes ,", "épaississants : arraghénanes ,")
                .replace(". produits ", "").replace("flageol,s,", "flageoles ,")
                .replace("viande deanard graisse deanard,", "viande de canard graisse de canard ,")
                .replace("nav,s, p,its pois", "naves, petits pois")
                .replace("p,its pois très fins", "petits pois très fins")
                .replace("foie gras deanard,", "foie gras de canard ,")
                .replace(".onservateur: e250 ,", ", conservateur: e250 ,")
                .replace(",œur de bœuf,", ", cœur de bœuf ,").replace("jeunesarottes.", "jeunes carottes , ")
                .replace(",,", " , ").replace("mog,te", "mogete").replace("p,its pois ,", "petits pois ,")
                .replace(",arottes 12.8%,", ", carottes 12.8% ,")
                .replace(",arottes 9.8%,hampignons de paris 8.6%,", ", carottes 9.8% , champignons de paris 8.6% ,")
                .replace("traces éventuelles de lait , de céréalesontenant du gluten.", "")
                .replace(",ourg,tes,", ", courgetes ,").replace(". ingrédient", "")
                .replace(" conservateur: e250 , stabilisants: e451. e452. jusuisiné: eau, sel", "")
                .replace(",oncentré de tomate 1.8%,", ", concentré de tomate 1.8% ,")
                .replace(", p,its pois 28%,", ", petits pois 28% ,")
                .replace(", pépites dehocolat 9%,", ", pépites de chocolat 9% ,")
                .replace("légumes 77 %tomates,", "légumes 77 %, tomates ,")
                .replace(",arottes 9% , p,its poids 9% ,", ", carottes 9% , petits poids 9% ,")
                .replace(
                        " les informations en gras sont destinées aux personnes allergiques ou intolérantes.acao en poudre",
                        "")
                .replace(" peutontenir: des traces de fruits àoque.", "")
                .replace("de poules élevées en plein air, quelques morceaux de", " , ")
                .replace("beurre a.o.p.harentes", "beurre  ")
                .replace("semouleomplète de blé dur", "semoule complète de blé dur")
                .replace("peutontenir des traces  d'œufs.", "").replace("quinoa,arotte,", "quinoa , carotte ,")
                .replace("conservateur: e202. antioxydant: e223. acidifiant: e330.",
                        "conservateur: e202 , antioxydant: e223 , acidifiant: e330 ,")
                .replace("épaississants: e415.onservateur: e202. antioxydant: e223. acidifiants: e330. e260",
                        "épaississants: e415 , conservateur: e202 , antioxydant: e223 , acidifiants: e330. e260 ,")
                .replace(
                        " conservateur: e202. antioxydant: e223. acidifiant: e330. maltodextrine, extrait de poireau",
                        " conservateur: e202 , antioxydant: e223 , acidifiant: e330 , maltodextrine , extrait de poireau")
                .replace("ingredientsrev,tes,", "crevetes ,").replace("crev,tes,", "crevetes ,")
                .replace("crev,tespêchées auhalut ", "crevetes pêchées au chalut ")
                .replace("crev,tes88% ", "crevetes 88% ,")
                .replace("crevetes , sel, conservateur: métabisulfite.", "")
                .replace("crevetes , sel, antioxydant: disulfite de sodium,acidifiant: acide citrique.",
                        "crevetes , sel , antioxydant: disulfite de sodium , acidifiant: acide citrique.")
                .replace("crev,tes litopenaeus vannamei, sel, conservateur: e223. acidifiant: e330",
                        "crevetes litopenaeus vannamei, sel, conservateur: e223, acidifiant: e330")
                .replace(
                        "crev,tesd'élevage d'equateur entièrement décortiquéesuites au sel de noirmoutier, sel, ferment lactique",
                        "crevetes d'élevage , sel, ferment lactique")
                .replace(", e407 , e327. e472b,", "").replace(" stabilisants: e407. e460 , e466.", "")
                .replace("épaississants: agar,agar, pectine, arôme, ferments lactiques.", "")
                .replace(
                        "stabilisants: pectine,arraghénanes,  e472e, correcteur d'acidité: e339. arôme, colorant:aroténoïdes.",
                        "")
                .replace("épaississants sin1422 / e1422. sin407 / e407. émulsifiant sin471/ e471.", "")
                .replace(
                        ", additifs alimentaires: épaississants: sin 1422 / e1422. sin 440 / e440. sin 415 / e415. sin 407 / e407.  sin 471 / e471.",
                        "")
                .replace("·  e472e · stabilisants: e440. e407 ·orrecteur d'acidité: e339 · arôme ·olorant: e160a.",
                        "  e472e , stabilisants: e440. e407 , correcteur d'acidité: e339 , arôme  , colorant: e160a.")
                .replace(" e471, e472e,", " e471. e472e,")
                .replace("lait écrémé,reme légère à 20 %  ", "lait écréme légère à 20 % ")
                .replace(" bêta,carotène.", " bêtacarotène.")
                .replace(
                        "diglycérides d'acides gras. epaississants:gomme xanthane,arraghénanes. lait d'origine france.",
                        "diglycérides d'acides gras , epaississants : gomme xanthane , arraghénanes, lait d'origine france.")
                .replace(".,", " ,").replace(",aramel aromatique", ", caramel aromatique")
                .replace(", arôme. fourrage 11.5 %: sucre.", ", arôme , fourrage 11.5 % , sucre.")
                .replace(
                        ".onseils d'utilisation: réchauffer à la poêle ou au four. a déguster nature, à laonfiture, auhocolat ou flambées. produit décongelé , ne pas recongeler",
                        "")
                .replace(" vitamine e, vitamine a, ", "").replace(", arraghénanes, colorant: b,aarotène. ", "")
                .replace(", colorants: rocou,aramel ordinaire.", "").replace("colorant:aramel e150c,", "")
                .replace("dur5.9%.issus de l'agriculture biologique", "dur 5.9% ")
                .replace(".issus de l'agriculture biologique,", "")
                .replace(".d'origine agricole obtenus selon les règles de la production   ", "")
                .replace(" huile deoco•,", " huile de coco ,")
                .replace(" épaississants e407. e412, colorant e150c", "")
                .replace("correcteur d'acidité:itrates de sodium", "correcteur d'acidité : nitrates de sodium")
                .replace("pourcentage mis en œuvre pourhaque parfum.", "")
                .replace(". ontient les sucres naturellement présents dans les fruits  ", "")
                .replace("  pommes  purée de pommes: 69.8%, purée d'abricots 30%, arômes naturels,", "")
                .replace(" vitamine,", "").replace(" issus de l'agriculture biologique", "")
                .replace("base de lait deoco spécialité fermentée à base de lait deoco,",
                        "base de lait de coco spécialité fermentée à base de lait de coco,")
                .replace(
                        ", fabriqué dans un atelier utilisant des fruitsàoques , des produits laitiers.onvient dans leadre dune alimentation équilibrée enalčum",
                        "")
                .replace(
                        "raffiné de l'atlantique.100% desd’origine agricole ont été obtenus selon les règles de production",
                        "")
                .replace(" vitamines b1. b6 , e.", "").replace("vitamines b1. b6. b9 , e,", "")
                .replace("arôme vanille. sach, d'éclats dearamel 4g.",
                        "arôme vanille, sach, d'éclats de caramel 4g.")
                .replace(". , produits  ", "").replace(",aramel 8.1%,", ", caramel 8.1%,")
                .replace(",rème pasteurisée.", "crème pasteurisée.").replace("île flottante: ", "")
                .replace(", correcteur d'acidité.", "")
                .replace(
                        ".orrecteur d'acidité: e330. antioxydant: e300. ganache auhocolat 27 %:rème,hocolat noir 33 %,acao 1.3 %.",
                        "")
                .replace(",aramel,", ", caramel ,")
                .replace(".  lait ,rème origine: france.", ",  lait crème origine: france.")
                .replace(". les ferments sontultivées sur des bases végétales", "")
                .replace(", jus de citrononcentré,", ", jus de citron concentré ,")
                .replace("specialite de pommmehassion sans sucres ajoutls ngredients:", "")
                .replace("sans gluten.", "").replace(", jus de citrononcentré,", ", jus de citron concentré ,")
                .replace("spécialité de fruits", "")
                .replace("pomme fraise sans sucres ajoutés&quot", "pomme , fraise ")
                .replace("antioxydant: acide ascorbique. pomme pomme 74.9 %, poire 24.9 %, arôme naturel,",
                        "antioxydant: acide ascorbique, pomme 74.9 % , poire 24.9 % , arôme naturel ,")
                .replace(",alimentairesolorants,", " , ")
                .replace(" jus de citrononcentré", " jus de citron concentré")
                .replace(" arôme. fines feuilles de cacao: pâte de cacao, beurre de cacao, arôme, édulcorants.",
                        " arôme , fines feuilles de cacao: pâte de cacao, beurre de cacao, arôme, édulcorants.")
                .replace(", edulcorants ,acesulfame k,yclamates, neohesperidine dc.", "")
                .replace(" correcteur d'acidité: hydroxyde de sodium.", "")
                .replace(" épaississant: e407. minéraux du lait,", " épaississant: e407 , minéraux du lait,")
                .replace(
                        "sucrée,ube de pomme semi,confit,ube de fraise semi,confite 31 %,ompotée de rhubarbe 26.5 %, ",
                        "sucréecube de pomme semi, confitube de fraise semi , confite 31 % , compotée de rhubarbe 26.5 %, ")
                .replace("pêches bio,erises bio", "pêches bio , cerises bio").replace("issu de l'agriculture ", "")
                .replace("  origine: france.", "")
                .replace("gélifiants: e407 , e415. sel", "gélifiants: e407 . e415 , sel")
                .replace(",itron 10 %,", ", citron 10 % ,")
                .replace("lait deoco, eau deoco", "lait de coco, eau de coco")
                .replace("lait deoco. eau deoco,", "lait de coco, eau de coco,")
                .replace("sucre de cannerème3 %,", "sucre de canne , crème3 %,")
                .replace(",hocolat en poudre 10%,", ", chocolat en poudre 10% ,")
                .replace(" 'issus de l'agriculture biologique", "").replace("amidon.rème", "amidon crème")
                .replace(
                        "chlorurçs silice: 7.5 mg/l, résidu sec à 1800c: 32mgli , ph: 618 résistivité: 30 000 q.cm , rh2 25 95 ",
                        " eau ")
                .replace(".ertains ingrédient dee produit ne proviennent pas de france.", "")
                .replace("arômeitron vert", "arôme citron vert")
                .replace(
                        ". la fécule de pomme de terreontribue à améliorer la qualité de votre emmental rapé président, en limitant la formation de mottes pour un saupoudrage idéal. afinage minimum 50 jours. aonserver entre +4°c , +8°c. afin de mieux profiter de toute la saveur , du moelleux de votre emmental président, nous vousonseillons de leonsommer dans les 6 jours suivant l'ouverture du sach,",
                        "")
                .replace(",oagulant.", ", coagulant.").replace(" affinage minimum 50 jours.", "")
                .replace("laitru,", "lait cru,")
                .replace(" présure lait issu de l'aire géographique définie par l'igp.", "")
                .replace("avec autres arômes naturels 4.4 %, ", "")
                .replace(
                        ". aonsommer de préférence avant le / numéro de lot: voirouvercle. aonserver à l'abri de lahaleur, de la lumière , de l'humidité. après ouverture, àonserver dans son liquide au réfrigérateur une semaine maximum. poids n, égoutté: poids n,: 700g 390g",
                        "")
                .replace(
                        "n oivron de tournesol pts paprika%0booriandrild,ecéleri oivr , t éventuellesdeblé,loit, outdt inforut10nsiàconsommer qregiçons é iii il i i iii iii 3 250391 967827",
                        ", tournesol ,")
                .replace(",arottes en morceaux,", ", carottes en morceaux ,")
                .replace("poivreissus de l’agriculture biologique ", "").replace(",arottes,", ", carottes ,")
                .replace(
                        " acidifiant: e330. moutarde de dijon, conservateur: e202. épaississant: e415. poivre blanc.",
                        " acidifiant: e330 , moutarde de dijon, conservateur: e202 , épaississant: e415 , poivre blanc.")
                .replace("deconcom8ht5itron à base deoncentr'é,rème fraîche, {l,", "")
                .replace(" piment d'espel,te ,", " piment d'espelete,")
                .replace("mi,réduite 56.7%", "mie réduite 56.7%")
                .replace("real issu duommerce équitable de bolivie , de l'agriculture ", "")
                .replace(", issue de l'agriculture ", "")
                .replace("ingrédient: épeautreompl,a issude l'agriculture ", "épeautre")
                .replace(",rème pasteurisés,", "crème pasteurisés,").replace(" lactiques.des épices ", "")
                .replace(",rème de lait,", "crème de lait ,")

                /* =============Dimitri 3_351-6_700===================================== */
                .replace("arôme naturel de vanille.", "arôme naturel de vanille,")
                .replace("crème, poudre de babeurre, gélifiants: e407 et e415. sel.",
                        "crème, poudre de babeurre, gélifiants: e407 et e415, sel")
                .replace("noix de coco râpée 1%, arômes naturels. jus concentré de citron,",
                        "noix de coco râpée 1%, arômes naturels: jus concentré de citron,")
                .replace("citron: jus de soja 78 %,", "jus de soja 78 %,")
                .replace(
                        "ferments sélectionnés. conforme à la norme nf v 29,001. graines de soja filière française garanties sans ogm.",
                        "ferments sélectionnés. conforme à la norme nf v 29.001, graines de soja filière française garanties sans ogm.")
                .replace(
                        "jus de soja 85%, sucre, épaississants: amidon transformé de maïs sans ogm , , carraghénanes pectines , gomme guar, phosphate de calcium, concentrés végétaux, arôme naturel, sel. soja filière française, garanti sans ogm.",
                        "jus de soja 85%. soja filière française, garanti sans ogm, sucre, épaississants: amidon transformé de maïs sans ogm, carraghénanes pectines , gomme guar, phosphate de calcium, concentrés végétaux, arôme naturel, sel")
                .replace("colorant: extrait de paprika, vitamine d.", "colorant: extrait de paprika")// ?
                .replace(
                        "jus de soja1 86%, sucre de canne 9.5%, amidon, arômes naturels de vanille, concentrés de carotte et de pomme, épaississant: carraghénanes, sel.  issu de l’agriculture biologique 1soja sans utilisation d’ogm conformément à la réglementation en vigueur sur le mode de production biologique.",
                        "jus de soja1 86% . issu de l’agriculture biologique, sucre de canne 9.5%, amidon, arômes naturels de vanille, concentrés de carotte et de pomme, épaississant: carraghénanes, sel")
                .replace(
                        "tonyu 74.7 %, sucre 11.6 %, framboise 10 %, amidon modifié, phosphate de calcium, jus concentré de carotte pourpre, arôme naturel, sel, ferments, vitamine d.",
                        "tonyu 74.7 %, sucre 11.6 %, framboise 10 %, amidon modifié, phosphate de calcium, jus concentré de carotte pourpre, arôme naturel, sel, ferments.")// ?
                .replace("sel, ferments, vitamine d2.", "sel, ferments")// ?
                .replace(
                        "jus de soja1 77%. issu de l’agriculture biologique, sucre de canne, poudre de cacao maigre, chocolat 1.5%, chocolat en poudre 1.5%, amidon, épaississant: carraghénanes, sel, arôme naturel.  issu de l’agriculture biologique 1soja sans utilisation d’ogm conformément à la réglementation en vigueur sur le mode de production biologique.",
                        "jus de soja1 77%, sucre de canne, poudre de cacao maigre, chocolat 1.5%, chocolat en poudre 1.5%, amidon, épaississant: carraghénanes, sel, arôme naturel ")
                .replace(" café lyophilisé 0.2 %, sel, vitamine d2.", " café lyophilisé 0.2 %, sel")// ?
                .replace(
                        "jus de soja  79%, sucre de canne 9.4%, purée d’abricot 5%, amidon, jus concentré de carotte, épaississant: pectines, arôme naturel, sel, ferments.  issu de l’agriculture biologique soja sans utilisation d’ogm conformément à la réglementation en vigueur sur le mode de production biologique.",
                        "jus de soja  79%. issu de l’agriculture biologique, sucre de canne 9.4%, purée d’abricot 5%, amidon, jus concentré de carotte, épaississant: pectines, arôme naturel, sel, ferments. ")
                .replace(
                        "jus de soja 80%, sucre de canne 9.9%, citron 3.9%, amidon, sel, épaississant: pectines, arôme naturel, ferments.  issu de l'agriculture biologique / soja sans utilisation d'ogm conformément à la réglementation en vigueur sur le mode de production biologique. traces éventuelles de fruits à coque.",
                        "jus de soja 80%. issu de l'agriculture biologique, sucre de canne 9.9%, citron 3.9%, amidon, sel, épaississant: pectines, arôme naturel, ferments")
                .replace(
                        "morceaux d’abricot: jus de soja 75.1%, sucre 11%, abricot 10%, amidon, amidon modifié, phosphate de calcium, épaississants: pectine – gomme guar, sel, arôme naturel, ferments, colorant: extrait de paprika, vitamine d2. morceaux de pêche: jus de soja 75.1%, sucre 11.9%, pêche 10%, amidon, amidon modifié, phosphate de calcium, arôme naturel, concentrés de carotte, épaississants: pectine – gomme guar, sel, ferments, vitamine d2. morceaux d’ananas: jus de soja 71.5%, sucre 12.7%, ananas 10%, amidon, phosphate de calcium, amidon modifié, arôme naturel, épaississants: pectine – gomme guar, sel, ferments, concentré de carthame, vitamine d2.",
                        "morceaux d’abricot: jus de soja 75.1%, sucre 11%, abricot 10%, amidon, amidon modifié, phosphate de calcium, épaississants: pectine – gomme guar, sel, arôme naturel, ferments, colorant: extrait de paprika, vitamine d2. morceaux de pêche: jus de soja 75.1%, sucre 11.9%, pêche 10%, amidon, amidon modifié, phosphate de calcium, arôme naturel, concentrés de carotte, épaississants: pectine – gomme guar, sel, ferments, morceaux d’ananas: jus de soja 71.5%, sucre 12.7%, ananas 10%, amidon, phosphate de calcium, amidon modifié, arôme naturel, épaississants: pectine – gomme guar, sel, ferments, concentré de carthame")
                .replace(" amidon de maïs. amidon de pomme de terre", " amidon de maïs, amidon de pomme de terre")
                .replace("vitamine d. traces éventuelles de fruits à coque.",
                        "traces éventuelles de fruits à coque")
                .replace("amidon de maïs et amidon transformé de tapioca,",
                        "amidon de maïs, amidon transformé de tapioca,")
                .replace("fraise: ", "").replace("framboise: ", "")
                .replace("concentré de carotte et de cassis", "concentré de carotte, concentré de cassis")
                .replace("pêches: ", "")
                .replace("les ingrédients de ces produits ne proviennent pas de france.", "")
                .replace(" acide ascorbique. non", " acide ascorbique")
                .replace(". contient plus de 85% d'humidité.", ", contient plus de 85% d'humidité.")
                .replace("pomme: ", "").replace("poire: ", "").replace("banane: ", "").replace("abricots:", "")
                .replace(".  ingrédients issus de l'agriculture biologique", "")
                .replace("protéines de lait.traces éventuelles de soja.dessert",
                        "protéines de lait. traces éventuelles de soja, dessert")
                .replace("oui= traces éventuelles de soja pour la référence chocolat", "")
                .replace(". les ingrédients de ces produits ne proviennent pas de france.", "")
                .replace(". ingrédients issus de i'agriculture biologique.", "")
                .replace("pomme pêche abricot + acérola:", "")
                .replace("colorant: caroténoïdes • ferments lactiques.",
                        "colorant: caroténoïdes, ferments lactiques,")
                .replace("vitamine d. peut contenir des traces d'autres céréales contenant du gluten.", "")
                .replace("agar agar ingredients agricoles issus de l'agriculture biologique", "")
                .replace(", et", ", ").replace("bio = issu de l'agriculture biologique", "")
                .replace(
                        "lait entier biochocolat en poudre bio 10.9 % sucre de canne bio crème biopoudre de lait écrémé bio amidon bio",
                        "lait entier, biochocolat en poudre bio 10.9 %, sucre de canne bio, crème biopoudre de lait écrémé bio, amidon bio")
                .replace("épaississant e407. gaz propulseur e942.", "épaississant e407, gaz propulseur e942.")
                .replace("lactose et minéraux du lait", "lactose, minéraux du lait")
                .replace("épaississant: e407. protéines de lait", "épaississant: e407, protéines de lait")
                .replace(", vitamine d.", "").replace(", ingrédients caramel:", "")
                .replace("ingrédients chocolat:", "")
                .replace(
                        "lait entier 79.7% 6—  sucre blond de canne , chocolat en poudre 5 %,  , amidon de manioc — épaississant: pectine.  ingrédients d'origine agricole issus de l'agriculture biologique",
                        "lait entier 79.7%,  sucre blond de canne , chocolat en poudre 5 %, amidon de manioc, épaississant: pectine.")
                .replace("sel.", "sel").replace("vitamine d2.", "").replace(". traces éventuelles de soja", "")
                .replace(
                        "ingredients c tea desemouamidon transformé.épaississant: gomme xanthane semolina cake te de cacao, su ",
                        "thé, amidon, épaississant: gomme xanthane, gateau de cacao, sucre")
                .replace("(p", ", p")
                .replace("émulsifiants: e472b, e471. stabilisants: e407. e412.",
                        "émulsifiants: e472b. e471, stabilisants: e407. e412,")
                .replace("colorants: e100. e120. e131. ", "colorants: e100. e120. e131,")
                .replace("acésulfame,k.", "acésulfame k.").replace("citron et citron vert", "citron , citron vert")
                .replace("saint,pierre", "saint pierre")
                .replace("édulcorants: sucralose et acésulfame,k.", "édulcorants: sucralose , acésulfame k.")
                .replace("édulcorants: sucralose et acésulfame k.", "édulcorants: sucralose , acésulfame k.")
                .replace("acidifiant: e330.  conservateurs: e202. e242.",
                        "acidifiant: e330 ,  conservateurs: e202. e242.")
                .replace("saint,georges 1.", "saint georges")
                .replace(
                        "chlorurçs silice: 7.5 mg/l résidu sec à 1800c: 32mgli , ph: 618 résistivité: 30 000 q.cm , rh2 25 95  • i sur la boulell\\e.",
                        "chlorurçs silice: 7.5 mg/l, résidu sec à 1800c: 32mgli , ph: 618 résistivité: 30 000 q.cm , rh2 25 95 ")
                .replace(". origine: pradesfrance.", ", origine: pradesfrance.").replace(". non", "")
                .replace("co2. ", "co2, ")
                .replace("certains ingrédient de ce produit ne proviennent pas de france.", "")
                .replace("eau minérale naturelle naturellement gazeuse",
                        "eau minérale naturelle, naturellement gazeuse")
                .replace(". colorant:", ", colorant:").replace("présure.", "présure")
                .replace("arôme naturel d'épices et autres arômes naturels.",
                        "arôme naturel d'épices , autres arômes naturels.")
                .replace(".  biologique. peut contenir des traces de lait, de moutarde et de céleri.",
                        ",  biologique.")
                .replace(
                        "n oivron de tournesol pts paprika%0bo coriandrild,ecéleri oivr , t éventuellesdeblé,loit, outdt inforut10nsiàconsommer qregiç cons é iii il i i iii iii 3 250391 967827",
                        "poivron de tournesol , paprika , coriandre, céleri , poivre , traces éventuels de blé , lait")
                .replace("ingrédient issu de l'agriculture biologique.", "")
                .replace(" les informations en gras sont destinées aux personnes intolérantes ou allergiques.", "")
                .replace("  traces éventuelles de graines de sésame.", "")
                .replace("traces éventuelles de céréales contenant du gluten et de céleri.", "")
                .replace(".ingrédient issu de l agriculture biologique.", "")
                .replace("penicilium roqueforti et chlorure de calcium.",
                        "penicilium roqueforti , chlorure de calcium.")
                .replace("sels de fonte: e452. e450.", "sels de fonte: e452. e450,")
                .replace("ingrédients:iait (98%}, sel , ferments lactiques,  coagulant",
                        "lait 98%, sel , ferments lactiques,  coagulant")
                .replace("vitamines: a, b2. b12", "").replace("ingrédient issu de l'agriculture biologiq, ue.", "")
                .replace(". confiture artisanale.", ", confiture artisanale.")
                .replace(
                        "asteurized milk, pareurzec cream, pepper 5%, sait, best before: see original label refrigerated between +2'<, , 71,c , ?eüed in protective atmosphere.to ee within 3 days after opening. ingrediënten: gepasteuriseerde melk gepasteuriseerde room, peoc:r melkkiemen, strernsel. , vekpa'gt cn beschermende atmofeer. dagen na het. zutaten: pasteurisierter frischt<éië 'milch pfeffer 596. salz. mindenstens 72: fett i. tr. unter verpacvt. geniessen sie von 3 tagen nach dem otinen.",
                        "lait, poivron ,creme ")
                .replace("ingrédients des épices et aromates ::", ",").replace("e160b, e120.", "e160b. e120,")
                .replace("colorants: e160b, e120", "colorants: e160b. e120")
                .replace("conseils de conservation: avant ouverture: craint la chaleur et l'humidité.", "")
                .replace(
                        "peut contenir des traces de gluten, arachides, soja, lait, autres fruits à coque et céleri.",
                        "")
                .replace(
                        "traces éventuelles d'arachide, de fruits a coque, de graines de sésame, de céréales contenant du gluten, d'anhydride sulfureux et de soja.",
                        "")
                .replace(
                        "traces éventuelles fabriqué dans un atelier utilisant du gluten, des arachides, du soja, du lait et d'autres fruits à coque.",
                        "")
                .replace(
                        "traces éventuelles de céréales contenant du gluten, autres fruits à coque, arachides et soja.",
                        "")
                .replace("origine bolivie", "").replace("émulsifiants:", "")
                .replace("et morceaux de cookies au chocolat 4 %.", ", morceaux de cookies au chocolat 4 %.")
                .replace("émulsifiant:", "").replace("enrobage ananas:", "")
                .replace("poudres à lever: e450. e500.", "poudres à lever: e450. e500,")
                .replace("de soja et de graines de sésame", "de soja , de graines de sésame")
                .replace(", (noix ", ", noix ").replace("(lait", ",lait")
                .replace("cannelle oeuf entier", "cannelle, oeuf, entier,")
                .replace("beurre. caramel", "beurre, caramel").replace("mono,e", "mono")
                .replace(", blanc d'œuf.", ", blanc d'œuf,")
                .replace(
                        "a consomme e pré ance avantlu dele ir liquée sur le côté du paquet. co orve dans u eg frais ei sec, à abri de lumière.",
                        "")
                .replace("issu d'animaux sans traitement antibiotique dès la fin du sevrage et nourris sans ogm",
                        "")
                .replace("frais issu d'animaux nourris sans ogm", "")
                .replace(". antioxydant: e316.", ", antioxydant: e316,")
                .replace("antioxydant: e316.", "antioxydant: e316,")
                .replace("stabilisants: e450,e451,e452.", "stabilisants: e450. e451. e452,")
                .replace("exhausteur de goût: e621. acidifiant: e262.",
                        "exhausteur de goût: e621, acidifiant: e262.")
                .replace("biologique.", "")
                .replace(
                        " l'action des ferments génère l'apparition de nitrites d'origine végétale pour préserver toutes les qualités du produit. ce mode d'élevage garantit le bien,être de l'animal durant toute sa croissance et une alimentation variée à base de céréales.",
                        "")
                .replace(
                        "l'action des ferments génère l'apparition de nitrites d'origine végétale pour préserver toutes les qualités du produit.",
                        "")
                .replace(
                        "la matière première jambon ainsi que les épices et les plantes aromatiques ont été obtenues selon le mode de production de l'agriculture biologique.",
                        "")
                .replace("antioxydant: e316.", "antioxydant: e316,")
                .replace("antioxydant: e316. conservateur: e250", "antioxydant: e316 , conservateur: e250")
                .replace("conservateur: e250. antioxydant: e316. stabilisant: e451.",
                        "conservateur: e250, antioxydant: e316, stabilisant: e451,")
                .replace("peut contenir des traces de lait et de moutarde.", "")
                .replace("séché et affiné dans le bassin de l'adour", "")
                .replace("ascorbate de sodium. chorizo:", "ascorbate de sodium,")
                .replace("conservateur: e252. préparations aromatisantes.", "conservateur: e252,")
                .replace(" vitamine c", "").replace(" vitamines: c, b9.", "").replace("vitamine c, vitamine b9", "")
                .replace("vitamine: c, b9", "")
                .replace(" les oranges de ce produit ne proviennent pas de france.", "")
                .replace(". biologique.", ", biologique.")
                .replace(". } ingrédients issus de l'agriculture biologique", "")
                .replace("ce jus est sans additif, et sans conservateur, conformément à la réglementation.", "")
                .replace("abricot 20.4% et kiwi 4.8%.", "abricot 20.4% , kiwi 4.8%.")
                .replace("ingrédient issu de l'agriculture biologique", "")
                .replace("vitamines c, e, b1. b9 et provitamine a.", "")
                .replace(" une cuillère de purée de pomme pour la texture et une pointe de gingembre.",
                        "  purée de pomme  , pointe de gingembre.")
                .replace("poudres à lever:", "").replace("issu d'animaux nourris sans ogm < 0.9 %", "")
                .replace(
                        "huile vierge de twrnesot' 35 huile d'olive vierge extra' 5 hui!e nierge cie tournesol citron 0.35 oléiquei arôme rot ingrédients issus de itogç biologique.",
                        "huile vierge de tournesot, 35 huile d'olive vierge extra 5 huile, citron 0.35")
                .replace("vitamines: b1. b2. b3. b5. b6. b8. b9. c, e, pro,vitamine a.", "")
                .replace(", gluten de blé ·", ", gluten de blé ,")
                .replace("vinaigre de cidre ·", "vinaigre de cidre ,").replace("farine d'orge• ", "farine d'orge, ")
                .replace("émulsifiant e471.", "émulsifiant e471,")
                .replace(
                        "teneurs pour / durchschnittliche nàhrterte pro / gemiddeide v oedingswaarden per i av erage nutritional value per 100g de pain cuit: energie / energetyaarde / energy 1073kj ,",
                        "farine complète de blé, son d'avoine")
                .replace("farine de blé type 65. eau, farine d’épeautre type 110. levain,",
                        "farine de blé type 65, eau, farine d’épeautre type 110, levain,")
                .replace("blé/", "blé")
                .replace(
                        "farine de blé 54 %/, eau, huile de colza. levure, sucre de canne roux, graines de tournesol décortiquées 3.3 %. ",
                        "farine de blé 54 %, eau, huile de colza. levure, sucre de canne roux, graines de tournesol décortiquées 3.3 %, ")
                .replace("farine de blé t65.", "farine de blé t65,")
                .replace(" conservateur: e282.", " conservateur: e282,")
                .replace("ingrédients/ingredientes/ingredient", "")
                .replace(", et diglycérides d'acides gras traces éventuelles de lait et de fruits à coque.",
                        ",  diglycérides d'acides gras traces éventuelles de lait , de fruits à coque.")
                .replace(".  ingrédients issus de l'agriculture biologique.", "")
                .replace(
                        "inghédients & nutrition mélange équilibré pour la préparation de pain de campagne ingrédients:",
                        "")

                /* =============Remi 6_701-10_050======================================== */

                .replace("maïs 70%, sucre 28 %, extrait de malt d'orge, sel.",
                        "maïs 70%, sucre 28 %, extrait de malt d'orge, sel,")
                .replace(
                        "sirop de malt d'orge bio, arôme naturel d'abricot avec autres arômes naturels,  sel de mer, émulsiﬁant: lécithines de tournesol",
                        "sirop de malt d'orge bio, arôme naturel d'abricot avec autres arômes naturels,  sel de mer, émulsiﬁant: lécithines de tournesol.")
                .replace(" préparation à base d'huile végétale, sel, vitamines: niacine, e, b6.",
                        " préparation à base d'huile végétale, sel")
                .replace(
                        "colorant: caroténoïdes, antiagglomérant carbonate de calcium, émulsifiant lécithines de tournesol, arôme, antioxydant tocophérols.",
                        "colorant: caroténoïdes, antiagglomérant carbonate de calcium, émulsifiant lécithines de tournesol, arôme, antioxydant tocophérols,")
                .replace(
                        "préparation à base d'huile végétale, sel, vitamines: niacine, e, b6, riboflavine, thiamine, acide folique, b12. fer.",
                        "préparation à base d'huile végétale, sel, vitamines: niacine, e, b6, riboflavine, thiamine, acide folique, b12, fer.")
                .replace(
                        "émulsifiant: mono, et diglycérides d'acides gras, vitamines c, niacine, acide pantothénique, b6. riboflavine",
                        "émulsifiant: mono, et diglycérides d'acides gras, vitamines c, niacine, acide pantothénique, b6, riboflavine")
                .replace(
                        "graines de lin brin 11%, fruits secs, sucre de coco 6.3%, huile de coco 6.3%, noix de coco 2.1%, sel, poudre de vanille.:",
                        "graines de lin brin 11%, fruits secs, sucre de coco 6.3%, huile de coco 6.3%, noix de coco 2.1%, sel, poudre de vanille:")
                .replace(
                        "farine de blé sucre: huile de palme, lait en poudre écrémé, poudres à lever: e503. e500. e450. sel,",
                        "farine de blé sucre: huile de palme, lait en poudre écrémé, poudres à lever: e503, e500, e450, sel,")
                .replace("vitamines: c, niacine, b6. riboflavine, thiamine, acide folique, b12.", "")
                .replace(
                        "flocons d'épeautre 45%, flocons d'avoine, abricots 8%, graines de tournesol, pruneaux 7%, pommes 6%, amandes 5%, noix de pécan.",
                        "flocons d'épeautre 45%, flocons d'avoine, abricots 8%, graines de tournesol, pruneaux 7%, pommes 6%, amandes 5%, noix de pécan,")
                .replace(
                        "flocons d'avoine complet 35%, sucre, flocons de blé complet“ 13%, farine de blé 11.9%, chocolat noir 11%, huile de tournesol, farine de riz, noix de coco en poudre, cacao en poudre 1.6%, miel, sel, extrait de malt d'orge, émulsifiant: lécithines de tournesol, antioxydant: extrait riche en tocophérol",
                        "flocons d'avoine complet 35%, sucre, flocons de blé complet“ 13%, farine de blé 11.9%, chocolat noir 11%, huile de tournesol, farine de riz, noix de coco en poudre, cacao en poudre 1.6%, miel, sel, extrait de malt d'orge, émulsifiant: lécithines de tournesol, antioxydant: extrait riche en tocophérol.")
                .replace(
                        "céréales complètes 67.4%, caramel 20.4% crème caramel au beurre salé 11.6%, caramel 8.8%, huile de tournesol, sucre de canne, sel de mer // ingrédients issus de l’agriculture biologique",
                        "céréales complètes 67.4%, caramel 20.4% crème caramel au beurre salé 11.6%, caramel 8.8%, huile de tournesol, sucre de canne, sel de mer // ingrédients issus de l’agriculture biologique.")
                .replace(
                        "superfruits 9.3 % cranberries 4.3 %, jus concentré de grenade 3.8 %, cassis lyophilisés 0.6%, myrtilles lyophilisées 0.6 %",
                        "superfruits 9.3 % cranberries 4.3 %, jus concentré de grenade 3.8 %, cassis lyophilisés 0.6%, myrtilles lyophilisées 0.6 %.")
                .replace(
                        "flocons d'avoine 54%, sucre de canne complet, huile de tournesol, sirop de riz, billettes de riz, amandes 4%, tapioca",
                        "flocons d'avoine 54%, sucre de canne complet, huile de tournesol, sirop de riz, billettes de riz, amandes 4%, tapioca.")
                .replace("sarrasin 99%, sel", "sarrasin 99%, sel.")
                .replace(" noisettes toastées, pommes séchées 1%, noix de coco séchées 1%, figues séchées",
                        " noisettes toastées, pommes séchées 1%, noix de coco séchées 1%, figues séchées.")
                .replace("millet", "millet.")
                .replace(
                        "sucre farine de blé oeuf sirop de glucose fructose graisses végétaleslait entier concentré sucré 8.5%humectantbeurre de cacao lait écrémé en poudre 3.2%pâte de cacao lactoserum en poudre de lait beurre concentré émulsifiantalcool sel poudre à leverarômes amidon de froment  peut contenir fruit à coque",
                        "sucre , farine de blé, oeuf ,sirop de glucose, fructose ,graisses végétales,lait entier  concentré sucré 8.5% ,humectant , beurre de cacao lait écrémé en poudre 3.2% , pâte de cacao , lactoserum en poudre de lait , beurre , concentré émulsifiant , alcool, sel, poudre à lever , arômes amidon de froment  peut contenir fruit à coque")
                .replace("noix de coco toastée en lamelles 4%. produits issus de l'agriculture biologique",
                        "noix de coco toastée en lamelles 4%.")
                .replace("flocons d'avoine complets", "flocons d'avoine complets.")
                .replace("flocons de blé, flocons d'orge· pépites de chocolat noir 10%,",
                        "flocons de blé, flocons d'orge, pépites de chocolat noir 10%,")
                .replace("ingrédients issus de l'agriculture biologique.", "")
                .replace(
                        "céréales 65.2%, sucre de canne, huile de tournesol, noix de coco, sirop de riz. ingrédients issus de l’agriculture biologique.",
                        "céréales 65.2%, sucre de canne, huile de tournesol, noix de coco, sirop de riz,")
                .replace(
                        "sucre de canne, huile de tournesol, morceaux de chocolat noir 5.1 %, sirop de riz, cacao maigre en poudre. ingrédients issus de l’agriculture biologique.",
                        "sucre de canne, huile de tournesol, morceaux de chocolat noir 5.1 %, sirop de riz, cacao maigre en poudre.")
                .replace(
                        " chocolat 3.9 % (sucre de canne sirop de malt d'orge , sucre de canne, sirop de blé, pâte de cacao , beurre de cacao,",
                        " chocolat 3.9 % , sucre de canne , sirop de malt d'orge , sucre de canne, sirop de blé, pâte de cacao , beurre de cacao,")
                .replace(
                        "sarrasin, datte, noisette 5%, éclats de fève de cacao cru 3.9%, poudre de cacao cru 1.6%, poudre de caroube crue, vanille",
                        "sarrasin, datte, noisette 5%, éclats de fève de cacao cru 3.9%, poudre de cacao cru 1.6%, poudre de caroube crue, vanille.")
                .replace(
                        "fibre de guar, arôme naturel, farine de seigle.  ingrédients d'origine agricole obtenus selon le mode de production biologique.",
                        "fibre de guar, arôme naturel, farine de seigle.")
                .replace(
                        "blancs d’œufs frais arômes naturels, sel, émulsifiants, sirop de sucre inverti, protéines de lait, arôme, levure désactivée, colorant.",
                        "blancs d’œufs frais ,arômes naturels, sel, émulsifiants, sirop de sucre inverti, protéines de lait, arôme, levure désactivée, colorant.")
                .replace(
                        "farine de blé 1.7 %, farine de riz, chocolat au lait 0.7 %, farine de soja, miel. ingrédients agricoles issus de l'agriculture biologique.",
                        "farine de blé 1.7 %, farine de riz, chocolat au lait 0.7 %, farine de soja, miel.")
                .replace(
                        "poudre de cacao maigre 2.2%, sirop d'épeautre.ingrédients agricoles issus de l'agriculture biologique.",
                        "poudre de cacao maigre 2.2%, sirop d'épeautre.")
                .replace(
                        "sucre de canne, huile de palme non hydrogénée, sirop de riz, billettes de céréales 4.1 %, farine de blé",
                        "sucre de canne, huile de palme non hydrogénée, sirop de riz, billettes de céréales 4.1 %, farine de blé.")
                .replace(
                        "diglycérides d'acides gras, vitamines: thiamine, riboflavine, b3. acide pantothénique, b6. b8. acide folique, b12. fer.",
                        "diglycérides d'acides gras, fer.")
                .replace(
                        ", vitamines: niacine, vitamine e, acide pantothénique, vitamine b6. riboflavine, thiamine, acide folique, vitamine b12 diphosphate ferrique",
                        "")
                .replace("vitamines: niacine, b6. riboflavine, thiamine, acide folique, b12.", "")
                .replace(
                        "céréales complètesblé complet, farine de blé complet, fruits secsraisins secs 10.9 %, bananes sucrées aromatisées 8.6 %, noix de coco 3.1 %, pommes 2.3 %, noisettes 2.3 %, sucre, extrait de malt d'orge, sel, vitamines: niacine, acide pantothénique, b6. riboflavine, thiamine, acide folique, biotine, b12. fer, arôme.",
                        "céréales complètes, blé complet, farine de blé complet, fruits secs, raisins secs 10.9 %, bananes sucrées aromatisées 8.6 %, noix de coco 3.1 %, pommes 2.3 %, noisettes 2.3 %, sucre, extrait de malt d'orge, sel, fer, arôme.")
                .replace("caramel, sirop de sucre caramélisé , vitamines: bl, b2. b3. b5. b6. b9. b8. bl2",
                        "caramel, sirop de sucre caramélisé ")
                .replace(
                        "dérivé du lactosérum riche en calcium, vitamines: thiamine, riboflavine, b3. b6. acide folique, b12. e,",
                        "dérivé du lactosérum riche en calcium,")
                .replace(
                        ", vitamines: niacine, e, b6. riboflavine, thiamine, acide folique, b12 , fer. traces d'arachides, de soja, d'autres fruits à coque et de graines de sésame",
                        "")
                .replace(
                        "vitamines: niacine, acide pantothénique, riboflavine, b6. thiamine, acide folique, biotine, b12 , poudre à lever: carbonate acide de sodium , protéines de lait , fer. traces d'arachide, soja, fruits à coque et graines de sésame.",
                        " poudre à lever: carbonate acide de sodium , protéines de lait , fer.")
                .replace(
                        "céréales 61.5 %1% farine complete de segle 1%,pepite de chocolat 3%,sucre, huile de colza,pate de cacao, huiles végétalesdextrose beurre de cacao émulsifiant, noisettes 2.5 %, miel 2%, minérauxpoudre à lever,, sel correcteur d acidité, emulsifiant, aromes.",
                        "céréales 61.5%, farine complete de segle 1%,pepite de chocolat 3%,sucre, huile de colza,pate de cacao, huiles végétales,dextrose ,beurre de cacao, émulsifiant, noisettes 2.5 %, miel 2%, minéraux ,poudre à lever, sel ,correcteur d acidité, emulsifiant, aromes.")
                .replace(
                        "céréales,   pâte à tartiner à la noisette du lot et garenne et au cacaosucre, noisettes du lot et garonne, poudre de lait, huile de coco, poudre de cacao, huile de colza, émulsiﬁant: lécithine de tournesol, arôme: vanille, chocolat en poudre, son de blé, sucre, sel, émulsifiant: mono, et diglycérldes d’acides gras, vitamines, b6. riboflavine, thiamine, acide folique, b12 et fer. , % dans le produit fini",
                        "céréales, pâte à tartiner à la noisette du lot et garonne et au cacao, sucre, noisettes du lot et garonne, poudre de lait, huile de coco, poudre de cacao, huile de colza, émulsiﬁant: lécithine de tournesol, arôme: vanille, chocolat en poudre, son de blé, sucre, sel, émulsifiant: mono, et diglycérldes d’acides gras, fer.")
                .replace(
                        "flocons d’avoine, farine de blé— graisse végétale— sucre — pépites de cranberries, sucre, humectant: glycérine, correcteur d'acidité: acide citrique, huile de tournesol, sirop de sucre inverti — billettes de riz soufflées, sucre, gluten de blé, avoine sirop de malt d’orge, sel — poudre à lever: carbonates de sodium , émulsifiant: e472e , sel — arôme — vitamines niacine, riboflavine, b12. d3. b6 thiamine — fer.",
                        "flocons d’avoine, farine de blé, graisse végétale, sucre, pépites de cranberries, sucre, humectant: glycérine, correcteur d'acidité: acide citrique, huile de tournesol, sirop de sucre inverti, billettes de riz soufflées, sucre, gluten de blé, avoine sirop de malt d’orge, sel, poudre à lever: carbonates de sodium , émulsifiant: e472e , sel, arôme, fer.")
                .replace(
                        "flocons d’avoine, farine de blé— graisse végétale— sucre — sirop de sucre inverti— billettes de riz soufflées, sucre, gluten de blé, avoine sirop de malt d’orge, sel — poudre à lever: carbonates de sodium , émulsifiant: e472e , sel — arôme — vitamines niacine, riboflavine, b12. d3. b6 thiamine — fer.",
                        "flocons d’avoine, farine de blé, graisse végétale, sucre , sirop de sucre inverti, billettes de riz soufflées, sucre, gluten de blé, avoine ,sirop de malt d’orge, sel ,poudre à lever: carbonates de sodium , émulsifiant: e472e , sel ,arôme , fer.")
                .replace("vitamines: niacine, e, b6. riboflavine, thiamine, folacine, b12 ,", "")
                .replace("céréalesflocons d'avoine, flocons de blé,", "céréales, flocons d'avoine, flocons de blé,")
                .replace("céréales 55% flocons d’avoine, flocons de blé, farine de blé, céréales extrudées,",
                        "céréales 55% ,flocons d’avoine, flocons de blé, farine de blé, céréales extrudées,")
                .replace("sel, farine d'orge maltée, vitamines: c, b3 ou pp, b6. b2. b1. b9. b21.",
                        "sel, farine d'orge maltée, ")
                .replace("son de riz, sucre de canne, sel //  ingrédients issus de l'agriculture biologique",
                        "son de riz, sucre de canne, sel .")
                .replace(
                        "correcteur d'acidité: phosphate de sodium, arôme, vitamines d, pp, b5. b6. b2. b1. b9 et minéraux",
                        "correcteur d'acidité: phosphate de sodium, arôme.")
                .replace(
                        "émulsifiant, sel, arômes naturels. peut contenir du lait, des arachides et des fruits à coque.",
                        "émulsifiant, sel, arômes naturels.")
                .replace("cacao maigre en poudre, vitamines et minéraux, sel, arôme naturel",
                        "cacao maigre en poudre, vitamines et minéraux, sel, arôme naturel.")
                .replace(
                        "rocou e160b, arôme cannelle, antioxydant: extrait riche en tocophérols, vitamineset minéraux",
                        "rocou e160b, arôme cannelle, antioxydant: extrait riche en tocophérols, vitamines et minéraux")
                .replace("régulateur d'acidité: phosphate trisodique, arôme, vitamineset minéraux.",
                        "régulateur d'acidité: phosphate trisodique, arôme, vitamines et minéraux.")
                .replace(", vitaminesacide pantothénique, b6. riboflavine, thiamine, acide folique, b12, fer", "")
                .replace("cacao maigre en poudre 50%, sucre, arôme", "cacao maigre en poudre 50%, sucre, arôme.")
                .replace("flocons de quinoa", "flocons de quinoa.")
                .replace(
                        "fourrage 50 %: chocolat au lait: sucre de canne, beurre de cacao, poudre de lait entier, masse de cacao, extrait de vanille, biscuit 50 %: farine de riz, farine de maïs, sucre de canne //  ingrédients issus de l’agriculture biologique",
                        "fourrage 50 %, chocolat au lait, sucre de canne, beurre de cacao, poudre de lait entier, masse de cacao, extrait de vanille, biscuit 50 %, farine de riz, farine de maïs, sucre de canne.")
                .replace(
                        "fourrage 50 %: chocolat noir: sucre de canne, masse de cacao, beurre de cacao, extrait de vanille, biscuit 50%: farine de riz, farine de maïs, sucre de canne. // ingrédients issus de l'agriculture biologique",
                        "fourrage 50 %, chocolat noir, sucre de canne, masse de cacao, beurre de cacao, extrait de vanille, biscuit 50%, farine de riz, farine de maïs, sucre de canne.")
                .replace(
                        "flocons de seigle 36% sucre de canne non raffiné, pralin de noisettes 12%, billettes de céréales, huile de tournesol, noisettes 6%, sirop de riz, farine de blé.",
                        "flocons de seigle 36%, sucre de canne non raffiné, pralin de noisettes 12%, billettes de céréales, huile de tournesol, noisettes 6%, sirop de riz, farine de blé.")
                .replace(
                        "flocons de céréales, graines de tournesol 7%, graines de courges 6%, pétales jus de pomme concentré de jus de pomme , sel, kasha, noisettes 3.5%, amandes sésame , graines de lin 2.2%.",
                        "flocons de céréales, graines de tournesol 7%, graines de courges 6%, pétales jus de pomme, concentré de jus de pomme , sel, kasha, noisettes 3.5%, amandes sésame , graines de lin 2.2%.")
                .replace(" , produit issu de l'agriculture biologique.", "")
                .replace("sarrasin 99%, sel..", "sarrasin 99%, sel.")
                .replace(
                        "céréales 63.8 %, sucre de canne non raffiné. chocolats 58 %. pépites de chocolats 21% pâte de cacao,",
                        "céréales 63.8 %, sucre de canne non raffiné, chocolats 58 %. pépites de chocolats 21% pâte de cacao,")
                .replace(
                        "flocons d'avoine 57.5%, sucre de canne non raffiné, huile de tournesol, billettes de céréales, sirop de riz, farine de blé, sirop de malt d'orge",
                        "flocons d'avoine 57.5%, sucre de canne non raffiné, huile de tournesol, billettes de céréales, sirop de riz, farine de blé, sirop de malt d'orge.")
                .replace(
                        "flocons d'avoine, chocolat en poudre 10%, farine de blé, sucre de canne non raffiné, billettes de céréales, sirop de riz, huile de tournesol, noix de coco 2%  ingrédients issus de l'agriculture biologique",
                        "flocons d'avoine, chocolat en poudre 10%, farine de blé, sucre de canne non raffiné, billettes de céréales, sirop de riz, huile de tournesol, noix de coco 2% .")
                .replace("epeautre 98%, sel", "epeautre 98%, sel.")
                .replace(
                        "carbonates d'ammonium, arôme, sel, émulsifiant: lécithines de colza. traces éventuelles de lait, d'oeufs et de fruits à coque.",
                        "carbonates d'ammonium, arôme, sel, émulsifiant: lécithines de colza.")
                .replace(
                        "émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.  traces éventuelles de lait, arachides et fruits à coques",
                        "émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.")
                .replace(
                        "ananas sucrés, pommes séchées, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel",
                        "ananas sucrés, pommes séchées, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel.")
                .replace(
                        "carbonates de sodium , carbonates d'ammonium, arômes, émulsifiants: lécithines de colza ,e472e , sel. traces éventuelles d'oeufs et de fruits à coque.",
                        "carbonates de sodium , carbonates d'ammonium, arômes, émulsifiants: lécithines de colza ,e472e , sel. traces éventuelles d'oeufs et de fruits à coque.")
                .replace(
                        ", vitamines: niacine, acide pantothénique, b6 , riboflavine, thiamine, acide folique, biotine, b12. fer. traces éventuelles de soja, lait et fruits à coque",
                        "")
                .replace(
                        ", vitamines: niacine, acide pantothénique— b6 , riboflavine, thiamine, acide folique, biotine, b12. fer",
                        "")
                .replace(
                        "céréales 67% {flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, flocons de ble, farine de ble, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras}, fruits secs 21% raisins secs, bananes sucrées aromatisées, lamelles de noix de coco, papayes sucrées, pommes, ananas sucrés, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel. traces éventuelles de soja, fruits à coque et lait.",
                        "céréales 67% ,flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, flocons de ble, farine de ble, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras, fruits secs 21% raisins secs, bananes sucrées aromatisées, lamelles de noix de coco, papayes sucrées, pommes, ananas sucrés, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel.")
                .replace(" fabriqué à partir de 99% de maïs pour obtenir 100g de produit fini.", "")
                .replace(
                        "stabilisant: sorbitols, dextrose, sucre, sel, arôme, émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.  traces éventuelles de lait, sulfites et arachides.",
                        "stabilisant: sorbitols, dextrose, sucre, sel, arôme, émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.")
                .replace(
                        "poudres à lever: diphosphates , carbonates d'ammonium , carbonates de sodium, arôme, émulsifiants: lécithines de colza , e172e, sel. traces éventuelles d'oeufs et de fruits à coque.",
                        "poudres à lever: diphosphates , carbonates d'ammonium , carbonates de sodium, arôme, émulsifiants: lécithines de colza , e172e, sel.")
                .replace(
                        "céréales 74% riz, ble complet24%, orge, copeaux de chocolat au lait 17%, sucre, farine complète de ble1.3%, sel, farine d'orge maltée, émulsifiant: mono, et diglycérides d'acides gras, arôme. traces éventuelles de fruits à coque",
                        "céréales 74% riz, ble complet24%, orge, copeaux de chocolat au lait 17%, sucre, farine complète de ble 1.3%, sel, farine d'orge maltée, émulsifiant: mono, et diglycérides d'acides gras, arôme.")
                .replace(
                        "céréales 50.5%, sucre, graines 12%, huile de tournesol, abricot sec 4%, mangue déshydratée 4%, morceaux de caramel 4%, miel, noix de coco, arôme naturel, antioxydant: extrait riche en tocophérols. traces éventuelles d'arachides et fruits à coque.",
                        "céréales 50.5%, sucre, graines 12%, huile de tournesol, abricot sec 4%, mangue déshydratée 4%, morceaux de caramel 4%, miel, noix de coco, arôme naturel, antioxydant: extrait riche en tocophérols.")
                .replace(
                        "céréales 68% {flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre}, sucre, huile de tournesol, carrés de chocolat noir 4%, carrés de chocolat au lait 4%, carrés de chocolat blanc 4%, cacao maigre en poudre, noix de coco en poudre, maltodextrine, sel, arôme. traces éventuelles de soja et fruits à coque.",
                        "céréales 68% ,flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre, sucre, huile de tournesol, carrés de chocolat noir 4%, carrés de chocolat au lait 4%, carrés de chocolat blanc 4%, cacao maigre en poudre, noix de coco en poudre, maltodextrine, sel, arôme.")
                .replace(
                        "céréales 66% flocons d'avoine complet 47%, flocons de ble complet, farine de ble, céréales extrudées, pétales de maïs, chocolat au lait 15%, sucre, huile de tournesol, sirop de glucose, noix de coco en poudre, sel, caramel, arôme. traces éventuelles de soja, fruits à coque.",
                        "céréales 66% ,flocons d'avoine complet 47%, flocons de ble complet, farine de ble, céréales extrudées, pétales de maïs, chocolat au lait 15%, sucre, huile de tournesol, sirop de glucose, noix de coco en poudre, sel, caramel, arôme.")
                .replace(
                        "céréales 57% flocons d'avoine complet, flocons de blé complet, farine de blé, céréales extrudées, fruits secs 24% raisins, bananes, abricots, noix de coco en lamelles, noix de coco en poudre, amandes en tranches, noisettes, sucre, huile de tournesol, miel, graines de courges 1%, sel. traces éventuelles d'arachide, soja, lait et autres fruits à coque.  ingrédient issu de l'agriculture biologique",
                        "céréales 57%, flocons d'avoine complet, flocons de blé complet, farine de blé, céréales extrudées, fruits secs 24% raisins, bananes, abricots, noix de coco en lamelles, noix de coco en poudre, amandes en tranches, noisettes, sucre, huile de tournesol, miel, graines de courges 1%, sel.")
                .replace(
                        "céréales semoule de maïs43%, farine de ble39%, sucre, miel 5%, sel, antiagglomérant: carbonate de calcium, arôme naturel, colorants: béta carotène et rocou, caramel, sirop de sucre caramélisé, vitamines: niacine, acide pantothénique, riboflavine, thiamine, b6 , b12 , acide folique, biotine, fer. traces éventuelles d'arachide, soja, lait, fruits à coque et graines de sésame.",
                        "céréales semoule de maïs 43%, farine de ble 39%, sucre, miel 5%, sel, antiagglomérant: carbonate de calcium, arôme naturel, colorants: béta carotène et rocou, caramel, sirop de sucre caramélisé.")
                .replace(
                        "sucre de canne roux, cacao maigre en poudre 21%, crèmes de céréales 10%, extrait de vanille bourbon, sel. , ingrédients issus de l'agriculture biologique",
                        "sucre de canne roux, cacao maigre en poudre 21%, crèmes de céréales 10%, extrait de vanille bourbon, sel.")
                .replace(
                        "flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %blé 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits à coque, graines de sésame et soja.  ",
                        "flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %, blé 0.8 %, avoine 0.5 %, sel.")
                .replace(
                        "ingrédients flocons d'avoine 38 %, sucre de canne, chocolat noir 11 %, flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %blé 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits à coque, graines de sésame et soja.",
                        "flocons d'avoine 38 %, sucre de canne, chocolat noir 11 %, flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %blé 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits à coque, graines de sésame et soja.")
                .replace(
                        "ingrédients brioche au beurre farine de blé: aæufs entiers, eau, beurre 8.5%, sucre pure canne, sirop de sucre inverti, levurè, arômes naturels, sel, poudre de lait écrémé, gluten de blé, épaississant,farine de blé malté&quot",
                        "brioche au beurre farine de blé: oeufs entiers, eau, beurre 8.5%, sucre pure canne, sirop de sucre inverti, levure, arômes naturels, sel, poudre de lait écrémé, gluten de blé, épaississant,farine de blé maltéquot")
                .replace(
                        "ingrédients riz 44 % , blé complet 24 %,copeaux de chocolat noir 19 %, sucre , orge 6 % , farine complète de blé , sel , farine d'orge maltée , vitamines: c, niacine, b6. riboflavine, thiamine, acide folique, b12 , fer , émulsifiant: mono , et diglycérides d'acides gras.",
                        "riz 44 % , blé complet 24 %,copeaux de chocolat noir 19 %, sucre , orge 6 % , farine complète de blé , sel , farine d'orge maltée , fer , émulsifiant: mono , et diglycérides d'acides gras.")
                .replace(
                        "cereales 71% farine de ble complete flocons de cereales avoine ble orge seigle riz sucre de canne, beurre 16%, œufs entiers, poudre de lait ecreme poudres a lever carbonates d'ammonium et de sodium, acide citrique sel marin arôme naturel.",
                        "cereales 71%,farine de ble complete,flocons de cereales,avoine,ble,orge,seigle,riz, sucre de canne, beurre 16%, œufs entiers, poudre de lait ecreme poudres a lever, carbonates d'ammonium et de sodium, acide citrique, sel marin, arôme naturel.")
                .replace(
                        "farine de blé, pâte à glacer lait 30%, sucre, beurre concentré reconstitué 16%, œufs entiers frais, amidon de blé, poudre à lever, fleur de sel 0.35%, lait écrémé en poudre",
                        "farine de blé, pâte à glacer lait 30%, sucre, beurre concentré reconstitué 16%, œufs entiers frais, amidon de blé, poudre à lever, fleur de sel 0.35%, lait écrémé en poudre.")
                .replace(
                        "farine de blé, beurre pâtissier 11.9 %, sucre, œufs, poudre de lait, sel de guérande, poudre à lever (bicarbonate de sodium et bicarbonate d'ammonium, arômes.",
                        "farine de blé, beurre pâtissier 11.9 %, sucre, œufs, poudre de lait, sel de guérande, poudre à lever, bicarbonate de sodium, bicarbonate d'ammonium, arômes.")
                .replace(
                        " poudres à lever: carbonates acides d’ammonium et de sodium , acide citrique, sel marin non raffiné, arôme  naturel de vanille, lait demi~écrémé",
                        " poudres à lever: carbonates acides d’ammonium et de sodium , acide citrique, sel marin non raffiné, arôme  naturel de vanille, lait demi-écrémé.")
                .replace(
                        "poudres à lever: carbonatesd'ammonium, carbonates de sodium, diphosphates , sel , lait écrémé en poudre , blanc d'œuf en poudre , cacao en poudre , jaune d'oeuf en poudre , arômes. traces de graines de sésame.",
                        "poudres à lever: carbonates d'ammonium, carbonates de sodium, diphosphates , sel , lait écrémé en poudre , blanc d'œuf en poudre , cacao en poudre , jaune d'oeuf en poudre , arômes.")
                .replace(
                        "ingredients: farine et amidon de blé 61.8%, sucre, pépites de, beurre concentré 11.5%, poudre de lait écrémé, poudres à lever: carbonates d'ammonium , carbonates de sodium , diphosphates, poudre de cacao, sel, extrait de malt d'orge, œufs, arômedont lait. traces éventuelles d’autres céréales contenant du gluten, arachides, soja et fruits à coque.",
                        "ingredients: farine et amidon de blé 61.8%, sucre, pépites de, beurre concentré 11.5%, poudre de lait écrémé, poudres à lever: carbonates d'ammonium , carbonates de sodium , diphosphates, poudre de cacao, sel, extrait de malt d'orge, œufs, arôme dont lait.")
                .replace(
                        "carbonates de sodium, diphosphates, lait demi,écrémé, œufs entiers en poudre, lait écrémé en poudre, cacao en poudre, arômes.",
                        "carbonates de sodium, diphosphates, lait demi-écrémé, œufs entiers en poudre, lait écrémé en poudre, cacao en poudre, arômes.")
                .replace(
                        "farine de blé, sucre de canne beurre 16%, lactosérum en poudre sel, poudres à lever, œufs entiers, jus de citron",
                        "farine de blé, sucre de canne beurre 16%, lactosérum en poudre sel, poudres à lever, œufs entiers, jus de citron.")
                .replace("petits pois 56 %, pomme de terre, lait demi,écrémé, crème fraîche, sel poivre",
                        "petits pois 56 %, pomme de terre, lait demi,écrémé, crème fraîche, sel poivre.")
                .replace(
                        "olives dénoyautées vertes, tournantes et noires 75%, lupins 17%, poivrons, sel, huile de tournesol, piments, épices et aromates. saumure, acidifiant, anti,oxygène, sulfites",
                        "olives dénoyautées vertes, tournantes et noires 75%, lupins 17%, poivrons, sel, huile de tournesol, piments, épices et aromates. saumure, acidifiant, anti,oxygène, sulfites.")
                .replace("olives noires , 92%, sel, huile de tournesol",
                        "olives noires , 92%, sel, huile de tournesol.")
                .replace("olives, eau et sel", "olives, eau et sel.")
                .replace(
                        "eau, rameaux de salicorne, vinaigre, carottes, arômes naturels, oignons blancs, coriandre",
                        "eau, rameaux de salicorne, vinaigre, carottes, arômes naturels, oignons blancs, coriandre.")
                .replace("câpres, eau, vinaigre, sel", "câpres, eau, vinaigre, sel.")
                .replace("olives, huile de colza, sel", "olives, huile de colza, sel.")
                .replace(
                        "pâtes alimentaires précuites 42% eau, nouilles 16% {semoule de ble dur. poudre de blanc doeuf, huile de colza, sen, viande de bœuf traitée en salaison, blanchie et précuite 22% poitrine de bœuf 150/j, eau. fécule de pomme de terre. protéines de lait, arômes natureis, maltodextrjne de blé et/ou maïs, antioxydant ascorbate de sodium, sen. vin rouge 11%, eau, carottes en rondelle oignons grelots 3.3%, oignon émincé 2.3%, champignons de paris émincés 1.7%, arôme, acidifiant: acide cmque, lardons cuits fumés 1.3% poitine de porc, sel, dextose, arôme de fumée, acidifiant: acide citrique, antioxydant: érythorbate de sodium, conservateur: nitrite de sodium, amidon modifié de maïs, gé, extrait de malt, arômes, poudre de champignon. traces éventuelles de soja, céleri, poissons, crustacés, mollusques. graines de sésame et fruis à coque. exprimés sur la totalité de la recette.",
                        "pâtes alimentaires précuites 42%, eau, nouilles 16% ,semoule de ble dur, poudre de blanc doeuf, huile de colza, sen, viande de bœuf traitée en salaison blanchie et précuite 22% ,poitrine de bœuf 150/j, eau, fécule de pomme de terre, protéines de lait, arômes natureis, maltodextrine de blé et/ou maïs, antioxydant ascorbate de sodium, sel, vin rouge 11%, eau, carottes en rondelle oignons grelots 3.3%, oignon émincé 2.3%, champignons de paris émincés 1.7%, arôme, acidifiant: acide cmque, lardons cuits fumés 1.3% poitine de porc, sel, dextose, arôme de fumée, acidifiant: acide citrique, antioxydant: érythorbate de sodium, conservateur: nitrite de sodium, amidon modifié de maïs, gé, extrait de malt, arômes, poudre de champignon.")
                .replace(
                        "nems porc 4x70 g:eau, farine de riz, oignon réhydraté, viande de porcet gras de porc 8%, protéines de soja, carotte, huile de tournesol, pousse de haricot mungo, amidon de pois, sel, champignon noir, chou, amidon de mais, sauce soja, ail, sucre, poivre. pourcentage exprimé sur le nem. sauce au nuoc,mâm 30 g eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%, sauce soja. pourcentage exprimé sur la sauce. traces éventuelles de crustacés, ceuf, lait et arachide.",
                        "nems porc 4x70 g,eau, farine de riz, oignon réhydraté, viande de porcet gras de porc 8%, protéines de soja, carotte, huile de tournesol, pousse de haricot mungo, amidon de pois, champignon noir, chou, amidon de mais, sauce soja, ail, sucre, poivre, sauce au nuoc,mâm 30 g ,eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%, sauce soja.")
                .replace("jus cuisiné: eau, oignons, sel, arômes. lentilles précuites.",
                        "eau, oignons, sel, arômes, lentilles précuites.")
                .replace("vinaigrette à l'huile de noix 10.9%4.1%", "vinaigrette à l'huile de noix 10.9%")
                .replace(
                        "noix de coco 2.1%, arôme naturel, antioxydant: extrait riche en tocophérols. traces éventuelles de lait, arachides et fruits à coque. produit fabriqué à partir d'ingrédients origine france et hors france.",
                        "noix de coco 2.1%, arôme naturel, antioxydant: extrait riche en tocophérols.")
                .replace(
                        "échalotes, sel, huile d'olive, basilic, dextrose, persil, ail, muscade, gélifiants: carraghénanes et farine de graine de caroube, arômes naturels",
                        "échalotes, sel, huile d'olive, basilic, dextrose, persil, ail, muscade, gélifiants: carraghénanes et farine de graine de caroube, arômes naturels.")
                .replace(
                        "macaronis cuits 45%, lait demi,écrémé 22%, jambon de porc supérieur cuit 16%, ferments, emmental râpé 11%, beurre 1.5%, huile de tournesol, amidon de maïs cireux, farine de blé 0.4%, gélatine de bœuf, sel, poivre, muscade",
                        "macaronis cuits 45%, lait demi,écrémé 22%, jambon de porc supérieur cuit 16%, ferments, emmental râpé 11%, beurre 1.5%, huile de tournesol, amidon de maïs cireux, farine de blé 0.4%, gélatine de bœuf, sel, poivre, muscade.")
                .replace(
                        "emmental 36%, eau, vin blanc, tomme 7%, crème, comté aop 3%, amidon modifié de mais, sel, correcteur d'acidité: phosphates de sodium, ail, boisson spiritueuse au kirsch, colorant: rocou",
                        "emmental 36%, eau, vin blanc, tomme 7%, crème, comté aop 3%, amidon modifié de mais, sel, correcteur d'acidité: phosphates de sodium, ail, boisson spiritueuse au kirsch, colorant: rocou.")
                .replace(
                        "pâtes alimentaires aux oeufs frais tagliatelles cuites 48%, préparation à base de surimi 20%, eau, amidons de pomme de terre et de blé, blanc d'oeuf réhydraté, huile de colza, poivrons rouges 8.5%, eau, crème fraîche, vinaigre d'alcool, jus de citron concentré, sel, jaune d'oeuf, moutarde de dijon, aneth 0.2%, amidon modifié de pomme de terre, poivre blanc, épaississant: gomme xanthane",
                        "pâtes alimentaires aux oeufs frais tagliatelles cuites 48%, préparation à base de surimi 20%, eau, amidons de pomme de terre et de blé, blanc d'oeuf réhydraté, huile de colza, poivrons rouges 8.5%, eau, crème fraîche, vinaigre d'alcool, jus de citron concentré, sel, jaune d'oeuf, moutarde de dijon, aneth 0.2%, amidon modifié de pomme de terre, poivre blanc, épaississant: gomme xanthane.")
                .replace(
                        "levure, sucre, farine de fèves, muscade, vinaigre d'alcool, farine d'orge maltée torréfiée, gluten de blé. pourcentages exprimés sur le nem au poulet et au curry.  nems aux legumes: galette de riz 31% garnie d'une farce aux légumes: eau, farine de riz 12%, oignon 10.5%, champignon noir 10.5%, protéines de soja, petit pois 6.5%, châtaigne d'eau 5%, huile de tournesol, blanc d'œuf, ciboule, sel, fécule de manioc, coriandre, caramel aromatique, piment. pourcentages exprimés sur le nem aux légumes. sauce nuoc,mâm: eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%,, sauce soja. pourcentage exprimé sur la sauce nuoc,mâm.  peut contenir des traces de crustacés et arachide.",
                        "levure, sucre, farine de fèves, muscade, vinaigre d'alcool, farine d'orge maltée torréfiée, gluten de blé. pourcentages exprimés sur le nem au poulet et au curry.  nems aux legumes: galette de riz 31% garnie d'une farce aux légumes: eau, farine de riz 12%, oignon 10.5%, champignon noir 10.5%, protéines de soja, petit pois 6.5%, châtaigne d'eau 5%, huile de tournesol, blanc d'œuf, ciboule, sel, fécule de manioc, coriandre, caramel aromatique, piment. pourcentages exprimés sur le nem aux légumes. sauce nuoc,mâm: eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%, sauce soja. ")
                .replace(
                        "farine de blé, beurre, levure, sel, lait écrémé en poudre, extrait de malt d'orge, poudre d'acérola. pourcentages exprimés sur l'ensemble du produit.",
                        "farine de blé, beurre, levure, sel, lait écrémé en poudre, extrait de malt d'orge, poudre d'acérola.")
                .replace("thon listao 25%, lentilles vertes pré,trempées 19%, poivrons rouges 11.4%",
                        "thon listao 25%, lentilles vertes pré-trempées 19%, poivrons rouges 11.4%")
                .replace("peut contenir des traces de moutarde, gluten et oeuf", "")
                .replace("piment de cayenne, poivre, sel, pavot bleu, épaississants: gomme xanthane et gomme guar",
                        "piment de cayenne, poivre, sel, pavot bleu, épaississants: gomme xanthane et gomme guar.")
                .replace("arôme naturel, sel, ciboulette, fibres d'agrumes, sucre",
                        "arôme naturel, sel, ciboulette, fibres d'agrumes, sucre.")
                .replace(
                        "peut contenir des traces de crustacés, mollusques, moutarde, œufs, poissons et fruits à coque",
                        "")
                .replace("gluten de blé. pourcentages exprimés sur le nem au porc.  nems au poulet et au curry",
                        "gluten de blé, nems au poulet et au curry")
                .replace(
                        "piment. pourcentages exprimés sur le nem aux légumes. sauce nuoc,mâm: eau, sucre, vinaigre d'alcool",
                        "piment, sauce nuoc,mâm: eau, sucre, vinaigre d'alcool")
                .replace(
                        "nuoc,mâm 1.6%,, sauce soja. pourcentage exprimé sur la sauce nuoc,mâm.  peut contenir des traces de crustacés et arachide.",
                        "nuoc,mâm 1.6%, sauce soja.")
                .replace(
                        "origan déshydraté, purée d'ail, huile d'olive vierge. peut contenir des traces de crustacés, mollusques,  moutarde, œufs, poissons et fruits à coque.  e.",
                        "origan déshydraté, purée d'ail, huile d'olive vierge.")
                .replace(
                        " peut contenir des traces de crustacés, mollusques, moutarde, œufs, poissons et fruits à coque.",
                        "")
                .replace(
                        " peut contenir des traces de crustacés, mollusques, moutarde, œufs, poissons et fruits à coque. mise en œuvre d'ingrédients décongelés, ne pas congeler.",
                        "")
                .replace(" pourcentages exprimés sur l'ensemble du produit.", "")
                .replace(" . mise en œuvre d'ingrédients décongelés, ne pas congeler.", "")
                .replace(
                        "sucre, vinaigre d'alcool, sel. pourcentage exprimé sur la sauce. peut contenir des traces de blé et lait.",
                        "sucre, vinaigre d'alcool, sel.")
                .replace("biologique. peut contenir des traces de soja, céleri et fruits à coque.", "")
                .replace("conservateurs: érythorbate de sodium et nitrite de sodium. emmental 10.7%.",
                        "conservateurs: érythorbate de sodium et nitrite de sodium, emmental 10.7%.")

                /* =============Moa reste================================================== */
                .replace(" protéines de lait'", "protéines de lait")
                .replace(" extrat de vanille. 'ingredtent ongtne unjon eurqéenre.", " extrait de vanille")
                .replace("lait écrémé – fruits: fraise ou framboise ou pêche ou abricot 10.3%",
                        "lait écrémé , fruits: fraise ou framboise ou pêche ou abricot 10.3%")
                .replace(" eau – crème – lait écrémé en poudre ", " eau , crème , lait écrémé en poudre ")
                .replace("citrate de calcium – amidon transformé – épaississants: carraghénanes",
                        "citrate de calcium , amidon transformé , épaississants: carraghénanes")
                .replace("gomme de guar – colorants: anthocyanes", "gomme de guar , colorants: anthocyanes")
                .replace("cochenille – arômes – édulcorants: acésulfame k",
                        "cochenille , arômes , édulcorants: acésulfame k")
                .replace("sucralose –  ferments lactiques du yaourt ", "sucralose , ferments lactiques du yaourt ")
                .replace("jus de potiron — amidon transformé de mais ",
                        "jus de potiron , amidon transformé de maïs ")
                .replace("sucre 8.9% · jus de carotte · sirop de glucose",
                        "sucre 8.9% , jus de carotte , sirop de glucose")
                .replace("une garniturecomposée de: viande bovine française de race charolaise 53%",
                        "une garniture composée de: viande bovine française de race charolaise 53%")
                .replace("émulsifiant: e471. colorant: bêtacarotène.", "émulsifiant: e471, colorant: bêtacarotène.")
                .replace("amidon transformé de pomme d terre. salade 9%.",
                        "amidon transformé de pomme d terre, salade 9%.")
                .replace("farine de blé malté, antioxydant: e316. œuf.",
                        "farine de blé malté, antioxydant: e316, œuf")
                .replace("agent de traitement de la farine: e300. œuf.",
                        "agent de traitement de la farine: e300, œuf")
                .replace(
                        "pain de mie au blé malté 40%— poulet rôti 19%— sauce caesar 13%   mayonnaise allégée en matières grasses,",
                        "pain de mie au blé malté 40%, poulet rôti 19%, sauce caesar 13%   mayonnaise allégée en matières grasses,")
                .replace(", ognon, boyau naturel de mouton", ", oignon, boyau naturel de mouton")
                .replace("boyau naturel de mouton.", "boyau naturel de mouton")
                .replace("colorant e160c", "colorant: e160c")
                .replace("sucre. boyau naturel de mouton", "sucre, boyau naturel de mouton")
                .replace("arômes. boyau naturel de mouton", "arômes, boyau naturel de mouton")
                .replace(
                        "viande de porc origine union européenne (81 eau, sel, sirop de glucose, dextrose, arôme naturel, épices et plantes aromatiques, acidifiant: e331. conservateur: e250.",
                        "viande de porc origine union européenne , eau, sel, sirop de glucose, dextrose, arôme naturel, épices et plantes aromatiques, acidifiant: e331, conservateur: e250.")
                .replace(
                        "fumée liquide émulsifiants: e450,e451. exhausteur de goût: e621. antioxydants: e300 , e301 , e316. colorant: e120. conservateurs: e250,e326,e262. ferments.",
                        "fumée liquide émulsifiants: e450 e451, exhausteur de goût: e621, antioxydants: e300 e301 e316 colorant: e120, conservateurs: e250 e326 e262, ferments.")
                .replace("antioxydant: ascorbate de sodium (e300. enveloppe naturelle de mouton.",
                        "antioxydant: ascorbate de sodium e300, enveloppe naturelle de mouton.")
                .replace(
                        "sirop de glucose, stabilisants: e451. e450 arômes, antioxydants: e315. e316 conservateur: e250. boyau naturel de mouton.",
                        "sirop de glucose, stabilisants: e451 e450, arômes, antioxydants: e315 e316, conservateur: e250, boyau naturel de mouton.")
                .replace("conservateur: nitrite de sodium. fumage au bois de hêtre.",
                        "conservateur: nitrite de sodium, fumage au bois de hêtre.")
                .replace("colorant e120. boyau nature de mouton.", "colorant e120, boyau nature de mouton.")
                .replace("stabilisants: di,, tri, et polyphosphates, ",
                        "stabilisants: diphosphate triphosphate et polyphosphates, ")
                .replace("conservateur: e262. antioxydants: e316,e331.",
                        "conservateur: e262. , antioxydants: e316 e331.")
                .replace("conservateur: nitrite de sodium. boyau naturel de porc.",
                        "conservateur: nitrite de sodium, boyau naturel de porc.")
                .replace("ferments. boyau naturel de porc.", "ferments, boyau naturel de porc.")
                .replace("exhausteur de goût: monoglutamate de sodium. boyau naturel de porc.",
                        "exhausteur de goût: monoglutamate de sodium, boyau naturel de porc.")

                /* 10377 */
                .replace(
                        "exhausteur de goût: e621. arômes naturel, acidifiant: e575 , colorant: e120 , antioxydant: e316 , conservateur: e250. enveloppe.",
                        "exhausteur de goût: e621. arômes naturel, acidifiant: e575 , colorant: e120 , antioxydant: e316 , conservateur: e250. , enveloppe.")
                .replace("acidifiant: glucono,delta,lactone, conservateur: nitrite d",
                        "acidifiant: glucono delta lactone, conservateur: nitrite d")
                .replace("colorant: e120. boyau naturel de mouton", "colorant: e120. , boyau naturel de mouton.")
                .replace("plante aromatique. boyaux naturels de mouton.",
                        "plante aromatique. , boyaux naturels de mouton.")
                .replace(",conservateur: nitrite de sodium. boyau naturel de porc.",
                        "conservateur: nitrite de sodium. , boyau naturel de porc.")
                .replace(
                        " colorants: e160c, e120 , antioxydant: e301 , conservateur: e250. traces de moutarde. porc origine: ue.",
                        " colorants: e160c e120 , antioxydant: e301 , conservateur: e250. , traces de moutarde. , porc origine: ue.")
                .replace(
                        "conservateurs: e250,e326. antioxydants: e300,e301,e316. dextrose, acidifiant: e262. colorant: e120. ferments.",
                        "conservateurs: e250 e326., antioxydants: e300 e301 e316., dextrose, acidifiant: e262. colorant: e120. , ferments.")
                .replace(
                        "exhausteur de gout: e621. arômes naturels, dextrose, acidifiant: e575. colorant: e120. antioxydant: e316. conservateur: e250. traces éventuelles d'oeuf, lait, soja et fruits à coque. boyau non comestible, retirez avant consommation",
                        "exhausteur de gout: e621., arômes naturels, dextrose, acidifiant: e575., colorant: e120., antioxydant: e316., conservateur: e250., traces éventuelles d'oeuf, lait, soja et fruits à coque., boyau non comestible")
                .replace(
                        "antioxydant: acide ascorbique, conservateur: nitrite de sodium, eau, sel. traces éventuelles de lait et de céréales contenant du gluten.",
                        "antioxydant: acide ascorbique, conservateur: nitrite de sodium eau sel., traces éventuelles de lait et de céréales contenant du gluten.")
                .replace(
                        "conservateur: nitrite de sodium. boyau naturel de mouton. ingrédient issu de l'agriculture biologique traces éventuelles d oeufs, de soja et de fruits à coque",
                        "conservateur: nitrite de sodium., boyau naturel de mouton., ingrédient issu de l'agriculture biologique , traces éventuelles d'oeufs de soja et de fruits à coque")
                .replace("girofle, ferments. enveloppe: boyau collagénique.",
                        "girofle, ferments., enveloppe: boyau collagénique.")
                .replace("ferments. enveloppe: boyau naturel de porc.",
                        "ferments., enveloppe: boyau naturel de porc.")
                .replace("e252. ferments.", "e252., ferments.")
                .replace(
                        "plantes aromatiques, sirop de glucose, dextrose, conservateur: nitrate de potassium, ferments, boyau naturel de porc. 152g de viande mise en œuvre pour 100 g de produit fini.",
                        "plantes aromatiques, sirop de glucose, dextrose, conservateur: nitrate de potassium, ferments, boyau naturel de porc., 152g de viande mise en œuvre pour 100 g de produit fini.")
                .replace("ferments. boyau naturel de porc.", "ferments., boyau naturel de porc.")
                .replace("ferments. boyau en fibre animale.", "ferments., boyau en fibre animale.")
                .replace("ferment. boyau naturel de bœuf.", "ferments., boyau naturel de bœuf.")
                .replace(
                        "saumon atlantiqueélevé au royaume,uni, norvège ou irlande 97.2 %, sel 2.8 %. ingrédient issu de l'agriculture biologique. pays d'élevage: voir ci,dessous.",
                        "saumon atlantiqueélevé au royaume-uni, norvège ou irlande 97.2 %, sel 2.8 %. ,ingrédient issu de l'agriculture biologique. pays d'élevage")
                .replace("saumon atlantiquenourri sans ogm97%, sel 3%.",
                        "saumon atlantique nourri sans ogm 97%, sel 3%.")
                .replace(
                        "sirop de glucose de maïs, igre, coriandre. mon élevé en norvège, ecosseou irlande: voir impression 'essous.",
                        "sirop de glucose de maïs, igre, coriandre., saumon élevé en norvège ecosse ou irlande")
                .replace(
                        "saumon 80 %, eau, protéine de soja, gélifiant: e407. blanc d'oeuf poudre, stabilisants: e452,e451. sucre, arôme naturel de romarin, sel, colorant: e160c.",
                        "saumon 80 %, eau, protéine de soja, gélifiant: e407., blanc d'oeuf poudre, stabilisants: e452 e451., sucre, arôme naturel de romarin, sel, colorant: e160c.")
                .replace("boyau naturel de porc. poudre de fleurage: carbonate de calcium. talc.",
                        "boyau naturel de porc., poudre de fleurage: carbonate de calcium., talc.")
                .replace(
                        " graines de sésame, maltodextrine de maïs, algues séchées, piment en poudre, colorants: e140. e100. e160c, e162. e150a.",
                        " graines de sésame, maltodextrine de maïs, algues séchées, piment en poudre, colorants: e140. e100. e160c e162. e150a.")
                .replace("sel marin. dorure: œufs.", "sel marin., dorure: œufs.")
                .replace(" emmental  lait demi,écrémé", " emmental  lait demi-écrémé")
                .replace("100 % viande bovine française.", "100% viande bovine française.")
                .replace("eau, fromage frais 18.2 % lait écrémé,", "eau, fromage frais 18.2 %, lait écrémé,")
                .replace("mini,cake à l'olive noire: œuf entier liquide pasteurisé,",
                        "mini cake à l'olive noire, œuf entier liquide pasteurisé,")
                .replace("queue de crevette crue décortiquée 12 % crevetteconservateur: métabisulfite de sodium,",
                        "queue de crevette crue décortiquée 12 % , conservateur: métabisulfite de sodium,")
                .replace(" laitécrémé en poudre,", " lait écrémé en poudre,")
                .replace(
                        "moule cuite décoquillée 45 %mytilus edulis sauvage pêchée à la drague en atlantique nord,est, sous,zone mer du nord, noix de saint,jacques crue27.5 % zygochlamys patagonica sauvage pêchée au chalut en atlantique sud,ouest, crevette nordique cuite décortiquée26.8 % pandalus borealis sauvage pêchée au chalut en atlantique nord,est, sous,zones mer de barents, mers de norvège, sel.",
                        "moule cuite décoquillée 45 % mytilus edulis sauvage pêchée à la drague en atlantique nord est sous zone mer du nord, noix de saint-jacques crue 27.5 % zygochlamys patagonica sauvage pêchée au chalut en atlantique sud-ouest, crevette nordique cuite décortiquée 26.8 % pandalus borealis sauvage pêchée au chalut en atlantique nord-est sous zones mers de norvège, sel.")
                .replace("noix de saint,jacques sans corail.", "noix de saint-jacques sans corail.")
                .replace("noix de saint,jacques 100%.", "noix de saint-jacques 100%.")
                .replace(
                        "crème fraîche liquide44.2 %, girolle 21.1 %, cèpe 21.1 %, morille 8 %, ail 2.9 %, persil 1.9 %, sel, poivre. 1.9+2.9+8+42.2",
                        "crème fraîche liquide 44.2 %, girolle 21.1 %, cèpe 21.1 %, morille 8 %, ail 2.9 %, persil 1.9 %, sel, poivre.")
                .replace("noix de muscade 0.01% issus d'animaux nés, élevés et abattus en ue",
                        "noix de muscade 0.01%")
                .replace(" fromage de chèvreaffiné 5 %, ", " fromage de chèvre affiné 5 %, ")
                .replace(" levain de seigledévitalisé,", " levain de seigle dévitalisé,")
                .replace("choux,fleurs en fleurettes 100 %.", "choux-fleurs en fleurettes 100 %.")
                .replace(
                        "59% choux,fleurs, eau, 8.5% crème, jaune d'œuf, 5% emmental, préparation à base de lactosérum,",
                        "59% choux,fleurs, eau, 8.5% crème, jaune d'œuf, 5% emmental, préparation à base de lactosérum,")
                .replace(
                        "mozzareila, olives noircies avec noyau, basilic et origan..  41% pâte: farine de blé, eau, levure, sel.",
                        "mozzarella, olives noircies avec noyau, basilic et origan. , farine de blé, eau, levure, sel.");
        return replaceEnderscoreIngredients;
    }
}
