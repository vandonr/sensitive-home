package GestionProfils;

public class Multiprise extends Socle
{
	public Multiprise(int ID,int taille)
	{
		super(ID,taille);
	}
	
	public Multiprise(int ID, Prise[] morceaux)
	{
		super(ID, morceaux);
	}
	
	public void setPrise(Prise p)
	{
		super.setMorceau(p);
	}

	public Prise getPrise(int index)
	{
		return (Prise)(super.getMorceau(index));
	}
	
	public void setEtatPrise(Etat e, int index)
	{
		((Prise)(morceaux[index])).setEtat(e);
	}
}
