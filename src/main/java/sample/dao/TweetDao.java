package sample.dao;

import sample.model.Tweet;

import java.text.ParseException;
import java.util.List;

// Interface
public interface TweetDao {
	// Appel la fonction findAllTweets() de TweetDaoImpl
    List<Tweet> findAllTweets() throws ParseException;

}
