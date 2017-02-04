package machineCafe;

import java.util.ArrayList;

public class Boisson {
	

	@Override
	public String toString() {
		return "Boisson [nom=" + nom + ", prix=" + prix + ", ingredients=" + ingredients + ", cafeQuantite="
				+ cafeQuantite + ", chocolatQuantite=" + chocolatQuantite + ", laitQuantite=" + laitQuantite
				+ ", sucreQuantite=" + sucreQuantite + "]";
	}

	private String nom;
	private int prix;
	public int getLaitQuantite() {
		return laitQuantite;
	}
	public void setLaitQuantite(int laitQuantite) {
		this.laitQuantite = laitQuantite;
	}

	private ArrayList<Ingredient> ingredients;
	
	private int cafeQuantite;
	private int chocolatQuantite;
	private int laitQuantite;
	private int sucreQuantite;
	
	
	
	public int getCafeQuantite() {
		return cafeQuantite;
	}
	public void setCafeQuantite(int cafeQuantite) {
		this.cafeQuantite = cafeQuantite;
	}
	public int getChocolatQuantite() {
		return chocolatQuantite;
	}
	public void setChocolatQuantite(int chocolatQuantite) {
		this.chocolatQuantite = chocolatQuantite;
	}
	public int getSucreQuantite() {
		return sucreQuantite;
	}
	public void setSucreQuantite(int sucreQuantite) {
		this.sucreQuantite = sucreQuantite;
	}
	
	
	
	public Boisson(String nom, int prix, ArrayList<Ingredient> ingredients, int cafeQuantite, int chocolatQuantite,
			int laitQuantite, int sucreQuantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.ingredients = ingredients;
		this.cafeQuantite = cafeQuantite;
		this.chocolatQuantite = chocolatQuantite;
		this.laitQuantite = laitQuantite;
		this.sucreQuantite = sucreQuantite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void ajouterIngredient(Ingredient i){
		this.ingredients.add(i);
	}	
}
