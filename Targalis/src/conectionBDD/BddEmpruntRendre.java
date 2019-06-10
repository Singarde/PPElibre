package conectionBDD;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import classe.Auteur;
import classe.Livre;
import classe.Login;
import classe.Ressources;
import requetteSql.requetteSql;
import resultatSQL.ResultSQL;

public class BddEmpruntRendre {
	
	public static void initialise() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			/* Gérer les éventuelles erreurs ici. */

		}
	}

	public static void connect(Login logActuel,Livre livre,JTextPane tp,String code) {

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

			case "emprunter" :
				Statement st = null;
				st = conn.createStatement();

				int statut = st.executeUpdate( requetteSql.emprunterLivre(livre));
				if (statut==1) {

					BddEmpruntRendre.connect(logActuel, livre,null, "dateLimite");
				}
				break;
				
			case "dateLimite" :
				Statement st2 = null;
				st2 = conn.createStatement();

				int statut2 = st2.executeUpdate( requetteSql.emprunterLivreLimite(logActuel, livre));
				if (statut2==1) {

					
				}
				break;
				
			case "listAllLivre":
				query = requetteSql.allLivreEmprunter(logActuel, livre);
				result = state.executeQuery(query);
				break;
			

			}

			switch (code) {
			case "listAllLivre":
				if (result.next()) {
					tp.setText("yolo");
				}
				
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
