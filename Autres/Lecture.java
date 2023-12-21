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
import univers.Personnages.Mortels.Heros;

public class Lecture {
    protected static List<Boolean> isInner = new ArrayList<>();
    protected static List<Boolean> isDeci = new ArrayList<>();
    
    public static List<String> lireFichier(String lien) {
        List<String> lignes = new ArrayList<>();
        try (BufferedReader fichier = new BufferedReader(new FileReader(new File(lien)))){
            String line;
            
            while((line =fichier.readLine()) != null) {
                line = line.trim().replace("\\n","\n");
                if (line.equals(""))  
                    continue;  
                //System.out.println(line);
                lignes.add(line);
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return lignes;

    }
    /* 
    public static List<String> extractNodeText(String lien){
        List<String> lignes = lireFichier(lien);
        for (int i = 0; i<lignes.size();i++){
            lignes.set(i,extracInfo(lignes.get(i))); 
        }
        return lignes;
    }

    public static List<List<Boolean>> extractNodeType(String lien){
        List<String> lignes = lireFichier(lien);
        List<Boolean> isInner = new ArrayList<>();// [0] : isInner, [1] : isDeci, 
        List<Boolean> isDeci = new ArrayList<>();
        String ligne;
        for (int i = 0; i<lignes.size();i++){
            ligne = lignes.get(i).trim();
            //System.out.println("ligne " + i + " : " + ligne);
            isInner.add(false);
            isDeci.add(false);
            if (ligne.startsWith("I")){
                isInner.set(i,true);
            }else if(ligne.startsWith("D")) {
                isDeci.set(i,true);
            }
        }
        List<List<Boolean>> nodesType = new ArrayList<>();
        nodesType.add(isInner);
        nodesType.add(isDeci);
        return nodesType;
    }

    public static List<Node> CreateNodeList(String lien){
        List<String> nodesText = extractNodeText(lien);
        List<List<Boolean>> nodesType = extractNodeType(lien);
        List<Boolean> isInn = nodesType.get(0);
        List<Boolean> isDeci = nodesType.get(1);
        List<Node> nodeList = new ArrayList<>();
        int n = nodesText.size();
        for(int i = 0; i<n; i++){
            if(isInn.get(i))
                nodeList.add(new InnerNode(nodesText.get(i)));
            else if(isDeci.get(i))
                nodeList.add(new DecisionNode(nodesText.get(i)));
        }
        return nodeList;
    }
    */


    public static String extracInfo(String line) {
        int index = line.indexOf(":");
        if (index != -1) {
            return line.substring(index+1).trim();

        }
        return "";
    }

    public static String extracBefore(String line) {
        int index = line.indexOf(":");
        if (index != -1) {
            return line.substring(0,index).trim();
        }
        return "";
    }

    public static Map<String,Node> CreateNodeMap(String lien){
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

    public static Map<String,List<String>> CreateNodeNextMap(String lien){
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

    public static <K, V> Map<K, V> createMap(List<K> keys, List<V> values) {
        try{
            if (keys.size() != values.size()) {
                throw new IllegalArgumentException();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("ERREUR:");
            System.out.println("nbkeys:" + keys.size() + " keys :" + keys);
            System.out.println("nbvalues : " + values.size());
            System.out.println("Problème de taille");
        }
        /* 
        System.out.println("nbkeys:" + keys.size() );
        System.out.println("nbvalues : " + values.size());
        System.out.println(" keys :" + keys);
        */
        
        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    public static <K> List<K> tabToArray(K[] tab){
        List<K>array = new ArrayList<>();
        for (K elem : tab){
            array.add(elem);
        }
        return array;
    }

    public static void AfficheNodeMap( Map<String,Node> nodeMap , Map<String,List<String>> NodeNextMap){
        for (String key : nodeMap.keySet() ){
            System.out.print("key :" + key +"\nvalue : " +nodeMap.get(key).getDescription() + "\nnextnodes :" + NodeNextMap.get(key) + "\n\n");
        }
    }

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

    public static void addHerosNodeMap(Map<String,Node> nodeMap,Heros heros){
        for (String key : nodeMap.keySet()){
            nodeMap.get(key).addPerso(heros);
        }
    }
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
    