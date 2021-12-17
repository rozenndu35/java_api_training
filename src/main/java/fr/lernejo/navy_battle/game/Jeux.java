package fr.lernejo.navy_battle.game;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import fr.lernejo.navy_battle.Data;
import fr.lernejo.navy_battle.ServeurClientJeu;


public class Jeux {
	
	private final int TAILLE_PLATEAU = 10;
	private final int[][] monPlateau = new int[TAILLE_PLATEAU][TAILLE_PLATEAU];
	private final int[][] adversePlateau = new int[TAILLE_PLATEAU][TAILLE_PLATEAU];
	private final ArrayList<Navire> armada ;
	
	private final Plateau plateau= new Plateau();
	private final PlateauAdverse plateauAdverse = new PlateauAdverse();
	private final OutilJeux outilJeux= new OutilJeux();
	private final Computeur computeur= new Computeur();
	private final Bateau bateau= new Bateau();
	private final Request request = new Request();

	private final ServeurClientJeu serveurclient;
	private final Data datas;
	
	public Jeux( ServeurClientJeu serveurclient,Data p_datas) {
		this.serveurclient = serveurclient;
		this.datas = p_datas;
		this.armada = this.computeur.initialiserBateaux(this.monPlateau);
	}

	public void jouer() {
		System.out.println("Bienvenu dans la bataille naval");
		boolean continuer = true;
		while(continuer) {
			if(this.datas.getData("monTour").equals("true")) {
				continuer = this.lancerAttaque();
				this.datas.addData("monTour", "false");
			}
		}
		System.out.println("Le jeux est fini");
		
	}
		
	public boolean lancerAttaque() {
		//choisir la case
		String caseChoisi = computeur.choisirCase(this.TAILLE_PLATEAU);
		System.out.println(this.datas.getData("monPort") + " j'attaque : " + caseChoisi);
		while (plateauAdverse.checkDejaVisiter(adversePlateau, caseChoisi)) {
			caseChoisi = computeur.choisirCase(this.TAILLE_PLATEAU);
		}
		//lance l'attaque /api/game/fire retourne si on peut continuer
		return getInfoAdverse(caseChoisi);
	}
	
	public boolean getInfoAdverse( String p_caseChoisi) {
		HashMap<String, Integer> info = this.request.fireOther(this.serveurclient ,this.datas.getData("adresseOtherServeur"), p_caseChoisi);
		this.modifierInfoAdverse(p_caseChoisi, info);
		return this.outilJeux.finJeux(info.get("shipLeft"));
	}
	
	public void modifierInfoAdverse (String p_caseChoisi, HashMap<String, Integer> p_info) {
		// modifier le plateau adverse
		if(p_info.get("consequence") == 0) {adversePlateau[plateau.dissocierCase(p_caseChoisi).get("ligne")][plateau.dissocierCase(p_caseChoisi).get("collone")] = 1;
		}else {adversePlateau[plateau.dissocierCase(p_caseChoisi).get("ligne")][plateau.dissocierCase(p_caseChoisi).get("collone")] = 2;}
	}

	public JSONObject subirAttaque() {
		//metre a jour mes bateaux
		JSONObject obj = new JSONObject();
		for (Navire navire : this.armada) {
			boolean toucher = this.bateau.toucher(navire, this.datas.getData("caseAdverseVisit"));
			if(toucher) {
				return this.attaqueToucher(navire);
			}
		}
		obj.put("consequence", "miss");
		obj.put("shipLeft", true);
		return obj;
	}
	public ArrayList<Navire> getArmade(){
		return this.armada;
	}
	
	public JSONObject attaqueToucher(Navire p_navire) {
		JSONObject obj = new JSONObject();
		boolean couler = p_navire.checkCouler();
		boolean allCouler = bateau.checkCouler(this.armada);
		if(couler) { obj.put("consequence", "sunk");
		}else { obj.put("consequence", "hit");}
		obj.put("shipLeft", !allCouler);
		return obj;
	}
	
}
