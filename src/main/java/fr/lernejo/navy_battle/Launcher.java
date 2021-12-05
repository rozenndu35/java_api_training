package fr.lernejo.navy_battle;

public class Launcher {

    public static void main(String args[]){
    	int port = Integer.parseInt(args[0]);
    	if (args.length > 0 ) {
    		try{         
                ServeurJeux serveur = new ServeurJeux(port);
                serveur.initServeur();
    		}catch (Exception e) {}
    	}
    	if (args.length > 1 ) {
    		try{     
                String adresseOtherServeur = args[1];
                ServeurClientJeu serveurClient = new ServeurClientJeu(port);
                serveurClient.connectOther(adresseOtherServeur);
    		}catch (Exception e) {}
    	}
    }
}