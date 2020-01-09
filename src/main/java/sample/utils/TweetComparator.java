package sample.utils;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import sample.model.Tweet;

public class TweetComparator implements Comparator<Tweet> {
	private typeTri type;
	// Pour l'utilisation des caractères accentués
	private Collator COLLATOR = Collator.getInstance(Locale.FRENCH);
	
	// Constructeur qui prend en paramètre le type de comparaison
	public TweetComparator(typeTri t) {
		// Le type de la class prend pour valeur le type entré en paramètre
		this.type = t;
	}

	// Fonction qui compare 2 tweets
	@Override
	public int compare(Tweet t1, Tweet t2) {
		// Si on veut trier par user (type == user)
		if (this.type == typeTri.USER) {
			// on retourne un entier construit grâce à la comparaison
			return COLLATOR.compare(t1.getUserId(), t2.getUserId());
		} else if (this.type == typeTri.DATE) { // Sinon si on veut trier par date (type == date)
			if (t1.getDate().before(t2.getDate())) return -1; // Si la date de t1 est antérieur à la date de t2 alors return -1
	        else if (t1.getDate().after(t2.getDate())) return 1; // Sinon si la date t1 est postérieur à la date t2 alors return 1
	        else return 0; // Sinon return 0
			// Le tri par date ne fonctionne pas très bien
		} else {
			// Sinon return 0 (dans le cas où type n'est n'y égale à typeTri.DATE ou typeTri.USER (Pas très utile ici mais obligatoire pour le fonctionnement de compare)
			return 0;
		}
	}
	
	// Création d'un type enum qui permet de choisir entre USER ou DATE
	public enum typeTri {
		USER,
		DATE;
	}
}

