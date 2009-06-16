package GestionProfils;

import francois.Communication;

public class Multiprise extends Socle {
	Communication communication;
	String ip;

	public Multiprise(long ID, int taille , String adresse) {
		super(ID,taille);
		ip = adresse;
		communication = new Communication(ip);
	}

	public Multiprise(long ID, Prise[] morceaux, String adresse) {
		super(ID, morceaux);
		ip = adresse;
		communication = new Communication(ip);
	}

	public void setPrise(Prise p) {
		super.setMorceau(p);
	}

	public Prise getPrise(int index) {
		return (Prise) (super.getMorceau(index));
	}

	public void setEtatPrise(Etat e, int index) {
		((Prise) (morceaux[index])).setEtat(e);
	}

	public void sendMessage(String message) {
		for (int i = 0; i < message.length(); i++) {
			communication.sendSomeData((byte) message.charAt(i));
		}
	}
}