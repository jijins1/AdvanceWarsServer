package com.advancewars.model.carte;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Carte<T> {


    private static Long DEFAULT = 1L;
    private List<Ligne<T>> mapRaw;

    public Carte(Integer largeur, Integer hauteur, T defaultValue) {
        this.mapRaw = new ArrayList<>();
        for (int i = 0; i < largeur; i++) {
            Ligne<T> ordonne = new Ligne<>(new ArrayList<>(hauteur));
            for (int j = 0; j < hauteur; j++) {
                ordonne.add(defaultValue);
            }
            mapRaw.add(ordonne);
        }

    }

    /**
     * Retourne la valeur au coordonnée x ,y
     *
     * @param x abcisse
     * @param y ordonnée
     * @return
     */
    public T getValue(Integer x, Integer y) {
        return mapRaw.get(x).get(y);
    }

    public T getValue(Point point) {
        return this.getValue(point.getX(), point.getY());
    }

    /**
     * Set la valeur au coordonnée x ,y
     *
     * @param x     abcisse
     * @param y     ordonnée
     * @param value
     * @return
     */
    public T setValue(Integer x, Integer y, T value) {
        return mapRaw.get(x).set(y, value);
    }

    public T setValue(Point point, T value) {
        return this.setValue(point.getX(), point.getY(), value);
    }

    public Integer getXSize() {
        return mapRaw.size();
    }

    public Integer getYSize() {
        return mapRaw.get(0).size();
    }

    public void putCarte(Carte<T> carte, Point center) {
        for (int x = 0; x < carte.getXSize(); x++) {
            for (int y = 0; y < carte.getYSize(); y++) {
                try {
                    T value = carte.getValue(x, y);
                    if (value != null) {
                        this.setValue(x + center.getX(), y + center.getY(), value);
                    }
                } catch (IndexOutOfBoundsException e) {

                }

            }
        }
    }

    public List<T> getLigneValue(Integer y) {
        ArrayList<T> ligne = new ArrayList<>();
        for (int x = 0; x < getXSize(); x++) {
            ligne.add(getValue(x, y));
        }
        return ligne;
    }

    public Ligne<T> getColonneValue(Integer x) {
        return mapRaw.get(x);
    }

    @Override
    public String toString() {
        String s = "";
        for (int y = getYSize() - 1; y >= 0; y--) {
            for (int x = 0; x < getYSize(); x++) {
                String value = getValue(x, y) == null ? "N" : getValue(x, y).toString();
                s = s + value;
            }
            if (y != 0) {
                s = s.concat("\n");
            }
        }
        return s;
    }

    public List<Ligne<T>> getRawValue() {
        return this.mapRaw;
    }

    public boolean pointIsIn(Point point) {
        Boolean xIn = point.getX() >= 0 && point.getX() < getXSize();
        Boolean yIn = point.getY() >= 0 && point.getY() < getYSize();

        return xIn && yIn;
    }

    ;
}
