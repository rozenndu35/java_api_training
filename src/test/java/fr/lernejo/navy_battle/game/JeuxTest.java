package fr.lernejo.navy_battle.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.ServeurClientJeu;

public class JeuxTest{
	@Test
	void subirAttaque() {
		Data datas = new Data();
		datas.addData("monPort", "9096");
		ServeurClientJeu clientServeur = new ServeurClientJeu(datas);
		Jeux jeux = new Jeux(clientServeur, datas);
		ArrayList<Navire> armada = jeux.getArmade();
		Plateau plateau = new Plateau();
		int nbBateau = 1;
		for (Navire navire : armada) {
			Map<String, Integer> positionBateau = navire.getPostion();
			Map<String, Integer> position = new HashMap<>();
			position.put("ligne", positionBateau.get("ligne"));
			position.put("collone", positionBateau.get("collone"));
			int taille = navire.getTaille();
			boolean vertical = navire.getVertical();
			datas.addData("caseAdverseVisit", plateau.createCase(position));
			JSONObject resultat = jeux.subirAttaque();
			Assertions.assertEquals(resultat.getString("consequence"),"hit");
			Assertions.assertEquals(resultat.getBoolean("shipLeft"),true);
			for (int avance = 1; avance < taille; avance ++ ) {
				if(vertical) {position.put(
						"ligne", (position.get("ligne") + 1));}
				else {position.put(
						"collone", (position.get("collone") + 1));}
				datas.addData("caseAdverseVisit", plateau.createCase(position));
				resultat = jeux.subirAttaque();
				if( avance != taille -1) {
					Assertions.assertEquals(resultat.getString("consequence"),"hit");
					Assertions.assertEquals(resultat.getBoolean("shipLeft"),true);
				}else if (nbBateau == 5) {
					Assertions.assertEquals(resultat.getString("consequence"),"sunk");
					Assertions.assertEquals(resultat.getBoolean("shipLeft"),false);
				}else {
					Assertions.assertEquals(resultat.getString("consequence"),"sunk");
					Assertions.assertEquals(resultat.getBoolean("shipLeft"),true);
				}
			}
			nbBateau ++;
		}
		
	}
}
