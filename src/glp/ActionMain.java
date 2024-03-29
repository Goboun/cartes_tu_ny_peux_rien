package glp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ActionMain {

    private ArrayList<JCheckBox> j1main;
    private JButton playButton;

    public ActionMain(JFrame frame, JPanel jpOptions, JPanel jpTas, JPanel cartesJ1, Deck pioche, Joueur j1) {
        //initialise le deck
    	pioche.initDeck();
		
		frame.getContentPane().add(jpTas);

        /*Création des cases à cocher invisible pour chaque carte
		les cartes peuvent être selectionnées comme déséléctionnées*/
        j1main = new ArrayList<>();
        
        /*la boucle n'est pas necessaire mais je la laisse quand meme au cas où
        cette boucle ajoute les 5 cartes par défault au j1 mais je sais pas pourquoi
        il n'y en a pas besoin
        
        for(int i = 0; i < 5; i++) {
            Carte carte1 = pioche.piocherCarte();
            j1.getMain().piocherCarte(carte1);
        }*/
        
        //cette boucle ajoute les cartes dans une ArrayList
        for (Carte carte : j1.getMain().getMain()) {
            JCheckBox checkBox = new JCheckBox(carte.imagecarte());
            checkBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        checkBox.setBackground(Color.YELLOW);
                    } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                        checkBox.setBackground(null); // Retour à la couleur par défaut
                    }
                }
            });
            j1main.add(checkBox);
        }

        // Bouton pour jouer les cartes sélectionnées
        playButton = new JButton("Jouer");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jouerCartes(frame, jpTas);
            }
        });

        // Création d'un panel pour les cartes à cocher
        cartesJ1.setLayout(new FlowLayout());
        for (JCheckBox checkBox : j1main) {
            cartesJ1.add(checkBox);
        }
        playButton.setForeground(Color.ORANGE);
        playButton.setFont(new Font("Eurostile", Font.PLAIN, 25));
        playButton.setBounds(79, 15, 197, 40);
		jpOptions.add(playButton);
        frame.getContentPane().add(cartesJ1);
    }

    public void jouerCartes(JFrame frame, JPanel jpTas) {
    	/*@brief = permet de mettre la/les carte(s) jouer au milieu dans le Tas
    	 *@param = frame : JFrame pour ajouter les element dans la frame de jeux
    	 *@variable = jpTas : JPanel pour ajouter la/les carte(s) jouer au centre
    	 *@return = la frame actualiser avec les cartes séléctionnées au centre 
    	 */
        // Parcourir les cases à cocher et déplacer les cartes sélectionnées vers jpTas
        for (int i = j1main.size() - 1; i >= 0; i--) {        	
            JCheckBox checkBox = j1main.get(i);
            if (checkBox.isSelected()) {
            	checkBox.setBackground(null);
                jpTas.add(checkBox);  // Ajouter la carte à jpTas
            }
        }

        // Réactualiser le cadre pour refléter les changements
        frame.revalidate();
        frame.repaint();
    }
}