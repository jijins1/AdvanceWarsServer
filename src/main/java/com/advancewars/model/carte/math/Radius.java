package com.advancewars.model.carte.math;

import com.advancewars.model.carte.Carte;
import com.advancewars.model.carte.Point;

import java.util.function.Function;

public class Radius {
    private static Point diagonaleLeftDown = new Point(-1, -1);
    private static Point diagonaleRigthDown = new Point(1, -1);
    private static Point diagonaleRightUp = new Point(1, 1);
    private static Point diagonaleLeftUp = new Point(-1, 1);
    private Integer radiusSize;
    private Carte<Boolean> carte;
    private Boolean isRandomSize;


    public Radius(Integer radiusSize) {
        new Radius(radiusSize, true);
    }

    public Radius(Integer radiusSize, Boolean isRandomSize) {
        this.radiusSize = radiusSize;
        this.isRandomSize = isRandomSize;
        this.carte = new Carte<>(radiusSize * 2, radiusSize * 2, false);
    }


    private void makePerimeter() {
        Integer realRadius = getRealRadius();
        Point center = new Point(realRadius - 1, realRadius - 1);
        Point current = center.getPointAfterMouvement(0, realRadius);
        current = makeDiagonale(current, diagonaleLeftDown, point -> !point.getY().equals(center.getY() + 1));
        current = makeDiagonale(current, diagonaleRigthDown, point -> !point.getX().equals(center.getX() + 1));
        current = makeDiagonale(current, diagonaleRightUp, point -> !point.getY().equals(center.getY()));
        makeDiagonale(current, diagonaleLeftUp, point -> !point.getX().equals(center.getX()));
    }

    private Point makeIn() {
        for (int y = 0; y < this.carte.getYSize(); y++) {
            boolean isBetween = false;
            boolean ligneAsAtLeastTwoPoint = this.carte.getLigneValue(y).stream().filter(Boolean::booleanValue).count() > 1;
            for (int x = 0; x < this.carte.getXSize(); x++) {
                Boolean coordinateValue = this.carte.getValue(x, y);
                this.carte.setValue(x, y, coordinateValue || isBetween);
                isBetween = Boolean.logicalXor(isBetween, coordinateValue) && ligneAsAtLeastTwoPoint;
            }
        }
        return null;
    }

    private Point makeDiagonale(Point current, Point sens, Function<Point, Boolean> conditionStop) {
        while (conditionStop.apply(current)) {
            carte.setValue(current, true);
            current = current.getPointAfterMouvement(sens);
        }
        return current;
    }

    private Integer getRealRadius() {
        double decimalRealRadius = isRandomSize ? Math.random() * radiusSize : radiusSize;
        return (int) decimalRealRadius;
    }

    public Carte<Boolean> getCarte() {
        makePerimeter();
        makeIn();
        return carte;
    }
}
