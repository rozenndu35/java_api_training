package fr.lernejo.navy_battle.game;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlateauJoueurTest {

	private PlateauJoueur plateau = new PlateauJoueur();
	@Test
	public void verifCaseBataeu() {
		Assertions.assertEquals(plateau.verifCaseBataeu(5, 3, 2, true, new int[10][10]),true);
		int[][] cell = new int[10][10];
		cell[2][3] = 1;
		Assertions.assertEquals(plateau.verifCaseBataeu(5, 3, 2, true, cell),false);
		int[][] cell2 = new int[10][10];
		cell2[4][3] = 1;
		Assertions.assertEquals(plateau.verifCaseBataeu(5, 3, 2, true, cell2),false);
		int[][] cell3 = new int[10][10];
		cell3[6][3] = 1;
		Assertions.assertEquals(plateau.verifCaseBataeu(5, 3, 2, true, cell3),false);
		Assertions.assertEquals(plateau.verifCaseBataeu(3, 1, 5, false, new int[10][10]),true);
		int[][] cell4 = new int[10][10];
		cell4[1][5] = 1;
		Assertions.assertEquals(plateau.verifCaseBataeu(3, 1, 5, false, cell4),false);
		int[][] cell5 = new int[10][10];
		cell5[1][6] = 1;
		Assertions.assertEquals(plateau.verifCaseBataeu(3, 1, 5, false, cell5),false);
		int[][] cell6 = new int[10][10];
		cell6[1][7] = 1;
		Assertions.assertEquals(plateau.verifCaseBataeu(3, 1, 5, false, cell6),false);
	}
	
	@Test
	public void placerBateau() {
		int[][] cell = new int[10][10];
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		plateau.placerBateau(3, true,position, cell);
		Assertions.assertEquals(cell[1][1],1);
		Assertions.assertEquals(cell[2][1],1);
		Assertions.assertEquals(cell[3][1],1);
		HashMap<String, Integer> position2 = new HashMap<String, Integer>();
		position2.put("collone", 4);
		position2.put("ligne", 4);
		plateau.placerBateau(3, false,position2 , cell);
		Assertions.assertEquals(cell[4][4],1);
		Assertions.assertEquals(cell[4][5],1);
		Assertions.assertEquals(cell[4][6],1);
	}
	@Test
	public void placerBateauVerticale() {
		int[][] cell = new int[10][10];
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		position.put("collone", 1);
		position.put("ligne", 1);
		plateau.placerBateauVerticale(3, position, cell);
		Assertions.assertEquals(cell[1][1],1);
		Assertions.assertEquals(cell[2][1],1);
		Assertions.assertEquals(cell[3][1],1);
		
	}
	@Test
	public void placerBateauHorizontale() {
		int[][] cell = new int[10][10];
		HashMap<String, Integer> position2 = new HashMap<String, Integer>();
		position2.put("collone", 4);
		position2.put("ligne", 4);
		plateau.placerBateauHorizontale(3, position2 , cell);
		Assertions.assertEquals(cell[4][4],1);
		Assertions.assertEquals(cell[4][5],1);
		Assertions.assertEquals(cell[4][6],1);
	}
	
	@Test
	public void toucherAdverse() {
		int[][] cell = new int[10][10];
		plateau.toucherAdverse("A0", cell);
		Assertions.assertEquals(cell[0][0],2);
	}
}
