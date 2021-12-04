package fr.lernejo.navy_battle.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonUtilTest {

	private final JsonUtil jsonUtil = new JsonUtil();
	
	@Test
	void inputStringTOJSON() {
		Assertions.assertEquals(jsonUtil.inputStringTOJSON(null), null);
		
		//creer un input stream avec un json
	}
}