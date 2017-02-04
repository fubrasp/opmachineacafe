package util;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import machineCafe.Boisson;
import machineCafe.Ingredient;
import machineCafe.Machine;

public class Tool {
	public Machine initialise(){
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
		
		return m1;
	}
	
	public Machine initialiserAvecDeuxBoissons(){
		Machine m=this.initialise();
		m.getBoissons().remove(m.getBoissons().size()-1);
		return m;
	}
	
	public Machine epuiserStock(ArrayList<Integer> type){
		Machine m=this.initialise();
		int [] stk= m.getStock();
		for (Integer integer : type) {
			stk[integer]=0;
		}
		m.setStock(stk);
		return m;
	}
	
	
	public boolean contient(String nomBoisson, Machine m){
		boolean contient=false;
		for (Boisson crt : m.getBoissons()) {
			if(crt.getNom().equals(nomBoisson)){
				contient=true;
			}
		}
		return contient;
	}
	
	public String nextString() {
		return UUID.randomUUID().toString();	
	}
	
	public int nextInt(){
		Random r = new Random();
		return  r.nextInt(6 - 0) + 1;
	}

	public int nextInt(int max){
		Random r = new Random();
		return  r.nextInt(max - 0) + 1;
	}
	
	

	public Boisson randomBoisson(Machine m){
		int nombreActuelBoissons = m.getBoissons().size();
		Random r = new Random();
		int indexAleatoire = r.nextInt(nombreActuelBoissons - 0) + 0;
		return m.getBoissons().get(indexAleatoire);
	}
}
