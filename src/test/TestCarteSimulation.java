package test;

import gui.AccueilGUI;
import gui.PartieGUI;

public class TestCarteSimulation {

    public static void main(String[] args) {
        //Instancier la fenêtre du menu
        AccueilGUI accueil = new AccueilGUI();
        accueil.app_run();

        //Attendre la fermeture de la fenêtre de menu
        while (accueil.isActive()) {
            try {
                //Attendre un court instant avant de vérifier à nouveau
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Démarrer la partie une fois que la fenêtre de menu est fermée
        PartieGUI partieGUI = new PartieGUI();
        partieGUI.initialize(accueil);
        partieGUI.play(accueil);
        partieGUI.jfPartie.setVisible(true);
    }
}