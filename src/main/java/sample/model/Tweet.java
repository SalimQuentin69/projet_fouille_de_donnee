package sample.model;

import java.util.Date;

// Utilisation d'interface pour plus de sécurité (pas forcément nécessaire pour un tel projet mais bon)
public interface Tweet {
	// Tweet peut utiliser ces différentes fonctions (méthodes vu qu'on est en objet mais ce n'est qu'une simple appelation de mes commentaires)
    String getUserId();
    Date getDate();
    String getText();
    String getRtid();
}