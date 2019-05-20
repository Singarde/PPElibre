package fonction;

import javax.swing.JOptionPane;

import classe.Livre;
import classe.Login;
import conectionBDD.BddCreateBookAndAuthor;
import verification.Verif;

public class CreateBook {
	
	public static void createBook(Login logActuel,Livre livre) {
		if(Verif.verifChampNonNull(livre.getTitre())&&Verif.verifChampNonNull(livre.getISBN())) {
			
			BddCreateBookAndAuthor.connect(null,livre,null, null, "findIdAuthor");//trouver l'id de l'auteur du livre
			BddCreateBookAndAuthor.connect(null,livre,null, null, "numExemplaire");//trouver le num de l'exemplaire
			BddCreateBookAndAuthor.connect(null,livre,null, null, "createBook");//crée un livre dans bdd
			BddCreateBookAndAuthor.connect(null,livre,null, null, "findIdBook");//trouver id du nouveau livre
			BddCreateBookAndAuthor.connect(logActuel,livre,null, null, "addTransactionCreateBook");//transaction création livre	
			
		}else {
			JOptionPane.showMessageDialog(null, "erreur de champs pour la création du livre", "erreur de champs",
					JOptionPane.ERROR_MESSAGE);			
		}		
	}
}
