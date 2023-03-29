package com.froscii.drawing.Parts;

public class Line implements Comparable<Line>{
    private Coordinate pointA;
    private Coordinate pointB;
    public Line(Coordinate a, Coordinate b) {
       pointA = a;
       pointB = b;
    }
    public int aX() {
        return pointA.getX();
    }
    public int aY() {
        return pointA.getY();
    }
    public int bX() {
        return pointB.getX();
    }
    public int bY() {
        return pointB.getY();
    }
    public Coordinate getA(){
        return pointA;
    }
    public void setA(Coordinate a) {
        pointA = a;
    }
    public Coordinate getB(){
        return pointB;
    }
    public void setB(Coordinate b) {
        pointB = b;
    }
    @Override
    public int compareTo(Line otherline) {
        if (this.aX() > otherline.aX()) {
            return 2;
        }
        if (this.aX() == otherline.aX()) {
            if (this.bX() > otherline.bX()) {
                return 1;
            }
            if (this.bX() < otherline.bX()) {
                return -1;
            }
            return 0;
        }
        return -2;
    }
}