package univers.Armes;
import univers.Element;

/**
 * La classe Epee représente une épée, un type d'arme, dans un univers.
 * Les épées ont une attaque forte, une portée faible et une précision moyenne.
 *  */
public class Epee extends Arme{

    /**
     * Constructeur pour créer une épée avec un nom, des valeurs d'attaque, de portée et de précision.
     *
     * @param Nom       Le nom de l'épée.
     * @param attaque   La valeur d'attaque de l'épée (0 à 50).
     * @param portee    La valeur de portée de l'épée (0 à 50).
     * @param precision La valeur de précision de l'épée (0 à 50).
     */
    public Epee(String Nom, int attaque, int portee, int precision){
        super(Nom,attaque,portee,precision);
    }
 
    /**
     * Constructeur pour créer une épée avec un nom, des valeurs d'attaque, de portée, de précision et un élément associé.
     *
     * @param Nom       Le nom de l'épée.
     * @param attaque   La valeur d'attaque de l'épée (0 à 50).
     * @param portee    La valeur de portée de l'épée (0 à 50).
     * @param precision La valeur de précision de l'épée (0 à 50).
     * @param Elem      L'élément associé à l'épée.
     */

    public Epee(String Nom, int attaque, int portee, int precision,Element Elem){
        super(Nom,attaque,portee,precision,Elem);
    }

    /**
     * Méthode représentant la compétence de l'épée.
     * Cette compétence retire 2 vies à l'ennemi.
     */

    public void Competence(){ // retire 2 vies à l'ennemi
        return;
    }

}
