package fr.lernejo.navy_battle;

import java.util.HashMap;

public class Data {
	private final HashMap<String, String> datas = new HashMap<String, String>();
	//monPort
	//otherPort
	//adresseOtherServeur
	//monTour
	
	/*
	 * ajoute la donnée voulu en parametre
	 * @param p_key : la clé de la donnée 
	 * @param p_value : la valeur de la donnée 
	 */
	public void addData(String p_key, String p_value) {
		this.datas.put(p_key, p_value);
	}
	
	/*
	 * Recuperer la donnée voulu en parametre
	 * @return : la valeur de la donnée si elle n'est pas stocké retourne null
	 */
	public String getData(String p_key) {
		return this.datas.get(p_key);
	}
	
	/*
	 * initialiser les donner du jeux
	 * @param p_otherAdresse : l'addresse du deuxime serveur
	 * @param p_tour : si a nous de jouer
	 */
	public void addOtherTour(String p_otherAdresse, String p_tour) {
		this.datas.put("adresseOtherServeur", p_otherAdresse);
		this.datas.put("monTour", p_tour);
	}
}
