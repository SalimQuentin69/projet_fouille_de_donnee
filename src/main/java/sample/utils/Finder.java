package sample.utils;
import sample.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class Finder {

	// Constructeur
    public Finder() {
    }
    
    // Fonction pour chercher un mot dans un tweet
    public List<Tweet> tweetContainsWord(List<Tweet> tweets, String word) {
    	// Création une liste de tweet
    	List<Tweet> tweetContainWord = new ArrayList<Tweet>();
    	// Recherche dans la liste passée en paramètre
        for (Tweet tweet: tweets) {
        	// Si le mot recherché passé en paramètre correspond à un mot dans un tweet
            if (tweet.getText().contains(word)) {
            	// On ajoute le tweet dans la liste tweetContainWord créé au début de la fonction
                tweetContainWord.add(tweet);
            }
        }
        // Retourne la nouvelle liste de tweet
        return tweetContainWord;
    }
    
    // Fonction pour chercher un utlisateur précis
    public List<Tweet> userIdContainsWord(List<Tweet> tweets, String word) {
    	// Création une liste de tweet
        List<Tweet> userIdContainWord = new ArrayList<Tweet>();
        // Recherche dans la liste passée en paramètre
        for (Tweet tweet: tweets) {
        	// Si le nom de l'utilisateur passé en paramètre correspond au userId d'un tweet
            if (tweet.getUserId().contains(word)) {
            	// On ajoute le tweet dans la liste userIdContainsWord créé au début de la fonction
            	userIdContainWord.add(tweet);
            }
        }
        // Retourne la nouvelle liste de tweet
        return userIdContainWord;
    }
}