package classe;

public class Trie {
	private boolean iftitre;
	private boolean ifauteur;
	private String categorie;
	private String genre;
	private String auteur;
	
	
	public Trie(boolean iftitre,boolean ifauteur, String categorie, String genre, String auteur) {
		this.iftitre=iftitre;
		this.ifauteur=ifauteur;
		this.categorie=categorie;
		this.genre=genre;
		this.auteur=auteur;
	}
	
	
	public boolean getifTitre() {
		return iftitre;
	}
	public void setifTitre(boolean iftitre) {
		this.iftitre = iftitre;
	}
	public boolean getifAuteur() {
		return ifauteur;
	}
	public void setifAuteur(boolean ifauteur) {
		this.ifauteur = ifauteur;
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


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}



}
