package com.froscii.drawing.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = "collections")
public class DrawingCollection {
    private String name;
    private List<Integer> drawingIds;

    public DrawingCollection(){
        drawingIds = new ArrayList<>();
    }
    public DrawingCollection(String name){
        this.name = name;
        // Check if name is in database already
        drawingIds = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }
    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }
    @DynamoDBAttribute(attributeName = "drawingIds")
    public List<Integer> getDrawingIds() {
        return drawingIds;
    }
    public void setDrawingIds(List<Integer> drawingIds) {
        this.drawingIds = drawingIds;
    }
    public void addDrawing(int drawingId) {drawingIds.add(drawingId); }
}