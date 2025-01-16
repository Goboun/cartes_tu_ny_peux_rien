package data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

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
	 * vieux constructeur, la dernière carte posé
	 */
	private Carte dernierCarte;
	
	/**
	 * Les dernières cartes à poser
	 */
	private ArrayList<Carte> dernieresCartes;
	
	/**
	 * Contructeur qui sert à instancier un paquet de cartes vide.
	 * Son rôle est définit en fonction des besoins du jeu (pioche ou tas)
	 */
	public Deck() {
		deck = new ArrayList<Carte>();
		dernieresCartes = new ArrayList<Carte>();
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
			if (i == 1) {
				carte = new Carte(i+13, "pique");
				deck.add(carte);
			} else {
				carte = new Carte(i, "pique");
				deck.add(carte);
			}
		}
		
		//On rajoute les cartes de couleur coeur, de 1 à 13
		for(int i =1; i <= 13; i++) {
			if (i == 1) {
				carte = new Carte(i+13, "coeur");
				deck.add(carte);
			} else {
				carte = new Carte(i, "coeur");
				deck.add(carte);
			}
		}
		
		//On rajoute les cartes de couleur carreau, de 1 à 13
		for(int i =1; i <= 13; i++) {
			if (i == 1) {
				carte = new Carte(i+13, "carreau");
				deck.add(carte);
			} else {
				carte = new Carte(i, "carreau");
				deck.add(carte);
			}
		}
		
		//On rajoute les cartes de couleur trèfle, de 1 à 13
		for(int i =1; i <= 13; i++) {
			if (i == 1) {
				carte = new Carte(i+13, "trefle");
				deck.add(carte);
			} else {
				carte = new Carte(i, "trefle");
				deck.add(carte);
			}
		}
		
		/*
		 * On rajoute les deux Joker représenté par le numéro 14.
		 * Il n'a pas de couleur mais chaque carte est unique,
		 * donc on doit quand même les différencier
		 */
		/*carte = new Carte(15, "joker");
		deck.add(carte);
		carte = new Carte(16, "joker");
		deck.add(carte);*/
		
		//Pour mélanger le deck, on utilise Collections.shuffle(ArrayList)
		Collections.shuffle(deck);
		this.deck = deck;
	}
	
	/**
	 * Méthode pour piocher une Carte
	 */
	public Carte piocherCarte() {
		if (this.deck.isEmpty()) {
			initDeck();
		}
		Carte carte = this.deck.remove(0);
		return carte;
	}
	
	/**
	 * Vieille méthode, avec la dernierCarte représenté par une Carte
	 */
	public void jouerCarte(Carte carte) {
		this.dernierCarte = carte;
		this.deck.add(carte);
	}
	
	/**
	 * Méthode pour placer, une liste de Carte séléctionné, dans le tas quand on joue
	 * @param listeCartes les cartes sélectionnés qui sont jouées et qui vont rejoindre le tas
	 */
	public void jouerCarte(ArrayList<Carte> listeCartes) {
		this.dernieresCartes = listeCartes;
		//Pour fusionner deux ArrayList
		this.deck.addAll(listeCartes);
	}

	public ArrayList<Carte> getDeck() {
		return deck;
	}
	
	public ArrayList<Carte> getDeck2() {
		return dernieresCartes;
	}

	public Carte getDernier() {
		Iterator<Carte> it = dernieresCartes.iterator();
		Carte lastCarte = null;
        while(it.hasNext()){
			lastCarte = it.next();
		}
		dernierCarte = lastCarte;
		
		return dernierCarte;
	}
	
	public void setDernieresCartes(ArrayList<Carte> listeCartes) {
		dernieresCartes = listeCartes;
	}
	
	public void ajouter_tas(Carte c) {
		dernieresCartes.add(c);
		deck.add(c);
	}

	public boolean tasVide() {
		return dernieresCartes.isEmpty();
	}

	public int getCombo() {
		return dernieresCartes.size();
	}
	
	public void trierTas() {
	    if (getDeck() != null) {
	        Collections.sort(deck, Comparator.comparingInt(Carte::getNumero));
	        Collections.sort(dernieresCartes, Comparator.comparingInt(Carte::getNumero));
	    }
	}
	
	
	public void suppTas() {
	    deck.clear();
	    dernieresCartes.clear();
	}
}