package fonction;


import java.awt.Choice;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classe.Auteur;
import conectionBDD.BddCreateBookAndAuthor;
import verification.Verif;
public class CreateAuthor {
	
	public static void createAuthor(Choice choice,JPanel pan1,Auteur auteur) {
		
		if(Verif.verifChampNonNull(auteur.getNom())&&Verif.verifChampNonNull(Integer.toString(auteur.getAnnee()))) {//si les champs sont vide ou pas
			JOptionPane.showMessageDialog(null, "Valider vous cette saisie ?", "vérification des champs",
					JOptionPane.INFORMATION_MESSAGE);
			
			BddCreateBookAndAuthor.connect(null,null,auteur, null, "createAutor");
			choice.setVisible(false);
			
			Choice cAuteur = new Choice();
			cAuteur.setBounds(146, 193, 104, 20);
			BddCreateBookAndAuthor.connect(null,null,null,cAuteur, "listeAuteur");
			pan1.add(cAuteur);
			
			
		}else {
			JOptionPane.showMessageDialog(null, "erreur de champs pour la création de l'auteur", "erreur de champs",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
