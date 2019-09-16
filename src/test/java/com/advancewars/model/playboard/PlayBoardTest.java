package com.advancewars.model.playboard;

import com.advancewars.model.action.Action;
import com.advancewars.model.carte.GenerateurDeCarte;
import com.advancewars.model.carte.Point;
import com.advancewars.model.exception.NotPermitedMove;
import com.advancewars.model.exception.NotPermittedAttack;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayBoardTest {
    @Test
    public void applyAnAction() {
        PlayBoard playBoard = new PlayBoard(new GenerateurDeCarte(10).generate());
        Unit unit = new Unit(10L, 10L, 5L, 6L, 2L);
        Unit unitCible = new Unit(10L, 10L, 5L, 6L, 2L);

        Point positionUnit = new Point(1, 1);
        Point positionAfterMouv = new Point(2, 2);
        Point positionCible = new Point(3, 3);

        playBoard.addUnit(positionUnit, unit);
        playBoard.addUnit(positionCible, unitCible);

        Action action = new Action(positionUnit, positionAfterMouv, positionCible);
        playBoard.applyAction(action);

        assertThat(playBoard.getUnitPlacement().getValue(positionUnit)).isNull();
        assertThat(playBoard.getUnitPlacement().getValue(positionAfterMouv)).isNotNull().isEqualTo(unit);
        assertThat(playBoard.getUnitPlacement().getValue(positionCible)).isNotNull().isEqualTo(unitCible);
        assertThat(playBoard.getUnitPlacement().getValue(positionCible).getPv()).isNotNull().isEqualTo(9L);

    }

    @Test
    public void dontApplyIncorrectAttack() {
        PlayBoard playBoard = new PlayBoard(new GenerateurDeCarte(100).generate());
        Unit unit = new Unit(10L, 20L, 5L, 6L, 2L);
        Unit unitCible = new Unit(10L, 20L, 5L, 6L, 2L);

        Point positionUnit = new Point(0, 0);
        Point positionAfterMouv = new Point(8, 0);
        Point positionCible = new Point(20, 0);


        playBoard.addUnit(positionUnit, unit);
        playBoard.addUnit(positionCible, unitCible);

        Action action = new Action(positionUnit, positionAfterMouv, positionCible);

        assertThatThrownBy(() -> playBoard.applyAction(action)).isInstanceOf(NotPermittedAttack.class);

    }

    @Test
    public void dontApplyIncorrectMove() {
        PlayBoard playBoard = new PlayBoard(new GenerateurDeCarte(100).generate());
        Unit unit = new Unit(10L, 10L, 5L, 6L, 2L);
        Unit unitCible = new Unit(10L, 10L, 5L, 6L, 2L);

        Point positionUnit = new Point(0, 0);
        Point positionAfterMouv = new Point(11, 0);
        Point positionCible = new Point(12, 0);

        playBoard.addUnit(positionUnit, unit);
        playBoard.addUnit(positionCible, unitCible);

        Action action = new Action(positionUnit, positionAfterMouv, positionCible);

        assertThatThrownBy(() -> playBoard.applyAction(action)).isInstanceOf(NotPermitedMove.class);
    }

    @Test
    public void applyLimiteMove() {
        PlayBoard playBoard = new PlayBoard(new GenerateurDeCarte(100).generate());
        Unit unit = new Unit(10L, 10L, 5L, 6L, 2L);
        Unit unitCible = new Unit(10L, 10L, 5L, 6L, 2L);

        Point positionUnit = new Point(0, 0);
        Point positionAfterMouv = new Point(5, 0);
        Point positionCible = new Point(6, 0);

        playBoard.addUnit(positionUnit, unit);
        playBoard.addUnit(positionCible, unitCible);

        Action action = new Action(positionUnit, positionAfterMouv, positionCible);

    }

}
