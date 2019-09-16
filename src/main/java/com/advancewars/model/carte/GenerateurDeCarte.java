package com.advancewars.model.carte;

import com.advancewars.model.carte.math.Radius;
import com.advancewars.model.carte.math.Road;
import lombok.Getter;

import java.util.Random;

public class GenerateurDeCarte {
    private Carte<TypeTerrain> carte;
    private Integer size;

    public GenerateurDeCarte(Integer size) {
        carte = new Carte<>(size, size, TypeTerrain.PLAINE);
        this.size = size;
    }

    private Carte<TypeTerrain> changeCarteBoolenToTypeTerrainValue(Carte<Boolean> booleanCarte, TypeTerrain value) {
        Carte<TypeTerrain> newCarte = new Carte<>(booleanCarte.getXSize(), booleanCarte.getYSize(), null);
        for (int x = 0; x < booleanCarte.getXSize(); x++) {
            for (int y = 0; y < booleanCarte.getYSize(); y++) {
                if (booleanCarte.getValue(x, y)) {
                    newCarte.setValue(x, y, value);
                }
            }
        }
        return newCarte;
    }

    public GenerateurDeCarte withForet() {
        withRadiusTerrainType(TypeTerrain.FORET, 5);
        return this;
    }

    public GenerateurDeCarte withMontagne() {
        withRadiusTerrainType(TypeTerrain.MONTAGNE, 4);
        return this;
    }

    private void withRadiusTerrainType(TypeTerrain terrainType, Integer facteur) {
        Random random = new Random();
        Double nbPointD = size * random.nextDouble() * ((1 / Double.parseDouble(facteur.toString())));
        Integer nbPoint = nbPointD.intValue();
        for (int i = 0; i < nbPoint; i++) {
            int radiusSize = random.nextInt(Integer.divideUnsigned(size, facteur));
            if (radiusSize > 1) {
                Radius radius = new Radius(radiusSize, false);
                Carte<TypeTerrain> carteForet = changeCarteBoolenToTypeTerrainValue(radius.getCarte(), terrainType);
                carte.putCarte(carteForet, new Point(random.nextInt(size), random.nextInt(size)));
            }
        }
    }

    public GenerateurDeCarte withRoute() {
        withRoadTerrainType(TypeTerrain.ROUTE, 4);
        return this;
    }

    private void withRoadTerrainType(TypeTerrain terrainType, Integer largeur) {
        Road road = new Road(size, size, true);
        Carte<TypeTerrain> carteRoute = changeCarteBoolenToTypeTerrainValue(road.getCarte(), terrainType);
        carte.putCarte(carteRoute, new Point(0, 0));

    }

    public Carte<TypeTerrain> generate() {
        return carte;
    }

    @Getter
    public enum TypeTerrain {
        MONTAGNE(8L),
        FORET(4L),
        PLAINE(2L),
        ROUTE(1L),
        WATER(30L);

        private Long mpCost;

        TypeTerrain(Long mpCost) {
            this.mpCost = mpCost;
        }

        @Override
        public String toString() {
            return this.name().substring(0, 1);
        }
    }

}
