package univers.Armes;
import java.io.Serializable;

import univers.Element;

/**
 * La classe abstraite Arme représente un type d'arme dans un univers.
 * Chaque arme possède des attributs tels que l'attaque, la portée, la précision (valeurs de 0 à 50).
 * Certains choix nécessitent un minimum d'attaque, de portée ou de précision.
 *  Si le choix est effectué et que le nombre d'attaque,
 * de portée ou de précision est insuffisant, le héros perd une vie.
 * Deplus, la puissance d'une arme correspond à la somme de l'attaque, la portée et la précision.
 * Plus, la puissance d'une arme est élevée plus ses chances de coups critiques en combats sont élevées.
 * Il existe un cycle de force-faiblesse entre les éléments. Une arme peut avoir un élément
 * Eau gagne contre Feu | Feu gagne contre Terre | Terre gagne contre Eau 
 * Chaque arme possede une compétence en fonction de si c'est une épée, lance ou un arc.
 * */
public abstract class Arme implements Serializable{
    /**
     * Le nom de l'arme.
     */
    protected String Nom;
    /**
     * L'élément associé à l'arme (FEU, VENT, EAU, FOUDRE).
     */
    protected Element Elem; // FEU VENT EAU FOUDRE
    /**
     * La valeur d'attaque de l'arme (0 à 50).
     */
    protected int attaque ; // 0 à 50
    /**
     * La valeur de portée de l'arme (0 à 50).
     */
    protected int portee ; // 0 à 50
    /**
     * La valeur de précision de l'arme (0 à 50).
     */
    protected int precision; // 0 à 50

    /**
     * Constructeur pour créer une arme avec un nom, des valeurs d'attaque, de portée et de précision.
     *
     * @param Nom       Le nom de l'arme.
     * @param attaque   La valeur d'attaque de l'arme (0 à 50).
     * @param portee    La valeur de portée de l'arme (0 à 50).
     * @param precision La valeur de précision de l'arme (0 à 50).
     */
    public Arme(String Nom, int attaque, int portee, int precision){
        this.Nom = Nom;
        this.attaque= attaque;
        this.portee = portee;
        this.precision = precision;
    }

    /**
     * Constructeur pour créer une arme avec un nom, des valeurs d'attaque, de portée, de précision et un élément associé.
     *
     * @param Nom       Le nom de l'arme.
     * @param attaque   La valeur d'attaque de l'arme (0 à 50).
     * @param portee    La valeur de portée de l'arme (0 à 50).
     * @param precision La valeur de précision de l'arme (0 à 50).
     * @param Elem      L'élément associé à l'arme.
     */
    public Arme(String Nom, int attaque, int portee, int precision,Element Elem) {
        this(Nom,attaque,portee,precision);
        this.Elem = Elem;
    }

    public static boolean CycleElem(Element e1, Element e2){
        if(e1==null ||e2 == null) return false;
        if (e1 == e2){
            return false;
        }
        else{
            if ( (e1 == Element.EAU && e2 == Element.FEU) || (e1 == Element.FEU && e2 == Element.TERRE) || (e1 == Element.TERRE && e2 == Element.EAU))
                return true;
            else 
                return false;
        }
    }

    // Méthodes statiques pour les cycles d'éléments
    public static boolean CycleElems(Element e1, Element[] elements){
        if (elements == null) return false;
        boolean e = false;
        for (Element e2 : elements)
            e = e || CycleElem(e1, e2);
        return e;
    }

    @Override 
    public String toString(){
        if (this.Elem==null)
            return "\n" + this.Nom + "\nAttaque : " + this.attaque + "\nPortee : " + this.portee  + "\nPrecision : " + this.precision + "\nElement : AUCUN" ;
        else
            return "\n" + this.Nom + "\nAttaque : " + this.attaque + "\nPortee : " + this.portee  + "\nPrecision : " + this.precision + "\nELEMENT : " + this.Elem ;
    }

    // Ameliore les attributs de l'arme
    public void Ameliorer(int att,int port,int prec){
        this.attaque+= att;
        this.portee += port;
        this.precision += prec;
    }

    //GETTERS
    /**
     * Méthode pour obtenir la puissance totale de l'arme.
     *
     * @return La puissance totale de l'arme.
     */
    public int getPuissance(){
        return this.attaque + this.portee + this.precision;
    }

    /**
     * Getter pour obtenir le nom de l'arme.
     *
     * @return Le nom de l'arme.
     */
    public String getNom(){
        return this.Nom ;
    }

    /**
     * Getter pour obtenir la valeur d'attaque de l'arme.
     *
     * @return La valeur d'attaque de l'arme.
     */

    public int getAttaque(){
        return this.attaque;
    }

    /**
     * Getter pour obtenir la valeur de portée de l'arme.
     *
     * */
    public int getPortee(){
        return this.portee ;
    }

    /**
     * Getter pour obtenir la précision de l'arme.
     *
     * */
    public int getPrecision(){
        return this.precision ;
    }

    /**
     * Getter pour obtenir l'Element de l'arme.
     *
     * */
    public Element getElem(){
        return this.Elem ;
    }

    //SETTERS

    public void setAttaque(int attaque){
        this.attaque = attaque;
    }

    public void setPortee(int portee){
        this.portee = portee;
    }

    public void setPrecision(int precision){
        this.precision = precision;
    }


    public void setElem(Element Elem){
        this.Elem = Elem;
    }
}
