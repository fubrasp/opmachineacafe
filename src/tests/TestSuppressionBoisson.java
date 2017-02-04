package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import machineCafe.Boisson;
import machineCafe.Machine;
import util.Tool;

public class TestSuppressionBoisson {
	private Machine m;
	private Tool t;
	
	@Before
	public void preprocess() {
		this.t = new Tool();
		this.m=t.initialise();
	}
	
	@Test
	public void testSuppressionBoissonCasNominal() {
		int nombreActuelBoissons = this.m.getBoissons().size();
		Random r = new Random();
		int indexAleatoire = r.nextInt(nombreActuelBoissons - 0) + 0;
		
		Boisson b = this.m.getBoissons().get(indexAleatoire);
		
		String nomBoisson = b.getNom();
		this.m.supprimerBoisson(nomBoisson);
		
		int nombreAttenduBoissons = nombreActuelBoissons-1;
		nombreActuelBoissons=this.m.getBoissons().size();
		
		assertEquals("La boisson n'a pas ete supprimee", nombreAttenduBoissons, nombreActuelBoissons);
		
		boolean contient=t.contient(nomBoisson, this.m);
		assertFalse("La (bonne) boisson n'a pas ete supprimee", contient);		
	}
	
	@Test
	public void testSuppressionBoissonNonPresente() {
		boolean result = this.m.supprimerBoisson("argBsddqsdqs");
		assertFalse(result);
	}
}
