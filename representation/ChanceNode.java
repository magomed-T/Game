package representation;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe ChanceNode représente un Node qui a plusieurs options de transition avec des probabilités associées.
 * Elle étend la classe InnerNode, héritant ainsi des fonctionnalités d'un Node avec des conditions.
 */
public class ChanceNode extends InnerNode {

    /** Liste des probabilités associées à chaque option de transition. La somme des probabilités doit être égale à 1. */
    private List<Double> proba = new ArrayList<>();

    /** Constantes statiques pour représenter différentes configurations de probabilités. */
    public static double[] AlmostSure = new double[]{1, 0};
    public static double[] VeryHighChance = new double[]{0.9, 0.1};
    public static double[] HighChance = new double[]{0.9, 0.1};
    public static double[] MiddleChance = new double[]{0.65, 0.35};
    public static double[] FiftyFifty = new double[]{0.5, 0.5};
    public static double[] LowChance = new double[]{0.2, 0.8};

    /**
     * Constructeur avec une description spécifiée.
     * 
     * @param description La description de la situation.
     */
    public ChanceNode(String description) {
        super(description);
    }

    /**
     * Définit les probabilités associées à chaque option de transition.
     * 
     * @param proba Un tableau de doubles représentant les probabilités.
     */
    public void setProba(double[] proba) {
        try {
            double somme = 0;
            for (double p : proba) {
                this.proba.add(p);
                somme += p;
            }
            if (somme != 1)
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("\nERREUR:\n(NodeId:" + this.getId() + "): La somme des probas ne vaut pas 1\n");
        }
    }

    /**
     * Choisi le prochain Node en fonction des probabilités associées à chaque option de transition.
     * 
     * @return Le prochain Node choisi aléatoirement en fonction des probabilités.
     */
    @Override
    public Node chooseNext() {
        Random rd = new Random();
        double rand = rd.nextDouble(); // Génère un nombre aléatoire entre 0 et 1

        double sommeProb = 0;
        for (int i = 0; i < proba.size(); i++) {
            sommeProb += proba.get(i);
            if (rand <= sommeProb) {
                return nodes.get(i);
            }
        }
        return null;
    }
}
