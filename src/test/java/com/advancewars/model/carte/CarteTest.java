package com.advancewars.model.carte;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarteTest {
    @Before
    public void init() {

    }

    @Test
    public void createCarteWithRightLength() {
        Carte<Long> carte = new Carte(10, 10, 1L);
        assertThat(carte.getValue(9, 9)).isNotNull().isEqualTo(1L);
    }

    @Test
    public void createCarteWithRightGetLength() {
        Carte<Long> carte = new Carte(10, 15, 1L);
        assertThat(carte.getXSize()).isNotNull().isEqualTo(10);
        assertThat(carte.getYSize()).isNotNull().isEqualTo(15);
    }

    @Test
    public void toStringRight() {
        Carte<Long> carte = new Carte<>(2, 2, 0L);
        carte.setValue(0, 0, 1L);
        carte.setValue(1, 0, 2L);
        carte.setValue(0, 1, 3L);
        carte.setValue(1, 1, 4L);
        System.out.println(carte.toString());
        assertThat(carte.toString()).isEqualTo("34\n12");
    }

    @Test
    public void testIn() {
        Carte<Long> carte = new Carte(10, 10, 1L);
        assertThat(carte.pointIsIn(new Point(-1, 0))).isFalse();
        assertThat(carte.pointIsIn(new Point(0, -1))).isFalse();
        assertThat(carte.pointIsIn(new Point(10, 0))).isFalse();
        assertThat(carte.pointIsIn(new Point(0, 10))).isFalse();
        assertThat(carte.pointIsIn(new Point(0, 0))).isTrue();
        assertThat(carte.pointIsIn(new Point(9, 9))).isTrue();
    }

}
