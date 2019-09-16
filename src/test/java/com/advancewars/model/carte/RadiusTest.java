package com.advancewars.model.carte;

import com.advancewars.model.carte.math.Radius;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RadiusTest {
    @Test
    public void perimeterShouldBeGood() {
        Radius radius = new Radius(3, false);
        Carte<Boolean> carte = radius.getCarte();
        Assertions.assertThat(carte.getValue(2, 5)).isTrue();
        Assertions.assertThat(carte.getValue(1, 4)).isTrue();
        Assertions.assertThat(carte.getValue(0, 3)).isTrue();

        Assertions.assertThat(carte.getValue(1, 2)).isTrue();
        Assertions.assertThat(carte.getValue(2, 1)).isTrue();
        Assertions.assertThat(carte.getValue(3, 0)).isTrue();

        Assertions.assertThat(carte.getValue(4, 1)).isTrue();
        Assertions.assertThat(carte.getValue(5, 2)).isTrue();

        Assertions.assertThat(carte.getValue(4, 3)).isTrue();
        Assertions.assertThat(carte.getValue(3, 4)).isTrue();
        System.out.println(carte);

    }

    @Test
    public void InShouldBeGood() {
        Radius radius = new Radius(3, false);
        Carte<Boolean> carte = radius.getCarte();
        Assertions.assertThat(carte.getValue(2, 4)).isTrue();
        Assertions.assertThat(carte.getValue(1, 3)).isTrue();
        Assertions.assertThat(carte.getValue(2, 3)).isTrue();
        Assertions.assertThat(carte.getValue(3, 3)).isTrue();
        Assertions.assertThat(carte.getValue(4, 2)).isTrue();
    }

    @Test
    public void OutShouldBeGood() {
        Radius radius = new Radius(3, false);
        Carte<Boolean> carte = radius.getCarte();
        Assertions.assertThat(carte.getValue(0, 5)).isFalse();
        Assertions.assertThat(carte.getValue(1, 5)).isFalse();
        Assertions.assertThat(carte.getValue(3, 5)).isFalse();
        Assertions.assertThat(carte.getValue(4, 5)).isFalse();
        Assertions.assertThat(carte.getValue(5, 5)).isFalse();

        Assertions.assertThat(carte.getValue(0, 4)).isFalse();
        Assertions.assertThat(carte.getValue(4, 4)).isFalse();
        Assertions.assertThat(carte.getValue(5, 4)).isFalse();

        Assertions.assertThat(carte.getValue(3, 5)).isFalse();

        Assertions.assertThat(carte.getValue(0, 2)).isFalse();

        Assertions.assertThat(carte.getValue(0, 1)).isFalse();
        Assertions.assertThat(carte.getValue(1, 1)).isFalse();
        Assertions.assertThat(carte.getValue(5, 1)).isFalse();

        Assertions.assertThat(carte.getValue(0, 0)).isFalse();
        Assertions.assertThat(carte.getValue(1, 0)).isFalse();
        Assertions.assertThat(carte.getValue(2, 0)).isFalse();
        Assertions.assertThat(carte.getValue(4, 0)).isFalse();
        Assertions.assertThat(carte.getValue(5, 0)).isFalse();
    }


    @Test
    public void size1() {
        Radius radius = new Radius(1, false);
        Carte<Boolean> carte = radius.getCarte();

    }
}
