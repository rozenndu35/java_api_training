package fr.lernejo.navy_battle.game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

public class Computeur {
	private final Plateau plateau = new Plateau();
	private final PlateauJoueur plateauJoueur = new PlateauJoueur();
	
	public String choisirCase(int max) {
		SecureRandom random = new SecureRandom();
        int ligne = random.nextInt(max);
		int colonne = random.nextInt(max);
		char colonneC = this.plateau.coloneinChar(colonne);
		return String.valueOf(colonneC)+ligne;
	}
	
	public ArrayList<Navire> initialiserBateaux( int[][] p_cell) {
		ArrayList<Navire> listeBateau = new ArrayList<Navire>();
		listeBateau.add(this.createPorteAvion(p_cell));
		listeBateau.add(this.createCroiseur(p_cell));
		listeBateau.add(this.createTorpilleur(p_cell));
		listeBateau.add(this.createContreTorpilleur(p_cell));
		listeBateau.add(this.createContreTorpilleur(p_cell));
		return listeBateau;
	}
	
	public Navire createPorteAvion( int[][] p_cell) {
		return createNavire(p_cell, 5);
	}
	public Navire createCroiseur( int[][] p_cell) {
		return createNavire(p_cell, 4);
	}
	public Navire createTorpilleur( int[][] p_cell) {
		return createNavire(p_cell, 3);
	}
	public Navire createContreTorpilleur( int[][] p_cell) {
		return createNavire(p_cell, 2);
	}
	public Navire createNavire( int[][] p_cell, int taille) {
		boolean verticale = this.choisirSens();
		int sens = this.caseSens();
		int autreSens = this.autreCase(taille);
		while ( ! this.plateauJoueur.verifCaseBataeu(taille, sens, autreSens, verticale, p_cell)) {
			sens = this.caseSens();
			autreSens = this.autreCase(taille);
		}
		Map<String, Integer> position ;
		if(verticale) {position = this.plateau.createPosition(sens, autreSens);}
		else {position = this.plateau.createPosition(autreSens, sens);}
		this.plateauJoueur.placerBateau(taille, verticale, position, p_cell);
		return new Navire(taille, position, verticale);
	}
	
	public boolean choisirSens() {
		SecureRandom random = new SecureRandom();
        int ligne = random.nextInt(100);
        if (ligne < 49) {
        	return true;
        }
        return false;
	}
	public int caseSens() {
		SecureRandom random = new SecureRandom();
        return random.nextInt(10);
	}
	public int autreCase(int p_tailleBateau) {
		SecureRandom random = new SecureRandom();
        return random.nextInt(10 - p_tailleBateau );
	}
}
