package fr.lernejo.navy_battle;

public class Launcher {

    public static void main(String args[]){
    	if (args.length > 1 ) {
    		try{                
                int port = Integer.parseInt(args[0]);
                String adresseOtherServeur = args[1];
                ServeurJeux serveur = new ServeurJeux(port);
                serveur.initServeur();
                ServeurClientJeu serveurClient = new ServeurClientJeu(port);
                serveurClient.connectOther(adresseOtherServeur);
                
    		}catch (Exception e) {}
    	}else if (args.length > 0 ) {
    		try{                
                int port = Integer.parseInt(args[0]);
                ServeurJeux serveur = new ServeurJeux(port);
                serveur.initServeur();
    		}catch (Exception e) {}
    	}
    }
}