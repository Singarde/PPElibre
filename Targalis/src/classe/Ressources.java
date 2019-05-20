package classe;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Ressources {
	public String url;
	public String user;
	public String passwd;
	
	public Ressources(String url,String user,String passwd) {
		this.url=url;
		this.user=user;
		this.passwd=passwd;
	}
	
	public static Ressources readAll() {

		final Properties prop = new Properties();
		InputStream input = null;
		Ressources res = null;
		try {
			input = new FileInputStream("ressource/ressource.properties");
			// load a properties file
			prop.load(input);
			res = new Ressources(prop.getProperty("db.url"), prop.getProperty("db.user"), "");
			
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return res;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	

}
