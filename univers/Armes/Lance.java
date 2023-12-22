package univers.Armes;
import univers.Element;

/**
 * La classe Lance représente une lance, un type d'arme, dans un univers.
 * Les lances ont une attaque moyenne, une portée faible et une précision forte.
 * <p>
 * */
public class Lance extends Arme{
    //Arme ayant Attaque MOYEN, Portee FAIBLE, Precision FORTE
    /**
     * Constructeur pour créer une lance avec un nom, des valeurs d'attaque, de portée et de précision.
     *
     * @param Nom       Le nom de la lance.
     * @param attaque   La valeur d'attaque de la lance (0 à 50).
     * @param portee    La valeur de portée de la lance (0 à 50).
     * @param precision La valeur de précision de la lance (0 à 50).
     */ 
    public Lance(String Nom, int attaque, int portee, int precision){
        super(Nom,attaque,portee,precision);
    }

    /**
     * Constructeur pour créer une lance avec un nom, des valeurs d'attaque, de portée, de précision et un élément associé.
     *
     * @param Nom       Le nom de la lance.
     * @param attaque   La valeur d'attaque de la lance (0 à 50).
     * @param portee    La valeur de portée de la lance (0 à 50).
     * @param precision La valeur de précision de la lance (0 à 50).
     * @param Elem      L'élément associé à la lance.
     */

    public Lance(String Nom, int attaque, int portee, int precision,Element Elem){
        super(Nom,attaque,portee,precision,Elem);
    }

    /**
     * Méthode représentant la compétence de la lance.
     * Cette compétence attaque les ennemis en vol.
     */
    public void Competence(){ // ataque les ennemis en vol
        return;
    }

}
