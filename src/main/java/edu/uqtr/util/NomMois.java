package edu.uqtr.util;

/**
 * Récupère le nom d'un mois en français à partir d'un numéro
 */
public class NomMois {

    /**
     * Nom des mois
     */
    private static String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};

    /**
     * Retourne le nom du mois base sur le numéro de celui-ci.
     * @param numeroMois le numéro du mois.
     * @return le nom du mois associé au numéro
     */
    public static String getNom(int numeroMois) {
        if(numeroMois < 0 || numeroMois > 11) {
            throw new ArrayIndexOutOfBoundsException("Le numéro du mois doit être entre 0 et 11.");
        }

        return mois[numeroMois];
    }
}
