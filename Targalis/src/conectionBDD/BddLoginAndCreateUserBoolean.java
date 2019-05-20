package conectionBDD;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTextField;

import classe.Ressources;
import classe.Utilisateur;
import requetteSql.requetteSql;
import resultatSQL.ResultSQL;

public class BddLoginAndCreateUserBoolean{

	public static void initialise(String code) {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			/* Gérer les éventuelles erreurs ici. */

		}
	}

	public static boolean connect(Utilisateur utilisateurRef,String code) {
			boolean verif=false;
		try {
			
			initialise(code);

			Ressources bddVar=Ressources.readAll();

			String url = bddVar.url;
			String user = bddVar.user;
			String passwd = bddVar.passwd;

			Connection conn = DriverManager.getConnection(url, user, passwd);
			java.sql.Statement state = conn.createStatement();
			String query="";

			switch (code) {


			case "verificationIfnotExistLogin":		
				query = requetteSql.verrifIfLoginExiste(utilisateurRef);
				break;


			}

			ResultSet result = state.executeQuery(query);

			switch (code) {
			
			case "verificationIfnotExistLogin":
				verif=ResultSQL.ResultIfNotLoginExist(result);		
				break;
		
			}

			result.close();
			state.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return verif;
	}

}

