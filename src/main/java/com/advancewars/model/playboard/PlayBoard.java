package com.advancewars.model.playboard;

import com.advancewars.model.action.Action;
import com.advancewars.model.carte.Carte;
import com.advancewars.model.carte.GenerateurDeCarte;
import com.advancewars.model.carte.Point;
import com.advancewars.model.carte.math.CarteDistance;
import com.advancewars.model.exception.NotPermitedMove;
import com.advancewars.model.exception.NotPermittedAttack;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
@RedisHash("Playboard")
public class PlayBoard {
    @Id
    private String id;
    private CarteUnit unitPlacement;
    private Carte<GenerateurDeCarte.TypeTerrain> carte;

    public PlayBoard(Carte<GenerateurDeCarte.TypeTerrain> carte) {
        this.carte = carte;
        this.unitPlacement = new CarteUnit(carte.getXSize(), carte.getYSize(), null);
    }

    public void addUnit(Point position, Unit unit) {
        this.unitPlacement.setValue(position, unit);
    }

    public void applyAction(Action action) {
        deplaceUnit(action.getPosUnit(), action.getPosUnitAfterDeplacement());
        applyAttack(action.getPosUnitAfterDeplacement(), action.getPosCible());
    }

    /**
     * applique l'attaque
     *
     * @param posUnitAfterDeplacement
     * @param posCible
     */
    private void applyAttack(Point posUnitAfterDeplacement, Point posCible) {
        Unit unit = unitPlacement.getValue(posUnitAfterDeplacement);
        if (inRangeAttack(unit, posUnitAfterDeplacement, posCible)) {
            Unit unitCible = unitPlacement.getValue(posCible);
            unitCible.setPv(calculDegat(unit, unitCible));

        } else {
            throw new NotPermittedAttack();
        }
    }

    private boolean inRangeAttack(Unit unit, Point posUnitAfterDeplacement, Point posCible) {
        return unit.getRange() >= posUnitAfterDeplacement.getDistance(posCible);
    }

    private long calculDegat(Unit unit, Unit unitCible) {
        return unitCible.getPv() - (unit.getAtk() - unitCible.getDef());
    }


    private void deplaceUnit(Point posUnit, Point posAfterMouv) {
        if (inRange(posUnit, posAfterMouv) && isValide(posAfterMouv)) {
            Unit unit = unitPlacement.getValue(posUnit);
            unitPlacement.setValue(posAfterMouv, unit);
            unitPlacement.setValue(posUnit, null);
        } else {
            throw new NotPermitedMove();
        }
    }

    /**
     * Verifie si la position cible peu accueillir une unité
     *
     * @param posCible
     * @return
     */
    private boolean isValide(Point posCible) {

        return getUnitPlacement().getValue(posCible) == null;
    }

    /**
     * Verifie si la position de cible et dans le portée de l'unité
     *
     * @param posUnit
     * @param posCible
     * @return
     */
    private boolean inRange(Point posUnit, Point posCible) {

        CarteDistance carteDistance = new CarteDistance(getCarte(), posUnit);
        Long distanceMouv = carteDistance.getDistance(posCible);
        Unit unit = getUnitPlacement().getValue(posUnit);
        return unit.getPm() >= distanceMouv;
    }

}
