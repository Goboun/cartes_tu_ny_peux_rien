package gui;


import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import glp.ActionMain;
import glp.Carte;
import glp.Deck;
import glp.Joueur;
import glp.Partie;
import glp.Tour;

import java.util.Random;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;


public class PartieGUI {

	public JFrame jfPartie = new JFrame();
	//Sur la frame
	private JLabel jlTour = new JLabel("TOURS ");
	private JLabel jlPioche = new JLabel("Pioche");
	private JPanel jpPioche = new JPanel();
	private JLabel jlTas = new JLabel("Tas");
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
	private JButton jbPoser = new JButton("POSER");
	private JButton jbSeCoucher = new JButton("Se Coucher");
	private JButton jbQuitter = new JButton("Quitter");
	//Panel Joueur
	private JPanel jpJ1 = new JPanel();

	private Partie partie;
	private HashMap<Carte, JButton> cartes_b = new HashMap<>(); 

	public PartieGUI() {
	}

	public void play() {
		partie = new Partie();

		//Chaque joueur pioche une carte pour commencer un tour
		//J1
		Carte carte = partie.pioche.piocherCarte();
		partie.J1.getMain().piocherCarte(carte);
		//J2
		carte = partie.pioche.piocherCarte();
		partie.IA1.getMain().piocherCarte(carte);

		//On affiche leurs cartes
		//Supprimer la carte sélectionné dans le GUI
		jpJ1.removeAll();
		jpIA1.removeAll();
		jfPartie.validate();
		jfPartie.repaint();

		//On affiche les cartes de J2 dans le GUI	
		for (Carte carteIA1 : partie.getIA1().getMain().getMain()) {
			JButton carteButton2 = new JButton(carteIA1.imagecarte());
			carteButton2.setBorder(null);
			cartes_b.put(carteIA1, carteButton2);
			jpIA1.add(carteButton2);
		}

		ActionMain mainJ1 = new ActionMain(jfPartie, jpOptions, jpTas, jpJ1, partie.pioche, partie.getJ1());
		/*//On affiche les cartes cliquable de j1 dans le GUI	
		for (Carte carteJ1 : partie.getJ1().getMain().getMain()) {
			JButton carteButton = new JButton(carteJ1.imagecarte()); 
			carteButton.setBorder(null); // enlève le contour du boutton autour de la carte
			carteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Affiche la carte sélectionnée dans le panel1
					JLabel carteJouer = new JLabel(carteJ1.imagecarte());
					jpTas.removeAll();		 // Effacer le contenu précédent
					jpTas.add(carteJouer);	 // ajoute la carte dans le panel
					frmCartes.validate();    //valide les changement
					frmCartes.repaint();	 //reactualise la frame de partie

					Carte carte1 = partie.J1.getMain().jouerCarte(carteJ1);
					partie.tas.jouerCarte(carte1);

					// Supprimer la carte séléctionné dans le GUI
					cartesJ1.remove(carteButton);
					frmCartes.validate();
					frmCartes.repaint();

					if(partie.J1.getMain().getMain().isEmpty()) {
						vainqueur();
					}

					//Action du J2
					//JLabel carteJouer2 = new JLabel(carte.imagecarte());
					for(Iterator<Carte> it = partie.IA1.getMain().getMain().iterator(); it.hasNext(); ) {
						Carte dernier = it.next();
						if(partie.tas.getDernier().getNumero() +1 == dernier.getNumero()) {
							jpTas.removeAll();		 // Effacer le contenu précédent
							jpTas.add(cartes_b.get(dernier));	 // ajoute la carte dans le panel
							frmCartes.validate();    //valide les changement
							frmCartes.repaint();	 //reactualise la frame de partie

							Carte carteTas = partie.IA1.getMain().jouerCarte(dernier);
							partie.tas.jouerCarte(carteTas);

							// Supprimer la carte séléctionné dans le JPanel ET l'ArrayList<JButton>
							cartesIA1.remove(cartes_b.get(carteTas));
							cartes_b.remove(carteTas);
							frmCartes.validate();
							frmCartes.repaint();

							if(partie.IA1.getMain().getMain().isEmpty()) {
								nul();
							}
							break;
						}
					}
				}
			});
			cartesJ1.add(carteButton);
		} */
	}

