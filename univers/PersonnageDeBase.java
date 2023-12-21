package univers;

import java.io.Serializable;

public abstract class PersonnageDeBase implements Serializable {
    protected int vie; // Nombres de vies du personnage
    protected String Nom;
    public PersonnageDeBase(String Nom){
        this.Nom = Nom;
    }

    public PersonnageDeBase(String Nom,int vie){
        this.Nom = Nom;
        this.vie = vie;
    }


    @Override 
    public String toString(){
        return "\n" + this.Nom + "\nVies : " + this.vie ;
    }

    @Override 
    public boolean equals(Object obj){
        PersonnageDeBase P2 = (PersonnageDeBase) obj;
        return this.vie == P2.vie && this.Nom.equals(P2.Nom);
    }

    //GETTERS
    public int getVie(){
        return this.vie;
    }

    public String getNom(){
        return this.Nom;
    }

    //SETTERS
    public void setVie(int vie){
        this.vie = vie;
    }

    public void setNom(String Nom){
        this.Nom = Nom;
    }
}