package fr.lernejo.navy_battle.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OutilJeuxTest {
	private final OutilJeux jeux = new OutilJeux();
	
	@Test
	public void finJeux() {
		Assertions.assertEquals(jeux.finJeux(0),false);
		Assertions.assertEquals(jeux.finJeux(1),true);	
	}
	
	
	
}
