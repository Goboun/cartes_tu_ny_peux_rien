package gui;


import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.sound.sampled.*;
import javax.swing.*;

import config.GameConfiguration;
import data.Carte;
import process.*;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PartieGUI {

	public JFrame jfPartie = new JFrame();
	public JFrame frameStat = new JFrame();

	//Sur la frame
	private JLabel jlTour = new JLabel("Tour : ");
	private JLabel jlPioche = new JLabel("Pioche");
	private JPanel jpPioche = new JPanel();
	private JPanel jpTas = new JPanel();
	//Panel IA
	private JPanel jpIA = new JPanel();
	private JLabel jlIA1 = new JLabel("IA1");
	private JPanel jpIA1 = new JPanel();
	private JLabel jlIA2 = new JLabel("IA2");
	private JPanel jpIA2 = new JPanel();
	private JLabel jlIA3 = new JLabel("IA3");
	private JPanel jpIA3 = new JPanel();
	private JLabel jlIA4 = new JLabel("IA4");
	private JPanel jpIA4 = new JPanel();
	//Panel Options
	private JPanel jpOptions = new JPanel();
	public JButton playButton = new JButton("Jouer");
	public JButton jbSeCoucher = new JButton("Se Coucher");
	private JButton jbQuitter = new JButton("Quitter");
	public JButton jbPasser = new JButton("Passer");
	//Panel Joueur
	private JPanel jpZoneJ1 = new JPanel();
	private JPanel jpJ1 = new JPanel();
	private Partie partie;
	private HashMap<Carte, JButton> cartes_bot1 = new HashMap<>(); 
	private HashMap<Carte, JButton> cartes_bot2 = new HashMap<>(); 
	private HashMap<Carte, JButton> cartes_bot3 = new HashMap<>(); 
	private HashMap<Carte, JButton> cartes_bot4 = new HashMap<>(); 
	//gestion panel
	private ArrayList<JPanel> listePanelIA = new ArrayList<>();
	private ArrayList<HashMap<Carte, JButton>> listeHashMapCarteBot = new ArrayList<>();
	
	public PartieGUI() {
	}

	public void play(AccueilGUI accueil) {
		partie = new Partie(accueil.nbBot());
		jlTour.setText(jlTour.getText() + partie.getTour().getNbTour());

		//On affiche leurs cartes
		//Supprimer la carte sélectionné dans le GUI
		jpJ1.removeAll();
		jpIA1.removeAll();
		jfPartie.validate();
		jfPartie.repaint();

		//On affiche les cartes des robots dans le GUI
		switch (accueil.nbBot()) {
			case 4 :
				for (Carte carteIA4 : partie.getIA4().getMain().getMain()) {
					JButton carteButton4 = null;
					switch (accueil.getMode2jeu()) {
					case GameConfiguration.CARTE_CACHEE :
						carteButton4 =new JButton(carteIA4.carte_dos());
						break;
					case GameConfiguration.CARTE_VISIBLE :
						carteButton4 = new JButton(carteIA4.imagecarte());
						break;
					}
					carteButton4.setBorder(null);
					cartes_bot4.put(carteIA4, carteButton4);
					jpIA4.add(carteButton4);
				}
			case 3 :
				for (Carte carteIA3 : partie.getIA3().getMain().getMain()) {
					JButton carteButton3 = null;
					switch (accueil.getMode2jeu()) {
					case GameConfiguration.CARTE_CACHEE :
						carteButton3 =new JButton(carteIA3.carte_dos());
						break;
					case GameConfiguration.CARTE_VISIBLE :
						carteButton3 = new JButton(carteIA3.imagecarte());
						break;
					}
					carteButton3.setBorder(null);
					cartes_bot3.put(carteIA3, carteButton3);
					jpIA3.add(carteButton3);
				}
			case 2 :
				for (Carte carteIA2 : partie.getIA2().getMain().getMain()) {
					JButton carteButton2 = null;
					switch (accueil.getMode2jeu()) {
					case GameConfiguration.CARTE_CACHEE :
						carteButton2 =new JButton(carteIA2.carte_dos());
						break;
					case GameConfiguration.CARTE_VISIBLE :
						carteButton2 = new JButton(carteIA2.imagecarte());
						break;
					}
					carteButton2.setBorder(null);
					cartes_bot2.put(carteIA2, carteButton2);
					jpIA2.add(carteButton2);
				}
			case 1 : 
				for (Carte carteIA1 : partie.getIA1().getMain().getMain()) {
					JButton carteButton = null;
					switch (accueil.getMode2jeu()) {
					case GameConfiguration.CARTE_CACHEE :
						carteButton =new JButton(carteIA1.carte_dos());
						break;
					case GameConfiguration.CARTE_VISIBLE :
						carteButton = new JButton(carteIA1.imagecarte());
						break;
					}
					carteButton.setBorder(null);
					cartes_bot1.put(carteIA1, carteButton);
					jpIA1.add(carteButton);
				}
			default :
				break;
		}
		
		ActionMain action_principale = new ActionMain(partie,jfPartie, jpTas, jpJ1);		
		ActionBot actionbot = new ActionBot(listePanelIA, listeHashMapCarteBot);
		SystemePlay sp = new SystemePlay();
		
		//Bouton Jouer
        playButton.setForeground(Color.ORANGE);
        playButton.setFont(new Font("Eurostile", Font.PLAIN, 25));
		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                sp.test1(accueil, PartieGUI.this, partie, action_principale, actionbot, jfPartie, jpTas, jpJ1);
			}
		});
		
		//Bouton Se coucher
		jbSeCoucher.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jbSeCoucher.setForeground(Color.ORANGE);
		jbSeCoucher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				partie.getJ1().setCan_Play(false);
				jbPasser.setEnabled(true);
				jbSeCoucher.setEnabled(false);
				playButton.setEnabled(false);
				sp.test1(accueil, PartieGUI.this, partie, action_principale, actionbot, jfPartie, jpTas, jpJ1);
				System.out.println("ah bout t nul ty n'y py ryen "+partie.getJ1().getNom() +  " " + partie.getJ1().getCan_Play());
			}
		});
		
		//Bouton Faire passer cycle
		jbPasser.setForeground(Color.ORANGE);
		jbPasser.setFont(new Font("Eurostile", Font.PLAIN, 20));
		jbPasser.setEnabled(false);
		jbPasser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sp.test1(accueil, PartieGUI.this, partie, action_principale, actionbot, jfPartie, jpTas, jpJ1);
			}
		});
		
	}

	public void initialize(AccueilGUI accueil) {

		//FENETRE
		jfPartie.setTitle("En Partie8");
		jfPartie.getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 13));
		jfPartie.getContentPane().setBackground(new Color(0, 128, 0));
		jfPartie.setBounds(100, 100, 1271, 713);
		jfPartie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfPartie.getContentPane().setLayout(null);

		//Label Tour
		jlTour.setForeground(Color.WHITE);
		jlTour.setFont(new Font("Downtempo", Font.PLAIN, 25));
		jlTour.setBounds(1128, 16, 240, 57);
		jfPartie.getContentPane().add(jlTour);

		//Label Pioche
		jlPioche.setBounds(475, 332, 88, 29);
		jlPioche.setForeground(Color.WHITE);
		jfPartie.getContentPane().add(jlPioche);

		//Panel Pioche
		jpPioche.setBackground(new Color(0, 128, 0));
		jpPioche.setBounds(460, 200, 109, 132);
		jlPioche.setFont(new Font("Eurostile", Font.PLAIN, 25));
		Carte carte = new Carte(1,"rouge");
		JButton carteTas = new JButton(carte.carte_dos());
		carteTas.setBorder(null);
		jpPioche.add(carteTas);
		jfPartie.getContentPane().add(jpPioche);

		//Panel Tas
		jpTas.setBounds(568, 69, 658, 430);
		jpTas.setBackground(new Color(0, 128, 0));
		jfPartie.getContentPane().add(jpTas);
		
		//Panel IA
		jpIA.setBackground(Color.LIGHT_GRAY);
		jpIA.setBounds(0, 0, 455, 535);
		jpIA.setLayout(new GridLayout(0, 2, -373, 0));
		jfPartie.getContentPane().add(jpIA);

		//Label IA1
		jlIA1.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jlIA1.setForeground(Color.RED);
		jpIA.add(jlIA1);

		//Panel IA1
		jpIA1.setBackground(new Color(139, 0, 0));
		jpIA1.setPreferredSize(new Dimension(0,1000)); //1000 c'est pour la hauteur du panel et donc la barre défilante
		JScrollPane jspIA1 = new JScrollPane(jpIA1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspIA1.getVerticalScrollBar().setUnitIncrement(5);
		jpIA.add(jspIA1);

		//Label IA2
		jlIA2.setForeground(Color.BLUE);
		jlIA2.setFont(new Font("Eurostile", Font.PLAIN, 25));

		//Panel IA2
		jpIA2.setBackground(new Color(0, 0, 139));
		jpIA2.setPreferredSize(new Dimension(0,1000)); //1000 c'est pour la hauteur du panel et donc la barre défilante
		JScrollPane jspIA2 = new JScrollPane(jpIA2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspIA2.getVerticalScrollBar().setUnitIncrement(5);
		
		//Label IA3
		jlIA3.setForeground(Color.GREEN);
		jlIA3.setFont(new Font("Eurostile", Font.PLAIN, 25));

		//Panel IA3
		jpIA3.setBackground(new Color(60, 179, 113));
		jpIA3.setPreferredSize(new Dimension(0,1000)); //1000 c'est pour la hauteur du panel et donc la barre défilante
		JScrollPane jspIA3 = new JScrollPane(jpIA3, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspIA3.getVerticalScrollBar().setUnitIncrement(5);

		//Label IA4
		jlIA4.setForeground(Color.YELLOW);
		jlIA4.setFont(new Font("Eurostile", Font.PLAIN, 25));

		//Panel IA4
		jpIA4.setBackground(new Color(184, 134, 11));
		jpIA4.setPreferredSize(new Dimension(0,1000)); //1000 c'est pour la hauteur du panel et donc la barre défilante
		JScrollPane jspIA4 = new JScrollPane(jpIA4, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspIA4.getVerticalScrollBar().setUnitIncrement(5);

		//Pourquoi on break ? A cause du layout qu'on utilise car sinon ça fait IA1, IA4, IA3, IA2 pour 4 bots
		switch (accueil.nbBot()) {
		    case 4:
		    	jpIA.add(jlIA2);
		        jpIA.add(jspIA2);
		    	jpIA.add(jlIA3);
		        jpIA.add(jspIA3);
		        jpIA.add(jlIA4);
		        jpIA.add(jspIA4);
		        break;
		    case 3:
		    	jpIA.add(jlIA2);
		        jpIA.add(jspIA2);
		        jpIA.add(jlIA3);
		        jpIA.add(jspIA3);
		        break;
		    case 2:
		        jpIA.add(jlIA2);
		        jpIA.add(jspIA2);
		        break;
		    default:
		        break;
		}
				
		//Panel Zone Joueur
		jpZoneJ1.setBounds(0, 535, 1271, 146);
		jpZoneJ1.setLayout(new BorderLayout(0, 0));
		jfPartie.getContentPane().add(jpZoneJ1);
		
		//Panel Options
		jpOptions.setLayout(new GridLayout(4, 1, 0, 0));
		jpOptions.setBackground(Color.DARK_GRAY);
		jpOptions.setBorder(BorderFactory.createEmptyBorder(20,137,20,137));
		jpZoneJ1.add(jpOptions, BorderLayout.WEST);
		//Important d'ajouter les boutons dans cet ordre là
		jpOptions.add(playButton);
		jpOptions.add(jbSeCoucher);
		jpOptions.add(jbPasser);
		
		
		//Bouton Quitter
		jbQuitter.setForeground(Color.ORANGE);
		jbQuitter.setFont(new Font("Eurostile", Font.PLAIN, 20));
		jbQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfPartie.dispose();
				System.exit(0);			}
		});
		jpOptions.add(jbQuitter);
		
		//Panel Joueur
		jpJ1.setBackground(Color.LIGHT_GRAY);
		jpJ1.setPreferredSize(new Dimension(0,1000)); //1000 c'est pour la hauteur du panel et donc la barre défilante
		JScrollPane jspJ1 = new JScrollPane(jpJ1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jspJ1.getVerticalScrollBar().setUnitIncrement(5); //Pour la vitesse du scroll, changer la valeur
		jpZoneJ1.add(jspJ1, BorderLayout.CENTER);
		
		//test
    	listePanelIA.add(jpIA1);
    	listePanelIA.add(jpIA2);
    	listePanelIA.add(jpIA3);
    	listePanelIA.add(jpIA4);

    	listeHashMapCarteBot.add(cartes_bot1);
    	listeHashMapCarteBot.add(cartes_bot2);
    	listeHashMapCarteBot.add(cartes_bot3);
    	listeHashMapCarteBot.add(cartes_bot4);
	}		

	public void vainqueur() {
		ImageIcon iconWin = new ImageIcon();
		iconWin = new ImageIcon("src/images/win.png");
		JLabel win = new JLabel(iconWin);
		JPanel jpWin = new JPanel();
		jpWin.add(win);
		jpWin.setBounds(650, 80, 400, 250);
		jpWin.setBackground(new Color(0, 128, 0));
		jfPartie.getContentPane().remove(jpPioche);
		jfPartie.getContentPane().remove(jlPioche);
		jfPartie.getContentPane().remove(jpTas);
		playButton.setEnabled(false);
		jbSeCoucher.setEnabled(false);
		jbPasser.setEnabled(false);
		jfPartie.getContentPane().add(jpWin);
		jfPartie.revalidate();
		jfPartie.repaint();
		try {
            File soundFile = new File("src/images/brawl-stars-win.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}	

	public void perdant() {
		ImageIcon iconLose = new ImageIcon();
		iconLose = new ImageIcon("src/images/lose.png");
		JLabel lose = new JLabel(iconLose);
		JPanel jpLose = new JPanel();
		jpLose.add(lose);
		jpLose.setBounds(710, 105, 400, 250);
		jpLose.setBackground(new Color(0, 128, 0));
		jfPartie.getContentPane().remove(jpPioche);
		jfPartie.getContentPane().remove(jlPioche);
		jfPartie.getContentPane().remove(jlTour);
		jfPartie.getContentPane().remove(jpTas);
		playButton.setEnabled(false);
		jbSeCoucher.setEnabled(false);
		jbPasser.setEnabled(false);
		jfPartie.getContentPane().add(jpLose);
		jfPartie.revalidate();
		jfPartie.repaint();
		try {
            File soundFile = new File("src/images/brawl-stars-defeat.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    public ArrayList<JPanel> getListePanelIA() {
    	return this.listePanelIA;
    }
    public ArrayList<HashMap<Carte, JButton>> getListeHashMapCarteBot() {
    	return this.listeHashMapCarteBot;
    } 
    public JLabel getJlTour() {
    	return this.jlTour;
    }
	
}