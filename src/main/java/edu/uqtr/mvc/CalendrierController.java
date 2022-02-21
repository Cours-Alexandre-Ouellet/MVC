package edu.uqtr.mvc;

import edu.uqtr.util.NomMois;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

/**
 * Contrôleur de l'affichage du calendrier
 */
public class CalendrierController {

    /**
     * TilePane affichant le calendrier
     */
    @FXML
    private TilePane calendrier;

    /**
     * Champ texte ou le mois affiché est indiqué
     */
    @FXML
    private Text indicateurMois;

    /**
     * Contient la période affichée à l'écran.
     */
    private Periode periodeAffichee;

    /**
     * Référence vers la fabrique de cases
     */
    private FabriqueCaseFX fabriqueCase;

    /**
     * Liste tous les événements du calendrier
     */
    private ListeEvenement listeEvenement;

    /**
     * Crée le contrôleur de calendrier
     */
    public CalendrierController() {
        fabriqueCase = new FabriqueCaseFX();
        listeEvenement = new ListeEvenement();

        // Ajout de quelques événements pour un démonstration...
        listeEvenement.ajouterExemples();
    }

    public void ajouterEvenement(Evenement evenement) {
        listeEvenement.ajouter(evenement);
        affichezPeriode();
    }

    /**
     * Initialise l'affichage du contrôleur de calendrier
     */
    @FXML
    private void initialize() {
        periodeAffichee = Periode.getPeriodeDeCalendar(Calendar.getInstance());     // Période du jour actuel

        affichezPeriode();
    }

    /**
     * Passe à la période précédente.
     */
    @FXML
    private void affichezPeriodePrecedente() {
        periodeAffichee.reculerPeriode();
        affichezPeriode();
    }

    /**
     * Passe à la période suivante.
     */
    @FXML
    private void affichezPeriodeSuivante() {
        periodeAffichee.avancerPeriode();
        affichezPeriode();
    }

    /**
     * Lance la fenêtre permettant d'ajouter un nouvel événement.
     */
    @FXML
    private void ajouterEvenement(ActionEvent event) {
        try {
            // Crée une nouvelle fenêtre et charge le FXML
            Stage stage = new Stage();
            FXMLLoader chargeurFXML = new FXMLLoader(CalendrierController.class.getResource("creation-evenement.fxml"));

            // Passe des valeurs au contrôleur parent
            CreationEvenementController creationEvenementController = new CreationEvenementController(stage, this);
            chargeurFXML.setController(creationEvenementController);

            // Crée la scéne
            Scene scene = new Scene(chargeurFXML.load(), 400, 400);

            // Paramètres de la fenêtre
            stage.setTitle("Ajouter événement");
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);     // On doit fermer cette fenêtre avant de revenir au calendrier
            stage.show();



        } catch (IOException exception) {
            System.err.println("Impossible de charger le fichier FXML pour afficher la fenêtre d'ajout d'événement.");
        }
    }

    /**
     * Affiche le calendrier lié à une période.
     */
    @FXML
    private void affichezPeriode() {
        // Retire tous les enfants du calendrier
        while (!calendrier.getChildren().isEmpty()) {
            calendrier.getChildren().remove(0);
        }

        // Information pour l'affichage du calendrier
        Calendar jour = calculerPremierJourAffiche();
        Calendar dernierJourPeriode = calculerDernierJourPeriode();

        // Boucle sur les jours tant que la fin de la période n'a pas été atteinte et la fin de la semaine.
        // S'arrête si jour semaine = 0 et que le jour est après la fin de la période
        int jourSemaine = 1;
        while (jourSemaine != 1 || !jour.after(dernierJourPeriode)) {
            affichezCase(jour);

            // Avancer d'un jour
            jourSemaine++;
            if (jourSemaine > 7) {
                jourSemaine = 1;
            }
            jour.add(Calendar.DAY_OF_YEAR, 1);
        }

        // Affiche le mois et l'année
        indicateurMois.setText(NomMois.getNom(periodeAffichee.getMois()) + " " + periodeAffichee.getAnnee());
    }

    /**
     * Affiche une case dans le calendrier graphique.
     */
    private void affichezCase(Calendar jour) {
        Node caseHoraire = (Node) fabriqueCase.fabriquerCaseHoraire(listeEvenement.getEvenementDuJour(jour),
                jour.get(Calendar.DAY_OF_MONTH), periodeAffichee.contientDate(jour));

        calendrier.getChildren().add(caseHoraire);  // Ajoute la case au TilePane
    }

    /**
     * Récupère le premier jour à afficher pour la période donnée.
     *
     * @return Un objet calendrier pointant sur le premier jour à afficher.
     */
    private Calendar calculerPremierJourAffiche() {
        Calendar premierJourAffiche = Calendar.getInstance();

        // Premier jour de la période
        premierJourAffiche.set(periodeAffichee.getAnnee(), periodeAffichee.getMois(), 1);

        // Jour de la semaine du premier jour
        int jourSemaine = premierJourAffiche.get(Calendar.DAY_OF_WEEK);

        // On récupère le jour de la première case
        premierJourAffiche.add(Calendar.DAY_OF_YEAR, -1 * (jourSemaine - 1));

        return premierJourAffiche;
    }

    /**
     * Récupère le dernier jour de la période à afficher.
     *
     * @return Un objet calendrier pointant sur le dernier jour de la période.
     */
    private Calendar calculerDernierJourPeriode() {
        Calendar dernierJour = Calendar.getInstance();
        dernierJour.set(periodeAffichee.getAnnee(), periodeAffichee.getMois(), 1);
        int dernierJourMois = dernierJour.getActualMaximum(Calendar.DAY_OF_MONTH);

        dernierJour.set(Calendar.DAY_OF_MONTH, dernierJourMois);

        return dernierJour;
    }


}
