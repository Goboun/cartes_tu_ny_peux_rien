package data;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import config.GameConfiguration;
import gui.*;
import process.Partie;
import process.ActionMain;
import process.ActionBot;

public class Tour {
	private int nbtour;//+1 avant de piocher
	private String meneur;//dernier qui c coucher
	private String rythme;//si c simple, double, série
	private ArrayList<Joueur> listjoueur;	
	
    public Tour(int nbtour, ArrayList<Joueur> joueurs) {
        this.nbtour = nbtour;
        meneur = "J1";
        rythme = null;
        this.listjoueur = joueurs;
    }
    public int getNbTour() {
    	return this.nbtour;
    }  
    public String getMeneur() {
    	return this.meneur;
    }
    public String getRythme() {
    	return this.rythme;
    }
    public void setMeneur(String meneur) {
    	this.meneur = meneur;
    }   
    public void setRythme(String rythme) {
    	this.rythme = rythme;
    }	
    public void incrementerNbTour() {
    	this.nbtour++;
    }  
    public ArrayList<Joueur> getJoueurs() {
    	return listjoueur;
    }
    
    public boolean tousLesJoueursCouchers() {
        /**
         * Pour vérifier quand tout les joueurs sont couchés, afin de passer au prochain tour
         * @return res Vrai si tout les joueurs sont couchés, sinon faux
        */
        for (Joueur joueur : listjoueur) {
            if (joueur.getCan_Play()) {
                //Au moins un joueur peut jouer, la condition n'est pas satisfaite
                return false;
            }
        }
        //Tous les joueurs sont couchés ici
        return true; 
    } 
    
    public void rythmeTour(Deck tas, Joueur joueur) {
        //on vérifie après qu'il ai joué, si c'est le meneur ou non
    	if(getMeneur() == joueur.getNom() && getRythme() == null) {
    		if(tas.getCombo() == 0) {
    			setRythme(null);
    		}
    		else {
        		if(tas.getCombo() == 1) {
        			setRythme("Simple");
        		}
        		else {
            		if(tas.getCombo() == 2) {
            			System.out.println("22");
    					if(tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(1).getNumero()) {
    						setRythme("Double");
    					}
        				else{
        					if((tas.getDeck2().get(0).getNumero()+1 == tas.getDeck2().get(1).getNumero()) || (tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(1).getNumero()+1)) {
            					setRythme("Serie2");
            				}
        				}
            		}
        			else {
						if(tas.getCombo() == 3){
							System.out.println("333");
							if(tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(1).getNumero() && tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(2).getNumero()) {
	    						setRythme("Bombe3");
							}
							else {
								if(tas.getDeck2().get(0).getNumero()+1 == tas.getDeck2().get(1).getNumero() && tas.getDeck2().get(1).getNumero()+1 == tas.getDeck2().get(2).getNumero()) {
		    						setRythme("Serie3");
								}
							}
						}
						else {
							if(tas.getCombo() == 4){
								System.out.println("4444");
								if(tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(1).getNumero() && tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(2).getNumero() && tas.getDeck2().get(0).getNumero() == tas.getDeck2().get(3).getNumero()) {
		    						setRythme("Bombe4");
								}
								else {
									if(tas.getDeck2().get(0).getNumero()+1 == tas.getDeck2().get(1).getNumero() && tas.getDeck2().get(1).getNumero()+1 == tas.getDeck2().get(2).getNumero() && tas.getDeck2().get(2).getNumero()+1 == tas.getDeck2().get(3).getNumero()) {
			    						setRythme("Serie4");
									}
								}
							}
							else {
								setRythme(null);
							}
						}  					       				
            		}
        		}
    		}
    		System.out.println(getRythme());
    	}
	}
    