	public void initialize() {

		//FENETRE
		jfPartie.setTitle("En Partie");
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
		jlPioche.setBounds(629, 332, 88, 29);
		jlPioche.setForeground(Color.WHITE);
		jfPartie.getContentPane().add(jlPioche);

		//Panel Pioche
		jpPioche.setBackground(Color.WHITE);
		jpPioche.setBounds(619, 189, 109, 132);
		jlPioche.setFont(new Font("Eurostile", Font.PLAIN, 25));
		Carte carte = new Carte(1,"rouge");
		JButton carteTas = new JButton(carte.carte_dos());
		carteTas.setBorder(null);
		jpPioche.add(carteTas);
		jfPartie.getContentPane().add(jpPioche);

		//Label Tas
		jlTas.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jlTas.setBounds(964, 330, 73, 32);
		jlTas.setForeground(Color.WHITE);
		jfPartie.getContentPane().add(jlTas);

		//Panel Tas
		jpTas.setBounds(928, 189, 109, 132);
		jpTas.setBackground(Color.DARK_GRAY);

		jfPartie.getContentPane().add(jpTas);
		

		//Panel IA
		jpIA.setBackground(Color.LIGHT_GRAY);
		jpIA.setBounds(0, 0, 368, 521);
		jpIA.setLayout(null);
		jfPartie.getContentPane().add(jpIA);

		//Label IA1
		jlIA1.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jlIA1.setForeground(Color.RED);
		jlIA1.setBounds(24, 59, 84, 38);
		jpIA.add(jlIA1);

		//Panel IA1
		jpIA1.setBounds(78, 44, 262, 115);
		jpIA.add(jpIA1);
		jpIA1.setBackground(new Color(139, 0, 0));

		//Label IA2
		jlIA2.setForeground(Color.BLUE);
		jlIA2.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jlIA2.setBounds(24, 175, 84, 38);
		jpIA.add(jlIA2);

		//Panel IA2
		jpIA2.setBackground(new Color(0, 0, 139));
		jpIA2.setBounds(78, 160, 262, 115);
		//pour setBounds avant ct 75 maintenant c 115
		jpIA.add(jpIA2);

		//Label IA3
		jlIA3.setForeground(Color.GREEN);
		jlIA3.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jlIA3.setBounds(24, 290, 84, 38);
		jpIA.add(jlIA3);

		//Panel IA3
		jpIA3.setBackground(new Color(60, 179, 113));
		jpIA3.setBounds(78, 277, 262, 115);
		jpIA.add(jpIA3);

		//Label IA4
		jlIA4.setForeground(Color.YELLOW);
		jlIA4.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jlIA4.setBounds(24, 416, 84, 38);
		jpIA.add(jlIA4);

		//Panel IA4
		jpIA4.setBackground(new Color(184, 134, 11));
		jpIA4.setBounds(78, 397, 262, 115);
		jpIA.add(jpIA4);

		//Panel Options
		jpOptions.setBackground(Color.DARK_GRAY);
		jpOptions.setBounds(0, 522, 368, 163);
		jpOptions.setLayout(null);
		jfPartie.getContentPane().add(jpOptions);

		//Bouton Poser
		/*ce bouton est crée dans une autre classe*/

		//Bouton Se coucher
		jbSeCoucher.setFont(new Font("Eurostile", Font.PLAIN, 25));
		jbSeCoucher.setForeground(Color.ORANGE);
		jbSeCoucher.setBounds(79, 67, 197, 40);
		jbSeCoucher.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

			}
		});
		jpOptions.add(jbSeCoucher);

		//Bouton Quitter
		jbQuitter.setForeground(Color.ORANGE);
		jbQuitter.setFont(new Font("Eurostile", Font.PLAIN, 20));
		jbQuitter.setBounds(115, 119, 119, 27);
		jbQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfPartie.dispose();
				System.exit(0);			}
		});
		jpOptions.add(jbQuitter);

		//Panel Joueur
		jpJ1.setBackground(Color.LIGHT_GRAY);
		jpJ1.setBounds(369, 522, 902, 163);
		jfPartie.getContentPane().add(jpJ1);
	}		

	public void vainqueur() {
		ImageIcon iconWin = new ImageIcon();
		iconWin = new ImageIcon("./images/win.png");
		JLabel win = new JLabel(iconWin);
		win.setBounds(200, 300, 729, 123);
		jfPartie.getContentPane().add(win);
	}

	public void nul() {
		ImageIcon iconLose = new ImageIcon();
		iconLose = new ImageIcon("./images/lose.png");
		JLabel lose = new JLabel(iconLose);
		lose.setBounds(200, 300, 729, 123);
		jfPartie.getContentPane().add(lose);
	}
}