package representation;

import java.util.List;

import Autres.MenuInter;
import univers.Combattant;
import univers.Armes.Arc;
import univers.Armes.Arme;
import univers.Objets.Objet;
import univers.Personnages.Heros;

import java.util.ArrayList;

/**
 * La classe InnerNode représente un Node qui contient des Nodes enfants et peut avoir une condition pour choisir le prochain Node.
 * Elle étend la classe abstraite Node.
 */
public class InnerNode extends Node {

    /** Liste des Nodes enfants. */
    protected List<Node> nodes = new ArrayList<Node>();

    /** Condition pour choisir le prochain Node. */
    private int condition = 0;

    /** Statistiques minimales pour la condition. */
    private int[] StatsMin;

    /**
     * Constructeur par défaut.
     */
    public InnerNode() {
        super(null);
    }

    /**
     * Constructeur avec une condition spécifiée.
     * 
     * @param condition La condition pour choisir le prochain Node.
     */
    public InnerNode(int condition) {
        this();
        this.condition = condition;
    }

    /**
     * Constructeur avec une description spécifiée.
     * 
     * @param description La description de la situation.
     */
    public InnerNode(String description) {
        super(description);
    }

    /**
     * Constructeur avec une description et une condition spécifiées.
     * 
     * @param description La description de la situation.
     * @param condition   La condition pour choisir le prochain Node.
     */
    public InnerNode(String description, int condition) {
        super(description);
        this.condition = condition;
    }

    /**
     * Constructeur avec une description et des Nodes enfants spécifiés.
     * 
     * @param description La description de la situation.
     * @param n1          Premier Node enfant.
     * @param n2          Deuxième Node enfant.
     * @param n3          Troisième Node enfant.
     * @param n4          Quatrième Node enfant.
     */
    public InnerNode(String description, Node n1, Node n2, Node n3, Node n4) {
        super(description);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
    }

    /**
     * Obtient la liste des Nodes enfants.
     * 
     * @return La liste des Nodes enfants.
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Ajoute un Node à la liste des Nodes enfants.
     * 
     * @param n1 Le Node à ajouter.
     */
    public void setNodes(Node n1) {
        this.nodes.add(n1);
    }

    /**
     * Ajoute plusieurs Nodes à la liste des Nodes enfants.
     * 
     * @param nodes Le tableau de Nodes à ajouter.
     */
    public void setNodes(Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            this.nodes.add(nodes[i]);
        }
    }

    /**
     * Lie un Node à un numéro spécifié dans la liste des Nodes enfants.
     * 
     * @param numero Le numéro de liaison.
     * @param node   Le Node à lier.
     */
    public void setNodes(int numero, Node node) {
        int n = nodes.size();
        if (n < numero) {
            for (int i = 0; i < numero - n; i++) {
                nodes.add(null);
            }
        }
        nodes.set(numero - 1, node);
    }

    /**
     * Définit la condition pour choisir le prochain Node.
     * 
     * @param condition La nouvelle condition.
     */
    public void setCondition(int condition) {
        this.condition = condition;
    }

    /**
     * Définit les statistiques minimales pour la condition.
     * 
     * @param StatsMin Les statistiques minimales.
     */
    public void setStatsMin(int[] StatsMin) {
        this.StatsMin = StatsMin;
    }

    /**
     * Obtient la condition pour choisir le prochain Node.
     * 
     * @return La condition pour choisir le prochain Node.
     */
    public int getCondition() {
        return this.condition;
    }

    /**
     * Détermine la condition suivante en fonction des statistiques du Héros.
     * 
     * @return 1 si la condition est satisfaite, 2 sinon.
     */
    public int NextCondition() {
        Heros heros = (Heros) this.personnages.get(0);

        // Condition basée sur les statistiques de l'arme du Héros
        if (this.condition == 1) {
            if (heros.getArme() == null) {
                System.out.println("ERREUR : Le héros n'a pas d'arme.");
            }
            Arme HerosArme = heros.getArme();
            int attaqueMin = StatsMin[0];
            int porteeMin = StatsMin[1];
            int precMin = StatsMin[2];

            if (HerosArme.getPrecision() >= precMin && HerosArme.getAttaque() >= attaqueMin
                    && HerosArme.getPortee() >= porteeMin)
                return 1;
            else
                return 2;
        }

        // Condition basée sur la possession d'un objet spécifique
        else if (this.condition == 2) {
            if (heros.hasObjet(this.objets.get(0)))
                return 1;
            else
                return 2;
        }

        else if (this.condition == 3) {
            int vies = heros.getVie();
            if (vies > 0) {
                System.out.print("Vous avez perdu une vie.");
                System.out.println(" VIES : " + (vies) + "\n");
                return 1;
            } else {
                return 2;
            }
        }

        
        if (this.condition == 4) {
            boolean victoire = MenuInter.combat(heros, (Combattant) personnages.get(1));
            if (victoire)
                return 1;
            else
                return 2;
        }

        if (this.condition == 5) { // Contre les combattants volants
            if (heros.getArme() instanceof Arc || heros.hasObjet(Objet.AILES_ICARE))
                return 1;
            else
                return 2;
        }
        return -2;
    }

    @Override
    public Node chooseNext() {
        if (this.condition == 0) {
            if (this.nodes.isEmpty()) {
                return null;
            } else {
                return this.nodes.get(0);
            }
        } else {
            int numNextNode = this.NextCondition();
            return this.nodes.get(numNextNode - 1);
        }
    }
}

