package representation;

import univers.Armes.Arme;
import univers.Objets.Objet;
import univers.Personnages.Heros;
import univers.PersonnageDeBase;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Un Node représente une situation dans l'histoire de notre jeu.
 * Les classes concrètes qui étendent Node définissent le comportement spécifique de chaque situation.
 */
public abstract class Node implements Event, Serializable {

    /** Texte décrivant la situation de l'histoire dans le Node. */
    private String description;

    /** Le nombre de nodes créés. */
    private static int nbNodes = 0;

    /** Numéro unique associé à chaque Node. */
    private final int id;

    /** Vrai si le Node génère un son. */
    private boolean isSoundNode = false;

    /** Chemin du fichier si c'est un SoundNode. */
    private String fileName;

    /** Liste des personnages présents sur la scène. */
    protected List<PersonnageDeBase> personnages = new LinkedList<>();

    /** Liste des armes présentes sur la scène. */
    protected List<Arme> armes = new LinkedList<>();

    /** Liste des objets présents sur la scène. */
    protected List<Objet> objets = new LinkedList<>();

    /**
     * Numéro d'action associé au Node.
     * 0 si aucune action, différent de 0, exécute une action en fonction du numéro d'action.
     */
    protected int action = 0;

    /**
     * Constructeur initialisant la description et l'ID du Node.
     * @param description La description de la situation.
     */
    public Node(String description) {
        this.description = description;
        nbNodes += 1;
        id = nbNodes;
    }

    // Méthodes pour ajouter des éléments à la scène

    /**
     * Ajoute une arme à la scène.
     * 
     * @param arme L'arme à ajouter.
     */
    public void addArme(Arme arme) {
        armes.add(arme);
    }

    /**
     * Ajoute un personnage à la scène.
     * 
     * @param perso Le personnage à ajouter.
     */
    public void addPerso(PersonnageDeBase perso) {
        personnages.add(perso);
    }

    /**
     * Ajoute un objet à la scène.
     * 
     * @param objet L'objet à ajouter.
     */
    public void addObjets(Objet objet) {
        objets.add(objet);
    }

    /**
     * Définit la liste des objets présents sur la scène.
     * 
     * @param objets Le tableau d'objets à définir.
     */
    public void setObjets(Objet[] objets) {
        for (Objet o : objets)
            this.objets.add(o);
    }

    /**
     * Obtient l'ID du Node.
     * 
     * @return L'ID du Node.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient la description de la situation.
     * @return La description de la situation.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtient la liste des personnages présents sur la scène.
     * 
     * @return La liste des personnages présents sur la scène.
     */
    public List<PersonnageDeBase> getPersonnages() {
        return this.personnages;
    }

    /**
     * Définit l'action associée au Node.
     * 
     * @param action Le numéro d'action à définir.
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * Obtient l'action associée au Node.
     * 
     * @return Le numéro d'action associé au Node.
     */
    public int getAction() {
        return action;
    }

    /**
     * Définit le nom du fichier pour un SoundNode.
     * @param fileName Le nom du fichier son.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Obtient le nom du fichier pour un SoundNode.
     * @return Le nom du fichier son associé au Node.
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Définit si le Node génère un son.
     */
    public void setIsSoundNode() {
        isSoundNode = true;
    }

    /**
     * Vérifie si le Node génère un son.
     * @return Vrai si le Node génère un son, sinon faux.
     */
    public boolean getIsSoundNode() {
        return isSoundNode;
    }

    /**
     * Exécute l'action associée au Node.
     */
    public void callAction() {
        Heros heros = null;
        if (this.personnages.get(0) == null)
            System.out.println("Il n'y a pas d'héros dans la scène.");
        heros = (Heros) this.personnages.get(0);

        // Exécute différentes actions en fonction du numéro d'action
        if (action == 2) {
            heros.addObjet(objets.get(0));
        }
        if (action == 3) {
            heros.setArme(armes.get(0));
        }
        if (action == 5) {
            int vies = heros.getVie();
            heros.setVie(vies - 1);
        }
        if (action == 6) {
            int vies = heros.getVie();
            heros.setVie(vies + 2);
        }
        if (action == 7) {
            // Voir Execute SoundNode
        }

        return;
    }

    /**
     * Affiche la description de la situation.
     */
    @Override
    public void display() {
        System.out.println(this.description);
    }

    /**
     * Méthode abstraite pour choisir le Node suivant.
     *
     * @return Le Node suivant.
     */
    abstract public Node chooseNext();

    /**
     * Convertit le Node en une représentation textuelle.
     *
     * @return Une représentation textuelle du Node.
     */
    @Override
    public String toString() {
        this.display();
        return "";
    }
}
