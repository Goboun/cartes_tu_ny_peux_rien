package process;

import javax.swing.*;

import data.Carte;
import data.Joueur;
import gui.*;
import process.ActionMain;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.ArrayList;

public class ActionMain {

	private HashMap<Carte, JCheckBox> j1main = new HashMap<>();

	public ActionMain(Partie partie, JFrame frame, JPanel jpTas, JPanel jpJ1) {
		// Initialise le deck
		partie.getDeck().initDeck();
		frame.getContentPane().add(jpTas);

		Joueur j1 = partie.getJ1();

		//Création des cases à cocher invisible pour chaque carte
		//Les cartes peuvent être sélectionnées ou désélectionnées
		//Cette boucle ajoute les cartes dans une HashMap
		j1.getMain().trierMain();
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
			j1main.put(carte, checkBox);
		}

		//Création d'un panel pour les cartes à cocher
		//jpJ1.setLayout(new FlowLayout());
		for (JCheckBox checkBox : j1main.values()) {
			jpJ1.add(checkBox);
		}
	}

	public boolean peut_jouer(Carte c1, Partie partie) {
		if (partie.tas.tasVide()) {
			//Si le tas est vide, le joueur peut jouer n'importe quelle carte
			return true; 
		} else {
			//Sinon, le joueur doit jouer une carte qui respecte les règles du jeu
			return c1.getNumero() == partie.tas.getDernier().getNumero() + 1 || c1.getNumero() == 2 || partie.tas.getDernier().getNumero() == 2;
		}
	}

	public void jouerCartes(AccueilGUI accueil, PartieGUI partiegui, Partie partie, ActionBot actionbot, JFrame frame, JPanel jpTas, JPanel jpJ1) {
		ArrayList<Carte> cartes_sel = new ArrayList<>();
		JCheckBox checkBox = new JCheckBox();

		for (Carte c1 : j1main.keySet()) {
			checkBox = j1main.get(c1);
			if (checkBox.isSelected()){
				cartes_sel.add(c1);
			}
		}

		for (int i = 1; i < cartes_sel.size(); i++) {
			Carte key = cartes_sel.get(i);
			int j = i - 1;
			while (j >= 0 && cartes_sel.get(j).getNumero() > key.getNumero()) {
				cartes_sel.set(j + 1, cartes_sel.get(j));
				j = j - 1;
			}
			cartes_sel.set(j + 1, key);
		}

		if(partie.tour.getRythme() == null) {
			switch(cartes_sel.size()) {
			case 1 :
				partie.getTour().setRythme("Simple");
				break;
			case 2 :
				if (cartes_sel.get(0).getNumero() + 1 == cartes_sel.get(1).getNumero()) {
					partie.getTour().setRythme("Serie2");
				}
				if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero()) {
					partie.getTour().setRythme("Double");
				}
				break;
			case 3 :
				if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
				cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
					partie.getTour().setRythme("Bombe3");
				}
				if (cartes_sel.get(0).getNumero() + 1 == cartes_sel.get(1).getNumero() &&
						cartes_sel.get(1).getNumero() + 1 == cartes_sel.get(2).getNumero()) {
					partie.getTour().setRythme("Serie3");
				}
				break;
			case 4 :
				if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
				cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
				cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
					partie.getTour().setRythme("Bombe4");
				}
				if (cartes_sel.get(0).getNumero() + 1 == cartes_sel.get(1).getNumero() &&
						cartes_sel.get(1).getNumero() + 1 == cartes_sel.get(2).getNumero() &&
						cartes_sel.get(2).getNumero() + 1 == cartes_sel.get(3).getNumero()) {
					partie.getTour().setRythme("Serie4");
				}
				break;
			default :
				break;
			}
		}

		if(partie.tour.getRythme() != null) {
			switch(partie.tour.getRythme()) {
			case "Simple" :
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe4") && peut_jouer(cartes_sel.get(0), partie))
								||!partie.tour.getRythme().equals("Bombe4")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.tour.setRythme("Bombe4");
							partie.setaJouer(true);
						} 
					} 
				}
				else if(cartes_sel.size()==3) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe3") && peut_jouer(cartes_sel.get(0), partie))
								|| !partie.tour.getRythme().equals("Bombe3")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								partie.setaJouer(true);
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
								jpJ1.remove(j1main.get(carte));
							}
							partie.tour.setRythme("Bombe3");
							partie.setaJouer(true);
						} 
					} 
				}
				else {
					for (Carte carte : cartes_sel) {
						if (peut_jouer(carte, partie)) {
							partie.getJ1().getMain().effacerCarte(carte);
							partie.getTas().ajouter_tas(carte);
							partie.setaJouer(true);
							JLabel labelCarte = new JLabel(carte.imagecarte());
							jpTas.add(labelCarte);
							jpJ1.remove(j1main.get(carte));
							j1main.remove(carte, checkBox);
							
						} 
					}
				}

				break;
			case "Double" :
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe4") && peut_jouer(cartes_sel.get(0), partie))
								||!partie.tour.getRythme().equals("Bombe4")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.tour.setRythme("Bombe4");
							partie.setaJouer(true);
						} 
					} 
				}
				else if(cartes_sel.size()==3) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe3") && peut_jouer(cartes_sel.get(0), partie))
								|| !partie.tour.getRythme().equals("Bombe3")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								partie.setaJouer(true);
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
								jpJ1.remove(j1main.get(carte));
							}
							partie.tour.setRythme("Bombe3");
							partie.setaJouer(true);
						} 
					} 
				}
				else {
					for (int i = 0; i < cartes_sel.size() - 1; i++) {
						Carte carte = cartes_sel.get(i);
						Carte carteSuivante = cartes_sel.get(i + 1);
						if (carte.getNumero() == carteSuivante.getNumero() && peut_jouer(carte, partie)) {
							partie.getJ1().getMain().effacerCarte(carte);
							partie.getJ1().getMain().effacerCarte(carteSuivante);
							partie.getTas().ajouter_tas(carte);
							partie.getTas().ajouter_tas(carteSuivante);
							partie.setaJouer(true);
							JLabel labelCarte = new JLabel(carte.imagecarte());
							jpTas.add(labelCarte);
							JLabel labelCarte1 = new JLabel(carteSuivante.imagecarte());
							jpTas.add(labelCarte1);
							jpJ1.remove(j1main.get(carte));
							jpJ1.remove(j1main.get(carteSuivante));
							break;
						} 
					}
					break;
				}         
			case "Bombe3" :
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe4") && peut_jouer(cartes_sel.get(0), partie))
								||!partie.tour.getRythme().equals("Bombe4")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.tour.setRythme("Bombe4");
							partie.setaJouer(true);
						} 
					} 
				}
				else if (cartes_sel.size() == 3) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
						if (peut_jouer(cartes_sel.get(0), partie)) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								partie.setaJouer(true);
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
								jpJ1.remove(j1main.get(carte));
							}
							partie.setaJouer(true);
						} 
					} 
				} 
				break;
			case "Bombe4" :
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if (peut_jouer(cartes_sel.get(0), partie)) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.setaJouer(true);
						} 
					} 
				}
				break;
			case "Serie2" :
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe4") && peut_jouer(cartes_sel.get(0), partie))
								||!partie.tour.getRythme().equals("Bombe4")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.tour.setRythme("Bombe4");
							partie.setaJouer(true);
						} 
					} 
				}
				else if(cartes_sel.size()==3) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe3") && peut_jouer(cartes_sel.get(0), partie))
								|| !partie.tour.getRythme().equals("Bombe3")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								partie.setaJouer(true);
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
								jpJ1.remove(j1main.get(carte));
							}
							partie.tour.setRythme("Bombe3");
							partie.setaJouer(true);
						} 
					} 
				}
				else{
					if (cartes_sel.size() == 2) {
						if (cartes_sel.get(0).getNumero() + 1 == cartes_sel.get(1).getNumero()) {
							if (peut_jouer(cartes_sel.get(0), partie)) {
								for (Carte carte : cartes_sel) {
									partie.getJ1().getMain().effacerCarte(carte);
									partie.getTas().ajouter_tas(carte);
									jpJ1.remove(j1main.get(carte));
									JLabel labelCarte = new JLabel(carte.imagecarte());
									jpTas.add(labelCarte);
								}
								partie.setaJouer(true);
							}
						}
					}
				}
				break;
			case "Serie3":
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe4") && peut_jouer(cartes_sel.get(0), partie))
								||!partie.tour.getRythme().equals("Bombe4")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.tour.setRythme("Bombe4");
							partie.setaJouer(true);
						} 
					} 
				}
				else if(cartes_sel.size()==3) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe3") && peut_jouer(cartes_sel.get(0), partie))
								|| !partie.tour.getRythme().equals("Bombe3")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								partie.setaJouer(true);
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
								jpJ1.remove(j1main.get(carte));
							}
							partie.tour.setRythme("Bombe3");
							partie.setaJouer(true);
						} 
					}
					else if (cartes_sel.get(0).getNumero() + 1 == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(1).getNumero() + 1 == cartes_sel.get(2).getNumero()) {
						if (peut_jouer(cartes_sel.get(0), partie)) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.setaJouer(true);
						}
					}
					
				}
				

				break;
			case "Serie4":
				if (cartes_sel.size() == 4) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(3).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe4") && peut_jouer(cartes_sel.get(0), partie))
								||!partie.tour.getRythme().equals("Bombe4")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.tour.setRythme("Bombe4");
							partie.setaJouer(true);
						} 
					} 
					if (cartes_sel.get(0).getNumero() + 1 == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(1).getNumero() + 1 == cartes_sel.get(2).getNumero() &&
							cartes_sel.get(2).getNumero() + 1 == cartes_sel.get(3).getNumero()) {
						if (peut_jouer(cartes_sel.get(0), partie)) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								jpJ1.remove(j1main.get(carte));
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
							}
							partie.setaJouer(true);
						}
					}
				}
				else if(cartes_sel.size()==3) {
					if (cartes_sel.get(0).getNumero() == cartes_sel.get(1).getNumero() &&
							cartes_sel.get(0).getNumero() == cartes_sel.get(2).getNumero()) {
						if ((partie.tour.getRythme().equals("Bombe3") && peut_jouer(cartes_sel.get(0), partie))
								|| !partie.tour.getRythme().equals("Bombe3")) {
							for (Carte carte : cartes_sel) {
								partie.getJ1().getMain().effacerCarte(carte);
								partie.getTas().ajouter_tas(carte);
								partie.setaJouer(true);
								JLabel labelCarte = new JLabel(carte.imagecarte());
								jpTas.add(labelCarte);
								jpJ1.remove(j1main.get(carte));
							}
							partie.tour.setRythme("Bombe3");
							partie.setaJouer(true);
						} 
					} 
				}

				break;
			default :
				break;
			}
		}
		else {
			for (Carte carte : cartes_sel) {
				if (peut_jouer(carte, partie)) {
					for (Carte c1 : j1main.keySet()) {
						checkBox = j1main.get(c1);
						if (checkBox.isSelected()) {
							checkBox.setSelected(false);
							jpJ1.remove(checkBox);
						}
					}
					partie.getJ1().getMain().effacerCarte(carte);
					partie.getTas().ajouter_tas(carte);
					partie.setaJouer(true);
					JLabel labelCarte = new JLabel(carte.imagecarte());
					jpTas.add(labelCarte);
					j1main.remove(carte, checkBox);
				}
			}
		}

		cartes_sel.removeAll(cartes_sel);
		partie.getTour().setMeneur(partie.getJ1().getNom());
		partie.getTour().rythmeTour(partie.getTas(), partie.getJ1());

		jpTas.revalidate();
		jpTas.repaint();
		jpJ1.revalidate();
		jpJ1.repaint();
		frame.revalidate();
		frame.repaint();
	}


	public void piocherCarteJ1(Partie partie, JFrame frame, JPanel jpJ1, HashMap<Carte, JCheckBox> j1main) {
		Carte nouvelleCarte = partie.pioche.piocherCarte();
		partie.getJ1().getMain().piocherCarte(nouvelleCarte);
		jpJ1.removeAll();
		j1main.clear();
		partie.getJ1().getMain().trierMain();
		for (Carte carte : partie.getJ1().getMain().getMain()) {
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
			j1main.put(carte, checkBox);
		}
		jpJ1.setLayout(new FlowLayout());
		for (JCheckBox checkBox : j1main.values()) {
			jpJ1.add(checkBox);
		}
	}

	public HashMap<Carte, JCheckBox> getJ1main(){
		return j1main;
	}

}