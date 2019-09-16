package com.advancewars.model.action;

import com.advancewars.model.carte.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    /**
     * Position de l'unité au debut de l'action
     */
    private Point posUnit;
    /**
     * Position apres le deplacement
     */
    private Point posUnitAfterDeplacement;
    /**
     * Position de l'unité attaqué
     */
    private Point posCible;

}
