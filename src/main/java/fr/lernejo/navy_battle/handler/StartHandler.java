package fr.lernejo.navy_battle.handler;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class StartHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		InputStream bodyRequest = exchange.getRequestBody();
		if (!"POST".equals(exchange.getRequestMethod())) {
			exchange.sendResponseHeaders(404, 0);
			return;
		}else if ( checkBody(bodyRequest)){
			String body = createBody(exchange);
			exchange.sendResponseHeaders(202, body.length());
			try (OutputStream os = exchange.getResponseBody()) {
				os.write(body.getBytes());
			}
		}else {
			exchange.sendResponseHeaders(400, 0);
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
		JSONObject json = inputStringTOJSON(bodyRequest);
		if (json != null) {
			String id = json.getString("id");
			String url = json.getString("url");
			String message = json.getString("message");
			if( id != null && url != null && message != null) {
				return true;				
			}
		}
		return false;
	}
	
	private JSONObject inputStringTOJSON(InputStream inputStream) {
		try {
		       BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		       StringBuilder responseStrBuilder = new StringBuilder();
		       String inputStr;
		       while ((inputStr = streamReader.readLine()) != null)
		           responseStrBuilder.append(inputStr);
		       JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
		       return jsonObject;
		   } catch (IOException | JSONException e) {
		       e.printStackTrace();
		   }
		return null;
	}

}
