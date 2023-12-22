package univers;

import java.io.Serializable;
/**
 * La classe abstraite PersonnageDeBase représente un personnage de base dans un univers.
 * Elle implémente l'interface Serializable pour permettre la sérialisation des objets.
 *
 * Cette classe définit des attributs communs à tous les personnages :
 * - vie : le nombre de vies du personnage
 * - Nom : le nom du personnage
 *
 * */

public abstract class PersonnageDeBase implements Serializable {
    /** Nombre de vies du personnage */
    protected int vie; // Nombre de vies du personnage
    /** Nom du personnage */
    protected String Nom;

    /**
     * Constructeur pour créer un personnage avec un nom.
     *
     * @param Nom Le nom du personnage.
     */

    public PersonnageDeBase(String Nom){
        this.Nom = Nom;
    }

    /**
     * Constructeur pour créer un personnage avec un nom et un nombre de vies initial.
     *
     * @param Nom Le nom du personnage.
     * @param vie Le nombre initial de vies du personnage.
     */
    public PersonnageDeBase(String Nom,int vie){
        this.Nom = Nom;
        this.vie = vie;
    }

    /**
     * Méthode pour obtenir une représentation textuelle du personnage.
     *
     * @return Une chaîne de caractères représentant le personnage.
     */
    @Override 
    public String toString(){
        return "\n" + this.Nom + "\nVies : " + this.vie ;
    }

    /**
     * Méthode pour vérifier l'égalité entre deux personnages.
     *
     * @param obj L'objet à comparer avec le personnage actuel.
     * @return true si les personnages sont égaux, sinon false.
     */
    @Override 
    public boolean equals(Object obj){
        PersonnageDeBase P2 = (PersonnageDeBase) obj;
        return this.vie == P2.vie && this.Nom.equals(P2.Nom);
    }

    //GETTERS
    /**
     * Getter pour obtenir le nombre de vies du personnage.
     *
     * @return Le nombre de vies du personnage.
     */
    public int getVie(){
        return this.vie;
    }
    /**
     * Getter pour obtenir le nom du personnage.
     *
     * @return Le nom du personnage.
     */
    public String getNom(){
        return this.Nom;
    }

    //SETTERS
    /**
     * Setter pour définir le nombre de vies du personnage.
     *
     * @param vie Le nouveau nombre de vies du personnage.
     */
    public void setVie(int vie){
        this.vie = vie;
    }
    
    /**
     * Setter pour définir le nom du personnage.
     *
     * @param Nom Le nouveau nom du personnage.
     */
    public void setNom(String Nom){
        this.Nom = Nom;
    }
}