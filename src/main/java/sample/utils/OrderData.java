package sample.utils;

import sample.model.Tweet;
import sample.utils.TweetComparator.typeTri;
import java.util.Collections;
import java.util.List;

public class OrderData {
	
    // Fonction order qui retourne une liste de tweet trié selon le type de tri
    public List<Tweet> order(List<Tweet> tweetList, String orderType) {
        // Si le type passé en paramètre est égale à date
    	if (orderType.equals("date")){
    		// Tri sur la liste passé en paramètre et utilise la class TweetComparator pour trier par date
    		Collections.sort(tweetList, new TweetComparator(typeTri.DATE));
        } else if (orderType.equals("user")){ // Sinon si égale à user
        	// Tri sur la liste passé en paramètre et utilise la class TweetComparator pour trier par user
        	Collections.sort(tweetList, new TweetComparator(typeTri.USER));
        } // Pas de else par défaut car pas d'autre valeur possible (le sinon si est pas utile mais plus simple pour la compréhension)
        // Retourne la liste de tweet trié
    	return tweetList;
    }
}
