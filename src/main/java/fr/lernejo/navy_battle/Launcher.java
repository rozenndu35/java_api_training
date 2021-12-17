package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Jeux;
import fr.lernejo.navy_battle.game.OutilJeux;

public class Launcher {

   public static void main(String args[]){
    	Data datas = new Data(); OutilJeux outilJeux = new OutilJeux();
    	ServeurClientJeu serveurClient = new ServeurClientJeu(datas); Jeux jeux = new Jeux(serveurClient, datas); ServeurJeux serveur = new ServeurJeux(datas, jeux);
    	    	
    	if (args.length > 0 ) { datas.addData("monPort", args[0]);
    		try{         
                serveur.initServeur();
                if (args.length > 1 ) {outilJeux.connectOtherServer(args[1], "false", serveurClient, datas);}
                else {
            		while( datas.getData("connectionEffectuer") == null) {}
            		outilJeux.connectOtherServer("http://localhost:"+ datas.getData("otherPort"), "true", serveurClient, datas);
            	}jeux.jouer();
    		}catch (Exception e) {}
    	}
    }
}