	public void nouveauTour(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionMain actionmain, ActionBot actionbot, JFrame frame, JPanel jpTas, JPanel jpJ1) {
	    /**
		 * On distribue une nouvelle carte dans la main de chaque joueur, en début de nouveau tour
		 * @param deck Une ArrayList composé des cartes, représente le deck de la partie
		*/
		if(tousLesJoueursCouchers()) {
			
			System.out.println("tour numero "+getNbTour()+" fini");
			System.out.println("nvtour");
			rythme = null;
			incrementerNbTour();
	        partiegui.getJlTour().setText("Tour : " + partie.getTour().getNbTour());	
	        //Vérifier si tous les joueurs sont couchés
			Iterator<Joueur> it = getJoueurs().iterator();
	        while(it.hasNext()) {
				Joueur j = it.next();
				j.setCan_Play(true);
			}
	        //pour distrubuer une carte apres la fin du tour
	        for (Joueur joueur : listjoueur) {
	        	switch (joueur.getNom()) {
		        	case "J1" :
		        		actionmain.piocherCarteJ1(partie, frame, jpJ1, actionmain.getJ1main());
		        		break;
		        	case "IA1" :
		        		partie.piocherCartBot(accueil, joueur, partiegui.getListeHashMapCarteBot().get(0), partiegui.getListePanelIA().get(0));
		        		break;
		        	case "IA2" :
		        		partie.piocherCartBot(accueil, joueur, partiegui.getListeHashMapCarteBot().get(1), partiegui.getListePanelIA().get(1));
		        		break; 
		        	case "IA3" :
		        		partie.piocherCartBot(accueil, joueur, partiegui.getListeHashMapCarteBot().get(2), partiegui.getListePanelIA().get(2));
		        		break; 
		        	case "IA4" :
		        		partie.piocherCartBot(accueil, joueur, partiegui.getListeHashMapCarteBot().get(3), partiegui.getListePanelIA().get(3));
		        		break;
		        	}
	            frame.revalidate();
	            frame.repaint();
	        }	
	        //actualise le jeu et supprime le tas apres les cartes distribuer
	        partie.tas.suppTas();
	        jpTas.removeAll();
	        jpTas.revalidate();
	        jpTas.repaint();
	        partiegui.playButton.setEnabled(true);
        	partiegui.jbSeCoucher.setEnabled(true);
        	partiegui.jbPasser.setEnabled(false);
	        frame.revalidate();
            frame.repaint();
	        
	        //fais jouer le meneur
			partie.tour.meneurPlay(accueil, partiegui,partie, actionmain, actionbot,  frame, jpTas, jpJ1);
			
		}

	}
	
	public void meneurPlay(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionMain actionmain, ActionBot actionbot, JFrame frame, JPanel jpTas, JPanel jpJ1) {
		//le meneur joue en 1er
        switch (partie.tour.getMeneur()) {
        
        case "J1":
        	actionmain.jouerCartes(accueil,partiegui, partie,actionbot, frame, jpTas, jpJ1);
        	break;
        	
        case "IA1":
        	actionbot.jouerAleatoire(accueil,partiegui,partie,actionmain,frame,jpTas,partie.getIA1(),GameConfiguration.COULEUR_BOT1,jpJ1);
        	
        	//vérifie si il y a les autres IA apres IA1 pour qu'il suivent
        	switch (accueil.nbBot()) {        	
        	case 2 :
        		//IA2
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA2(),GameConfiguration.COULEUR_BOT2,jpJ1);
        		break;
        	case 3 :
        		//IA2 et IA3
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2,jpJ1);
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3,jpJ1);
        		// faire pareil pour double et serie
            	break;
        	case 4 :
        		//IA2 et IA3 et IA4
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2,jpJ1);
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3,jpJ1);
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4,jpJ1);
        		break;
        	}
        	break;
        	
        case "IA2":
        	actionbot.jouerAleatoire(accueil,partiegui,partie,actionmain,frame, jpTas,partie.getIA2(), GameConfiguration.COULEUR_BOT2,jpJ1);
        	
        	//vérifie si il y a les autres IA apres IA2 pour qu'il suivent
        	switch (accueil.nbBot()) {        	
        	case 3 :
        		//IA3
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3,jpJ1);
        		break;
        	case 4 :
        		//IA3 et IA4
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3,jpJ1);
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4,jpJ1);
        		break;
        	}        	
        	break;
        	
        case "IA3":
        	actionbot.jouerAleatoire(accueil,partiegui,partie,actionmain,frame,jpTas,partie.getIA3(), GameConfiguration.COULEUR_BOT3,jpJ1);
        	
        	//vérifie si il y a les autres IA apres IA3 pour qu'il suivent
        	switch (accueil.nbBot()) {        	
        	case 4 :
        		//IA4
        		actionbot.jouer_bot(accueil, partiegui, partie, actionmain, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4,jpJ1);
        		break;
        	}        	
        	break;
        	
        case "IA4":
        	actionbot.jouerAleatoire(accueil,partiegui,partie,actionmain,frame,jpTas,partie.getIA4(), GameConfiguration.COULEUR_BOT4,jpJ1);
        	break;
        }
        jpTas.revalidate();
        jpTas.repaint();
        frame.revalidate();
        frame.repaint();
	}
	public void Wineur(Tour tour, PartieGUI partiegui) {
	    for (Joueur j : tour.getJoueurs()) {
	        if (j.getMain().getMain().size() == 0 && j.getNom() == "J1") {
	        	partiegui.vainqueur();
	            return;
	        }
	        if (j.getMain().getMain().size() == 0 && j.getNom() != "J1") {
	        	partiegui.perdant();
	        	return; 
	        }
	    }
	}
}