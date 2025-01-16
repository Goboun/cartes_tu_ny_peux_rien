package process;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import gui.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import config.GameConfiguration;
import data.Carte;
import data.Joueur;

public class ActionBot {
	private ArrayList<JPanel> listePanelIA;
	private ArrayList<HashMap<Carte, JButton>> listeHashMapCarteBot;

	
	public ActionBot(ArrayList<JPanel> listePanelIA, ArrayList<HashMap<Carte, JButton>> listeHashMapCarteBot) {
		this.listePanelIA = listePanelIA;
		this.listeHashMapCarteBot = listeHashMapCarteBot;
	}
	
    public boolean peut_jouer(Carte c1, Partie partie) {
        /**
    	 * Permet de savoir si un joueur peut poser une carte conformément aux règles du jeu
    	 * @param c1 Une ArrayList composé des cartes sélectionnés durant la partie
    	 * @param carteTas la dernière carte du tas, la carte la plus récente jouée
    	 * @return res Vrai si le joueur peut jouer sa carte, sinon renvoie faux
    	*/
        if (partie.tas.tasVide()) {
            //cas au tour 1, ou aucune carte est dans le tas, on fait ce qu'on veut
            return true; 
        }
        return c1.getNumero() == partie.tas.getDernier().getNumero() + 1 || c1.getNumero() == 2;//  || partie.tas.getDernier().getNumero() == 2;
    }
    
    public boolean peut_jouerSerie2(Carte c1, Partie partie) {
        /**
    	 * Permet de savoir si un joueur peut poser une carte conformément aux règles du jeu
    	 * @param c1 Une ArrayList composé des cartes sélectionnés durant la partie
    	 * @param carteTas la dernière carte du tas, la carte la plus récente jouée
    	 * @return res Vrai si le joueur peut jouer sa carte, sinon renvoie faux
    	*/
        if (partie.tas.tasVide()) {
            //cas au tour 1, ou aucune carte est dans le tas, on fait ce qu'on veut
            return true; 
        }
        return c1.getNumero() == partie.tas.getDernier().getNumero();// || c1.getNumero() == 2;//  || partie.tas.getDernier().getNumero() == 2;
    }
    
    public boolean peut_jouerSerie3(Carte c1, Partie partie) {
        /**
    	 * Permet de savoir si un joueur peut poser une carte conformément aux règles du jeu
    	 * @param c1 Une ArrayList composé des cartes sélectionnés durant la partie
    	 * @param carteTas la dernière carte du tas, la carte la plus récente jouée
    	 * @return res Vrai si le joueur peut jouer sa carte, sinon renvoie faux
    	*/
        if (partie.tas.tasVide()) {
            //cas au tour 1, ou aucune carte est dans le tas, on fait ce qu'on veut
            return true; 
        }
        return c1.getNumero() == partie.tas.getDernier().getNumero() - 1;// || c1.getNumero() == 2;//  || partie.tas.getDernier().getNumero() == 2;
    }
    
    public boolean peut_jouerSerie4(Carte c1, Partie partie) {
        /**
    	 * Permet de savoir si un joueur peut poser une carte conformément aux règles du jeu
    	 * @param c1 Une ArrayList composé des cartes sélectionnés durant la partie
    	 * @param carteTas la dernière carte du tas, la carte la plus récente jouée
    	 * @return res Vrai si le joueur peut jouer sa carte, sinon renvoie faux
    	*/
        if (partie.tas.tasVide()) {
            //cas au tour 1, ou aucune carte est dans le tas, on fait ce qu'on veut
            return true; 
        }
        return c1.getNumero() == partie.tas.getDernier().getNumero() - 2;// || c1.getNumero() == 2;//  || partie.tas.getDernier().getNumero() == 2;
    }

