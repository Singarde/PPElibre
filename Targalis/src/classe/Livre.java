package classe;

public class Livre {
	private String ISBN;
	private String titre;
	private Auteur auteur;
	private String categorie;
	private String genre;
	private int exemplaire;
	private String etat;
	private int emprunt;
	private int id;
	
	public Livre (String ISBN, String titre, Auteur auteur, String categorie, String genre, int exemplaire, String etat, int emprunt,int id) {
		this.ISBN=ISBN;
		this.titre=titre;
		this.setAuteur(auteur);
		this.setCategorie(categorie);
		this.setGenre(genre);
		this.setExemplaire(exemplaire);
		this.setEtat(etat);
		this.setEmprunt(emprunt);
		this.setId(id);
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(int exemplaire) {
		this.exemplaire = exemplaire;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(int emprunt) {
		this.emprunt = emprunt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
