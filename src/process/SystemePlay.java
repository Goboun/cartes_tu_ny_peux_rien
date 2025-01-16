package process;

import java.util.Iterator;
import javax.swing.*;

import config.GameConfiguration;
import data.*;
import gui.*;


public class SystemePlay {
	
	public SystemePlay() {
		
	}
	
	public void test1(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionMain actionmain, ActionBot actionbot, JFrame jfPartie, JPanel jpTas, JPanel jpJ1) {
		if (partie.getJ1().getCan_Play() == true ) {
            actionmain.jouerCartes(accueil, partiegui, partie, actionbot, jfPartie, jpTas, jpJ1);
        	
            //verifie si le j1 a gagné 
            partie.tour.Wineur(partie.tour, partiegui);
            
            
        }
        //action des bots
        for (int i = 1; i <= accueil.nbBot(); i++) {
            switch (i) {
                case 1:
                	actionbot.jouer_bot(accueil, partiegui, partie, actionmain, jfPartie, jpTas, partie.getIA1(), GameConfiguration.COULEUR_BOT1, jpJ1);
                	break;
                case 2:
                	actionbot.jouer_bot(accueil, partiegui, partie, actionmain, jfPartie, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2, jpJ1);
                	break;
                case 3:
                	actionbot.jouer_bot(accueil, partiegui, partie, actionmain, jfPartie, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3, jpJ1);
                	break;
                case 4:
                	actionbot.jouer_bot(accueil, partiegui, partie, actionmain, jfPartie, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4, jpJ1);
                	break;
            }
            
            //vérifie si J1 a perdu
            partie.tour.Wineur(partie.tour, partiegui);
            
            
        }
        
        Iterator<Joueur> it1 = partie.tour.getJoueurs().iterator();
        while(it1.hasNext()) {
			Joueur j = it1.next();
			System.out.println(j.getNom() + " " + j.getCan_Play());
		}
        System.out.println(partie.tas.getDeck());	
        if (partie.getaJouer() == true) {
            partie.tour.nouveauTour(accueil,partiegui,partie,actionmain,actionbot,jfPartie,jpTas,jpJ1);
        }

	}

}
