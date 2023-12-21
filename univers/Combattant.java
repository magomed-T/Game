package univers;

public abstract class Combattant extends PersonnageDeBase{
    protected Element[] Elems; // Liste des Elements associée au monstre

    public Combattant(String Nom, int vies){
        super(Nom,vies);
    }

    public Combattant(String Nom,int vies,Element[] Elems){
        this(Nom,vies);
        this.Elems = Elems;
    }

    public Element[] getElems(){
        return Elems;
    }

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
