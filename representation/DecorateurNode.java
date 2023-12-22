package representation;

/**
 * La classe DecorateurNode est une classe abstraite représentant un décorateur pour un Event.
 * Elle implémente l'interface Event et permet de décorer le comportement d'un Event existant.
 */
public abstract class DecorateurNode implements Event {

    /** L'Event original à décorer. */
    protected Event E;

    /**
     * Constructeur avec un Event spécifié.
     * 
     * @param E L'Event à décorer.
     */
    public DecorateurNode(Event E) {
        this.E = E;
    }

    /**
     * Choisi le prochain Event en utilisant le comportement de l'Event original.
     * 
     * @return Le prochain Event choisi.
     */
    @Override
    public Event chooseNext() {
        return E.chooseNext();
    }

    /**
     * Affiche le contenu de l'Event en utilisant le comportement de l'Event original.
     */
    @Override
    public void display() {
        E.display();
    }

    /**
     * Obtient l'Event original.
     * 
     * @return L'Event original.
     */
    public Event getEvent() {
        return this.E;
    }
}
