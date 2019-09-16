package com.advancewars.model.carte;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Point {
    private Integer x;
    private Integer Y;

    public Point getPointAfterMouvement(Integer x, Integer y) {
        return new Point(this.getX() + x, this.getY() + y);
    }

    public Point getPointAfterMouvement(Point point) {
        return getPointAfterMouvement(point.getX(), point.getY());
    }

    public Integer getDistance(Point point) {
        Integer xDistance = Math.abs(getX() - point.getX());
        Integer yDistance = Math.abs(getY() - point.getY());
        return xDistance + yDistance;
    }

}
