package univers.Armes;
import univers.Element;

/**
 * La classe Arc représente un arc, un type d'arme, dans un univers.
 * Les arcs ont une attaque faible, une portée forte et une précision moyenne.
 * */

public class Arc extends Arme{
    //Arme ayant Attaque FAIBLE, Portee FORTE, Precision MOYEN (attaque les adversaires de type VOL) 
    public Arc(String Nom, int attaque, int portee, int precision){
        super(Nom,attaque,portee,precision);
    }

    /**
     * Constructeur pour créer un arc avec un nom, des valeurs d'attaque, de portée, de précision et un élément associé.
     *
     * @param Nom       Le nom de l'arc.
     * @param attaque   La valeur d'attaque de l'arc (0 à 50).
     * @param portee    La valeur de portée de l'arc (0 à 50).
     * @param precision La valeur de précision de l'arc (0 à 50).
     * @param Elem      L'élément associé à l'arc.
     */
    public Arc(String Nom, int attaque, int portee, int precision,Element Elem){
        super(Nom,attaque,portee,precision,Elem);
    }

    /**
     * Méthode représentant la compétence de l'arc.
     */
    public void Competence(){ // Fleche Elementaire
        return;
    }
}
