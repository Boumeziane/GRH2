package com.rh.grh.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    private static final String DEFAULT_PATTERN = "dd/MM/yyyy";

    /**
     * Formater une date en chaîne (par défaut : dd/MM/yyyy)
     */
    public static String format(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    /**
     * Formater une date avec un pattern personnalisé
     */
    public static String format(LocalDate date, String pattern) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern(pattern, Locale.FRANCE));
    }

    /**
     * Parser une chaîne en LocalDate (par défaut : dd/MM/yyyy)
     */
    public static LocalDate parse(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
    }

    /**
     * Calculer le nombre de jours entre deux dates
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        return java.time.temporal.ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Calculer le nombre de mois entre deux dates
     */
    public static long monthsBetween(LocalDate start, LocalDate end) {
        return java.time.temporal.ChronoUnit.MONTHS.between(start, end);
    }

    /**
     * Calculer l’ancienneté (en années, mois, jours)
     */
    public static Period getPeriod(LocalDate start, LocalDate end) {
        return Period.between(start, end);
    }

    /**
     * Ajouter des jours à une date
     */
    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    /**
     * Vérifier si une date est comprise entre deux autres
     */
    public static boolean isBetween(LocalDate target, LocalDate start, LocalDate end) {
        return (target.isEqual(start) || target.isAfter(start)) &&
                (target.isEqual(end) || target.isBefore(end));
    }
}
