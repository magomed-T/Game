package univers.Personnages.Mortels;
import univers.Personnages.Mortel;
import java.util.LinkedList;
import java.util.List;

import univers.Armes.*;
import univers.Objets.Objet;

public class Heros extends Mortel {
    Arme arme;
    List<Objet> objets = new LinkedList<>(); // Liste des objets dans l'inventaire du heros

    public Heros(String Nom){
        super(Nom);
        this.vie = 5;
    }

    // Ajoute l'objet "objet" dans notre liste des objets "objets"
    public void addObjet( Objet objet ){
        objets.add(objet);
    }

    public boolean hasObjet(Objet o0){
        for(Objet o : this.objets){
            if(o==o0)
                return true;
        }
        return false;
    }

    @Override
    public String toString(){
        System.out.println("\nINVENTAIRE:");
        System.out.print("\tHeros : " + this.Nom + "\n");
        System.out.print("\nVies : " + this.vie + "\n");
        System.out.print("\nArme :" + this.arme + "\n");
        System.out.print("\nObjets:\n");
        showObjets();

        return"";
    }

    public void showObjets(){
        for (Objet objet : this.objets){
            if (objet!=null)
                System.out.println(objet + "\t");
            else
                return;
        }
    }

    public void showArme(){
        System.out.println(this.arme.getNom());
    }

    //GETTERS
    public Arme getArme(){
        return this.arme;
    }

    public List<Objet> getObjets(){
        return this.objets;
    }
    
    //SETTERS
    public void setVie(int vie){
        this.vie = vie; // 3 de base
    }

    public void setArme( Arme arme){
        this.arme = arme;
    }
}
