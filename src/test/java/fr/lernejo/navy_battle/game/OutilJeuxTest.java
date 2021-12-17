package fr.lernejo.navy_battle.game;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.ServeurClientJeu;
import fr.lernejo.navy_battle.ServeurJeux;

public class OutilJeuxTest {
	private final OutilJeux jeux = new OutilJeux();
	
	@Test
	public void finJeux() {
		Assertions.assertEquals(jeux.finJeux(0),false);
		Assertions.assertEquals(jeux.finJeux(1),true);	
	}
	
	@Test
	public void connectOtherServer() {
		try {
			Data datasServeur = new Data();
			datasServeur.addData("monPort", "9099");
			ServeurClientJeu clientServeur = new ServeurClientJeu(datasServeur);
			Jeux jeux = new Jeux(clientServeur, datasServeur);
			ServeurJeux serveur = new ServeurJeux(datasServeur, jeux);
			serveur.initServeur();
			
			Data datasClient = new Data();
	    	ServeurClientJeu serveurClient = new ServeurClientJeu(datasClient); 
			this.jeux.connectOtherServer("http://localhost:9099", "false", serveurClient, datasClient);
			Assertions.assertEquals(datasClient.getData("adresseOtherServeur"),"http://localhost:9099");
			Assertions.assertEquals(datasClient.getData("monTour"),"false");
		}catch(Exception e) {}
	}
	
	@Test
	public void addConsequenceShipLeft() {
		JSONObject obj = new JSONObject();
		jeux.addConsequenceShipLeft(obj , "conseTest" , true);
		Assertions.assertEquals(obj.getString("consequence"),"conseTest");
		Assertions.assertEquals(obj.getBoolean("shipLeft"),true);	
		
		JSONObject obj2 = new JSONObject();
		jeux.addConsequenceShipLeft(obj2 , "conseTest" , false);
		Assertions.assertEquals(obj2.getBoolean("shipLeft"),false);
		
	}
	
}
