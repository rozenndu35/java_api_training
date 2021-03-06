package fr.lernejo.navy_battle.handler;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.util.JsonUtil;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

import java.io.InputStream;
import org.json.JSONObject;

public class StartHandler implements HttpHandler{
	private final Data datas;

	public StartHandler(Data p_datas) {
		super();
		this.datas = p_datas;
	}
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		InputStream bodyRequest = exchange.getRequestBody();
		String body = "";
		int code = 400;
		if (!"POST".equals(exchange.getRequestMethod())) { code = 404;}
		else if ( checkBody(bodyRequest) != null){
			body = createBody(exchange);
			code = 202;
		}
		exchange.sendResponseHeaders(code, body.length());
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(body.getBytes());
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
	
	private String checkBody(InputStream bodyRequest) {
		JSONObject json = new JsonUtil().inputStringTOJSON(bodyRequest);
		if (json != null) {
			try {
				String id = json.getString("id");
				String url = json.getString("url");
				String message = json.getString("message");
				this.datas.addData("otherPort", url.split(":")[2]);
				this.datas.addData("connectionEffectuer", "true");
				if( id != null && url != null && message != null) {
					return url;				
				}
			}catch(Exception e) {}
		}
		return null;
	}
		
}
