package fr.lernejo.navy_battle.handler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.ServeurClientJeu;
import fr.lernejo.navy_battle.ServeurJeux;
import fr.lernejo.navy_battle.game.Jeux;

public class StartHandlerTest {
	@Test
	void handle() {
		try {

			Data datas = new Data();
			datas.addData("monPort", "9096");
			ServeurClientJeu clientServeur = new ServeurClientJeu(datas);
			Jeux jeux = new Jeux(clientServeur, datas);
			ServeurJeux serveur = new ServeurJeux(datas, jeux);
	        serveur.initServeur();
	        
			HttpClient client = HttpClient.newHttpClient();
			
			//post avec ok json
			HttpRequest requetePost = HttpRequest.newBuilder()
				    .uri(URI.create("http://localhost:9096/api/game/start"))
				    .setHeader("Accept", "application/json")
				    .setHeader("Content-Type", "application/json")
				    .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:9090\", \"message\":\"hello\"}"))
				    .build();
			CompletableFuture<HttpResponse<String>> completableFuturePOST = client.sendAsync(requetePost, HttpResponse.BodyHandlers.ofString());
			completableFuturePOST.thenApplyAsync(HttpResponse::headers);
			HttpResponse<String> responsePOST = completableFuturePOST.join();
			Assertions.assertEquals(responsePOST.statusCode(),202);
			Assertions.assertEquals(responsePOST.body(),"{\"id\":\"xxx\",\"message\":\"Start ressus\",\"url\":\"http://localhost:9096\"}");
			Assertions.assertEquals(datas.getData("otherPort"),"9090");

				
			//autre post avec manque un parametre json
			HttpRequest requestPOSTManque = HttpRequest.newBuilder()
			        .uri(URI.create("http://localhost:9096/api/game/start"))
			        .header("Accept", "application/json")
			        .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:9090\", \"test\":\"hello\"}"))
			        .build();
			int statusCodePOSTmanque = client.sendAsync(requestPOSTManque, HttpResponse.BodyHandlers.ofString())
			        .thenApplyAsync(HttpResponse::statusCode).join();
			Assertions.assertEquals(statusCodePOSTmanque,400);
			
			//autre post avec movais json
			HttpRequest requestPUT = HttpRequest.newBuilder()
			        .uri(URI.create("http://localhost:9096/api/game/start"))
			        .header("Accept", "application/json")
			        .PUT(HttpRequest.BodyPublishers.ofString("ping!"))
			        .build();
			int statusCodePUT = client.sendAsync(requestPUT, HttpResponse.BodyHandlers.ofString())
			        .thenApplyAsync(HttpResponse::statusCode).join();
			Assertions.assertEquals(statusCodePUT,404);
			
			//post avec movais json
			HttpRequest requestPOSTKO = HttpRequest.newBuilder()
			        .uri(URI.create("http://localhost:9096/api/game/start"))
			        .header("Accept", "application/json")
			        .POST(HttpRequest.BodyPublishers.ofString("ping!"))
			        .build();
			 int statusCodePOSTKO = client.sendAsync(requestPOSTKO, HttpResponse.BodyHandlers.ofString())
				        .thenApplyAsync(HttpResponse::statusCode).join();
			Assertions.assertEquals(statusCodePOSTKO,400);
			
		}catch(Exception e) {}
		
	}

	@Test
	void creatBody() {
		//TODO
	}
	
	@Test
	void checkBody() {
		//TODO
	}
}
