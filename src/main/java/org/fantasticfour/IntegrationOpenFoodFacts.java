package org.fantasticfour;

import org.fantasticfour.bll.AppService;
import org.fantasticfour.bo.Category;
import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.bo.Mark;
import org.fantasticfour.bo.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class IntegrationOpenFoodFacts {


    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
    private static EntityManager em = emf.createEntityManager();
    private static AppService appService = AppService.getSingle();


    public static void main(String[] args) throws IOException {


        Path paths = Paths.get("/workspace/www/traitement-fichier-jpa-off/src/main/resources/open-food-facts.csv");

        FileWriter myWriter = new FileWriter("/workspace/www/traitement-fichier-jpa-off/src/main/resources/files/recensement-copy.csv");
        List<String> lines = Files.readAllLines(paths, StandardCharsets.UTF_8);

        HashSet<String> deleteSameIngredients = new HashSet<>();
        HashSet<String> deleteSameCategories = new HashSet<>();
        HashSet<String> deleteSameMarks = new HashSet<>();

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] part = line.replace("|’", "l'")
                    .replace("—|a", " a")
                    .replace("ty|ate de sodium", "tyate de sodium")
                    .replace(" conservateur |antioxydant: levure", " conservateur antioxydant: levure")
                    .replace("|% [maltodextrine de blé", ",maltodextrine de blé")
                    .replace("Filets de colin d’A|aska 72%qualité sans arête*", "Filets de colin d’Aaska 72% qualité sans arête")
                    .replace("Calin+ Fruits Pêche, Abricot, Fraise, Framboise)", "Calin+ Fruits Pêche, Abricot, Fraise, Framboise")
                    .split("\\|");

            String category = part[0];
            String mark = part[1];

            appService.initVariable(part);
            String name = appService.name;
            String nutriGrade = appService.nutriGrade;
            String ingredients = appService.ingredients;
            double energie = appService.energie;
            double fat = appService.fat;
            double sugar = appService.sugar;
            double fiber = appService.fiber;
            double proteins = appService.proteins;
            double salt = appService.salt;
            double vitA = appService.vitA;
            double vitD = appService.vitD;
            double vitE = appService.vitE;
            double vitK = appService.vitK;
            double vitC = appService.vitC;
            double vitB1 = appService.vitB1;
            double vitB2 = appService.vitB2;
            double vitPP = appService.vitPP;
            double vitB6 = appService.vitB6;
            double vitB9 = appService.vitB9;
            double vitB12 = appService.vitB12;
            double calcium = appService.calcium;
            double magnesium = appService.magnesium;
            double iron = appService.iron;
            double fer = appService.fer;
            double beta_carotene = appService.beta_carotene;
            boolean palm_oil = appService.palm_oil;

            if (deleteSameCategories.add(category)) {
                em.getTransaction().begin();
                Category categories = new Category(category);
                em.persist(categories);
                em.getTransaction().commit();
            }

            if (deleteSameMarks.add(mark)) {
                em.getTransaction().begin();
                Mark marks = new Mark(mark);
                em.persist(marks);
                em.getTransaction().commit();
            }
            System.out.println("nomProduit----------------------------------");
            System.out.println(i + " " + name);
            System.out.println("------------------------------------------------");


            String replaceEnderscoreIngredients = ingredients.toLowerCase(Locale.ROOT)
                    .replace("_", "")
                    .replace("]", "")
                    .replace("[", "")
                    .replace(")", "")
                    .replace(" .", "")
                    .replace("[%", "")
                    .replace("*", "")
                    .replace(".   ", "")
                    .replace("-", ",")
                    .replace(" : ", ": ")
                    .replace(";", ",")
                    .replace("marmelade de citrons confiture d’ananas et de fruits de la passion", "marmelade de citrons confiture d’ananas et de fruits de la passion,")
                    .replace("confiture de framboises et de litchis", "confiture de framboises et de litchis,")
                    .replace("confiture de pamplemousses et de fruits du dragon", "confiture de pamplemousses et de fruits du dragon,")
                    .replace("plein air, issus de l'agriculture", "plein air issus de l'agriculture")
                    .replace("confiture de mangues et de pêches", "confiture de mangues et de pêches,")
                    .replace("confiture de poires et de mirabelles", "confiture de poires et de mirabelles,")
                    .replace("confiture de fraises à la verveine", "confiture de fraises à la verveine,")
                    .replace("confiture de framboises et de groseilles", "confiture de framboises et de groseilles,")
                    .replace("confiture extra de cerises et de mûres", "confiture extra de cerises et de mûres,")
                    .replace("marmelade d’oranges à la cannelle", "marmelade d’oranges à la cannelle,")
                    .replace("confiture extra de 4 fruits", "confiture extra de 4 fruits,")
                    .replace("marmelade d’oranges douces et de mandarines", "marmelade d’oranges douces et de mandarines,")
                    .replace("confiture de fraises et de groseilles", "confiture de fraises et de groseilles,")
                    .replace("confiture extra de rhubarbe", "confiture extra de rhubarbe,")
                    .replace("confiture extra de fraises et de fraises des bois", "confiture extra de fraises et de fraises des bois,")
                    .replace("marmelade de citrons confiture d’ananas et de fruits de la passion", "marmelade de citrons confiture d’ananas et de fruits de la passion,")
                    .replace("confiture extra de reines", "confiture extra de reines,")
                    .replace("oeclatatton nutritionnelle pour / nutrition declaration for / nahrwertdeklaration pto / voedingswaardever,melding per 100 g energie / energy / energie", "")
                    .replace("anti,agglomérant", "antiagglomérant")
                    .replace("/ ingrediénten:", "")
                    .replace("[eau", ",eau")
                    .replace("sirop de glucose de blé ou mai's", "sirop de glucose de blé ou maïs")
                    .replace("dextrose de ma`i`s", "dextrose de maïs")
                    .replace("beurre. emmental râpé20%. levure", "beurre, emmental râpé20%, levure")
                    .replace("lait  écrémé en poudre. farine de malt d‘orge", "lait  écrémé en poudre, farine de malt d‘orge")
                    .replace("sucre ? crème fruits: fraise ou pêche ou ou framboise ou abricot", "sucre , crème fruits: fraise ou pêche ou ou framboise ou abricot")
                    .replace("poudre de écrémé ? jus de carotte", "poudre de écrémé , jus de carotte")
                    .replace("jus de potiron ? jus de betterave sureau ? arômes ?: : e330. e331 ferments sans", "jus de potiron , jus de betterave sureau , arômes  e330 e331")


                    /*=============Victoria 1-3_350======================================*/
                    .replace("vitamines: b3. b5. b12. b6. b2. b1. b9. b8:", "")
                    .replace("agent de traitement de la farine: l,cystéine.", "agent de traitement de la farine: lecystéine.")
                    .replace("pre,cooked wholegrain basmati rice", "precooked wholegrain basmati rice")
                    .replace("conservateur: e202. colorant: e160c, arôme.", "conservateur: e202. , colorant: e160c, arôme.")
                    .replace("glucose syrup, sweetened condensed skim milk, sugar, water, salted modified starch, colouring: ordinary caramel, salt, flavour, preservative :, emulsifier: mono and diglycerides of fatty acids, acidifier: citric acid. 31 35 % ,fr , ingrédients: sirop de glucose, lait écrémé condensé sucré, sucre, eau, amidon modifié, colorant: caramel ordinaire, sel, arôme, conservateur., émulsifiant: mono et diglycérides d'acide gras, acidifiant: acide citrique caramel.' min 35 %. en'. production date and best before date: see below , to be stored in a clean, dry and cool placeopening. fr: date de production et a consommer de préférence avant le: voir ci,dessous , a conserver dans un endroit propre, 50 et250 ,a consommer de préférence 6 semaines après ouverture. yf±jfiifi pd 2clfl",
                            "sirop de glucose, lait écrémé condensé sucré, sucre, eau, amidon modifié, colorant: caramel ordinaire, sel, arôme, conservateur., émulsifiant: mono et diglycérides d'acide gras, acidifiant: acide citrique caramel. min 35 %")
                    .replace(", vitamines: b1. b6 et b12.", "")
                    .replace("pulpes de fruits: pêche: pulpes de fruits 52.5%, mûre: pulpes de fruits 53%, abricot: pulpes de fruits 35%, framboise: pulpes de fruits 52.5%, fraise: pulpes de fruits 52.5%, poire: pulpes de fruits 52.5%",
                            "pulpes de fruits: pêche: 52.5% mûre:  53% abricot: 35% framboise: 52.5% fraise: 52.5% poire: 52.5%")
                    .replace("jeunes pousses de laitue verte, de laitue rouge, d'épinard.", "jeunes pousses de laitue verte de laitue rouge d'épinard.")
                    .replace("ingredienw quinoa. issu de l'agriculture biologique. risques de traces de gluten, de fruits à coques, de sésame, de lait et d'oeuf.",
                            "quinoa issu de l'agriculture biologique risques de traces de gluten de fruits à coques de sésame de lait et d'oeuf.")
                    .replace("tomates mi,séchées 68%,", "tomates mi-séchées 68%,")
                    .replace("émulsifiant: lécithine de tournesol,  issu de l'agriculture biologique", "émulsifiant: lécithine de tournesol issu de l'agriculture biologique")
                    .replace("arôme naturel de vanille.  ingrédients agricoles issus de l'agriculture biologique.", "arôme naturel de vanille issus de l'agriculture biologique.")
                    .replace("sirop de glucose/fructose,saccharose, conservateur: e202. acidifiant: e330.", "sirop de glucose/fructose,saccharose, conservateur: e202. , acidifiant: e330.")
                    .replace("correcteur d'acidité: e330. conservateur: anhydride sulfureux.", "correcteur d'acidité: e330. , conservateur: anhydride sulfureux.")
                    .replace("agent de traitement de la farine: l,cystéine, colorant: caroténoïdes.", "agent de traitement de la farine: lecystéine, colorant: caroténoïdes.")
                    .replace("pulpes de fruits: abricot: pulpes de fruits 53%, mûre: pulpes de fruits 53%, poire: pulpes de fruits 52.5%,", "pulpes de fruits: abricot: 53% mûre: 53% poire: 52.5%,")
                    .replace("légumes 60% :, eau, sel.", "légumes 60% , eau, sel.")
                    /*116*/
                    .replace(" farine de blé  graisse et huile végétales•eau", "farine de blé  graisse et huile végétales,eau")
                    .replace("dans un saladier température 500c au four traditionnelou au micro ondes. sortir le saladier du micro ondes et râper du chocolat noir sur le chocolat chaud en homogénéisant le tout avec une spatule pour redescendre à 27 oc. tempérez à nouveau au micro ondes pour atteindre 31 oc/320c. vous pourrez tremper vos fruits confits en les retirant du chocolat avec une fourchette et les poser doucement sur du papier sulfurisé pour les laisser sécher. courbe de température à respecter :",
                            "fruits confits")
                    .replace("farine de blé  graisse et huile végétales,eau , alcool éthylique ,sucre , sel , jus de citron concentré , colorant: caroténoïdes , agent de traitement de la farine: lecystéine.",
                            "farine de blé , graisse et huile végétales,eau , alcool éthylique ,sucre , sel , jus de citron concentré , colorant: caroténoïdes , agent de traitement de la farine: lecystéine.")
                    .replace("abricot: pulpes de fruits 53 %. cassis: pulpes de fruits. fraise: pulpes de fruits 55 %. framboise: pulpes de fruits 52 %. orange: pulpes de fruits 50 %. prune: pulpes de fruits 50 %. sucre, sirop de glucose de blé, gélifiant: pectines, arômes naturelsavec autres arômes naturels, acidifiant: jus de citron.",
                            "abricot: pulpes de fruits 53 %. cassis: pulpes de fruits. fraise: pulpes de fruits 55 %. framboise: pulpes de fruits 52 %. orange: pulpes de fruits 50 %. prune: pulpes de fruits 50 %. sucre, sirop de glucose de blé, gélifiant: pectines, arômes naturels avec autres arômes naturels, acidifiant: jus de citron.")
                    .replace("pulpe de pommes, sucre, sirop de glucose, gélifiant: pectines, acidifiant: acide citrique, arômes, colorants: e100 , e163 , e160c , e141.",
                            "pulpe de pommes, sucre, sirop de glucose, gélifiant: pectines, acidifiant: acide citrique, arômes, colorants: e100  e163  e160c  e141.")
                    .replace("polenta 76.4%, légumes 19.9%, eau , huile de tournesol , estragon , sel de mer   ingrédients issus de l'agriculture biologique", "polenta 76.4%, légumes 19.9%, eau , huile de tournesol , estragon , sel de mer ,  ingrédients issus de l'agriculture biologique")
                    .replace("jus de soja 87%, huile de tournesol, émulsifiant: gomme arabique, épaississants: gomme xanthane, carraghénanes, sucre de canne non raffiné. soja sans ogm. ingrédients issus de l'agriculture biologique.",
                            "jus de soja 87%, huile de tournesol, émulsifiant: gomme arabique, épaississants: gomme xanthane, carraghénanes, sucre de canne non raffiné. soja sans ogm. , ingrédients issus de l'agriculture biologique.")
                    .replace("agent de traitement de la farine l,cystéine.", "agent de traitement de la farine lecystéine.")
                    .replace("chocolat au lait 58%, riz 42% ingrédients issus de l'agriculture biologique.", "chocolat au lait 58%, riz 42% ,ingrédients issus de l'agriculture biologique.")
                    .replace("chocolat noir 57%, riz 48%. ingrédients issus de l'agriculture biologique.", "chocolat noir 57%, riz 48%. , ingrédients issus de l'agriculture biologique.")
                    .replace("conservateur: e202. e220 anhydride sulfureux, e330. e120. e150a, e133. e127.", "conservateur: e202. e220 anhydride sulfureux e330. e120. e150a. e133. e127.")
                    .replace("correcteur d'acidité e330. conservateur e220 anhydride sulfureux.", "correcteur d'acidité e330, conservateur e220 anhydride sulfureux.")
                    .replace("agent de traitement de la farine: l,cystéine , colorant: bêta,carotène.", "agent de traitement de la farine: lecystéine , colorant: bêtacarotène.")
                    .replace("herbes de provence. ingrédients agricoles issus de l'agriculture biologique.", "herbes de provence., ingrédients agricoles issus de l'agriculture biologique.")
                    .replace("préparation de protéines de soja et de ble: 44%, fromage fondu: 28%, panure: 28%. huile de tournesol", "préparation de protéines de soja et de ble: 44%, fromage fondu: 28%, panure: 28%. , huile de tournesol")
                    .replace("carmins, arôme naturel. contient des sulfites et anhydride sulfureux.", "carmins, arôme naturel., contient des sulfites et anhydride sulfureux.")
                    .replace("eau. fèves de soja, sel de nigari. sans conservateurproduits issus de l’agriculture biologique", "eau., fèves de soja, sel de nigari. sans conservateur ,produits issus de l’agriculture biologique")
                    .replace("farine de blé t80, beurre, eau, sucre de canne blond, sel de guérande, jus de citron concentré. ingrédients issus de l'agriculture biologique.", "farine de blé t80, beurre, eau, sucre de canne blond, sel de guérande, jus de citron concentré., ingrédients issus de l'agriculture biologique.")
                    .replace("jus de soja 89%, huile de tournesol, émulsifiant: lécithine de soja , épaississants: gomme xanthane, carraghénanes. ingrédients issus de l'agriculture biologique.",
                            "jus de soja 89%, huile de tournesol, émulsifiant: lécithine de soja , épaississants: gomme xanthane, carraghénanes. , ingrédients issus de l'agriculture biologique.")
                    .replace("fabriqué en espagne dans un atelier qui utilise fruits à coque, moutarde, ceufs, produits laitiers, gluten, lupin et celeri", "fabriqué en espagne dans un atelier qui utilise fruits à coque, moutarde, ceufs, produits laitiers, gluten, lupin , celeri")
                    .replace("tofu 93%, sel, épices.  ingrédients d'origine agricole obtenus selon les règles de production biologique.", "tofu 93%, sel, épices. , ingrédients d'origine agricole obtenus selon les règles de production biologique.")
                    .replace("correcteur d'acidité: concentré lactique, conservateur: sorbate de potassium, arôme, colorant: béta,carotène, vitamine e.", "correcteur d'acidité: concentré lactique, conservateur: sorbate de potassium, arôme, colorant: bétacarotène")
                    .replace("crème de lait pasteurisée, sel 2% , ferments lactiques.  biologique. les informations en gras sont destinées aux personnes intolérantes ou allergiques.", "crème de lait pasteurisée, sel 2% , ferments lactiques.  biologique.")
                    .replace("beurrepasteurisé doux. 1: ingrédient issu de l'agriculture biologique.", "beurre pasteurisé doux. ingrédient issu de l'agriculture biologique.")
                    .replace("beurre", "beurre.")
                    .replace("huiles et graisses végétales biologiques non hydrogénées1. eau, sel de mer 1.4%, émulsifiants: lécithine de soja biologique, arômes naturels, jus de citron concentré biologique. ingrédients d'origine végétale 1 ces huiles et graisses végétales donnent à ce produit primevère un profil riche en acides gras insaturés oméga 3.6.9formulé avec l'aide du service nutrition de l'institut pasteur de lille.",
                            "huiles et graisses végétales biologiques non hydrogénées., eau, sel de mer 1.4%, émulsifiants: lécithine de soja biologique, arômes naturels, jus de citron concentré biologique. ingrédients d'origine végétale")
                    /*439*/





































                    /*=============Dimitri 3_351-6_700=====================================*/


                    /*=============Remi 6_701-10_050========================================*/
































































































































































































































                    /*=============Moa reste==================================================*/
                    .replace(" protéines de lait'", "protéines de lait")
                    .replace(" extrat de vanille. 'ingredtent ongtne unjon eurqéenre.", " extrait de vanille")
                    .replace("lait écrémé – fruits: fraise ou framboise ou pêche ou abricot 10.3%", "lait écrémé , fruits: fraise ou framboise ou pêche ou abricot 10.3%")
                    .replace(" eau – crème – lait écrémé en poudre ", " eau , crème , lait écrémé en poudre ")
                    .replace("citrate de calcium – amidon transformé – épaississants: carraghénanes", "citrate de calcium , amidon transformé , épaississants: carraghénanes")
                    .replace("gomme de guar – colorants: anthocyanes", "gomme de guar , colorants: anthocyanes")
                    .replace("cochenille – arômes – édulcorants: acésulfame k", "cochenille , arômes , édulcorants: acésulfame k")
                    .replace("sucralose –  ferments lactiques du yaourt ", "sucralose , ferments lactiques du yaourt ")
                    .replace("jus de potiron — amidon transformé de mais ", "jus de potiron , amidon transformé de maïs ")
                    .replace("sucre 8.9% · jus de carotte · sirop de glucose", "sucre 8.9% , jus de carotte , sirop de glucose")
                    .replace("une garniturecomposée de: viande bovine française de race charolaise 53%", "une garniture composée de: viande bovine française de race charolaise 53%")
                    .replace("émulsifiant: e471. colorant: bêtacarotène.", "émulsifiant: e471, colorant: bêtacarotène.")
                    .replace("amidon transformé de pomme d terre. salade 9%.", "amidon transformé de pomme d terre, salade 9%.")
                    .replace("farine de blé malté, antioxydant: e316. œuf.", "farine de blé malté, antioxydant: e316, œuf")
                    .replace("agent de traitement de la farine: e300. œuf.", "agent de traitement de la farine: e300, œuf")
                    .replace("pain de mie au blé malté 40%— poulet rôti 19%— sauce caesar 13%   mayonnaise allégée en matières grasses,", "pain de mie au blé malté 40%, poulet rôti 19%, sauce caesar 13%   mayonnaise allégée en matières grasses,")
                    .replace(", ognon, boyau naturel de mouton", ", oignon, boyau naturel de mouton")
                    .replace("boyau naturel de mouton.", "boyau naturel de mouton")
                    .replace("colorant e160c", "colorant: e160c")
                    .replace("sucre. boyau naturel de mouton", "sucre, boyau naturel de mouton")
                    .replace("arômes. boyau naturel de mouton", "arômes, boyau naturel de mouton")
                    .replace("viande de porc origine union européenne (81 eau, sel, sirop de glucose, dextrose, arôme naturel, épices et plantes aromatiques, acidifiant: e331. conservateur: e250.", "viande de porc origine union européenne , eau, sel, sirop de glucose, dextrose, arôme naturel, épices et plantes aromatiques, acidifiant: e331, conservateur: e250.")
                    .replace("fumée liquide émulsifiants: e450,e451. exhausteur de goût: e621. antioxydants: e300 , e301 , e316. colorant: e120. conservateurs: e250,e326,e262. ferments.", "fumée liquide émulsifiants: e450 e451, exhausteur de goût: e621, antioxydants: e300 e301 e316 colorant: e120, conservateurs: e250 e326 e262, ferments.")
                    .replace("antioxydant: ascorbate de sodium (e300. enveloppe naturelle de mouton.", "antioxydant: ascorbate de sodium e300, enveloppe naturelle de mouton.")
                    .replace("sirop de glucose, stabilisants: e451. e450 arômes, antioxydants: e315. e316 conservateur: e250. boyau naturel de mouton.", "sirop de glucose, stabilisants: e451 e450, arômes, antioxydants: e315 e316, conservateur: e250, boyau naturel de mouton.")
                    .replace("conservateur: nitrite de sodium. fumage au bois de hêtre.", "conservateur: nitrite de sodium, fumage au bois de hêtre.")
                    .replace("colorant e120. boyau nature de mouton.", "colorant e120, boyau nature de mouton.")
                    .replace("stabilisants: di,, tri, et polyphosphates, ", "stabilisants: diphosphate triphosphate et polyphosphates, ")
                    .replace("conservateur: e262. antioxydants: e316,e331.", "conservateur: e262. , antioxydants: e316 e331.")
                    .replace("conservateur: nitrite de sodium. boyau naturel de porc.", "conservateur: nitrite de sodium, boyau naturel de porc.")
                    .replace("ferments. boyau naturel de porc.", "ferments, boyau naturel de porc.")
                    .replace("exhausteur de goût: monoglutamate de sodium. boyau naturel de porc.", "exhausteur de goût: monoglutamate de sodium, boyau naturel de porc.")

                    /*10377*/
                    .replace("exhausteur de goût: e621. arômes naturel, acidifiant: e575 , colorant: e120 , antioxydant: e316 , conservateur: e250. enveloppe.", "exhausteur de goût: e621. arômes naturel, acidifiant: e575 , colorant: e120 , antioxydant: e316 , conservateur: e250. , enveloppe.")
                    .replace("acidifiant: glucono,delta,lactone, conservateur: nitrite d", "acidifiant: glucono delta lactone, conservateur: nitrite d")
                    .replace("colorant: e120. boyau naturel de mouton", "colorant: e120. , boyau naturel de mouton.")
                    .replace("plante aromatique. boyaux naturels de mouton.", "plante aromatique. , boyaux naturels de mouton.")
                    .replace(",conservateur: nitrite de sodium. boyau naturel de porc.", "conservateur: nitrite de sodium. , boyau naturel de porc.")
                    .replace(" colorants: e160c, e120 , antioxydant: e301 , conservateur: e250. traces de moutarde. porc origine: ue.", " colorants: e160c e120 , antioxydant: e301 , conservateur: e250. , traces de moutarde. , porc origine: ue.")
                    .replace("conservateurs: e250,e326. antioxydants: e300,e301,e316. dextrose, acidifiant: e262. colorant: e120. ferments.", "conservateurs: e250 e326., antioxydants: e300 e301 e316., dextrose, acidifiant: e262. colorant: e120. , ferments.")
                    .replace("exhausteur de gout: e621. arômes naturels, dextrose, acidifiant: e575. colorant: e120. antioxydant: e316. conservateur: e250. traces éventuelles d'oeuf, lait, soja et fruits à coque. boyau non comestible, retirez avant consommation",
                            "exhausteur de gout: e621., arômes naturels, dextrose, acidifiant: e575., colorant: e120., antioxydant: e316., conservateur: e250., traces éventuelles d'oeuf, lait, soja et fruits à coque., boyau non comestible")
                    .replace("antioxydant: acide ascorbique, conservateur: nitrite de sodium, eau, sel. traces éventuelles de lait et de céréales contenant du gluten.", "antioxydant: acide ascorbique, conservateur: nitrite de sodium eau sel., traces éventuelles de lait et de céréales contenant du gluten.")
                    .replace("conservateur: nitrite de sodium. boyau naturel de mouton. ingrédient issu de l'agriculture biologique traces éventuelles d oeufs, de soja et de fruits à coque", "conservateur: nitrite de sodium., boyau naturel de mouton., ingrédient issu de l'agriculture biologique , traces éventuelles d'oeufs de soja et de fruits à coque")
                    .replace("girofle, ferments. enveloppe: boyau collagénique.", "girofle, ferments., enveloppe: boyau collagénique.")
                    .replace("ferments. enveloppe: boyau naturel de porc.", "ferments., enveloppe: boyau naturel de porc.")
                    .replace("e252. ferments.", "e252., ferments.")
                    .replace("plantes aromatiques, sirop de glucose, dextrose, conservateur: nitrate de potassium, ferments, boyau naturel de porc. 152g de viande mise en œuvre pour 100 g de produit fini.",
                            "plantes aromatiques, sirop de glucose, dextrose, conservateur: nitrate de potassium, ferments, boyau naturel de porc., 152g de viande mise en œuvre pour 100 g de produit fini.")
                    .replace("ferments. boyau naturel de porc.", "ferments., boyau naturel de porc.")
                    .replace("ferments. boyau en fibre animale.", "ferments., boyau en fibre animale.")
                    .replace("ferment. boyau naturel de bœuf.", "ferments., boyau naturel de bœuf.")
                    .replace("saumon atlantiqueélevé au royaume,uni, norvège ou irlande 97.2 %, sel 2.8 %. ingrédient issu de l'agriculture biologique. pays d'élevage: voir ci,dessous.",
                            "saumon atlantiqueélevé au royaume-uni, norvège ou irlande 97.2 %, sel 2.8 %. ,ingrédient issu de l'agriculture biologique. pays d'élevage")
                    .replace("saumon atlantiquenourri sans ogm97%, sel 3%.", "saumon atlantique nourri sans ogm 97%, sel 3%.")
                    .replace("sirop de glucose de maïs, igre, coriandre. mon élevé en norvège, ecosseou irlande: voir impression 'essous.", "sirop de glucose de maïs, igre, coriandre., saumon élevé en norvège ecosse ou irlande")
                    .replace("saumon 80 %, eau, protéine de soja, gélifiant: e407. blanc d'oeuf poudre, stabilisants: e452,e451. sucre, arôme naturel de romarin, sel, colorant: e160c.",
                            "saumon 80 %, eau, protéine de soja, gélifiant: e407., blanc d'oeuf poudre, stabilisants: e452 e451., sucre, arôme naturel de romarin, sel, colorant: e160c.")
                    .replace("boyau naturel de porc. poudre de fleurage: carbonate de calcium. talc.", "boyau naturel de porc., poudre de fleurage: carbonate de calcium., talc.")
                    .replace(" graines de sésame, maltodextrine de maïs, algues séchées, piment en poudre, colorants: e140. e100. e160c, e162. e150a.",
                            " graines de sésame, maltodextrine de maïs, algues séchées, piment en poudre, colorants: e140. e100. e160c e162. e150a.")
                    .replace("sel marin. dorure: œufs.", "sel marin., dorure: œufs.");
            myWriter.write(replaceEnderscoreIngredients + "\n");

            em.getTransaction().begin();

            Product products = new Product(name, nutriGrade, energie, fat, sugar, fiber, proteins, salt, calcium, magnesium, iron, fer, beta_carotene, palm_oil);

            List<String> blockIngredientj = new ArrayList<String>(Arrays.asList(replaceEnderscoreIngredients.trim().split(",")));
            for (int j = 0; j < blockIngredientj.size(); j++) {

                if (deleteSameIngredients.add(blockIngredientj.get(j).trim())) {
                    System.out.println(blockIngredientj.get(j));

                    /*Ingredient ingredientsDB = new Ingredient(blockIngredientj.get(j));
                    em.persist(ingredientsDB);*/

                }

            }

            em.getTransaction().commit();

            /*for (int j = 1; j < blockIngredientj.size(); j++) {
                List<String> blockIngredientk = new ArrayList<String>(Arrays.asList(blockIngredientj.get(j).trim().split(";")));

                for (int k = 1; k < blockIngredientk.size(); k++) {
                    System.out.println(blockIngredientk.get(k));
                    //List<String> blockIngredientClean = new ArrayList<String>(Arrays.asList(blockIngredientk.get(j).trim().split(";")));
                    if (deleteSameIngredients.add(blockIngredientj.get(j).trim().toLowerCase(Locale.ROOT))) {
                        List<String> blockIngredientf = new ArrayList<String>(Arrays.asList(blockIngredientj.get(j).trim().split(";")));
                        for (int f = 1; f < blockIngredientf.size(); f++) {
                            if (deleteSameIngredients2.add(blockIngredientf.get(f).trim().toLowerCase())) {

                                System.out.println(blockIngredientf.get(f));
                                em.getTransaction().begin();
                                Ingredient ingredientsDB = new Ingredient(blockIngredientf.get(f));
                                em.persist(ingredientsDB);
                                em.getTransaction().commit();
                            }
                        }
                        *//*if(deleteSameIngredients.add(blockIngredientk.get(k))) {

                            System.out.println(blockIngredientj.get(j));
                            System.out.println("-----------------------------------------------------");
                            System.out.println(blockIngredientk.get(k));
                        }*//*
                    }
                    *//*em.getTransaction().begin();
                    Ingredient ingredient = new Ingredient(blockIngredient.get(j));
                    em.persist(ingredient);
                    em.getTransaction().commit();*//*
                }
            }*/
        }
        myWriter.close();


        emf.close();
        em.close();
    }
}
