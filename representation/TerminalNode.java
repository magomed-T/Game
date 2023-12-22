package representation;

/**
 * La classe TerminalNode représente un Node terminal dans le jeu, c'est-à-dire un Node sans successeurs.
 * Elle étend la classe Node.
 */
public class TerminalNode extends Node {

    /**
     * Constructeur avec une description spécifiée.
     * 
     * @param description La description du Node terminal.
     */
    public TerminalNode(String description) {
        super(description);
    }

    /**
     * Retourne le Node terminal lui-même, car il n'y a pas d'option de transition.
     * 
     * @return Le Node terminal lui-même.
     */
    @Override
    public Node chooseNext() {
        return this;
    }
}