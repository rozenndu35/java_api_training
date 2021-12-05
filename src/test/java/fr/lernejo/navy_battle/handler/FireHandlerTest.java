package fr.lernejo.navy_battle.handler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lernejo.navy_battle.ServeurJeux;

public class FireHandlerTest {
	@Test
	void handle() {
		try {
			ServeurJeux serveur = new ServeurJeux(9097);
	        serveur.initServeur();
			HttpClient client = HttpClient.newHttpClient();
			
			//autre post avec movais json
			HttpRequest requestPUT = HttpRequest.newBuilder()
			        .uri(URI.create("http://localhost:9097/api/game/fire"))
			        .header("Accept", "application/json")
			        .PUT(HttpRequest.BodyPublishers.ofString("ping!"))
			        .build();
			int statusCodePUT = client.sendAsync(requestPUT, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
			Assertions.assertEquals(statusCodePUT,404);
			
			//post get ok
			HttpRequest requeteGet = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?cell=B2")).build();
			CompletableFuture<HttpResponse<String>> completableFutureGet = client.sendAsync(requeteGet, HttpResponse.BodyHandlers.ofString());
			completableFutureGet.thenApplyAsync(HttpResponse::headers);
			HttpResponse<String> responseGET = completableFutureGet.join();
			Assertions.assertEquals(responseGET.statusCode(),202);
			Assertions.assertEquals(responseGET.body(),"{\"consequence\":\"miss\",\"shipLeft\":false}");
		
			//autre get avec manque un parametre
			HttpRequest requestGetManque = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire")).build();
			int statusCodeGetmanque = client.sendAsync(requestGetManque, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
			Assertions.assertEquals(statusCodeGetmanque,400);
			
			
			//get avec movais param
			HttpRequest requestGetKO = HttpRequest.newBuilder().uri(URI.create("http://localhost:9097/api/game/fire?ici=B2")).build();
			 int statusCodeGetKO = client.sendAsync(requestGetKO, HttpResponse.BodyHandlers.ofString()).thenApplyAsync(HttpResponse::statusCode).join();
			Assertions.assertEquals(statusCodeGetKO,400);
			
		}catch(Exception e) {}
		
	}
	

}
