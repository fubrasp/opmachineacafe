package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import machineCafe.Boisson;
import machineCafe.Machine;
import util.Tool;

public class TestAjoutIngredients {

	private Machine m;
	private Tool t;
	
	@Before
	public void preprocess() {
		this.t = new Tool();
		this.m=t.initialise();
	}
	
	@Test
	public void testAjouterIngredientCasNominal() {
		Random r = new Random();
		int [] stock = this.m.getStock();
		int typeRandom = r.nextInt(stock.length - 0)+0;;
		int quantiteDeDepart = stock[typeRandom];
		int quantiteRandom = r.nextInt(100 - 0)+1;;
		
		this.m.ajouterUnIngrédient(typeRandom, quantiteRandom);
		assertEquals("l'ingredient n'a pas ete ajoute correctement", quantiteDeDepart+quantiteRandom,  stock[typeRandom]);
		
	}
	
	@Test
	public void testAjouterIngredientNegatifOuNul() {
		Random r = new Random();
		int [] stock = this.m.getStock();
		int typeRandom = r.nextInt(stock.length - 0)+0;;
		
		assertFalse(this.m.ajouterUnIngrédient(typeRandom, -10));
		assertFalse(this.m.ajouterUnIngrédient(typeRandom, 0));
	}
}
