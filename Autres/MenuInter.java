package Autres;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

import representation.*;
import univers.Combattant;
import univers.Element;
import univers.Armes.Arme;
import univers.Interfaces.Divin;
import univers.Personnages.Demon;
import univers.Personnages.Divinite;
import univers.Personnages.Heros;
/**
 * La classe MenuInter représente un menu interactif pour un jeu. 
 * Elle permet de sauvegarder et charger des parties, de lancer une nouvelle partie
 * ou de reprendre une partie sauvegardée, et de gérer les combats entre le héros
 * et des adversaires.
 * 
 * Les méthodes principales comprennent sauvegarder, afficher le menu, charger une sauvegarde,
 * et gérer les combats.
 * 
 */

public class MenuInter{
		/**
	 * Sauvegarde un nœud (Node) dans un fichier en utilisant la sérialisation.
	 * L'utilisateur est invité à entrer le nom de la sauvegarde.
	 * Entrez "s" dans le termine lorsque vous êtes pas sur une une décision pour pouvoir sauvegarder la partie.
	 * @param node Le nœud à sauvegarder.
	 */

	public static void sauvegarder(Node node){
		try{
			Scanner sc = new Scanner(System.in);
			System.out.println("\nNom de la sauvegarde :");
			
			String saveName = sc.nextLine();
			System.out.println("\nPARTIE SAUVEGARDEE\n");
			ObjectOutputStream oos;
			oos = new ObjectOutputStream( new FileOutputStream(new File("Autres/Sauvegarde/"+ saveName + ".txt")));	
			oos.writeObject(node);
			oos.close();
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}

	/**
	 * Affiche un menu interactif permettant à l'utilisateur de choisir entre
	 * lancer une nouvelle partie, reprendre une sauvegarde ou quitter le programme.
	 * 
	 * @param nodeMap Une carte de nœuds associés à des chaînes de caractères.
	 */
	public static void Display(Map<String,Node> nodeMap) {
        Scanner sc = new Scanner(System.in);
        int rep;
        do {
            System.out.println("-------------");
            System.out.println("Voici le Menu");
            System.out.println("-------------");
            System.out.println("Tapez 1 pour quitter le programme");
            System.out.println("Tapez 2 pour lancer une nouvelle partie");
            System.out.println("Tapez 3 pour reprendre une sauvegarde");
            System.out.println();
            System.out.println("Tapez votre reponse");
            rep = Lecture.reponseInt(3);
            if (rep == 1) {
                System.out.println("Fin du programme.");
            } else if (rep == 2) {
				Introduction((Heros) nodeMap.get("I1").getPersonnages().get(0));
				NodeF.Execute(nodeMap.get("I1"),sc);
				break;
            } else if (rep == 3) {
				try{
					ChargerSauvegarde(nodeMap);
					break;
				}
				catch(IllegalArgumentException e){System.out.println("Aucune partie sauvegardée.");}
				catch(Exception e){System.out.println("ERREUR : Chemin de la  sauvegarde introuvable");}
				
            } else {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }while(rep != 1);
            
    }

	 /**
     * Charge une sauvegarde existante à partir du répertoire "Autres/Sauvegarde".
     * L'utilisateur sélectionne une sauvegarde parmi celles disponibles.
     * 
     * @param nodeMap Une carte de nœuds associés à des chaînes de caractères.
     */

	public static void ChargerSauvegarde(Map<String,Node> nodeMap) throws IllegalArgumentException,Exception{
		Scanner sc = new Scanner(System.in);
		// Afficher les sauvegardes
		String cheminRepertoire = "Autres/Sauvegarde";
		File repertoire = new File(cheminRepertoire);
		String[] sauvegardes = repertoire.list();
		if(sauvegardes.length == 0){
			throw new IllegalArgumentException();
			}
		System.out.println("\nNom des sauvegardes :");
		int i = 1;
		for (String element : sauvegardes) {
			System.out.println(i+"." + element);
			i+=1;
		}
		System.out.print("\nChoix de la sauvegarde : ");
		int numSauvegarde = Lecture.reponseInt(sauvegardes.length);
		System.out.println("\n");

		//Charger la sauvegarde
		try{
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream(new File("Autres/Sauvegarde/"+sauvegardes[numSauvegarde-1]))) ;
			Node node = (Node) ois.readObject();
			NodeF.Execute(node,sc);
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
     * Gère un combat entre un héros et un adversaire (Combattant).
	 * Système semblable au PIERRE-FEUILLE-CISEAU:
	 * Attaque gagne contre BriseGarde | BriseGarde gagne contre Défense | Défense gagne contre Attaque.
	 * Les actions du joueur sont déterminées par les décisions du joueur.
     * Les actions de l'adversaire sont déterminéees de manière aléatoire.
     * L'héros possède des chances de coups critiques définies selon la puissance de l'arme (somme des stats /100 )
	 * Si coup critique, alors l'adversaire perd deux vies au lieu d'un
	 * Si Element arme gagne contre Element adversaire alors , toujours coup critique
	 * 
     * @param heros Le héros participant au combat.
     * @param adversaire L'adversaire avec lequel le héros combat.
     * @return true si le héros gagne le combat, false sinon.
     */

	public static boolean combat(Heros heros, Combattant adversaire){
		Random random = new Random();
		String[] choix = new String[] {"Attaque","Brise-Garde","Defense"};
		double coutCritique = (double) heros.getArme().getPuissance() / 100 ;
		//System.out.println("Coup critique : " + coutCritique);
		double nombreAleatoire;
		Scanner sc = new Scanner(System.in);
		int nbAlea;
		int rep;
		int nbVie=heros.getVie();
		int nbVie_ad=adversaire.getVie();
		int value;
		System.out.println(adversaire +"\n");
		boolean isDivin = false;
		boolean isDemon = false;
		boolean hasCompetence = true;
		Double coeffDivin = 0.;
		Double coeffDemon = 0.;
		if (adversaire instanceof Divin){
			coeffDivin = ((Divinite) adversaire).getCoeffDivin();
			isDivin = true;
		}
		if (adversaire instanceof Demon){
			coeffDemon = 0.3;
			isDemon = true;
		}

		Element[] ElemAd = adversaire.getElems();
		Element ElemArme = heros.getArme().getElem();
		System.out.println("COMBAT :");
		do {
			value = 0;
			//System.out.println("\nChoississez");
			System.out.println("Tapez 1 pour : Attaque");
			System.out.println("Tapez 2 pour : Brise-Garde");
			System.out.print("Tapez 3 pour : Defense");
			rep = Lecture.reponseInt(3);
			rep -=1;
			nbAlea= random.nextInt(3);
			nombreAleatoire = random.nextDouble();
			System.out.println("L'adversaire choisi son attaque ...\n");
			try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();};

			if ((rep==0 && nbAlea==1) || (rep == 1 && nbAlea==2) ||(rep ==2 && nbAlea==0)) {
		        System.out.println("Votre adversaire a choisi "+choix[nbAlea]+" : GAGNE");

		        if (coutCritique < nombreAleatoire || Arme.CycleElems(ElemArme,ElemAd) ) {
					//System.out.println("isElem : " + Arme.CycleElems(ElemArme,ElemAd));
		        	System.out.println("Vous avez fait un coup critique : l'adversaire perd deux vies");
					if (nbVie_ad == 1)
		        		value = 1;
					else
						value = 2;
		        }else {
		        	value = 1;
		        }
		        nbVie_ad = nbVie_ad - value;
			}else if ((rep==0 && nbAlea==2) || (rep == 1 && nbAlea==0) || (rep==2 && nbAlea==1)) {
				System.out.println("Votre adversaire a choisi "+choix[nbAlea]+" : PERDU");
				nbVie=nbVie - 1;
			
			}else if (rep==nbAlea) {
				System.out.println("Votre adversaire a choisi "+choix[nbAlea]+" : EGALITE");
		        
			}
			if(isDivin && nombreAleatoire<coeffDivin){
				System.out.println("La divinité a utilisé sa compétence. Il a gagné une vie.");
				nbVie_ad +=1;
			}
			if(isDemon && nombreAleatoire<coeffDemon){
				System.out.println("Le Demon a utilisé sa compétence. Il est invincible pendant ce tour.");
				nbVie_ad +=value;
			}
			
			System.out.println("Score (en terme de vie) : ");
	        System.out.print("Vous :"+nbVie +"\t");
	        System.out.println("\tAdversaire :"+nbVie_ad +"\n");
		
		}while ((nbVie >0) && (nbVie_ad > 0));
		
		if (nbVie <= 0) {
			return false;
		}else {
			return true;
		}
	}	
	
   /**
	 * Génère un texte d'introduction pour le joueur et permet de choisir un nom pour le héros.
	 * Demande également au joueur de choisir la difficulté du jeu.
	 * 
	 * @param heros Le héros du joueur.
	 */
    public static void Introduction(Heros heros){
		Scanner sc = new Scanner(System.in);
		System.out.println("Pour sauvegarder la partie, entrez le mot \"s\" au cours du jeu dans le terminal ( lorsque ce n'est pas une DECISION)");
        sc.nextLine();
		System.out.print("Choisissez le nom de votre Héros : ");
        String Nom = sc.next();
        heros.setNom(Nom);
        sc.nextLine();
        System.out.println("Salut ! Tu es " +heros.getNom());
        sc.nextLine();
        System.out.println("Quelle difficulte veux tu choisir :\n1.Super Facile \n2.Facile (recommandé)\n3.Moyen\n4.Difficile");
        int v = Lecture.reponseInt(4);
        if (v == 1)
            heros.setVie(8);
        if (v == 2)
            heros.setVie(5);
        if (v == 3)
            heros.setVie(4);
        if (v == 4)
            heros.setVie(3);

        
        System.out.print("Es tu prêt à commencer l'aventure ??");
        sc.nextLine();
        System.out.print("Super !!");
        sc.nextLine();

    }
}