package glp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class Tour {
	private int nbtour;//+1 avant de piocher
	private String meneur;//dernier qui c coucher
	private String rythme;//si c simple, double,série
	private ArrayList<Joueur> listjoueur;
	
	
    public Tour(ArrayList<Joueur> joueurs/*,String rythme, String meneur*/) {
        this.listjoueur = joueurs;
        //this.rythme = rythme;
        //this.meneur = meneur;
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
    
    public boolean tousLesJoueursCouchers() {
        for (Joueur joueur : listjoueur) {
            if (joueur.getCan_Play()) {
                return false; // Au moins un joueur peut jouer, la condition n'est pas satisfaite
            }
        }
        return true; // Tous les joueurs sont couchés
    }
    
	public void nouveauTour(Deck deck) {
		incrementerNbTour();
		//Iterator<Joueur> it = listjoueur.iterator();
        // Vérifier si tous les joueurs sont couchés
        if (tousLesJoueursCouchers()) {
            // Distribuer une carte à chaque joueur
            for (Joueur joueur : listjoueur) {
                if (!joueur.getCan_Play()) {
                    Carte nouvelleCarte = deck.piocherCarte();
                	JButton carteButton = new JButton(nouvelleCarte.imagecarte());
                	carteButton.setBorder(null);
                	carteButton.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent e) {
        					//ajouter l'effet de clique
        				}
        			});
        			//faire l'équivalent cartesJ1.add(carteButton); 
                    joueur.getMain().piocherCarte(nouvelleCarte);
                }
            } 
        }
	}
}