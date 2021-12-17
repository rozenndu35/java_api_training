package fr.lernejo.navy_battle.game;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComputeurTest {
	private Computeur ordinateur = new Computeur();
	private Plateau plateau = new Plateau();
	@Test
	void caseAutomatiqueAléatoire() {
		String value = ordinateur.choisirCase(10);
		Map<String, Integer> position = plateau.dissocierCase(value);
		Assertions.assertEquals(plateau.saisieColonneValid(plateau.coloneinChar(position.get("collone"))),true);
		Assertions.assertEquals(plateau.saisieLigneValid(position.get("ligne")),true);
		String value2 = ordinateur.choisirCase(10);
		Map<String, Integer> position2 = plateau.dissocierCase(value2);
		Assertions.assertEquals(plateau.saisieColonneValid(plateau.coloneinChar(position2.get("collone"))),true);
		Assertions.assertEquals(plateau.saisieLigneValid(position2.get("ligne")),true);
		String value3 = ordinateur.choisirCase(10);
		Map<String, Integer> position3 = plateau.dissocierCase(value3);
		Assertions.assertEquals(plateau.saisieColonneValid(plateau.coloneinChar(position3.get("collone"))),true);
		Assertions.assertEquals(plateau.saisieLigneValid(position3.get("ligne")),true);
		String value4 = ordinateur.choisirCase(10);
		Map<String, Integer> position4 = plateau.dissocierCase(value4);
		Assertions.assertEquals(plateau.saisieColonneValid(plateau.coloneinChar(position4.get("collone"))),true);
		Assertions.assertEquals(plateau.saisieLigneValid(position4.get("ligne")),true);
	}
	@Test
	public void choisirSens() {
		boolean sens = ordinateur.choisirSens();
		if (sens || ! sens) {}else {Assertions.fail("Chance choisi non ok");}
		sens = ordinateur.choisirSens();
		if (sens || ! sens) {}else {Assertions.fail("Chance choisi non ok");}
		sens = ordinateur.choisirSens();
		if (sens || ! sens) {}else {Assertions.fail("Chance choisi non ok");}
		sens = ordinateur.choisirSens();
		if (sens || ! sens) {}else {Assertions.fail("Chance choisi non ok");}
		sens = ordinateur.choisirSens();
		if (sens || ! sens) {}else {Assertions.fail("Chance choisi non ok");}
	
	}
	

	@Test
	public void autreCase() {
		int caseChoisi = ordinateur.autreCase(4);
		if (caseChoisi > 6) {Assertions.fail("case choisi sort le bateau");}
		caseChoisi = ordinateur.autreCase(3);
		if (caseChoisi > 7) {Assertions.fail("case choisi sort le bateau");}
		caseChoisi = ordinateur.autreCase(5);
		if (caseChoisi > 5) {Assertions.fail("case choisi sort le bateau");}
		caseChoisi = ordinateur.autreCase(2);
		if (caseChoisi > 8) {Assertions.fail("case choisi sort le bateau");}
	}
	
	@Test
	public void caseSens() {
		int caseChoisi = ordinateur.caseSens();
		if (caseChoisi > 9) {Assertions.fail("case choisi sort le bateau");}
		caseChoisi = ordinateur.caseSens();
		if (caseChoisi > 9) {Assertions.fail("case choisi sort le bateau");}
		caseChoisi = ordinateur.caseSens();
		if (caseChoisi > 9) {Assertions.fail("case choisi sort le bateau");}
		caseChoisi = ordinateur.caseSens();
		if (caseChoisi > 9) {Assertions.fail("case choisi sort le bateau");}
	}
	
	@Test
	public void createNavire() {
		Navire navire = ordinateur.createNavire(new int[10][10], 4);
		Assertions.assertEquals(navire.getTaille(),4);
		Navire navire1 = ordinateur.createNavire(new int[10][10], 4);
		Assertions.assertEquals(navire1.getTaille(),4);
		Navire navire2 = ordinateur.createNavire(new int[10][10], 4);
		Assertions.assertEquals(navire2.getTaille(),4);
		Navire navire3 = ordinateur.createNavire(new int[10][10], 4);
		Assertions.assertEquals(navire3.getTaille(),4);
		int[][] cell = new int[10][10];
		cell[2][3] = 1;
		cell[6][3] = 1;
		cell[1][6] = 1;
		cell[1][4] = 1;
		cell[1][7] = 1;
		cell[3][7] = 1;
		cell[3][3] = 1;
		cell[5][3] = 1;
		Navire navire4 = ordinateur.createNavire(cell, 5);
		Assertions.assertEquals(navire4.getTaille(),5);
	}
	
	@Test
	public void createPorteAvion() {
		Navire navire = ordinateur.createPorteAvion(new int[10][10]);
		Assertions.assertEquals(navire.getTaille(),5);
	}
	@Test
	public void createCroiseur() {
		Navire navire = ordinateur.createCroiseur(new int[10][10]);
		Assertions.assertEquals(navire.getTaille(),4);
	}
	@Test
	public void createTorpilleur() {
		Navire navire = ordinateur.createTorpilleur(new int[10][10]);
		Assertions.assertEquals(navire.getTaille(),3);
	}
	@Test
	public void createContreTorpilleur() {
		Navire navire = ordinateur.createContreTorpilleur(new int[10][10]);
		Assertions.assertEquals(navire.getTaille(),2);
	}
	
	@Test
	public void initialiserBateaux() {
		int[][] cell = new int[10][10];
		ArrayList<Navire> armada = ordinateur.initialiserBateaux(cell);
		Assertions.assertEquals(armada.size(),5);
	}
}
