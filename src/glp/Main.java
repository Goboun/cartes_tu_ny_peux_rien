package glp;
import java.util.ArrayList;

/**
 * Représente la main d'un joueur, vide ou non
 */
public class Main {
	
	/**
	 * La main du joueur est représenté par une ArrayList de Carte
	 */
	private ArrayList<Carte> main;
	
	/**
	 * Créer un main vide pour un joueur
	 */
	public Main() {
		main = new ArrayList<Carte>();
	}
	
	/**
	 * Méthode pour piocher une Carte
	 */
	public void piocherCarte(Carte carte) {
		this.main.add(carte);
	}
	
	/**
	 * Pose une carte de la main au tas
	 * @return la carte joué
	 */
	public Carte jouerCarte(Carte carte) {
		this.main.remove(carte);
		return carte;
	}
	
	/**
	 * 
	 */
	public Carte getLastCarte() {
		return this.main.get(this.main.size()-1);
	}
	
	/**
	 * Retourne la main du joueur
	 * @return la main du joueur
	 */
	public ArrayList<Carte> getMain(){
		return this.main;
	}
}