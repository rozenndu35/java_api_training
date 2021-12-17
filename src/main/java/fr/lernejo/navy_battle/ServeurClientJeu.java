package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class ServeurClientJeu {
	private final Data datas;
	private final HttpClient serveurC;
	
	public ServeurClientJeu(Data p_data) {
		this.datas = p_data;
		this.serveurC = HttpClient.newHttpClient();
	}
	
	public void connectOther() {
		System.out.println("Connection au deuxieme serveur sur : " + this.datas.getData("adresseOtherServeur"));
		HttpRequest requetePost = creationRequestPostStart();
		makeReponseRequest(requetePost);
    }
	
	public HashMap<String, String> fireOther(String p_otherAdress, String p_choixCase) {
		HttpRequest requeteGet = creationRequestGetFire(p_otherAdress, p_choixCase);
		
		return makeReponseRequest(requeteGet);
    }
	
	public HttpRequest creationRequestPostStart() {
		return HttpRequest.newBuilder()
			    .uri(URI.create(this.datas.getData("adresseOtherServeur") + "/api/game/start"))
			    .setHeader("Accept", "application/json")
			    .setHeader("Content-Type", "application/json")
			    .POST(BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:" + this.datas.getData("monPort") + "\", \"message\":\"hello\"}"))
			    .build();
	}
	public HttpRequest creationRequestGetFire(String p_otherAdress, String p_choixCase) {
		return HttpRequest.newBuilder().uri(URI.create(p_otherAdress + "/api/game/fire?cell=" + p_choixCase)).build();
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
