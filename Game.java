import representation.*;
import Autres.*;
import univers.*;
import univers.Armes.*;
import univers.Objets.Objet;
import univers.Personnages.*;
import java.util.Scanner;
import java.util.List;
import java.util.Map;

/**
 * La classe Game représente le déroulement d'un jeu dans un univers fictif.
 * Elle inclut des personnages, des armes, des objets, des décisions et des scénarios.
 *
 * @see Heros
 * @see Divinite
 * @see Monstre
 * @see Epee
 * @see Lance
 * @see Arc
 * @see Objet
 * @see Node
 * @see DecisionNode
 * @see ChanceNode
 * @see InnerNode
 */

public class Game {

    /**
     * Constructeur par défaut de la classe Game.
     */
    public Game(){
    }
    /**
     * Fonction principale du jeu.
     * Elle initie et déroule le scénario du jeu en interagissant avec les différents nœuds.
     */
    public void play(){
    // Initialisation des données du jeu (personnages, armes, objets, etc.)
    // ------------------ PERSONNAGES -----------------------------
    Divinite[] Dieux = {
        new Divinite("Hermes",2,Element.TERRE),
        //new Divinite("Poseidon",6,Element.EAU),
        //new Divinite("Ares",6,Element.FEU),
    };
    //DemiDieu[] DemiDieux = {new DemiDieu("Hercule"),new DemiDieu("Achille"),new DemiDieu("Thesee"),new DemiDieu("Ulysse")};
    //Civil[] Civils = {new Civil("Persee"),new Civil("Dedale"),new Civil("Icare"),new Civil("Oedipe"),new Civil("Jason"),new Civil("Midas")};
    Monstre[] Monstres = {
        new Monstre("Araignees",1),
        new Monstre("Sirene",2, new Element[]{Element.EAU}),
        new Monstre("Centaure",2, new Element[]{Element.TERRE}),
        new Monstre("LoupGarou",3, new Element[]{Element.TERRE,Element.EAU}),
        new Monstre("Minotaure",4, new Element[]{Element.FEU,Element.TERRE})
    };
    Demon[] Demons = {new Demon("La Meduse",4, new Element[]{Element.FEU})};
    
    // ------------------ ARMES -----------------------------
    Epee[] Epees = {new Epee("Epee en bois",9,4,7), new Epee("Epee d'Achille",15,7,10,Element.FEU),new Epee("Epee d'Hector",18,10,14) ,new Epee("Epee d'Hades", 20, 15, 15,Element.FEU), new Epee("Excalibur", 100, 50, 20,Element.FEU)};
    Lance[] Lances = {new Lance("Lance en bois",7,9,4),new Lance("Lance d'Athena",9,13,7,Element.TERRE)};
    Arc[] Arcs = {new Arc("Arc en bois",4,7,9),new Arc("Arc d'Artemis",9,15,20,Element.EAU)}; 


    //-------------------HISTOIRE------------------
        
    Scanner sc = new Scanner(System.in);
    // Choix du nom du personnage
    Heros heros = new Heros(null);


    // Initialisation des nœuds de décision, de chance
    /** Contient toutes les nodes du jeu avec le titre du node associé à la clé : (titres)
     * I? : pour InnerNode      (? : numero du node)
     * D? : pour DecisionNode 
     * C? : pour ChanceNode
     * T? : pour TerminalNode
     * */
    Map<String,Node> nodeMap = null;
    /** Contient la liste des successeurs pour chaque node associée*/
    Map<String,List<String>> nodeNextMap = null;
    try{
    nodeMap = Lecture.CreateNodeMap("Donnees/Nodes.txt");
    nodeNextMap = Lecture.CreateNodeNextMap("Donnees/NextNodes.txt");
    Lecture.LinkNodeMap(nodeMap,nodeNextMap);
    //Ajoute l'héros principal dans tous les nodes
    Lecture.addHerosNodeMap(nodeMap,heros);
    //Lecture.AfficheNodeMap(nodeMap,nodeNextMap);
    }
    catch(Exception e){
        System.out.println("ERREUR : Problème dans l'extraction des données dans les fichiers");
    }

    DecisionNode D2 = (DecisionNode) nodeMap.get("D2");
    DecisionNode D3 = (DecisionNode) nodeMap.get("D3");
    DecisionNode D7 = (DecisionNode) nodeMap.get("D7");
    DecisionNode D9 = (DecisionNode) nodeMap.get("D9");
    DecisionNode D10 = (DecisionNode) nodeMap.get("D10");
    DecisionNode D11 = (DecisionNode) nodeMap.get("D11");
    DecisionNode D14 = (DecisionNode) nodeMap.get("D14");
    DecisionNode D15 = (DecisionNode) nodeMap.get("D15");
    DecisionNode D17 = (DecisionNode) nodeMap.get("D17");
    DecisionNode D20 = (DecisionNode) nodeMap.get("D20");
    DecisionNode D23 = (DecisionNode) nodeMap.get("D23");
    DecisionNode D24 = (DecisionNode) nodeMap.get("D24");  
    DecisionNode D27 = (DecisionNode) nodeMap.get("D27");  
    ChanceNode C1 = (ChanceNode) nodeMap.get("C1");
    ChanceNode C2 = (ChanceNode) nodeMap.get("C2");
    ChanceNode C3 = (ChanceNode) nodeMap.get("C3");
    ChanceNode C4 = (ChanceNode) nodeMap.get("C4");
    ChanceNode C5 = (ChanceNode) nodeMap.get("C5");
    InnerNode I31 = (InnerNode) nodeMap.get("I31");
    InnerNode I37 = (InnerNode) nodeMap.get("I37");
    InnerNode I84 = (InnerNode) nodeMap.get("I84");
    InnerNode I106 = (InnerNode) nodeMap.get("I106");
    InnerNode I119 = (InnerNode) nodeMap.get("I119");
    
    
    // Configuration des choix, conditions, objets, armes, etc. pour chaque nœud
    D2.addArme(Epees[0]);
    D2.addArme(Lances[0]);
    D2.addArme(Arcs[0]);
    D2.setAction(1);
    D3.setNbOfDecision(3);
    D3.setCondition(1);
    D3.setIsSoundNode();
    D3.setFileName("Arraignee.wav");
    D3.setAllStatsMin(new int[][]{{0,0,0},{5,0,0},{0,5,0}} );
    D3.setNextChoice(new int[][]{{1,1},{2,1},{3,4}}); // [[victoire,perdu], ...]
    D7.setNbOfDecision(3);
    D7.setCondition(2);
    D7.setObjets(new Objet[]{null,Objet.AMULETTE_OEDIPE,null});
    D7.setNextChoice(new int[][]{{1,1},{4,2},{3,3}});
    D9.setAction(2);
    D9.addArme(Epees[1]);
    D10.setCondition(1);
    D10.setAllStatsMin(new int[][]{{0,0,9},{0,0,0},{0,5,0}} );
    D10.setNextChoice(new int[][]{{1,2},{2,2},{3,3}});
    D11.setNbOfDecision(4);
    D11.setCondition(1);
    D11.setAllStatsMin(new int[][]{{9,0,0},{7,0,7},{0,9,0},{0,7,7}});
    D11.setNextChoice(new int[][]{{2,1},{2,1},{2,1},{2,1}});
    D14.setCondition(2);
    D14.setNbOfDecision(2);
    D14.setObjets(new Objet[]{null,Objet.SANDALE_HERMES});
    D14.setNextChoice(new int[][]{{1,1},{2,3}});
    D15.setNbOfDecision(3);
    D15.setCondition(2);
    D15.setObjets(new Objet[]{null,Objet.CIRE,null});
    D15.setNextChoice(new int[][]{{1,1},{2,4},{3,3}});
    D17.setNbOfDecision(3);
    D17.setCondition(2);
    D17.setObjets(new Objet[]{Objet.FLUTE_DE_PAN,null,null});
    D17.setNextChoice(new int[][]{{1,4},{2,2},{3,3}});
    D20.setNbOfDecision(2);
    D20.setCondition(2);
    D20.setObjets(new Objet[]{Objet.FLUTE_DE_PAN,null});
    D20.setNextChoice(new int[][]{{1,3},{2,2}});
    D23.setNbOfDecision(3);
    D23.setCondition(2);
    D23.setObjets(new Objet[]{null,null,Objet.FIL_ARIANE});
    D23.setNextChoice(new int[][]{{1,1},{2,2},{3,4}});
    D24.setNbOfDecision(3);
    D24.setCondition(1);
    D24.setAllStatsMin(new int[][]{{0,0,0},{9,7,9},{10,7,7}} );
    D24.setNextChoice(new int[][]{{1,2},{1,2},{1,2}});
    D27.setNbOfDecision(2);
    D27.setCondition(2);
    D27.setObjets(new Objet[]{Objet.CLEF_DES_ENFERS,null});
    D27.setNextChoice(new int[][]{{1,3},{2,2}});

    C1.setProba(ChanceNode.AlmostSure);
    C2.setProba(ChanceNode.LowChance);
    C3.setProba(ChanceNode.FiftyFifty);
    C4.setProba(ChanceNode.HighChance);
    C5.setProba(ChanceNode.MiddleChance);

    I31.setCondition(1);
    I31.setStatsMin(new int[]{5,4,4});
    I37.setCondition(2);
    I37.addObjets(Objet.VIN);
    I84.setAction(6);
    I106.setCondition(5);
    I119.setCondition(2);
    I119.addObjets(Objet.BOUCLIER_MIROIR);

    // Enumère la Liste des Nodes où un combat se produit
    List<String> CombatNode = Lecture.tabToArray(new String[]{"I18","I19","I81","I91","I98","I103","I120","I123"});
    List<Combattant> NodeCombattant= Lecture.tabToArray(new Combattant[]{Monstres[0],Monstres[0],Monstres[3],Monstres[4],Monstres[2],Dieux[0],Demons[0],Demons[0]});// Combattants associés à chaque Node
    // Enumère la Liste des Nodes où l'héros reçoit un objet
    List<String> getItemNode = Lecture.tabToArray(new String[]{"I20","I36","I59","I70","I75","I99","I100","I105","I50"});
    List<Objet> NodeItem= Lecture.tabToArray(new Objet[]{Objet.CIRE,Objet.VIN,Objet.AILES_ICARE,Objet.CLEF_DES_ENFERS,Objet.BOUCLIER_MIROIR,Objet.AMULETTE_OEDIPE,Objet.FIL_ARIANE,Objet.SANDALE_HERMES,Objet.FLUTE_DE_PAN});
    // Enumère la Liste des Nodes où l'héros reçoit une arme
    List<String> getArmeNode = Lecture.tabToArray(new String[]{"I52","I71","I74","I80","I126"});
    List<Arme> NodeArme= Lecture.tabToArray(new Arme[]{Epees[1],Lances[1],Epees[2],Arcs[1],Epees[3]}); // Liste des Armes associées
    // Enumère la Liste des Nodes où le héros perd une vie où les nodes qui mènent bers game Over.
    String[] GameOverNode = new String[]{"I17","I25","I29","I32","I39","I44","I45","I48","I55","I57","I67","I77","I78","I88","I89","I93","I107","I117","I122","I124"};
    
    String[][] SoundNode = new String[][]{
        {"D3","Arraignee.wav"},
        {"I15","Pas.wav"},
        {"I18","EpeeCoupe.wav"},
        {"I25","Aigle.wav"},
        {"I27","Pas.wav"},
        {"I29","RireGrave.wav"},
        {"I33","EpeeFend.wav"},
        {"I39","Fleche.wav"},
        {"I40","VerreEau.wav"},
        {"I42","Nature.wav"},
        {"I43","Fleche.wav"},
        {"I44","Fleche.wav"},
        {"I45","Galop.wav"},
        {"I46","EpeeFend.wav"},
        {"I64","Ailes.wav"},
        {"I73","Flute.wav"},
        {"I82","Flute.wav"},
        {"I77","Loup.wav"},
        {"I79","EpeeFend.wav"},
        {"I87","CriAnimal.wav"},
        {"I90","EpeeFend.wav"},
        {"I92","EpeeFend.wav"},
        {"D13","RireGrave.wav"},
        {"D20","Loup.wav"},
        {"T2","Victoire.wav"}
    };
    Map<String,Combattant> combatMap = Lecture.createMap(CombatNode,NodeCombattant);
    Map<String,Objet> getItemMap = Lecture.createMap(getItemNode,NodeItem);
    Map<String,Arme> getArmeMap = Lecture.createMap(getArmeNode,NodeArme);

    for (String[] ls : SoundNode){
        nodeMap.get(ls[0]).setIsSoundNode();
        nodeMap.get(ls[0]).setFileName(ls[1]);

    }
    for(String nodeName : getArmeMap.keySet()){
        InnerNode I = (InnerNode) nodeMap.get(nodeName);
        I.setAction(3);
        I.addArme(getArmeMap.get(nodeName));
    }

    for(String nodeName : getItemMap.keySet()){
        InnerNode I = (InnerNode) nodeMap.get(nodeName);
        I.setAction(2);
        I.addObjets(getItemMap.get(nodeName));
    }

    for(String nodeName : combatMap.keySet()){
        InnerNode I = (InnerNode) nodeMap.get(nodeName);
        I.setCondition(4);
        I.addPerso(combatMap.get(nodeName));
    }

    for(String nodeName : GameOverNode){
        InnerNode I = (InnerNode) nodeMap.get(nodeName);
        I.setCondition(3);
        I.setAction(5);
    }


    //Lancement du jeu : Execution
    
    //COMMANDE A UTILISER POUR TRICHER :
    
    //heros.addObjet(Objet.CLEF_DES_ENFERS);
    //heros.setArme(Epees[1]);
    //heros.setVie(50);
    //NodeF.Execute(nodeMap.get("I50"),sc);

    MenuInter.Display(nodeMap);

    }
}
