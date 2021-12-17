package fr.lernejo.navy_battle.game;

import org.json.JSONObject;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.ServeurClientJeu;

public class OutilJeux {
	
	public boolean finJeux( Integer fin) {
		if(fin == 0 ) {return false;}
		return true;
	}

	public void connectOtherServer(String p_adresse, String p_tour, ServeurClientJeu p_serveurClient, Data p_datas) {
		p_datas.addOtherTour(p_adresse, p_tour );
		p_serveurClient.connectOther();
	}
	
	public void addConsequenceShipLeft(JSONObject obj , String p_consequence, Boolean p_shipLeft) {
		obj.put("consequence", p_consequence);
		obj.put("shipLeft", p_shipLeft);
	}
}
