package cpt;

public class CompteurBorne extends Compteur {
	private int max;
	private int min;
	
	public CompteurBorne(int n_min, int n_max, int val) {
		super(val);
		this.min = n_min;
		this.max = n_max;
	}
	
	public void incrementer() {
		if (getVal() != this.max) {
			super.incrementer();
		}
	}
	
	public void decrementer() {
		if (getVal() != this.min) {
			super.decrementer();
		}
	}
}