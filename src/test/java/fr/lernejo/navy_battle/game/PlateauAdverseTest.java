package fr.lernejo.navy_battle.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlateauAdverseTest {
	private final PlateauAdverse plateau = new PlateauAdverse();
	@Test
	void checkDejaVisiter() {
		int[][] cell = new int[10][10];
		Assertions.assertEquals(plateau.checkDejaVisiter(cell, "B2"),false);
		cell[0][0] = 1; //visiter
		Assertions.assertEquals(plateau.checkDejaVisiter(cell, "A0"),true);
		cell[1][1] = 1; //toucher -> visiter
		Assertions.assertEquals(plateau.checkDejaVisiter(cell, "B1"),true);
	}
}
