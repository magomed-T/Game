package Autres;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;

import representation.*;
import univers.Personnages.Heros;

/**
 * La classe Lecture fournit des méthodes utilitaires pour la lecture de fichiers,
 * l'extraction d'informations, la création de structures de données.
 *
 *
 * @author Willy VO Magomed Tsitsiev
 * @version 1.0
 */

public class Lecture {
    /** Liste pour stocker si un noeud est InnerNode */
    protected static List<Boolean> isInner = new ArrayList<>();
     /**Liste pour stocker si un noeud est une décision.*/
    protected static List<Boolean> isDeci = new ArrayList<>();
    
    /**
     * Lit le contenu d'un fichier et renvoie une liste de lignes.
     *
     * @param lien Le chemin du fichier à lire.
     * @return Une liste de lignes lues depuis le fichier.
     * @throws Exception si il y a un problème dans lecture dans lireFichier
     */
    public static List<String> lireFichier(String lien) throws Exception{
        List<String> lignes = new ArrayList<>();
        BufferedReader fichier = new BufferedReader(new FileReader(new File(lien)));
        String line;
        
        while((line =fichier.readLine()) != null) {
            line = line.trim().replace("\\n","\n");
            if (line.equals(""))  
                continue;  
            //System.out.println(line);
            lignes.add(line);
            
        }
        return lignes;
    }

    /**
     * Extrait les informations d'une ligne après le symbole ':'.
     *
     * @param line La ligne à traiter.
     * @return Les informations extraites après le symbole ':'.
     */
    public static String extracInfo(String line) {
        int index = line.indexOf(":");
        if (index != -1) {
            return line.substring(index+1).trim();

        }
        return "";
    }

     /**
     * Extrait les informations d'une ligne avant le symbole ':'.
     *
     * @param line La ligne à traiter.
     * @return Les informations extraites avant le symbole ':'.
     */
    public static String extracBefore(String line) {
        int index = line.indexOf(":");
        if (index != -1) {
            return line.substring(0,index).trim();
        }
        return "";
    }

    /**
     * Crée une map de noeuds à partir d'un fichier spécifié.
     *
     * @param lien Le chemin du fichier à lire.
     * @return Une map contenant les noeuds créés avec les titres comme clés.
     * @throws Exception si il y a un problème dans lecture dans lireFichier
     */

    public static Map<String,Node> CreateNodeMap(String lien) throws Exception{
        List<String> lignes = lireFichier(lien);
        List<String> keys = new ArrayList<>();
        List<Node> allNodes = new ArrayList<>();
        String nodeText;
        String nodeTitle;
        for (String ligne : lignes){
            nodeTitle = extracBefore(ligne);
            nodeText = extracInfo(ligne); 
            keys.add(nodeTitle);
            if (nodeTitle.startsWith("I"))
                allNodes.add(new InnerNode(nodeText));
            else if(nodeTitle.startsWith("D"))
                allNodes.add(new DecisionNode(nodeText));
            else if(nodeTitle.startsWith("T"))
                allNodes.add(new TerminalNode(nodeText));
            else if(nodeTitle.startsWith("C"))
                allNodes.add(new ChanceNode(nodeText));

        }
        Map<String,Node> nodeMap = createMap(keys, allNodes);
        return nodeMap;
    }
    /**
     * Crée une map des relations entre les noeuds à partir d'un fichier spécifié.
     *
     * @param lien Le chemin du fichier à lire.
     * @return Une map contenant les relations entre les noeuds avec les titres comme clés.
     * @throws Exception si il y a un problème dans lecture dans lireFichier
     * */
    public static Map<String,List<String>> CreateNodeNextMap(String lien) throws Exception{
        List<String> lignes = lireFichier(lien);
        List<String> keys = new ArrayList<>();
        List<List<String>> values = new ArrayList<>();
        String nodeTitle;
        String nodeInfo;
        for( String ligne : lignes){
            nodeTitle = extracBefore(ligne);
            nodeInfo = extracInfo(ligne);
            keys.add(nodeTitle);
            List<String> ls = new ArrayList<>(); 
            StringTokenizer tok = new StringTokenizer(nodeInfo,",");
            int n = tok.countTokens();
            for (int i = 0 ; i<n; i++){
                ls.add(tok.nextToken());
            }
            values.add(ls);
        }
        Map<String,List<String>> nodeNextMap = createMap(keys, values);
        return nodeNextMap;
    }

