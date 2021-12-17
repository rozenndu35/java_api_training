package fr.lernejo.navy_battle.game;

import java.util.ArrayList;

public class Bateau {
	
	private final Plateau plateau = new Plateau();
	
	public boolean checkCouler(ArrayList<Navire> p_armada ) {
		for (Navire navire : p_armada) {
			if(!navire.checkCouler()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean toucher(Navire p_navire, String p_caseSaisie) {
		int collone = this.plateau.coloneinInt(p_caseSaisie.charAt(0)); 
		int ligne = Integer.parseInt(p_caseSaisie.substring(1,p_caseSaisie.length())); 
		if(p_navire.getVertical() && collone == p_navire.getPostion().get("collone")) {
			return this.checkTaille(ligne, p_navire.getPostion().get("ligne"), p_navire );
		}else if( !p_navire.getVertical() && ligne == p_navire.getPostion().get("ligne")) {
			return this.checkTaille(collone, p_navire.getPostion().get("collone"), p_navire);
		}
		return false;
	}
	
	public boolean checkTaille(int p_saisie,int p_depart ,Navire p_navire) {
		if( (p_depart + p_navire.getTaille()) > p_saisie && p_saisie >= p_depart) {
			p_navire.toucherBateau(p_depart, p_saisie);
			return true;
		}
		return false;
	}
	
	
	public int toucherCoulerRater(String info) {
		switch (info) {
			case "miss": return 0;
			case "hit": return 1;
			case "sunk": return 2;
		}
		return -1;
	}
	
}
