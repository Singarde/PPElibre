package conectionBDD;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import classe.Livre;
import classe.Login;
import classe.Ressources;
import classe.Trie;
import classe.Utilisateur;
import requetteSql.requetteSql;
import resultatSQL.ResultSQL;

public class BddRecherche {
	
	public static void initialise() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			/* Gérer les éventuelles erreurs ici. */

		}
	}

	public static void connect(JList listAllBook,Trie trie, String code) {

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

			case "listAllLivre":
				query = requetteSql.allBook(trie);
				result = state.executeQuery(query);
				break;
			
			case "createUser":
				
//				Statement st = null;
//				st = conn.createStatement();
//
//				int statut = st.executeUpdate( requetteSql.creatUser(utilisateurRef));
//				if (statut==1) {
//					JOptionPane.showMessageDialog(null, "tout et bon nouveau utilisateur crée", "validation",
//						JOptionPane.INFORMATION_MESSAGE);
//				}
				
				break;
			}
			

			switch (code) {

			case "listAllLivre":
				ResultSQL.createListAllBook(listAllBook, result);
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
