package fr.lernejo.navy_battle.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HttpRequestUtilTest {
	
	private final HttpRequestUtil HttpRequestUtil = new HttpRequestUtil();
	
	@Test
	void queryToMap() {
		Map<String, String> vide = HttpRequestUtil.queryToMap(null);
		Assertions.assertAll(
		        () -> Assertions.assertNotNull(vide),
		        () -> Assertions.assertNull(vide.get("cell"))
		    );
		
		Map<String, String> cell = HttpRequestUtil.queryToMap("cell=B2");
		Assertions.assertAll(
		        () -> Assertions.assertNotNull(cell),
		        () -> Assertions.assertEquals(cell.get("cell"), "B2")
		    );
		Map<String, String> cell2 =HttpRequestUtil.queryToMap("cell=B2&ici=J10");
		Assertions.assertAll(
		        () -> Assertions.assertNotNull(cell2),
		        () -> Assertions.assertEquals(cell2.get("cell"), "B2"),
		        () -> Assertions.assertEquals(cell2.get("ici"), "J10")
		    );
	}
	
	@Test
	void jsonStringToMap() {
		Map<String, String> vide = HttpRequestUtil.jsonStringToMap(null);
		Assertions.assertAll(
		        () -> Assertions.assertNotNull(vide),
		        () -> Assertions.assertNull(vide.get("cell"))
		    );
		Map<String, String> cell2 = HttpRequestUtil.jsonStringToMap("{\"consequence\":\"miss\",\"shipLeft\":false}");
		Assertions.assertAll(
		        () -> Assertions.assertNotNull(cell2),
		        () -> Assertions.assertEquals(cell2.get("consequence"), "miss"),
		        () -> Assertions.assertEquals(cell2.get("shipLeft"), "false")
		    );
	}
}
