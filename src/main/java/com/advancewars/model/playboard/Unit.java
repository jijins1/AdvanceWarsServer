package com.advancewars.model.playboard;

import lombok.*;


@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Unit {
    private Long pv;

    private Long pm;
    private Long def;
    private Long atk;
    private Long range;

    public Unit(Long pv, Long pm, Long def, Long atk, Long range) {
        this.pv = pv;
        this.pm = pm;
        this.def = def;
        this.atk = atk;
        this.range = range;
    }
}
