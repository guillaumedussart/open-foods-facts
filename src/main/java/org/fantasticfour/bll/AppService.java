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
        String[] parts = line.replace("|???", "l'")
                .replace("???|a", " a")
                .replace("ty|ate de sodium", "tyate de sodium")
                .replace(" conservateur |antioxydant: levure", " conservateur antioxydant: levure")
                .replace("|% [maltodextrine de bl??", ",maltodextrine de bl??")
                .replace("Filets de colin d???A|aska 72%qualit?? sans ar??te*", "Filets de colin d???Aaska 72% qualit?? sans ar??te")
                .replace("Calin+ Fruits P??che, Abricot, Fraise, Framboise)", "Calin+ Fruits P??che, Abricot, Fraise, Framboise")
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
                        "marmelade de citrons confiture d???ananas et de fruits de la passion",
                        "marmelade de citrons confiture d???ananas et de fruits de la passion,")
                .replace("confiture de framboises et de litchis", "confiture de framboises et de litchis,").replace(
                        "confiture de pamplemousses et de fruits du dragon",
                        "confiture de pamplemousses et de fruits du dragon,")
                .replace("plein air, issus de l'agriculture", "plein air issus de l'agriculture").replace(
                        "confiture de mangues et de p??ches", "confiture de mangues et de p??ches,")
                .replace("confiture de poires et de mirabelles", "confiture de poires et de mirabelles,").replace(
                        "confiture de fraises ?? la verveine", "confiture de fraises ?? la verveine,")
                .replace("confiture de framboises et de groseilles", "confiture de framboises et de groseilles,")
                .replace("confiture extra de cerises et de m??res", "confiture extra de cerises et de m??res,")
                .replace("marmelade d???oranges ?? la cannelle", "marmelade d???oranges ?? la cannelle,").replace(
                        "confiture extra de 4 fruits", "confiture extra de 4 fruits,")
                .replace("marmelade d???oranges douces et de mandarines",
                        "marmelade d???oranges douces et de mandarines,")
                .replace("confiture de fraises et de groseilles", "confiture de fraises et de groseilles,").replace(
                        "confiture extra de rhubarbe", "confiture extra de rhubarbe,")
                .replace("confiture extra de fraises et de fraises des bois",
                        "confiture extra de fraises et de fraises des bois,")
                .replace("marmelade de citrons confiture d???ananas et de fruits de la passion",
                        "marmelade de citrons confiture d???ananas et de fruits de la passion,")
                .replace("confiture extra de reines", "confiture extra de reines,").replace(
                        "oeclatatton nutritionnelle pour / nutrition declaration for / nahrwertdeklaration pto / voedingswaardever,melding per 100 g energie / energy / energie",
                        "")
                .replace("anti,agglom??rant", "antiagglom??rant").replace("/ ingredi??nten:", "").replace("[eau",
                        ",eau")
                .replace("sirop de glucose de bl?? ou mai's", "sirop de glucose de bl?? ou ma??s").replace(
                        "dextrose de ma`i`s", "dextrose de ma??s")
                .replace("beurre. emmental r??p??20%. levure", "beurre, emmental r??p??20%, levure").replace(
                        "lait  ??cr??m?? en poudre. farine de malt d???orge",
                        "lait  ??cr??m?? en poudre, farine de malt d???orge")
                .replace("sucre ? cr??me fruits: fraise ou p??che ou ou framboise ou abricot",
                        "sucre , cr??me fruits: fraise ou p??che ou ou framboise ou abricot")
                .replace("poudre de ??cr??m?? ? jus de carotte", "poudre de ??cr??m?? , jus de carotte").replace(
                        "jus de potiron ? jus de betterave sureau ? ar??mes ?: : e330. e331 ferments sans",
                        "jus de potiron , jus de betterave sureau , ar??mes  e330 e331")

                /* =============Victoria 1-3_350====================================== */

                .replace("vitamines: b3. b5. b12. b6. b2. b1. b9. b8:", "").replace(
                        "agent de traitement de la farine: l,cyst??ine.",
                        "agent de traitement de la farine: lecyst??ine.")
                .replace("pre,cooked wholegrain basmati rice", "precooked wholegrain basmati rice").replace(
                        "conservateur: e202. colorant: e160c, ar??me.",
                        "conservateur: e202. , colorant: e160c, ar??me.")
                .replace(
                        "glucose syrup, sweetened condensed skim milk, sugar, water, salted modified starch, colouring: ordinary caramel, salt, flavour, preservative :, emulsifier: mono and diglycerides of fatty acids, acidifier: citric acid. 31 35 % ,fr , ingr??dients: sirop de glucose, lait ??cr??m?? condens?? sucr??, sucre, eau, amidon modifi??, colorant: caramel ordinaire, sel, ar??me, conservateur., ??mulsifiant: mono et diglyc??rides d'acide gras, acidifiant: acide citrique caramel.' min 35 %. en'. production date and best before date: see below , to be stored in a clean, dry and cool placeopening. fr: date de production et a consommer de pr??f??rence avant le: voir ci,dessous , a conserver dans un endroit propre, 50 et250 ,a consommer de pr??f??rence 6 semaines apr??s ouverture. yf??jfiifi pd 2clfl",
                        "sirop de glucose, lait ??cr??m?? condens?? sucr??, sucre, eau, amidon modifi??, colorant: caramel ordinaire, sel, ar??me, conservateur., ??mulsifiant: mono et diglyc??rides d'acide gras, acidifiant: acide citrique caramel. min 35 %")
                .replace(", vitamines: b1. b6 et b12.", "").replace(
                        "pulpes de fruits: p??che: pulpes de fruits 52.5%, m??re: pulpes de fruits 53%, abricot: pulpes de fruits 35%, framboise: pulpes de fruits 52.5%, fraise: pulpes de fruits 52.5%, poire: pulpes de fruits 52.5%",
                        "pulpes de fruits: p??che: 52.5% m??re:  53% abricot: 35% framboise: 52.5% fraise: 52.5% poire: 52.5%")
                .replace("jeunes pousses de laitue verte, de laitue rouge, d'??pinard.",
                        "jeunes pousses de laitue verte de laitue rouge d'??pinard.")
                .replace(
                        "ingredienw quinoa. issu de l'agriculture biologique. risques de traces de gluten, de fruits ?? coques, de s??same, de lait et d'oeuf.",
                        "quinoa issu de l'agriculture biologique risques de traces de gluten de fruits ?? coques de s??same de lait et d'oeuf.")
                .replace("tomates mi,s??ch??es 68%,", "tomates mi-s??ch??es 68%,").replace(
                        "??mulsifiant: l??cithine de tournesol,  issu de l'agriculture biologique",
                        "??mulsifiant: l??cithine de tournesol issu de l'agriculture biologique")
                .replace("ar??me naturel de vanille.  ingr??dients agricoles issus de l'agriculture biologique.",
                        "ar??me naturel de vanille issus de l'agriculture biologique.")
                .replace("sirop de glucose/fructose,saccharose, conservateur: e202. acidifiant: e330.",
                        "sirop de glucose/fructose,saccharose, conservateur: e202. , acidifiant: e330.")
                .replace("correcteur d'acidit??: e330. conservateur: anhydride sulfureux.",
                        "correcteur d'acidit??: e330. , conservateur: anhydride sulfureux.")
                .replace("agent de traitement de la farine: l,cyst??ine, colorant: carot??no??des.",
                        "agent de traitement de la farine: lecyst??ine, colorant: carot??no??des.")
                .replace(
                        "pulpes de fruits: abricot: pulpes de fruits 53%, m??re: pulpes de fruits 53%, poire: pulpes de fruits 52.5%,",
                        "pulpes de fruits: abricot: 53% m??re: 53% poire: 52.5%,")
                .replace("l??gumes 60% :, eau, sel.", "l??gumes 60% , eau, sel.")
                /* 116 */
                .replace(" farine de bl??  graisse et huile v??g??tales???eau",
                        "farine de bl??  graisse et huile v??g??tales,eau")
                .replace(
                        "dans un saladier temp??rature 500c au four traditionnelou au micro ondes. sortir le saladier du micro ondes et r??per du chocolat noir sur le chocolat chaud en homog??n??isant le tout avec une spatule pour redescendre ?? 27 oc. temp??rez ?? nouveau au micro ondes pour atteindre 31 oc/320c. vous pourrez tremper vos fruits confits en les retirant du chocolat avec une fourchette et les poser doucement sur du papier sulfuris?? pour les laisser s??cher. courbe de temp??rature ?? respecter :",
                        "fruits confits")
                .replace(
                        "farine de bl??  graisse et huile v??g??tales,eau , alcool ??thylique ,sucre , sel , jus de citron concentr?? , colorant: carot??no??des , agent de traitement de la farine: lecyst??ine.",
                        "farine de bl?? , graisse et huile v??g??tales,eau , alcool ??thylique ,sucre , sel , jus de citron concentr?? , colorant: carot??no??des , agent de traitement de la farine: lecyst??ine.")
                .replace(
                        "abricot: pulpes de fruits 53 %. cassis: pulpes de fruits. fraise: pulpes de fruits 55 %. framboise: pulpes de fruits 52 %. orange: pulpes de fruits 50 %. prune: pulpes de fruits 50 %. sucre, sirop de glucose de bl??, g??lifiant: pectines, ar??mes naturelsavec autres ar??mes naturels, acidifiant: jus de citron.",
                        "abricot: pulpes de fruits 53 %. cassis: pulpes de fruits. fraise: pulpes de fruits 55 %. framboise: pulpes de fruits 52 %. orange: pulpes de fruits 50 %. prune: pulpes de fruits 50 %. sucre, sirop de glucose de bl??, g??lifiant: pectines, ar??mes naturels avec autres ar??mes naturels, acidifiant: jus de citron.")
                .replace(
                        "pulpe de pommes, sucre, sirop de glucose, g??lifiant: pectines, acidifiant: acide citrique, ar??mes, colorants: e100 , e163 , e160c , e141.",
                        "pulpe de pommes, sucre, sirop de glucose, g??lifiant: pectines, acidifiant: acide citrique, ar??mes, colorants: e100  e163  e160c  e141.")
                .replace(
                        "polenta 76.4%, l??gumes 19.9%, eau , huile de tournesol , estragon , sel de mer   ingr??dients issus de l'agriculture biologique",
                        "polenta 76.4%, l??gumes 19.9%, eau , huile de tournesol , estragon , sel de mer ,  ingr??dients issus de l'agriculture biologique")
                .replace(
                        "jus de soja 87%, huile de tournesol, ??mulsifiant: gomme arabique, ??paississants: gomme xanthane, carragh??nanes, sucre de canne non raffin??. soja sans ogm. ingr??dients issus de l'agriculture biologique.",
                        "jus de soja 87%, huile de tournesol, ??mulsifiant: gomme arabique, ??paississants: gomme xanthane, carragh??nanes, sucre de canne non raffin??. soja sans ogm. , ingr??dients issus de l'agriculture biologique.")
                .replace("agent de traitement de la farine l,cyst??ine.",
                        "agent de traitement de la farine lecyst??ine.")
                .replace("chocolat au lait 58%, riz 42% ingr??dients issus de l'agriculture biologique.",
                        "chocolat au lait 58%, riz 42% ,ingr??dients issus de l'agriculture biologique.")
                .replace("chocolat noir 57%, riz 48%. ingr??dients issus de l'agriculture biologique.",
                        "chocolat noir 57%, riz 48%. , ingr??dients issus de l'agriculture biologique.")
                .replace("conservateur: e202. e220 anhydride sulfureux, e330. e120. e150a, e133. e127.",
                        "conservateur: e202. e220 anhydride sulfureux e330. e120. e150a. e133. e127.")
                .replace("correcteur d'acidit?? e330. conservateur e220 anhydride sulfureux.",
                        "correcteur d'acidit?? e330, conservateur e220 anhydride sulfureux.")
                .replace("agent de traitement de la farine: l,cyst??ine , colorant: b??ta,carot??ne.",
                        "agent de traitement de la farine: lecyst??ine , colorant: b??tacarot??ne.")
                .replace("herbes de provence. ingr??dients agricoles issus de l'agriculture biologique.",
                        "herbes de provence., ingr??dients agricoles issus de l'agriculture biologique.")
                .replace(
                        "pr??paration de prot??ines de soja et de ble: 44%, fromage fondu: 28%, panure: 28%. huile de tournesol",
                        "pr??paration de prot??ines de soja et de ble: 44%, fromage fondu: 28%, panure: 28%. , huile de tournesol")
                .replace("carmins, ar??me naturel. contient des sulfites et anhydride sulfureux.",
                        "carmins, ar??me naturel., contient des sulfites et anhydride sulfureux.")
                .replace(
                        "eau. f??ves de soja, sel de nigari. sans conservateurproduits issus de l???agriculture biologique",
                        "eau., f??ves de soja, sel de nigari. sans conservateur ,produits issus de l???agriculture biologique")
                .replace(
                        "farine de bl?? t80, beurre, eau, sucre de canne blond, sel de gu??rande, jus de citron concentr??. ingr??dients issus de l'agriculture biologique.",
                        "farine de bl?? t80, beurre, eau, sucre de canne blond, sel de gu??rande, jus de citron concentr??., ingr??dients issus de l'agriculture biologique.")
                .replace(
                        "jus de soja 89%, huile de tournesol, ??mulsifiant: l??cithine de soja , ??paississants: gomme xanthane, carragh??nanes. ingr??dients issus de l'agriculture biologique.",
                        "jus de soja 89%, huile de tournesol, ??mulsifiant: l??cithine de soja , ??paississants: gomme xanthane, carragh??nanes. , ingr??dients issus de l'agriculture biologique.")
                .replace(
                        "fabriqu?? en espagne dans un atelier qui utilise fruits ?? coque, moutarde, ceufs, produits laitiers, gluten, lupin et celeri",
                        "fabriqu?? en espagne dans un atelier qui utilise fruits ?? coque, moutarde, ceufs, produits laitiers, gluten, lupin , celeri")
                .replace(
                        "tofu 93%, sel, ??pices.  ingr??dients d'origine agricole obtenus selon les r??gles de production biologique.",
                        "tofu 93%, sel, ??pices. , ingr??dients d'origine agricole obtenus selon les r??gles de production biologique.")
                .replace(
                        "correcteur d'acidit??: concentr?? lactique, conservateur: sorbate de potassium, ar??me, colorant: b??ta,carot??ne, vitamine e.",
                        "correcteur d'acidit??: concentr?? lactique, conservateur: sorbate de potassium, ar??me, colorant: b??tacarot??ne")
                .replace(
                        "cr??me de lait pasteuris??e, sel 2% , ferments lactiques.  biologique. les informations en gras sont destin??es aux personnes intol??rantes ou allergiques.",
                        "cr??me de lait pasteuris??e, sel 2% , ferments lactiques.  biologique.")
                .replace("beurrepasteuris?? doux. 1: ingr??dient issu de l'agriculture biologique.",
                        "beurre pasteuris?? doux. ingr??dient issu de l'agriculture biologique.")
                .replace("beurre.", "beurre ").replace(
                        "huiles et graisses v??g??tales biologiques non hydrog??n??es1. eau, sel de mer 1.4%, ??mulsifiants: l??cithine de soja biologique, ar??mes naturels, jus de citron concentr?? biologique. ingr??dients d'origine v??g??tale 1 ces huiles et graisses v??g??tales donnent ?? ce produit primev??re un profil riche en acides gras insatur??s om??ga 3.6.9formul?? avec l'aide du service nutrition de l'institut pasteur de lille.",
                        "huiles et graisses v??g??tales biologiques non hydrog??n??es., eau, sel de mer 1.4%, ??mulsifiants: l??cithine de soja biologique, ar??mes naturels, jus de citron concentr?? biologique. ingr??dients d'origine v??g??tale")
                /* 439 */

                .replace("vitamines: b3. b5. b12. b6. b2. b1. b9. b8:", "").replace(
                        "agent de traitement de la farine: l,cyst??ine.",
                        "agent de traitement de la farine: lecyst??ine.")
                .replace("pre,cooked wholegrain basmati rice", "precooked wholegrain basmati rice").replace(
                        ". biologique.", "")
                .replace("mati??re grasse et huile de palme", "mati??re grasse , huile de palme").replace(
                        "biscuit sec / ingr??dients / ingredientes cd", "")
                .replace(" biologiques.", "").replace("??mulsifiant: e471.", "??mulsifiant: e471,").replace(
                        "laits ??cr??m?? et entier en poudre", "laits ??cr??m?? , entier en poudre")
                .replace("carbonates d'ammonium et de sodium", "carbonates d'ammonium , de sodium").replace(
                        " lactose et prot??ines de lait", " lactose , prot??ines de lait")
                .replace("carbonates de sodium et d'ammonium", "carbonates de sodium , d'ammonium").replace(
                        "lactose et prot??ines de lait", "lactose , prot??ines de lait")
                .replace("carbonates de sodium et d'ammonium", "carbonates de sodium , d'ammonium").replace(
                        "l,cyst??ine,", "l??cyst??ine,")
                .replace("lactose et prot??ines de lait", "lactose , prot??ines de lait").replace(
                        "carbonates d'ammonium et de sodium ", "carbonates d'ammonium , de sodium ")
                .replace(". peut contenir des traces d'oeuf. conservation:", "").replace(
                        "biscuit sec / ingr??dients / ingredientes cd", "")
                .replace("c??r??ales  farine de froment,", "c??r??ales,  farine de froment,").replace(
                        "carbonates de sodium et d'ammonium", "carbonates de sodium, d'ammonium")
                .replace("lactose et prot??ines de lait,", "lactose, prot??ines de lait,").replace("l,cyst??ine",
                        "l??cyst??ine")
                .replace("laits ??cr??m?? et entier en poudre", "laits ??cr??m?? , entier en poudre").replace(
                        " laits entiers et ??cr??m?? en poudre", " laits entiers , ??cr??m?? en poudre")
                .replace("vet,urs nutritionne!tes ??netye 2054 kj'", "").replace(
                        " +ingr??dients issus de l'agriculture biologique + origine francetraces ??ventuelles d'amande et de s??same fabriqu?? en france",
                        "")
                .replace("ingr??dients agricoles issus de l'agriculture biologique.", "").replace(
                        " ingr??dients agricoles issus de l'agriculture biologique", "")
                .replace(". ingr??dients agricoles issus de l'agriculture biologique.", "").replace(
                        "issus de l'agriculture biologique.", "")
                .replace(" almond powder, sugar, egg whites, flavour, salt, wheat flour.", "").replace(
                        " vitamines b1. b2. b6 et b9.", "")
                .replace("p??pites de raisin rouge d??shydrat?? 4.7 %", "p??pites de raisin rouge d??shydrat?? 4.7 %,")
                .replace("vitamines: pp, e, b6. b2. b1. b9.", "").replace("c??r??ales 48%/ chocolat noir 29%/",
                        "c??r??ales 48%, chocolat noir 29%,")
                .replace(
                        "antioxydant: extrait de romarin.  dont lait. traces ??ventuelles dlautres c??r??ales contenant du gluten, oeufs, soja, arachide et fruits ?? coque.",
                        "antioxydant: extrait de romarin,lait. ")
                .replace("biscuit 35.4%: farine de bl?? 32.5%,", "biscuit 35.4%, farine de bl?? 32.5%,").replace(
                        "certains ingr??dients ne sont pas fran??ais.  ingr??dients  traces ??ventuelles d'autres c??r??ales contenant du gluten, ceufs, arachides, soja et fruits ?? coque.",
                        "")
                .replace(
                        "ingr??dients  fabriqu?? dans un atelier qui utilise des fruits ?? coque, du soja, de l'oeuf, du lait et du lupin.",
                        "")
                .replace("anti,oxyg??ne", "antioxyg??ne").replace(
                        "vitamine e. fabriqu?? dans un atelier qui utilise des fruits ?? coque, du soja, des oeufs et du lupin.",
                        "")
                .replace("  bl?? stock?? sans pesticides de synth??se.", "").replace(" vitamines b6. b9. d et e.", "")
                .replace(", vitamines b1. b2. b6. b9. pp et e.", "").replace(" vitamines b1. b6. b9 et e, fer.", "")
                .replace("correcteur d'acidit??. chocolat au lait 48 %:",
                        "correcteur d'acidit??, chocolat au lait 48 %:")
                .replace(" :.", "").replace(". , ingr??dients ", "").replace("farine de bl?? t 65.",
                        "farine de bl?? t 65,")
                .replace("du pivert bucliichil bio ganzilia& chocolade fabriqu?? en aveyron , france", ",chocolat,")
                .replace(".  ingr??dients issus de l???agriculture ", "").replace("lngr??dients:", "").replace(
                        "traces possible de fruits ?? coque, soja et s??same.", "")
                .replace("ingr??dients issus de l???agriculture ", "").replace("(", ",").replace("et", ",").replace(
                        ",, lactoseet", ", lactoseet")
                .replace("de graines de s??same et d'arachides.", "de graines de s??same , d'arachides.").replace(
                        " (", "")
                .replace("poudres a lever (", "poudres a lever ,").replace("et ar??me", "").replace(
                        "traces ??ventuelles d'autres fruits ?? coque et de graines de s??same", "")
                .replace("traces ??ventuelles d'oeufs et de fruits ?? coque.", "").replace(
                        "traces ??ventuelles de fruits ?? coque et d oeufs.", "")
                .replace("traces ??ventuelles d'oeufs et de fruits ?? coque.", "").replace(
                        "traces ??ventuelles de lait.", "")
                .replace("traces ??ventuelles d'autres fruits ?? coque et graines de s??same", "").replace(
                        "traces ??ventuelles d oeufs.", "")
                .replace("sel traces ??ventuelles de fruits ?? coque.", "sel,").replace(
                        " trace ??ventuelles de fruits ?? coque et de graines de s??same.", "")
                .replace(". traces ??ventuelles d'oeufs.", "").replace(
                        "traces ??ventuelles de lait, de soja , de fruits ??oque.", "")
                .replace(
                        "farine de bl?? 50%, beurreoncentr?? 25%, sucre. oeufs. jaunes d'??ufs, poudres ?? lever. selaramel.olorant:arot??no??des.",
                        "farine de bl?? 50%, beurreoncentr?? 25%, sucre, oeufs, jaunes d'??ufs, poudres ?? leve,")
                .replace("g??lifiant:", ",").replace("nappage fraise 50%:", ",").replace(
                        " traces ??ventuelles de fruits ??oque , de soja. tenir au sec, ?? l'abri de lahaleur , de la lumi??re 0% des aqr",
                        "")
                .replace("farine de bl??, beurreoncentr??28 %,olorant ",
                        "farine de bl??, beurre concentr?? 28 %, colorant ")
                .replace("olorant:armins", "colorant: carmins").replace("oriandre", "coriandre")
                .replace("arotte 8.5%", "carotte 8.5%").replace("vitamine b1", "")
                .replace("itrate de sodium", "nitrate de sodium")
                .replace(", ingr??dients issus de l'agriculture biologique", "")
                .replace("e330.onservateur: sorbate de potassium e202.",
                        "e330 , conservateur: sorbate de potassium e202.")
                .replace(", vitamines riboflavine, b12. d.", "")
                .replace("olorant:aramel e150a,", "colorant: caramel e150a ,").replace("eau.", "eau ,")
                .replace(" ingr??dients ", "").replace("ingr??dients ", "").replace("dearotte", "de carotte")
                .replace("deitron", "de citron").replace("deoncentr??", "de concentr??")
                .replace("olorant:oncentr??", " colorant: concentr??").replace("pommeoncentr??", "pomme concentr??")
                .replace("deanne", "de canne").replace("deacao", "de cacao").replace("alcium", "calcium")
                .replace("   100 % d'origine france.", "").replace("jusoncentr??", "jus concentr??")
                .replace(
                        "eau gaz??ifi??e, sucre, jus deassis issu de jus deassisoncentr??, sirop de glucose, ar??me, acidifiant e330. taurine,orrecteur d'acidit?? e331. sucrearam??lis??, extrait de guarana,af??ine,onservateur e211. vitamines: niacine, acide pantoth??nique, vitamine b6. thiamine,olorants: e122. e131. e150d, e122.",
                        "eau gaz??ifi??e, sucre, jus de cassis issu de jus de cassis concentr??, sirop de glucose, ar??me, acidifiant e330. taurine, correcteur d'acidit?? e331. sucrearam??lis??, extrait de guarana,af??ine,conservateur e211.  colorants: e122. e131. e150d, e122.")
                .replace(
                        "eau, jus de fruits ?? base deoncentr??s: ananas, orange,itron, sirop de glucose,fructose, sucre, jus de de carotte ?? base deoncentr??, stabilisants: farine de guar , pectine, ar??me, vitamine, vitamine e, provitamine a.",
                        "eau, jus de fruits ?? base de concentr??s: ananas, orange, citron, sirop de glucose,fructose, sucre, jus de de carotte ?? base de concentr??")
                .replace(
                        "jus , pur??e de pomme 83.7 %, jus deoncombre 10 %, pur??e de panais 4 %, jus de citron 1.5 %, fibres d'acacia, ar??me naturel deoncombre, ar??me naturel de menthe,oncentr?? de spiruline ,arthame.",
                        "jus , pur??e de pomme 83.7 %, jus de concombre 10 %, pur??e de panais 4 %, jus de citron 1.5 %, fibres d'acacia, ar??me naturel de concombre, ar??me naturel de menthe,concentr?? de spiruline ,carthame.")
                .replace("deoncentr??", "de concentr??").replace("citron vert0.", "citron vert")
                .replace(" deacao, vitamines,", " de cacao, ").replace("deanne", "de canne")
                .replace("deoncentr??", "de concentr??").replace("vitamine.", "").replace(",,amomille,", ",amomille,")
                .replace(
                        " ar??me naturel issu de l'agriculture   allerg??ne:ontient de l'extrait d'amande am??re.  100% desd'origine agricole sont issus de l'agriculture biologique , 96% duommerce ??quitable",
                        "")
                .replace("ar??me,olorant", "ar??me, colorant")
                .replace(",oncentr?? de jus de raisin ", ", concentr?? de jus de raisin ")
                .replace("olorant: e120.", "colorant: e120.")
                .replace(" prot??ines de lait. huiles v??g??tales, saccharose,",
                        " prot??ines de lait, huiles v??g??tales, ")
                .replace("min??raux.olorant,", "colorant,").replace("orrecteur d'acidit??,", "correcteur d'acidit??,")
                .replace(",olorant", ", colorant").replace("ascorbique,olorant", "ascorbique , colorant")
                .replace("jus de raisin deabern", "jus de raisin de cabern").replace(",orrecteur", ", correcteur")
                .replace("b,teraveoncentr??e", "beteraveoncentr??e").replace(",assis", ", cassis")
                .replace("gent d'enrobage:ire dearnauba,", "").replace("colorants:armin deochenille,", "")
                .replace(
                        "colorants: blanc: e171. turquoise: e131. e132. e171. fuschia: e120. e171.hocolat: e151. e155. e171. vert: e100. e133. e171. agents d'enrobage: e901. e903. e904. ar??me naturel de vanille,",
                        "")
                .replace("agent d'enrobage:ire dearnauba,", "")
                .replace("denr??es alimentairesolorantes:oncentr??s de spiruline , dearthame. amidon de ble.", "")
                .replace(" colorant:aramel ordinaire. ", " colorant: caramel ordinaire. ")
                .replace("colorants:urcumine", "colorants: curcumine")
                .replace(",armins. traces ??ventuelles d'oeufs , de fruits ??oques.", ", carmins.")
                .replace("traces ??ventuelles de lait , fruits ??oque.", "")
                .replace("alimantaireolorante:oncentr?? ", "alimantaire colorante: concentr?? ")
                .replace("colorant:aramel ordinaire.", "colorant: caramel ordinaire.")
                .replace("amidon de pomme de terre. amidon de ble.", "amidon de pomme de terre , amidon de ble.")
                .replace(
                        "agent d'enrobage:ire dearnauba, colorants:urcumine , extrait de paprika , anthocyanes, denr??es alimentairesolorantes:oncentr??s de spiruline , dearthame, amidon de ble. mini sucre, sirop de glucose, eau, g??latine de porc , acidifiant: acideitrique, ar??me naturel, colorant:urcumine, amidon de bleminirocodile: sirop de glucose, sucre, g??latine de porc, acidifiant: acideitrique, ar??mes naturels, ar??me naturel d'orange, ar??me naturel de citron avec autres ar??mes naturels, huile de tournesol, agent d'enrobage:ire dearnauba, colorants:urcumine , extrait de paprika , anthocyanes, denr??es alimentairesolorantes:oncentr??s de spiruline, dearthame , de pomme, amidon de ble.",
                        "")
                .replace(
                        "agent d'enrobage:ire dearnauba, colorants:urcumine ,armins ,aramel ordinaire , extrait de paprika , denr??es alimentairesolorantes:oncentr??s de spiruline, dearthame , de pomme. amidon de bl??.",
                        "")
                .replace("pr??par??s par le proc??d?? de pr??saumurage.", "")
                .replace("pr??par?? par le proc??d?? de pr??saumurage.", "")
                .replace("42% bol,, 25%hampignon noir, 25% pleurote, 8%??pe", "champignons blancs de paris")
                .replace(
                        "fil, de poul,, sel, dextrose de ma??s, jus concentr??s de??leri , de b,terave jaune, extrait de levure, ail 0.1%, ferments,??colorant:aramel ordinaire.",
                        "filet de poulet, sel, dextrose de ma??s, jus concentr??s de beterave jaune, extrait de levure, ail 0.1%, ferments,colorant : caramel ordinaire.")
                .replace(
                        "74 %uisse de poul,, eau, 6% f??cule de pomme de terre, dextrose, prot??ines de soja, stabilisants: e452. e451 , e450. sel, exhausteur de go??t: e621. , e407. sucre, ar??mes, antioxyg??ne: e301 , sel nitrit??, colorant: rouge de b,terave.",
                        "74 cuisse de poulet, eau, 6% f??cule de pomme de terre , dextrose , prot??ines de soja , stabilisants: e452. e451. e450. , sel, exhausteur de go??t: e621. e407, sucre, ar??mes, antioxyg??ne: e301 , sel nitrit??, colorant: rouge de beterave.")
                .replace("plantes aromatiques,onservateur:", "plantes aromatiques , conservateur:")
                .replace(" acidifiants: e262. e575. antioxydant: e301.",
                        " acidifiants: e262. e575 , antioxydant: e301.")
                .replace(" aromates,onservateur: ", " aromates , conservateur: ")
                .replace(",onservateur nitrite de sodiumolorantaramel ordinaire.",
                        ", conservateur nitrite de sodium, colorant caramel ordinaire.")
                .replace(" extrait d'??pice. boyau naturel de porc.", " extrait d'??pice, boyau naturel de porc.")
                .replace(",onservateur nitrite de sodiumolorantaramel ordinaire.", "")
                .replace("fumage au bois de h??tre.  ", "")
                .replace("e330.onservateur: e250.", "e330, conservateur: e250,").replace("porc, ,", "porc,")
                .replace("gras de porc,r??me fra??che, ", "gras de porc , cr??me fra??che , ")
                .replace("onservateur: e250.olorant:aramel", "conservateur: e250, colorant: caramel")
                .replace("fumage au bois de h??tre. ", "").replace("sucre,onservateurs:", "sucre , conservateurs:")
                .replace(
                        " fruits ??oque argopecten purpuratus , origine p??rou,hlamys opercularis , origine royaume,uni: voir l,tre apr??s le num??ro de lot.",
                        " fruits ?? coque  ")
                .replace("onjonctif de porc,", " conjonctif de porc,").replace("ouenne de porc", " couenne de porc")
                .replace("onservateur: nitrite de sodium. peutontenir des traces d'oeuf , de soja.", "")
                .replace("delou de girofle.", "de clou de girofle.").replace(",onservateur: ", ", conservateur: ")
                .replace(" fumage au bois de h??tre", "").replace("traces de soja.", "")
                .replace(",onservateurs:", ", conservateurs:").replace(".onservateurs", ", conservateurs")
                .replace(".olorant:ochenille.", ", colorant: cochenille.")
                .replace("colorant:ochenille.", "colorant: cochenille.")
                .replace("foie de poul,,", "foie de poulet ,")
                .replace("antioxydants: e300 , e301.", "antioxydants: e300 . e301 ,")
                .replace(" sauge,iboul,te,", " sauge, ciboulete,")
                .replace("armagnac, porto. viandes de porc , deanard origine: france.",
                        "armagnac, porto, viandes de porc , de canard origine: france.")
                .replace(",onservateur.", ", conservateur.")
                .replace(" fabriqu?? en france ?? partir de viande de porc origine ue.", "").replace("di, ,", "")
                .replace(". d??cor:r??pine.", "")
                .replace("acidifiant: e325. boyau naturel de porc.", "acidifiant: e325, boyau naturel de porc.")
                .replace(" magr,s deanard", " magr??s de canard").replace("nois,tes", "noisetes")
                .replace(",ognac, ", ", cognac, ")
                .replace(" antioxydant: e301.olorants: e101,e150a,e120. lactose.", "")
                .replace("porc,ouenne,", "porc, couenne,")
                .replace("antioxydants: e316 , e301.", "antioxydants: e316 . e301,")
                .replace("stabilisants: e450 , e452.", "stabilisants: e450 . e452,")
                .replace(",onservateur: e250. ar??mes.", ", conservateur: e250 , ar??mes.")
                .replace("acidifiant: e262.", "acidifiant: e262,").replace(",lous ", ", clous ")
                .replace(", ,", " , ").replace("e325. e,262,", "e325. e262 ,")
                .replace("conservateur: e250. ar??mes,colorant: e120.",
                        "conservateur: e250 ,  ar??mes , colorant: e120.")
                .replace("e250.", "e250 ,").replace("ar??mes,colorant", ", ar??mes , colorant:")
                .replace(
                        "fabriqu?? en france avec de la viande de porcs d'origine: union europ??enne issus d'??levages rigoureusement s??lectionn??s. sans gluten.",
                        "")
                .replace(".olorant:", ", colorant:")
                .replace("conservateurs: e250. e316. e202. stabilisants: e407. e508.",
                        "conservateurs: e250. e316. e202, stabilisants: e407. e508,")
                .replace("peau de poul,,", "peau de poulet ,").replace("viande deanard,", "viande de canard ,")
                .replace("andouill,te", "andouillete").replace("b,terave,", "beterave ,")
                .replace("deampagne", "de campagne").replace(",r??me,", ", cr??me ,").replace("peutontenir ??ufs.", "")
                .replace("conservateurs: e250 , e252. ", "conservateurs: e250 . e252 , ")
                .replace("ferments.", "ferments ,").replace("fil,", "file").replace("/ou", "")
                .replace(",onservateur:", ", conservateur:").replace("peau de poul,.", "peau de poulet ,")
                .replace("tournesol???.", "tournesol").replace(", an,h,iboul,te", "").replace(".uisson", ", cuisson")
                .replace("colorant:urcumine.", "colorant: curcumine ,").replace("derev,te", "de crevete")
                .replace("de??r??alesontenant", "de c??r??alesontenant")
                .replace("colorants:omplexesuivre,", "colorants , ")
                .replace("traces ??ventuelles d'autres??r??alesontenant du gluten, lait,??leri, moutarde.", "")
                .replace(",??leri", ", c??leri").replace("deh??vre,", "de ch??vre ,")
                .replace("traces ??ventuelles gluten, moutarde", "")
                .replace("traces ??ventuelles lait, gluten, moutarde", "")
                .replace(". traces ??ventuelles lait, moutarde, gluten", "")
                .replace("  traces ??ventuelles de gluten.", "")
                .replace(
                        "badigeonnage rougecacao >51% dans lehocolat noiracao >31% dans lehocolat laitacao >26% dans lehocolat blanc",
                        "")
                .replace(".acao 30 % minimum dans lehocolat au lait.", "").replace(". ingredients ", "")
                .replace("8 %ranberries.", "8 % cranberries.").replace("??issus duommerce ??quitable.", "")
                .replace("extrait de vanille.acao: 55% minimum dans lehocolat noir.  produits ", "")
                .replace("??clats dearamel a", "??clats de caramel au").replace(",oncass??es,", ", concass??es ,")
                .replace("laitieroncentr??,", " laitier concentr?? ,")
                .replace("peutontenir de l'??uf , d'autres fruits ??oque.", "")
                .replace("peutontenir autres fruits ??oque , bl??.  ", "")
                .replace(". produit issu de l'agriculture", "").replace(" noix deoco.", " noix de coco ,")
                .replace(
                        " produits  ingr??dientsonformes aux standards duommerce ??quitable fairtrade / max havelaar:acao, sucre.",
                        "")
                .replace("canneompl,,", "canne , ").replace("enacao", "en cacao")
                .replace("colorants: e120 ,e160c.", "colorants: e120 . e160c, ")
                .replace(". gomme arabique", ", gomme arabique").replace("desd'origine agricole sont", "")
                .replace("acao :37% minimum dans lehocolat au lait.", "")
                .replace("f??ves de cacao provenant du programme transparenceacao.", "")
                .replace("fruits ??oque,", "fruits ?? coque ,")
                .replace("traces ??ventuelles d'autres fruits ??oque,??r??alesontenant du gluten , d oeufs", "")
                .replace("lehocolat", "le chocolat")
                .replace(". traces ??ventuelles de fruits ?? coque , de c??r??alesontenant du gluten , d oeuf.", "")
                .replace("traces ??ventuelles d'autres fruits ?? coque ,??r??alesontenant du gluten , d oeufs", "")
                .replace(" traces ??ventuelles d'autres fruits ?? coque ,??r??alesontenant du gluten , oeufs.", "")
                .replace(",urcuma.", ", curcuma.")
                .replace("oseilleoup??e.", "estragon 96 %, huiles de tournesol , deolza.")
                .replace(",iboul,te issus de l'agriculture biologique", "")
                .replace("esdee produit proviennent de diverses origines g??ographiques.", "")
                .replace(
                        "pr??par??e avec 50g de fruits pour 100g. tere totale en sucres: 60g pour 1 oog aonsommer de pr??f??rere",
                        "")
                .replace(
                        "de litchis,onfiture de pamplemousses , de fruits du dragon,onfiture de mangues , de p??ches,onfiture de poires , de mirabelles,onfit",
                        "")
                .replace(
                        "cideitriq pr??par??e avec 59 g de fruitspour 100 g de prod it fini. teneur g totale en sucres 48 pour 100 g de produit peutontenir es traces de lait d",
                        "")
                .replace(
                        "acidifiant: acideitriq pr??par??e avec 59 g de fruitspour 100 g de prod it fini. teneur g totale en sucres 48 pour 100 g de produit peutontenir es traces de lait d , d'autes fruits ??oqbe.onserva inn",
                        "")
                .replace("pectines de fruits. pr??par??e avec 50 g de fruitspour 100g de produit fini.", "")
                .replace(".ontient des ??corces d'orange", "")
                .replace(" pr??par??e avec 54 g de fruits pour 100 g de produit fini.", "")
                .replace(" pr??par??e avec 50 g de fruitspour 100g de produit fini.", "")
                .replace("pr??par??e avec 50 g de fruits pour 100 g.", "")
                .replace("pr??par??e avec 41g de fruits pour 100 g de produit fini. teneur totale en sucres", "")
                .replace(
                        "acidifiant: acideitrique pr??par??e avec 41g de fruits pour 100 g de produit fini. teneur totale en sucres 56 g pour 100 g de produit fini. traces de fruits ??oque , de lait.",
                        "")
                .replace(". produits issus de l'agriculture biologique", "")
                .replace("produit ??labor?? dans un atelier qui utilise: lait, fruits ?? coque ,", "")
                .replace("pr??par??e avec 50 g de fruits pour 100 g.", "")
                .replace("jus de citrononcentr??.", "jus de citron concentr??.")
                .replace(". pr??par??e avec 50 g de fruits pour 100 g de produit fini.", "")
                .replace(
                        ". pr??par??e avec 50g de fruits pour l00 g de produit fini. teneur totale  en sucres 1 62g pour 100 g de produit fini. ",
                        "")
                .replace(". traces ??ventuelles de fruits ??oque , de lait.", "")
                .replace(". traces ??ventuelles de fruits ??oque , de lait.", "")
                .replace(" teneur total en sucre environ 46.9g. sansonservateurs, sans aromes artificiels.", "")
                .replace(
                        " pur??e de tomates doubleoncentr??e, sel, amidon modifi??. oignon grill?? en poudre, ar??me naturel,  ",
                        " pur??e de tomates double concentr??e , sel , amidon modifi?? , oignon grill?? en poudre, ar??me naturel,  ")
                .replace("pr??sence d'd'origine animale: poule", "")
                .replace(". pr??sence d'd'origine animale: poule", "")
                .replace(
                        "haricots vertsoup??s 59%,arottes en rondelles 13%, d??s d'aubergines grill??es 11%, poivrons rouges en d??s 9.3%, huile d'olive vierge extra, tomates s??ch??es en d??s 1.6%, persil, ail, eau, ar??mes naturels, jus de citrononcentr??, sel, antioxydant: acide ascorbique, extrait naturel de romarin.",
                        "haricots vert , soup??s 59% , carottes en rondelles 13% , d??s d'aubergines grill??es 11% , poivrons rouges en d??s 9.3% , huile d'olive vierge extra , tomates s??ch??es en d??s 1.6% , persil , ail , eau , ar??mes naturels , jus de citron concentr?? , sel , antioxydant: acide ascorbique , extrait naturel de romarin.")
                .replace(" peutontenir des traces de gluten.", "")
                .replace("affermissant:hlorure decalcium ", "affermissant: chlorure de calcium ")
                .replace(" acideitrique", " acide citrique").replace(", viande de porc", "")
                .replace(". traces de moutarde.", "").replace("55 %:hou,", "55 %: hou ,").replace("s,,", ",")
                .replace("b,teraves rouges,", "beteraves rouges ,")
                .replace("p,its pois doux,", "petits pois doux ,").replace("p,its pois,", "petits pois ,")
                .replace(
                        "non raffin?? de l'atlantique inqr??dient d'origine aqricole obtenu selon res r??gles de production biologique",
                        "")
                .replace("??paississants:arragh??nanes ,", "??paississants : arragh??nanes ,")
                .replace(". produits ", "").replace("flageol,s,", "flageoles ,")
                .replace("viande deanard graisse deanard,", "viande de canard graisse de canard ,")
                .replace("nav,s, p,its pois", "naves, petits pois")
                .replace("p,its pois tr??s fins", "petits pois tr??s fins")
                .replace("foie gras deanard,", "foie gras de canard ,")
                .replace(".onservateur: e250 ,", ", conservateur: e250 ,")
                .replace(",??ur de b??uf,", ", c??ur de b??uf ,").replace("jeunesarottes.", "jeunes carottes , ")
                .replace(",,", " , ").replace("mog,te", "mogete").replace("p,its pois ,", "petits pois ,")
                .replace(",arottes 12.8%,", ", carottes 12.8% ,")
                .replace(",arottes 9.8%,hampignons de paris 8.6%,", ", carottes 9.8% , champignons de paris 8.6% ,")
                .replace("traces ??ventuelles de lait , de c??r??alesontenant du gluten.", "")
                .replace(",ourg,tes,", ", courgetes ,").replace(". ingr??dient", "")
                .replace(" conservateur: e250 , stabilisants: e451. e452. jusuisin??: eau, sel", "")
                .replace(",oncentr?? de tomate 1.8%,", ", concentr?? de tomate 1.8% ,")
                .replace(", p,its pois 28%,", ", petits pois 28% ,")
                .replace(", p??pites dehocolat 9%,", ", p??pites de chocolat 9% ,")
                .replace("l??gumes 77 %tomates,", "l??gumes 77 %, tomates ,")
                .replace(",arottes 9% , p,its poids 9% ,", ", carottes 9% , petits poids 9% ,")
                .replace(
                        " les informations en gras sont destin??es aux personnes allergiques ou intol??rantes.acao en poudre",
                        "")
                .replace(" peutontenir: des traces de fruits ??oque.", "")
                .replace("de poules ??lev??es en plein air, quelques morceaux de", " , ")
                .replace("beurre a.o.p.harentes", "beurre  ")
                .replace("semouleompl??te de bl?? dur", "semoule compl??te de bl?? dur")
                .replace("peutontenir des traces  d'??ufs.", "").replace("quinoa,arotte,", "quinoa , carotte ,")
                .replace("conservateur: e202. antioxydant: e223. acidifiant: e330.",
                        "conservateur: e202 , antioxydant: e223 , acidifiant: e330 ,")
                .replace("??paississants: e415.onservateur: e202. antioxydant: e223. acidifiants: e330. e260",
                        "??paississants: e415 , conservateur: e202 , antioxydant: e223 , acidifiants: e330. e260 ,")
                .replace(
                        " conservateur: e202. antioxydant: e223. acidifiant: e330. maltodextrine, extrait de poireau",
                        " conservateur: e202 , antioxydant: e223 , acidifiant: e330 , maltodextrine , extrait de poireau")
                .replace("ingredientsrev,tes,", "crevetes ,").replace("crev,tes,", "crevetes ,")
                .replace("crev,tesp??ch??es auhalut ", "crevetes p??ch??es au chalut ")
                .replace("crev,tes88% ", "crevetes 88% ,")
                .replace("crevetes , sel, conservateur: m??tabisulfite.", "")
                .replace("crevetes , sel, antioxydant: disulfite de sodium,acidifiant: acide citrique.",
                        "crevetes , sel , antioxydant: disulfite de sodium , acidifiant: acide citrique.")
                .replace("crev,tes litopenaeus vannamei, sel, conservateur: e223. acidifiant: e330",
                        "crevetes litopenaeus vannamei, sel, conservateur: e223, acidifiant: e330")
                .replace(
                        "crev,tesd'??levage d'equateur enti??rement d??cortiqu??esuites au sel de noirmoutier, sel, ferment lactique",
                        "crevetes d'??levage , sel, ferment lactique")
                .replace(", e407 , e327. e472b,", "").replace(" stabilisants: e407. e460 , e466.", "")
                .replace("??paississants: agar,agar, pectine, ar??me, ferments lactiques.", "")
                .replace(
                        "stabilisants: pectine,arragh??nanes,  e472e, correcteur d'acidit??: e339. ar??me, colorant:arot??no??des.",
                        "")
                .replace("??paississants sin1422 / e1422. sin407 / e407. ??mulsifiant sin471/ e471.", "")
                .replace(
                        ", additifs alimentaires: ??paississants: sin 1422 / e1422. sin 440 / e440. sin 415 / e415. sin 407 / e407.  sin 471 / e471.",
                        "")
                .replace("??  e472e ?? stabilisants: e440. e407 ??orrecteur d'acidit??: e339 ?? ar??me ??olorant: e160a.",
                        "  e472e , stabilisants: e440. e407 , correcteur d'acidit??: e339 , ar??me  , colorant: e160a.")
                .replace(" e471, e472e,", " e471. e472e,")
                .replace("lait ??cr??m??,reme l??g??re ?? 20 %  ", "lait ??cr??me l??g??re ?? 20 % ")
                .replace(" b??ta,carot??ne.", " b??tacarot??ne.")
                .replace(
                        "diglyc??rides d'acides gras. epaississants:gomme xanthane,arragh??nanes. lait d'origine france.",
                        "diglyc??rides d'acides gras , epaississants : gomme xanthane , arragh??nanes, lait d'origine france.")
                .replace(".,", " ,").replace(",aramel aromatique", ", caramel aromatique")
                .replace(", ar??me. fourrage 11.5 %: sucre.", ", ar??me , fourrage 11.5 % , sucre.")
                .replace(
                        ".onseils d'utilisation: r??chauffer ?? la po??le ou au four. a d??guster nature, ?? laonfiture, auhocolat ou flamb??es. produit d??congel?? , ne pas recongeler",
                        "")
                .replace(" vitamine e, vitamine a, ", "").replace(", arragh??nanes, colorant: b,aarot??ne. ", "")
                .replace(", colorants: rocou,aramel ordinaire.", "").replace("colorant:aramel e150c,", "")
                .replace("dur5.9%.issus de l'agriculture biologique", "dur 5.9% ")
                .replace(".issus de l'agriculture biologique,", "")
                .replace(".d'origine agricole obtenus selon les r??gles de la production   ", "")
                .replace(" huile deoco???,", " huile de coco ,")
                .replace(" ??paississants e407. e412, colorant e150c", "")
                .replace("correcteur d'acidit??:itrates de sodium", "correcteur d'acidit?? : nitrates de sodium")
                .replace("pourcentage mis en ??uvre pourhaque parfum.", "")
                .replace(". ontient les sucres naturellement pr??sents dans les fruits  ", "")
                .replace("  pommes  pur??e de pommes: 69.8%, pur??e d'abricots 30%, ar??mes naturels,", "")
                .replace(" vitamine,", "").replace(" issus de l'agriculture biologique", "")
                .replace("base de lait deoco sp??cialit?? ferment??e ?? base de lait deoco,",
                        "base de lait de coco sp??cialit?? ferment??e ?? base de lait de coco,")
                .replace(
                        ", fabriqu?? dans un atelier utilisant des fruits??oques , des produits laitiers.onvient dans leadre dune alimentation ??quilibr??e enal??um",
                        "")
                .replace(
                        "raffin?? de l'atlantique.100% desd???origine agricole ont ??t?? obtenus selon les r??gles de production",
                        "")
                .replace(" vitamines b1. b6 , e.", "").replace("vitamines b1. b6. b9 , e,", "")
                .replace("ar??me vanille. sach, d'??clats dearamel 4g.",
                        "ar??me vanille, sach, d'??clats de caramel 4g.")
                .replace(". , produits  ", "").replace(",aramel 8.1%,", ", caramel 8.1%,")
                .replace(",r??me pasteuris??e.", "cr??me pasteuris??e.").replace("??le flottante: ", "")
                .replace(", correcteur d'acidit??.", "")
                .replace(
                        ".orrecteur d'acidit??: e330. antioxydant: e300. ganache auhocolat 27 %:r??me,hocolat noir 33 %,acao 1.3 %.",
                        "")
                .replace(",aramel,", ", caramel ,")
                .replace(".  lait ,r??me origine: france.", ",  lait cr??me origine: france.")
                .replace(". les ferments sontultiv??es sur des bases v??g??tales", "")
                .replace(", jus de citrononcentr??,", ", jus de citron concentr?? ,")
                .replace("specialite de pommmehassion sans sucres ajoutls ngredients:", "")
                .replace("sans gluten.", "").replace(", jus de citrononcentr??,", ", jus de citron concentr?? ,")
                .replace("sp??cialit?? de fruits", "")
                .replace("pomme fraise sans sucres ajout??s&quot", "pomme , fraise ")
                .replace("antioxydant: acide ascorbique. pomme pomme 74.9 %, poire 24.9 %, ar??me naturel,",
                        "antioxydant: acide ascorbique, pomme 74.9 % , poire 24.9 % , ar??me naturel ,")
                .replace(",alimentairesolorants,", " , ")
                .replace(" jus de citrononcentr??", " jus de citron concentr??")
                .replace(" ar??me. fines feuilles de cacao: p??te de cacao, beurre de cacao, ar??me, ??dulcorants.",
                        " ar??me , fines feuilles de cacao: p??te de cacao, beurre de cacao, ar??me, ??dulcorants.")
                .replace(", edulcorants ,acesulfame k,yclamates, neohesperidine dc.", "")
                .replace(" correcteur d'acidit??: hydroxyde de sodium.", "")
                .replace(" ??paississant: e407. min??raux du lait,", " ??paississant: e407 , min??raux du lait,")
                .replace(
                        "sucr??e,ube de pomme semi,confit,ube de fraise semi,confite 31 %,ompot??e de rhubarbe 26.5 %, ",
                        "sucr??ecube de pomme semi, confitube de fraise semi , confite 31 % , compot??e de rhubarbe 26.5 %, ")
                .replace("p??ches bio,erises bio", "p??ches bio , cerises bio").replace("issu de l'agriculture ", "")
                .replace("  origine: france.", "")
                .replace("g??lifiants: e407 , e415. sel", "g??lifiants: e407 . e415 , sel")
                .replace(",itron 10 %,", ", citron 10 % ,")
                .replace("lait deoco, eau deoco", "lait de coco, eau de coco")
                .replace("lait deoco. eau deoco,", "lait de coco, eau de coco,")
                .replace("sucre de canner??me3 %,", "sucre de canne , cr??me3 %,")
                .replace(",hocolat en poudre 10%,", ", chocolat en poudre 10% ,")
                .replace(" 'issus de l'agriculture biologique", "").replace("amidon.r??me", "amidon cr??me")
                .replace(
                        "chlorur??s silice: 7.5 mg/l, r??sidu sec ?? 1800c: 32mgli , ph: 618 r??sistivit??: 30 000 q.cm , rh2 25 95 ",
                        " eau ")
                .replace(".ertains ingr??dient dee produit ne proviennent pas de france.", "")
                .replace("ar??meitron vert", "ar??me citron vert")
                .replace(
                        ". la f??cule de pomme de terreontribue ?? am??liorer la qualit?? de votre emmental rap?? pr??sident, en limitant la formation de mottes pour un saupoudrage id??al. afinage minimum 50 jours. aonserver entre +4??c , +8??c. afin de mieux profiter de toute la saveur , du moelleux de votre emmental pr??sident, nous vousonseillons de leonsommer dans les 6 jours suivant l'ouverture du sach,",
                        "")
                .replace(",oagulant.", ", coagulant.").replace(" affinage minimum 50 jours.", "")
                .replace("laitru,", "lait cru,")
                .replace(" pr??sure lait issu de l'aire g??ographique d??finie par l'igp.", "")
                .replace("avec autres ar??mes naturels 4.4 %, ", "")
                .replace(
                        ". aonsommer de pr??f??rence avant le / num??ro de lot: voirouvercle. aonserver ?? l'abri de lahaleur, de la lumi??re , de l'humidit??. apr??s ouverture, ??onserver dans son liquide au r??frig??rateur une semaine maximum. poids n, ??goutt??: poids n,: 700g 390g",
                        "")
                .replace(
                        "n oivron de tournesol pts paprika%0booriandrild,ec??leri oivr , t ??ventuellesdebl??,loit, outdt inforut10nsi??consommer qregi??ons ?? iii il i i iii iii 3 250391 967827",
                        ", tournesol ,")
                .replace(",arottes en morceaux,", ", carottes en morceaux ,")
                .replace("poivreissus de l???agriculture biologique ", "").replace(",arottes,", ", carottes ,")
                .replace(
                        " acidifiant: e330. moutarde de dijon, conservateur: e202. ??paississant: e415. poivre blanc.",
                        " acidifiant: e330 , moutarde de dijon, conservateur: e202 , ??paississant: e415 , poivre blanc.")
                .replace("deconcom8ht5itron ?? base deoncentr'??,r??me fra??che, {l,", "")
                .replace(" piment d'espel,te ,", " piment d'espelete,")
                .replace("mi,r??duite 56.7%", "mie r??duite 56.7%")
                .replace("real issu duommerce ??quitable de bolivie , de l'agriculture ", "")
                .replace(", issue de l'agriculture ", "")
                .replace("ingr??dient: ??peautreompl,a issude l'agriculture ", "??peautre")
                .replace(",r??me pasteuris??s,", "cr??me pasteuris??s,").replace(" lactiques.des ??pices ", "")
                .replace(",r??me de lait,", "cr??me de lait ,")

                /* =============Dimitri 3_351-6_700===================================== */
                .replace("ar??me naturel de vanille.", "ar??me naturel de vanille,")
                .replace("cr??me, poudre de babeurre, g??lifiants: e407 et e415. sel.",
                        "cr??me, poudre de babeurre, g??lifiants: e407 et e415, sel")
                .replace("noix de coco r??p??e 1%, ar??mes naturels. jus concentr?? de citron,",
                        "noix de coco r??p??e 1%, ar??mes naturels: jus concentr?? de citron,")
                .replace("citron: jus de soja 78 %,", "jus de soja 78 %,")
                .replace(
                        "ferments s??lectionn??s. conforme ?? la norme nf v 29,001. graines de soja fili??re fran??aise garanties sans ogm.",
                        "ferments s??lectionn??s. conforme ?? la norme nf v 29.001, graines de soja fili??re fran??aise garanties sans ogm.")
                .replace(
                        "jus de soja 85%, sucre, ??paississants: amidon transform?? de ma??s sans ogm , , carragh??nanes pectines , gomme guar, phosphate de calcium, concentr??s v??g??taux, ar??me naturel, sel. soja fili??re fran??aise, garanti sans ogm.",
                        "jus de soja 85%. soja fili??re fran??aise, garanti sans ogm, sucre, ??paississants: amidon transform?? de ma??s sans ogm, carragh??nanes pectines , gomme guar, phosphate de calcium, concentr??s v??g??taux, ar??me naturel, sel")
                .replace("colorant: extrait de paprika, vitamine d.", "colorant: extrait de paprika")// ?
                .replace(
                        "jus de soja1 86%, sucre de canne 9.5%, amidon, ar??mes naturels de vanille, concentr??s de carotte et de pomme, ??paississant: carragh??nanes, sel.  issu de l???agriculture biologique 1soja sans utilisation d???ogm conform??ment ?? la r??glementation en vigueur sur le mode de production biologique.",
                        "jus de soja1 86% . issu de l???agriculture biologique, sucre de canne 9.5%, amidon, ar??mes naturels de vanille, concentr??s de carotte et de pomme, ??paississant: carragh??nanes, sel")
                .replace(
                        "tonyu 74.7 %, sucre 11.6 %, framboise 10 %, amidon modifi??, phosphate de calcium, jus concentr?? de carotte pourpre, ar??me naturel, sel, ferments, vitamine d.",
                        "tonyu 74.7 %, sucre 11.6 %, framboise 10 %, amidon modifi??, phosphate de calcium, jus concentr?? de carotte pourpre, ar??me naturel, sel, ferments.")// ?
                .replace("sel, ferments, vitamine d2.", "sel, ferments")// ?
                .replace(
                        "jus de soja1 77%. issu de l???agriculture biologique, sucre de canne, poudre de cacao maigre, chocolat 1.5%, chocolat en poudre 1.5%, amidon, ??paississant: carragh??nanes, sel, ar??me naturel.  issu de l???agriculture biologique 1soja sans utilisation d???ogm conform??ment ?? la r??glementation en vigueur sur le mode de production biologique.",
                        "jus de soja1 77%, sucre de canne, poudre de cacao maigre, chocolat 1.5%, chocolat en poudre 1.5%, amidon, ??paississant: carragh??nanes, sel, ar??me naturel ")
                .replace(" caf?? lyophilis?? 0.2 %, sel, vitamine d2.", " caf?? lyophilis?? 0.2 %, sel")// ?
                .replace(
                        "jus de soja  79%, sucre de canne 9.4%, pur??e d???abricot 5%, amidon, jus concentr?? de carotte, ??paississant: pectines, ar??me naturel, sel, ferments.  issu de l???agriculture biologique soja sans utilisation d???ogm conform??ment ?? la r??glementation en vigueur sur le mode de production biologique.",
                        "jus de soja  79%. issu de l???agriculture biologique, sucre de canne 9.4%, pur??e d???abricot 5%, amidon, jus concentr?? de carotte, ??paississant: pectines, ar??me naturel, sel, ferments. ")
                .replace(
                        "jus de soja 80%, sucre de canne 9.9%, citron 3.9%, amidon, sel, ??paississant: pectines, ar??me naturel, ferments.  issu de l'agriculture biologique / soja sans utilisation d'ogm conform??ment ?? la r??glementation en vigueur sur le mode de production biologique. traces ??ventuelles de fruits ?? coque.",
                        "jus de soja 80%. issu de l'agriculture biologique, sucre de canne 9.9%, citron 3.9%, amidon, sel, ??paississant: pectines, ar??me naturel, ferments")
                .replace(
                        "morceaux d???abricot: jus de soja 75.1%, sucre 11%, abricot 10%, amidon, amidon modifi??, phosphate de calcium, ??paississants: pectine ??? gomme guar, sel, ar??me naturel, ferments, colorant: extrait de paprika, vitamine d2. morceaux de p??che: jus de soja 75.1%, sucre 11.9%, p??che 10%, amidon, amidon modifi??, phosphate de calcium, ar??me naturel, concentr??s de carotte, ??paississants: pectine ??? gomme guar, sel, ferments, vitamine d2. morceaux d???ananas: jus de soja 71.5%, sucre 12.7%, ananas 10%, amidon, phosphate de calcium, amidon modifi??, ar??me naturel, ??paississants: pectine ??? gomme guar, sel, ferments, concentr?? de carthame, vitamine d2.",
                        "morceaux d???abricot: jus de soja 75.1%, sucre 11%, abricot 10%, amidon, amidon modifi??, phosphate de calcium, ??paississants: pectine ??? gomme guar, sel, ar??me naturel, ferments, colorant: extrait de paprika, vitamine d2. morceaux de p??che: jus de soja 75.1%, sucre 11.9%, p??che 10%, amidon, amidon modifi??, phosphate de calcium, ar??me naturel, concentr??s de carotte, ??paississants: pectine ??? gomme guar, sel, ferments, morceaux d???ananas: jus de soja 71.5%, sucre 12.7%, ananas 10%, amidon, phosphate de calcium, amidon modifi??, ar??me naturel, ??paississants: pectine ??? gomme guar, sel, ferments, concentr?? de carthame")
                .replace(" amidon de ma??s. amidon de pomme de terre", " amidon de ma??s, amidon de pomme de terre")
                .replace("vitamine d. traces ??ventuelles de fruits ?? coque.",
                        "traces ??ventuelles de fruits ?? coque")
                .replace("amidon de ma??s et amidon transform?? de tapioca,",
                        "amidon de ma??s, amidon transform?? de tapioca,")
                .replace("fraise: ", "").replace("framboise: ", "")
                .replace("concentr?? de carotte et de cassis", "concentr?? de carotte, concentr?? de cassis")
                .replace("p??ches: ", "")
                .replace("les ingr??dients de ces produits ne proviennent pas de france.", "")
                .replace(" acide ascorbique. non", " acide ascorbique")
                .replace(". contient plus de 85% d'humidit??.", ", contient plus de 85% d'humidit??.")
                .replace("pomme: ", "").replace("poire: ", "").replace("banane: ", "").replace("abricots:", "")
                .replace(".  ingr??dients issus de l'agriculture biologique", "")
                .replace("prot??ines de lait.traces ??ventuelles de soja.dessert",
                        "prot??ines de lait. traces ??ventuelles de soja, dessert")
                .replace("oui= traces ??ventuelles de soja pour la r??f??rence chocolat", "")
                .replace(". les ingr??dients de ces produits ne proviennent pas de france.", "")
                .replace(". ingr??dients issus de i'agriculture biologique.", "")
                .replace("pomme p??che abricot + ac??rola:", "")
                .replace("colorant: carot??no??des ??? ferments lactiques.",
                        "colorant: carot??no??des, ferments lactiques,")
                .replace("vitamine d. peut contenir des traces d'autres c??r??ales contenant du gluten.", "")
                .replace("agar agar ingredients agricoles issus de l'agriculture biologique", "")
                .replace(", et", ", ").replace("bio = issu de l'agriculture biologique", "")
                .replace(
                        "lait entier biochocolat en poudre bio 10.9 % sucre de canne bio cr??me biopoudre de lait ??cr??m?? bio amidon bio",
                        "lait entier, biochocolat en poudre bio 10.9 %, sucre de canne bio, cr??me biopoudre de lait ??cr??m?? bio, amidon bio")
                .replace("??paississant e407. gaz propulseur e942.", "??paississant e407, gaz propulseur e942.")
                .replace("lactose et min??raux du lait", "lactose, min??raux du lait")
                .replace("??paississant: e407. prot??ines de lait", "??paississant: e407, prot??ines de lait")
                .replace(", vitamine d.", "").replace(", ingr??dients caramel:", "")
                .replace("ingr??dients chocolat:", "")
                .replace(
                        "lait entier 79.7% 6???  sucre blond de canne , chocolat en poudre 5 %,  , amidon de manioc ??? ??paississant: pectine.  ingr??dients d'origine agricole issus de l'agriculture biologique",
                        "lait entier 79.7%,  sucre blond de canne , chocolat en poudre 5 %, amidon de manioc, ??paississant: pectine.")
                .replace("sel.", "sel").replace("vitamine d2.", "").replace(". traces ??ventuelles de soja", "")
                .replace(
                        "ingredients c tea desemouamidon transform??.??paississant: gomme xanthane semolina cake te de cacao, su ",
                        "th??, amidon, ??paississant: gomme xanthane, gateau de cacao, sucre")
                .replace("(p", ", p")
                .replace("??mulsifiants: e472b, e471. stabilisants: e407. e412.",
                        "??mulsifiants: e472b. e471, stabilisants: e407. e412,")
                .replace("colorants: e100. e120. e131. ", "colorants: e100. e120. e131,")
                .replace("ac??sulfame,k.", "ac??sulfame k.").replace("citron et citron vert", "citron , citron vert")
                .replace("saint,pierre", "saint pierre")
                .replace("??dulcorants: sucralose et ac??sulfame,k.", "??dulcorants: sucralose , ac??sulfame k.")
                .replace("??dulcorants: sucralose et ac??sulfame k.", "??dulcorants: sucralose , ac??sulfame k.")
                .replace("acidifiant: e330.  conservateurs: e202. e242.",
                        "acidifiant: e330 ,  conservateurs: e202. e242.")
                .replace("saint,georges 1.", "saint georges")
                .replace(
                        "chlorur??s silice: 7.5 mg/l r??sidu sec ?? 1800c: 32mgli , ph: 618 r??sistivit??: 30 000 q.cm , rh2 25 95  ??? i sur la boulell\\e.",
                        "chlorur??s silice: 7.5 mg/l, r??sidu sec ?? 1800c: 32mgli , ph: 618 r??sistivit??: 30 000 q.cm , rh2 25 95 ")
                .replace(". origine: pradesfrance.", ", origine: pradesfrance.").replace(". non", "")
                .replace("co2. ", "co2, ")
                .replace("certains ingr??dient de ce produit ne proviennent pas de france.", "")
                .replace("eau min??rale naturelle naturellement gazeuse",
                        "eau min??rale naturelle, naturellement gazeuse")
                .replace(". colorant:", ", colorant:").replace("pr??sure.", "pr??sure")
                .replace("ar??me naturel d'??pices et autres ar??mes naturels.",
                        "ar??me naturel d'??pices , autres ar??mes naturels.")
                .replace(".  biologique. peut contenir des traces de lait, de moutarde et de c??leri.",
                        ",  biologique.")
                .replace(
                        "n oivron de tournesol pts paprika%0bo coriandrild,ec??leri oivr , t ??ventuellesdebl??,loit, outdt inforut10nsi??consommer qregi?? cons ?? iii il i i iii iii 3 250391 967827",
                        "poivron de tournesol , paprika , coriandre, c??leri , poivre , traces ??ventuels de bl?? , lait")
                .replace("ingr??dient issu de l'agriculture biologique.", "")
                .replace(" les informations en gras sont destin??es aux personnes intol??rantes ou allergiques.", "")
                .replace("  traces ??ventuelles de graines de s??same.", "")
                .replace("traces ??ventuelles de c??r??ales contenant du gluten et de c??leri.", "")
                .replace(".ingr??dient issu de l agriculture biologique.", "")
                .replace("penicilium roqueforti et chlorure de calcium.",
                        "penicilium roqueforti , chlorure de calcium.")
                .replace("sels de fonte: e452. e450.", "sels de fonte: e452. e450,")
                .replace("ingr??dients:iait (98%}, sel , ferments lactiques,  coagulant",
                        "lait 98%, sel , ferments lactiques,  coagulant")
                .replace("vitamines: a, b2. b12", "").replace("ingr??dient issu de l'agriculture biologiq, ue.", "")
                .replace(". confiture artisanale.", ", confiture artisanale.")
                .replace(
                        "asteurized milk, pareurzec cream, pepper 5%, sait, best before: see original label refrigerated between +2'<, , 71,c , ?e??ed in protective atmosphere.to ee within 3 days after opening. ingredi??nten: gepasteuriseerde melk gepasteuriseerde room, peoc:r melkkiemen, strernsel. , vekpa'gt cn beschermende atmofeer. dagen na het. zutaten: pasteurisierter frischt<??i?? 'milch pfeffer 596. salz. mindenstens 72: fett i. tr. unter verpacvt. geniessen sie von 3 tagen nach dem otinen.",
                        "lait, poivron ,creme ")
                .replace("ingr??dients des ??pices et aromates ::", ",").replace("e160b, e120.", "e160b. e120,")
                .replace("colorants: e160b, e120", "colorants: e160b. e120")
                .replace("conseils de conservation: avant ouverture: craint la chaleur et l'humidit??.", "")
                .replace(
                        "peut contenir des traces de gluten, arachides, soja, lait, autres fruits ?? coque et c??leri.",
                        "")
                .replace(
                        "traces ??ventuelles d'arachide, de fruits a coque, de graines de s??same, de c??r??ales contenant du gluten, d'anhydride sulfureux et de soja.",
                        "")
                .replace(
                        "traces ??ventuelles fabriqu?? dans un atelier utilisant du gluten, des arachides, du soja, du lait et d'autres fruits ?? coque.",
                        "")
                .replace(
                        "traces ??ventuelles de c??r??ales contenant du gluten, autres fruits ?? coque, arachides et soja.",
                        "")
                .replace("origine bolivie", "").replace("??mulsifiants:", "")
                .replace("et morceaux de cookies au chocolat 4 %.", ", morceaux de cookies au chocolat 4 %.")
                .replace("??mulsifiant:", "").replace("enrobage ananas:", "")
                .replace("poudres ?? lever: e450. e500.", "poudres ?? lever: e450. e500,")
                .replace("de soja et de graines de s??same", "de soja , de graines de s??same")
                .replace(", (noix ", ", noix ").replace("(lait", ",lait")
                .replace("cannelle oeuf entier", "cannelle, oeuf, entier,")
                .replace("beurre. caramel", "beurre, caramel").replace("mono,e", "mono")
                .replace(", blanc d'??uf.", ", blanc d'??uf,")
                .replace(
                        "a consomme e pr?? ance avantlu dele ir liqu??e sur le c??t?? du paquet. co orve dans u eg frais ei sec, ?? abri de lumi??re.",
                        "")
                .replace("issu d'animaux sans traitement antibiotique d??s la fin du sevrage et nourris sans ogm",
                        "")
                .replace("frais issu d'animaux nourris sans ogm", "")
                .replace(". antioxydant: e316.", ", antioxydant: e316,")
                .replace("antioxydant: e316.", "antioxydant: e316,")
                .replace("stabilisants: e450,e451,e452.", "stabilisants: e450. e451. e452,")
                .replace("exhausteur de go??t: e621. acidifiant: e262.",
                        "exhausteur de go??t: e621, acidifiant: e262.")
                .replace("biologique.", "")
                .replace(
                        " l'action des ferments g??n??re l'apparition de nitrites d'origine v??g??tale pour pr??server toutes les qualit??s du produit. ce mode d'??levage garantit le bien,??tre de l'animal durant toute sa croissance et une alimentation vari??e ?? base de c??r??ales.",
                        "")
                .replace(
                        "l'action des ferments g??n??re l'apparition de nitrites d'origine v??g??tale pour pr??server toutes les qualit??s du produit.",
                        "")
                .replace(
                        "la mati??re premi??re jambon ainsi que les ??pices et les plantes aromatiques ont ??t?? obtenues selon le mode de production de l'agriculture biologique.",
                        "")
                .replace("antioxydant: e316.", "antioxydant: e316,")
                .replace("antioxydant: e316. conservateur: e250", "antioxydant: e316 , conservateur: e250")
                .replace("conservateur: e250. antioxydant: e316. stabilisant: e451.",
                        "conservateur: e250, antioxydant: e316, stabilisant: e451,")
                .replace("peut contenir des traces de lait et de moutarde.", "")
                .replace("s??ch?? et affin?? dans le bassin de l'adour", "")
                .replace("ascorbate de sodium. chorizo:", "ascorbate de sodium,")
                .replace("conservateur: e252. pr??parations aromatisantes.", "conservateur: e252,")
                .replace(" vitamine c", "").replace(" vitamines: c, b9.", "").replace("vitamine c, vitamine b9", "")
                .replace("vitamine: c, b9", "")
                .replace(" les oranges de ce produit ne proviennent pas de france.", "")
                .replace(". biologique.", ", biologique.")
                .replace(". } ingr??dients issus de l'agriculture biologique", "")
                .replace("ce jus est sans additif, et sans conservateur, conform??ment ?? la r??glementation.", "")
                .replace("abricot 20.4% et kiwi 4.8%.", "abricot 20.4% , kiwi 4.8%.")
                .replace("ingr??dient issu de l'agriculture biologique", "")
                .replace("vitamines c, e, b1. b9 et provitamine a.", "")
                .replace(" une cuill??re de pur??e de pomme pour la texture et une pointe de gingembre.",
                        "  pur??e de pomme  , pointe de gingembre.")
                .replace("poudres ?? lever:", "").replace("issu d'animaux nourris sans ogm < 0.9 %", "")
                .replace(
                        "huile vierge de twrnesot' 35 huile d'olive vierge extra' 5 hui!e nierge cie tournesol citron 0.35 ol??iquei ar??me rot ingr??dients issus de itog?? biologique.",
                        "huile vierge de tournesot, 35 huile d'olive vierge extra 5 huile, citron 0.35")
                .replace("vitamines: b1. b2. b3. b5. b6. b8. b9. c, e, pro,vitamine a.", "")
                .replace(", gluten de bl?? ??", ", gluten de bl?? ,")
                .replace("vinaigre de cidre ??", "vinaigre de cidre ,").replace("farine d'orge??? ", "farine d'orge, ")
                .replace("??mulsifiant e471.", "??mulsifiant e471,")
                .replace(
                        "teneurs pour / durchschnittliche n??hrterte pro / gemiddeide v oedingswaarden per i av erage nutritional value per 100g de pain cuit: energie / energetyaarde / energy 1073kj ,",
                        "farine compl??te de bl??, son d'avoine")
                .replace("farine de bl?? type 65. eau, farine d?????peautre type 110. levain,",
                        "farine de bl?? type 65, eau, farine d?????peautre type 110, levain,")
                .replace("bl??/", "bl??")
                .replace(
                        "farine de bl?? 54 %/, eau, huile de colza. levure, sucre de canne roux, graines de tournesol d??cortiqu??es 3.3 %. ",
                        "farine de bl?? 54 %, eau, huile de colza. levure, sucre de canne roux, graines de tournesol d??cortiqu??es 3.3 %, ")
                .replace("farine de bl?? t65.", "farine de bl?? t65,")
                .replace(" conservateur: e282.", " conservateur: e282,")
                .replace("ingr??dients/ingredientes/ingredient", "")
                .replace(", et diglyc??rides d'acides gras traces ??ventuelles de lait et de fruits ?? coque.",
                        ",  diglyc??rides d'acides gras traces ??ventuelles de lait , de fruits ?? coque.")
                .replace(".  ingr??dients issus de l'agriculture biologique.", "")
                .replace(
                        "ingh??dients & nutrition m??lange ??quilibr?? pour la pr??paration de pain de campagne ingr??dients:",
                        "")

                /* =============Remi 6_701-10_050======================================== */

                .replace("ma??s 70%, sucre 28 %, extrait de malt d'orge, sel.",
                        "ma??s 70%, sucre 28 %, extrait de malt d'orge, sel,")
                .replace(
                        "sirop de malt d'orge bio, ar??me naturel d'abricot avec autres ar??mes naturels,  sel de mer, ??mulsi???ant: l??cithines de tournesol",
                        "sirop de malt d'orge bio, ar??me naturel d'abricot avec autres ar??mes naturels,  sel de mer, ??mulsi???ant: l??cithines de tournesol.")
                .replace(" pr??paration ?? base d'huile v??g??tale, sel, vitamines: niacine, e, b6.",
                        " pr??paration ?? base d'huile v??g??tale, sel")
                .replace(
                        "colorant: carot??no??des, antiagglom??rant carbonate de calcium, ??mulsifiant l??cithines de tournesol, ar??me, antioxydant tocoph??rols.",
                        "colorant: carot??no??des, antiagglom??rant carbonate de calcium, ??mulsifiant l??cithines de tournesol, ar??me, antioxydant tocoph??rols,")
                .replace(
                        "pr??paration ?? base d'huile v??g??tale, sel, vitamines: niacine, e, b6, riboflavine, thiamine, acide folique, b12. fer.",
                        "pr??paration ?? base d'huile v??g??tale, sel, vitamines: niacine, e, b6, riboflavine, thiamine, acide folique, b12, fer.")
                .replace(
                        "??mulsifiant: mono, et diglyc??rides d'acides gras, vitamines c, niacine, acide pantoth??nique, b6. riboflavine",
                        "??mulsifiant: mono, et diglyc??rides d'acides gras, vitamines c, niacine, acide pantoth??nique, b6, riboflavine")
                .replace(
                        "graines de lin brin 11%, fruits secs, sucre de coco 6.3%, huile de coco 6.3%, noix de coco 2.1%, sel, poudre de vanille.:",
                        "graines de lin brin 11%, fruits secs, sucre de coco 6.3%, huile de coco 6.3%, noix de coco 2.1%, sel, poudre de vanille:")
                .replace(
                        "farine de bl?? sucre: huile de palme, lait en poudre ??cr??m??, poudres ?? lever: e503. e500. e450. sel,",
                        "farine de bl?? sucre: huile de palme, lait en poudre ??cr??m??, poudres ?? lever: e503, e500, e450, sel,")
                .replace("vitamines: c, niacine, b6. riboflavine, thiamine, acide folique, b12.", "")
                .replace(
                        "flocons d'??peautre 45%, flocons d'avoine, abricots 8%, graines de tournesol, pruneaux 7%, pommes 6%, amandes 5%, noix de p??can.",
                        "flocons d'??peautre 45%, flocons d'avoine, abricots 8%, graines de tournesol, pruneaux 7%, pommes 6%, amandes 5%, noix de p??can,")
                .replace(
                        "flocons d'avoine complet 35%, sucre, flocons de bl?? complet??? 13%, farine de bl?? 11.9%, chocolat noir 11%, huile de tournesol, farine de riz, noix de coco en poudre, cacao en poudre 1.6%, miel, sel, extrait de malt d'orge, ??mulsifiant: l??cithines de tournesol, antioxydant: extrait riche en tocoph??rol",
                        "flocons d'avoine complet 35%, sucre, flocons de bl?? complet??? 13%, farine de bl?? 11.9%, chocolat noir 11%, huile de tournesol, farine de riz, noix de coco en poudre, cacao en poudre 1.6%, miel, sel, extrait de malt d'orge, ??mulsifiant: l??cithines de tournesol, antioxydant: extrait riche en tocoph??rol.")
                .replace(
                        "c??r??ales compl??tes 67.4%, caramel 20.4% cr??me caramel au beurre sal?? 11.6%, caramel 8.8%, huile de tournesol, sucre de canne, sel de mer // ingr??dients issus de l???agriculture biologique",
                        "c??r??ales compl??tes 67.4%, caramel 20.4% cr??me caramel au beurre sal?? 11.6%, caramel 8.8%, huile de tournesol, sucre de canne, sel de mer // ingr??dients issus de l???agriculture biologique.")
                .replace(
                        "superfruits 9.3 % cranberries 4.3 %, jus concentr?? de grenade 3.8 %, cassis lyophilis??s 0.6%, myrtilles lyophilis??es 0.6 %",
                        "superfruits 9.3 % cranberries 4.3 %, jus concentr?? de grenade 3.8 %, cassis lyophilis??s 0.6%, myrtilles lyophilis??es 0.6 %.")
                .replace(
                        "flocons d'avoine 54%, sucre de canne complet, huile de tournesol, sirop de riz, billettes de riz, amandes 4%, tapioca",
                        "flocons d'avoine 54%, sucre de canne complet, huile de tournesol, sirop de riz, billettes de riz, amandes 4%, tapioca.")
                .replace("sarrasin 99%, sel", "sarrasin 99%, sel.")
                .replace(" noisettes toast??es, pommes s??ch??es 1%, noix de coco s??ch??es 1%, figues s??ch??es",
                        " noisettes toast??es, pommes s??ch??es 1%, noix de coco s??ch??es 1%, figues s??ch??es.")
                .replace("millet", "millet.")
                .replace(
                        "sucre farine de bl?? oeuf sirop de glucose fructose graisses v??g??taleslait entier concentr?? sucr?? 8.5%humectantbeurre de cacao lait ??cr??m?? en poudre 3.2%p??te de cacao lactoserum en poudre de lait beurre concentr?? ??mulsifiantalcool sel poudre ?? leverar??mes amidon de froment  peut contenir fruit ?? coque",
                        "sucre , farine de bl??, oeuf ,sirop de glucose, fructose ,graisses v??g??tales,lait entier  concentr?? sucr?? 8.5% ,humectant , beurre de cacao lait ??cr??m?? en poudre 3.2% , p??te de cacao , lactoserum en poudre de lait , beurre , concentr?? ??mulsifiant , alcool, sel, poudre ?? lever , ar??mes amidon de froment  peut contenir fruit ?? coque")
                .replace("noix de coco toast??e en lamelles 4%. produits issus de l'agriculture biologique",
                        "noix de coco toast??e en lamelles 4%.")
                .replace("flocons d'avoine complets", "flocons d'avoine complets.")
                .replace("flocons de bl??, flocons d'orge?? p??pites de chocolat noir 10%,",
                        "flocons de bl??, flocons d'orge, p??pites de chocolat noir 10%,")
                .replace("ingr??dients issus de l'agriculture biologique.", "")
                .replace(
                        "c??r??ales 65.2%, sucre de canne, huile de tournesol, noix de coco, sirop de riz. ingr??dients issus de l???agriculture biologique.",
                        "c??r??ales 65.2%, sucre de canne, huile de tournesol, noix de coco, sirop de riz,")
                .replace(
                        "sucre de canne, huile de tournesol, morceaux de chocolat noir 5.1 %, sirop de riz, cacao maigre en poudre. ingr??dients issus de l???agriculture biologique.",
                        "sucre de canne, huile de tournesol, morceaux de chocolat noir 5.1 %, sirop de riz, cacao maigre en poudre.")
                .replace(
                        " chocolat 3.9 % (sucre de canne sirop de malt d'orge , sucre de canne, sirop de bl??, p??te de cacao , beurre de cacao,",
                        " chocolat 3.9 % , sucre de canne , sirop de malt d'orge , sucre de canne, sirop de bl??, p??te de cacao , beurre de cacao,")
                .replace(
                        "sarrasin, datte, noisette 5%, ??clats de f??ve de cacao cru 3.9%, poudre de cacao cru 1.6%, poudre de caroube crue, vanille",
                        "sarrasin, datte, noisette 5%, ??clats de f??ve de cacao cru 3.9%, poudre de cacao cru 1.6%, poudre de caroube crue, vanille.")
                .replace(
                        "fibre de guar, ar??me naturel, farine de seigle.  ingr??dients d'origine agricole obtenus selon le mode de production biologique.",
                        "fibre de guar, ar??me naturel, farine de seigle.")
                .replace(
                        "blancs d?????ufs frais ar??mes naturels, sel, ??mulsifiants, sirop de sucre inverti, prot??ines de lait, ar??me, levure d??sactiv??e, colorant.",
                        "blancs d?????ufs frais ,ar??mes naturels, sel, ??mulsifiants, sirop de sucre inverti, prot??ines de lait, ar??me, levure d??sactiv??e, colorant.")
                .replace(
                        "farine de bl?? 1.7 %, farine de riz, chocolat au lait 0.7 %, farine de soja, miel. ingr??dients agricoles issus de l'agriculture biologique.",
                        "farine de bl?? 1.7 %, farine de riz, chocolat au lait 0.7 %, farine de soja, miel.")
                .replace(
                        "poudre de cacao maigre 2.2%, sirop d'??peautre.ingr??dients agricoles issus de l'agriculture biologique.",
                        "poudre de cacao maigre 2.2%, sirop d'??peautre.")
                .replace(
                        "sucre de canne, huile de palme non hydrog??n??e, sirop de riz, billettes de c??r??ales 4.1 %, farine de bl??",
                        "sucre de canne, huile de palme non hydrog??n??e, sirop de riz, billettes de c??r??ales 4.1 %, farine de bl??.")
                .replace(
                        "diglyc??rides d'acides gras, vitamines: thiamine, riboflavine, b3. acide pantoth??nique, b6. b8. acide folique, b12. fer.",
                        "diglyc??rides d'acides gras, fer.")
                .replace(
                        ", vitamines: niacine, vitamine e, acide pantoth??nique, vitamine b6. riboflavine, thiamine, acide folique, vitamine b12 diphosphate ferrique",
                        "")
                .replace("vitamines: niacine, b6. riboflavine, thiamine, acide folique, b12.", "")
                .replace(
                        "c??r??ales compl??tesbl?? complet, farine de bl?? complet, fruits secsraisins secs 10.9 %, bananes sucr??es aromatis??es 8.6 %, noix de coco 3.1 %, pommes 2.3 %, noisettes 2.3 %, sucre, extrait de malt d'orge, sel, vitamines: niacine, acide pantoth??nique, b6. riboflavine, thiamine, acide folique, biotine, b12. fer, ar??me.",
                        "c??r??ales compl??tes, bl?? complet, farine de bl?? complet, fruits secs, raisins secs 10.9 %, bananes sucr??es aromatis??es 8.6 %, noix de coco 3.1 %, pommes 2.3 %, noisettes 2.3 %, sucre, extrait de malt d'orge, sel, fer, ar??me.")
                .replace("caramel, sirop de sucre caram??lis?? , vitamines: bl, b2. b3. b5. b6. b9. b8. bl2",
                        "caramel, sirop de sucre caram??lis?? ")
                .replace(
                        "d??riv?? du lactos??rum riche en calcium, vitamines: thiamine, riboflavine, b3. b6. acide folique, b12. e,",
                        "d??riv?? du lactos??rum riche en calcium,")
                .replace(
                        ", vitamines: niacine, e, b6. riboflavine, thiamine, acide folique, b12 , fer. traces d'arachides, de soja, d'autres fruits ?? coque et de graines de s??same",
                        "")
                .replace(
                        "vitamines: niacine, acide pantoth??nique, riboflavine, b6. thiamine, acide folique, biotine, b12 , poudre ?? lever: carbonate acide de sodium , prot??ines de lait , fer. traces d'arachide, soja, fruits ?? coque et graines de s??same.",
                        " poudre ?? lever: carbonate acide de sodium , prot??ines de lait , fer.")
                .replace(
                        "c??r??ales 61.5 %1% farine complete de segle 1%,pepite de chocolat 3%,sucre, huile de colza,pate de cacao, huiles v??g??talesdextrose beurre de cacao ??mulsifiant, noisettes 2.5 %, miel 2%, min??rauxpoudre ?? lever,, sel correcteur d acidit??, emulsifiant, aromes.",
                        "c??r??ales 61.5%, farine complete de segle 1%,pepite de chocolat 3%,sucre, huile de colza,pate de cacao, huiles v??g??tales,dextrose ,beurre de cacao, ??mulsifiant, noisettes 2.5 %, miel 2%, min??raux ,poudre ?? lever, sel ,correcteur d acidit??, emulsifiant, aromes.")
                .replace(
                        "c??r??ales,   p??te ?? tartiner ?? la noisette du lot et garenne et au cacaosucre, noisettes du lot et garonne, poudre de lait, huile de coco, poudre de cacao, huile de colza, ??mulsi???ant: l??cithine de tournesol, ar??me: vanille, chocolat en poudre, son de bl??, sucre, sel, ??mulsifiant: mono, et diglyc??rldes d???acides gras, vitamines, b6. riboflavine, thiamine, acide folique, b12 et fer. , % dans le produit fini",
                        "c??r??ales, p??te ?? tartiner ?? la noisette du lot et garonne et au cacao, sucre, noisettes du lot et garonne, poudre de lait, huile de coco, poudre de cacao, huile de colza, ??mulsi???ant: l??cithine de tournesol, ar??me: vanille, chocolat en poudre, son de bl??, sucre, sel, ??mulsifiant: mono, et diglyc??rldes d???acides gras, fer.")
                .replace(
                        "flocons d???avoine, farine de bl????? graisse v??g??tale??? sucre ??? p??pites de cranberries, sucre, humectant: glyc??rine, correcteur d'acidit??: acide citrique, huile de tournesol, sirop de sucre inverti ??? billettes de riz souffl??es, sucre, gluten de bl??, avoine sirop de malt d???orge, sel ??? poudre ?? lever: carbonates de sodium , ??mulsifiant: e472e , sel ??? ar??me ??? vitamines niacine, riboflavine, b12. d3. b6 thiamine ??? fer.",
                        "flocons d???avoine, farine de bl??, graisse v??g??tale, sucre, p??pites de cranberries, sucre, humectant: glyc??rine, correcteur d'acidit??: acide citrique, huile de tournesol, sirop de sucre inverti, billettes de riz souffl??es, sucre, gluten de bl??, avoine sirop de malt d???orge, sel, poudre ?? lever: carbonates de sodium , ??mulsifiant: e472e , sel, ar??me, fer.")
                .replace(
                        "flocons d???avoine, farine de bl????? graisse v??g??tale??? sucre ??? sirop de sucre inverti??? billettes de riz souffl??es, sucre, gluten de bl??, avoine sirop de malt d???orge, sel ??? poudre ?? lever: carbonates de sodium , ??mulsifiant: e472e , sel ??? ar??me ??? vitamines niacine, riboflavine, b12. d3. b6 thiamine ??? fer.",
                        "flocons d???avoine, farine de bl??, graisse v??g??tale, sucre , sirop de sucre inverti, billettes de riz souffl??es, sucre, gluten de bl??, avoine ,sirop de malt d???orge, sel ,poudre ?? lever: carbonates de sodium , ??mulsifiant: e472e , sel ,ar??me , fer.")
                .replace("vitamines: niacine, e, b6. riboflavine, thiamine, folacine, b12 ,", "")
                .replace("c??r??alesflocons d'avoine, flocons de bl??,", "c??r??ales, flocons d'avoine, flocons de bl??,")
                .replace("c??r??ales 55% flocons d???avoine, flocons de bl??, farine de bl??, c??r??ales extrud??es,",
                        "c??r??ales 55% ,flocons d???avoine, flocons de bl??, farine de bl??, c??r??ales extrud??es,")
                .replace("sel, farine d'orge malt??e, vitamines: c, b3 ou pp, b6. b2. b1. b9. b21.",
                        "sel, farine d'orge malt??e, ")
                .replace("son de riz, sucre de canne, sel //  ingr??dients issus de l'agriculture biologique",
                        "son de riz, sucre de canne, sel .")
                .replace(
                        "correcteur d'acidit??: phosphate de sodium, ar??me, vitamines d, pp, b5. b6. b2. b1. b9 et min??raux",
                        "correcteur d'acidit??: phosphate de sodium, ar??me.")
                .replace(
                        "??mulsifiant, sel, ar??mes naturels. peut contenir du lait, des arachides et des fruits ?? coque.",
                        "??mulsifiant, sel, ar??mes naturels.")
                .replace("cacao maigre en poudre, vitamines et min??raux, sel, ar??me naturel",
                        "cacao maigre en poudre, vitamines et min??raux, sel, ar??me naturel.")
                .replace(
                        "rocou e160b, ar??me cannelle, antioxydant: extrait riche en tocoph??rols, vitamineset min??raux",
                        "rocou e160b, ar??me cannelle, antioxydant: extrait riche en tocoph??rols, vitamines et min??raux")
                .replace("r??gulateur d'acidit??: phosphate trisodique, ar??me, vitamineset min??raux.",
                        "r??gulateur d'acidit??: phosphate trisodique, ar??me, vitamines et min??raux.")
                .replace(", vitaminesacide pantoth??nique, b6. riboflavine, thiamine, acide folique, b12, fer", "")
                .replace("cacao maigre en poudre 50%, sucre, ar??me", "cacao maigre en poudre 50%, sucre, ar??me.")
                .replace("flocons de quinoa", "flocons de quinoa.")
                .replace(
                        "fourrage 50 %: chocolat au lait: sucre de canne, beurre de cacao, poudre de lait entier, masse de cacao, extrait de vanille, biscuit 50 %: farine de riz, farine de ma??s, sucre de canne //  ingr??dients issus de l???agriculture biologique",
                        "fourrage 50 %, chocolat au lait, sucre de canne, beurre de cacao, poudre de lait entier, masse de cacao, extrait de vanille, biscuit 50 %, farine de riz, farine de ma??s, sucre de canne.")
                .replace(
                        "fourrage 50 %: chocolat noir: sucre de canne, masse de cacao, beurre de cacao, extrait de vanille, biscuit 50%: farine de riz, farine de ma??s, sucre de canne. // ingr??dients issus de l'agriculture biologique",
                        "fourrage 50 %, chocolat noir, sucre de canne, masse de cacao, beurre de cacao, extrait de vanille, biscuit 50%, farine de riz, farine de ma??s, sucre de canne.")
                .replace(
                        "flocons de seigle 36% sucre de canne non raffin??, pralin de noisettes 12%, billettes de c??r??ales, huile de tournesol, noisettes 6%, sirop de riz, farine de bl??.",
                        "flocons de seigle 36%, sucre de canne non raffin??, pralin de noisettes 12%, billettes de c??r??ales, huile de tournesol, noisettes 6%, sirop de riz, farine de bl??.")
                .replace(
                        "flocons de c??r??ales, graines de tournesol 7%, graines de courges 6%, p??tales jus de pomme concentr?? de jus de pomme , sel, kasha, noisettes 3.5%, amandes s??same , graines de lin 2.2%.",
                        "flocons de c??r??ales, graines de tournesol 7%, graines de courges 6%, p??tales jus de pomme, concentr?? de jus de pomme , sel, kasha, noisettes 3.5%, amandes s??same , graines de lin 2.2%.")
                .replace(" , produit issu de l'agriculture biologique.", "")
                .replace("sarrasin 99%, sel..", "sarrasin 99%, sel.")
                .replace(
                        "c??r??ales 63.8 %, sucre de canne non raffin??. chocolats 58 %. p??pites de chocolats 21% p??te de cacao,",
                        "c??r??ales 63.8 %, sucre de canne non raffin??, chocolats 58 %. p??pites de chocolats 21% p??te de cacao,")
                .replace(
                        "flocons d'avoine 57.5%, sucre de canne non raffin??, huile de tournesol, billettes de c??r??ales, sirop de riz, farine de bl??, sirop de malt d'orge",
                        "flocons d'avoine 57.5%, sucre de canne non raffin??, huile de tournesol, billettes de c??r??ales, sirop de riz, farine de bl??, sirop de malt d'orge.")
                .replace(
                        "flocons d'avoine, chocolat en poudre 10%, farine de bl??, sucre de canne non raffin??, billettes de c??r??ales, sirop de riz, huile de tournesol, noix de coco 2%  ingr??dients issus de l'agriculture biologique",
                        "flocons d'avoine, chocolat en poudre 10%, farine de bl??, sucre de canne non raffin??, billettes de c??r??ales, sirop de riz, huile de tournesol, noix de coco 2% .")
                .replace("epeautre 98%, sel", "epeautre 98%, sel.")
                .replace(
                        "carbonates d'ammonium, ar??me, sel, ??mulsifiant: l??cithines de colza. traces ??ventuelles de lait, d'oeufs et de fruits ?? coque.",
                        "carbonates d'ammonium, ar??me, sel, ??mulsifiant: l??cithines de colza.")
                .replace(
                        "??mulsifiant: l??cithine de soja, antioxydant: extrait riche en tocoph??rols.  traces ??ventuelles de lait, arachides et fruits ?? coques",
                        "??mulsifiant: l??cithine de soja, antioxydant: extrait riche en tocoph??rols.")
                .replace(
                        "ananas sucr??s, pommes s??ch??es, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, ar??me, caramel",
                        "ananas sucr??s, pommes s??ch??es, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, ar??me, caramel.")
                .replace(
                        "carbonates de sodium , carbonates d'ammonium, ar??mes, ??mulsifiants: l??cithines de colza ,e472e , sel. traces ??ventuelles d'oeufs et de fruits ?? coque.",
                        "carbonates de sodium , carbonates d'ammonium, ar??mes, ??mulsifiants: l??cithines de colza ,e472e , sel. traces ??ventuelles d'oeufs et de fruits ?? coque.")
                .replace(
                        ", vitamines: niacine, acide pantoth??nique, b6 , riboflavine, thiamine, acide folique, biotine, b12. fer. traces ??ventuelles de soja, lait et fruits ?? coque",
                        "")
                .replace(
                        ", vitamines: niacine, acide pantoth??nique??? b6 , riboflavine, thiamine, acide folique, biotine, b12. fer",
                        "")
                .replace(
                        "c??r??ales 67% {flocons d'avoine, p??tales de bl?? complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, flocons de ble, farine de ble, c??r??ales extrud??es farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caram??lis?? en poudre, p??tales de ma??s ma??s, sucre, extrait de malt d'orge, sel, ??mulsifiant: mono, et diglyc??rides d'acides gras}, fruits secs 21% raisins secs, bananes sucr??es aromatis??es, lamelles de noix de coco, papayes sucr??es, pommes, ananas sucr??s, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, ar??me, caramel. traces ??ventuelles de soja, fruits ?? coque et lait.",
                        "c??r??ales 67% ,flocons d'avoine, p??tales de bl?? complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, flocons de ble, farine de ble, c??r??ales extrud??es farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caram??lis?? en poudre, p??tales de ma??s ma??s, sucre, extrait de malt d'orge, sel, ??mulsifiant: mono, et diglyc??rides d'acides gras, fruits secs 21% raisins secs, bananes sucr??es aromatis??es, lamelles de noix de coco, papayes sucr??es, pommes, ananas sucr??s, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, ar??me, caramel.")
                .replace(" fabriqu?? ?? partir de 99% de ma??s pour obtenir 100g de produit fini.", "")
                .replace(
                        "stabilisant: sorbitols, dextrose, sucre, sel, ar??me, ??mulsifiant: l??cithine de soja, antioxydant: extrait riche en tocoph??rols.  traces ??ventuelles de lait, sulfites et arachides.",
                        "stabilisant: sorbitols, dextrose, sucre, sel, ar??me, ??mulsifiant: l??cithine de soja, antioxydant: extrait riche en tocoph??rols.")
                .replace(
                        "poudres ?? lever: diphosphates , carbonates d'ammonium , carbonates de sodium, ar??me, ??mulsifiants: l??cithines de colza , e172e, sel. traces ??ventuelles d'oeufs et de fruits ?? coque.",
                        "poudres ?? lever: diphosphates , carbonates d'ammonium , carbonates de sodium, ar??me, ??mulsifiants: l??cithines de colza , e172e, sel.")
                .replace(
                        "c??r??ales 74% riz, ble complet24%, orge, copeaux de chocolat au lait 17%, sucre, farine compl??te de ble1.3%, sel, farine d'orge malt??e, ??mulsifiant: mono, et diglyc??rides d'acides gras, ar??me. traces ??ventuelles de fruits ?? coque",
                        "c??r??ales 74% riz, ble complet24%, orge, copeaux de chocolat au lait 17%, sucre, farine compl??te de ble 1.3%, sel, farine d'orge malt??e, ??mulsifiant: mono, et diglyc??rides d'acides gras, ar??me.")
                .replace(
                        "c??r??ales 50.5%, sucre, graines 12%, huile de tournesol, abricot sec 4%, mangue d??shydrat??e 4%, morceaux de caramel 4%, miel, noix de coco, ar??me naturel, antioxydant: extrait riche en tocoph??rols. traces ??ventuelles d'arachides et fruits ?? coque.",
                        "c??r??ales 50.5%, sucre, graines 12%, huile de tournesol, abricot sec 4%, mangue d??shydrat??e 4%, morceaux de caramel 4%, miel, noix de coco, ar??me naturel, antioxydant: extrait riche en tocoph??rols.")
                .replace(
                        "c??r??ales 68% {flocons d'avoine, p??tales de bl?? complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, p??tales de ma??s ma??s, sucre, extrait de malt d'orge, sel, ??mulsifiant: mono, et diglyc??rides d'acides gras, c??r??ales extrud??es farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caram??lis?? en poudre}, sucre, huile de tournesol, carr??s de chocolat noir 4%, carr??s de chocolat au lait 4%, carr??s de chocolat blanc 4%, cacao maigre en poudre, noix de coco en poudre, maltodextrine, sel, ar??me. traces ??ventuelles de soja et fruits ?? coque.",
                        "c??r??ales 68% ,flocons d'avoine, p??tales de bl?? complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, p??tales de ma??s ma??s, sucre, extrait de malt d'orge, sel, ??mulsifiant: mono, et diglyc??rides d'acides gras, c??r??ales extrud??es farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caram??lis?? en poudre, sucre, huile de tournesol, carr??s de chocolat noir 4%, carr??s de chocolat au lait 4%, carr??s de chocolat blanc 4%, cacao maigre en poudre, noix de coco en poudre, maltodextrine, sel, ar??me.")
                .replace(
                        "c??r??ales 66% flocons d'avoine complet 47%, flocons de ble complet, farine de ble, c??r??ales extrud??es, p??tales de ma??s, chocolat au lait 15%, sucre, huile de tournesol, sirop de glucose, noix de coco en poudre, sel, caramel, ar??me. traces ??ventuelles de soja, fruits ?? coque.",
                        "c??r??ales 66% ,flocons d'avoine complet 47%, flocons de ble complet, farine de ble, c??r??ales extrud??es, p??tales de ma??s, chocolat au lait 15%, sucre, huile de tournesol, sirop de glucose, noix de coco en poudre, sel, caramel, ar??me.")
                .replace(
                        "c??r??ales 57% flocons d'avoine complet, flocons de bl?? complet, farine de bl??, c??r??ales extrud??es, fruits secs 24% raisins, bananes, abricots, noix de coco en lamelles, noix de coco en poudre, amandes en tranches, noisettes, sucre, huile de tournesol, miel, graines de courges 1%, sel. traces ??ventuelles d'arachide, soja, lait et autres fruits ?? coque.  ingr??dient issu de l'agriculture biologique",
                        "c??r??ales 57%, flocons d'avoine complet, flocons de bl?? complet, farine de bl??, c??r??ales extrud??es, fruits secs 24% raisins, bananes, abricots, noix de coco en lamelles, noix de coco en poudre, amandes en tranches, noisettes, sucre, huile de tournesol, miel, graines de courges 1%, sel.")
                .replace(
                        "c??r??ales semoule de ma??s43%, farine de ble39%, sucre, miel 5%, sel, antiagglom??rant: carbonate de calcium, ar??me naturel, colorants: b??ta carot??ne et rocou, caramel, sirop de sucre caram??lis??, vitamines: niacine, acide pantoth??nique, riboflavine, thiamine, b6 , b12 , acide folique, biotine, fer. traces ??ventuelles d'arachide, soja, lait, fruits ?? coque et graines de s??same.",
                        "c??r??ales semoule de ma??s 43%, farine de ble 39%, sucre, miel 5%, sel, antiagglom??rant: carbonate de calcium, ar??me naturel, colorants: b??ta carot??ne et rocou, caramel, sirop de sucre caram??lis??.")
                .replace(
                        "sucre de canne roux, cacao maigre en poudre 21%, cr??mes de c??r??ales 10%, extrait de vanille bourbon, sel. , ingr??dients issus de l'agriculture biologique",
                        "sucre de canne roux, cacao maigre en poudre 21%, cr??mes de c??r??ales 10%, extrait de vanille bourbon, sel.")
                .replace(
                        "flocons de bl?? 9.8 %, riz 6.5 %, huile de tournesol, noix de coco r??p??e, farine de bl?? 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %bl?? 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits ?? coque, graines de s??same et soja.  ",
                        "flocons de bl?? 9.8 %, riz 6.5 %, huile de tournesol, noix de coco r??p??e, farine de bl?? 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %, bl?? 0.8 %, avoine 0.5 %, sel.")
                .replace(
                        "ingr??dients flocons d'avoine 38 %, sucre de canne, chocolat noir 11 %, flocons de bl?? 9.8 %, riz 6.5 %, huile de tournesol, noix de coco r??p??e, farine de bl?? 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %bl?? 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits ?? coque, graines de s??same et soja.",
                        "flocons d'avoine 38 %, sucre de canne, chocolat noir 11 %, flocons de bl?? 9.8 %, riz 6.5 %, huile de tournesol, noix de coco r??p??e, farine de bl?? 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %bl?? 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits ?? coque, graines de s??same et soja.")
                .replace(
                        "ingr??dients brioche au beurre farine de bl??: a??ufs entiers, eau, beurre 8.5%, sucre pure canne, sirop de sucre inverti, levur??, ar??mes naturels, sel, poudre de lait ??cr??m??, gluten de bl??, ??paississant,farine de bl?? malt??&quot",
                        "brioche au beurre farine de bl??: oeufs entiers, eau, beurre 8.5%, sucre pure canne, sirop de sucre inverti, levure, ar??mes naturels, sel, poudre de lait ??cr??m??, gluten de bl??, ??paississant,farine de bl?? malt??quot")
                .replace(
                        "ingr??dients riz 44 % , bl?? complet 24 %,copeaux de chocolat noir 19 %, sucre , orge 6 % , farine compl??te de bl?? , sel , farine d'orge malt??e , vitamines: c, niacine, b6. riboflavine, thiamine, acide folique, b12 , fer , ??mulsifiant: mono , et diglyc??rides d'acides gras.",
                        "riz 44 % , bl?? complet 24 %,copeaux de chocolat noir 19 %, sucre , orge 6 % , farine compl??te de bl?? , sel , farine d'orge malt??e , fer , ??mulsifiant: mono , et diglyc??rides d'acides gras.")
                .replace(
                        "cereales 71% farine de ble complete flocons de cereales avoine ble orge seigle riz sucre de canne, beurre 16%, ??ufs entiers, poudre de lait ecreme poudres a lever carbonates d'ammonium et de sodium, acide citrique sel marin ar??me naturel.",
                        "cereales 71%,farine de ble complete,flocons de cereales,avoine,ble,orge,seigle,riz, sucre de canne, beurre 16%, ??ufs entiers, poudre de lait ecreme poudres a lever, carbonates d'ammonium et de sodium, acide citrique, sel marin, ar??me naturel.")
                .replace(
                        "farine de bl??, p??te ?? glacer lait 30%, sucre, beurre concentr?? reconstitu?? 16%, ??ufs entiers frais, amidon de bl??, poudre ?? lever, fleur de sel 0.35%, lait ??cr??m?? en poudre",
                        "farine de bl??, p??te ?? glacer lait 30%, sucre, beurre concentr?? reconstitu?? 16%, ??ufs entiers frais, amidon de bl??, poudre ?? lever, fleur de sel 0.35%, lait ??cr??m?? en poudre.")
                .replace(
                        "farine de bl??, beurre p??tissier 11.9 %, sucre, ??ufs, poudre de lait, sel de gu??rande, poudre ?? lever (bicarbonate de sodium et bicarbonate d'ammonium, ar??mes.",
                        "farine de bl??, beurre p??tissier 11.9 %, sucre, ??ufs, poudre de lait, sel de gu??rande, poudre ?? lever, bicarbonate de sodium, bicarbonate d'ammonium, ar??mes.")
                .replace(
                        " poudres ?? lever: carbonates acides d???ammonium et de sodium , acide citrique, sel marin non raffin??, ar??me  naturel de vanille, lait demi~??cr??m??",
                        " poudres ?? lever: carbonates acides d???ammonium et de sodium , acide citrique, sel marin non raffin??, ar??me  naturel de vanille, lait demi-??cr??m??.")
                .replace(
                        "poudres ?? lever: carbonatesd'ammonium, carbonates de sodium, diphosphates , sel , lait ??cr??m?? en poudre , blanc d'??uf en poudre , cacao en poudre , jaune d'oeuf en poudre , ar??mes. traces de graines de s??same.",
                        "poudres ?? lever: carbonates d'ammonium, carbonates de sodium, diphosphates , sel , lait ??cr??m?? en poudre , blanc d'??uf en poudre , cacao en poudre , jaune d'oeuf en poudre , ar??mes.")
                .replace(
                        "ingredients: farine et amidon de bl?? 61.8%, sucre, p??pites de, beurre concentr?? 11.5%, poudre de lait ??cr??m??, poudres ?? lever: carbonates d'ammonium , carbonates de sodium , diphosphates, poudre de cacao, sel, extrait de malt d'orge, ??ufs, ar??medont lait. traces ??ventuelles d???autres c??r??ales contenant du gluten, arachides, soja et fruits ?? coque.",
                        "ingredients: farine et amidon de bl?? 61.8%, sucre, p??pites de, beurre concentr?? 11.5%, poudre de lait ??cr??m??, poudres ?? lever: carbonates d'ammonium , carbonates de sodium , diphosphates, poudre de cacao, sel, extrait de malt d'orge, ??ufs, ar??me dont lait.")
                .replace(
                        "carbonates de sodium, diphosphates, lait demi,??cr??m??, ??ufs entiers en poudre, lait ??cr??m?? en poudre, cacao en poudre, ar??mes.",
                        "carbonates de sodium, diphosphates, lait demi-??cr??m??, ??ufs entiers en poudre, lait ??cr??m?? en poudre, cacao en poudre, ar??mes.")
                .replace(
                        "farine de bl??, sucre de canne beurre 16%, lactos??rum en poudre sel, poudres ?? lever, ??ufs entiers, jus de citron",
                        "farine de bl??, sucre de canne beurre 16%, lactos??rum en poudre sel, poudres ?? lever, ??ufs entiers, jus de citron.")
                .replace("petits pois 56 %, pomme de terre, lait demi,??cr??m??, cr??me fra??che, sel poivre",
                        "petits pois 56 %, pomme de terre, lait demi,??cr??m??, cr??me fra??che, sel poivre.")
                .replace(
                        "olives d??noyaut??es vertes, tournantes et noires 75%, lupins 17%, poivrons, sel, huile de tournesol, piments, ??pices et aromates. saumure, acidifiant, anti,oxyg??ne, sulfites",
                        "olives d??noyaut??es vertes, tournantes et noires 75%, lupins 17%, poivrons, sel, huile de tournesol, piments, ??pices et aromates. saumure, acidifiant, anti,oxyg??ne, sulfites.")
                .replace("olives noires , 92%, sel, huile de tournesol",
                        "olives noires , 92%, sel, huile de tournesol.")
                .replace("olives, eau et sel", "olives, eau et sel.")
                .replace(
                        "eau, rameaux de salicorne, vinaigre, carottes, ar??mes naturels, oignons blancs, coriandre",
                        "eau, rameaux de salicorne, vinaigre, carottes, ar??mes naturels, oignons blancs, coriandre.")
                .replace("c??pres, eau, vinaigre, sel", "c??pres, eau, vinaigre, sel.")
                .replace("olives, huile de colza, sel", "olives, huile de colza, sel.")
                .replace(
                        "p??tes alimentaires pr??cuites 42% eau, nouilles 16% {semoule de ble dur. poudre de blanc doeuf, huile de colza, sen, viande de b??uf trait??e en salaison, blanchie et pr??cuite 22% poitrine de b??uf 150/j, eau. f??cule de pomme de terre. prot??ines de lait, ar??mes natureis, maltodextrjne de bl?? et/ou ma??s, antioxydant ascorbate de sodium, sen. vin rouge 11%, eau, carottes en rondelle oignons grelots 3.3%, oignon ??minc?? 2.3%, champignons de paris ??minc??s 1.7%, ar??me, acidifiant: acide cmque, lardons cuits fum??s 1.3% poitine de porc, sel, dextose, ar??me de fum??e, acidifiant: acide citrique, antioxydant: ??rythorbate de sodium, conservateur: nitrite de sodium, amidon modifi?? de ma??s, g??, extrait de malt, ar??mes, poudre de champignon. traces ??ventuelles de soja, c??leri, poissons, crustac??s, mollusques. graines de s??same et fruis ?? coque. exprim??s sur la totalit?? de la recette.",
                        "p??tes alimentaires pr??cuites 42%, eau, nouilles 16% ,semoule de ble dur, poudre de blanc doeuf, huile de colza, sen, viande de b??uf trait??e en salaison blanchie et pr??cuite 22% ,poitrine de b??uf 150/j, eau, f??cule de pomme de terre, prot??ines de lait, ar??mes natureis, maltodextrine de bl?? et/ou ma??s, antioxydant ascorbate de sodium, sel, vin rouge 11%, eau, carottes en rondelle oignons grelots 3.3%, oignon ??minc?? 2.3%, champignons de paris ??minc??s 1.7%, ar??me, acidifiant: acide cmque, lardons cuits fum??s 1.3% poitine de porc, sel, dextose, ar??me de fum??e, acidifiant: acide citrique, antioxydant: ??rythorbate de sodium, conservateur: nitrite de sodium, amidon modifi?? de ma??s, g??, extrait de malt, ar??mes, poudre de champignon.")
                .replace(
                        "nems porc 4x70 g:eau, farine de riz, oignon r??hydrat??, viande de porcet gras de porc 8%, prot??ines de soja, carotte, huile de tournesol, pousse de haricot mungo, amidon de pois, sel, champignon noir, chou, amidon de mais, sauce soja, ail, sucre, poivre. pourcentage exprim?? sur le nem. sauce au nuoc,m??m 30 g eau, sucre, vinaigre d'alcool, sel, nuoc,m??m 1.6%, sauce soja. pourcentage exprim?? sur la sauce. traces ??ventuelles de crustac??s, ceuf, lait et arachide.",
                        "nems porc 4x70 g,eau, farine de riz, oignon r??hydrat??, viande de porcet gras de porc 8%, prot??ines de soja, carotte, huile de tournesol, pousse de haricot mungo, amidon de pois, champignon noir, chou, amidon de mais, sauce soja, ail, sucre, poivre, sauce au nuoc,m??m 30 g ,eau, sucre, vinaigre d'alcool, sel, nuoc,m??m 1.6%, sauce soja.")
                .replace("jus cuisin??: eau, oignons, sel, ar??mes. lentilles pr??cuites.",
                        "eau, oignons, sel, ar??mes, lentilles pr??cuites.")
                .replace("vinaigrette ?? l'huile de noix 10.9%4.1%", "vinaigrette ?? l'huile de noix 10.9%")
                .replace(
                        "noix de coco 2.1%, ar??me naturel, antioxydant: extrait riche en tocoph??rols. traces ??ventuelles de lait, arachides et fruits ?? coque. produit fabriqu?? ?? partir d'ingr??dients origine france et hors france.",
                        "noix de coco 2.1%, ar??me naturel, antioxydant: extrait riche en tocoph??rols.")
                .replace(
                        "??chalotes, sel, huile d'olive, basilic, dextrose, persil, ail, muscade, g??lifiants: carragh??nanes et farine de graine de caroube, ar??mes naturels",
                        "??chalotes, sel, huile d'olive, basilic, dextrose, persil, ail, muscade, g??lifiants: carragh??nanes et farine de graine de caroube, ar??mes naturels.")
                .replace(
                        "macaronis cuits 45%, lait demi,??cr??m?? 22%, jambon de porc sup??rieur cuit 16%, ferments, emmental r??p?? 11%, beurre 1.5%, huile de tournesol, amidon de ma??s cireux, farine de bl?? 0.4%, g??latine de b??uf, sel, poivre, muscade",
                        "macaronis cuits 45%, lait demi,??cr??m?? 22%, jambon de porc sup??rieur cuit 16%, ferments, emmental r??p?? 11%, beurre 1.5%, huile de tournesol, amidon de ma??s cireux, farine de bl?? 0.4%, g??latine de b??uf, sel, poivre, muscade.")
                .replace(
                        "emmental 36%, eau, vin blanc, tomme 7%, cr??me, comt?? aop 3%, amidon modifi?? de mais, sel, correcteur d'acidit??: phosphates de sodium, ail, boisson spiritueuse au kirsch, colorant: rocou",
                        "emmental 36%, eau, vin blanc, tomme 7%, cr??me, comt?? aop 3%, amidon modifi?? de mais, sel, correcteur d'acidit??: phosphates de sodium, ail, boisson spiritueuse au kirsch, colorant: rocou.")
                .replace(
                        "p??tes alimentaires aux oeufs frais tagliatelles cuites 48%, pr??paration ?? base de surimi 20%, eau, amidons de pomme de terre et de bl??, blanc d'oeuf r??hydrat??, huile de colza, poivrons rouges 8.5%, eau, cr??me fra??che, vinaigre d'alcool, jus de citron concentr??, sel, jaune d'oeuf, moutarde de dijon, aneth 0.2%, amidon modifi?? de pomme de terre, poivre blanc, ??paississant: gomme xanthane",
                        "p??tes alimentaires aux oeufs frais tagliatelles cuites 48%, pr??paration ?? base de surimi 20%, eau, amidons de pomme de terre et de bl??, blanc d'oeuf r??hydrat??, huile de colza, poivrons rouges 8.5%, eau, cr??me fra??che, vinaigre d'alcool, jus de citron concentr??, sel, jaune d'oeuf, moutarde de dijon, aneth 0.2%, amidon modifi?? de pomme de terre, poivre blanc, ??paississant: gomme xanthane.")
                .replace(
                        "levure, sucre, farine de f??ves, muscade, vinaigre d'alcool, farine d'orge malt??e torr??fi??e, gluten de bl??. pourcentages exprim??s sur le nem au poulet et au curry.  nems aux legumes: galette de riz 31% garnie d'une farce aux l??gumes: eau, farine de riz 12%, oignon 10.5%, champignon noir 10.5%, prot??ines de soja, petit pois 6.5%, ch??taigne d'eau 5%, huile de tournesol, blanc d'??uf, ciboule, sel, f??cule de manioc, coriandre, caramel aromatique, piment. pourcentages exprim??s sur le nem aux l??gumes. sauce nuoc,m??m: eau, sucre, vinaigre d'alcool, sel, nuoc,m??m 1.6%,, sauce soja. pourcentage exprim?? sur la sauce nuoc,m??m.  peut contenir des traces de crustac??s et arachide.",
                        "levure, sucre, farine de f??ves, muscade, vinaigre d'alcool, farine d'orge malt??e torr??fi??e, gluten de bl??. pourcentages exprim??s sur le nem au poulet et au curry.  nems aux legumes: galette de riz 31% garnie d'une farce aux l??gumes: eau, farine de riz 12%, oignon 10.5%, champignon noir 10.5%, prot??ines de soja, petit pois 6.5%, ch??taigne d'eau 5%, huile de tournesol, blanc d'??uf, ciboule, sel, f??cule de manioc, coriandre, caramel aromatique, piment. pourcentages exprim??s sur le nem aux l??gumes. sauce nuoc,m??m: eau, sucre, vinaigre d'alcool, sel, nuoc,m??m 1.6%, sauce soja. ")
                .replace(
                        "farine de bl??, beurre, levure, sel, lait ??cr??m?? en poudre, extrait de malt d'orge, poudre d'ac??rola. pourcentages exprim??s sur l'ensemble du produit.",
                        "farine de bl??, beurre, levure, sel, lait ??cr??m?? en poudre, extrait de malt d'orge, poudre d'ac??rola.")
                .replace("thon listao 25%, lentilles vertes pr??,tremp??es 19%, poivrons rouges 11.4%",
                        "thon listao 25%, lentilles vertes pr??-tremp??es 19%, poivrons rouges 11.4%")
                .replace("peut contenir des traces de moutarde, gluten et oeuf", "")
                .replace("piment de cayenne, poivre, sel, pavot bleu, ??paississants: gomme xanthane et gomme guar",
                        "piment de cayenne, poivre, sel, pavot bleu, ??paississants: gomme xanthane et gomme guar.")
                .replace("ar??me naturel, sel, ciboulette, fibres d'agrumes, sucre",
                        "ar??me naturel, sel, ciboulette, fibres d'agrumes, sucre.")
                .replace(
                        "peut contenir des traces de crustac??s, mollusques, moutarde, ??ufs, poissons et fruits ?? coque",
                        "")
                .replace("gluten de bl??. pourcentages exprim??s sur le nem au porc.  nems au poulet et au curry",
                        "gluten de bl??, nems au poulet et au curry")
                .replace(
                        "piment. pourcentages exprim??s sur le nem aux l??gumes. sauce nuoc,m??m: eau, sucre, vinaigre d'alcool",
                        "piment, sauce nuoc,m??m: eau, sucre, vinaigre d'alcool")
                .replace(
                        "nuoc,m??m 1.6%,, sauce soja. pourcentage exprim?? sur la sauce nuoc,m??m.  peut contenir des traces de crustac??s et arachide.",
                        "nuoc,m??m 1.6%, sauce soja.")
                .replace(
                        "origan d??shydrat??, pur??e d'ail, huile d'olive vierge. peut contenir des traces de crustac??s, mollusques,  moutarde, ??ufs, poissons et fruits ?? coque.  e.",
                        "origan d??shydrat??, pur??e d'ail, huile d'olive vierge.")
                .replace(
                        " peut contenir des traces de crustac??s, mollusques, moutarde, ??ufs, poissons et fruits ?? coque.",
                        "")
                .replace(
                        " peut contenir des traces de crustac??s, mollusques, moutarde, ??ufs, poissons et fruits ?? coque. mise en ??uvre d'ingr??dients d??congel??s, ne pas congeler.",
                        "")
                .replace(" pourcentages exprim??s sur l'ensemble du produit.", "")
                .replace(" . mise en ??uvre d'ingr??dients d??congel??s, ne pas congeler.", "")
                .replace(
                        "sucre, vinaigre d'alcool, sel. pourcentage exprim?? sur la sauce. peut contenir des traces de bl?? et lait.",
                        "sucre, vinaigre d'alcool, sel.")
                .replace("biologique. peut contenir des traces de soja, c??leri et fruits ?? coque.", "")
                .replace("conservateurs: ??rythorbate de sodium et nitrite de sodium. emmental 10.7%.",
                        "conservateurs: ??rythorbate de sodium et nitrite de sodium, emmental 10.7%.")

                /* =============Moa reste================================================== */
                .replace(" prot??ines de lait'", "prot??ines de lait")
                .replace(" extrat de vanille. 'ingredtent ongtne unjon eurq??enre.", " extrait de vanille")
                .replace("lait ??cr??m?? ??? fruits: fraise ou framboise ou p??che ou abricot 10.3%",
                        "lait ??cr??m?? , fruits: fraise ou framboise ou p??che ou abricot 10.3%")
                .replace(" eau ??? cr??me ??? lait ??cr??m?? en poudre ", " eau , cr??me , lait ??cr??m?? en poudre ")
                .replace("citrate de calcium ??? amidon transform?? ??? ??paississants: carragh??nanes",
                        "citrate de calcium , amidon transform?? , ??paississants: carragh??nanes")
                .replace("gomme de guar ??? colorants: anthocyanes", "gomme de guar , colorants: anthocyanes")
                .replace("cochenille ??? ar??mes ??? ??dulcorants: ac??sulfame k",
                        "cochenille , ar??mes , ??dulcorants: ac??sulfame k")
                .replace("sucralose ???  ferments lactiques du yaourt ", "sucralose , ferments lactiques du yaourt ")
                .replace("jus de potiron ??? amidon transform?? de mais ",
                        "jus de potiron , amidon transform?? de ma??s ")
                .replace("sucre 8.9% ?? jus de carotte ?? sirop de glucose",
                        "sucre 8.9% , jus de carotte , sirop de glucose")
                .replace("une garniturecompos??e de: viande bovine fran??aise de race charolaise 53%",
                        "une garniture compos??e de: viande bovine fran??aise de race charolaise 53%")
                .replace("??mulsifiant: e471. colorant: b??tacarot??ne.", "??mulsifiant: e471, colorant: b??tacarot??ne.")
                .replace("amidon transform?? de pomme d terre. salade 9%.",
                        "amidon transform?? de pomme d terre, salade 9%.")
                .replace("farine de bl?? malt??, antioxydant: e316. ??uf.",
                        "farine de bl?? malt??, antioxydant: e316, ??uf")
                .replace("agent de traitement de la farine: e300. ??uf.",
                        "agent de traitement de la farine: e300, ??uf")
                .replace(
                        "pain de mie au bl?? malt?? 40%??? poulet r??ti 19%??? sauce caesar 13%   mayonnaise all??g??e en mati??res grasses,",
                        "pain de mie au bl?? malt?? 40%, poulet r??ti 19%, sauce caesar 13%   mayonnaise all??g??e en mati??res grasses,")
                .replace(", ognon, boyau naturel de mouton", ", oignon, boyau naturel de mouton")
                .replace("boyau naturel de mouton.", "boyau naturel de mouton")
                .replace("colorant e160c", "colorant: e160c")
                .replace("sucre. boyau naturel de mouton", "sucre, boyau naturel de mouton")
                .replace("ar??mes. boyau naturel de mouton", "ar??mes, boyau naturel de mouton")
                .replace(
                        "viande de porc origine union europ??enne (81 eau, sel, sirop de glucose, dextrose, ar??me naturel, ??pices et plantes aromatiques, acidifiant: e331. conservateur: e250.",
                        "viande de porc origine union europ??enne , eau, sel, sirop de glucose, dextrose, ar??me naturel, ??pices et plantes aromatiques, acidifiant: e331, conservateur: e250.")
                .replace(
                        "fum??e liquide ??mulsifiants: e450,e451. exhausteur de go??t: e621. antioxydants: e300 , e301 , e316. colorant: e120. conservateurs: e250,e326,e262. ferments.",
                        "fum??e liquide ??mulsifiants: e450 e451, exhausteur de go??t: e621, antioxydants: e300 e301 e316 colorant: e120, conservateurs: e250 e326 e262, ferments.")
                .replace("antioxydant: ascorbate de sodium (e300. enveloppe naturelle de mouton.",
                        "antioxydant: ascorbate de sodium e300, enveloppe naturelle de mouton.")
                .replace(
                        "sirop de glucose, stabilisants: e451. e450 ar??mes, antioxydants: e315. e316 conservateur: e250. boyau naturel de mouton.",
                        "sirop de glucose, stabilisants: e451 e450, ar??mes, antioxydants: e315 e316, conservateur: e250, boyau naturel de mouton.")
                .replace("conservateur: nitrite de sodium. fumage au bois de h??tre.",
                        "conservateur: nitrite de sodium, fumage au bois de h??tre.")
                .replace("colorant e120. boyau nature de mouton.", "colorant e120, boyau nature de mouton.")
                .replace("stabilisants: di,, tri, et polyphosphates, ",
                        "stabilisants: diphosphate triphosphate et polyphosphates, ")
                .replace("conservateur: e262. antioxydants: e316,e331.",
                        "conservateur: e262. , antioxydants: e316 e331.")
                .replace("conservateur: nitrite de sodium. boyau naturel de porc.",
                        "conservateur: nitrite de sodium, boyau naturel de porc.")
                .replace("ferments. boyau naturel de porc.", "ferments, boyau naturel de porc.")
                .replace("exhausteur de go??t: monoglutamate de sodium. boyau naturel de porc.",
                        "exhausteur de go??t: monoglutamate de sodium, boyau naturel de porc.")

                /* 10377 */
                .replace(
                        "exhausteur de go??t: e621. ar??mes naturel, acidifiant: e575 , colorant: e120 , antioxydant: e316 , conservateur: e250. enveloppe.",
                        "exhausteur de go??t: e621. ar??mes naturel, acidifiant: e575 , colorant: e120 , antioxydant: e316 , conservateur: e250. , enveloppe.")
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
                        "exhausteur de gout: e621. ar??mes naturels, dextrose, acidifiant: e575. colorant: e120. antioxydant: e316. conservateur: e250. traces ??ventuelles d'oeuf, lait, soja et fruits ?? coque. boyau non comestible, retirez avant consommation",
                        "exhausteur de gout: e621., ar??mes naturels, dextrose, acidifiant: e575., colorant: e120., antioxydant: e316., conservateur: e250., traces ??ventuelles d'oeuf, lait, soja et fruits ?? coque., boyau non comestible")
                .replace(
                        "antioxydant: acide ascorbique, conservateur: nitrite de sodium, eau, sel. traces ??ventuelles de lait et de c??r??ales contenant du gluten.",
                        "antioxydant: acide ascorbique, conservateur: nitrite de sodium eau sel., traces ??ventuelles de lait et de c??r??ales contenant du gluten.")
                .replace(
                        "conservateur: nitrite de sodium. boyau naturel de mouton. ingr??dient issu de l'agriculture biologique traces ??ventuelles d oeufs, de soja et de fruits ?? coque",
                        "conservateur: nitrite de sodium., boyau naturel de mouton., ingr??dient issu de l'agriculture biologique , traces ??ventuelles d'oeufs de soja et de fruits ?? coque")
                .replace("girofle, ferments. enveloppe: boyau collag??nique.",
                        "girofle, ferments., enveloppe: boyau collag??nique.")
                .replace("ferments. enveloppe: boyau naturel de porc.",
                        "ferments., enveloppe: boyau naturel de porc.")
                .replace("e252. ferments.", "e252., ferments.")
                .replace(
                        "plantes aromatiques, sirop de glucose, dextrose, conservateur: nitrate de potassium, ferments, boyau naturel de porc. 152g de viande mise en ??uvre pour 100 g de produit fini.",
                        "plantes aromatiques, sirop de glucose, dextrose, conservateur: nitrate de potassium, ferments, boyau naturel de porc., 152g de viande mise en ??uvre pour 100 g de produit fini.")
                .replace("ferments. boyau naturel de porc.", "ferments., boyau naturel de porc.")
                .replace("ferments. boyau en fibre animale.", "ferments., boyau en fibre animale.")
                .replace("ferment. boyau naturel de b??uf.", "ferments., boyau naturel de b??uf.")
                .replace(
                        "saumon atlantique??lev?? au royaume,uni, norv??ge ou irlande 97.2 %, sel 2.8 %. ingr??dient issu de l'agriculture biologique. pays d'??levage: voir ci,dessous.",
                        "saumon atlantique??lev?? au royaume-uni, norv??ge ou irlande 97.2 %, sel 2.8 %. ,ingr??dient issu de l'agriculture biologique. pays d'??levage")
                .replace("saumon atlantiquenourri sans ogm97%, sel 3%.",
                        "saumon atlantique nourri sans ogm 97%, sel 3%.")
                .replace(
                        "sirop de glucose de ma??s, igre, coriandre. mon ??lev?? en norv??ge, ecosseou irlande: voir impression 'essous.",
                        "sirop de glucose de ma??s, igre, coriandre., saumon ??lev?? en norv??ge ecosse ou irlande")
                .replace(
                        "saumon 80 %, eau, prot??ine de soja, g??lifiant: e407. blanc d'oeuf poudre, stabilisants: e452,e451. sucre, ar??me naturel de romarin, sel, colorant: e160c.",
                        "saumon 80 %, eau, prot??ine de soja, g??lifiant: e407., blanc d'oeuf poudre, stabilisants: e452 e451., sucre, ar??me naturel de romarin, sel, colorant: e160c.")
                .replace("boyau naturel de porc. poudre de fleurage: carbonate de calcium. talc.",
                        "boyau naturel de porc., poudre de fleurage: carbonate de calcium., talc.")
                .replace(
                        " graines de s??same, maltodextrine de ma??s, algues s??ch??es, piment en poudre, colorants: e140. e100. e160c, e162. e150a.",
                        " graines de s??same, maltodextrine de ma??s, algues s??ch??es, piment en poudre, colorants: e140. e100. e160c e162. e150a.")
                .replace("sel marin. dorure: ??ufs.", "sel marin., dorure: ??ufs.")
                .replace(" emmental  lait demi,??cr??m??", " emmental  lait demi-??cr??m??")
                .replace("100 % viande bovine fran??aise.", "100% viande bovine fran??aise.")
                .replace("eau, fromage frais 18.2 % lait ??cr??m??,", "eau, fromage frais 18.2 %, lait ??cr??m??,")
                .replace("mini,cake ?? l'olive noire: ??uf entier liquide pasteuris??,",
                        "mini cake ?? l'olive noire, ??uf entier liquide pasteuris??,")
                .replace("queue de crevette crue d??cortiqu??e 12 % crevetteconservateur: m??tabisulfite de sodium,",
                        "queue de crevette crue d??cortiqu??e 12 % , conservateur: m??tabisulfite de sodium,")
                .replace(" lait??cr??m?? en poudre,", " lait ??cr??m?? en poudre,")
                .replace(
                        "moule cuite d??coquill??e 45 %mytilus edulis sauvage p??ch??e ?? la drague en atlantique nord,est, sous,zone mer du nord, noix de saint,jacques crue27.5 % zygochlamys patagonica sauvage p??ch??e au chalut en atlantique sud,ouest, crevette nordique cuite d??cortiqu??e26.8 % pandalus borealis sauvage p??ch??e au chalut en atlantique nord,est, sous,zones mer de barents, mers de norv??ge, sel.",
                        "moule cuite d??coquill??e 45 % mytilus edulis sauvage p??ch??e ?? la drague en atlantique nord est sous zone mer du nord, noix de saint-jacques crue 27.5 % zygochlamys patagonica sauvage p??ch??e au chalut en atlantique sud-ouest, crevette nordique cuite d??cortiqu??e 26.8 % pandalus borealis sauvage p??ch??e au chalut en atlantique nord-est sous zones mers de norv??ge, sel.")
                .replace("noix de saint,jacques sans corail.", "noix de saint-jacques sans corail.")
                .replace("noix de saint,jacques 100%.", "noix de saint-jacques 100%.")
                .replace(
                        "cr??me fra??che liquide44.2 %, girolle 21.1 %, c??pe 21.1 %, morille 8 %, ail 2.9 %, persil 1.9 %, sel, poivre. 1.9+2.9+8+42.2",
                        "cr??me fra??che liquide 44.2 %, girolle 21.1 %, c??pe 21.1 %, morille 8 %, ail 2.9 %, persil 1.9 %, sel, poivre.")
                .replace("noix de muscade 0.01% issus d'animaux n??s, ??lev??s et abattus en ue",
                        "noix de muscade 0.01%")
                .replace(" fromage de ch??vreaffin?? 5 %, ", " fromage de ch??vre affin?? 5 %, ")
                .replace(" levain de seigled??vitalis??,", " levain de seigle d??vitalis??,")
                .replace("choux,fleurs en fleurettes 100 %.", "choux-fleurs en fleurettes 100 %.")
                .replace(
                        "59% choux,fleurs, eau, 8.5% cr??me, jaune d'??uf, 5% emmental, pr??paration ?? base de lactos??rum,",
                        "59% choux,fleurs, eau, 8.5% cr??me, jaune d'??uf, 5% emmental, pr??paration ?? base de lactos??rum,")
                .replace(
                        "mozzareila, olives noircies avec noyau, basilic et origan..  41% p??te: farine de bl??, eau, levure, sel.",
                        "mozzarella, olives noircies avec noyau, basilic et origan. , farine de bl??, eau, levure, sel.");
        return replaceEnderscoreIngredients;
    }
}
