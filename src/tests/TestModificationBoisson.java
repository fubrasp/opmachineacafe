package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import machineCafe.Boisson;
import machineCafe.Machine;
import util.Tool;

public class TestModificationBoisson {

	private Machine m;
	private Tool t;
	
	@Before
	public void preprocess() {
		this.t = new Tool();
		this.m=t.initialise();
	}
	
	@Test
	public void testModificationNomBoissonCasNominal() {
		String randomString = t.nextString();
		Boisson b = t.randomBoisson(this.m);
		String nomBoisson = b.getNom();
		this.m.modifierBoisson(nomBoisson, "nom", randomString, 0, 0);	
		assertEquals("Le nom de la boisson n'a pas ete correctement modifie", randomString, b.getNom());
	}

	@Test
	public void testModificationPrixBoissonCasNominal() {
		int randomInt = t.nextInt();
		Boisson b = t.randomBoisson(this.m);
		String nomBoisson = b.getNom();
		this.m.modifierBoisson(nomBoisson, "prix", String.valueOf(randomInt), 0, 0);
		assertEquals("Le prix de la boisson n'a pas ete correctement modifie", randomInt, b.getPrix());
	}
	
	@Test
	public void testModificationPrixBoissonCasNégatif() {
		int nombreNegatif=-15;
		Boisson b = t.randomBoisson(this.m);
		int previousPrice=b.getPrix();
		String nomBoisson = b.getNom();
		boolean resultat=this.m.modifierBoisson(nomBoisson, "prix", String.valueOf(nombreNegatif), 0, 0);
		assertFalse("La fonction de modification devrait retourner nul le cas d'un prix negatif", resultat);
		assertEquals("Le prix de la boisson n'aurait pas du etre modifie", previousPrice, b.getPrix());
	}
	
	//modification ingredients non testable ==> necessite de refractor du code
	@Test
	public void testModificationBoissonQuantiteIngredient() {
		//int randomInt = t.nextInt();
		int randomIntIngredient = t.nextInt(3);
		int randomQte = t.nextInt(20);

		Boisson b = t.randomBoisson(this.m);
		String nomBoisson = b.getNom();
		this.m.modifierBoisson(nomBoisson, "ingredients", String.valueOf(randomIntIngredient), randomIntIngredient, randomQte);
		switch(randomIntIngredient){
		case 0:
			assertEquals("La quantite lie a l'ingredient n'a pas ete correctement modifie", randomQte, this.m.getBoisson(b.getNom()).getCafeQuantite());
			break;
		case 1:
			assertEquals("La quantite lie a l'ingredient n'a pas ete correctement modifie", randomQte, this.m.getBoisson(b.getNom()).getLaitQuantite());
			break;
		case 2:
			assertEquals("La quantite lie a l'ingredient n'a pas ete correctement modifie", randomQte, this.m.getBoisson(b.getNom()).getChocolatQuantite());
			break;
		case 3:
			assertEquals("La quantite lie a l'ingredient n'a pas ete correctement modifie", randomQte, this.m.getBoisson(b.getNom()).getSucreQuantite());
			break;	
		}
	}


}
