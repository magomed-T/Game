package univers.Armes;
import java.io.Serializable;

import univers.Element;

/* 
- Chaque arme contient 3 attributs: attaque, portee, precision (0 à 50).
- Certains choix EN COMBAT nécessite un minimum d'attaque, de portee, ou de precision pour
pouvoir faire perdre une vie à l'adversaire.
- Si le choix est choisi et le nbr d'attaque , portee, precision est inférieur, 
alors le héros perd une vie.
 */
public abstract class Arme implements Serializable{
    protected String Nom;
    protected Element Elem; // FEU VENT EAU FOUDRE
    protected int attaque ; // 0 à 50
    protected int portee ; // 0 à 50
    protected int precision; // 0 à 50

    public Arme(String Nom, int attaque, int portee, int precision){
        this.Nom = Nom;
        this.attaque= attaque;
        this.portee = portee;
        this.precision = precision;
    }

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

    public int getPuissance(){
        return this.attaque + this.portee + this.precision;
    }

    //GETTERS
    public String getNom(){
        return this.Nom ;
    }
    
    public int getAttaque(){
        return this.attaque;
    }

    public int getPortee(){
        return this.portee ;
    }

    public int getPrecision(){
        return this.precision ;
    }

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
