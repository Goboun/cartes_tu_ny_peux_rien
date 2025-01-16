package process;

import config.GameConfiguration; 
import data.Carte;
import data.Deck;
import data.Joueur;
import data.Tour;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import gui.*;

public class Partie {
	public Deck pioche;
	public Deck tas;
	public Tour tour;

	public Joueur J1;
	public Joueur IA1;

	private Joueur IA2;
	private Joueur IA3;
	private Joueur IA4;
	
	public boolean aJouer = false;
	
	
	public Partie(int nbBot) {
		//On initialise la pioche
		pioche = new Deck();
		pioche.initDeck();
		//On initialise le tas
		tas = new Deck();
		//On initialise les joueurs principaux
		J1 = new Joueur("J1");
		IA1 = new Joueur("IA1");
				
		//On distribue les cartes, on commence avec 4 cartes avant le début + 1 carte de pioche
		for(int i = 0; i < GameConfiguration.NB_CARTES_J1; i++) {
		    Carte carteJ1 = /*new Carte(10,"coeur");*/pioche.piocherCarte();
		    J1.getMain().piocherCarte(carteJ1);
		}
		//On défini les joueurs de la partie
		ArrayList<Joueur> listjoueur = new ArrayList<Joueur>();
		listjoueur.add(J1);
		
		//tout décaler de 1 je crois, case 1, pour AI2 ectect
		switch (nbBot) {
			case 4 :
				IA4 = new Joueur("IA4");
				listjoueur.add(IA4);
				for(int i = 0; i < GameConfiguration.NB_CARTES; i++) {
					Carte carte = pioche.piocherCarte();
					IA4.getMain().piocherCarte(carte);
				}
				IA4.getMain().trierMain();
			case 3 :
				IA3 = new Joueur("IA3");
				listjoueur.add(IA3);
				for(int i = 0; i < GameConfiguration.NB_CARTES; i++) {
					Carte carte = pioche.piocherCarte();
					IA3.getMain().piocherCarte(carte);
				}
				IA3.getMain().trierMain();
			case 2 :
				IA2 = new Joueur("IA2");
				listjoueur.add(IA2);
				for(int i = 0; i < GameConfiguration.NB_CARTES; i++) {
					Carte carte = pioche.piocherCarte();					
					IA2.getMain().piocherCarte(carte);	
				}
				IA2.getMain().trierMain();
			case 1 :
				IA1 = new Joueur("IA1");
				listjoueur.add(IA1);
				for(int i = 0; i < GameConfiguration.NB_CARTES; i++) {
					Carte carte = pioche.piocherCarte();
					IA1.getMain().piocherCarte(carte);
				}
				IA1.getMain().trierMain();
			default : 
				break;
		}
		//On initialise l'objet Tour
		tour = new Tour(1,listjoueur);
	}
	
	public Joueur getJ1() {
		return J1;
	}
	public Joueur getIA1() {
		return IA1;
	}
	public Joueur getIA2() {
		return IA2;
	}
	public Joueur getIA3() {
		return IA3;
	}
	public Joueur getIA4() {
		return IA4;
	}
	public Tour getTour() {
		return tour;
	}
	public Deck getTas() {
		return tas;
	}
	public Deck getDeck() {
		return pioche;
	}
	public void piocherCartBot(AccueilGUI accueil, Joueur joueur, HashMap<Carte, JButton> cartes_bot, JPanel jpIA) {
		Carte nouvelleCarte = pioche.piocherCarte();
        joueur.getMain().piocherCarte(nouvelleCarte);
        jpIA.removeAll();
        for(Carte carte : joueur.getMain().getMain()) {
        	JButton carteButton = null;
			switch (accueil.getMode2jeu()) {
			case GameConfiguration.CARTE_CACHEE :
				carteButton =new JButton(carte.carte_dos());
				break;
			case GameConfiguration.CARTE_VISIBLE :
				carteButton = new JButton(carte.imagecarte());
				break;
			}
			carteButton.setBorder(null);
			cartes_bot.put(carte, carteButton);
			jpIA.add(carteButton);
		}
	}
	
	public boolean getaJouer(){
		return this.aJouer;
	}
	
	public void setaJouer(boolean b) {
		this.aJouer = b;
	}
}
