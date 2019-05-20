package conectionBDD;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import classe.Login;
import classe.Ressources;
import classe.Utilisateur;
import requetteSql.requetteSql;
import resultatSQL.ResultSQL;

public class BddLoginAndCreateUser {

	public static void initialise() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			/* Gérer les éventuelles erreurs ici. */

		}
	}

	public static void connect(Utilisateur utilisateurRef, Login logActuelle, Choice cRole,JFrame frameLogin, String code) {

		try {
			initialise();
			
			Ressources bddVar=Ressources.readAll();

			String url = bddVar.url;
			String user = bddVar.user;
			String passwd = bddVar.passwd;
			Statement statement = null;

			Connection conn = DriverManager.getConnection(url, user, passwd);
			java.sql.Statement state = conn.createStatement();
			String query="";
			ResultSet result = null;
			
			switch (code) {

			case "conect":
				query = requetteSql.verrifConection(logActuelle);
				result = state.executeQuery(query);
				break;
			case "verificationIfnotExistLogin":		
				query = requetteSql.verrifIfLoginExiste(utilisateurRef);
				result = state.executeQuery(query);
				break;
			case "listeRole":
				query = requetteSql.listeRole();
				result = state.executeQuery(query);
				break;
			case "createUser":
				
				Statement st = null;
				st = conn.createStatement();

				int statut = st.executeUpdate( requetteSql.creatUser(utilisateurRef));
				if (statut==1) {
					JOptionPane.showMessageDialog(null, "tout et bon nouveau utilisateur crée", "validation",
						JOptionPane.INFORMATION_MESSAGE);
				}
				
				break;
			case "recupRole":
				query = requetteSql.recupRole(logActuelle);
				result = state.executeQuery(query);
				break;
				

			}

			

			switch (code) {

			case "conect":
				ResultSQL.ResultLogin(frameLogin, result, logActuelle);
				result.close();
				break;
			case "verificationIfnotExistLogin":
				ResultSQL.ResultIfNotLoginExist(result);		
				break;
			case "listeRole":
				ResultSQL.createListRole(cRole,result);
				result.close();
				break;
			case "recupRole":
				ResultSQL.recupRole(result,logActuelle);
				result.close();
				break;
			}

			
			state.close();

		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}

	}

}
