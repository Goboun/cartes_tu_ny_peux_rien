 package data;

import javax.swing.*;
/**
 * Représente une carte d'un jeu de carte
 */
public class Carte {
	/**
	 * Une carte est représenté par son numéro
	 * 1 à 10, puis de 11 à 13 pour le Valet, la Dame, et le Roi,
	 * puis 14 pour le Joker
	 */
	private int numero;
	/**
	 * Une carte est représenté par sa couleur:
	 * pique, coeur, carreau, trèfle.
	 * A noter que le Joker n'a pas de couleur mais sera défini
	 * en tant que aucune1 et aucune2 respectivement pour les deux Joker
	 */
	private String couleur;
	/**
	 * Créer une carte avec son numéro et sa couleur
	 * @param numero le numéro de la carte
	 * @param couleur la couleur de la carte
	 */
	public Carte(int numero, String couleur) {
		this.numero = numero;
		this.couleur = couleur;
	}
	/**
	 * Avec le numéro et la couleur, on récupère la carte
	 * @param numero le numéro de la carte
	 * @param couleur la couleur de la carte
	 * @return carte la carte qu'on recherche
	 */
	public Carte getCarte(int numero, String couleur) {
		Carte carte = new Carte(numero, couleur);
		return carte;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public String getCouleur() {
		return this.couleur;
	}
	
	public String afficheCarte() {
		return this.numero + " " + this.couleur;
	}
		
	public ImageIcon imagecarte() {
		ImageIcon icon = new ImageIcon();
		if (numero == 14) {
			icon = new ImageIcon("src/images/" + couleur + "/" + 1 + ".png");
		} else {
			if((numero!=15) && (numero !=16)) {
				icon = new ImageIcon("src/images/" + couleur + "/" + numero + ".png");
			}else {
				if (numero == 15) {
					icon = new ImageIcon("src/images/joker/" + numero + ".png");			
				} else {
					icon = new ImageIcon("src/images/joker/15.png");			
				}
			}
		}
		
		return icon;
	}
	
	public ImageIcon carte_dos() {
		ImageIcon icon = new ImageIcon();
		icon = new ImageIcon("src/images/dos_carte.jpg");	
		return icon;
	}
	
}

