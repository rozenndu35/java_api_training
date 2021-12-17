package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.game.Jeux;

public class Launcher {

   public static void main(String args[]){
    	Data datas = new Data();
    	ServeurClientJeu serveurClient = new ServeurClientJeu(datas);
    	Jeux jeux = new Jeux(serveurClient, datas);
    	ServeurJeux serveur = new ServeurJeux(datas, jeux);
    	
    	
    	if (args.length > 0 ) {
    		System.out.println("Bienvenu");
    		datas.addData("monPort", args[0]);
    		try{         
                serveur.initServeur();
                if (args.length > 1 ) {
            		datas.addData("adresseOtherServeur", args[1]);
                    datas.addData("monTour", "false");
                    serveurClient.connectOther();
            	}else {
            		while( datas.getData("connectionEffectuer") == null) {}
                    datas.addData("adresseOtherServeur", "http://localhost:"+ datas.getData("otherPort"));
                    datas.addData("monTour", "true");
                    serveurClient.connectOther();
            	}
                jeux.jouer();
                
                
    		}catch (Exception e) {}
    		
    	}
    	
    	
    }
}