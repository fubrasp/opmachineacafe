package machineCafe;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sql.rowset.spi.SyncFactoryException;

public class Machine {
	private ArrayList<Boisson> boissons;
	private int argent;
	private int[] stock = new int[4];

	public static int CAFE = 0;
	public static int LAIT = 1;
	public static int CHOCOLAT = 2;
	public static int SUCRE = 3;

	public Machine(ArrayList<Boisson> boissons, int argent, int[] stock) {
		super();
		this.boissons = boissons;
		this.argent = argent;
		this.stock = stock;
	}

	public void setBoissons(ArrayList<Boisson> boissons) {
		this.boissons = boissons;
	}

	public boolean ajouterBoisson(Boisson b) {
		boolean resultat = false;
		if (this.boissons.size() < 3 && this.verifierIngredientsNonNullsOuNegatifs(b)) {
			this.boissons.add(b);
			resultat = true;
		} else {
			resultat = false;
		}
		return resultat;
	}

	public boolean supprimerBoisson(String nameBoisson) {
		boolean bool = false;
		// limit 3
		int indexToSupp = 4;
		for (Boisson boisson : boissons) {
			if (boisson.getNom().equals(nameBoisson)) {
				indexToSupp = this.boissons.indexOf(boisson);
			}
		}
		System.out.println(indexToSupp);
		if (indexToSupp != 4) {
			this.boissons.remove(this.boissons.get(indexToSupp));
			bool = true;
		} else {
			bool = false;
		}
		return bool;
	}

	public boolean modifierBoisson(String nomBoissonAModifier, String field, Object newValue, int ch, int qteObtenue) {
		boolean bool = false;
		// limit 3
		int indexToModify = 4;
		for (Boisson boisson : boissons) {
			if (boisson.getNom().equals(nomBoissonAModifier)) {
				indexToModify = this.boissons.indexOf(boisson);
			}
		}
		System.out.println(this.boissons.get(indexToModify).toString());
		if (indexToModify != 4) {
			// Boisson depart=this.boissons.get(indexToModify);
			Boisson b1 = this.boissons.get(indexToModify);

			switch (field) {
			case "nom":
				b1.setNom((String) newValue);
				this.boissons.set(indexToModify, b1);
				bool = true;
				break;
			case "prix":
				int prix=Integer.valueOf((String) newValue);
				if(prix>=0){
					b1.setPrix(prix);
					this.boissons.set(indexToModify, b1);
					bool = true;
				}else{
					bool = false;
				}
				break;
			case "ingredients":
				switch (ch) {
				case 0:
					b1.setCafeQuantite(qteObtenue);
					this.boissons.set(indexToModify, b1);
					break;
				case 1:
					b1.setLaitQuantite(qteObtenue);
					this.boissons.set(indexToModify, b1);
					break;
				case 2:
					b1.setChocolatQuantite(qteObtenue);
					this.boissons.set(indexToModify, b1);
					break;
				case 3:
					b1.setSucreQuantite(qteObtenue);
					this.boissons.set(indexToModify, b1);
					break;
				default:
					break;
				}

				break;
			default:
				bool = false;
				break;
			}
		} else {
			bool = false;
		}
		System.out.println(this.boissons.get(indexToModify).toString());

		return bool;

	}

	public Boisson acheterBoisson(String nomBoisson) {

		int indexToGet = 0;
		for (Boisson boisson : boissons) {
			if (boisson.getNom().equals(nomBoisson)) {
				indexToGet = this.boissons.indexOf(boisson);
			}
		}

		Boisson b = this.boissons.get(indexToGet);

		switch (nomBoisson) {
		case "cafePur":
			if (this.stock[this.CAFE] < b.getCafeQuantite()) {
				b = null;
			} else {
				this.stock[this.CAFE] -=  b.getCafeQuantite();
			}
			break;
		case "chocolat":
			if ((this.stock[this.CHOCOLAT] < b.getChocolatQuantite())) {
				b = null;
			} else {
				this.stock[this.CHOCOLAT] -= b.getChocolatQuantite();
				;
			}
			break;
		case "chocolatLait":
			if ((this.stock[this.CHOCOLAT] < b.getChocolatQuantite()) && (this.stock[this.LAIT] < b.getLaitQuantite())) {
				b = null;
			} else {
				this.stock[this.CHOCOLAT] -= b.getChocolatQuantite();
				;
				this.stock[this.LAIT] -= b.getLaitQuantite();
				;
			}
			break;
		}
		if (b != null) {
			this.argent += b.getPrix();
		}
		return b;
	}

	public String verifierStock() {
		String str = "";
		for (int i = 0; i < this.stock.length; i++) {
			switch (i) {
			case 0:
				str += "CAFE ==>" + this.stock[i] + "\n";
				break;
			case 1:
				str += "LAIT ==>" + this.stock[i] + "\n";
				break;
			case 2:
				str += "CHOCOLAT ==>" + this.stock[i] + "\n";
				break;
			case 3:
				str += "SUCRE ==>" + this.stock[i] + "\n";
				break;
			default:
				break;
			}
		}
		return str;
	}

	public String boissonsPresentes() {
		String str = "";
		for (Boisson boisson : this.boissons) {
			str += boisson.toString();
		}
		return str;
	}

	public Boisson getDerniereBoisson() {
		return this.boissons.get(this.boissons.size() - 1);
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}

	public int[] getStock() {
		return stock;
	}

	public void setStock(int[] stock) {
		this.stock = stock;
	}

	public ArrayList<Boisson> getBoissons() {
		return boissons;
	}

	public boolean ajouterUnIngrédient(int type, int quantite) {
		boolean resultat = false;
		if (quantite > 0) {
			switch (type) {
			case 0:
				this.stock[Machine.CAFE] += quantite;
				resultat = true;
				break;
			case 1:
				this.stock[Machine.LAIT] += quantite;
				resultat = true;
				break;
			case 2:
				this.stock[Machine.CHOCOLAT] += quantite;
				resultat = true;
				break;
			case 3:
				this.stock[Machine.SUCRE] += quantite;
				resultat = true;
				break;
			}
		} else {
			resultat = false;
		}
		return resultat;
	}

	public String afficherBoissons() {
		String str = "";
		for (Boisson boisson : boissons) {
			str += boisson.getNom() + "\n";
		}
		return str;
	}

	public static String listerIngredients() {
		return "cafe\nlait\nchocolat\nsucre\n";
	}

	private boolean verifierIngredientsNonNullsOuNegatifs(Boisson b) {
		int qteCafe = b.getCafeQuantite();
		int qteChocolat = b.getChocolatQuantite();
		int qteSucre = b.getSucreQuantite();
		int qteLait = b.getLaitQuantite();
		return ((qteCafe >= 0 && qteChocolat >= 0 && qteSucre >= 0 && qteLait >= 0)
				&& (qteCafe + qteChocolat + qteLait + qteSucre != 0)) ? true : false;
	}
	public Boisson getBoisson(String nomBoisson){
		int indexToGet = 0;
		for (Boisson boisson : boissons) {
			if (boisson.getNom().equals(nomBoisson)) {
				indexToGet = this.boissons.indexOf(boisson);
			}
		}
		return this.boissons.get(indexToGet);
	}
}
