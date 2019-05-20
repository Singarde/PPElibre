package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classe.Auteur;
import classe.Livre;
import classe.Login;
import classe.Utilisateur;
import requetteSql.requetteSql;

class testRequetteSql {
	Login logActuelle = new Login(3,"test","xept3");
	Auteur auteur = new Auteur("nomTest", 1999, 6);
	Livre livre = new Livre("", "Druide", auteur, "roman", "fantasy", 1, "correct", 0, 11);
	Utilisateur utilisateurRef = new Utilisateur(2,"nom","prenom","3 rue des sapin","05000",
			"Gap","administrateur","test@test.fr","06-74-74-52-70","test","xept3");
	

	
	@Test
	void verrifConection() {
		assertEquals(requetteSql.verrifConection(logActuelle),"SELECT * FROM utilisateur WHERE LOGIN='test'" +
	"AND MDP='xept3';");

	}
	@Test
	void recupRole() {
		assertEquals(requetteSql.recupRole(logActuelle),"SELECT ROLE FROM utilisateur WHERE LOGIN='test'" + "AND MDP='xept3';");

	}
	@Test
	void verrifIfLoginExiste() {
		assertEquals(requetteSql.verrifIfLoginExiste(utilisateurRef),"SELECT * FROM utilisateur WHERE LOGIN='test';");

	}
	@Test
	void creatUser() {
		assertEquals(requetteSql.creatUser(utilisateurRef),"INSERT INTO utilisateur (ROLE, NOM, PRENOM, ADRESSE, CODEPOSTAL, VILLE, TEL, EMAIL, LOGIN, MDP) "
				+ "VALUES ('administrateur','nom','prenom','3 rue des sapin','05000',"
				+ "'Gap','06-74-74-52-70','test@test.fr','test','xept3');");

	}

}
