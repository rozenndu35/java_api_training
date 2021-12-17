package fr.lernejo.navy_battle.game;

import java.util.HashMap;
import fr.lernejo.navy_battle.ServeurClientJeu;
import fr.lernejo.navy_battle.util.HttpRequestUtil;

public class Request {
	
	private final HttpRequestUtil requestUtil = new HttpRequestUtil();
	private final Bateau bateau = new Bateau();
		
	public HashMap<String, Integer> fireOther(ServeurClientJeu serveurclient ,String otherAdresse, String p_caseChoisi) {
		HashMap<String, String> requette = serveurclient.fireOther(otherAdresse, p_caseChoisi);
		if(requette.get("status").equals("202") ) {
			HashMap<String, Integer> info = new HashMap<>();
			HashMap<String, String> requettebody = this.requestUtil.jsonStringToMap(requette.get("body"));
			info.put("consequence", bateau.toucherCoulerRater(requettebody.get("consequence")));
			if(requettebody.get("shipLeft").equals("true")){
				info.put("shipLeft", 1);
			}else {
				info.put("shipLeft", 0);
			}
			return info;
		}
		return null;
	}
	
}
