package com.advancewars.model.carte;

import com.advancewars.model.carte.math.CarteDistance;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CarteDistancetest {
    @Test
    public void initCarte() {
        Carte<GenerateurDeCarte.TypeTerrain> carteTerrain = new GenerateurDeCarte(10).generate();
        CarteDistance carteDistance = new CarteDistance(carteTerrain, new Point(0, 5));
        Long distance = carteDistance.getDistance(new Point(0, 0));
        Assertions.assertThat(distance).isEqualTo(10);
        Assertions.assertThat(carteDistance.getDistance(new Point(0, 9))).isEqualTo(8);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 0))).isEqualTo(28);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 9))).isEqualTo(26);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 0))).isEqualTo(28);
        Assertions.assertThat(carteDistance.getDistance(new Point(5, 9))).isEqualTo(18);
    }

    @Test
    public void initCarteWithObstacle() {
        Carte<GenerateurDeCarte.TypeTerrain> carteTerrain = new GenerateurDeCarte(10).generate();
        carteTerrain.setValue(1, 5, GenerateurDeCarte.TypeTerrain.MONTAGNE);
        CarteDistance carteDistance = new CarteDistance(carteTerrain, new Point(0, 5));
        Long distance = carteDistance.getDistance(new Point(0, 0));
        Assertions.assertThat(distance).isEqualTo(10);
        Assertions.assertThat(carteDistance.getDistance(new Point(0, 9))).isEqualTo(8);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 0))).isEqualTo(28);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 9))).isEqualTo(26);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 0))).isEqualTo(28);
        Assertions.assertThat(carteDistance.getDistance(new Point(9, 5))).isEqualTo(22);
    }

}