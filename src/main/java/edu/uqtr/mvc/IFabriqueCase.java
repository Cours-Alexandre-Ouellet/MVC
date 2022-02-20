package edu.uqtr.mvc;

import javafx.scene.Node;

import java.util.List;

/**
 * Interface de base pour les fabriques de case.
 *
 * Assure de découpler le contrôleur de la vue
 */
public interface IFabriqueCase {

    /**
     * Fabrique une case pour le calendrier pour l'affichage graphique
     * @param evenements la liste d'événements de la journée
     * @param jour le numéro du jour
     * @param actif est-ce que le jour est dans le mois actif
     * @return Un objet graphique représentant la case
     */
    public Object fabriquerCaseHoraire(List<Evenement> evenements, int jour, boolean actif);

}
