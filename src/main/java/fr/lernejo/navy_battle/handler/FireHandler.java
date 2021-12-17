package fr.lernejo.navy_battle.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.game.Jeux;
import fr.lernejo.navy_battle.game.OutilJeux;
import fr.lernejo.navy_battle.util.HttpRequestUtil;

public class FireHandler implements HttpHandler{
	private final Data datas;
	private final Jeux jeux;
	private final OutilJeux outilJeux = new OutilJeux();

	public FireHandler(Data p_datas, Jeux p_jeux) {
		super();
		this.datas = p_datas;
		this.jeux = p_jeux;
		
	}
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String body = "";
		int code = 400;
		JSONObject bodyCreate = checkBody(exchange);
		if (!"GET".equals(exchange.getRequestMethod())) { code = 404;}
		else if (bodyCreate.getBoolean("passer")){
			body = createBody(bodyCreate.getString("consequence"), bodyCreate.getBoolean("shipLeft"));
			code = 202;
			this.datas.addData("monTour", "true");
		}
		exchange.getResponseHeaders().set("Accept", "application/json");
		exchange.getResponseHeaders().set("Content-Type", "application/json");
		exchange.sendResponseHeaders(code, body.length());
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(body.getBytes());
		}
	}
	
	private String createBody(String consequence , boolean shipLeft) {
		JSONObject obj = new JSONObject();
		this.outilJeux.addConsequenceShipLeft(obj , consequence,!shipLeft );
		return obj.toString();
	}
	private JSONObject checkBody(HttpExchange exchange) {
		Map<String, String> params = new HttpRequestUtil().queryToMap(exchange.getRequestURI().getQuery()); 
		if(params.get("cell") != null) {
			try {
				this.datas.addData("caseAdverseVisit", params.get("cell"));
				JSONObject body = this.jeux.subirAttaque();
				body.put("passer", true);
				return body;
			}catch (Exception e) {}
		}; 
		JSONObject body= new JSONObject();
		body.put("passer", false);
		return body;
	}
	
}
