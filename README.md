## Utilisation

Veuillez charger les fichiers `foot.txt` ainsi que `climat.txt` dans le dossier `executable/data/`.
Une fois les données char�es, exécutez la commande suivante: 

```
java -jar projet_fouille_de_donnee-0.0.1-SNAPSHOT.jar
```


## Recompiler le projet :
```
mvn clean install  # Dans le répertoire courant contenant le pom.xml, le jar sera généré dans target

# Remarque : il Faudra cependant crée le dossier data au même niveau que le .jar car les fichiers son chargé selon leur chemin relatif. (String FILE_NAME = "./data/" + jr2Top.getText();
```
