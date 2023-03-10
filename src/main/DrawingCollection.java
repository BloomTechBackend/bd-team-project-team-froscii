package main;

import java.util.ArrayList;
import java.util.List;

public class DrawingCollection {
    private String name;
    private List<Integer> drawingIds;

    public DrawingCollection(String name){
        this.name = name;
        // Check if name is in database already
        drawingIds = new ArrayList<>();
    }

    public void addDrawing(Drawing drawing) {
        drawingIds.add(drawing.getId());
    }
}