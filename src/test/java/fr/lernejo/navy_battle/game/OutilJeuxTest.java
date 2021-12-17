package fr.lernejo.navy_battle.game;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutilJeuxTest {
	private final OutilJeux jeux = new OutilJeux();
	
	@Test
	public void finJeux() {
		Assertions.assertEquals(jeux.finJeux(0),false);
		Assertions.assertEquals(jeux.finJeux(1),true);	
	}
	
	@Test
	public void connectOtherServer() {
		// TODO
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
