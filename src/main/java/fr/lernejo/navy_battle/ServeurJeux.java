package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;
import fr.lernejo.navy_battle.handler.CallHandler;
import fr.lernejo.navy_battle.handler.StartHandler;


public class ServeurJeux {
	private final int port;
	
	public ServeurJeux(int p_port) {
		this.port = p_port;
	}
	
	public void initServeur() throws IOException {
		System.out.println("debut");
		 InetSocketAddress addrToBind = new InetSocketAddress(this.port);
		 HttpServer httpSrv = HttpServer.create(addrToBind, 0);
		 httpSrv.setExecutor(Executors.newFixedThreadPool(1));
		 httpSrv.createContext("/ping", new CallHandler());
		 httpSrv.createContext("/api/game/start", new StartHandler());
		 
		 httpSrv.start();
    }

}
