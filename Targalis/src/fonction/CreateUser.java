package fonction;

import java.awt.Choice;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import classe.Utilisateur;
import conectionBDD.BddLoginAndCreateUser;
import conectionBDD.BddLoginAndCreateUserBoolean;
import verification.Verif;

public class CreateUser {

	public static void createUser(Utilisateur utilisateurRef, JTextField tfNom, JTextField tfPrenom,
			JTextField tfAdresse, JTextField tfCp, JTextField tfVille, JTextField tfEmail, JTextField tfTel,
			JTextField tfLogin, JTextField tfMdep, Choice cRole) {
		
		if (verrifChampsRemplie(utilisateurRef)) {//
			
			System.out.println("verification positif des champs");
			
			
			
		if (BddLoginAndCreateUserBoolean.connect(utilisateurRef,"verificationIfnotExistLogin")) {
			
			System.out.println("verification positif");
			
			JOptionPane.showMessageDialog(null, "Login déjà existant", "erreur de creation",
					JOptionPane.ERROR_MESSAGE);
			
		}else {
			System.out.println("verification négatif");
			JOptionPane.showMessageDialog(null, "Valider vous cette saisie ?", "vérification des champs",
					JOptionPane.INFORMATION_MESSAGE);
			
			BddLoginAndCreateUser.connect(utilisateurRef,null,null,null,"createUser");
			
		}
			
			
		}else {
			JOptionPane.showMessageDialog(null, "Il y a une erreur dans le formulaire", "vérification des champs",
					JOptionPane.ERROR_MESSAGE);
			
			
		}

	}

	public static boolean verrifChampsRemplie(Utilisateur utilisateurRef) {
		boolean resultat;

		if (Verif.verifChampNonNull(utilisateurRef.getNom()) && 
				Verif.verifChampNonNull(utilisateurRef.getPrenom()) && 
				Verif.verifChampNonNull(utilisateurRef.getAdresse())&&
				Verif.verifChampNonNull(utilisateurRef.getCp()) && 
				Verif.verifChampNonNull(utilisateurRef.getVille()) && 
				Verif.verifChampNonNull(utilisateurRef.getEmail()) && 
				Verif.verifChampNonNull(utilisateurRef.getTel()) && 
				Verif.verifChampNonNull(utilisateurRef.getLogin()) && 
				Verif.verifChampNonNull(utilisateurRef.getMdep())) {
			
			resultat = true;
		} else {
			resultat = false;
		}

		return resultat;

	}



}
