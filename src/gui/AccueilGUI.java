package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import cpt.CompteurBorne;

public class AccueilGUI {
	private JFrame jfAccueil;

	//sert pour savoir si la frame est active
    private boolean estActive = false;

    public AccueilGUI() {

    }

    public boolean isActive() {
        return estActive;
    }

    public void ferme_accueil() {
		jfAccueil.setVisible(false);
        estActive = false;
    }

	public void app_run(){
		estActive = true;

		//Init la frame de menu
		jfAccueil = new JFrame("Menu");
		jfAccueil.setResizable(false);
		jfAccueil.getContentPane().setBackground(Color.DARK_GRAY);
		//déjà un titre au dessus
		//jfAccueil.setTitle("CARTES");
		jfAccueil.getContentPane().setLayout(new BorderLayout(10,10));
		jfAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfAccueil.setBounds(100, 100, 750, 666);

		jfAccueil.setVisible(true);

		//Panel du titre
		JPanel jpTitreMenu = new JPanel();
		jpTitreMenu.setForeground(Color.DARK_GRAY);
		jpTitreMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		jpTitreMenu.setBackground(Color.DARK_GRAY);
		jfAccueil.getContentPane().add(jpTitreMenu,BorderLayout.NORTH);
		
		//Label du titre
		JLabel jlJeuDeCartes = new JLabel("Tu ny peux rien !");
		jlJeuDeCartes.setForeground(Color.ORANGE);
		jlJeuDeCartes.setFont(new Font("Downtempo", Font.BOLD, 50));
		jpTitreMenu.add(jlJeuDeCartes);
		
		//Panel du menu
		JPanel jpBoutonsMenu = new JPanel();
		jpBoutonsMenu.setBackground(Color.DARK_GRAY);
		jpBoutonsMenu.setLayout(null);
		jfAccueil.getContentPane().add(jpBoutonsMenu,BorderLayout.CENTER);
		
		//////NOUVELLE PARTIE////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Panel Titre de Nouvelle Partie
        JPanel jpTitreNouvellePartie = new JPanel();
        jpTitreNouvellePartie.setForeground(Color.DARK_GRAY);
        jpTitreNouvellePartie.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        jpTitreNouvellePartie.setBackground(Color.DARK_GRAY);
        
        //Label Titre Réglages de la partie
        JLabel jlRéglagesDeLaPartie = new JLabel("Réglages de la partie");
        jlRéglagesDeLaPartie.setForeground(Color.ORANGE);
        jlRéglagesDeLaPartie.setFont(new Font("Downtempo", Font.BOLD, 50));
        jpTitreNouvellePartie.add(jlRéglagesDeLaPartie);
        
        //Panel Options
        JPanel jpOptions = new JPanel();
        jpOptions.setBackground(Color.DARK_GRAY);
        jpOptions.setLayout(null);

        //Mode de jeu
        String[] mode2jeux = {"Classique","Débutant"};
        
        //Label Mode de jeu
        JLabel jlModeDeJeu = new JLabel("Mode de jeu");
        jlModeDeJeu.setForeground(Color.ORANGE);
        jlModeDeJeu.setBounds(45, 120, 174, 37);
        jlModeDeJeu.setFont(new Font("Eurostile", Font.PLAIN, 25));
        jpOptions.add(jlModeDeJeu);
        
        //Liste déroulante Mode de jeu
        JComboBox<String> jcbModeDeJeu = new JComboBox<String>(mode2jeux);
        jcbModeDeJeu.setForeground(Color.ORANGE);
        jcbModeDeJeu.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jcbModeDeJeu.setBounds(40, 172, 158, 27);
        jpOptions.add(jcbModeDeJeu);
        
        //Compteur pour le nombre de bots
      	CompteurBorne cpt = new CompteurBorne(1,4,1);
      	
      	//Label Nombre de bots
        JLabel jlNombre = new JLabel("Nombre d'ordinateur");
        jlNombre.setForeground(Color.ORANGE);
        jlNombre.setBounds(233, 120, 293, 37);
        jlNombre.setFont(new Font("Eurostile", Font.PLAIN, 25));
        jpOptions.add(jlNombre);
        
        //Label Compteur
        JLabel jlcpt = new JLabel("" + cpt.getVal());
        jlcpt.setForeground(Color.ORANGE);
        jlcpt.setBounds(351, 162, 21, 45);
        jlcpt.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jpOptions.add(jlcpt);
        
        //Bouton Moins
        JButton jbMoins= new JButton("-");
        jbMoins.setForeground(Color.ORANGE);
        jbMoins.setBounds(284, 170, 42, 32);
        jbMoins.setFocusable(false);
        jbMoins.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jbMoins.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cpt.decrementer();
        		jlcpt.setText("" + cpt.getVal());
        	}
        });
        jpOptions.add(jbMoins);
        
        //Bouton Plus
        JButton jbPlus = new JButton("+");
        jbPlus.setForeground(Color.ORANGE);
        jbPlus.setBounds(391, 169, 42, 32);
        jbPlus.setFocusable(false);
        jbPlus.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jbPlus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cpt.incrementer();
        		jlcpt.setText("" + cpt.getVal());
        	}
        });
        jpOptions.add(jbPlus);
        
        //Stratégie
        String[] strategie = {"Aventure", "Réservé", "Équilibré"};
        
        //Label Stratégie
        JLabel jlStratégie = new JLabel("Stratégie");
        jlStratégie.setForeground(Color.ORANGE);
        jlStratégie.setBounds(560, 45, 128, 57);
        jlStratégie.setFont(new Font("Eurostile", Font.PLAIN, 25));
        jpOptions.add(jlStratégie);
        
        //Label IA1
        JLabel jlIA1 = new JLabel("IA 1");
        jlIA1.setForeground(Color.RED);
        jlIA1.setBounds(516, 92, 86, 57);
        jlIA1.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jpOptions.add(jlIA1);
        
        //Liste déroulante IA1
        JComboBox<String> jcbIA1 = new JComboBox<String>(strategie);
        jcbIA1.setForeground(Color.ORANGE);
        jcbIA1.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jcbIA1.setBounds(575, 108, 134, 27);
        jpOptions.add(jcbIA1);
        
        //Label IA2
        JLabel jlIA2 = new JLabel("IA 2");
        jlIA2.setForeground(Color.BLUE);
        jlIA2.setBounds(516, 140, 93, 57);
        jlIA2.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jpOptions.add(jlIA2);
        
        //Liste déroulante IA2
        JComboBox<String> jcbIA2 = new JComboBox<String>(strategie);
        jcbIA2.setForeground(Color.ORANGE);
        jcbIA2.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jcbIA2.setBounds(575, 156, 134, 27);
        jpOptions.add(jcbIA2);
        
        //Label IA3
        JLabel jlIA3 = new JLabel("IA 3");
        jlIA3.setForeground(Color.GREEN);
        jlIA3.setBounds(516, 187, 93, 57);
        jlIA3.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jpOptions.add(jlIA3);
        
        //Liste déroulante IA3
        JComboBox<String> jcbIA3 = new JComboBox<String>(strategie);
        jcbIA3.setForeground(Color.ORANGE);
        jcbIA3.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jcbIA3.setBounds(575, 203, 134, 27);
        jpOptions.add(jcbIA3);
        
        //Label IA4
        JLabel jlIA4 = new JLabel("IA 4");
        jlIA4.setForeground(Color.YELLOW);
        jlIA4.setBounds(516, 236, 93, 57);
        jlIA4.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jpOptions.add(jlIA4);
        
        //Liste déroulante IA4
        JComboBox<String> jcbIA4 = new JComboBox<String>(strategie);
        jcbIA4.setForeground(Color.ORANGE);
        jcbIA4.setFont(new Font("Eurostile", Font.PLAIN, 20));
        jcbIA4.setBounds(575, 252, 134, 27);
        jpOptions.add(jcbIA4);
        
        //Bouton Jouer
        JButton jbJouer = new JButton("Jouer");
        jbJouer.setBounds(227, 390, 140, 45);
        jbJouer.setForeground(Color.ORANGE);
        jbJouer.setFocusable(false);
        jbJouer.setFont(new Font("Eurostile", Font.PLAIN, 30));
        jbJouer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				ferme_accueil();
            	//lance la partie la
            
            }
        });
        jpOptions.add(jbJouer);
        
        //Bouton Retour en arrière de Nouvelle Partie
        JButton jbRetourNouvellePartie = new JButton("Retour");
        jbRetourNouvellePartie.setBounds(381, 390, 140, 45);
        jbRetourNouvellePartie.setForeground(Color.ORANGE);
        jbRetourNouvellePartie.setFocusable(false);
        jbRetourNouvellePartie.setFont(new Font("Eurostile", Font.PLAIN, 30));
        jbRetourNouvellePartie.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jfAccueil.getContentPane().removeAll();
        		jfAccueil.revalidate();
        		jfAccueil.repaint();
        		jfAccueil.getContentPane().add(jpTitreMenu,BorderLayout.NORTH);
        		jfAccueil.getContentPane().add(jpBoutonsMenu,BorderLayout.CENTER);
        		jfAccueil.revalidate();
        		jfAccueil.repaint();
        	}
        });
        jpOptions.add(jbRetourNouvellePartie);
        
        
        //////REGLES//////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //Panel Titre de Règles
        JPanel jpTitreRègles = new JPanel();
        JPanel jpRègles = new JPanel();
        jpTitreRègles.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
        jpTitreRègles.setBackground(Color.DARK_GRAY);
        jpRègles.setBackground(Color.DARK_GRAY);

        //Label Règles du jeu
        JLabel jlTitreRègles = new JLabel("Les règles du jeu");
        jlTitreRègles.setForeground(Color.ORANGE);
        jlTitreRègles.setFont(new Font("Downtempo", Font.BOLD, 50));
        jpTitreRègles.add(jlTitreRègles);
        jpRègles.setLayout(null);

        //Bouton Retour de Règles
        JButton jbRetourRègles = new JButton("Retour");
        jbRetourRègles.setForeground(Color.ORANGE);
        jbRetourRègles.setBounds(311, 391, 135, 43);
        jbRetourRègles.setFocusable(false); //enleve le carré en pointiller dégeulasse
        jbRetourRègles.setFont(new Font("Eurostile", Font.PLAIN, 30));
        jbRetourRègles.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jfAccueil.getContentPane().removeAll();
        		jfAccueil.revalidate();
        		jfAccueil.repaint();
        		jfAccueil.getContentPane().add(jpTitreMenu,BorderLayout.NORTH);
        		jfAccueil.getContentPane().add(jpBoutonsMenu,BorderLayout.CENTER);
        		jfAccueil.revalidate();
        		jfAccueil.repaint();
        	}
        });
        jpRègles.add(jbRetourRègles);

        //Champs de texte Règles
        JTextArea jtaTexteRègles = new JTextArea();
        jtaTexteRègles.setBackground(Color.DARK_GRAY);
        jtaTexteRègles.setForeground(Color.ORANGE);
        jtaTexteRègles.setFont(new Font("Eurostile", Font.PLAIN, 15));
        jtaTexteRègles.setEditable(false);
        String texte = "Les joueurs doivent poser une carte de valeur supérieur d'uniquement de 1"
        		+ " par rapport à la\ndernière carte posé dans l'ordre suivant : 3-4-5-6-7-8-9-10-Valet-Dame-Roi-As.\n"
        		+"De plus, des combinaisons existent pour rendre le jeu stimulant :\n"
        		+ "- La carte 2 : Littéralement la carte de valeur 2, permet de rejouer\n"
        		+ "- Les séries : Le joueur qui pose une série de cartes dont les valeurs se suivent impose aux autres\njoueurs de poser à leurs tours une série qui commence"
        		+ "par une valeur supérieur de 1 uniquement\npar rapport à la première carte de la série du joueur d'avant.\n"
        		+ "- Les doubles : poser 2 cartes de la même valeur supérieur de 1\n"
        		+ "- Les doubles série: Même chose qu'une série mais chaque carte de la série est en double.\n"
        		+ "La personne qui suit doit faire une double série qui commence par une valeur supérieur de 1\nuniquement par rapport à la première carte de la série du joueur d'avant\n"
        		+ "- Les bombes : Trois ou Quatre cartes de la même valeur.\n"
        		+ "ça brise la chaine et la carte 2, le joueur suivant doit ainsi suivre une nouvelle chaine\nà partir de la valeur de la bombe. Une bombe peut en suivre une autre\n"
        		+ "- La carte JOKER : Remplace n'importe quelle carte mais doit être accompagné par une autre\ncarte. Autrement dit, elle imite la carte qui l'accompagne.\n";
        jtaTexteRègles.setText(texte);
        jtaTexteRègles.setBounds(17, 10, 716, 321);
        jpRègles.add(jtaTexteRègles);
        
        //////CREDITS//////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //Panel Titre de Crédits
        JPanel jpZoneTitreCrédits = new JPanel();
		jpZoneTitreCrédits.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		jpZoneTitreCrédits.setBackground(Color.DARK_GRAY);
		
		//Panel Informations de Crédits
		JPanel jpInfoCrédits = new JPanel();
		jpInfoCrédits.setBackground(Color.DARK_GRAY);
		jpInfoCrédits.setLayout(null);
		
		//Label Titre Crédits
		JLabel jlCrédits = new JLabel("Crédit");
		jlCrédits.setForeground(Color.ORANGE);
		jlCrédits.setFont(new Font("Downtempo", Font.BOLD, 50));
		jpZoneTitreCrédits.add(jlCrédits);
		
		//Bouton Retour de Crédits
		JButton jbRetourCrédits = new JButton("Retour");
		jbRetourCrédits.setForeground(Color.ORANGE);
		jbRetourCrédits.setBounds(307, 392, 135, 43);
		jpInfoCrédits.add(jbRetourCrédits);
		jbRetourCrédits.setFocusable(false); //enleve le carré en pointiller dégeulasse
		jbRetourCrédits.setFont(new Font("Eurostile", Font.PLAIN, 30));
		jbRetourCrédits.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            jfAccueil.getContentPane().removeAll();
        		jfAccueil.revalidate();
        		jfAccueil.repaint();
        		jfAccueil.getContentPane().add(jpTitreMenu,BorderLayout.NORTH);
        		jfAccueil.getContentPane().add(jpBoutonsMenu,BorderLayout.CENTER);
        		jfAccueil.revalidate();
        		jfAccueil.repaint();
	        }
    	});
		
		//Texte pané de Crédits
		JTextPane jtpTexteCrédits = new JTextPane();
		jtpTexteCrédits.setEditable(false);
		jtpTexteCrédits.setForeground(Color.ORANGE);
		jtpTexteCrédits.setBackground(Color.DARK_GRAY);
		jtpTexteCrédits.setFont(new Font("Eurostile", Font.PLAIN, 15));
		jtpTexteCrédits.setText("Le groupe HEBC vous présente le jeu de cartes : tu ny peux rien!\n"
				+ "Année de conception : 2023-2024\n"
				+ "version : 1.0\n"
				+ "Author : Hebc, 22302932, Stefx300");
		jtpTexteCrédits.setBounds(20, 122, 711, 76);
		//Pour que le texte soit aligné au milieu
		StyledDocument doc = jtpTexteCrédits.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		jpInfoCrédits.add(jtpTexteCrédits);
        
        
		//////MENU PRINCIPAL////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Bouton Nouvelle Partie
		JButton jbNouvellePartie = new JButton("Nouvelle partie");
		jbNouvellePartie.setForeground(Color.ORANGE);
		jbNouvellePartie.setBounds(250, 99, 248, 43);
		jbNouvellePartie.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbNouvellePartie.setFocusable(false);
		jbNouvellePartie.setFont(new Font("Eurostile", Font.PLAIN, 30));
		jbNouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfAccueil.getContentPane().removeAll();//enleve le contenue de frame1
				jfAccueil.revalidate();//valide les elements supprimer de frame1
				jfAccueil.repaint();//affiche frame1 avec rien
				jfAccueil.getContentPane().add(jpTitreNouvellePartie, BorderLayout.NORTH);
				jfAccueil.getContentPane().add(jpOptions, BorderLayout.CENTER);
				jfAccueil.revalidate();
				jfAccueil.repaint();
			}
		});
		jpBoutonsMenu.add(jbNouvellePartie);
		
		//Bouton Règles
		JButton jbRègles = new JButton("Règles");
		jbRègles.setForeground(Color.ORANGE);
		jbRègles.setBounds(250, 154, 248, 43);
		jbRègles.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbRègles.setFocusable(false); //enleve le carré en pointiller dégeulasse
		jbRègles.setFont(new Font("Eurostile", Font.PLAIN, 30));
		jbRègles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfAccueil.getContentPane().removeAll();                
				jfAccueil.revalidate();
				jfAccueil.repaint(); 
				jfAccueil.getContentPane().add(jpTitreRègles, BorderLayout.NORTH);
				jfAccueil.getContentPane().add(jpRègles, BorderLayout.CENTER);
				jfAccueil.revalidate();
				jfAccueil.repaint(); 
			}
		});
		jpBoutonsMenu.add(jbRègles);
		
		//Bouton Crédits
		JButton jbCrédits = new JButton("Crédits");
		jbCrédits.setForeground(Color.ORANGE);
		jbCrédits.setBounds(250, 209, 248, 43);
		jbCrédits.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbCrédits.setFocusable(false); //enleve le carré en pointiller dégeulasse
		jbCrédits.setFont(new Font("Eurostile", Font.PLAIN, 30));
		jbCrédits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfAccueil.getContentPane().removeAll();
				jfAccueil.revalidate();
				jfAccueil.repaint();
				jfAccueil.getContentPane().add(jpZoneTitreCrédits, BorderLayout.NORTH);
				jfAccueil.getContentPane().add(jpInfoCrédits, BorderLayout.CENTER);
				jfAccueil.revalidate();
				jfAccueil.repaint();
			}
		});
		jpBoutonsMenu.add(jbCrédits);
		
		//Bouton Quitter
		JButton jbQuitter = new JButton("Quitter");
		jbQuitter.setForeground(Color.ORANGE);
		jbQuitter.setFont(new Font("Eurostile", Font.PLAIN, 30));
		jbQuitter.setFocusable(false);
		jbQuitter.setAlignmentX(0.5f);
		jbQuitter.setBounds(250, 264, 248, 43);
		jbQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jfAccueil.dispose();
				System.exit(0);
			}
		});
		jpBoutonsMenu.add(jbQuitter);
	}
}
