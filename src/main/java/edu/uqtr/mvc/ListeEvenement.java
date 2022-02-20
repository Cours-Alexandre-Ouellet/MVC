package edu.uqtr.mvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Contient une liste d'événement triés et définit des méthodes pour lister
 * les événements.
 */
public class ListeEvenement {

    /**
     * Contient la liste des événements triés par date de debut
     */
    private ArrayList<Evenement> listeInterne;

    /**
     * Crée un nouvelle liste pour y stocker des événements.
     */
    public ListeEvenement() {
        listeInterne = new ArrayList<Evenement>();
    }

    /**
     * Ajoute un événements à la liste de ceux-ci.
     * @param evenement l'événement à ajouter.
     */
    public void ajouter(Evenement evenement) {
        listeInterne.add(evenement);
    }

    /**
     * Retourne une liste d'événements qui se produisent durant cette journée.
     *
     * @param calendar le calendrier représentant la journée souhaitée.
     * @return La liste d'événements, triée par heure de début, commençant la journée indiquée.
     */
    public ArrayList<Evenement> getEvenementDuJour(Calendar calendar) {
        ArrayList<Evenement> evenementsJour = new ArrayList<>();

        // Trouve les événements du jour dans la liste
        // Note : Il y aurait beaucoup d'optimisation à faire à cet endroit
        for (Evenement evenement : listeInterne) {
            if (evenement.estJournee(calendar)) {
                evenementsJour.add((Evenement) evenement);
            }
        }

        // Trie les événements selon le comparateur définit
        evenementsJour.sort(Comparator.naturalOrder());

        return evenementsJour;
    }

    /**
     * Ajoute des exemples à la liste d'événements
     */
    public void ajouterExemples(){
        Calendar debutEvenement1 = Calendar.getInstance();
        debutEvenement1.set(2022, 1, 14, 8, 15);
        Calendar finEvenement1 = Calendar.getInstance();
        finEvenement1.set(2022, 1, 14, 11, 0);

        ajouter(new Evenement("Ex 1", debutEvenement1, finEvenement1));

        Calendar debutEvenement2 = Calendar.getInstance();
        debutEvenement2.set(2022, 1, 14, 13, 15);
        Calendar finEvenement2 = Calendar.getInstance();
        finEvenement2.set(2022, 1, 14, 16, 30);

        ajouter(new Evenement("Ex 2", debutEvenement2, finEvenement2));

        Calendar debutEvenement3 = Calendar.getInstance();
        debutEvenement3.set(2022, 1, 16, 8, 30);
        Calendar finEvenement3 = Calendar.getInstance();
        finEvenement3.set(2022, 1, 16, 11, 0);

        ajouter(new Evenement("Ex 3", debutEvenement3, finEvenement3));

        Calendar debutEvenement4 = Calendar.getInstance();
        debutEvenement4.set(2022, 1, 22, 17, 0);
        Calendar finEvenement4 = Calendar.getInstance();
        finEvenement4.set(2022, 1, 22, 19, 0);

        ajouter(new Evenement("Ex 4", debutEvenement4, finEvenement4));
    }

}
