package sample.csvManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileHelper {
	
	// Fonction qui retourne le chemin d'un fichier
    public static String getResourcePath(String fileName) {
        // Initialisation d'un fichier f
    	final File f = new File("");
    	// Retourne le fichier f avec son chemin absolu
        return f.getAbsolutePath() + File.separator + fileName;
    }

    // Fonction qui retourne le nom complet d'un fichier
    public static File getResource(String fileName) {
    	// Initialisation d'un nom de fichier complet à l'aide de getResourcePath
        final String completeFileName = getResourcePath(fileName);
        // Retourne le fichier
        return new File(completeFileName);
    }

    // Fonction qui lit un fichier
    public static List<String> readFile(File file) throws IOException {
    	// Création d'une liste de string result
        List<String> result = new ArrayList<String>();
        // Création d'un fileReader pour lire le fichier
        FileReader fr = new FileReader(file);
        // Création d'un BufferedReader à partir de notre fileReader (plus efficace d'utiliser un BufferedReader)
        BufferedReader br = new BufferedReader(fr);
        // Parcours du fichier tant qu'il n'est pas vide
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            // Ajoute la ligne courante du fichier dans la liste de string
        	result.add(line);
        }
        // Ferme le BufferedReader
        br.close();
        // Ferme le FileReader
        fr.close();
        
        // Retourne la liste de string
        return result;
    }
}
