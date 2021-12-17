package fr.lernejo.navy_battle.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BateauTest {
	
	private final Bateau bateau = new Bateau();

	@Test
	void checkCouler() {
		ArrayList<Navire> listeBateau = new ArrayList<Navire>();
		HashMap<String, Integer> position1 = new HashMap<String, Integer>();
		position1.put("collone", 1);
		position1.put("ligne", 1);
		Navire navire1 = new Navire(3, position1, false);
		HashMap<String, Integer> position2 = new HashMap<String, Integer>();
		position2.put("collone", 5);
		position2.put("ligne", 5);
		Navire navire2 = new Navire(3, position2, false);
		listeBateau.add(navire1);
		listeBateau.add(navire2);
		Assertions.assertEquals(bateau.checkCouler(listeBateau),false);
		navire1.toucherBateau( 1, 1);
		navire1.toucherBateau( 1, 2);
		navire1.toucherBateau( 1, 3);
		Assertions.assertEquals(bateau.checkCouler(listeBateau),false);
		navire2.toucherBateau( 5, 5);
		navire2.toucherBateau( 5, 6);
		navire2.toucherBateau( 5, 7);
		Assertions.assertEquals(bateau.checkCouler(listeBateau),true);
	}
	
	@Test
	void toucher() {
		HashMap<String, Integer> position= new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, false);
		Assertions.assertEquals(bateau.toucher(navire1, "B1"),true);
		Assertions.assertEquals(bateau.toucher(navire1, "B1"),true);
		Assertions.assertEquals(bateau.toucher(navire1, "B5"),false);
		Assertions.assertEquals(bateau.toucher(navire1, "J1"),false);
		Assertions.assertEquals(bateau.toucher(navire1, "J1"),false);
		Assertions.assertEquals(bateau.toucher(navire1, "17"),false);
	}
	
	
	@Test
	void checkTaille() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, false);
		Assertions.assertEquals(bateau.checkTaille(1,1, navire1),true);
		Assertions.assertEquals(bateau.checkTaille(2,1, navire1),true);
		Assertions.assertEquals(bateau.checkTaille(3,1, navire1),true);
		Assertions.assertEquals(bateau.checkTaille(4,1, navire1),false);
		Assertions.assertEquals(bateau.checkTaille(0,1, navire1),false);
		Assertions.assertEquals(bateau.checkTaille(7,1, navire1),false);
	}
	

	
	
	@Test
	void toucherCoulerRater() {
		Assertions.assertEquals(bateau.toucherCoulerRater("miss"),0);
		Assertions.assertEquals(bateau.toucherCoulerRater("hit"),1);
		Assertions.assertEquals(bateau.toucherCoulerRater("sunk"),2);
		Assertions.assertEquals(bateau.toucherCoulerRater("ici"),-1);
	}
	
	
}
