package representation;

import Autres.Lecture;

import java.util.ArrayList;
import java.util.List;

import univers.Objets.Objet;
import univers.Personnages.Heros;
import univers.Armes.Arme;

/**
 * La classe DecisionNode représente un nœud de décision dans une structure arborescente.
 * Elle étend la classe InnerNode et est utilisée pour modéliser les choix que le joueur peut faire
 * au cours de l'histoire.
 */

public class DecisionNode extends InnerNode {
    /** Variable entière contenant la decision du joueur */
    private int decision;
    /** Nombre de decisions definis dans le node */
    private int nbOfDecision;
    /** Liste des statistiques minimales à avoir pour chaque decision pour pouvoir passer au prochain node*/
    private List<List<Integer>> allStatsMin = new ArrayList<>();
    /** Liste des prochains nodes en fonction de chaque décision ( en fontion de si c'est {victoire,défaite})*/
    private List<List<Integer>> nextChoice = new ArrayList<>();

    /**
     * Constructeur de la classe DecisionNode.
     *
     * @param description La description associée à ce nœud de décision.
     */
    public DecisionNode(String description){
        super(description);
    }

    /**
     * Définit les statistiques minimales nécessaires pour chaque choix de décision.
     *
     * @param T Un tableau d'entiers représentant les statistiques minimales pour chaque choix.
     */
    public void setAllStatsMin(int[][] T){
        for (int i = 0; i<T.length ; i++){
            allStatsMin.add(new ArrayList<>());
            if (T[i] != null){
                for (int j = 0; j<3 ;j++){
                    allStatsMin.get(i).add(T[i][j]);
                }
                
            }
        }

    }

    /**
     * Effectue la décision du joueur en lui demandant de choisir parmi les options disponibles.
     */

    public void Decision(){
        if (nbOfDecision == 0)
            nbOfDecision = nodes.size();
        this.decision = Lecture.reponseInt(nbOfDecision);
    }

    /**
     * Obtient le numéro du prochain nœud en fonction de la condition et de la décision du joueur.
     *
     * @param decision  La décision du joueur.
     * @param condition La condition à évaluer.
     * @return Le numéro du prochain nœud en fonction de la condition et de la décision du joueur.
     */

    public int NextConditionUnderDecision(int decision, int condition){
        try{
        if ((this.personnages.get(0) == null))
            throw new Exception();
        }
        catch(Exception e){System.out.println("Probleme : Il y a pas d'heros dans le node");}
        Heros heros = (Heros) this.personnages.get(0) ;
        // Vérifie si l'arme du héros possède les caractéristiques minimales nécessaires avant d'aller au prochain node
        if(condition == 1){
            Arme HerosArme = heros.getArme();
            List<Integer> StatsMin = allStatsMin.get(decision-1);
            int attaqueMin = StatsMin.get(0);
            int porteeMin = StatsMin.get(1);
            int precMin = StatsMin.get(2);

            // Vérifie si l'arme du heros possède sles caractéristiques minimales nécessaire
            // Et, va au prochain noeud en fonction de si la condition est vérifiée ou non
            if( HerosArme.getPrecision() >= precMin && HerosArme.getAttaque() >= attaqueMin && HerosArme.getPortee() >= porteeMin ){
                return nextChoice.get(decision-1).get(0);   
            }
            else{
                return nextChoice.get(decision-1).get(1);
            }
        }
        // Vérifie si le joueur possède un objet avant d'acceder au prochain node
        else if(condition == 2){
            Objet o = objets.get(decision-1);
            if(heros.hasObjet(o)){
                return nextChoice.get(decision-1).get(0); 
            }
            else{
                return nextChoice.get(decision-1).get(1);
            }
        }
        return 0;
    }

    /**
     * Obtient la décision du joueur.
     *
     * @return Le numéro de la décision du joueur.
     */
    public int getDecision(){
        return decision;
    }

    /**
     * Définit les choix disponibles pour le prochain nœud en fonction de la décision du joueur.
     *
     * @param T Un tableau d'entiers représentant les choix pour chaque décision.
     */
    public void setNextChoice(int[][] T){
        for (int i = 0; i<T.length ; i++){
            this.nextChoice.add(new ArrayList<>());
            for (int j = 0; j<T[0].length ;j++){
                this.nextChoice.get(i).add(T[i][j]);
            }
        }
    }

    /**
     * Exécute l'action associée à ce nœud de décision.
     */
    @Override
    public void callAction(){
        Heros heros = (Heros) this.personnages.get(0);
        // Choix de prendre une arme parmi les decisions
        if (this.action ==1){
            heros.setArme(this.armes.get(decision-1));
        }
        // Choix de garder l'arme actuelle ou de la changer
        if (this.action ==2){
            if(this.decision == 1)
                heros.setArme(this.armes.get(0));
        }
    }

    /**
     * Définit le nombre de décisions possibles pour ce nœud.
     *
     * @param nbOfDecision Le nombre de décisions possibles.
     */
    public void setNbOfDecision(int nbOfDecision){
        this.nbOfDecision = nbOfDecision;
    }

    /**
     * Choix du prochain nœud en fonction de la décision du joueur et de la condition associée.
     *
     * @return Le prochain nœud en fonction de la décision du joueur et de la condition.
     */
    @Override
    public Node chooseNext(){
        //callAction();
        if (this.getCondition() == 0 ){
            return nodes.get(decision-1);
        }
        else{
            //System.out.println("condition :" + this.getCondition());
            int numNextNode = NextConditionUnderDecision(decision,getCondition()); // A FAIRE : Modifier decision
            return nodes.get(numNextNode-1);
        }

    }

    /**
     * Affiche les détails du nœud de décision.
     */
    @Override
    public void display(){
        super.display();
        Decision();
        System.out.println("");
    }
}