    public void jouer_bot(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionMain actionmain, JFrame frame, JPanel jpTas, Joueur jBot, Color couleur, JPanel jpJ1) {
    	if(partie.tour.getRythme() == null) {
    		Random random = new Random();
    		int rand = random.nextInt(7);
            switch(rand) {
            case 0 :
                bot_simple(accueil,partiegui,partie,actionmain,frame,jpTas,jBot,couleur,jpJ1);
                break;
            case 1 :
            	bot_double(accueil,partiegui,partie,actionmain,frame,jpTas,jBot,couleur,jpJ1);
            	break;
            case 2 :
            	bot_bombe3(partie,frame,jpTas,jBot,couleur);
            	break;
            case 3 :
            	bot_bombe4(partie,frame,jpTas,jBot,couleur);
            	break;
            case 4 :
            	bot_serie2(partie,frame,jpTas,jBot,couleur);
            	break;
            case 5 :
            	bot_serie3(partie,frame,jpTas,jBot,couleur);
            	break;
            case 6 :
            	bot_serie4(partie,frame,jpTas,jBot,couleur);
            	break;
            default :
            	break;
            }
            System.out.println("fin METHODE JOUER_BOT : "+jBot.getNom() + jBot.getCan_Play());

        }
        if(partie.tour.getRythme() != null) {
            switch(partie.tour.getRythme()) {
            case "Simple" :
                bot_simple(accueil,partiegui,partie,actionmain,frame,jpTas,jBot,couleur,jpJ1);
                break;
            case "Double" :
            	bot_double(accueil,partiegui,partie,actionmain,frame,jpTas,jBot,couleur,jpJ1);
            	break;
            case "Bombe3" :
            	bot_bombe3(partie,frame,jpTas,jBot,couleur);
            	break;
            case "Bombe4" :
            	bot_bombe4(partie,frame,jpTas,jBot,couleur);
            	break;
            case "Serie2" :
            	bot_serie2(partie,frame,jpTas,jBot,couleur);
            	break;
            case "Serie3" :
            	bot_serie3(partie,frame,jpTas,jBot,couleur);
            	break;
            case "Serie4" :
            	bot_serie4(partie,frame,jpTas,jBot,couleur);
            	break;
            default :
            	break;
            }
            System.out.println("fin METHODE JOUER_BOT : "+jBot.getNom() + jBot.getCan_Play());

        }
    }
    
    public void bot_simple(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionMain actionmain,JFrame frame, JPanel jpTas, Joueur jBot, Color couleur, JPanel jpJ1) {
    	if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */
        	System.out.println("METHODE BOT_SIMPLE : "+partie.tour.getRythme());
        	System.out.println("METHODE BOT_SIMPLE joueur : "+jBot.getNom() + jBot.getCan_Play());

    		int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                //si il joue autres choses
                if(peut_jouer(sel_carte, partie)){
                	
                	if (sel_carte.getNumero() == 15 || sel_carte.getNumero() == 16) {
                		break;
                	}
                    //condition des règles du jeu à la place ici dans le futur
                    partie.tas.ajouter_tas(sel_carte);

                    //joue la carte non graphiquement
                    jBot.getMain().jouerCarte(sel_carte);

                    //pose graphiquement la carte sur le Tas
                    JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
                    labelCarteJouer.setBackground(couleur);
                    jpTas.add(labelCarteJouer);

                    //retire graphiquement la carte qui a été jouée
                    listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));       
                    partie.tour.setMeneur(jBot.getNom());

                    //on retire la non graphiquement de la main
                    jBot.getMain().effacerCarte(sel_carte);

                    //set le meneur du tour
                    partie.tour.rythmeTour(partie.tas, jBot);
                    partie.setaJouer(true);
                    
                    // Mettre à true pour indiquer qu'une carte a été jouée
                    carteJouer = true;
                     
                    //actualise le jeux
                    listePanelIA.get(nb).repaint();
                    listePanelIA.get(nb).revalidate();
                    jpTas.repaint();
                    frame.revalidate();
                    frame.repaint();
                    
