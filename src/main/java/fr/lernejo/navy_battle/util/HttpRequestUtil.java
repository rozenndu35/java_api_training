package fr.lernejo.navy_battle.util;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtil {
	public Map<String, String> queryToMap(String query) {
	    Map<String, String> result = new HashMap<>();
	    try {
		    for (String param : query.split("&")) {
		        String[] entry = param.split("=");
		        if (entry.length > 1) {
		            result.put(entry[0], entry[1]);
		        }
		    }
	    }catch (Exception e) {}
	    return result;
	}
	public HashMap<String, String> jsonStringToMap(String json) {
	    HashMap<String, String> result = new HashMap<>();
	    try {
	    	json = json.replace("{", ""); 
	    	json = json.replace("}", ""); 
	    	json = json.replace("\"", ""); 
		    for (String param : json.split(",")) {
		        String[] entry = param.split(":");
		        if (entry.length > 1) {
		            result.put(entry[0], entry[1]);
		        }
		    }
	    }catch (Exception e) {}
	    return result;
	}
}
