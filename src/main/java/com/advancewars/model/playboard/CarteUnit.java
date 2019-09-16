package com.advancewars.model.playboard;

import com.advancewars.model.carte.Carte;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CarteUnit extends Carte<Unit> {
    public CarteUnit(Integer largeur, Integer hauteur, Unit o) {
        super(largeur, hauteur, o);
    }
}
