package requetteSql;

import java.awt.Choice;

import javax.swing.JTextField;

import classe.Auteur;
import classe.Livre;
import classe.Login;
import classe.Trie;
import classe.Utilisateur;

public class requetteSql {

	public static String verrifConection(Login logActuelle) {// verrifier la connection de l'utilisateur
		String requete = "SELECT * FROM utilisateur WHERE LOGIN='" + logActuelle.getLogin() + "'" + "AND MDP='"
				+ logActuelle.getMdep() + "';";
		return requete;

	}

	public static String recupRole(Login logActuelle) {// récuperre le role de l'utilisateur
		String requete = "SELECT ROLE FROM utilisateur WHERE LOGIN='" + logActuelle.getLogin() + "'" + "AND MDP='"
				+ logActuelle.getMdep() + "';";
		return requete;

	}

	public static String verrifIfLoginExiste(Utilisateur utilisateurRef) {// verrifie si le login entrée existe
		String requete = "SELECT * FROM utilisateur WHERE LOGIN='" + utilisateurRef.getLogin() + "';";

		return requete;
	}

	public static String listeRole() {// génére la liste des role
		String requete = "SELECT * FROM role;";
		return requete;
	}

	public static String creatUser(Utilisateur utilisateurRef) {// crée le nouveau utilisateur

		String requete = "INSERT INTO utilisateur (ROLE, NOM, PRENOM, ADRESSE, CODEPOSTAL, VILLE, TEL, EMAIL, LOGIN, MDP) "
				+ "VALUES ('" + utilisateurRef.getRole() + "','" + utilisateurRef.getNom() + "','"
				+ utilisateurRef.getPrenom() + "','" + utilisateurRef.getAdresse() + "','" + utilisateurRef.getCp()
				+ "','" + utilisateurRef.getVille() + "','" + utilisateurRef.getTel() + "','"
				+ utilisateurRef.getEmail() + "','" + utilisateurRef.getLogin() + "','" + utilisateurRef.getMdep()
				+ "');";
		System.out.println(requete);
		return requete;
	}

	public static String listeGenre() {// génére la liste des genre
		String requete = "SELECT * FROM genre;";
		return requete;
	}

	public static String listeCategorie() {// génére la liste des categorie
		String requete = "SELECT * FROM categorie;";
		return requete;
	}

	public static String listeAuteur() {// génére la liste des auteurs
		String requete = "SELECT * FROM auteur ORDER BY NOM;";
		return requete;
	}

	public static String creatAuthor(Auteur auteur) {// crée le nouveau utilisateur

		String requete = "INSERT INTO auteur (NOM, ANNEENAISSANCE) " + "VALUES ('" + auteur.getNom() + "',"
				+ auteur.getAnnee() + ");";
		System.out.println(requete);
		return requete;
	}

	public static String findIdAuthor(Livre livre) {// trouve l'id de l'auteur
		String requete = "SELECT * FROM auteur WHERE NOM='" + livre.getAuteur().getNom() + "';";
		return requete;
	}

	public static String numExemplaire(Livre livre) {// trouve l'id de l'auteur
		String requete = "SELECT MAX(EXEMPLAIRE) FROM livre WHERE ISBN='" + livre.getISBN() + "';";
		return requete;
	}

	public static String createBook(Livre livre) {// crée un nouveau livre

		String requete = "INSERT INTO `livre` (`ISBN`, `TITRE`, `ID_AUTEUR`, `LABELLE_CAT`, `LABELLE_GENRE`,"
				+" `EXEMPLAIRE`, `ETAT`, `EMPRUNT`)"
				+" VALUES ('"+livre.getISBN()+"','"+livre.getTitre()+"',"+livre.getAuteur().getId()+",'"+livre.getCategorie()
				+"','"+livre.getGenre()+"',"+livre.getExemplaire()+", 'correct' , 0);";
		System.out.println(requete);
		return requete;
	}
	
	public static String findIdBook() {// trouve l'id de l'auteur
		String requete = "SELECT MAX(ID_LIVRE) FROM livre;";
		return requete;
	}
	
	public static String addTransactionCreateBook(Login logActuel,Livre livre) {// crée un nouveau livre

		String requete = "INSERT INTO `transaction` (`LABELLE_MOTIF`, `ID_UTI`, `ID_LIVRE`, `DATE_ACTION`)"
				+" VALUES ('ajout livre', "+logActuel.getId()+", "+livre.getId()+", NOW());";
		
		return requete;
	}
	
	public static String allBook(Trie trie) {// tout les livre
		String requete = "SELECT * FROM livre INNER JOIN auteur ON livre.ID_AUTEUR = auteur.ID_AUTEUR ";
		if (trie.getAuteur()!=""&&trie.getCategorie()!=""&&trie.getGenre()!="") {//OK
			
			requete += "WHERE auteur.nom = '"+trie.getAuteur()+"' AND LABELLE_CAT = '"+trie.getCategorie()+"' AND LABELLE_GENRE = '"+trie.getGenre()+"' ";
		
		}else if (trie.getAuteur()!=""&&trie.getCategorie()!=""&&trie.getGenre()=="") {//OK
			
			requete += "WHERE auteur.nom = '"+trie.getAuteur()+"' AND LABELLE_CAT = '"+trie.getCategorie()+"' ";
		
		}else if (trie.getAuteur()!=""&&trie.getCategorie()==""&&trie.getGenre()!="") {//OK
			
			requete += "WHERE auteur.nom = '"+trie.getAuteur()+"' AND LABELLE_GENRE = '"+trie.getGenre()+"' ";
		
		}else if (trie.getAuteur()!=""&&trie.getCategorie()==""&&trie.getGenre()=="") {//OK
			
			requete += "WHERE auteur.nom = '"+trie.getAuteur()+"' ";
		
		}else if (trie.getAuteur()==""&&trie.getCategorie()!=""&&trie.getGenre()!="") {//OK
			
			requete += "WHERE LABELLE_CAT = '"+trie.getCategorie()+"' AND LABELLE_GENRE = '"+trie.getGenre()+"' ";
		
		}else if (trie.getAuteur()==""&&trie.getCategorie()!=""&&trie.getGenre()=="") {//OK
			
			requete += "WHERE LABELLE_CAT = '"+trie.getCategorie()+"' ";
		
		}else if (trie.getAuteur()==""&&trie.getCategorie()==""&&trie.getGenre()!="") {
			
			requete += "WHERE LABELLE_GENRE = '"+trie.getGenre()+"' ";
		
		}
		
		
		
		
		
		if (trie.getifAuteur()||trie.getifTitre()) {//trie généal
			requete += "ORDER BY ";
			if(trie.getifAuteur()) {
			requete += "auteur.nom ";
		}
		if (trie.getifTitre()&&trie.getifAuteur()) {
			requete += ",titre ";
		}
		if (trie.getifTitre()&&trie.getifAuteur()==false) {
			requete += "titre ";
		}
		
		}
		
		
		System.out.println(requete);
		return requete;
	}

	public static String listeEtat() {// génére la liste des role
		String requete = "SELECT * FROM etat;";
		return requete;
	}
	
	public static String modifEtatBook(Login logActuel, Choice choix,int idLivre) {// trouve l'id de l'auteur
		String requete = "UPDATE `livre` SET `ETAT` = '"+choix.getSelectedItem()+"' WHERE `livre`.`ID_LIVRE` ="+idLivre+";";
		return requete;
	}
}
