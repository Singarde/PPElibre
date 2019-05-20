package classe;

public class Utilisateur {
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String cp;
	private String ville;
	private String role;
	private String email;
	private String tel;
	private String login;
	private String mdep;
	
	public Utilisateur (int id,String nom,String prenom,String adresse,String cp,String ville,String role,String email,String tel,String login,String mdep) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.setCp(cp);
		this.setVille(ville);
		this.setRole(role);
		this.setEmail(email);
		this.setTel(tel);
		this.setLogin(login);
		this.setMdep(mdep);
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdep() {
		return mdep;
	}

	public void setMdep(String mdep) {
		this.mdep = mdep;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

}
