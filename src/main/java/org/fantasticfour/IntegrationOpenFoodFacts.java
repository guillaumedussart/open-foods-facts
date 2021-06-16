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

         FileWriter myWriter = new FileWriter("C:/Users/Poyatos Rémi/Desktop/projet/traitement-fichier-jpa-off/src/main/resources/files/recensement-copy.csv");


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
                    .replace("correcteur d'acidité: phosphate de sodium, arôme, vitamines d, pp, b5. b6. b2. b1. b9 et minéraux", "correcteur d'acidité: phosphate de sodium, arôme.")
                    .replace("émulsifiant, sel, arômes naturels. peut contenir du lait, des arachides et des fruits à coque.", "émulsifiant, sel, arômes naturels.")
                    .replace("cacao maigre en poudre, vitamines et minéraux, sel, arôme naturel", "cacao maigre en poudre, vitamines et minéraux, sel, arôme naturel.")
                    .replace("rocou e160b, arôme cannelle, antioxydant: extrait riche en tocophérols, vitamineset minéraux", "rocou e160b, arôme cannelle, antioxydant: extrait riche en tocophérols, vitamines et minéraux")
                    .replace("régulateur d'acidité: phosphate trisodique, arôme, vitamineset minéraux.", "régulateur d'acidité: phosphate trisodique, arôme, vitamines et minéraux.")
                    .replace(", vitaminesacide pantothénique, b6. riboflavine, thiamine, acide folique, b12, fer", "")
                    .replace("cacao maigre en poudre 50%, sucre, arôme", "cacao maigre en poudre 50%, sucre, arôme.")
                    .replace("flocons de quinoa", "flocons de quinoa.")
                    .replace("fourrage 50 %: chocolat au lait: sucre de canne, beurre de cacao, poudre de lait entier, masse de cacao, extrait de vanille, biscuit 50 %: farine de riz, farine de maïs, sucre de canne //  ingrédients issus de l’agriculture biologique", "fourrage 50 %, chocolat au lait, sucre de canne, beurre de cacao, poudre de lait entier, masse de cacao, extrait de vanille, biscuit 50 %, farine de riz, farine de maïs, sucre de canne.")
                    .replace("fourrage 50 %: chocolat noir: sucre de canne, masse de cacao, beurre de cacao, extrait de vanille, biscuit 50%: farine de riz, farine de maïs, sucre de canne. // ingrédients issus de l'agriculture biologique", "fourrage 50 %, chocolat noir, sucre de canne, masse de cacao, beurre de cacao, extrait de vanille, biscuit 50%, farine de riz, farine de maïs, sucre de canne.")
                    .replace("flocons de seigle 36% sucre de canne non raffiné, pralin de noisettes 12%, billettes de céréales, huile de tournesol, noisettes 6%, sirop de riz, farine de blé.", "flocons de seigle 36%, sucre de canne non raffiné, pralin de noisettes 12%, billettes de céréales, huile de tournesol, noisettes 6%, sirop de riz, farine de blé.")
                    .replace("flocons de céréales, graines de tournesol 7%, graines de courges 6%, pétales jus de pomme concentré de jus de pomme , sel, kasha, noisettes 3.5%, amandes sésame , graines de lin 2.2%.", "flocons de céréales, graines de tournesol 7%, graines de courges 6%, pétales jus de pomme, concentré de jus de pomme , sel, kasha, noisettes 3.5%, amandes sésame , graines de lin 2.2%.")
                    .replace(" , produit issu de l'agriculture biologique.", "")
                    .replace("sarrasin 99%, sel..", "sarrasin 99%, sel.")
                    .replace("céréales 63.8 %, sucre de canne non raffiné. chocolats 58 %. pépites de chocolats 21% pâte de cacao,", "céréales 63.8 %, sucre de canne non raffiné, chocolats 58 %. pépites de chocolats 21% pâte de cacao,")
                    .replace("flocons d'avoine 57.5%, sucre de canne non raffiné, huile de tournesol, billettes de céréales, sirop de riz, farine de blé, sirop de malt d'orge", "flocons d'avoine 57.5%, sucre de canne non raffiné, huile de tournesol, billettes de céréales, sirop de riz, farine de blé, sirop de malt d'orge.")
                    .replace("flocons d'avoine, chocolat en poudre 10%, farine de blé, sucre de canne non raffiné, billettes de céréales, sirop de riz, huile de tournesol, noix de coco 2%  ingrédients issus de l'agriculture biologique", "flocons d'avoine, chocolat en poudre 10%, farine de blé, sucre de canne non raffiné, billettes de céréales, sirop de riz, huile de tournesol, noix de coco 2% .")
                    .replace("epeautre 98%, sel", "epeautre 98%, sel.")
                    .replace("carbonates d'ammonium, arôme, sel, émulsifiant: lécithines de colza. traces éventuelles de lait, d'oeufs et de fruits à coque.", "carbonates d'ammonium, arôme, sel, émulsifiant: lécithines de colza.")
                    .replace("émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.  traces éventuelles de lait, arachides et fruits à coques", "émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.")
                    .replace("ananas sucrés, pommes séchées, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel", "ananas sucrés, pommes séchées, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel.")
                    .replace("carbonates de sodium , carbonates d'ammonium, arômes, émulsifiants: lécithines de colza ,e472e , sel. traces éventuelles d'oeufs et de fruits à coque.", "carbonates de sodium , carbonates d'ammonium, arômes, émulsifiants: lécithines de colza ,e472e , sel. traces éventuelles d'oeufs et de fruits à coque.")
                    .replace(", vitamines: niacine, acide pantothénique, b6 , riboflavine, thiamine, acide folique, biotine, b12. fer. traces éventuelles de soja, lait et fruits à coque", "")
                    .replace(", vitamines: niacine, acide pantothénique— b6 , riboflavine, thiamine, acide folique, biotine, b12. fer", "")
                    .replace("céréales 67% {flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, flocons de ble, farine de ble, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras}, fruits secs 21% raisins secs, bananes sucrées aromatisées, lamelles de noix de coco, papayes sucrées, pommes, ananas sucrés, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel. traces éventuelles de soja, fruits à coque et lait.", "céréales 67% ,flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, flocons de ble, farine de ble, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras, fruits secs 21% raisins secs, bananes sucrées aromatisées, lamelles de noix de coco, papayes sucrées, pommes, ananas sucrés, noix de coco en poudre, sucre, huile de tournesol, sirop de glucose, sel, arôme, caramel.")
                    .replace(" fabriqué à partir de 99% de maïs pour obtenir 100g de produit fini.", "")
                    .replace("stabilisant: sorbitols, dextrose, sucre, sel, arôme, émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.  traces éventuelles de lait, sulfites et arachides.", "stabilisant: sorbitols, dextrose, sucre, sel, arôme, émulsifiant: lécithine de soja, antioxydant: extrait riche en tocophérols.")
                    .replace("poudres à lever: diphosphates , carbonates d'ammonium , carbonates de sodium, arôme, émulsifiants: lécithines de colza , e172e, sel. traces éventuelles d'oeufs et de fruits à coque.", "poudres à lever: diphosphates , carbonates d'ammonium , carbonates de sodium, arôme, émulsifiants: lécithines de colza , e172e, sel.")
                    .replace("céréales 74% riz, ble complet24%, orge, copeaux de chocolat au lait 17%, sucre, farine complète de ble1.3%, sel, farine d'orge maltée, émulsifiant: mono, et diglycérides d'acides gras, arôme. traces éventuelles de fruits à coque", "céréales 74% riz, ble complet24%, orge, copeaux de chocolat au lait 17%, sucre, farine complète de ble 1.3%, sel, farine d'orge maltée, émulsifiant: mono, et diglycérides d'acides gras, arôme.")
                    .replace("céréales 50.5%, sucre, graines 12%, huile de tournesol, abricot sec 4%, mangue déshydratée 4%, morceaux de caramel 4%, miel, noix de coco, arôme naturel, antioxydant: extrait riche en tocophérols. traces éventuelles d'arachides et fruits à coque.", "céréales 50.5%, sucre, graines 12%, huile de tournesol, abricot sec 4%, mangue déshydratée 4%, morceaux de caramel 4%, miel, noix de coco, arôme naturel, antioxydant: extrait riche en tocophérols.")
                    .replace("céréales 68% {flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre}, sucre, huile de tournesol, carrés de chocolat noir 4%, carrés de chocolat au lait 4%, carrés de chocolat blanc 4%, cacao maigre en poudre, noix de coco en poudre, maltodextrine, sel, arôme. traces éventuelles de soja et fruits à coque.", "céréales 68% ,flocons d'avoine, pétales de blé complet et de riz 25% ble complet, riz, sucre, extrait de malt d'orge, sel, pétales de maïs maïs, sucre, extrait de malt d'orge, sel, émulsifiant: mono, et diglycérides d'acides gras, céréales extrudées farine de riz, farine de ble, sucre, farine de malt de ble, sel, sucre caramélisé en poudre, sucre, huile de tournesol, carrés de chocolat noir 4%, carrés de chocolat au lait 4%, carrés de chocolat blanc 4%, cacao maigre en poudre, noix de coco en poudre, maltodextrine, sel, arôme.")
                    .replace("céréales 66% flocons d'avoine complet 47%, flocons de ble complet, farine de ble, céréales extrudées, pétales de maïs, chocolat au lait 15%, sucre, huile de tournesol, sirop de glucose, noix de coco en poudre, sel, caramel, arôme. traces éventuelles de soja, fruits à coque.", "céréales 66% ,flocons d'avoine complet 47%, flocons de ble complet, farine de ble, céréales extrudées, pétales de maïs, chocolat au lait 15%, sucre, huile de tournesol, sirop de glucose, noix de coco en poudre, sel, caramel, arôme.")
                    .replace("céréales 57% flocons d'avoine complet, flocons de blé complet, farine de blé, céréales extrudées, fruits secs 24% raisins, bananes, abricots, noix de coco en lamelles, noix de coco en poudre, amandes en tranches, noisettes, sucre, huile de tournesol, miel, graines de courges 1%, sel. traces éventuelles d'arachide, soja, lait et autres fruits à coque.  ingrédient issu de l'agriculture biologique", "céréales 57%, flocons d'avoine complet, flocons de blé complet, farine de blé, céréales extrudées, fruits secs 24% raisins, bananes, abricots, noix de coco en lamelles, noix de coco en poudre, amandes en tranches, noisettes, sucre, huile de tournesol, miel, graines de courges 1%, sel.")
                    .replace("céréales semoule de maïs43%, farine de ble39%, sucre, miel 5%, sel, antiagglomérant: carbonate de calcium, arôme naturel, colorants: béta carotène et rocou, caramel, sirop de sucre caramélisé, vitamines: niacine, acide pantothénique, riboflavine, thiamine, b6 , b12 , acide folique, biotine, fer. traces éventuelles d'arachide, soja, lait, fruits à coque et graines de sésame.", "céréales semoule de maïs 43%, farine de ble 39%, sucre, miel 5%, sel, antiagglomérant: carbonate de calcium, arôme naturel, colorants: béta carotène et rocou, caramel, sirop de sucre caramélisé.")
                    .replace("sucre de canne roux, cacao maigre en poudre 21%, crèmes de céréales 10%, extrait de vanille bourbon, sel. , ingrédients issus de l'agriculture biologique", "sucre de canne roux, cacao maigre en poudre 21%, crèmes de céréales 10%, extrait de vanille bourbon, sel.")
                    .replace("flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %blé 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits à coque, graines de sésame et soja.  ", "flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %, blé 0.8 %, avoine 0.5 %, sel.")
                    .replace("ingrédients flocons d'avoine 38 %, sucre de canne, chocolat noir 11 %, flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %blé 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits à coque, graines de sésame et soja.", "flocons d'avoine 38 %, sucre de canne, chocolat noir 11 %, flocons de blé 9.8 %, riz 6.5 %, huile de tournesol, noix de coco râpée, farine de blé 3.8 %, sirop de riz, cacao maigre en poudre 2.3 %, chocolat au lait de couverture 1.0 %blé 0.8 %, avoine 0.5 %, sel. traces d'arachides, fruits à coque, graines de sésame et soja.")
                    .replace("ingrédients brioche au beurre farine de blé: aæufs entiers, eau, beurre 8.5%, sucre pure canne, sirop de sucre inverti, levurè, arômes naturels, sel, poudre de lait écrémé, gluten de blé, épaississant,farine de blé malté&quot", "brioche au beurre farine de blé: oeufs entiers, eau, beurre 8.5%, sucre pure canne, sirop de sucre inverti, levure, arômes naturels, sel, poudre de lait écrémé, gluten de blé, épaississant,farine de blé maltéquot")
                    .replace("ingrédients riz 44 % , blé complet 24 %,copeaux de chocolat noir 19 %, sucre , orge 6 % , farine complète de blé , sel , farine d'orge maltée , vitamines: c, niacine, b6. riboflavine, thiamine, acide folique, b12 , fer , émulsifiant: mono , et diglycérides d'acides gras.", "riz 44 % , blé complet 24 %,copeaux de chocolat noir 19 %, sucre , orge 6 % , farine complète de blé , sel , farine d'orge maltée , fer , émulsifiant: mono , et diglycérides d'acides gras.")
                    .replace("cereales 71% farine de ble complete flocons de cereales avoine ble orge seigle riz sucre de canne, beurre 16%, œufs entiers, poudre de lait ecreme poudres a lever carbonates d'ammonium et de sodium, acide citrique sel marin arôme naturel.", "cereales 71%,farine de ble complete,flocons de cereales,avoine,ble,orge,seigle,riz, sucre de canne, beurre 16%, œufs entiers, poudre de lait ecreme poudres a lever, carbonates d'ammonium et de sodium, acide citrique, sel marin, arôme naturel.")
                    .replace("farine de blé, pâte à glacer lait 30%, sucre, beurre concentré reconstitué 16%, œufs entiers frais, amidon de blé, poudre à lever, fleur de sel 0.35%, lait écrémé en poudre", "farine de blé, pâte à glacer lait 30%, sucre, beurre concentré reconstitué 16%, œufs entiers frais, amidon de blé, poudre à lever, fleur de sel 0.35%, lait écrémé en poudre.")
                    .replace("farine de blé, beurre pâtissier 11.9 %, sucre, œufs, poudre de lait, sel de guérande, poudre à lever (bicarbonate de sodium et bicarbonate d'ammonium, arômes.", "farine de blé, beurre pâtissier 11.9 %, sucre, œufs, poudre de lait, sel de guérande, poudre à lever, bicarbonate de sodium, bicarbonate d'ammonium, arômes.")
                    .replace(" poudres à lever: carbonates acides d’ammonium et de sodium , acide citrique, sel marin non raffiné, arôme  naturel de vanille, lait demi~écrémé", " poudres à lever: carbonates acides d’ammonium et de sodium , acide citrique, sel marin non raffiné, arôme  naturel de vanille, lait demi-écrémé.")
                    .replace("poudres à lever: carbonatesd'ammonium, carbonates de sodium, diphosphates , sel , lait écrémé en poudre , blanc d'œuf en poudre , cacao en poudre , jaune d'oeuf en poudre , arômes. traces de graines de sésame.", "poudres à lever: carbonates d'ammonium, carbonates de sodium, diphosphates , sel , lait écrémé en poudre , blanc d'œuf en poudre , cacao en poudre , jaune d'oeuf en poudre , arômes.")
                    .replace("ingredients: farine et amidon de blé 61.8%, sucre, pépites de, beurre concentré 11.5%, poudre de lait écrémé, poudres à lever: carbonates d'ammonium , carbonates de sodium , diphosphates, poudre de cacao, sel, extrait de malt d'orge, œufs, arômedont lait. traces éventuelles d’autres céréales contenant du gluten, arachides, soja et fruits à coque.", "ingredients: farine et amidon de blé 61.8%, sucre, pépites de, beurre concentré 11.5%, poudre de lait écrémé, poudres à lever: carbonates d'ammonium , carbonates de sodium , diphosphates, poudre de cacao, sel, extrait de malt d'orge, œufs, arôme dont lait.")
                    .replace("carbonates de sodium, diphosphates, lait demi,écrémé, œufs entiers en poudre, lait écrémé en poudre, cacao en poudre, arômes.", "carbonates de sodium, diphosphates, lait demi-écrémé, œufs entiers en poudre, lait écrémé en poudre, cacao en poudre, arômes.")
                    .replace("farine de blé, sucre de canne beurre 16%, lactosérum en poudre sel, poudres à lever, œufs entiers, jus de citron", "farine de blé, sucre de canne beurre 16%, lactosérum en poudre sel, poudres à lever, œufs entiers, jus de citron.")
                    .replace("petits pois 56 %, pomme de terre, lait demi,écrémé, crème fraîche, sel poivre", "petits pois 56 %, pomme de terre, lait demi,écrémé, crème fraîche, sel poivre.")
                    .replace("olives dénoyautées vertes, tournantes et noires 75%, lupins 17%, poivrons, sel, huile de tournesol, piments, épices et aromates. saumure, acidifiant, anti,oxygène, sulfites", "olives dénoyautées vertes, tournantes et noires 75%, lupins 17%, poivrons, sel, huile de tournesol, piments, épices et aromates. saumure, acidifiant, anti,oxygène, sulfites.")
                    .replace("olives noires , 92%, sel, huile de tournesol", "olives noires , 92%, sel, huile de tournesol.")
                    .replace("olives, eau et sel", "olives, eau et sel.")
                    .replace("eau, rameaux de salicorne, vinaigre, carottes, arômes naturels, oignons blancs, coriandre", "eau, rameaux de salicorne, vinaigre, carottes, arômes naturels, oignons blancs, coriandre.")
                    .replace("câpres, eau, vinaigre, sel", "câpres, eau, vinaigre, sel.")
                    .replace("olives, huile de colza, sel", "olives, huile de colza, sel.")
                    .replace("pâtes alimentaires précuites 42% eau, nouilles 16% {semoule de ble dur. poudre de blanc doeuf, huile de colza, sen, viande de bœuf traitée en salaison, blanchie et précuite 22% poitrine de bœuf 150/j, eau. fécule de pomme de terre. protéines de lait, arômes natureis, maltodextrjne de blé et/ou maïs, antioxydant ascorbate de sodium, sen. vin rouge 11%, eau, carottes en rondelle oignons grelots 3.3%, oignon émincé 2.3%, champignons de paris émincés 1.7%, arôme, acidifiant: acide cmque, lardons cuits fumés 1.3% poitine de porc, sel, dextose, arôme de fumée, acidifiant: acide citrique, antioxydant: érythorbate de sodium, conservateur: nitrite de sodium, amidon modifié de maïs, gé, extrait de malt, arômes, poudre de champignon. traces éventuelles de soja, céleri, poissons, crustacés, mollusques. graines de sésame et fruis à coque. exprimés sur la totalité de la recette.", "pâtes alimentaires précuites 42%, eau, nouilles 16% ,semoule de ble dur, poudre de blanc doeuf, huile de colza, sen, viande de bœuf traitée en salaison blanchie et précuite 22% ,poitrine de bœuf 150/j, eau, fécule de pomme de terre, protéines de lait, arômes natureis, maltodextrine de blé et/ou maïs, antioxydant ascorbate de sodium, sel, vin rouge 11%, eau, carottes en rondelle oignons grelots 3.3%, oignon émincé 2.3%, champignons de paris émincés 1.7%, arôme, acidifiant: acide cmque, lardons cuits fumés 1.3% poitine de porc, sel, dextose, arôme de fumée, acidifiant: acide citrique, antioxydant: érythorbate de sodium, conservateur: nitrite de sodium, amidon modifié de maïs, gé, extrait de malt, arômes, poudre de champignon.")
                    .replace("nems porc 4x70 g:eau, farine de riz, oignon réhydraté, viande de porcet gras de porc 8%, protéines de soja, carotte, huile de tournesol, pousse de haricot mungo, amidon de pois, sel, champignon noir, chou, amidon de mais, sauce soja, ail, sucre, poivre. pourcentage exprimé sur le nem. sauce au nuoc,mâm 30 g eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%, sauce soja. pourcentage exprimé sur la sauce. traces éventuelles de crustacés, ceuf, lait et arachide.", "nems porc 4x70 g,eau, farine de riz, oignon réhydraté, viande de porcet gras de porc 8%, protéines de soja, carotte, huile de tournesol, pousse de haricot mungo, amidon de pois, champignon noir, chou, amidon de mais, sauce soja, ail, sucre, poivre, sauce au nuoc,mâm 30 g ,eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%, sauce soja.")
                    .replace("jus cuisiné: eau, oignons, sel, arômes. lentilles précuites.", "eau, oignons, sel, arômes, lentilles précuites.")
                    .replace("vinaigrette à l'huile de noix 10.9%4.1%", "vinaigrette à l'huile de noix 10.9%")
                    .replace("noix de coco 2.1%, arôme naturel, antioxydant: extrait riche en tocophérols. traces éventuelles de lait, arachides et fruits à coque. produit fabriqué à partir d'ingrédients origine france et hors france.", "noix de coco 2.1%, arôme naturel, antioxydant: extrait riche en tocophérols.")
                    .replace("échalotes, sel, huile d'olive, basilic, dextrose, persil, ail, muscade, gélifiants: carraghénanes et farine de graine de caroube, arômes naturels", "échalotes, sel, huile d'olive, basilic, dextrose, persil, ail, muscade, gélifiants: carraghénanes et farine de graine de caroube, arômes naturels.")
                    .replace("macaronis cuits 45%, lait demi,écrémé 22%, jambon de porc supérieur cuit 16%, ferments, emmental râpé 11%, beurre 1.5%, huile de tournesol, amidon de maïs cireux, farine de blé 0.4%, gélatine de bœuf, sel, poivre, muscade", "macaronis cuits 45%, lait demi,écrémé 22%, jambon de porc supérieur cuit 16%, ferments, emmental râpé 11%, beurre 1.5%, huile de tournesol, amidon de maïs cireux, farine de blé 0.4%, gélatine de bœuf, sel, poivre, muscade.")
                    .replace("emmental 36%, eau, vin blanc, tomme 7%, crème, comté aop 3%, amidon modifié de mais, sel, correcteur d'acidité: phosphates de sodium, ail, boisson spiritueuse au kirsch, colorant: rocou", "emmental 36%, eau, vin blanc, tomme 7%, crème, comté aop 3%, amidon modifié de mais, sel, correcteur d'acidité: phosphates de sodium, ail, boisson spiritueuse au kirsch, colorant: rocou.")
                    .replace("pâtes alimentaires aux oeufs frais tagliatelles cuites 48%, préparation à base de surimi 20%, eau, amidons de pomme de terre et de blé, blanc d'oeuf réhydraté, huile de colza, poivrons rouges 8.5%, eau, crème fraîche, vinaigre d'alcool, jus de citron concentré, sel, jaune d'oeuf, moutarde de dijon, aneth 0.2%, amidon modifié de pomme de terre, poivre blanc, épaississant: gomme xanthane", "pâtes alimentaires aux oeufs frais tagliatelles cuites 48%, préparation à base de surimi 20%, eau, amidons de pomme de terre et de blé, blanc d'oeuf réhydraté, huile de colza, poivrons rouges 8.5%, eau, crème fraîche, vinaigre d'alcool, jus de citron concentré, sel, jaune d'oeuf, moutarde de dijon, aneth 0.2%, amidon modifié de pomme de terre, poivre blanc, épaississant: gomme xanthane.")
                    .replace("levure, sucre, farine de fèves, muscade, vinaigre d'alcool, farine d'orge maltée torréfiée, gluten de blé. pourcentages exprimés sur le nem au poulet et au curry.  nems aux legumes: galette de riz 31% garnie d'une farce aux légumes: eau, farine de riz 12%, oignon 10.5%, champignon noir 10.5%, protéines de soja, petit pois 6.5%, châtaigne d'eau 5%, huile de tournesol, blanc d'œuf, ciboule, sel, fécule de manioc, coriandre, caramel aromatique, piment. pourcentages exprimés sur le nem aux légumes. sauce nuoc,mâm: eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%,, sauce soja. pourcentage exprimé sur la sauce nuoc,mâm.  peut contenir des traces de crustacés et arachide.", "levure, sucre, farine de fèves, muscade, vinaigre d'alcool, farine d'orge maltée torréfiée, gluten de blé. pourcentages exprimés sur le nem au poulet et au curry.  nems aux legumes: galette de riz 31% garnie d'une farce aux légumes: eau, farine de riz 12%, oignon 10.5%, champignon noir 10.5%, protéines de soja, petit pois 6.5%, châtaigne d'eau 5%, huile de tournesol, blanc d'œuf, ciboule, sel, fécule de manioc, coriandre, caramel aromatique, piment. pourcentages exprimés sur le nem aux légumes. sauce nuoc,mâm: eau, sucre, vinaigre d'alcool, sel, nuoc,mâm 1.6%, sauce soja. ")
                    .replace("farine de blé, beurre, levure, sel, lait écrémé en poudre, extrait de malt d'orge, poudre d'acérola. pourcentages exprimés sur l'ensemble du produit.", "farine de blé, beurre, levure, sel, lait écrémé en poudre, extrait de malt d'orge, poudre d'acérola.")
                    .replace("thon listao 25%, lentilles vertes pré,trempées 19%, poivrons rouges 11.4%", "thon listao 25%, lentilles vertes pré-trempées 19%, poivrons rouges 11.4%")
                    .replace("peut contenir des traces de moutarde, gluten et oeuf", "")
                    .replace("piment de cayenne, poivre, sel, pavot bleu, épaississants: gomme xanthane et gomme guar", "piment de cayenne, poivre, sel, pavot bleu, épaississants: gomme xanthane et gomme guar.")
                    .replace("arôme naturel, sel, ciboulette, fibres d'agrumes, sucre", "arôme naturel, sel, ciboulette, fibres d'agrumes, sucre.")
                    .replace("peut contenir des traces de crustacés, mollusques, moutarde, œufs, poissons et fruits à coque", "")
                    .replace("gluten de blé. pourcentages exprimés sur le nem au porc.  nems au poulet et au curry", "gluten de blé, nems au poulet et au curry")
                    .replace("piment. pourcentages exprimés sur le nem aux légumes. sauce nuoc,mâm: eau, sucre, vinaigre d'alcool", "piment, sauce nuoc,mâm: eau, sucre, vinaigre d'alcool")
                    .replace("nuoc,mâm 1.6%,, sauce soja. pourcentage exprimé sur la sauce nuoc,mâm.  peut contenir des traces de crustacés et arachide.", "nuoc,mâm 1.6%, sauce soja.")
                    .replace("origan déshydraté, purée d'ail, huile d'olive vierge. peut contenir des traces de crustacés, mollusques,  moutarde, œufs, poissons et fruits à coque.  e.", "origan déshydraté, purée d'ail, huile d'olive vierge.")
                    .replace(" peut contenir des traces de crustacés, mollusques, moutarde, œufs, poissons et fruits à coque.", "")
                    .replace(" peut contenir des traces de crustacés, mollusques, moutarde, œufs, poissons et fruits à coque. mise en œuvre d'ingrédients décongelés, ne pas congeler.", "")
                    .replace(" pourcentages exprimés sur l'ensemble du produit.", "")
                    .replace(" . mise en œuvre d'ingrédients décongelés, ne pas congeler.", "")
                    .replace("sucre, vinaigre d'alcool, sel. pourcentage exprimé sur la sauce. peut contenir des traces de blé et lait.", "sucre, vinaigre d'alcool, sel.")
                    .replace("biologique. peut contenir des traces de soja, céleri et fruits à coque.", "")
                    .replace("conservateurs: érythorbate de sodium et nitrite de sodium. emmental 10.7%.", "conservateurs: érythorbate de sodium et nitrite de sodium, emmental 10.7%.")
                    .replace("pâte à pizza sauce tomategarniture", "pâte à pizza,sauce tomate,garniture")
                    
                    
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
