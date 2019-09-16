package com.advancewars.model.carte;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PointTest {
    @Test
    public void translatePointWithCoordonate() {
        Point pointOrigine = new Point(10, 10);
        Point pointArrive = pointOrigine.getPointAfterMouvement(5, 6);
        Assertions.assertThat(pointArrive.getX()).isEqualTo(15);
        Assertions.assertThat(pointArrive.getY()).isEqualTo(16);
    }

    @Test
    public void translatePointWithPoint() {
        Point pointOrigine = new Point(10, 10);
        Point pointIntermediaire = new Point(5, 6);
        Point pointArrive = pointOrigine.getPointAfterMouvement(pointIntermediaire);
        Assertions.assertThat(pointArrive.getX()).isEqualTo(15);
        Assertions.assertThat(pointArrive.getY()).isEqualTo(16);
    }

    @Test
    public void distanceCalcul() {
        Point pointOrigine = new Point(1, -2);
        Point pointCible = new Point(-9, 9);
        Assertions.assertThat(pointOrigine.getDistance(pointCible)).isEqualTo(21);
    }
}
