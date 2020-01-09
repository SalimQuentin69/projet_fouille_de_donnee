package sample.csvManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileInterfaceImpl implements CsvFileInterface {

    public final static char SEPARATOR = '\t';

    private File file;
    private List<String[]> data;

    // Constructeur simple
    private CsvFileInterfaceImpl() {
    }
    
    // Constructeur qui prend un fichier en paramètre
    public CsvFileInterfaceImpl(File file) throws IOException {
        // file de la class prend pour valeur le file en paramètre
    	this.file = file;
        // Appel la fonction (méthode) init();
    	init();
    }

    // Fonction d'initialisation
    private void init() throws IOException {
        // Création d'une liste de string line qui utilise la méthode de lecture de la classe CsvFileHelper
    	List<String> lines = CsvFileHelper.readFile(file);
    	// Initialisation de data avec la taille du fichier lu
        data = new ArrayList<String[]>(lines.size());
        // Initialisation du séparateur (pour les fichiers csv)
        String sep = Character.toString(SEPARATOR);
        // Parcours le fichier
        for (String line : lines) {
        	// Pour chaque ligne sépare tout les champs dans un tableau de string grâce au séparateur
            String[] oneData = line.split(sep);
            // Ajoute le tableau de string
            data.add(oneData);
        }
    }
    
    // Fonction qui retourne la liste de tableau de string
    // Utilisé dans l'interface
    public List<String[]> getData() {
        return data;
    }
}
