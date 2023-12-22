package univers.Personnages;
import java.util.LinkedList;
import java.util.List;

import univers.Armes.*;
import univers.Objets.Objet;

/**
 * La classe Heros représente un personnage mortel doté d'une arme et d'un inventaire d'objets.
 * Les héros ont un nombre de vies par défaut de 5.
 * */
public class Heros extends Mortel {
    /**
     * L'arme du héros.
     */
    Arme arme;

    /**
     * Liste des objets dans l'inventaire du héros.
     */
    List<Objet> objets = new LinkedList<>(); // Liste des objets dans l'inventaire du heros

    /**
     * Constructeur pour créer un héros avec un nom.
     *
     * @param Nom Le nom du héros.
     */
    public Heros(String Nom){
        super(Nom);
        this.vie = 5;
    }

    /**
     * Ajoute un objet à la liste des objets dans l'inventaire du héros.
     *
     * @param objet L'objet à ajouter à l'inventaire du héros.
     */
    public void addObjet( Objet objet ){
        objets.add(objet);
    }

    /**
     * Vérifie si le héros possède un objet spécifique dans son inventaire.
     *
     * @param o0 L'objet à vérifier.
     * @return true si le héros possède l'objet, sinon false.
     */
    public boolean hasObjet(Objet o0){
        for(Objet o : this.objets){
            if(o==o0)
                return true;
        }
        return false;
    }

    /**
     * Affiche les informations du héros, y compris son inventaire et son arme.
     *
     * @return Une chaîne de caractères représentant les informations du héros.
     */
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


    /**
     * Affiche les objets présents dans l'inventaire du héros.
     */
    public void showObjets(){
        for (Objet objet : this.objets){
            if (objet!=null)
                System.out.println(objet + "\t");
            else
                return;
        }
    }

    /**
     * Affiche l'arme actuelle du héros.
     */
    public void showArme(){
        System.out.println(this.arme.getNom());
    }

    // GETTERS
    /**
     * Getter pour obtenir l'arme du héros.
     *
     * @return L'arme du héros.
     */ 
    public Arme getArme(){
        return this.arme;
    }

    /**
     * Getter pour obtenir la liste des objets dans l'inventaire du héros.
     *
     * @return La liste des objets dans l'inventaire du héros.
     */
    public List<Objet> getObjets(){
        return this.objets;
    }
    
    // SETTERS
    /**
     * Setter pour définir le nombre de vies du héros.
     *
     * @param vie Le nouveau nombre de vies du héros.
     */
    public void setVie(int vie){
        this.vie = vie; // 3 de base
    }

    /**
     * Setter pour définir l'arme du héros.
     *
     * @param arme La nouvelle arme du héros.
     */
    public void setArme( Arme arme){
        this.arme = arme;
    }
}
