package cpt;

public class Compteur {
	private int val = 0;
	
	public Compteur(int val) {
		this.val = val;
	}
	
	public void incrementer() {
		this.val ++;
	}
	
	public void decrementer() {
		this.val --;
	}
	
	public int getVal() {
		return this.val;
	}
}