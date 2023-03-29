package com.froscii.drawing.Parts;

/**
 * Coordinate object that can also act as a node
 */
public class Coordinate {
    private int x;
    private int y;
    private Coordinate next;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX(){ return x;}
    public int getY(){ return y;}
    public void setNext(Coordinate next) {
        this.next = next;
    }
    public Coordinate getNext() {
        return next;
    }
}
