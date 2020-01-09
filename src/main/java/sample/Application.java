package sample;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import sample.csvManager.CsvFileHelper;
import sample.dao.TweetDao;
import sample.dao.TweetDaoImpl;
import sample.model.Tweet;
import sample.utils.Finder;
import sample.utils.OrderData;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Application extends JFrame {
	
	// JPanel principal
	private JPanel container = new JPanel();
	
	// Attributs pour la recherche de mot
	private JRadioButton jr1Center = new JRadioButton("UserId");
	private JRadioButton jr2Center = new JRadioButton("Tweet");
	private ButtonGroup bgCenter = new ButtonGroup();
	private JTextField jtfCenter = new JTextField("");
	private JLabel labelCenter = new JLabel("Mot à chercher : ");
	private JButton bCenter = new JButton ("OK");
	private JCheckBox check = new JCheckBox("Approfondir la recherche ?");
	
	private Boolean checkTrue = false;
	
	// Attributs pour le choix du fichier
	private JLabel labelTop = new JLabel("Choissiez le fichier à traiter : ");
	private JRadioButton jr1Top = new JRadioButton("climat.txt");
	private JRadioButton jr2Top = new JRadioButton("Foot.txt");
	private ButtonGroup bgTop = new ButtonGroup();
	
	// Attributs pour le choix du tri
	private JLabel labelBot = new JLabel("Trier par : ");
	private JRadioButton jr1Bot = new JRadioButton("date");
	private JRadioButton jr2Bot= new JRadioButton("user");
	private ButtonGroup bgBot = new ButtonGroup();

	// Création des liste de tweets utile
	private List<Tweet> tweetListWord = null;
	private List<Tweet> orderedListT = null;
	private List<Tweet> userIdListWord = null;
	private List<Tweet> orderedListUID = null;
	
	
	public Application() {
		
		// Initialise la fenêtre
		this.setTitle("Application Tweet");
		this.setSize(500, 175);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		// Le container
		// Ajoute un backgroude en blanc (on sait jamais)
		container.setBackground(Color.white);
		// Ajoute un layout pour pouvoir ensuite definir l'emplacement dans la fenêtre
		container.setLayout(new BorderLayout());
	
		// Ajoute un autre JPanel dans le container
		JPanel center = new JPanel();
		// Selection par défaut la recherche sur Tweet
		jr2Center.setSelected(true);
		// Initialise une police d'écriture pour le textField
		Font police = new Font("Arial", Font.PLAIN, 12);
		// Ajoute la police créée
	    jtfCenter.setFont(police);
	    // Ajoute une taille de préference
	    jtfCenter.setPreferredSize(new Dimension(150, 30));
		// Ajoute un bouton
	    bCenter.addActionListener(new BoutonRechercheListener());
	    // Ajoute la checkBox
	    check.addActionListener(new CheckListener());
	    // Créer un groupe de bouton
		bgCenter.add(jr1Center);
	    bgCenter.add(jr2Center);
	    // Les ajoute au JPanel du centre
	    center.add(labelCenter);
	    center.add(jtfCenter);
	    center.add(jr1Center);
	    center.add(jr2Center);
	    center.add(bCenter);
	    center.add(check);
	    
		// Ajoute un autre JPanel dans le container
		JPanel top = new JPanel();
		// Selection par défaut l'utilisation du fichier Foot.txt
		jr2Top.setSelected(true);
		// Créer un groupe de bouton
		bgTop.add(jr1Top);
		bgTop.add(jr2Top);
		// Les ajoute au JPanel du haut
		top.add(labelTop);
		top.add(jr1Top);
		top.add(jr2Top);
		
		// Ajoute un autre JPanel dans le container
		JPanel bot = new JPanel();
		// Selection par défaut le trie par utilisateur
		jr2Bot.setSelected(true);
		// Créer un groupe de bouton
		bgBot.add(jr1Bot);
		bgBot.add(jr2Bot);
		// Les ajoute au JPanel du bas
		bot.add(labelBot);
		bot.add(jr1Bot);
		bot.add(jr2Bot);
		
	    // Ajoute chaque JPanel intern à son container correspondant
	    container.add(top, BorderLayout.NORTH);
	    container.add(center, BorderLayout.CENTER);
	    container.add(bot, BorderLayout.SOUTH);
	    
	    // Ajoute à la fenêtre les container
	    this.setContentPane(container);
		
		// Affiche la fenêtre
		this.setVisible(true);            
	}       
	
	// Quand on active ou désactive le bouton check
	class CheckListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	// Si il est actif
	    	if (check.isSelected()) {
	    		// checkTrue passe à vrai
	    		checkTrue = true;
	    	} else {
	    		// Sinon passe à false
	    		checkTrue = false;
	    	}
	    }
	}
	
	// Quand on click sur le bouton OK
	class BoutonRechercheListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	// Pour simuler l'effacement de la console
            for (int i = 0; i < 50; ++i) System.out.println("\n");
	    	
            if ((tweetListWord == null && userIdListWord == null) && checkTrue == true) {
            	JOptionPane j = new JOptionPane();
            	// Affiche un message d'erreur car c'est une première recherche
            	j.showMessageDialog(null, "Première recherche !!! Ne pas activer le bouton check", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (tweetListWord == null && userIdListWord != null  && checkTrue == true && jr2Center.isSelected()) { // Si tweetListWord == null => pas de première recherche avec Tweet
				JOptionPane jop = new JOptionPane();
            	// Affiche un message d'erreur
				jop.showMessageDialog(null, "Pas de recherche avec tweet en amont !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else if (userIdListWord == null && tweetListWord != null && checkTrue == true && jr1Center.isSelected()) { // Si userIdListWord == null => pas de première recherche avec userId
				JOptionPane jop = new JOptionPane();
            	// Affiche un message d'erreur
				jop.showMessageDialog(null, "Pas de recherche avec UserId en amont !", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
            	// Si on rempli pas la barre du mot à rechercher
    	    	if (jtfCenter.getText().isEmpty()) {
    	    		// Alors on affiche un avertissement
    	    		// Initialise la fenêtre de l'avertissment
    	    		JOptionPane jop = new JOptionPane();
    	    		// Récupère l'option choisit sur la fenêtre d'avertissement
    		        int option = jop.showConfirmDialog(null, 
    		          "Voulez-vous vraiment lancer la recherche sur tous les tweets ? Cela peut prendre du temps", 
    		          "Lancement de la recherche", 
    		          JOptionPane.YES_NO_OPTION, 
    		          JOptionPane.WARNING_MESSAGE);
    		        
    		        // Si on click sur OK alors on veut que ça lance quand même
    		        if(option == JOptionPane.OK_OPTION){
    		        	// On lance la recherche
    		        	rechercheTweet();
    		        }
    		        
    	    	} else { // Dans le cas où on rempli la recherche
    	    		// On lance la recherche
    	    		rechercheTweet();
    	    	}
            }
	    }
	    
	    public void rechercheTweet() {
	    	// Ajoute le fichier choisit
	    	String FILE_NAME = "./data/" + jr2Top.getText();
	    	// Vérifie que le choix n'est pas celui de climat.txt
	    	if (jr1Top.isSelected()) {
	    		FILE_NAME = "./data/" + jr1Top.getText();
	    	}
	    	// Initialisation
	        File file;
	        TweetDao tweetDao = null;
	        Finder find = new Finder();
	        // précise le type de fichier ici considéré comme csv
	        file = CsvFileHelper.getResource(FILE_NAME);
	        
	        try {
	        	// Créer les tweets sous le format userId, date, text, rtid
				tweetDao = new TweetDaoImpl(file);
			} catch (IOException e2) { // Si exception
				// Affiche l'erreur
				e2.printStackTrace();
			}
	        // Initialisation d'une liste de tweet
	        List<Tweet> tweetList = null;
			try {
				// Ajoute tous les tweets dans une liste de tweet
				tweetList = tweetDao.findAllTweets();
			} catch (ParseException e1) { // Si exception
				// Affiche l'erreur
				e1.printStackTrace();
			}
			
			// Initialisation d'un compteur
			int cpt = 0;
			
			// Le choix de tri
			String choixTri = jr2Bot.getText();
			if (jr1Bot.isSelected())
				jr1Bot.getText();			
			
			// Si recherhe dans les tweets
	    	if (jr2Center.isSelected()) {
	    		// Si on veut approfondir la recherche
	    		if (checkTrue) {
					// Dans le cas où on veut trier par date	    		
		    		tweetListWord = find.tweetContainsWord(tweetListWord, jtfCenter.getText());
					// Change le checkTrue en false dans le cas où on veut recommencé une recherche
					checkTrue = false;
	    			
	    		} else  {
	    			// Dans le cas où on veut trier par date	    		
		    		tweetListWord = find.tweetContainsWord(tweetList, jtfCenter.getText());
	    		}
	    		// Tri par date à l'aide de la fonction order de la classe OrderData
				orderedListT = new OrderData().order(tweetListWord, choixTri);
	    		
				// Effectue la boucle d'affichage
	    		for (Tweet tweet: orderedListT) {
	    			// Affiche les tweets
	    			System.out.println(tweet);
	                // Compte le nombre de tweet
	                cpt++;
	            }
	    	} else {
	    		// Si on veut approfondir la recherche
	    		if (checkTrue) {
    				// Dans le cas où on veut trier par user
    				userIdListWord = find.userIdContainsWord(userIdListWord, jtfCenter.getText());
		    		// Change le checkTrue en false dans le cas où on veut recommencé une recherche
		    		checkTrue = false;
	    		} else {
	    			// Dans le cas où on veut trier par user	    		
		    		userIdListWord = find.userIdContainsWord(tweetList, jtfCenter.getText());
	    		}
				// Tri par user à l'aide de la fonction order de la classe OrderData
	    		orderedListUID = new OrderData().order(userIdListWord, choixTri);
	    		
	            // Effectue la boucle d'affichage
	    		for (Tweet tweet: orderedListUID) {
	                // Affiche les tweets
	    			System.out.println(tweet);
	    			// Compte le nombre de tweet
	                cpt++;
	            }
	    	}
	    	// Pour afficher le nombre de tweets
	    	System.out.println("Il y a " + cpt + " tweet(s) contenant le mot " + jtfCenter.getText());
            System.out.println("*******************************************************************************************************************");
	    }
	}
}

