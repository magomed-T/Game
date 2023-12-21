package Autres;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

import representation.*;
import univers.Combattant;
import univers.Element;
import univers.PersonnageDeBase;
import univers.Armes.Arme;
import univers.Interfaces.Divin;
import univers.Personnages.Divinite;
import univers.Personnages.Monstre;
import univers.Personnages.Mortels.Heros;

public class MenuInter{
	public static void sauvegarder(Node node){
		try{
			Scanner sc = new Scanner(System.in);
			String cheminRepertoire = "Autres/Sauvegarde";
			//File repertoire = new File(cheminRepertoire);
			//String[] sauvegardes = repertoire.list();
			System.out.println("\nNom des sauvegardes :");
			
			String saveName = sc.nextLine();
			System.out.println("\nPARTIE SAUVEGARDEE\n");
			ObjectOutputStream oos;
			oos = new ObjectOutputStream( new FileOutputStream(new File("Autres/Sauvegarde/sauvegarde"+ saveName + ".txt")));	
			oos.writeObject(node);
			oos.close();
		}
		catch(Exception e ){
			e.printStackTrace();
		}
	}

	public static void Display(Map<String,Node> nodeMap) {
        Scanner sc = new Scanner(System.in);
        int rep;
        do {
            System.out.println("-------------");
            System.out.println("Voici le Menu");
            System.out.println("-------------");
            System.out.println("Tapez 0 pour quitter le programme");
            System.out.println("Tapez 1 pour lancer une nouvelle partie");
            System.out.println("Tapez 2 pour reprendre une sauvegarde");
            System.out.println();
            System.out.println("Tapez votre reponse");
            rep = sc.nextInt();
            if (rep == 0) {
                System.out.println("Fin du programme.");
            } else if (rep == 1) {
				Introduction((Heros) nodeMap.get("I1").getPersonnages().get(0));
                NodeF.Execute(nodeMap.get("I98"),sc);
				break;
            } else if (rep == 2) {
                ChargerSauvegarde(nodeMap);
				break;
            } else {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }while(rep != 0);
            
    }

	public static void ChargerSauvegarde(Map<String,Node> nodeMap){
		Scanner sc = new Scanner(System.in);

		// Afficher les sauvegardes
		String cheminRepertoire = "Autres/Sauvegarde";
		File repertoire = new File(cheminRepertoire);
		System.out.println("\nNom des sauvegardes :");
		int i = 1;
		String[] sauvegardes = repertoire.list();
		for (String element : sauvegardes) {
			System.out.println(i+"." + element);
			i+=1;
		}
		System.out.print("\nChoix de la sauvegarde : ");
		int numSauvegarde = sc.nextInt();
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
		System.out.println(adversaire +"\n");
		boolean isDivin = false;
		Double coeffDivin = 0.;
		if (adversaire instanceof Divin){
			coeffDivin = ((Divinite) adversaire).getCoeffDivin();
			isDivin = true;
		}
		Element[] ElemAd = adversaire.getElems();
		Element ElemArme = heros.getArme().getElem();
		System.out.println("COMBAT :");
		do {
			//System.out.println("\nChoississez");
			System.out.println("Tapez 1 pour : Attaque");
			System.out.println("Tapez 2 pour : Brise-Garde");
			System.out.print("Tapez 3 pour : Defense");
			nbAlea= random.nextInt(3);
			nombreAleatoire = random.nextDouble();
			rep = Lecture.reponseInt(3);
			rep -=1;
			System.out.println("L'adversaire choisi son attaque ...\n");
			try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();};

			if ((rep==0 && nbAlea==1) || (rep == 1 && nbAlea==2) ||(rep ==2 && nbAlea==0)) {
		        System.out.println("Votre adversaire a choisi "+choix[nbAlea]+" :vous avez gagné");

		        if (coutCritique < nombreAleatoire || Arme.CycleElems(ElemArme,ElemAd) ) {
					//System.out.println("isElem : " + Arme.CycleElems(ElemArme,ElemAd));
		        	System.out.println("Vous avez fait un coup critique : l'adversaire perd deux vies");
					if (nbVie_ad == 1)
		        		nbVie_ad=nbVie_ad - 1;
					else
						nbVie_ad=nbVie_ad - 2;
		        }else {
		        	nbVie_ad=nbVie_ad - 1;
		        }
		       
		        
			}else if ((rep==0 && nbAlea==2) || (rep == 1 && nbAlea==0) || (rep==2 && nbAlea==1)) {
				System.out.println("Votre adversaire a choisi "+choix[nbAlea]+" :vous avez perdu");
				nbVie=nbVie - 1;
			
			}else if (rep==nbAlea) {
				System.out.println("Egalité");
		        
			}
			if(isDivin && nombreAleatoire<coeffDivin){
				System.out.println("La divinité a utilisé sa compétence. Il a gagné une vie.");
				nbVie_ad +=1;
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
     * Genere un texte d'introduction pour le joueur et donne un nom au Heros.
     * 
     * @param heros Heros du personnage
     * @param sc Scanner
     */

    public static void Introduction(Heros heros){
		Scanner sc = new Scanner(System.in);
        System.out.print("Choisissez le nom de votre Héros : ");
        String Nom = sc.next();
        heros.setNom(Nom);
        sc.nextLine();
        System.out.println("Salut ! Tu es " +heros.getNom());
        sc.nextLine();
        System.out.println("Quelle difficulte veux tu choisir :\n1.Facile (recommandé)\n2.Moyen\n3.Difficile\n4.Realiste (Deconseille)");
        int v = Lecture.reponseInt(4);
        if (v == 1)
            heros.setVie(5);
        if (v == 2)
            heros.setVie(4);
        if (v == 3)
            heros.setVie(3);
        if (v == 4)
            heros.setVie(1);

        
        System.out.print("Es tu prêt à commencer l'aventure ??");
        sc.nextLine();
        System.out.print("Super !!");
        sc.nextLine();

    }
}