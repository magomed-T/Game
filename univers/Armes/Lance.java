package univers.Armes;
import univers.Element;
;

public class Lance extends Arme{
    //Arme ayant Attaque MOYEN, Portee FAIBLE, Precision FORTE 
    public Lance(String Nom, int attaque, int portee, int precision){
        super(Nom,attaque,portee,precision);
    }

    public Lance(String Nom, int attaque, int portee, int precision,Element Elem){
        super(Nom,attaque,portee,precision,Elem);
    }

    public void Competence(){ // ataque les ennemis en vol
        return;
    }

}
