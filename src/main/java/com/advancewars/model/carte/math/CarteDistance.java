package com.advancewars.model.carte.math;

import com.advancewars.model.carte.Carte;
import com.advancewars.model.carte.GenerateurDeCarte;
import com.advancewars.model.carte.OneToMany;
import com.advancewars.model.carte.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CarteDistance {
    private Carte<GenerateurDeCarte.TypeTerrain> carteTerrain;
    private Point pointOrigine;
    private Carte<Long> carteDistance;
    private OneToMany<Long, Point> mapForPointNotPlaced;

    public CarteDistance(Carte<GenerateurDeCarte.TypeTerrain> carteTerrain, Point pointOrigine) {
        this.carteTerrain = carteTerrain;
        this.pointOrigine = pointOrigine;
        initCarte();
    }

    public Long getDistance(Point cible) {
        return carteDistance.getValue(cible);
    }

    private void initCarte() {
        this.carteDistance = new Carte<Long>(carteTerrain.getXSize(), carteTerrain.getYSize(), null);
        carteDistance.setValue(pointOrigine, 0L);
        this.mapForPointNotPlaced = new OneToMany<>();
        addPointAroundInWaitingQueue(pointOrigine);
        startPlaceIntermediate();

    }

    private void addPointAroundInWaitingQueue(Point pointOrigine) {
        List<Point> points = getAllAround(pointOrigine);
        Long currentDistance = getDistance(pointOrigine);
        points.stream()
                .filter(point -> carteDistance.pointIsIn(point))
                .filter(point -> getDistance(point) == null && newDistanceIsBetter(point, currentDistance))
                .forEach(point -> setNewDistance(point, currentDistance));
    }

    private void startPlaceIntermediate() {
        Long distance = 1L;
        while (mapForPointNotPlaced.containsValue()) {
            placeAllPointAtDistance(distance);
            distance++;
        }
    }

    private void placeAllPointAtDistance(Long distance) {
        List<Point> list = mapForPointNotPlaced.getValues(distance);
        while (list.size() > 0) {
            {
                Point point = list.get(0);
                carteDistance.setValue(point, distance);
                mapForPointNotPlaced.remove(distance, point);
                addPointAroundInWaitingQueue(point);
            }

        }
    }

    private void setNewDistance(Point point, Long currentDistance) {

        mapForPointNotPlaced.put(newDistance(point, currentDistance), point);
    }

    private boolean newDistanceIsBetter(Point point, Long currentDistance) {
        Long distanceIntermediaire = getIntermediateDistance(point);
        Long newDistance = newDistance(point, currentDistance);
        return distanceIntermediaire == null || distanceIntermediaire > newDistance;
    }

    private long newDistance(Point point, Long currentDistance) {
        return currentDistance + carteTerrain.getValue(point).getMpCost();
    }

    private Long getIntermediateDistance(Point point) {
        return mapForPointNotPlaced.getKeys(point);
    }

    private List<Point> getAllAround(Point pointOrigine) {
        return Arrays.asList(
                new Point(0, -1),
                new Point(-1, 0),
                new Point(1, 0),
                new Point(0, 1)).stream().map(pointOrigine::getPointAfterMouvement).collect(Collectors.toList());
    }
}
