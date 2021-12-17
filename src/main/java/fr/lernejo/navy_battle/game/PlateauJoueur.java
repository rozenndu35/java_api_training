package fr.lernejo.navy_battle.game;

import java.util.Map;

public class PlateauJoueur extends Plateau{
	public boolean verifCaseBataeu(int p_taille, int caseSens, int autreCase, boolean p_vertical, int[][] p_cell) {
		if (p_vertical) {
			for ( int i = autreCase ; i < autreCase + p_taille; i++) {
				if (p_cell[i][caseSens] != 0) {
					return false;
				}
			}
		}else {
			for ( int i = autreCase ; i < autreCase + p_taille; i++) {
				if (p_cell[caseSens][i] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean placerBateau(int p_taille, boolean p_vertical,Map<String, Integer> p_postion , int[][] p_cell) {
		if (p_vertical) {
			int i  = p_postion.get("ligne");
			while (p_taille != 0 ) {
				p_cell[i][p_postion.get("collone")] = 1;
				p_taille --;
				i++;
			}
		}else {
			int i  = p_postion.get("collone");
			while (p_taille != 0 ) {
				p_cell[p_postion.get("ligne")][i] = 1;
				p_taille --;
				i++;
			}
		}
		return true;
	}
}
