package univers.Personnages;
import univers.Combattant;
import univers.Element;
/**
 * La classe Monstre représente un type de Combattant spécifique dans un univers.
 * */
public class Monstre extends Combattant{ // Chaque monstre possède un niveau de rareté (Commun,Rare,Epic,Legendaire)

    /**
     * Constructeur pour créer un monstre avec un nom et un nombre de vies.
     *
     * @param Nom  Le nom du monstre.
     * @param vies Le nombre initial de vies du monstre.
     */

    public Monstre(String Nom, int vies){
        super(Nom,vies);
    }

    /**
     * Constructeur pour créer un monstre avec un nom, un nombre de vies, et des éléments associés.
     *
     * @param Nom   Le nom du monstre.
     * @param vies  Le nombre initial de vies du monstre.
     * @param Elems La liste d'éléments associés au monstre.
     */

    public Monstre(String Nom,int vies,Element[] Elems){
        super(Nom,vies,Elems);
    }
}
