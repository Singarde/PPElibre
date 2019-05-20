package classe;

public class Login {
	
	private String login;
	private String mdep;
	private String role;
	private int id;
	
	public Login (int id,String login,String mdep) {
		this.setId(id);
		this.setLogin(login);
		this.setMdep(mdep);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
