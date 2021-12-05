package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class ServeurClientJeu {
	private final int port;
	private final HttpClient serveurC;
	
	public ServeurClientJeu(int p_port) {
		this.port = p_port;
		this.serveurC = HttpClient.newHttpClient();
	}
	
	public void connectOther(String p_otherAdress) {
		System.out.println("Connection au deuxieme serveur");
		HttpRequest requetePost = creationRequestPostStart(p_otherAdress);
		HashMap<String, String> reponse = makeReponseRequest(requetePost);
    }
	
	public HttpRequest creationRequestPostStart(String p_otherAdress) {
		return HttpRequest.newBuilder()
			    .uri(URI.create(p_otherAdress + "/api/game/start"))
			    .setHeader("Accept", "application/json")
			    .setHeader("Content-Type", "application/json")
			    .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"hello\"}"))
			    .build();
	}
	
	public HashMap<String, String> makeReponseRequest(HttpRequest p_requetePost){
		CompletableFuture<HttpResponse<String>> completableFuture = this.serveurC.sendAsync(p_requetePost, HttpResponse.BodyHandlers.ofString());
		completableFuture.thenApplyAsync(HttpResponse::headers);
		HttpResponse<String> response = completableFuture.join();
		HashMap<String, String> reponseInfo = new HashMap<>();
		reponseInfo.put("status", Integer.toString(response.statusCode()));
		reponseInfo.put("body", response.body());
		return reponseInfo;
	}
}
