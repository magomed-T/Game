package representation;

/**
 * Decorateur:
 * L'interface Event définit les méthodes que doit implémenter une classe représentant un événement dans le jeu.
 */
public interface Event {

    /**
     * Affiche le contenu de l'événement.
     */
    public void display();

    /**
     * Choisi le prochain événement à exécuter.
     * 
     * @return Le prochain événement à exécuter.
     */
    public Event chooseNext();
}