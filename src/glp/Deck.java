package glp;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Représente un des deux paquets de cartes sur le plateau, 
 * soit la pioche, soit le tas
 */
public class Deck {
	
	/**
	 * Un paquet de cartes est représenté par une ArrayList de Carte
	 */
	private ArrayList<Carte> deck;
	
	/**
	 * La dernière carte posé
	 */
	private Carte dernierCarte;
	
	/**
	 * Contructeur qui sert à instancier un paquet de cartes vide.
	 * Son rôle est définit en fonction des besoins du jeu (pioche ou tas)
	 */
	public Deck() {
		deck = new ArrayList<Carte>();
	}
	
	/**
	 * Méthode pour initialiser le paquet de pioche
	 */
	public void initDeck(){
		ArrayList<Carte> deck = new ArrayList<Carte>();
		
		/*
		 * Une carte va de 1 à 10,
		 * puis de 11 à 13 pour le Valet, la Dame, et le Roi
		 */
		Carte carte;
		
		//On rajoute les cartes de couleur pique, de 1 à 13
		for(int i =1; i <= 13 ; i++) {
			carte = new Carte(i, "pique");
			deck.add(carte);
		}
		
		//On rajoute les cartes de couleur coeur, de 1 à 13
		for(int i =1; i <= 13; i++) {
			carte = new Carte(i, "coeur");
			deck.add(carte);
		}
		
		//On rajoute les cartes de couleur carreau, de 1 à 13
		for(int i =1; i <= 13; i++) {
			carte = new Carte(i, "carreau");
			deck.add(carte);
		}
		
		//On rajoute les cartes de couleur trèfle, de 1 à 13
		for(int i =1; i <= 13; i++) {
			carte = new Carte(i, "trefle");
			deck.add(carte);
		}
		
		/*
		 * On rajoute les deux Joker représenté par le numéro 14.
		 * Il n'a pas de couleur mais chaque carte est unique,
		 * donc on doit quand même les différencier
		 */
		carte = new Carte(14, "joker");
		deck.add(carte);
		carte = new Carte(15, "joker");
		deck.add(carte);
		
		//Pour mélanger le deck, on utilise Collections.shuffle(ArrayList)
		Collections.shuffle(deck);
		this.deck = deck;
	}
	
	/**
	 * Méthode pour piocher une Carte
	 */
	public Carte piocherCarte() {
		/*
		 * Le 1 dans remove indique qu'on enlève le 1er élément,
		 * à voir si c'est la 1er carte ou la dernière
		 */
		Carte carte = this.deck.remove(1);
		return carte;
	}
	
	/**
	 * Méthode pour placer une Carte dans le tas quand on joue
	 * A refaire pour pouvoir placer plusieurs cartes comme une bombe
	 * @param carte la carte qui est joué et qui va rejoindre le tas
	 */
	public void jouerCarte(Carte carte) {
		this.dernierCarte = carte;
		this.deck.add(carte);
	}

	public ArrayList<Carte> getDeck() {
		return deck;
	}

	public Carte getDernier() {
		return getDeck().get(getDeck().size()-1);
	}
}