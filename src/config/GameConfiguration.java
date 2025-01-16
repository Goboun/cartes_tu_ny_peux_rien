package config;

import java.awt.Color;

/**
 * Configuration du jeu de Cartes
 * 
 * @author HEBC's Corporation Presents
 *
 */
public class GameConfiguration {
	//largeur de la frame
	public static final int WINDOW_WIDTH = 10;
	//hauteur de la frame
	public static final int WINDOW_HEIGHT = 10;
	
	//nombre de cartes par défaut
	public static final int NB_CARTES = 5;
	public static final int NB_CARTES_J1 = 5;
	
	//couleur des robots
	public static final Color COULEUR_BOT1 = new Color(139, 0, 0);
	public static final Color COULEUR_BOT2 = new Color(0, 0, 139);
	public static final Color COULEUR_BOT3 = new Color(60, 179, 113);
	public static final Color COULEUR_BOT4 = new Color(184, 134, 11);
	
	//style de jeux
	public static final String CARTE_VISIBLE = "Débutant";
	public static final String CARTE_CACHEE = "Classique";

}
