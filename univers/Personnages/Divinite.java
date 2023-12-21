package univers.Personnages;
import univers.Combattant;
import univers.Element;
import univers.PersonnageDeBase;
import univers.Interfaces.*;

public class Divinite extends Combattant implements Divin { 
    private Double coeffDivin = 0.2;
    public Divinite(String Nom, int vie){
        super(Nom,vie);
    }

    public Divinite(String Nom, int vie,Element Elem){
        super(Nom,vie,new Element[]{Elem});
    }

    public Divinite(String Nom, int vie,Element[] Elems){
        super(Nom,vie,Elems);
    }

    public Double getCoeffDivin(){
        return this.coeffDivin;
    }

    public void setCoeffDivin(Double coeffDivin){
        this.coeffDivin = coeffDivin;
    }
    @Override
    public void Ressuciter(){
        vie++;
    }
}
