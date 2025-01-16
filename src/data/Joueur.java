package data;
/**
 * Représente un joueur
 */
public class Joueur {
	
	/**
	 * Le joueur a un nom
	 */
	private String nom;
	
	/**
	 * Le joueur a une main
	 */
	private Main main;
	
	/**
	 * Le joueur peut jouer ou non, pour les coups
	 */
	private boolean can_play = true;
	
	/**
	 * Créer un joueur
	 * @param le nom du joueur, humain ou ordinateur
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.main = new Main();
		this.can_play = true;
	}
	
	/**
	 * Retourne le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}
	
	public boolean getCan_Play() {
		return this.can_play;
	}
	
	public void setCan_Play(boolean bl) {
		this.can_play = bl;
	}
	
	/**
	 * Retourne la main du joueur
	 * @return la main du joueur
	 */
	public Main getMain() {
		return this.main;
	}
	
}