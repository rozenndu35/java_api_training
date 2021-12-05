package fr.lernejo.navy_battle.handler;

import fr.lernejo.navy_battle.util.JsonUtil;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import org.json.JSONObject;

public class StartHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		InputStream bodyRequest = exchange.getRequestBody();
		if (!"POST".equals(exchange.getRequestMethod())) {
			exchange.sendResponseHeaders(404, 0);
			try (OutputStream os = exchange.getResponseBody()) {
				os.write(0);
			}
			return;
		}else if ( checkBody(bodyRequest)){
			String body = createBody(exchange);
			exchange.sendResponseHeaders(202, body.length());
			try (OutputStream os = exchange.getResponseBody()) {
				os.write(body.getBytes());
			}
		}else {
			exchange.sendResponseHeaders(400, 0);
			try (OutputStream os = exchange.getResponseBody()) {
				os.write(0);
			}
		}		
	}
	
	private String createBody(HttpExchange exchange) {
		int port = exchange.getHttpContext().getServer().getAddress().getPort();
		JSONObject obj = new JSONObject();
		obj.put("id", "xxx");
		obj.put("url", "http://localhost:"+port);
		obj.put("message", "Start ressus");
		
		return obj.toString();
	}
	
	private boolean checkBody(InputStream bodyRequest) {
		JSONObject json = new JsonUtil().inputStringTOJSON(bodyRequest);
		if (json != null) {
			try {
				String id = json.getString("id");
				String url = json.getString("url");
				String message = json.getString("message");
				if( id != null && url != null && message != null) {
					return true;				
				}
			}catch(Exception e) {}
		}
		return false;
	}
		
}
