package resultatSQL;

import java.awt.Choice;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import classe.Auteur;
import classe.Livre;
import classe.Login;
import creatInterface.MainPanel;

public class ResultSQL {
	
	public static void ResultLogin(JFrame frameLogin, ResultSet result, Login logActuel) throws SQLException {
		if (result.next()) { //si il trouve un résultat
			
			logActuel.setId(Integer.parseInt(result.getString("ID_UTI")));
			
			System.out.println(logActuel.getId());
			frameLogin.setVisible(false);
			MainPanel.main(null,logActuel);
		} else {
			JOptionPane.showMessageDialog(null, "utilisateur ou mot de passe inconnu", "erreur de connexion",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("test trouver utilisateur - pas d'utilisateur");
		}
	}
	
	public static void recupRole(ResultSet result, Login logActuel) throws SQLException {
		
		if (result.next()) { //si il trouve un résultat
			System.out.println("test trouver utilisateur - " + result.getString("ROLE"));	
			logActuel.setRole(result.getString("ROLE"));
			
		} else {
			JOptionPane.showMessageDialog(null, "role inconue", "erreur de role",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("test trouver utilisateur - pas de role");
		}
	}
	
	public static boolean ResultIfNotLoginExist(ResultSet result) throws SQLException {
		
		if (result.next()) { //si il trouve un résultat
			System.out.println("test vérif si login existe - " + result.getString("LOGIN"));
			return true;	
		} else {
			System.out.println("test vérif si login existe - cool ce login existe pas ");
			return false;
		}
		}
	
	public static void createListRole(Choice cRole,ResultSet result) throws SQLException {
		while(result.next()) {
			cRole.add(result.getString("labelle"));	
		}

		}
	public static void createListGenre(Choice Choix,ResultSet result) throws SQLException {
		while(result.next()) {
			Choix.add(result.getString("LABELLE_GENRE"));	
		}

		}
	public static void createListCategorie(Choice Choix,ResultSet result) throws SQLException {
		while(result.next()) {
			Choix.add(result.getString("LABELLE_CAT"));	
		}
	}
		public static void createListAuteur(Choice Choix,ResultSet result) throws SQLException {
			while(result.next()) {
				Choix.add(result.getString("NOM"));	
			}

		}
		public static void findIdAuthor(Livre livre,ResultSet result) throws SQLException {
			while(result.next()) {
				livre.getAuteur().setId(Integer.parseInt(result.getString("ID_AUTEUR")));	
			}

		}
		public static void numExemplaire(Livre livre,ResultSet result) throws SQLException {
			if(result.next()) {
				if (result.getString(1) != null) {
					livre.setExemplaire(Integer.parseInt(result.getString(1))+1);
				}else {
					livre.setExemplaire(1);
				}				
			}
		}
		public static void findIdBook(Livre livre,ResultSet result) throws SQLException {
			while(result.next()) {
				livre.setId(Integer.parseInt(result.getString(1)));	
			}
		}
		public static void createListAllBook(JList listAllBook,ResultSet result) throws SQLException {
			while(result.next()) {
				Auteur auteur = new Auteur(result.getString("auteur.NOM"),
						Integer.parseInt(result.getString("auteur.ANNEENAISSANCE")),
						Integer.parseInt(result.getString("auteur.ID_AUTEUR")));
				
				Livre livre = new Livre(result.getString("ISBN"),result.getString("TITRE"),
						auteur,result.getString("LABELLE_CAT"),result.getString("LABELLE_GENRE"),
						Integer.parseInt(result.getString("EXEMPLAIRE")),result.getString("etat"),
						Integer.parseInt(result.getString("EMPRUNT")),Integer.parseInt(result.getString("ID_LIVRE")));
				((DefaultListModel)listAllBook.getModel()).addElement(livre);
				
			}
		}

	

}
