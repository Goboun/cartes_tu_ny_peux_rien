package glp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class Partie {
	public Deck pioche;
	public Deck tas;
	
	public Joueur J1;
	public Joueur IA1;
	private Joueur IA2;
	private Joueur IA3;
	private Joueur IA5;
	
	public int tour;
	public boolean pasFini;
	
	public Partie() {
		//On initialise la pioche
		pioche = new Deck();
		pioche.initDeck();
		//On initialise le tas
		tas = new Deck();
		//On initialise les joueurs principaux
		J1 = new Joueur("J1");
		IA1 = new Joueur("IA1");
		//On initialise le nombre de tours et on désactive le flag
		tour = 0;
		pasFini = true;
		//On distribue les cartes, on commence avec 4 cartes avant le début
		for(int i = 0; i < 4; i++) {
			Carte carte = pioche.piocherCarte();
			J1.getMain().piocherCarte(carte);
			carte = pioche.piocherCarte();
			IA1.getMain().piocherCarte(carte);
		}

	}
			
	public void jouer(Joueur J1, Joueur IA1) {
		while(pasFini) {
			//Tour
			this.tour++;
			//Chaque joueur pioche une carte pour commencer un tour
			//J1
			Carte carte = pioche.piocherCarte();
			J1.getMain().piocherCarte(carte);
			//J2
			carte = pioche.piocherCarte();
			IA1.getMain().piocherCarte(carte);
			
			//Tant que les joueurs n'ont pas cliqué sur se coucher, ça joue
			J1.setCan_Play(true);
			IA1.setCan_Play(true);
			while(J1.getCan_Play() || IA1.getCan_Play()) {
				
				//J1
				if(J1.getCan_Play() && pasFini) {
					//Le joueur doit faire une action
					boolean noPlay = true;
					while(noPlay) {
						
						if(false) {
							noPlay = false;
						}
						if(J1.getCan_Play() == false) {
							noPlay = false;
							J1.setCan_Play(false);
						}
						if (J1.getMain().getMain().isEmpty() == true) {
							pasFini = false;
						}
					} 
				}
				
				//J2
				if(IA1.getCan_Play() && pasFini) {
					//Le bot doit jouer
					if(false) {
						//La condition = code de l'IA
					}
					//sinon il se couche s'il peut pas
					else{
						IA1.setCan_Play(false);
					}
					if (IA1.getMain().getMain().isEmpty() == true) {
						pasFini = false;
					}
				}
			}

		}
		//mainGUI.setVainqueur();
	}
	
	public Joueur getJ1() {
		return J1;
	}
	public Joueur getIA1() {
		return IA1;
	}
	
	public Deck getTas() {
		return tas;
	}
			
}