    /**
     * Crée une map à partir de deux listes spécifiées.
     *
     * @param keys   Liste des clés.
     * @param values Liste des valeurs.
     * @param <K>    Type générique pour les clés.
     * @param <V>    Type générique pour les valeurs.
     * @return Une map créée à partir des listes spécifiées.
     */

    public static <K, V> Map<K, V> createMap(List<K> keys, List<V> values) {
        try{
            if (keys.size() != values.size()) {
                throw new IllegalArgumentException();
            }
        }
        catch(Exception e){
            System.out.println("ERREUR: Problème dans la création de Map ( Taille de listes différentes)");
        }
        
        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
    
    /**
     * Convertit un tableau en liste.
     *
     * @param tab Le tableau à convertir.
     * @param <K> Type générique pour les éléments du tableau.
     * @return Une liste contenant les éléments du tableau.
     */
    public static <K> List<K> tabToArray(K[] tab){
        List<K>array = new ArrayList<>();
        for (K elem : tab){
            array.add(elem);
        }
        return array;
    }

    /**
     * Affiche les informations contenues dans les maps de noeuds et de transitions.
     *
     * @param nodeMap     Map des noeuds.
     * @param NodeNextMap Map des transitions entre les noeuds.
     */
    public static void AfficheNodeMap( Map<String,Node> nodeMap , Map<String,List<String>> NodeNextMap){
        for (String key : nodeMap.keySet() ){
            System.out.print("key :" + key +"\nvalue : " +nodeMap.get(key).getDescription() + "\nnextnodes :" + NodeNextMap.get(key) + "\n\n");
        }
    }

    /**
     * Lie les noeuds en fonction des informations de transition spécifiées.
     *
     * @param nodeMap     Map des noeuds.
     * @param nodeNextMap Map des transitions entre les noeuds.
     */
    public static void LinkNodeMap(Map<String,Node> nodeMap,Map<String,List<String>> nodeNextMap){
        int i;
        for(String key : nodeNextMap.keySet()){
            i = 1;
            List<String> nodesTitle = nodeNextMap.get(key);
            if (nodesTitle.get(0).equals("null")){
                continue;
            }
            //System.out.println("key:" + key + " nbrNodes : " + nodesTitle);
            for( String nodeTitle : nodesTitle){
                Node node = nodeMap.get(nodeTitle);
                NodeF.Link((InnerNode) nodeMap.get(key),i, node);
                i++;
            }
            
        }
    }

    /**
     * Ajoute un personnage héroïque à tous les noeuds de la map.
     *
     * @param nodeMap Map des noeuds.
     * @param heros   Objet Heros à ajouter.
     */
    public static void addHerosNodeMap(Map<String,Node> nodeMap,Heros heros){
        for (String key : nodeMap.keySet()){
            nodeMap.get(key).addPerso(heros);
        }
    }

    /**
     * Obtient une réponse entière de l'utilisateur pour une décision spécifiée.
     *
     * @param nbOfDecision Nombre de décisions possibles.
     * @return La réponse entière de l'utilisateur.
     */
    public static int reponseInt(int nbOfDecision){
        Scanner sc = new Scanner(System.in);
        int reponse;

        do{
            System.out.print("\nDECISION : ");
            try{
            reponse = sc.nextInt();
            //System.out.println("nbOfDecision : " +nbOfDecision);
            if (reponse > nbOfDecision || reponse<1)
                throw new IllegalArgumentException();
            break;
            }
            catch(Exception e){
                System.out.println("\nCARACTERE INVALIDE:\nEntrez un entier parmi les reponses proposés...\n");
                sc.nextLine();
            }  
        }while(true);
        return reponse;
    }
}
    