package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import machineCafe.Boisson;
import machineCafe.Machine;
import util.Tool;

public class TestAchatBoisson {

	private Machine m;
	private Tool t;
	
	@Before
	public void preprocess() {
		this.t = new Tool();
		this.m=t.initialise();
	}
	
	@Test
	public void testAchatBoissonCasNominal() {
		String [] valeurs = {"cafePur", "chocolat", "chocolatLait"};
		Random r = new Random();
		int randomValue=r.nextInt(valeurs.length - 0)+0;
		String nomBoisson=valeurs[randomValue];
		int stockDepart[] = m.getStock().clone(); 
		Boisson resultat=this.m.acheterBoisson(nomBoisson);
		assertEquals("Il ne s'agit pas de la bonne boisson", nomBoisson, resultat.getNom());
		switch (nomBoisson) {
		case "cafePur":
			assertEquals("Le stock de cafe n'a pas ete affecte par l'achat du cafe", stockDepart[Machine.CAFE]-resultat.getCafeQuantite(),this.m.getStock()[Machine.CAFE]);
			break;
		case "chocolat":
			assertEquals("Le stock de chocolat n'a pas ete affecte par l'achat d'un chocolat", stockDepart[Machine.CHOCOLAT]-resultat.getChocolatQuantite(),this.m.getStock()[Machine.CHOCOLAT]);
			break;
		
		case "chocolatLait":
			assertEquals("Le stock de chocolat n'a pas ete affecte par l'achat du chocolat au lait", stockDepart[Machine.CHOCOLAT]-resultat.getChocolatQuantite(),this.m.getStock()[Machine.CHOCOLAT]);
			assertEquals("Le stock de lait n'a pas ete affecte par l'achat du chocolat au lait", stockDepart[Machine.LAIT]-resultat.getLaitQuantite(),this.m.getStock()[Machine.LAIT]);
			break;
		}
	}
	
	@Test
	public void testAchatCafeStockEpuise() {
		ArrayList<Integer> stConcerne = new ArrayList<Integer>();
		stConcerne.add(Machine.CAFE);
		Machine testMachine=t.epuiserStock(stConcerne);
		Object resultat=testMachine.acheterBoisson("cafePur");
		assertNull(resultat);
	}
	
	@Test
	public void testAchatChocolatStockEpuise() {
		ArrayList<Integer> stConcerne = new ArrayList<Integer>();
		stConcerne.add(Machine.CHOCOLAT);
		Machine testMachine=t.epuiserStock(stConcerne);
		Object resultat=testMachine.acheterBoisson("chocolat");
		assertNull(resultat);
	}
	@Test
	public void testAchatChocolatLaitStockEpuise() {
		ArrayList<Integer> stConcerne = new ArrayList<Integer>();
		stConcerne.add(Machine.CHOCOLAT);
		stConcerne.add(Machine.LAIT);
		Machine testMachine=t.epuiserStock(stConcerne);
		Object resultat=testMachine.acheterBoisson("chocolatLait");
		assertNull(resultat);
	}

}
