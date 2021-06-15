package org.fantasticfour;

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

    public static void main(String[] args) throws IOException {


        Path paths = Paths.get("C:/Users/Poyatos Rémi/Desktop/projet/open-food-facts.csv");

        File newFile = new File("C:\\Users\\Poyatos Rémi\\Desktop\\projet\\traitement-fichier-jpa-off\\src\\main\\resources\\files\\recensement-copy.csv");
        FileWriter myWriter = new FileWriter("C:\\Users\\Poyatos Rémi\\Desktop\\projet\\traitement-fichier-jpa-off\\src\\main\\resources\\files\\recensement-copy.csv");
        List<String> lines = Files.readAllLines(paths, StandardCharsets.UTF_8);

        Iterator<String> fileIte = lines.iterator();
        HashSet<String> deleteSameCategories = new HashSet<>();
        HashSet<String> deleteSameMarks = new HashSet<>();
        HashSet<String> deleteSameIngredients = new HashSet<>();
        HashSet<String> deleteSameIngredients2 = new HashSet<>();

        List<Category> allCategories = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] part = line.replace("|’", "l'")
                    .replace("—|a", " a")
                    .replace("ty|ate de sodium", "tyate de sodium")
                    .replace(" conservateur |antioxydant: levure", " conservateur antioxydant: levure")
                    .replace("|% [maltodextrine de blé", ",maltodextrine de blé")
                    .replace("Filets de colin d’A|aska 72%qualité sans arête*", "Filets de colin d’Aaska 72% qualité sans arête")
                    .replace("Calin+ Fruits Pêche, Abricot, Fraise, Framboise)","Calin+ Fruits Pêche, Abricot, Fraise, Framboise")
                    .split("\\|");

            String categorie = part[0];
            String mark = part[1];
            String name = part[2];
            String nutriGrade = part[3];
            String ingredients = part[4];

            double energie = 0;
            if (!part[5].isEmpty()) {
                energie = Double.parseDouble(part[5]);
            }
            double fat = 0;
            if (!part[6].isEmpty()) {
                fat = Double.parseDouble(part[6]);
            }

            double sugar = 0;
            if (!part[7].isEmpty()) {
                sugar = Double.parseDouble(part[7]);
            }


            double fiber = 0;
            if (!part[8].isEmpty()) {
                fiber = Double.parseDouble(part[8]);
            }
            double proteins = 0;
            if (!part[9].isEmpty()) {
                proteins = Double.parseDouble(part[9]);
            }
            double salt = 0;
            if (!part[10].isEmpty()) {
                salt = Double.parseDouble(part[10]);
            }
            double vitA = 0;
            if (!part[11].isEmpty()) {
                vitA = Double.parseDouble(part[11]);
            }
            double vitD = 0;
            if (!part[12].isEmpty()) {
                vitD = Double.parseDouble(part[12]);
            }
            double vitE = 0;
            if (!part[13].isEmpty()) {
                vitE = Double.parseDouble(part[13]);
            }
            double vitK = 0;
            if (!part[14].isEmpty()) {
                vitK = Double.parseDouble(part[14]);
            }
            double vitC = 0;
            if (!part[15].isEmpty()) {
                vitC = Double.parseDouble(part[15]);
            }
            double vitB1 = 0;
            if (!part[16].isEmpty()) {
                vitB1 = Double.parseDouble(part[16]);
            }
            double vitB2 = 0;
            if (!part[17].isEmpty()) {
                vitB2 = Double.parseDouble(part[17]);
            }
            double vitPP = 0;
            if (!part[18].isEmpty()) {
                vitPP = Double.parseDouble(part[18]);
            }
            double vitB6 = 0;
            if (!part[19].isEmpty()) {
                vitB6 = Double.parseDouble(part[19]);
            }
            double vitB9 = 0;
            if (!part[20].isEmpty()) {
                vitB9 = Double.parseDouble(part[20]);
            }
            double vitB12 = 0;
            if (!part[21].isEmpty()) {
                vitB12 = Double.parseDouble(part[21]);
            }
            double calcium = 0;
            if (!part[22].isEmpty()) {
                calcium = Double.parseDouble(part[22]);
            }
            double magnesium = 0;
            if (!part[23].isEmpty()) {
                magnesium = Double.parseDouble(part[23]);
            }
            double iron = 0;
            if (!part[24].isEmpty()) {
                iron = Double.parseDouble(part[24]);
            }
            double fer = 0;
            if (!part[25].isEmpty()) {
                fer = Double.parseDouble(part[25]);
            }
            double beta_carotene = 0;
            if (!part[26].isEmpty()) {
                beta_carotene = Double.parseDouble(part[26]);
            }
            boolean palm_oil = false;
            if (!part[27].isEmpty()) {
                palm_oil = Boolean.parseBoolean(part[27]);
            }
            /*if (deleteSameCategories.add(categorie)) {
                em.getTransaction().begin();
                Category categories = new Category(categorie);
                em.persist(categories);
                em.getTransaction().commit();
            }

            if (deleteSameMarks.add(mark)) {
                em.getTransaction().begin();
                Mark marks = new Mark(mark);
                em.persist(marks);
                em.getTransaction().commit();
            }*/

            System.out.println("nomProduit----------------------------------");
            System.out.println(i+" "+name);
            System.out.println("------------------------------------------------");


            Product products = new Product(name, nutriGrade, energie, fat, sugar, fiber, proteins, salt, calcium, magnesium, iron, fer, beta_carotene, palm_oil);


            String replaceEnderscoreIngredients = ingredients.toLowerCase(Locale.ROOT)
                    .replace("_", "")
                    .replace("]", "")
                    .replace("[", "")
                    .replace(")", "")
                    .replace(" .", "")
                    .replace("[%","")
                    .replace("*", "")
                    .replace(".   ", "")
                    .replace("-", ",")
                    .replace(" : ", ": ")
                    .replace(";", ",")
                    .replace("marmelade de citrons confiture d’ananas et de fruits de la passion", "marmelade de citrons confiture d’ananas et de fruits de la passion,")
                    .replace("confiture de framboises et de litchis", "confiture de framboises et de litchis,")
                    .replace("confiture de pamplemousses et de fruits du dragon", "confiture de pamplemousses et de fruits du dragon,")
                    .replace("plein air, issus de l'agriculture", "plein air issus de l'agriculture")
                    .replace("confiture de mangues et de pêches","confiture de mangues et de pêches,")
                    .replace("confiture de poires et de mirabelles","confiture de poires et de mirabelles,")
                    .replace("confiture de fraises à la verveine","confiture de fraises à la verveine,")
                    .replace("confiture de framboises et de groseilles","confiture de framboises et de groseilles,")
                    .replace("confiture extra de cerises et de mûres","confiture extra de cerises et de mûres,")
                    .replace("marmelade d’oranges à la cannelle","marmelade d’oranges à la cannelle,")
                    .replace("confiture extra de 4 fruits","confiture extra de 4 fruits,")
                    .replace("marmelade d’oranges douces et de mandarines","marmelade d’oranges douces et de mandarines,")
                    .replace("confiture de fraises et de groseilles","confiture de fraises et de groseilles,")
                    .replace("confiture extra de rhubarbe","confiture extra de rhubarbe,")
                    .replace("confiture extra de fraises et de fraises des bois","confiture extra de fraises et de fraises des bois,")
                    .replace("marmelade de citrons confiture d’ananas et de fruits de la passion","marmelade de citrons confiture d’ananas et de fruits de la passion,")
                    .replace("confiture extra de reines","confiture extra de reines,")
                    .replace("oeclatatton nutritionnelle pour / nutrition declaration for / nahrwertdeklaration pto / voedingswaardever,melding per 100 g energie / energy / energie","")
                    .replace("anti,agglomérant","antiagglomérant")
                    .replace("/ ingrediénten:","")
                    .replace("[eau",",eau")
                    .replace("sirop de glucose de blé ou mai's","sirop de glucose de blé ou maïs")
                    .replace("dextrose de ma`i`s","dextrose de maïs")
                    .replace("beurre. emmental râpé20%. levure","beurre, emmental râpé20%, levure")
                    .replace("lait  écrémé en poudre. farine de malt d‘orge","lait  écrémé en poudre, farine de malt d‘orge")
                    .replace("sucre ? crème fruits: fraise ou pêche ou ou framboise ou abricot","sucre , crème fruits: fraise ou pêche ou ou framboise ou abricot")
                    .replace("poudre de écrémé ? jus de carotte","poudre de écrémé , jus de carotte")
                    .replace("jus de potiron ? jus de betterave sureau ? arômes ?: : e330. e331 ferments sans","jus de potiron , jus de betterave sureau , arômes  e330 e331")
                    /*=============Victoria 1-3_350======================================*/
                    .replace("vitamines: b3. b5. b12. b6. b2. b1. b9. b8:","")
                    .replace("agent de traitement de la farine: l,cystéine.","agent de traitement de la farine: lecystéine.")
                    .replace("pre,cooked wholegrain basmati rice","precooked wholegrain basmati rice")


                    /*=============Dimitri 3_351-6_700=====================================*/


                    /*=============Remi 6_701-10_050========================================*/
                    .replace("maïs 70%, sucre 28 %, extrait de malt d'orge, sel.", "maïs 70%, sucre 28 %, extrait de malt d'orge, sel,")
                    .replace("sirop de malt d'orge bio, arôme naturel d'abricot avec autres arômes naturels,  sel de mer, émulsiﬁant: lécithines de tournesol", "sirop de malt d'orge bio, arôme naturel d'abricot avec autres arômes naturels,  sel de mer, émulsiﬁant: lécithines de tournesol.")
                    .replace(" préparation à base d'huile végétale, sel, vitamines: niacine, e, b6.", " préparation à base d'huile végétale, sel, vitamines: niacine, e, b6,")
                    .replace("colorant: caroténoïdes, antiagglomérant carbonate de calcium, émulsifiant lécithines de tournesol, arôme, antioxydant tocophérols.", "colorant: caroténoïdes, antiagglomérant carbonate de calcium, émulsifiant lécithines de tournesol, arôme, antioxydant tocophérols,")
                    .replace("préparation à base d'huile végétale, sel, vitamines: niacine, e, b6, riboflavine, thiamine, acide folique, b12. fer.","préparation à base d'huile végétale, sel, vitamines: niacine, e, b6, riboflavine, thiamine, acide folique, b12, fer.")
                    .replace("émulsifiant: mono, et diglycérides d'acides gras, vitamines c, niacine, acide pantothénique, b6. riboflavine", "émulsifiant: mono, et diglycérides d'acides gras, vitamines c, niacine, acide pantothénique, b6, riboflavine")
                    .replace("graines de lin brin 11%, fruits secs, sucre de coco 6.3%, huile de coco 6.3%, noix de coco 2.1%, sel, poudre de vanille.:", "graines de lin brin 11%, fruits secs, sucre de coco 6.3%, huile de coco 6.3%, noix de coco 2.1%, sel, poudre de vanille:")
                    .replace("farine de blé sucre: huile de palme, lait en poudre écrémé, poudres à lever: e503. e500. e450. sel,", "farine de blé sucre: huile de palme, lait en poudre écrémé, poudres à lever: e503, e500, e450, sel,")
                    .replace("vitamines: c, niacine, b6. riboflavine, thiamine, acide folique, b12.", "")
                    .replace("flocons d'épeautre 45%, flocons d'avoine, abricots 8%, graines de tournesol, pruneaux 7%, pommes 6%, amandes 5%, noix de pécan.", "flocons d'épeautre 45%, flocons d'avoine, abricots 8%, graines de tournesol, pruneaux 7%, pommes 6%, amandes 5%, noix de pécan,")
                    .replace("flocons d'avoine complet 35%, sucre, flocons de blé complet“ 13%, farine de blé 11.9%, chocolat noir 11%, huile de tournesol, farine de riz, noix de coco en poudre, cacao en poudre 1.6%, miel, sel, extrait de malt d'orge, émulsifiant: lécithines de tournesol, antioxydant: extrait riche en tocophérol", "flocons d'avoine complet 35%, sucre, flocons de blé complet“ 13%, farine de blé 11.9%, chocolat noir 11%, huile de tournesol, farine de riz, noix de coco en poudre, cacao en poudre 1.6%, miel, sel, extrait de malt d'orge, émulsifiant: lécithines de tournesol, antioxydant: extrait riche en tocophérol.")
                    .replace("céréales complètes 67.4%, caramel 20.4% crème caramel au beurre salé 11.6%, caramel 8.8%, huile de tournesol, sucre de canne, sel de mer // ingrédients issus de l’agriculture biologique", "céréales complètes 67.4%, caramel 20.4% crème caramel au beurre salé 11.6%, caramel 8.8%, huile de tournesol, sucre de canne, sel de mer // ingrédients issus de l’agriculture biologique.")
                    .replace("superfruits 9.3 % cranberries 4.3 %, jus concentré de grenade 3.8 %, cassis lyophilisés 0.6%, myrtilles lyophilisées 0.6 %", "superfruits 9.3 % cranberries 4.3 %, jus concentré de grenade 3.8 %, cassis lyophilisés 0.6%, myrtilles lyophilisées 0.6 %.")
                    .replace("flocons d'avoine 54%, sucre de canne complet, huile de tournesol, sirop de riz, billettes de riz, amandes 4%, tapioca", "flocons d'avoine 54%, sucre de canne complet, huile de tournesol, sirop de riz, billettes de riz, amandes 4%, tapioca.")
                    .replace("sarrasin 99%, sel", "sarrasin 99%, sel.")
                    .replace(" noisettes toastées, pommes séchées 1%, noix de coco séchées 1%, figues séchées", " noisettes toastées, pommes séchées 1%, noix de coco séchées 1%, figues séchées.")
                    .replace("millet", "millet.")
                    .replace("noix de coco toastée en lamelles 4%. produits issus de l'agriculture biologique", "noix de coco toastée en lamelles 4%.")
                    .replace("flocons d'avoine complets", "flocons d'avoine complets.")
                    .replace("flocons de blé, flocons d'orge· pépites de chocolat noir 10%,","flocons de blé, flocons d'orge, pépites de chocolat noir 10%,")
                    .replace("ingrédients issus de l'agriculture biologique.", "")
                    .replace("céréales 65.2%, sucre de canne, huile de tournesol, noix de coco, sirop de riz. ingrédients issus de l’agriculture biologique.", "céréales 65.2%, sucre de canne, huile de tournesol, noix de coco, sirop de riz,")
                    .replace("sucre de canne, huile de tournesol, morceaux de chocolat noir 5.1 %, sirop de riz, cacao maigre en poudre. ingrédients issus de l’agriculture biologique.", "sucre de canne, huile de tournesol, morceaux de chocolat noir 5.1 %, sirop de riz, cacao maigre en poudre.")
                    .replace(" chocolat 3.9 % (sucre de canne sirop de malt d'orge , sucre de canne, sirop de blé, pâte de cacao , beurre de cacao,", " chocolat 3.9 % , sucre de canne , sirop de malt d'orge , sucre de canne, sirop de blé, pâte de cacao , beurre de cacao,")
                    .replace("sarrasin, datte, noisette 5%, éclats de fève de cacao cru 3.9%, poudre de cacao cru 1.6%, poudre de caroube crue, vanille", "sarrasin, datte, noisette 5%, éclats de fève de cacao cru 3.9%, poudre de cacao cru 1.6%, poudre de caroube crue, vanille.")
                    .replace("fibre de guar, arôme naturel, farine de seigle.  ingrédients d'origine agricole obtenus selon le mode de production biologique.", "fibre de guar, arôme naturel, farine de seigle.")
                    .replace("blancs d’œufs frais arômes naturels, sel, émulsifiants, sirop de sucre inverti, protéines de lait, arôme, levure désactivée, colorant.", "blancs d’œufs frais ,arômes naturels, sel, émulsifiants, sirop de sucre inverti, protéines de lait, arôme, levure désactivée, colorant.")
                    .replace("farine de blé 1.7 %, farine de riz, chocolat au lait 0.7 %, farine de soja, miel. ingrédients agricoles issus de l'agriculture biologique.", "farine de blé 1.7 %, farine de riz, chocolat au lait 0.7 %, farine de soja, miel.")
                    .replace("poudre de cacao maigre 2.2%, sirop d'épeautre.ingrédients agricoles issus de l'agriculture biologique.", "poudre de cacao maigre 2.2%, sirop d'épeautre.")
                    .replace("sucre de canne, huile de palme non hydrogénée, sirop de riz, billettes de céréales 4.1 %, farine de blé", "sucre de canne, huile de palme non hydrogénée, sirop de riz, billettes de céréales 4.1 %, farine de blé.")
                    .replace("diglycérides d'acides gras, vitamines: thiamine, riboflavine, b3. acide pantothénique, b6. b8. acide folique, b12. fer.", "diglycérides d'acides gras, fer.")
                    .replace(", vitamines: niacine, vitamine e, acide pantothénique, vitamine b6. riboflavine, thiamine, acide folique, vitamine b12 diphosphate ferrique", "")
                    .replace("vitamines: niacine, b6. riboflavine, thiamine, acide folique, b12.", "")
                    .replace("céréales complètesblé complet, farine de blé complet, fruits secsraisins secs 10.9 %, bananes sucrées aromatisées 8.6 %, noix de coco 3.1 %, pommes 2.3 %, noisettes 2.3 %, sucre, extrait de malt d'orge, sel, vitamines: niacine, acide pantothénique, b6. riboflavine, thiamine, acide folique, biotine, b12. fer, arôme.", "céréales complètes, blé complet, farine de blé complet, fruits secs, raisins secs 10.9 %, bananes sucrées aromatisées 8.6 %, noix de coco 3.1 %, pommes 2.3 %, noisettes 2.3 %, sucre, extrait de malt d'orge, sel, fer, arôme.")
                    .replace("caramel, sirop de sucre caramélisé , vitamines: bl, b2. b3. b5. b6. b9. b8. bl2", "caramel, sirop de sucre caramélisé ")
                    .replace("dérivé du lactosérum riche en calcium, vitamines: thiamine, riboflavine, b3. b6. acide folique, b12. e,", "dérivé du lactosérum riche en calcium,")
                    .replace(", vitamines: niacine, e, b6. riboflavine, thiamine, acide folique, b12 , fer. traces d'arachides, de soja, d'autres fruits à coque et de graines de sésame", "")
                    .replace("vitamines: niacine, acide pantothénique, riboflavine, b6. thiamine, acide folique, biotine, b12 , poudre à lever: carbonate acide de sodium , protéines de lait , fer. traces d'arachide, soja, fruits à coque et graines de sésame.", " poudre à lever: carbonate acide de sodium , protéines de lait , fer.")
                    .replace("céréales 61.5 %1% farine complete de segle 1%,pepite de chocolat 3%,sucre, huile de colza,pate de cacao, huiles végétalesdextrose beurre de cacao émulsifiant, noisettes 2.5 %, miel 2%, minérauxpoudre à lever,, sel correcteur d acidité, emulsifiant, aromes.", "céréales 61.5%, farine complete de segle 1%,pepite de chocolat 3%,sucre, huile de colza,pate de cacao, huiles végétales,dextrose ,beurre de cacao, émulsifiant, noisettes 2.5 %, miel 2%, minéraux ,poudre à lever, sel ,correcteur d acidité, emulsifiant, aromes.")
                    .replace("céréales,   pâte à tartiner à la noisette du lot et garenne et au cacaosucre, noisettes du lot et garonne, poudre de lait, huile de coco, poudre de cacao, huile de colza, émulsiﬁant: lécithine de tournesol, arôme: vanille, chocolat en poudre, son de blé, sucre, sel, émulsifiant: mono, et diglycérldes d’acides gras, vitamines, b6. riboflavine, thiamine, acide folique, b12 et fer. , % dans le produit fini", "céréales, pâte à tartiner à la noisette du lot et garonne et au cacao, sucre, noisettes du lot et garonne, poudre de lait, huile de coco, poudre de cacao, huile de colza, émulsiﬁant: lécithine de tournesol, arôme: vanille, chocolat en poudre, son de blé, sucre, sel, émulsifiant: mono, et diglycérldes d’acides gras, fer.")
                    .replace("flocons d’avoine, farine de blé— graisse végétale— sucre — pépites de cranberries, sucre, humectant: glycérine, correcteur d'acidité: acide citrique, huile de tournesol, sirop de sucre inverti — billettes de riz soufflées, sucre, gluten de blé, avoine sirop de malt d’orge, sel — poudre à lever: carbonates de sodium , émulsifiant: e472e , sel — arôme — vitamines niacine, riboflavine, b12. d3. b6 thiamine — fer.", "flocons d’avoine, farine de blé, graisse végétale, sucre, pépites de cranberries, sucre, humectant: glycérine, correcteur d'acidité: acide citrique, huile de tournesol, sirop de sucre inverti, billettes de riz soufflées, sucre, gluten de blé, avoine sirop de malt d’orge, sel, poudre à lever: carbonates de sodium , émulsifiant: e472e , sel, arôme, fer.")
                    .replace("flocons d’avoine, farine de blé— graisse végétale— sucre — sirop de sucre inverti— billettes de riz soufflées, sucre, gluten de blé, avoine sirop de malt d’orge, sel — poudre à lever: carbonates de sodium , émulsifiant: e472e , sel — arôme — vitamines niacine, riboflavine, b12. d3. b6 thiamine — fer.", "flocons d’avoine, farine de blé, graisse végétale, sucre , sirop de sucre inverti, billettes de riz soufflées, sucre, gluten de blé, avoine ,sirop de malt d’orge, sel ,poudre à lever: carbonates de sodium , émulsifiant: e472e , sel ,arôme , fer.")
                    .replace("vitamines: niacine, e, b6. riboflavine, thiamine, folacine, b12 ,", "")
                    .replace("céréalesflocons d'avoine, flocons de blé,", "céréales, flocons d'avoine, flocons de blé,")
                    .replace("céréales 55% flocons d’avoine, flocons de blé, farine de blé, céréales extrudées,", "céréales 55% ,flocons d’avoine, flocons de blé, farine de blé, céréales extrudées,")
                    .replace("sel, farine d'orge maltée, vitamines: c, b3 ou pp, b6. b2. b1. b9. b21.", "sel, farine d'orge maltée, ")
                    .replace("son de riz, sucre de canne, sel //  ingrédients issus de l'agriculture biologique", "son de riz, sucre de canne, sel .")
                    
                    
                    /*=============Moa reste==================================================*/
                    .replace(" protéines de lait'","protéines de lait")
                    .replace(" extrat de vanille. 'ingredtent ongtne unjon eurqéenre."," extrait de vanille")
                    .replace("lait écrémé – fruits: fraise ou framboise ou pêche ou abricot 10.3%","lait écrémé , fruits: fraise ou framboise ou pêche ou abricot 10.3%")
                    .replace(" eau – crème – lait écrémé en poudre "," eau , crème , lait écrémé en poudre ")
                    .replace("citrate de calcium – amidon transformé – épaississants: carraghénanes","citrate de calcium , amidon transformé , épaississants: carraghénanes")
                    .replace("gomme de guar – colorants: anthocyanes","gomme de guar , colorants: anthocyanes")
                    .replace("cochenille – arômes – édulcorants: acésulfame k","cochenille , arômes , édulcorants: acésulfame k")
                    .replace("sucralose –  ferments lactiques du yaourt ","sucralose , ferments lactiques du yaourt ")
                    .replace("jus de potiron — amidon transformé de mais ","jus de potiron , amidon transformé de mais ")
                    .replace("sucre 8.9% · jus de carotte · sirop de glucose","sucre 8.9% , jus de carotte , sirop de glucose")
                    ;
            myWriter.write(replaceEnderscoreIngredients+"\n");
            List<String> blockIngredientj = new ArrayList<String>(Arrays.asList(replaceEnderscoreIngredients.trim().split(",")));
            for (int j = 0; j < blockIngredientj.size(); j++) {

                if (deleteSameIngredients.add(blockIngredientj.get(j).trim())) {
                    System.out.println(blockIngredientj.get(j));

                    /*em.getTransaction().begin();
                    Ingredient ingredientsDB = new Ingredient(blockIngredientj.get(j));
                    em.persist(ingredientsDB);
                    em.getTransaction().commit();*/
                }

            }
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
       /* while (fileIte.hasNext()) {
            fileIte.next();
            String[] getElem = fileIte.next().split("\\|");
            String category = getElem[0];
            String replaceEnderscore = category.replace("_","");
            //System.out.println(replaceEnderscore);
            if (deleteSame.add(category)) {
                System.out.println(category);
            }*/
            /*List<String> blockI = Arrays.asList(replaceEnderscore.split(","));
            System.out.println("---------------------------------");
            System.out.println(getIngredient[0]);
            System.out.println(getIngredient[1]);
            System.out.println(getIngredient[2]);
            System.out.println("________________________________");
            for (int i =0;i<blockI.size();i++){
                System.out.println(blockI.get(i));
            }}*/

        emf.close();
        em.close();
    }
}
