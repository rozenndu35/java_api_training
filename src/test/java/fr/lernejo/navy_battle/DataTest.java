package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataTest {

	@Test
	void addData() {
		Data data = new Data();
		data.addData("test", "reussi");
		Assertions.assertEquals(data.getData("test"),"reussi");
	}
	@Test
	void getData() {
		Data data = new Data();
		data.addData("test", "reussi");
		Assertions.assertEquals(data.getData("test"),"reussi");
	}
	@Test
	void addOtherTour() {
		Data data = new Data();
		data.addOtherTour("url", "true");
		Assertions.assertEquals(data.getData("adresseOtherServeur"),"url");
		Assertions.assertEquals(data.getData("monTour"),"true");
	}
}
