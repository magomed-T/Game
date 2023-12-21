package representation;
import java.util.Scanner;

import Autres.Lecture;

import java.util.ArrayList;
import java.util.List;

import univers.Personnages.Mortels.Heros;
import univers.PersonnageDeBase;
import univers.Objets.Objet;
import univers.Armes.Arme;
public class DecisionNode extends InnerNode {
    private int decision;
    private int nbOfDecision;
    private List<List<Integer>> allStatsMin = new ArrayList<>();
    private List<List<Integer>> nextChoice = new ArrayList<>();
    //private List<Objet> itemKeys = new ArrayList<>();

    public DecisionNode(String description){
        super(description);
    }

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
    /* 
    public void setItemKeys(Objet[] objets){
        for (Objet o : objets)
            itemKeys.add(o);
    }
    */

    public void Decision(){
        if (nbOfDecision == 0)
            nbOfDecision = nodes.size();
        this.decision = Lecture.reponseInt(nbOfDecision);
    }

    public int NextConditionUnderDecision(int decision, int condition){
        //System.out.println("Je suis dans NextConditionUnderDecision");
        if (!(this.personnages.get(0) instanceof Heros)){
            System.out.println("Probleme : Il y a pas d'heros dans le node");
            return -1;
        }
        Heros heros = (Heros) this.personnages.get(0) ;
        
        if(condition == 1){
            Arme HerosArme = heros.getArme();
            List<Integer> StatsMin = allStatsMin.get(decision-1);
            int attaqueMin = StatsMin.get(0);
            int porteeMin = StatsMin.get(1);
            int precMin = StatsMin.get(2);
            //System.out.println(StatsMin);
            if( HerosArme.getPrecision() >= precMin && HerosArme.getAttaque() >= attaqueMin && HerosArme.getPortee() >= porteeMin ){
                return nextChoice.get(decision-1).get(0);   
            }
            else{
                return nextChoice.get(decision-1).get(1);
            }
        }
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

    public int getDecision(){
        return decision;
    }

    public void setNextChoice(int[][] T){
        for (int i = 0; i<T.length ; i++){
            this.nextChoice.add(new ArrayList<>());
            for (int j = 0; j<T[0].length ;j++){
                this.nextChoice.get(i).add(T[i][j]);
            }
        }
    }

    @Override
    public void callAction(){
        Heros heros = (Heros) this.personnages.get(0);
        // Choix de prendre une arme parmi les decisions
        if (this.action ==1){
            heros.setArme(this.armes.get(decision-1));
        }
        // Choix de garder l'arme actuelle ou de changer
        if (this.action ==2){
            if(this.decision == 1)
                heros.setArme(this.armes.get(0));
        }

        if (this.action == 3)
        return;
    }

    public void setNbOfDecision(int nbOfDecision){
        this.nbOfDecision = nbOfDecision;
    }

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

    @Override
    public void display(){
        super.display();
        Decision();
        System.out.println("");
    }


}
