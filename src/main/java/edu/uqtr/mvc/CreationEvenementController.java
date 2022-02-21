package edu.uqtr.mvc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Contrôleur pour la création d'un nouvel événement
 */
public class CreationEvenementController {

    /**
     * Nom de l'événement
     */
    @FXML
    private TextField nomEvenement;

    /**
     * Date à laquelle il se déroule
     */
    @FXML
    private DatePicker dateEvenement;

    /**
     * Heure de début
     */
    @FXML
    private ComboBox heureDebut;

    /**
     * Minute de l'heure de début
     */
    @FXML
    private ComboBox minuteDebut;

    /**
     * Heure de la fin
     */
    @FXML
    private ComboBox heureFin;

    /**
     * Minute de l'heure de la fin
     */
    @FXML
    private ComboBox minuteFin;

    /**
     * Affiche un message à l'utilisateur
     */
    @FXML
    private Text champMessage;

    /**
     * Stage qui est affiché
     */
    private Stage stageAffiche;

    /**
     * Référence vers le parent
     */
    private CalendrierController parent;

    /**
     * Crée un nouveau contrôleur pour la fenêtre de création d'événements.
     *
     * @param stageAffiche le stage qui est affiché par ce contrôleur
     * @param parent le contrôleur parent
     */
    public CreationEvenementController(Stage stageAffiche, CalendrierController parent){
        this.stageAffiche = stageAffiche;
        this.parent = parent;
    }

    /**
     * Remplit les ComboBox avec les valeurs valides d'heure et de temps
     */
    @FXML
    private void initialize() {
        // Indicateur de l'heure
        ObservableList heure = FXCollections.observableArrayList(IntStream.rangeClosed(0, 23).
                mapToObj(i -> i).collect(Collectors.toList()));

        heureDebut.setItems(heure);
        heureFin.setItems(heure);

        // Indicateur des minutes
        ObservableList minute = FXCollections.observableArrayList(IntStream.rangeClosed(0, 3).
                mapToObj(i -> 15 * i).collect(Collectors.toList()));

        minuteDebut.setItems(minute);
        minuteFin.setItems(minute);
    }

    /**
     * Déclenche l'ajout d'événement à la liste des événements
     * @param event evenement qui s'est produit
     */
    @FXML
    private void ajouterEvenement(ActionEvent event) {
        // On valide que tous les champs sont remplis
        if (nomEvenement.getText().isBlank() || this.dateEvenement.getValue() == null ||
                heureDebut.getValue() == null || minuteDebut.getValue() == null ||
                heureFin.getValue() == null || minuteFin.getValue() == null) {

            champMessage.setText("Veuillez remplir tous les champs avant de soumettre.");
            return;
        }

        // On crée l'événement
        LocalDate dateEvenement = this.dateEvenement.getValue();    // Le mois est encodé de 1 à 12
        Calendar debut = Calendar.getInstance();
        debut.set(dateEvenement.getYear(), dateEvenement.getMonthValue() - 1, dateEvenement.getDayOfMonth(),
                (Integer) heureDebut.getValue(), (Integer) minuteDebut.getValue());

        Calendar fin = Calendar.getInstance();
        fin.set(dateEvenement.getYear(), dateEvenement.getMonthValue() - 1, dateEvenement.getDayOfMonth(),
                (Integer) heureFin.getValue(), (Integer) minuteFin.getValue());

        if(fin.before(debut)) {
            champMessage.setText("La fin de l'événement doit se situer après le début.");
            return;
        }

        Evenement evenement = new Evenement(nomEvenement.getText(), debut, fin);
        parent.ajouterEvenement(evenement);

        // Ferme la fenêtre
        stageAffiche.close();
    }

}