                    //vérifie si c un 2 
                    if (partie.tas != null && partie.tas.getDernier() != null && partie.tas.getDernier().getNumero() == 2) {			
            			
                    	partie.setaJouer(false);
                        systemeBombe(partie,frame,jpTas,jBot);
                        
                        System.out.println(partie.tas.getDeck());
                    	//permet que personne ne peut jouer apres la carte 2
                		Iterator<Joueur> it1 = partie.tour.getJoueurs().iterator();
                        while(it1.hasNext()) {
                			Joueur j = it1.next();
                    		j.setCan_Play(false);                			
                		}

            			//timer de 2sec avant d'effacer le tas et donc de relancer un tour
            			Timer timer = new Timer(2000, new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                            	partie.tour.nouveauTour(accueil,partiegui,partie,actionmain,ActionBot.this,frame,jpTas,jpJ1);
                                frame.validate();
                                frame.repaint();    
                            }
                            
                        });
                        timer.setRepeats(false); // Ne se répète pas
                        timer.start();
            		} // fin if pour la carte 2
                } // fin if

            }//fin boucle while 

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        }// fin if 
    }
    
    public void bot_double(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionMain actionmain, JFrame frame, JPanel jpTas, Joueur jBot,Color couleur, JPanel jpJ1) {
        if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */
        	System.out.println("METHODE BOT_DOUBLE : "+partie.tour.getRythme());

        	int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                Iterator<Carte> it2 = jBot.getMain().getMain().iterator();
                while(it2.hasNext() && !carteJouer){
                    Carte sel_carte2 = it2.next();
                    if(sel_carte.getNumero() == sel_carte2.getNumero() && sel_carte.getCouleur()!=sel_carte2.getCouleur()) {
                        if(peut_jouer(sel_carte, partie) || partie.tas == null){
                        	
                            //condition des règles du jeu à la place ici dans le futur
                        	partie.tas.ajouter_tas(sel_carte);
                        	partie.tas.ajouter_tas(sel_carte2);
                            
                            //joue la carte non graphiquement
                            jBot.getMain().jouerCarte(sel_carte);
                            jBot.getMain().jouerCarte(sel_carte2);
                            
                            //pose graphiquement la carte sur le Tas
                            JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
                            labelCarteJouer.setBackground(couleur);
                            jpTas.add(labelCarteJouer);
                            JButton labelCarteJouer2 = new JButton(sel_carte2.imagecarte());
                            labelCarteJouer2.setBackground(couleur);
                            jpTas.add(labelCarteJouer2);
                            
                            //retire graphiquement la carte qui a été jouée
                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));                                
                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte2));      
                            partie.tour.setMeneur(jBot.getNom());

                            //on retire la non graphiquement de la main
                            jBot.getMain().effacerCarte(sel_carte);
                            jBot.getMain().effacerCarte(sel_carte2);
                            
                            //actualise le jeux
                            listePanelIA.get(nb).repaint();
                            listePanelIA.get(nb).revalidate();
                            jpTas.repaint();
                            frame.revalidate();
                            frame.repaint();
                            
                            //set le meneur du tour
                            partie.tour.rythmeTour(partie.tas, jBot);
                            partie.setaJouer(true);
                            
                            // Mettre à true pour indiquer qu'une carte a été jouée

                            carteJouer = true;
                            
                            //vérifie si c un 2 
                            if (partie.tas != null && partie.tas.getDernier() != null && partie.tas.getDernier().getNumero() == 2) {			
                    			
                            	partie.setaJouer(false);
                                systemeBombe(partie,frame,jpTas,jBot);
                                
                                System.out.println(partie.tas.getDeck());
                            	//permet que personne ne peut jouer apres la carte 2
                        		Iterator<Joueur> it1 = partie.tour.getJoueurs().iterator();
                                while(it1.hasNext()) {
                        			Joueur j = it1.next();
                            		j.setCan_Play(false);
                        		}

                    			//timer de 2sec avant d'effacer le tas et donc de relancer un tour
                    			Timer timer = new Timer(2000, new ActionListener() {
                                    public void actionPerformed(ActionEvent evt) {
                                    	partie.tour.nouveauTour(accueil,partiegui,partie,actionmain,ActionBot.this,frame,jpTas,jpJ1);
                                        frame.validate();
                                        frame.repaint();
                                        
                                    }
                                    
                                });
                                timer.setRepeats(false); // Ne se répète pas
                                timer.start();
                    		} // fin if pour la carte 2
                            
                        }
                    }
                }
            }

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        } 
    }
        
    public void bot_bombe3(Partie partie, JFrame frame, JPanel jpTas, Joueur jBot,Color couleur) {
        if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */
        	System.out.println("METHODE BOT_BOMBE3 : "+partie.tour.getRythme());
        	System.out.println("METHODE BOT_BOMBE3 joueur : "+jBot.getNom() + jBot.getCan_Play());

        	int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                Iterator<Carte> it2 = jBot.getMain().getMain().iterator();
                while(it2.hasNext() && !carteJouer){
                    Carte sel_carte2 = it2.next();
                    
                    Iterator<Carte> it3 = jBot.getMain().getMain().iterator();
                    while(it3.hasNext() && !carteJouer){
                        Carte sel_carte3 = it3.next();
                        //verifie si les 3 cartes son la même 
	                    if(sel_carte.getNumero() == sel_carte2.getNumero() && sel_carte.getNumero() == sel_carte3.getNumero() && sel_carte.getCouleur()!=sel_carte2.getCouleur() && sel_carte.getCouleur()!=sel_carte3.getCouleur() && sel_carte2.getCouleur()!=sel_carte3.getCouleur()) {
	                    	
	                    	if (peut_jouer(sel_carte, partie)) {
		
	                            //condition des règles du jeu à la place ici dans le futur
	                        	partie.tas.ajouter_tas(sel_carte);
	                        	partie.tas.ajouter_tas(sel_carte2);
	                        	partie.tas.ajouter_tas(sel_carte3);
	                            
	                            //joue la carte non graphiquement
	                            jBot.getMain().jouerCarte(sel_carte);
	                            jBot.getMain().jouerCarte(sel_carte2);
	                            jBot.getMain().jouerCarte(sel_carte3);
	                            
	                            //pose graphiquement la carte sur le Tas
	                            JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
	                            labelCarteJouer.setBackground(couleur);
	                            jpTas.add(labelCarteJouer);
	                            JButton labelCarteJouer2 = new JButton(sel_carte2.imagecarte());
	                            labelCarteJouer2.setBackground(couleur);
	                            jpTas.add(labelCarteJouer2);
	                            JButton labelCarteJouer3 = new JButton(sel_carte3.imagecarte());
	                            labelCarteJouer3.setBackground(couleur);
	                            jpTas.add(labelCarteJouer3);
	                            
	                            //retire graphiquement la carte qui a été jouée
	                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));                                
	                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte2));  
	                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte3));      
	                            partie.tour.setMeneur(jBot.getNom());
	
	                            //on retire la non graphiquement de la main
	                            jBot.getMain().effacerCarte(sel_carte);
	                            jBot.getMain().effacerCarte(sel_carte2);
	                            jBot.getMain().effacerCarte(sel_carte3);
	                            
	                            //actualise le jeux
	                            listePanelIA.get(nb).repaint();
	                            listePanelIA.get(nb).revalidate();
	                            jpTas.repaint();
	                            frame.revalidate();
	                            frame.repaint();
	                            
	                            //set le meneur du tour
	                            partie.tour.rythmeTour(partie.tas, jBot);
	                            
	                            // Mettre à true pour indiquer qu'une carte a été jouée
	                            carteJouer = true;
	                            
	                            
	                            // verifie que ct une bombe de 2
	                            if (partie.tas.getDernier().getNumero() == 2) {
	                            	//change le rythme car bombe2 posé pour faire place au bombe4
	                            	partie.tour.setRythme("Bombe4");
	                            }
	                            //attend 3,5 sec
	                            Timer timer = new Timer(3500, new ActionListener() {public void actionPerformed(ActionEvent evt) {}});
	                            timer.setRepeats(false); // Ne se répète pas
	                            timer.start();
	                        }
	                    }
                    }
                }
            }

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        } 
        //System.out.println(partie.tas.getDeck());

    }
    
    public void bot_bombe4(Partie partie, JFrame frame, JPanel jpTas, Joueur jBot,Color couleur) {
        if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */
        	
        	System.out.println("METHODE BOT_BOMBE4 : "+partie.tour.getRythme());

        	int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                Iterator<Carte> it2 = jBot.getMain().getMain().iterator();
                while(it2.hasNext() && !carteJouer){
                    Carte sel_carte2 = it2.next();
                    
                    Iterator<Carte> it3 = jBot.getMain().getMain().iterator();
                    while(it3.hasNext() && !carteJouer){
                        Carte sel_carte3 = it3.next();
                        
                        Iterator<Carte> it4 = jBot.getMain().getMain().iterator();
                        while(it4.hasNext() && !carteJouer){
                            Carte sel_carte4 = it4.next();
                        
                            //verifie si les 4 carte sont les mêmes
		                    if(sel_carte.getNumero() == sel_carte2.getNumero() && sel_carte.getNumero() == sel_carte3.getNumero() && sel_carte.getNumero() == sel_carte4.getNumero() && sel_carte.getCouleur()!=sel_carte2.getCouleur() && sel_carte.getCouleur()!=sel_carte3.getCouleur() && sel_carte2.getCouleur()!=sel_carte3.getCouleur() && sel_carte.getCouleur()!=sel_carte4.getCouleur() && sel_carte2.getCouleur()!=sel_carte4.getCouleur() && sel_carte3.getCouleur()!=sel_carte4.getCouleur() ) {
		                        //veerifie si c'est superieur de 1
		                    	if(peut_jouer(sel_carte, partie)){
		                        	
		                            //condition des règles du jeu à la place ici dans le futur
		                        	partie.tas.ajouter_tas(sel_carte);
		                        	partie.tas.ajouter_tas(sel_carte2);
		                        	partie.tas.ajouter_tas(sel_carte3);
		                        	partie.tas.ajouter_tas(sel_carte4);
		                        	
		                            //joue la carte non graphiquement
		                            jBot.getMain().jouerCarte(sel_carte);
		                            jBot.getMain().jouerCarte(sel_carte2);
		                            jBot.getMain().jouerCarte(sel_carte3);
		                            jBot.getMain().jouerCarte(sel_carte4);
		                            
		                            //pose graphiquement la carte sur le Tas
		                            JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
		                            labelCarteJouer.setBackground(couleur);
		                            jpTas.add(labelCarteJouer);
		                            JButton labelCarteJouer2 = new JButton(sel_carte2.imagecarte());
		                            labelCarteJouer2.setBackground(couleur);
		                            jpTas.add(labelCarteJouer2);
		                            JButton labelCarteJouer3 = new JButton(sel_carte3.imagecarte());
		                            labelCarteJouer3.setBackground(couleur);
		                            jpTas.add(labelCarteJouer3);
		                            JButton labelCarteJouer4 = new JButton(sel_carte4.imagecarte());
		                            labelCarteJouer4.setBackground(couleur);
		                            jpTas.add(labelCarteJouer4);
		                            
		                            //retire graphiquement la carte qui a été jouée
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));                                
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte2));  
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte3));    
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte4));     
		                            
		                            //set le meneur du tour
		                            partie.tour.setMeneur(jBot.getNom());
		
		                            //on retire la non graphiquement de la main
		                            jBot.getMain().effacerCarte(sel_carte);
		                            jBot.getMain().effacerCarte(sel_carte2);
		                            jBot.getMain().effacerCarte(sel_carte3);
		                            jBot.getMain().effacerCarte(sel_carte4);
		                            
		                            //actualise le jeux
		                           
		                            listePanelIA.get(nb).repaint();
		                            listePanelIA.get(nb).revalidate();
		                            jpTas.repaint();
		                            frame.revalidate();
		                            frame.repaint();
		                            
		                            // Mettre à true pour indiquer qu'une carte a été jouée
		                            carteJouer = true;
		                            
		                            //attend 3,5 sec
		                            Timer timer = new Timer(3500, new ActionListener() {public void actionPerformed(ActionEvent evt) {}});
		                            timer.setRepeats(false); // Ne se répète pas
		                            timer.start();
		                        }
		                    }
                        }
                    }
                }
            }

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        } 
    }
    
    public void bot_serie2(Partie partie, JFrame frame, JPanel jpTas, Joueur jBot, Color couleur) {
        if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */      	      
        	System.out.println("METHODE BOT_SERIE2 : "+partie.tour.getRythme());

        	int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                Iterator<Carte> it2 = jBot.getMain().getMain().iterator();
                while(it2.hasNext() && !carteJouer){
                    Carte sel_carte2 = it2.next();
                    if((sel_carte.getNumero()+1 == sel_carte2.getNumero() && sel_carte.getCouleur()!=sel_carte2.getCouleur())) {
                        if(peut_jouerSerie2(sel_carte, partie)) { //|| partie.tas == null){
                        	
                            //condition des règles du jeu à la place ici dans le futur
                        	partie.tas.ajouter_tas(sel_carte);
                        	partie.tas.ajouter_tas(sel_carte2);
                            
                            //joue la carte non graphiquement
                            jBot.getMain().jouerCarte(sel_carte);
                            jBot.getMain().jouerCarte(sel_carte2);
                            
                            //pose graphiquement la carte sur le Tas
                            JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
                            labelCarteJouer.setBackground(couleur);
                            jpTas.add(labelCarteJouer);
                            JButton labelCarteJouer2 = new JButton(sel_carte2.imagecarte());
                            labelCarteJouer2.setBackground(couleur);
                            jpTas.add(labelCarteJouer2);
                            
                            //retire graphiquement la carte qui a été jouée
                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));                                
                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte2));      
                           
                            //set le meneur du tour
                            partie.tour.setMeneur(jBot.getNom());

                            //on retire la non graphiquement de la main
                            jBot.getMain().effacerCarte(sel_carte);
                            jBot.getMain().effacerCarte(sel_carte2);
                            
                            //actualise le jeux
                           
                            listePanelIA.get(nb).repaint();
                            listePanelIA.get(nb).revalidate();
                            jpTas.repaint();
                            frame.revalidate();
                            frame.repaint();
                            // Mettre à true pour indiquer qu'une carte a été jouée
                            carteJouer = true;
                            
                            partie.tour.rythmeTour(partie.tas, jBot);
                			partie.setaJouer(true);

                        }
                    }
                }
            }

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        } 
    }
    
    public void bot_serie3(Partie partie, JFrame frame, JPanel jpTas, Joueur jBot,Color couleur) {
        if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */
        	System.out.println("METHODE BOT_SERIE3 : "+partie.tour.getRythme());

        	int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                Iterator<Carte> it2 = jBot.getMain().getMain().iterator();
                while(it2.hasNext() && !carteJouer){
                    Carte sel_carte2 = it2.next();
                    
                    Iterator<Carte> it3 = jBot.getMain().getMain().iterator();
                    while(it3.hasNext() && !carteJouer){
                        Carte sel_carte3 = it3.next();
                        
	                    if((sel_carte.getNumero()+1 == sel_carte2.getNumero() && sel_carte2.getNumero()+1 == sel_carte3.getNumero() && sel_carte.getCouleur()!=sel_carte2.getCouleur() && sel_carte.getCouleur()!=sel_carte3.getCouleur() && sel_carte2.getCouleur()!=sel_carte3.getCouleur())) {
	                        if(peut_jouerSerie3(sel_carte, partie)){
	                        	
	                            //condition des règles du jeu à la place ici dans le futur
	                        	partie.tas.ajouter_tas(sel_carte);
	                        	partie.tas.ajouter_tas(sel_carte2);
	                        	partie.tas.ajouter_tas(sel_carte3);
	                            
	                            //joue la carte non graphiquement
	                            jBot.getMain().jouerCarte(sel_carte);
	                            jBot.getMain().jouerCarte(sel_carte2);
	                            jBot.getMain().jouerCarte(sel_carte3);
	                            
	                            //pose graphiquement la carte sur le Tas
	                            JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
	                            labelCarteJouer.setBackground(couleur);
	                            jpTas.add(labelCarteJouer);
	                            JButton labelCarteJouer2 = new JButton(sel_carte2.imagecarte());
	                            labelCarteJouer2.setBackground(couleur);
	                            jpTas.add(labelCarteJouer2);
	                            JButton labelCarteJouer3 = new JButton(sel_carte3.imagecarte());
	                            labelCarteJouer3.setBackground(couleur);
	                            jpTas.add(labelCarteJouer3);
	                            
	                            //retire graphiquement la carte qui a été jouée
	                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));                                
	                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte2));  
	                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte3));      
	                            
	                            //set le meneur du tour
	                            partie.tour.setMeneur(jBot.getNom());
	
	                            //on retire la non graphiquement de la main
	                            jBot.getMain().effacerCarte(sel_carte);
	                            jBot.getMain().effacerCarte(sel_carte2);
	                            jBot.getMain().effacerCarte(sel_carte3);
	                            
	                            //actualise le jeux
	                           
	                            listePanelIA.get(nb).repaint();
	                            listePanelIA.get(nb).revalidate();
	                            jpTas.repaint();
	                            frame.revalidate();
	                            frame.repaint();
	                            // Mettre à true pour indiquer qu'une carte a été jouée
	                            carteJouer = true;
	                            
	                            partie.tour.rythmeTour(partie.tas, jBot);
                    			partie.setaJouer(true);

	                        }
	                    }
                    }
                }
            }

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        } 
    }
    
    public void bot_serie4(Partie partie, JFrame frame, JPanel jpTas, Joueur jBot,Color couleur) {
        if (jBot.getCan_Play()) {
        	/*Variable pour suivre si une carte a été jouée
        	 * cela permet d'éviter que le bot joue malgré qu'il est couché
        	 */
        	System.out.println("METHODE BOT_SERIE4 : "+partie.tour.getRythme());

        	int nb = cquelbot(jBot);
            boolean carteJouer = false;
            // Ajouter !carteJouee pour sortir de la boucle si une carte est jouée
            Iterator<Carte> it = jBot.getMain().getMain().iterator();
            while(it.hasNext() && !carteJouer){
                Carte sel_carte = it.next();

                Iterator<Carte> it2 = jBot.getMain().getMain().iterator();
                while(it2.hasNext() && !carteJouer){
                    Carte sel_carte2 = it2.next();
                    
                    Iterator<Carte> it3 = jBot.getMain().getMain().iterator();
                    while(it3.hasNext() && !carteJouer){
                        Carte sel_carte3 = it3.next();
                        
                        Iterator<Carte> it4 = jBot.getMain().getMain().iterator();
                        while(it4.hasNext() && !carteJouer){
                            Carte sel_carte4 = it4.next();
                        
		                    if((sel_carte.getNumero()+1 == sel_carte2.getNumero() && sel_carte2.getNumero()+1 == sel_carte3.getNumero() && sel_carte3.getNumero()+1 == sel_carte4.getNumero() && sel_carte.getCouleur()!=sel_carte2.getCouleur() && sel_carte.getCouleur()!=sel_carte3.getCouleur() && sel_carte2.getCouleur()!=sel_carte3.getCouleur() && sel_carte.getCouleur()!=sel_carte4.getCouleur() && sel_carte2.getCouleur()!=sel_carte4.getCouleur() && sel_carte3.getCouleur()!=sel_carte4.getCouleur())) {
		                        if(peut_jouerSerie4(sel_carte, partie)){
		                        	
		                            //condition des règles du jeu à la place ici dans le futur
		                        	partie.tas.ajouter_tas(sel_carte);
		                        	partie.tas.ajouter_tas(sel_carte2);
		                        	partie.tas.ajouter_tas(sel_carte3);
		                        	partie.tas.ajouter_tas(sel_carte4);
		                        	
		                            //joue la carte non graphiquement
		                            jBot.getMain().jouerCarte(sel_carte);
		                            jBot.getMain().jouerCarte(sel_carte2);
		                            jBot.getMain().jouerCarte(sel_carte3);
		                            jBot.getMain().jouerCarte(sel_carte4);
		                            
		                            //pose graphiquement la carte sur le Tas
		                            JButton labelCarteJouer = new JButton(sel_carte.imagecarte());
		                            labelCarteJouer.setBackground(couleur);
		                            jpTas.add(labelCarteJouer);
		                            JButton labelCarteJouer2 = new JButton(sel_carte2.imagecarte());
		                            labelCarteJouer2.setBackground(couleur);
		                            jpTas.add(labelCarteJouer2);
		                            JButton labelCarteJouer3 = new JButton(sel_carte3.imagecarte());
		                            labelCarteJouer3.setBackground(couleur);
		                            jpTas.add(labelCarteJouer3);
		                            JButton labelCarteJouer4 = new JButton(sel_carte4.imagecarte());
		                            labelCarteJouer4.setBackground(couleur);
		                            jpTas.add(labelCarteJouer4);
		                            
		                            //retire graphiquement la carte qui a été jouée
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte));                                
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte2));  
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte3));    
		                            listePanelIA.get(nb).remove(listeHashMapCarteBot.get(nb).get(sel_carte4));     
		                            
		                            //set le meneur du tour
		                            partie.tour.setMeneur(jBot.getNom());
		
		                            //on retire la non graphiquement de la main
		                            jBot.getMain().effacerCarte(sel_carte);
		                            jBot.getMain().effacerCarte(sel_carte2);
		                            jBot.getMain().effacerCarte(sel_carte3);
		                            jBot.getMain().effacerCarte(sel_carte4);
		                            
		                            //actualise le jeux
		                           
		                            listePanelIA.get(nb).repaint();
		                            listePanelIA.get(nb).revalidate();
		                            jpTas.repaint();
		                            frame.revalidate();
		                            frame.repaint();
		                            // Mettre à true pour indiquer qu'une carte a été jouée
		                            carteJouer = true;
		                            
		                            partie.tour.rythmeTour(partie.tas, jBot);
	                    			partie.setaJouer(true);

		                        }
		                    }
                        }
                    }
                }
            }

            // Si aucune carte n'a été jouée, l'IA ne peut pas jouer
            if (!carteJouer) {
                jBot.setCan_Play(false);
            }
        } 
    }
    
    public int cquelbot(Joueur jbot) {
        int nb = 0;
        for (int i = 0; i < listePanelIA.size(); i++) {
            if (i == 0 && jbot.getNom() == "IA1") {
                nb = 0;
                break; // If the condition is met, exit the loop
            } else if (i == 1 && jbot.getNom() == "IA2") {
                nb = 1;
            } else if (i == 2 && jbot.getNom() == "IA3") {
                nb = 2;
            } else if (i == 3 && jbot.getNom() == "IA4") {
                nb = 3;
            }
        }
        return nb;
    }
    
    public void jouerAleatoire(AccueilGUI accueil,PartieGUI partiegui,Partie partie,ActionMain actionmain,JFrame frame, JPanel jpTas, Joueur jBot, Color couleur, JPanel jpJ1)  {
    	/* @description :
    	 * @param : accueil (AccueilGUI) = permet de recuperer le nombre de Bot dans la partie etc...
    	 * @param : partiegui (PartieGUI) = permet de recuperer les panel des bots etc...
    	 * @param : partie (Partie) = partie du jeu
    	 * @param : actionmain (AtionMain) = permet de gérer le J1
    	 * @param : frame (JFrame) = frame de jeu 
    	 * @param : jpTas (JPanel) = panel du tas de jeu 
    	 * @param : jbot (Joueur) = permet de faire jouer le bot actuelle
    	 * @param : couleur (Color) = permet de gerer la couleur du bot actuel 
    	 * @param : jpJ1 (JPanel) = recup le panel du joueur 1
    	 */
    	Random nb = new Random();
        int nbRandom = nb.nextInt(2);
        switch (nbRandom) {
	        case 0 : 
	        	partie.tour.setRythme("Simple");
	        	break;
	        case 1 :
	        	partie.tour.setRythme("Double");
	        	break;
	        case 2 :
	        	partie.tour.setRythme(Integer.toString(nbRandom));
	        	break;	
	        case 3 :
	        	partie.tour.setRythme(Integer.toString(nbRandom-1));
            	break;
            case 4 :
	        	partie.tour.setRythme(Integer.toString(nbRandom-1));
            	break;
            case 5 :
	        	partie.tour.setRythme(Integer.toString(nbRandom-1));
            	break;
             default : 
            	 break;
        }
        jouer_bot(accueil,partiegui,partie,actionmain,frame,jpTas,jBot,couleur,jpJ1);
    	if (!jBot.getCan_Play()) {
        	partie.tour.setRythme("Simple");
    		jBot.setCan_Play(true);
            jouer_bot(accueil,partiegui,partie,actionmain,frame,jpTas,jBot,couleur,jpJ1);
    	}
    }
   
    public void systemeBombe(Partie partie, JFrame frame,JPanel jpTas,Joueur joueur) {
    	/* @description : permet de vérifier si un joueur autre que le joueur actif à une bombe
    	 * @param : partie (Partie) = partie du jeu
    	 * @param : frame (JFrame) = frame de jeu 
    	 * @param : jpTas (JPanel) = panel du tas de jeu 
    	 * @param : joueur (Joueur) = joueur actif
    	 */
    	
    	System.out.println("----------methode systemeBombe----------");
    	
    	String tmp = partie.tour.getRythme(); //ancient rythme
    	partie.tour.setRythme("Bombe3");
    	switch (joueur.getNom()) {
    	case "IA1" :
    		if (partie.getIA2() != null && partie.getIA2().getCan_Play() && partie.tour.getRythme() == "Bombe3") { 
    		    bot_bombe3(partie, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2);
    		    partie.getIA2().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2);
    		    partie.getIA2().setCan_Play(false);
    		    break;
    		} 
    		
    		if (partie.getIA3() != null && partie.getIA3().getCan_Play() && partie.tour.getRythme() == "Bombe3") {
    		    bot_bombe3(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);  
    		    partie.getIA3().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);
    		    break;
    		}
    		
    		if (partie.getIA4() != null && partie.getIA4().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
    		    bot_bombe3(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);
    		    break;
    		}
    		break;
    		
    	case "IA2" :
    		if (partie.getIA3() != null && partie.getIA3().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);
    		    break;
    		}
    		
    		if (partie.getIA4() != null && partie.getIA4().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);
    		    break;
    		}
    		break;
    		
		case "IA3" :
    		if (partie.getIA4() != null && partie.getIA4().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);
    		    break;
    		}
    		break;
    		
    	case "IA4" :
    		if (partie.getIA1() != null && partie.getIA1().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA1(), GameConfiguration.COULEUR_BOT1);
    		    partie.getIA1().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA1(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA1().setCan_Play(false);
    		    break;
    		}
    		
    		if (partie.getIA2() != null && partie.getIA2().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2);
    		    partie.getIA2().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2);
    		    partie.getIA2().setCan_Play(false);
    		    break;
    		}
    		
    		if (partie.getIA3() != null && partie.getIA3().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);

    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);
    		    break;
    		}
    		break;
    		
    	case "J1" :
    		if (partie.getIA1() != null && partie.getIA1().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA1(), GameConfiguration.COULEUR_BOT1);
    		    partie.getIA1().setCan_Play(false);
    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA1(), GameConfiguration.COULEUR_BOT1);
    		    partie.getIA1().setCan_Play(false);
    		    break;
    		}
    		
    		if (partie.getIA2() != null && partie.getIA2().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2);
    		    partie.getIA2().setCan_Play(false);
    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA2(), GameConfiguration.COULEUR_BOT2);
    		    partie.getIA2().setCan_Play(false);
    		    break;
    		} 
    		
    		if (partie.getIA3() != null && partie.getIA3().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);
    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA3(), GameConfiguration.COULEUR_BOT3);
    		    partie.getIA3().setCan_Play(false);
    		    break;   
    		}
    		
    		if (partie.getIA4() != null && partie.getIA4().getCan_Play() &&  partie.tour.getRythme() == "Bombe3") {
	    		bot_bombe3(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);
    		} else if (partie.tour.getRythme() == "Bombe4") {
    		    bot_bombe4(partie, frame, jpTas, partie.getIA4(), GameConfiguration.COULEUR_BOT4);
    		    partie.getIA4().setCan_Play(false);
    		    break;
    		}
    		break;
    		
    	default :
    		break;
    	}
    	partie.tour.setRythme(tmp); //remet ancient rythme
    }
}