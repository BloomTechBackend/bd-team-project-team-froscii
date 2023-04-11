package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = "collections")
public class DrawingCollection {
    private String name;
    private List<String> drawingNames;

    public DrawingCollection(){
        drawingNames = new ArrayList<>();
    }
    public DrawingCollection(String name){
        this.name = name;
        drawingNames = new ArrayList<>();
    }
    public DrawingCollection(String name, List<String> drawingNames) {
        this.name = name;
        this.drawingNames = drawingNames;
    }

    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @DynamoDBAttribute(attributeName = "drawing_names")
    public List<String> getDrawingNames() {
        return drawingNames;
    }
    public void setDrawingNames(List<String> drawingNames) {
        this.drawingNames = drawingNames;
    }
    public void addDrawing(String name) {drawingNames.add(name); }
    public void addDrawing(Drawing drawing) {drawingNames.add(drawing.getName()); }
}