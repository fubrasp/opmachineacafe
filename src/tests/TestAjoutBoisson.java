package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import machineCafe.Boisson;
import machineCafe.Ingredient;
import machineCafe.Machine;
import util.Tool;

public class TestAjoutBoisson {

private Machine m;
private Tool t;
	
	@Before
	public void preprocess() {
		this.t = new Tool();
		this.m=t.initialise();
	}
	
	@Test
	public void testAjoutBoissonCasNominal() {
		this.m=t.initialiserAvecDeuxBoissons();
		int nombreActuelBoissons = this.m.getBoissons().size();
		int nombreAttenduBoissons = nombreActuelBoissons+1;

		Boisson b = new Boisson("sake", 100, new ArrayList<Ingredient>(), 1, 1, 1, 1);
		this.m.ajouterBoisson(b);
		nombreActuelBoissons=this.m.getBoissons().size();

		assertEquals("La boisson n'a pas ete ajoutee", nombreAttenduBoissons, nombreActuelBoissons);
		
		boolean contient=t.contient(b.getNom(), this.m);
		assertTrue("La (bonne) boisson n'a pas ete ajoutee", contient);		
	}
	
	@Test
	public void testAjoutBoissonSuperieurLimite() {
		int nombreActuelBoissons = this.m.getBoissons().size();

		Boisson b = new Boisson("sake", 100, new ArrayList<Ingredient>(), 1, 1, 1, 1);
		boolean result=this.m.ajouterBoisson(b);
		nombreActuelBoissons = this.m.getBoissons().size();
		assertFalse(result);
		assertEquals("La boisson a pas ete ajoutee", nombreActuelBoissons, nombreActuelBoissons);		
		boolean contient=t.contient(b.getNom(), this.m);
		assertFalse("La (bonne) boisson a ete ajoutee", contient);	
	}

	@Test
	public void testAjoutBoissonIngredientQuantiteNulles() {
		this.m=t.initialiserAvecDeuxBoissons();
		int nombreActuelBoissons = this.m.getBoissons().size();
		
		Boisson b = new Boisson("sake", 100, new ArrayList<Ingredient>(), 0, 0, 0, 0);
		boolean result=this.m.ajouterBoisson(b);
		nombreActuelBoissons = this.m.getBoissons().size();
		System.out.println(result);
		assertFalse(result);
		assertEquals("La boisson a ete ajoutee", nombreActuelBoissons, nombreActuelBoissons);		
		boolean contient=t.contient(b.getNom(), this.m);
		assertFalse("La (bonne) boisson a ete ajoutee", contient);	
	}

}
