package test;

import gui.AccueilGUI;
import gui.PartieGUI;

public class TestCarteSimulation {

    public static void main(String[] args) {
        // Instancier la fenêtre du menu
        AccueilGUI accueil = new AccueilGUI();
        accueil.app_run();

        // Attendre la fermeture de la fenêtre du menu
        while (accueil.isActive()) {
            try {
                Thread.sleep(100); // Attendre un court instant avant de vérifier à nouveau
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Démarrer la partie une fois que le menu est fermé
        PartieGUI partieGUI = new PartieGUI();
        partieGUI.initialize();
        partieGUI.play();
        partieGUI.jfPartie.setVisible(true);
    }
}