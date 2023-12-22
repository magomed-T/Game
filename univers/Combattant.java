    package univers;

    /**
    * La classe abstraite Combattant étend la classe PersonnageDeBase et représente un combattant dans un univers.
    * Un combattant possède des éléments associés et est capable d'être en combat contre le heros. (Voir fonction combat dans MenuInter.java)
    * */


public abstract class Combattant extends PersonnageDeBase{
    /**
     * Liste des éléments associés au combattant.
     */
    protected Element[] Elems; // Liste des Elements associée au monstre

    /**
     * Constructeur pour créer un combattant avec un nom et un nombre de vies.
     *
     * @param Nom  Le nom du combattant.
     * @param vies Le nombre initial de vies du combattant.
     */
    public Combattant(String Nom, int vies){
        super(Nom,vies);
    }

    /**
     * Constructeur pour créer un combattant avec un nom, un nombre de vies, et des éléments associés.
     *
     * @param Nom   Le nom du combattant.
     * @param vies  Le nombre initial de vies du combattant.
     * @param Elems La liste d'éléments associés au combattant.
     */
    public Combattant(String Nom,int vies,Element[] Elems){
        this(Nom,vies);
        this.Elems = Elems;
    }

    /**
     * Getter pour obtenir la liste des éléments associés au combattant.
     *
     * @return La liste des éléments associés au combattant.
     */

    public Element[] getElems(){
        return Elems;
    }

    /**
     * Setter pour définir la liste des éléments associés au combattant.
     *
     * @param Elems La nouvelle liste d'éléments associés au combattant.
     */
    public void setElems(Element[] Elems) {
        this.Elems = Elems;
    }

    /**
     * Méthode pour obtenir une représentation textuelle du combattant.
     *
     * @return Une chaîne de caractères représentant le combattant, y compris les éléments associés.
     */
    @Override
    public String toString() {
        if (Elems == null)
            return super.toString();
        else{
            String s ="";
            for (Element Elem : Elems){
                s = s + Elem.toString();
                s = s+" ";
            }
            if (s!="")
                return super.toString() + "\nElement: "+s+"";
            else
                return super.toString();
        }
    }
}
