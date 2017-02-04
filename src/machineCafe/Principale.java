package machineCafe;

import java.util.ArrayList;
import java.util.Scanner;

public class Principale {
	public static void main(String [] args){
	
		ArrayList<Ingredient> ingredientsCafePur=new ArrayList<Ingredient>();
		ingredientsCafePur.add(new Ingredient("cafe", 1));
		
		ArrayList<Ingredient> ingredientsChocolat=new ArrayList<Ingredient>();
		ingredientsChocolat.add(new Ingredient("cafe", 1));
		
		ArrayList<Ingredient> ingredientsChocolatLait=new ArrayList<Ingredient>();
		ingredientsChocolatLait.add(new Ingredient("cafe", 1));
		
		Boisson cafePur = new Boisson("cafePur", 1, ingredientsCafePur, 1, 0, 0, 0);
		Boisson chocolat = new Boisson("chocolat", 1, ingredientsChocolat, 0, 1, 1, 0);
		Boisson chocolatLait = new Boisson("chocolatLait", 1, ingredientsChocolatLait, 0, 1, 1, 0);
		
		ArrayList<Boisson> boissons=new ArrayList<Boisson>();
		boissons.add(cafePur);
		boissons.add(chocolat);
		boissons.add(chocolatLait);
		
		int [] stock = new int[4];
		stock[0]=10;
		stock[1]=10;
		stock[2]=10;
		stock[3]=10;
		
		Machine m1 = new Machine(boissons, 100, stock);
		
		Scanner sc=new Scanner(System.in);
		String response="";
		while(response!="QUITTER"){
			System.out.println("Quitter pour quitter la machine");
			System.out.println("Votre choix: ?, 1/choisir une boisson 2/ afficher le stock machine 3/ajout d'une boisson /4 modifier une boisson /5 supprimer une boisson 6/ajouter un ingrédient ");
			response=sc.nextLine();
			if(response.equals("1")){
				
				System.out.println("Que désirez vous");
				System.out.println(m1.afficherBoissons());
				Scanner scC = new Scanner(System.in);
				String choix = scC.nextLine();
				Boisson boissonAchete= m1.acheterBoisson(choix);
				System.out.println("Votre boisson achetée choisie");
				System.out.println(boissonAchete.toString());
			//verified
			}else if(response.equals("2")){
				System.out.println(m1.verifierStock());
				//verified
			}else if(response.equals("3")){
				System.out.println("--Ajout d'une boisson de la machine--");
				Scanner scAjoutBoisson = new Scanner(System.in);
				System.out.println("Nom de la boisson à ajouter:");
				String nom = scAjoutBoisson.nextLine();
				System.out.println("Prix de la boisson à ajouter:");
				String prix = scAjoutBoisson.nextLine();
				
				System.out.println("-Ingrédients-");
				
				System.out.println("Quantité de café");
				String cafe = scAjoutBoisson.nextLine();
				System.out.println("Quantité de chocolat");
				String choco = scAjoutBoisson.nextLine();
				System.out.println("Quantité de lait");
				String lait = scAjoutBoisson.nextLine();
				System.out.println("Quantité de sucre");
				String sucre = scAjoutBoisson.nextLine();
					
				Boisson boissonAAjouter = new Boisson(nom, Integer.parseInt(prix), ingredientsChocolatLait, Integer.parseInt(cafe), Integer.parseInt(choco), Integer.parseInt(lait), Integer.parseInt(sucre));
				System.out.println(m1.afficherBoissons());
				boolean resultat=m1.ajouterBoisson(boissonAAjouter);
				if(resultat){
					System.out.println(m1.afficherBoissons());
					System.out.println("Boisson ajoutée:");
					System.out.println(m1.getDerniereBoisson());					
				}else{
					System.out.println("Ajout de la boisson impossible");
				}
			}else if(response.equals("4")){
				System.out.println("--Modification d'une boisson de la machine--");
				System.out.println("Liste des boissons de la machine:");
				System.out.println(m1.afficherBoissons());
				System.out.println("Séclectionner la boisson à modifier (son nom) ?");
				Scanner scNomBoissonAModfifier = new Scanner(System.in);
				String nomDeLaBoissonAMODIFIER=scNomBoissonAModfifier.nextLine();
				
				System.out.println("Que souhaitez-vous modifier ?");
				System.out.println("1/ nom");
				System.out.println("2/ prix");
				System.out.println("3/ ingredients");
				Scanner scModif=new Scanner(System.in);
				String field = scModif.nextLine();
				
				Object value;
				int ch=0;
				int qteObtenue=0;
				
				if(!field.equals("ingredients")){
					System.out.println("valeur à affecter ?");
					value = scModif.nextLine(); 	
				}else{
					value = null;
					System.out.println("Sélectionner un ingrédient");
					System.out.println(Machine.listerIngredients());
					Scanner sc1 = new Scanner(System.in);
					String choix = sc1.nextLine();
					ch = Integer.parseInt(choix);
					System.out.println("Sélectionner quantité ?");
					String qte = sc1.nextLine();
					qteObtenue = Integer.parseInt(qte);
					
				}
				
				m1.modifierBoisson(nomDeLaBoissonAMODIFIER, field, value, ch, qteObtenue);

				System.out.println("Boisson modifiée:");
			
				//verified
			}else if(response.equals("5")){
				System.out.println("--Suppression d'une boisson de la machine--");
				Scanner scBsupp=new Scanner(System.in);
				System.out.println("Quelle boisson voulez vous supprimer ?");
				System.out.println(m1.afficherBoissons());
				String boissonASupprimmer=scBsupp.nextLine();
				boolean resultSupp=m1.supprimerBoisson(boissonASupprimmer);
				if(resultSupp){
					System.out.println("Boisson supprimée avec succès");
				}else{
					System.out.println("Boisson inxesitante dans la machine");
				}
				//verified
			}else if(response.equals("6")){
				System.out.println("--Ajout d'ingrédient à la machine--");
				Scanner scQte = new Scanner(System.in);
				System.out.println("Type de l'ingrédient 1 ==> CAFE, 2 ==> LAIT, 3 ==> CHOCOLAT, 4 ==> SUCRE");
				String type = scQte.nextLine();
				System.out.println("Quantite de l'ingrédient");
				String qte = scQte.nextLine();
				System.out.println(m1.verifierStock());
				m1.ajouterUnIngrédient(Integer.parseInt(type), Integer.parseInt(qte));
				System.out.println(m1.verifierStock());
				System.out.println("ingrédient "+ type +"ajouté avec une quantité de "+qte);
			}
		}
		//modifier une boisson
		//ajouter un ingrédient
	}
}
