package com.advancewars.model.carte;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GenerateurDeCarteTest {
    @Test
    public void shouldCreateAMap() {
        GenerateurDeCarte generateurDeCarte = new GenerateurDeCarte(100);
        Carte carte = generateurDeCarte.generate();
        Assertions.assertThat(carte).isNotNull();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Assertions.assertThat(carte.getValue(i, j)).isNotNull().isEqualTo(GenerateurDeCarte.TypeTerrain.PLAINE);
            }
        }
    }


    @Test
    public void shouldCreateAMapWithForet() {
        GenerateurDeCarte generateurDeCarte = new GenerateurDeCarte(10);
        Carte carte = generateurDeCarte
                .withForet()
                .generate();
        Assertions.assertThat(carte).isNotNull();
    }

    @Test
    public void shouldCreateABigMapWithAll() {
        GenerateurDeCarte generateurDeCarte = new GenerateurDeCarte(100);
        Carte carte = generateurDeCarte
                .withForet()
                .withMontagne()
                .generate();
        System.out.println(carte);
        Assertions.assertThat(carte).isNotNull();
    }

    @Test
    public void shouldCreateABigMapWithRoute() {
        GenerateurDeCarte generateurDeCarte = new GenerateurDeCarte(100);
        Carte carte = generateurDeCarte
                .withRoute()
                .generate();
        System.out.println(carte);
        Assertions.assertThat(carte).isNotNull();
    }

}
