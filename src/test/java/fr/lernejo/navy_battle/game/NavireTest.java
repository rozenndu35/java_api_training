package fr.lernejo.navy_battle.game;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NavireTest {
	
	@Test
	void getTaille() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, false);
		Assertions.assertEquals(navire1.getTaille(), 3);
		Navire navire2 = new Navire(1, position, false);
		Assertions.assertEquals(navire2.getTaille(), 1);
	}
	
	@Test
	void getVertical() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, true);
		Assertions.assertEquals(navire1.getVertical(), true);
		Navire navire2 = new Navire(1, position, false);
		Assertions.assertEquals(navire2.getVertical(), false);
	}
	
	
	@Test
	void getPostion() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, true);
		Map<String, Integer> positionRecup = navire1.getPostion();
		Assertions.assertEquals(positionRecup.get("collone"), position.get("collone"));
		Assertions.assertEquals(positionRecup.get("ligne"), position.get("ligne"));
	}
	
	@Test
	void getCaseBateau() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, true);
		boolean[] caseB= navire1.getCaseBateau();
		Assertions.assertEquals(caseB[0], false);
		Assertions.assertEquals(caseB[1], false);
		Assertions.assertEquals(caseB[2], false);
	}
	
	@Test
	void toucherBateau() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(3, position, false);
		navire1.toucherBateau( 1, 1);
		navire1.toucherBateau( 1, 2);
		navire1.toucherBateau( 1, 3);
		boolean[] bateauTab =  navire1.getCaseBateau();
		Assertions.assertEquals(bateauTab[0], true);
		Assertions.assertEquals(bateauTab[1],true);
		Assertions.assertEquals(bateauTab[2],true);
	}
	
	@Test
	void couler() {
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		Navire navire1 = new Navire(0, position, false);
		boolean[] bateauVide = {};
		Assertions.assertEquals(navire1.checkCouler(),true);
		Navire navire2 = new Navire(1, position, false);
		navire2.toucherBateau( 1, 1);
		Assertions.assertEquals(navire2.checkCouler(),true);
		Navire navire3 = new Navire(2, position, false);
		navire3.toucherBateau( 1, 1);
		navire3.toucherBateau( 1, 2);
		Assertions.assertEquals(navire3.checkCouler(),true);
		Navire navire4 = new Navire(1, position, false);
		Assertions.assertEquals(navire4.checkCouler(),false);
		Navire navire5 = new Navire(2, position, false);
		navire5.toucherBateau( 1, 1);
		Assertions.assertEquals(navire5.checkCouler(),false);
		Navire navire6 = new Navire(2, position, false);
		Assertions.assertEquals(navire6.checkCouler(),false);
	}

}
