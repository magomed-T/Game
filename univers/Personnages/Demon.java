package univers.Personnages;
import univers.Personnages.Monstre;
import univers.Interfaces.*;
import univers.Element;
/**
 * La classe `Demon` représente un type de monstre, étendant la classe `Monstre` et
 * implémentant l'interface `Enfer`. Les démons dans ce contexte sont caractérisés par leur nom,
 * leurs points de vie et leur capacité à intimider.
 * 
 * * Les démons peuvent effectuer la méthode `Intimider`, qui fait partie de l'interface `Enfer`.
 * */
public class Demon extends Monstre implements Enfer{

    /**
     * Construit un démon avec un nom et des points de vie spécifiés.
     *
     * @param Nom Le nom du démon.
     * @param vie Les points de vie du démon.
     */
    public Demon(String Nom,int vie){
        super(Nom,vie);
        //System.out.println("Je suis un Démon!");
        this.vie = 6;
    }

    /**
     * Construit un démon avec un nom, des points de vie et un tableau d'éléments spécifiés.
     *
     * @param Nom   Le nom du démon.
     * @param vie   Les points de vie du démon.
     * @param Elems Le tableau d'éléments associés au démon.
     */
    public Demon(String Nom,int vie, Element[] Elems){
        super(Nom,vie);
        this.Elems = Elems;
    }

    
    public void Intimider(){
        return;
    }
}
