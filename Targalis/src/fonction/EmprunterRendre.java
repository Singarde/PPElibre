package fonction;

import javax.swing.JTextPane;

import classe.Livre;
import classe.Login;
import conectionBDD.BddEmpruntRendre;

public class EmprunterRendre {
	
	public static void Emprunter (Login logActuel, Livre livre) {
		
		BddEmpruntRendre.connect(logActuel,livre,null,"emprunter");
		
	}
	public static void LivreEmprunter (Login logActuel, JTextPane tp) {
		
		BddEmpruntRendre.connect(logActuel,null,tp,"livreEmprunter");
		
	}

}
