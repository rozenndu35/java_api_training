package fr.lernejo.navy_battle;

public class Launcher {

    public static void main(String args[]){
    	if (args.length > 0 ) {
    		try{                
                int port = Integer.parseInt(args[0]);
                ServeurJeux serveur = new ServeurJeux(port);
                serveur.initServeur();
    		}catch (Exception e) {
    			
    		}
    	}
    	
    }
}