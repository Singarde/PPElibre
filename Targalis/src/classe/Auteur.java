package classe;

public class Auteur {
	
	private String nom;
	private int annee;
	private int id;
	
	public Auteur (String nom, int annee, int id) {
		this.nom=nom;
		this.annee=annee;
		this.setId(id);
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
