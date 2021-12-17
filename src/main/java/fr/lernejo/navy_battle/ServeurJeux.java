package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpServer;

import fr.lernejo.navy_battle.game.Jeux;
import fr.lernejo.navy_battle.handler.CallHandler;
import fr.lernejo.navy_battle.handler.FireHandler;
import fr.lernejo.navy_battle.handler.StartHandler;


public class ServeurJeux {
	private final Data datas;
	private final Jeux jeux;
	
	public ServeurJeux(Data donnees, Jeux p_jeux) {
		this.datas = donnees;
		this.jeux = p_jeux;
	}
	
	public void initServeur() throws IOException {
		System.out.println("Initialisation de mon serveur sur le port ; " + this.datas.getData("monPort"));
		InetSocketAddress addrToBind = new InetSocketAddress(Integer.parseInt(this.datas.getData("monPort")));
		try {
			HttpServer httpSrv = HttpServer.create(addrToBind, 0);
			httpSrv.setExecutor(Executors.newFixedThreadPool(1));
			httpSrv.createContext("/ping", new CallHandler());
			httpSrv.createContext("/api/game/start", new StartHandler(this.datas));
			httpSrv.createContext("/api/game/fire", new FireHandler(this.datas, this.jeux));
			httpSrv.start();
		}catch (Exception e) {}
		
    }

}
