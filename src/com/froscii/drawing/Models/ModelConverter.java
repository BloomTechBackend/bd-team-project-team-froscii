package com.froscii.drawing.Models;

import com.froscii.drawing.dynamodb.Drawing;
import com.froscii.drawing.dynamodb.DrawingCollection;

public class ModelConverter {
    public Drawing toDrawing(DrawingModel model) {
        Drawing drawing = new Drawing();
        drawing.setName(model.getName());
        drawing.setText(model.getText());
        return drawing;
    }
    public DrawingModel toDrawingModel(Drawing drawing) {
        return DrawingModel.builder()
                .withName(drawing.getName())
                .withText(drawing.getText())
                .build();
    }

    public DrawingCollection toDrawingCollection(CollectionModel model) {
        DrawingCollection collection = new DrawingCollection();
        collection.setName(model.getName());
        collection.setDrawingNames(model.getDrawingNames());
        return collection;
    }
    public CollectionModel toDrawingCollectionModel(DrawingCollection collection) {
        return CollectionModel.builder()
                .withName(collection.getName())
                .withDrawingNames(collection.getDrawingNames())
                .build();
    }
}
