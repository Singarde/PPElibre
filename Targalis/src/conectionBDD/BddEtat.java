package conectionBDD;

import java.awt.Choice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import classe.Auteur;
import classe.Livre;
import classe.Login;
import classe.Ressources;
import requetteSql.requetteSql;
import resultatSQL.ResultSQL;

public class BddEtat {
	
	public static void initialise() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {

			/* Gérer les éventuelles erreurs ici. */

		}
	}

	public static void connect(Login logActuel, Choice choix,int idLivre,String code) {

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

			case "listeEtat":
				query = requetteSql.listeEtat();
				result = state.executeQuery(query);
				break;	
			case "listeCategorie":
				query = requetteSql.listeCategorie();
				result = state.executeQuery(query);
				break;	
			
			case "ModifEtatLivre" :
				Statement st = null;
				st = conn.createStatement();

				int statut = st.executeUpdate( requetteSql.modifEtatBook(logActuel, choix,idLivre));
				if (statut==1) {
					JOptionPane.showMessageDialog(null, "Etat du livre modifié", "validation",
						JOptionPane.INFORMATION_MESSAGE);
				}
				break;
//				
//			case "addTransactionCreateBook":
//				Statement st3 = null;
//				st3 = conn.createStatement();
//
//				int statut3 = st3.executeUpdate( requetteSql.addTransactionCreateBook(logActuel,livre));
//				break;
//			case "createAutor":
//				Statement st2 = null;
//				st2 = conn.createStatement();
//
//				int statut2 = st2.executeUpdate( requetteSql.creatAuthor(auteur));
//				if (statut2==1) {
//					JOptionPane.showMessageDialog(null, "nouvel auteur crée !", "validation",
//						JOptionPane.INFORMATION_MESSAGE);
//				}
//				break;
			
			
				
			}

			

			switch (code) {

			case "listeEtat":
				ResultSQL.createListEtat(choix,result);
				result.close();
				break;
			case "listeCategorie":
				ResultSQL.createListCategorie(choix,result);
				result.close();
				break;
			case "listeAuteur":
				ResultSQL.createListAuteur(choix,result);
				result.close();
				break;
//			case "findIdAuthor":
//				ResultSQL.findIdAuthor(livre,result);
//				result.close();
//				break;
//			case "numExemplaire" :
//				ResultSQL.numExemplaire(livre,result);
//				result.close();
//				break;
//			case "findIdBook" :
//				ResultSQL.findIdBook(livre,result);
//				result.close();
//				break;
//			case "createBook" :
//				Statement st = null;
//				st = conn.createStatement();
//
//				int statut = st.executeUpdate( requetteSql.modifEtatBook(Login logActuel, Choice choix,Livre livre));
//				if (statut==1) {
//					JOptionPane.showMessageDialog(null, "nouveau livre crée !", "validation",
//						JOptionPane.INFORMATION_MESSAGE);
//				}
//				
//				break;
			}

			
			state.close();

		} catch (Exception e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}

	}


}
