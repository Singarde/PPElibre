package testUnitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classe.Livre;

class testVerifNotVide {
	
	Livre livre = new Livre("", "Druide", null, "roman", "fantasy", 1, "correct", 0, 11);

	@Test
	void testIsFull() {
		assertEquals(verification.Verif.verifChampNonNull("test"),true);

	}
	@Test
	void testIsEmpty() {
		assertEquals(verification.Verif.verifChampNonNull(""),false);

	}
	@Test
	void testIsEmptyInClass() {
		
		assertEquals(verification.Verif.verifChampNonNull(livre.getISBN()),false);

	}
	@Test
	void testIsFullInClass() {
		assertEquals(verification.Verif.verifChampNonNull(livre.getTitre()),true);

	}

}
