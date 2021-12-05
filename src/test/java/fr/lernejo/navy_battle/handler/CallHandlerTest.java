package fr.lernejo.navy_battle.handler;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.lernejo.navy_battle.ServeurJeux;

public class CallHandlerTest {
	@Test
	void handleCall() {
		try {
			ServeurJeux serveur = new ServeurJeux(9095);
	        serveur.initServeur();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
			        .uri(URI.create("http://localhost:9095/ping"))
			        .header("Accept", "application/json")
			        .POST(HttpRequest.BodyPublishers.ofString("ping!"))
			        .build();
			CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
			completableFuture
				.thenApplyAsync(HttpResponse::headers);
			HttpResponse<String> response = completableFuture.join();
			Assertions.assertEquals(response.statusCode(),200);
			Assertions.assertEquals(response.body(),"OK");
		}catch(Exception e) {}
		
	}
}
