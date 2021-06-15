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
                    .replace("arôme naturel de vanille.","arôme naturel de vanille,")
                    .replace("crème, poudre de babeurre, gélifiants: e407 et e415. sel.","crème, poudre de babeurre, gélifiants: e407 et e415, sel")
                    .replace("noix de coco râpée 1%, arômes naturels. jus concentré de citron,","noix de coco râpée 1%, arômes naturels: jus concentré de citron,")
                    .replace("citron: jus de soja 78 %,","jus de soja 78 %,")
                    .replace("ferments sélectionnés. conforme à la norme nf v 29,001. graines de soja filière française garanties sans ogm.","ferments sélectionnés. conforme à la norme nf v 29.001, graines de soja filière française garanties sans ogm.")
                    .replace("jus de soja 85%, sucre, épaississants: amidon transformé de maïs sans ogm , , carraghénanes pectines , gomme guar, phosphate de calcium, concentrés végétaux, arôme naturel, sel. soja filière française, garanti sans ogm.","jus de soja 85%. soja filière française, garanti sans ogm, sucre, épaississants: amidon transformé de maïs sans ogm, carraghénanes pectines , gomme guar, phosphate de calcium, concentrés végétaux, arôme naturel, sel")
                    .replace("colorant: extrait de paprika, vitamine d.","colorant: extrait de paprika")//?
                    .replace("jus de soja1 86%, sucre de canne 9.5%, amidon, arômes naturels de vanille, concentrés de carotte et de pomme, épaississant: carraghénanes, sel.  issu de l’agriculture biologique 1soja sans utilisation d’ogm conformément à la réglementation en vigueur sur le mode de production biologique.","jus de soja1 86% . issu de l’agriculture biologique, sucre de canne 9.5%, amidon, arômes naturels de vanille, concentrés de carotte et de pomme, épaississant: carraghénanes, sel")
                    .replace("tonyu 74.7 %, sucre 11.6 %, framboise 10 %, amidon modifié, phosphate de calcium, jus concentré de carotte pourpre, arôme naturel, sel, ferments, vitamine d.","tonyu 74.7 %, sucre 11.6 %, framboise 10 %, amidon modifié, phosphate de calcium, jus concentré de carotte pourpre, arôme naturel, sel, ferments.")//?
                    .replace("sel, ferments, vitamine d2.","sel, ferments")//?
                    .replace("jus de soja1 77%. issu de l’agriculture biologique, sucre de canne, poudre de cacao maigre, chocolat 1.5%, chocolat en poudre 1.5%, amidon, épaississant: carraghénanes, sel, arôme naturel.  issu de l’agriculture biologique 1soja sans utilisation d’ogm conformément à la réglementation en vigueur sur le mode de production biologique.","jus de soja1 77%, sucre de canne, poudre de cacao maigre, chocolat 1.5%, chocolat en poudre 1.5%, amidon, épaississant: carraghénanes, sel, arôme naturel ")
                    .replace(" café lyophilisé 0.2 %, sel, vitamine d2."," café lyophilisé 0.2 %, sel")//?
                    .replace("jus de soja  79%, sucre de canne 9.4%, purée d’abricot 5%, amidon, jus concentré de carotte, épaississant: pectines, arôme naturel, sel, ferments.  issu de l’agriculture biologique soja sans utilisation d’ogm conformément à la réglementation en vigueur sur le mode de production biologique.","jus de soja  79%. issu de l’agriculture biologique, sucre de canne 9.4%, purée d’abricot 5%, amidon, jus concentré de carotte, épaississant: pectines, arôme naturel, sel, ferments. ")
                    .replace("jus de soja 80%, sucre de canne 9.9%, citron 3.9%, amidon, sel, épaississant: pectines, arôme naturel, ferments.  issu de l'agriculture biologique / soja sans utilisation d'ogm conformément à la réglementation en vigueur sur le mode de production biologique. traces éventuelles de fruits à coque.","jus de soja 80%. issu de l'agriculture biologique, sucre de canne 9.9%, citron 3.9%, amidon, sel, épaississant: pectines, arôme naturel, ferments")
                    .replace("morceaux d’abricot: jus de soja 75.1%, sucre 11%, abricot 10%, amidon, amidon modifié, phosphate de calcium, épaississants: pectine – gomme guar, sel, arôme naturel, ferments, colorant: extrait de paprika, vitamine d2. morceaux de pêche: jus de soja 75.1%, sucre 11.9%, pêche 10%, amidon, amidon modifié, phosphate de calcium, arôme naturel, concentrés de carotte, épaississants: pectine – gomme guar, sel, ferments, vitamine d2. morceaux d’ananas: jus de soja 71.5%, sucre 12.7%, ananas 10%, amidon, phosphate de calcium, amidon modifié, arôme naturel, épaississants: pectine – gomme guar, sel, ferments, concentré de carthame, vitamine d2.","morceaux d’abricot: jus de soja 75.1%, sucre 11%, abricot 10%, amidon, amidon modifié, phosphate de calcium, épaississants: pectine – gomme guar, sel, arôme naturel, ferments, colorant: extrait de paprika, vitamine d2. morceaux de pêche: jus de soja 75.1%, sucre 11.9%, pêche 10%, amidon, amidon modifié, phosphate de calcium, arôme naturel, concentrés de carotte, épaississants: pectine – gomme guar, sel, ferments, morceaux d’ananas: jus de soja 71.5%, sucre 12.7%, ananas 10%, amidon, phosphate de calcium, amidon modifié, arôme naturel, épaississants: pectine – gomme guar, sel, ferments, concentré de carthame")
                    .replace(" amidon de maïs. amidon de pomme de terre"," amidon de maïs, amidon de pomme de terre")
                    .replace("vitamine d. traces éventuelles de fruits à coque.","traces éventuelles de fruits à coque")
                    .replace("amidon de maïs et amidon transformé de tapioca,","amidon de maïs, amidon transformé de tapioca,")
                    .replace("fraise: ","")
                    .replace("framboise: ","")
                    .replace("concentré de carotte et de cassis","concentré de carotte, concentré de cassis")
                    .replace("pêches: ","")
                    .replace("les ingrédients de ces produits ne proviennent pas de france.","")
                    .replace(" acide ascorbique. non"," acide ascorbique")
                    .replace(". contient plus de 85% d'humidité.",", contient plus de 85% d'humidité.")
                    .replace("pomme: ","")
                    .replace("poire: ","")
                    .replace("banane: ","")
                    .replace("abricots:","")
                    .replace(".  ingrédients issus de l'agriculture biologique","")
                    .replace("protéines de lait.traces éventuelles de soja.dessert","protéines de lait. traces éventuelles de soja, dessert")
                    .replace("oui= traces éventuelles de soja pour la référence chocolat","")
                    .replace(". les ingrédients de ces produits ne proviennent pas de france.","")
                    .replace(". ingrédients issus de i'agriculture biologique.","")
                    .replace("pomme pêche abricot + acérola:","")
                    .replace("colorant: caroténoïdes • ferments lactiques.","colorant: caroténoïdes, ferments lactiques,")
                    .replace("vitamine d. peut contenir des traces d'autres céréales contenant du gluten.","")
                    .replace("agar agar ingredients agricoles issus de l'agriculture biologique","")
                    .replace(", et",", ")
                    .replace("bio = issu de l'agriculture biologique","")
                    .replace("lait entier biochocolat en poudre bio 10.9 % sucre de canne bio crème biopoudre de lait écrémé bio amidon bio","lait entier, biochocolat en poudre bio 10.9 %, sucre de canne bio, crème biopoudre de lait écrémé bio, amidon bio")
                    .replace("épaississant e407. gaz propulseur e942.","épaississant e407, gaz propulseur e942.")
                    .replace("lactose et minéraux du lait","lactose, minéraux du lait")
                    .replace("épaississant: e407. protéines de lait","épaississant: e407, protéines de lait")
                    .replace(", vitamine d.","")
                    .replace(", ingrédients caramel:","")
                    .replace("ingrédients chocolat:","")
                    .replace("lait entier 79.7% 6—  sucre blond de canne , chocolat en poudre 5 %,  , amidon de manioc — épaississant: pectine.  ingrédients d'origine agricole issus de l'agriculture biologique","lait entier 79.7%,  sucre blond de canne , chocolat en poudre 5 %, amidon de manioc, épaississant: pectine.")
                    .replace("sel.","sel")
                    .replace("vitamine d2.","")
                    .replace(". traces éventuelles de soja","")
                    .replace("ingredients c tea desemouamidon transformé.épaississant: gomme xanthane semolina cake te de cacao, su ","thé, amidon, épaississant: gomme xanthane, gateau de cacao, sucre")
                    .replace("(p",", p")
                    .replace("émulsifiants: e472b, e471. stabilisants: e407. e412.","émulsifiants: e472b. e471, stabilisants: e407. e412,")
                    .replace("colorants: e100. e120. e131. ","colorants: e100. e120. e131,")
                    .replace("acésulfame,k.","acésulfame k.")
                    .replace("citron et citron vert","citron , citron vert")
                    .replace("saint,pierre","saint pierre")
                    .replace("édulcorants: sucralose et acésulfame,k.","édulcorants: sucralose , acésulfame k.")
                    .replace("édulcorants: sucralose et acésulfame k.","édulcorants: sucralose , acésulfame k.")
                    .replace("acidifiant: e330.  conservateurs: e202. e242.","acidifiant: e330 ,  conservateurs: e202. e242.")
                    .replace("saint,georges 1.","saint georges")
                    .replace("chlorurçs silice: 7.5 mg/l résidu sec à 1800c: 32mgli , ph: 618 résistivité: 30 000 q.cm , rh2 25 95  • i sur la boulell\\e.","chlorurçs silice: 7.5 mg/l, résidu sec à 1800c: 32mgli , ph: 618 résistivité: 30 000 q.cm , rh2 25 95 ")
                    .replace(". origine: pradesfrance.",", origine: pradesfrance.")
                    .replace(". non","")
                    .replace("co2. ","co2, ")
                    .replace("certains ingrédient de ce produit ne proviennent pas de france.","")
                    .replace("eau minérale naturelle naturellement gazeuse","eau minérale naturelle, naturellement gazeuse")
                    .replace(". colorant:",", colorant:")
                    .replace("présure.","présure")
                    .replace("arôme naturel d'épices et autres arômes naturels.","arôme naturel d'épices , autres arômes naturels.")
                    .replace(".  biologique. peut contenir des traces de lait, de moutarde et de céleri.",",  biologique.")
                    .replace("n oivron de tournesol pts paprika%0bo coriandrild,ecéleri oivr , t éventuellesdeblé,loit, outdt inforut10nsiàconsommer qregiç cons é iii il i i iii iii 3 250391 967827","poivron de tournesol , paprika , coriandre, céleri , poivre , traces éventuels de blé , lait")
                    .replace("ingrédient issu de l'agriculture biologique.","")
                    .replace(" les informations en gras sont destinées aux personnes intolérantes ou allergiques.","")
                    .replace("  traces éventuelles de graines de sésame.","")
                    .replace("traces éventuelles de céréales contenant du gluten et de céleri.","")
                    .replace(".ingrédient issu de l agriculture biologique.","")
                    .replace("penicilium roqueforti et chlorure de calcium.","penicilium roqueforti , chlorure de calcium.")
                    .replace("sels de fonte: e452. e450.","sels de fonte: e452. e450,")
                    .replace("ingrédients:iait (98%}, sel , ferments lactiques,  coagulant","lait 98%, sel , ferments lactiques,  coagulant")
                    .replace("vitamines: a, b2. b12","")
                    .replace("ingrédient issu de l'agriculture biologiq, ue.","")
                    .replace(". confiture artisanale.",", confiture artisanale.")
                    .replace("asteurized milk, pareurzec cream, pepper 5%, sait, best before: see original label refrigerated between +2'<, , 71,c , ?eüed in protective atmosphere.to ee within 3 days after opening. ingrediënten: gepasteuriseerde melk gepasteuriseerde room, peoc:r melkkiemen, strernsel. , vekpa'gt cn beschermende atmofeer. dagen na het. zutaten: pasteurisierter frischt<éië 'milch pfeffer 596. salz. mindenstens 72: fett i. tr. unter verpacvt. geniessen sie von 3 tagen nach dem otinen.","lait, poivron ,creme ")
                    .replace("ingrédients des épices et aromates ::",",")
                    .replace("e160b, e120.","e160b. e120,")
                    .replace("colorants: e160b, e120","colorants: e160b. e120")
                    .replace("conseils de conservation: avant ouverture: craint la chaleur et l'humidité.","")
                    .replace("peut contenir des traces de gluten, arachides, soja, lait, autres fruits à coque et céleri.","")
                    .replace("traces éventuelles d'arachide, de fruits a coque, de graines de sésame, de céréales contenant du gluten, d'anhydride sulfureux et de soja.","")
                    .replace("traces éventuelles fabriqué dans un atelier utilisant du gluten, des arachides, du soja, du lait et d'autres fruits à coque.","")
                    .replace("traces éventuelles de céréales contenant du gluten, autres fruits à coque, arachides et soja.","")
                    .replace("origine bolivie","")
                    .replace("émulsifiants:","")
                    .replace("et morceaux de cookies au chocolat 4 %.",", morceaux de cookies au chocolat 4 %.")
                    .replace("émulsifiant:","")
                    .replace("enrobage ananas:","")
                    .replace("poudres à lever: e450. e500.","poudres à lever: e450. e500,")
                    .replace("de soja et de graines de sésame","de soja , de graines de sésame")
                    .replace(", (noix ",", noix ")
                    .replace("(lait",",lait")
                    .replace("cannelle oeuf entier","cannelle, oeuf, entier,")
                    .replace("beurre. caramel","beurre, caramel")
                    .replace("mono,e","mono")
                    .replace(", blanc d'œuf.",", blanc d'œuf,")
                    .replace("a consomme e pré ance avantlu dele ir liquée sur le côté du paquet. co orve dans u eg frais ei sec, à abri de lumière.","")
                    .replace("issu d'animaux sans traitement antibiotique dès la fin du sevrage et nourris sans ogm","")
                    .replace("frais issu d'animaux nourris sans ogm","")
                    .replace(". antioxydant: e316.",", antioxydant: e316,")
                    .replace("antioxydant: e316.","antioxydant: e316,")
                    .replace("stabilisants: e450,e451,e452.","stabilisants: e450. e451. e452,")
                    .replace("exhausteur de goût: e621. acidifiant: e262.","exhausteur de goût: e621, acidifiant: e262.")
                    .replace("biologique.","")
                    .replace(" l'action des ferments génère l'apparition de nitrites d'origine végétale pour préserver toutes les qualités du produit. ce mode d'élevage garantit le bien,être de l'animal durant toute sa croissance et une alimentation variée à base de céréales.","")
                    .replace("l'action des ferments génère l'apparition de nitrites d'origine végétale pour préserver toutes les qualités du produit.","")
                    .replace("la matière première jambon ainsi que les épices et les plantes aromatiques ont été obtenues selon le mode de production de l'agriculture biologique.","")
                    .replace("antioxydant: e316.","antioxydant: e316,")
                    .replace("antioxydant: e316. conservateur: e250","antioxydant: e316 , conservateur: e250")
                    .replace("conservateur: e250. antioxydant: e316. stabilisant: e451.","conservateur: e250, antioxydant: e316, stabilisant: e451,")
                    .replace("peut contenir des traces de lait et de moutarde.","")
                    .replace("séché et affiné dans le bassin de l'adour","")
                    .replace("ascorbate de sodium. chorizo:","ascorbate de sodium,")
                    .replace("conservateur: e252. préparations aromatisantes.","conservateur: e252,")
                    .replace(" vitamine c","")
                    .replace(" vitamines: c, b9.","")
                    .replace("vitamine c, vitamine b9","")
                    .replace("vitamine: c, b9","")
                    .replace(" les oranges de ce produit ne proviennent pas de france.","")
                    .replace(". biologique.",", biologique.")
                    .replace(". } ingrédients issus de l'agriculture biologique","")
                    .replace("ce jus est sans additif, et sans conservateur, conformément à la réglementation.","")
                    .replace("abricot 20.4% et kiwi 4.8%.","abricot 20.4% , kiwi 4.8%.")
                    .replace("ingrédient issu de l'agriculture biologique","")
                    .replace("vitamines c, e, b1. b9 et provitamine a.","")
                    .replace(" une cuillère de purée de pomme pour la texture et une pointe de gingembre.","  purée de pomme  , pointe de gingembre.")
                    .replace("poudres à lever:","")
                    .replace("issu d'animaux nourris sans ogm < 0.9 %","")
                    .replace("huile vierge de twrnesot' 35 huile d'olive vierge extra' 5 hui!e nierge cie tournesol citron 0.35 oléiquei arôme rot ingrédients issus de itogç biologique.","huile vierge de tournesot, 35 huile d'olive vierge extra 5 huile, citron 0.35")
                    .replace("vitamines: b1. b2. b3. b5. b6. b8. b9. c, e, pro,vitamine a.","")
                    .replace(", gluten de blé ·",", gluten de blé ,")
                    .replace("vinaigre de cidre ·","vinaigre de cidre ,")
                    .replace("farine d'orge• ","farine d'orge, ")
                    .replace("émulsifiant e471.","émulsifiant e471,")
                    .replace("teneurs pour / durchschnittliche nàhrterte pro / gemiddeide v oedingswaarden per i av erage nutritional value per 100g de pain cuit: energie / energetyaarde / energy 1073kj ,","farine complète de blé, son d'avoine")
                    .replace("farine de blé type 65. eau, farine d’épeautre type 110. levain,","farine de blé type 65, eau, farine d’épeautre type 110, levain,")
                    .replace("blé/","blé")
                    .replace("farine de blé 54 %/, eau, huile de colza. levure, sucre de canne roux, graines de tournesol décortiquées 3.3 %. ","farine de blé 54 %, eau, huile de colza. levure, sucre de canne roux, graines de tournesol décortiquées 3.3 %, ")
                    .replace("farine de blé t65.","farine de blé t65,")
                    .replace(" conservateur: e282."," conservateur: e282,")
                    .replace("ingrédients/ingredientes/ingredient","")
                    .replace(", et diglycérides d'acides gras traces éventuelles de lait et de fruits à coque.",",  diglycérides d'acides gras traces éventuelles de lait , de fruits à coque.")
                    .replace(".  ingrédients issus de l'agriculture biologique.","")


                    /*=============Remi 6_701-10_050========================================*/


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
