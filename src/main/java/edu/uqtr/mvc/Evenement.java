package edu.uqtr.mvc;

import java.util.Calendar;

/**
 * Événement entré par un utilisateur qui s'affiche dans son calendrier.
 */
public class Evenement implements Comparable<Evenement> {

    /**
     * Moment du début de l'événement.
     */
    private Calendar debut;

    /**
     * Moment de fin de l'événement.
     */
    private Calendar fin;

    /**
     * Nom de l'événement.
     */
    private String nom;

    /**
     * Crée un nouvel événement nommé et situé dans le temps.
     * @param nom le nom de l'événement.
     * @param debut le début de l'événement.
     * @param fin la fin de l'événement.
     */
    public Evenement(String nom, Calendar debut, Calendar fin) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
    }

    public String getNom() {
        return nom;
    }

    public Calendar getDebut() {
        return debut;
    }

    public Calendar getFin() {
        return fin;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public int compareTo(Evenement evenement) {
        return debut.compareTo(evenement.debut);
    }

    /**
     * Indique si la journée représentée par autre est la même que début.
     * @param autre l'autre journée à utiliser dans la comparaison.
     * @return
     */
    public boolean estJournee(Calendar autre) {
        return debut.get(Calendar.YEAR) == autre.get(Calendar.YEAR) &&
                debut.get(Calendar.DAY_OF_YEAR) == autre.get(Calendar.DAY_OF_YEAR);
    }
}
