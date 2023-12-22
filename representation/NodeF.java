package representation;

import java.util.Scanner;
import Autres.MenuInter;

/**
 * La classe NodeF contient des méthodes utilitaires pour manipuler et exécuter des nodes dans le contexte du jeu.
 */
public class NodeF {

    /**
     * Lie un InnerNode à un autre Node.
     * 
     * @param I1 L'InnerNode source.
     * @param I2 Le Node à lier.
     */
    public static void Link(InnerNode I1, Node I2) {
        I1.setNodes(I2);
    }

    /**
     * Lie un InnerNode à un autre Node avec un numéro spécifié.
     * 
     * @param I1      L'InnerNode source.
     * @param numero  Le numéro de liaison.
     * @param I2      Le Node à lier.
     */
    public static void Link(InnerNode I1, int numero, Node I2) {
        I1.setNodes(numero, I2);
    }

    /**
     * Lie un InnerNode à plusieurs Nodes avec des numéros spécifiés.
     * 
     * @param I1        L'InnerNode source.
     * @param numeros   Les numéros de liaison.
     * @param allI      Les Nodes à lier.
     */
    public static void Link(InnerNode I1, int[] numeros, Node[] allI) {
        for (int i = 0; i < numeros.length; i++) {
            I1.setNodes(numeros[i], allI[i]);
        }
    }

    /**
     * Met à jour un Node en exécutant son action.
     * 
     * @param node Le Node à mettre à jour.
     */
    public static void mettreAjour(Node node) {
        if (node.getAction() == 0) {
            return;
        }
        if (node instanceof DecisionNode) {
            DecisionNode dnode = (DecisionNode) node;
            dnode.callAction();
        } else {
            node.callAction();
        }
        
        if (node instanceof InnerNode) {
            InnerNode n = (InnerNode) node;
            if (n.getCondition() != 3 && (n.getAction() != 0 || n.getCondition() != 0))
                System.out.println(node.personnages.get(0));
        }
        return;
    }

    /**
     * Exécute un Node donné et ses actions.
     * 
     * @param node Le Node à exécuter.
     * @param sc   Le Scanner pour les entrées utilisateur.
     */
    public static void Execute(Node node, Scanner sc) {
        if (node == null) {
            System.out.println("FIN !!");
            return;
        }
        if (!(node.getDescription().equals("null")))
            if (node.getIsSoundNode()) {
                SoundNode sn = new SoundNode(node, node.getFileName(), 0);
                sn.display();
            } else
                node.display();
        mettreAjour(node);
        if (!(node instanceof DecisionNode || node.getDescription().equals("null"))) {
            String s = sc.nextLine();
            if (s.equals("s")) {
                MenuInter.sauvegarder(node);
            }
        }

        if (node instanceof TerminalNode) {
            Execute((Node) null, sc);
            return;
        }
        Execute(node.chooseNext(), sc);
    }
}
