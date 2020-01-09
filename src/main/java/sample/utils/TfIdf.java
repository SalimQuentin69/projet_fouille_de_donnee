package sample.utils;

import java.util.List;


// Cette classe n'a pas été utilisé dans le projet car nous ne savions pas comment l'implémenter à notre cas
// Elle est ici à titre indicatif et parce que nous avons essayé tout de même à l'implémenter

public class TfIdf {
	    
		// Fonction qui calcul le tf
	    public double tf(List<String> data, String word) {
	    	// Création d'un variable resultat initialisé à 0
	        double resultat = 0;
	        // Parcours de la list de String passé en paramètre
	        for (String oneWord : data) {
	        	// Si le mot passé en paramètre est égale (non sensible à la casse) à un mot trouvé dans les données
	            if (word.equalsIgnoreCase(oneWord))
	            	// Alors on ajoute +1 au résultat
	            	resultat++;
	        }
	        // Retourne la valeur du tf (le nombre de fois où le mot a été trouvé / la taille des données)
	        return resultat / data.size();
	    }

	    // Fonction qui calcul l'idf
	    public double idf(List<List<String>> datas, String word) {
	    	// Création d'un variable n initialisé à 0
	        double n = 0;
	        // Parcours la list de list
	        for (List<String> data : datas) {
	        	// Parcours la list de string 
	            for (String oneWord : data) {
	            	// Si le mot passé en paramètre est égale (non sensible à la casse) à un mot trouvé dans les données
	                if (word.equalsIgnoreCase(oneWord)) {
	                	// Ajoute +1 à n
	                    n++;
	                    // Sort de la deuxième boucle
	                    break;
	                }
	            }
	        }
	        // Retourne la valeur de l'idf (taille de datas / n) n = le nombre de ligne où il y a le mot
	        return Math.log(datas.size() / n);
	    }

	    // Fonction qui retourne la valeur trouvé pour tfidf
	    public double calculTfIdf(List<String> data, List<List<String>> datas, String word) {
	        return tf(data, word) * idf(datas, word);
	    }
}
