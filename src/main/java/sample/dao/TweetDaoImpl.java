package sample.dao;


import sample.csvManager.CsvFileInterfaceImpl;
import sample.model.Tweet;
import sample.model.TweetSample;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TweetDaoImpl implements TweetDao {

    private File file;
    private CsvFileInterfaceImpl csvFile;

    // Constructeur simple
    private TweetDaoImpl() {
    	// Appel le constructeur de la class mère
        super();
    }

    // Constructeur qui prend en paramètre un fichier
    public TweetDaoImpl(File file) throws IOException {
        // Appel le constructeur simple de cette class on surcharge
    	this();
    	// Le file de la class prend pour valeur le file en paramètre
        this.file = file;
        // Le csvFile est initialisé avec la class CsvFileInterfaceImpl(file)
        this.csvFile = new CsvFileInterfaceImpl(file);
    }

    // Fonction qui prend un tableau de string pour en faire un objet tweet
    private Tweet tabToTweet(String[] tab) throws ParseException {
    	// Initialisation d'un tweet
        TweetSample tweet = new TweetSample();
        // position dans le tableau de string. Ici initialisé à 1
        int position = 1;
        
        if (tab.length < 3) // Gère le fait que le tweet n'a pas toutes les bonnes infos nécessaire
        	return null;
        else if (tab[2].length() < 19) // Gère le fait que la date ne soit pas au bon format
        	return null;
        
        // Si la taille du tableau est supérieur à la position (position = 1)
        if (tab.length > position ) {
        	// Si la tab[1] est égale à 000 normalement non vu que 000 (dans Foot.txt) serait en position 0 mais on ne sait jamais
            if (tab[position].equals("000")) {
                // Incrémente position
            	position ++;
            }
            // On ajoute tab[position] dans l'attribut userId de l'objet tweet
            tweet.setUserId(tab[position]);
            // Incrémente position
            position ++;
        } else
        	// Sinon rtid égale null dans le cas où il n'éxiste pas
            tweet.setRtid(null);

        // Si la taille du tableau est supérieur à la position (position = 2)
        if (tab.length > position ) {
        	// Création d'un formateur de date
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	// Création d'une date avec le string trouvé dans le tableau de string
            Date date = sdf.parse(tab[position]);
            // On ajoute date dans l'attribut date de l'objet tweet
            tweet.setDate(date);
            // Incrémente position
            position ++;
        } else
        	// Sinon rtid égale null dans le cas où il n'éxiste pas
            tweet.setRtid(null);

        // Si la taille du tableau est supérieur à la position (position = 3)
        if (tab.length > position ) {
        	// On ajoute le message trouvé dans le tableau dans l'attribut text de l'objet tweet
            tweet.setText(tab[position]);
            // Incrémente position
            position ++;
        } else
        	// Sinon rtid égale null dans le cas où il n'éxiste pas
            tweet.setRtid(null);

        // Si la taille du tableau est supérieur à la position (position = 4)
        if (tab.length > position ) {
        	// On ajoute le rtid trouvé dans le tableau dans l'attribut rtid de l'objet tweet
            tweet.setRtid(tab[position]);
        } else
        	// On ajoute une chaine vide dans le cas où il n'y pas de rtid dans l'attribut rtid de l'objet tweet 
            tweet.setRtid("");
        
        // Retourne l'objet tweet
        return tweet;
    }

    // Fonction qui trouve tous les tweets (Utilisé dans l'interface TweetDao)
    public List<Tweet> findAllTweets() throws ParseException {
    	// Création d'une liste de tweets
        List<Tweet> tweets = new ArrayList<Tweet>();
        // Création d'un tableau de string qui récupère le tableau de string créer à partir du fichier
        List<String[]> data = csvFile.getData();
        // Parcours le tableau de string data
        for(String[] oneData : data) {
        	// Création d'un tweet en utilisant la fonction tabToTweet
            Tweet tweet = tabToTweet(oneData);
            // Si le tweet n'est pas égale à null
            if (tweet != null) {
            	// Alors on ajoute le tweet à la liste de tweets
            	tweets.add(tweet);
            } else {
            	// Sinon on continu sans l'ajouter
            	continue;
            }
        }
        // On retourne la liste de tweets
        return tweets;
    }
}
