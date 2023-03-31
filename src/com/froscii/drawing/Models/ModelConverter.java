package com.froscii.drawing.Models;

import com.froscii.drawing.dynamodb.Drawing;
import com.froscii.drawing.dynamodb.DrawingCollection;

public class ModelConverter {
    public Drawing toDrawing(DrawingModel model) {
        Drawing drawing = new Drawing();
        drawing.setName(model.getName());
        drawing.setWidth(model.getWidth());
        drawing.setText(model.getText());
        return drawing;
    }
    public DrawingModel toDrawingModel(Drawing drawing) {
        return DrawingModel.builder()
                .withId(drawing.getId())
                .withName(drawing.getName())
                .withWidth(drawing.getWidth())
                .withText(drawing.getTextArray())
                .build();
    }

    public DrawingCollection toDrawingCollection(CollectionModel model) {
        DrawingCollection collection = new DrawingCollection();
        collection.setName(model.getName());
        collection.setDrawingIds(model.getdrawingIds());
        return collection;
    }
    public CollectionModel toDrawingCollectionModel(DrawingCollection collection) {
        return CollectionModel.builder()
                .withName(collection.getName())
                .withDrawingIds(collection.getDrawingIds())
                .build();
    }
}
