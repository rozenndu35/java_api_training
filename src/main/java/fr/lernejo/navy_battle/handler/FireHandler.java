package fr.lernejo.navy_battle.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import fr.lernejo.navy_battle.util.HttpRequestUtil;

public class FireHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {
		String body = "";
		int code = 400;
		if (!"GET".equals(exchange.getRequestMethod())) { code = 404;}
		else if ( checkBody(exchange)){
			body = createBody("miss", false);
			code = 202;
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
		obj.put("consequence", consequence);
		obj.put("shipLeft", shipLeft);
		return obj.toString();
	}
	private boolean checkBody(HttpExchange exchange) {
		Map<String, String> params = new HttpRequestUtil().queryToMap(exchange.getRequestURI().getQuery()); 
		if(params.get("cell") != null) {
			try {
				String collone = params.get("cell").substring(0,1); //lettre
				int ligne = Integer.parseInt(params.get("cell").substring(1,params.get("cell").length())); //nombre
				return true;
			}catch (Exception e) {}
		}; 
		return false;
	}
	
}
