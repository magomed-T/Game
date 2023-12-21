package univers.Personnages;
import univers.Combattant;
import univers.Element;
import univers.PersonnageDeBase;

public class Monstre extends Combattant{ // Chaque monstre possède un niveau de rareté (Commun,Rare,Epic,Legendaire)

    public Monstre(String Nom, int vies){
        super(Nom,vies);
    }

    public Monstre(String Nom,int vies,Element[] Elems){
        super(Nom,vies,Elems);
    }
}
