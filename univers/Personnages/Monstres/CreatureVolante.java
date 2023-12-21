package univers.Personnages.Monstres;
import univers.Element;
import univers.Interfaces.Vol;
import univers.Personnages.Monstre;

public class CreatureVolante extends Monstre implements Vol{
    boolean vol = false;
    public CreatureVolante(String Nom,int vie){
        super(Nom,vie);
    }

    public CreatureVolante(String Nom,int vie,Element[] Elems){
        this(Nom,vie);
        this.Elems = Elems;
    }

    public void Voler(){
        this.vol = true;
    }

    public void Atterir(){
        this.vol = false;
    }

    public boolean getVol(){
        return this.vol;
    }

}
