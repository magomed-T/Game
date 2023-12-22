package univers.Personnages;
import univers.Combattant;
import univers.Element;
import univers.Interfaces.*;

/**
 * La classe Divinite représente un type particulier de Combattant ayant des attributs divins.
 * Les divinités ont un coefficient divin qui influence leurs caractéristiques.
 * La classe implémente l'interface Divin pour définir des comportements divins spécifiques.
 *  */
public class Divinite extends Combattant implements Divin {
    /**
     * Le coefficient divin de la divinité.
     */ 
    private Double coeffDivin = 0.2;

    /**
     * Constructeur pour créer une divinité avec un nom et un nombre de vies.
     *
     * @param Nom Le nom de la divinité.
     * @param vie Le nombre initial de vies de la divinité.
     */
    public Divinite(String Nom, int vie){
        super(Nom,vie);
    }

    /**
     * Constructeur pour créer une divinité avec un nom, un nombre de vies, et un élément associé.
     *
     * @param Nom  Le nom de la divinité.
     * @param vie  Le nombre initial de vies de la divinité.
     * @param Elem L'élément associé à la divinité.
     */
    public Divinite(String Nom, int vie,Element Elem){
        super(Nom,vie,new Element[]{Elem});
    }
    /**
     * Constructeur pour créer une divinité avec un nom, un nombre de vies, et une liste d'éléments associés.
     *
     * @param Nom   Le nom de la divinité.
     * @param vie   Le nombre initial de vies de la divinité.
     * @param Elems La liste d'éléments associés à la divinité.
     */
    public Divinite(String Nom, int vie,Element[] Elems){
        super(Nom,vie,Elems);
    }

    /**
     * Getter pour obtenir le coefficient divin de la divinité.
     *
     * @return Le coefficient divin de la divinité.
     */
    public Double getCoeffDivin(){
        return this.coeffDivin;
    }

    /**
     * Setter pour définir le coefficient divin de la divinité.
     *
     * @param coeffDivin Le nouveau coefficient divin de la divinité.
     */
    public void setCoeffDivin(Double coeffDivin){
        this.coeffDivin = coeffDivin;
    }

    /**
     * Méthode divin pour ressusciter la divinité en augmentant le nombre de vies.
     */
    @Override
    public void Ressuciter(){
        vie++;
    }
}
