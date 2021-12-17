package fr.lernejo.navy_battle.game;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlateauTest {
	private final Plateau plateau = new Plateau();
	@Test
	void coloneinInt() {
		Assertions.assertEquals(plateau.coloneinInt('A'),0);
		Assertions.assertEquals(plateau.coloneinInt('B'),1);
		Assertions.assertEquals(plateau.coloneinInt('C'),2);
		Assertions.assertEquals(plateau.coloneinInt('D'),3);
		Assertions.assertEquals(plateau.coloneinInt('E'),4);
		Assertions.assertEquals(plateau.coloneinInt('F'),5);
		Assertions.assertEquals(plateau.coloneinInt('G'),6);
		Assertions.assertEquals(plateau.coloneinInt('H'),7);
		Assertions.assertEquals(plateau.coloneinInt('I'),8);
		Assertions.assertEquals(plateau.coloneinInt('J'),9);
		Assertions.assertEquals(plateau.coloneinInt('K'),-1);
	}
	
	@Test
	void coloneinChar() {
		Assertions.assertEquals(plateau.coloneinChar(0),'A');
		Assertions.assertEquals(plateau.coloneinChar(1),'B');
		Assertions.assertEquals(plateau.coloneinChar(2),'C');
		Assertions.assertEquals(plateau.coloneinChar(3),'D');
		Assertions.assertEquals(plateau.coloneinChar(4),'E');
		Assertions.assertEquals(plateau.coloneinChar(5),'F');
		Assertions.assertEquals(plateau.coloneinChar(6),'G');
		Assertions.assertEquals(plateau.coloneinChar(7),'H');
		Assertions.assertEquals(plateau.coloneinChar(8),'I');
		Assertions.assertEquals(plateau.coloneinChar(9),'J');
	}
	
	@Test
	void saisieColonneValid() {
		Assertions.assertEquals(plateau.saisieColonneValid('A'),true);
		Assertions.assertEquals(plateau.saisieColonneValid('C'),true);
		Assertions.assertEquals(plateau.saisieColonneValid('K'),false);
		Assertions.assertEquals(plateau.saisieColonneValid('J'),true);
		
	}
	
	@Test
	void saisieLigneValid() {
		Assertions.assertEquals(plateau.saisieLigneValid(0),true);
		Assertions.assertEquals(plateau.saisieLigneValid(5),true);
		Assertions.assertEquals(plateau.saisieLigneValid(9),true);
		Assertions.assertEquals(plateau.saisieLigneValid(10),false);
		Assertions.assertEquals(plateau.saisieLigneValid(-1),false);
		
	}
	
	@Test
	void dissocierCase() {
		Map<String, Integer> position = plateau.dissocierCase("B5");
		Assertions.assertEquals(position.get("collone"),1);
		Assertions.assertEquals(position.get("ligne"),5);
		Map<String, Integer> position2 = plateau.dissocierCase("X5");
		Assertions.assertEquals(position2.get("collone"),-1);
		Assertions.assertEquals(position2.get("ligne"),5);
	}
	
	@Test
	void createPosition(){
		Map<String, Integer> position = plateau.createPosition(5,2) ;
		Assertions.assertEquals(position.get("collone"),5);
		Assertions.assertEquals(position.get("ligne"),2);
	}

	@Test
	void createCase(){
		Map<String, Integer> position = plateau.createPosition(5,2) ;
		Assertions.assertEquals(plateau.createCase(position),"C6");

	}
	
	@Test
	void afficherMonPlateau(){
		// TODO

	}
	
	@Test
	void afficherOtherPlateau(){
		//TODO 

	}

}