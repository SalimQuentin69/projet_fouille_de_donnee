package sample.model;

import java.util.Date;

public class TweetSample implements Tweet {
	// Celui qui a tweeté le tweet
    private String userId;
    // La date à laquelle le tweet a été tweeté
    private Date date;
    // Le contenu du tweet
    private String text;
    // Si il y a un retweet alors rtid prend son nom
    private String rtid;

    // Accesseur de l'attribut userId
    public String getUserId() {
        return userId;
    }
    
    // Accesseur de l'attribut date
    public Date getDate() {
        return date;
    }
    
    // Accesseur de l'attribut text
    public String getText() {
        return text;
    }
    
    // Accesseur de l'attribut rtid
    public String getRtid() {
        return rtid;
    }
    
    // Mutateur de l'attribut userId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Mutateur de l'attribut text
    public void setText(String text) {
        this.text = text;
    }

    // Mutateur de l'attribut date
    public void setDate(Date date) {
        this.date = date;
    }

    // Mutateur de l'attribut rtid
    public void setRtid(String rtid) {
        this.rtid = rtid;
    }
    
    // Fonction toString() pour afficher un tweet
    public String toString() {   
    	// Création d'un String auquel on va ajouté les attributs
        String result =  "Tweeté par : " + getUserId() + "\n";
        result +=  "Date : " + getDate() + "\n";
        result +=  "Message : " + getText() + "\n";
        // Si rtid existe
        if (!getRtid().isEmpty()) {
        	result +=  "Re tweeté par : " + getRtid() + "\n";
        } // Sinon on n'affiche pas "Re tweeté par : "
        // Un séparateur (pour différentier les tweets)
        result += "*******************************************************************************************************************";
        // Retourne le string
        return result;	
    }
    
}
