package com.advancewars.model.carte.math;

import com.advancewars.model.carte.Carte;
import com.advancewars.model.carte.Point;

import java.util.Random;

public class Road {
    private Carte<Boolean> carte;
    private boolean isHorizontal;
    private boolean isReverse;
    private Point cursorPosition;

    public Road(Integer hauteur, Integer largeur, Boolean isHorizontal) {
        this.carte = new Carte<>(hauteur, largeur, false);
        this.isHorizontal = isHorizontal;
    }

    private void makeRoute() {
        startRoute();
        createRoute();
    }

    private void createRoute() {
        while (carte.pointIsIn(cursorPosition)) {
            addValue();
        }
    }

    private void startRoute() {
        Random random = new Random();
        if (this.isHorizontal) {
            this.cursorPosition = new Point(0, random.nextInt(carte.getYSize()));
        } else {
            this.cursorPosition = new Point(random.nextInt(carte.getXSize()), 0);
        }
        addValue();
    }

    private void addValue() {
        carte.setValue(cursorPosition, true);
        Integer facteur = isReverse ? -1 : 1;
        if (isHorizontal) {
            cursorPosition = cursorPosition.getPointAfterMouvement(facteur, 0);
        } else {
            cursorPosition = cursorPosition.getPointAfterMouvement(0, facteur);
        }
        isHorizontal = Boolean.logicalXor(isHorizontal, new Random().nextDouble() < 0.2);
        isReverse = Boolean.logicalXor(isReverse, new Random().nextDouble() < 0.003);

    }

    public Carte<Boolean> getCarte() {
        makeRoute();
        return carte;
    }
}
