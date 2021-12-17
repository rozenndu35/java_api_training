package fr.lernejo.navy_battle.game;

import java.util.Map;

public class PlateauAdverse extends Plateau{
	public boolean checkDejaVisiter(int[][] plateau, String visit) {
		Map<String, Integer> dissocier = this.dissocierCase(visit);
		if (plateau[dissocier.get("ligne")][dissocier.get("collone")] == 1 || plateau[dissocier.get("ligne")][dissocier.get("collone")] == 2) {
			return true;
		}
		return false;
	}
}
