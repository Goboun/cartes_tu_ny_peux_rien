package glp;

public class Regle {
	private Carte carteJoueur;
	private Deck tas;

	public Regle() {
	}

	public Boolean regleDuJeu(Carte carteJoueur, Deck tas) {
		Carte carteCentre = tas.getDernier();
		
		if (carteJoueur.getNumero() == carteCentre.getNumero()+1) {
			return true;
		}
		return false;
	}
}
