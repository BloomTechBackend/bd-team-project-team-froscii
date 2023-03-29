package com.froscii.drawing.Parts;

public class Directions {
    private Coordinate current;
    public static final int LENGTH = 8;
    public static final int[][] dirArray = new int[][] {new int[] {-1, 1}, new int[] {0,1},
                            new int[] {1,1}, new int[] {1,0},  new int[] {1,-1},
                            new int[] {0,-1},new int[] {-1,-1},new int[] {-1,0}};
    public Directions() {
        current = new Coordinate(-1,1);
        Coordinate start = current;
        int[][] sevenDirArray = new int[][] {new int[] {0,1},
                             new int[] {1,1}, new int[] {1,0},  new int[] {1,-1},
                             new int[] {0,-1},new int[] {-1,-1},new int[] {-1,0}};
        for (int[] xy : sevenDirArray) {
            Coordinate coordinate = new Coordinate(xy[0],xy[1]);
            current.setNext(coordinate);
            current = coordinate;
        }
        // Set the last to loop to the first.
        current.setNext(start);
        current = start;
    }
    public Coordinate next() {
        current = current.getNext();
        return current;
    }
    public Coordinate getCurrent(){
        return current;
    }
}
