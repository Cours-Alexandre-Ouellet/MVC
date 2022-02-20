package edu.uqtr.mvc;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Calendar;
import java.util.List;

/**
 * Fabrique des cases de calendrier pour JavaFX
 */
public class FabriqueCaseFX implements IFabriqueCase {

    private final int LARGEUR_CASE = 100;
    private final int HAUTEUR_CASE = 100;
    private final int PADDING_CASE = 5;

    @Override
    public Object fabriquerCaseHoraire(List<Evenement> evenements, int jour, boolean actif) {
        VBox caseHoraire = new VBox();
        caseHoraire.setMinWidth(LARGEUR_CASE);
        caseHoraire.setMaxWidth(LARGEUR_CASE);
        caseHoraire.setMinHeight(HAUTEUR_CASE);
        caseHoraire.setMaxHeight(HAUTEUR_CASE);
        caseHoraire.getStyleClass().add("case_horaire");

        if(!actif) {
            caseHoraire.getStyleClass().add("innactif");
        }

        caseHoraire.setPadding(new Insets(PADDING_CASE, PADDING_CASE, PADDING_CASE, PADDING_CASE));
        Text texteJour = new Text(String.valueOf(jour));
        caseHoraire.getChildren().add(texteJour);

        for(Evenement evenement : evenements) {
            Text afficheurEvenement = new Text(
                    evenement.getNom() + "\n" + evenement.getDebut().get(Calendar.HOUR) + ":" +
                            evenement.getDebut().get(Calendar.MINUTE) +" à " + evenement.getFin().get(Calendar.HOUR) +
                            ":" + evenement.getFin().get(Calendar.MINUTE)
            );
            afficheurEvenement.setWrappingWidth(LARGEUR_CASE);

            caseHoraire.getChildren().add(afficheurEvenement);
        }

        return caseHoraire;
    }
}
