package edu.uqtr.mvc;

import java.util.Calendar;

/**
 * Représente une période de l'année (paire mois, année)
 */
public class Periode {

    /**
     * Valeur de l'année.
     */
    private int annee;

    /**
     * Valeur de mois. 0 est le mois de janvier
     */
    private int mois;

    /**
     * Crée un nouvelle période à partir d'un numéro de mois et d'année.
     * @param annee le numéro de l'année de la période.
     * @param mois le numéro du mois de la période. 0 est le mois de janvier, 11 celui de décembre.
     * @throws IllegalArgumentException si le numéro de mois n'est pas valide
     */
    public Periode(int annee, int mois) throws IllegalArgumentException {
        if(mois < 0 || mois > 11) {
            throw new IllegalArgumentException("Le mois doit être entre 0 et 11.");
        }

        this.annee = annee;
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public int getMois() {
        return mois;
    }

    /**
     * Avance le compteur d'une période.
     */
    public void avancerPeriode() {
        mois ++;

        if(mois > 11) {
            mois = 0;
            annee++;
        }
    }

    /**
     * Recule le compteur d'une période.
     */
    public void reculerPeriode() {
        mois --;

        if(mois < 0) {
            mois = 11;
            annee--;
        }
    }

    /**
     * Convertit un calendrier Java en une période.
     * @param calendrier le calendrier à convertir.
     * @return La période associée au calendrier.
     * @throws IllegalArgumentException si les valeurs du calendrier sont hors de normes de la période.
     */
    public static Periode getPeriodeDeCalendar(Calendar calendrier) throws IllegalArgumentException {
        return new Periode(calendrier.get(Calendar.YEAR), calendrier.get(Calendar.MONTH));
    }

    /**
     * Vérifie si la période contient la date indiquée.
     * @param date la date pour laquelle vérifier la période.
     * @return true si la période contient la date, false autrement.
     */
    public boolean contientDate(Calendar date) {
        return date.get(Calendar.YEAR) == annee && date.get(Calendar.MONTH) == mois;
    }
}
