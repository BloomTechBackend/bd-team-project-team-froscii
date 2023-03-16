package com.amazon.ata.froscii.drawing.service.Models;

import com.amazon.ata.froscii.drawing.service.dynamodb.Drawing;
import com.amazon.ata.froscii.drawing.service.dynamodb.DrawingCollection;

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

    public DrawingCollection toDrawingCollection(DrawingCollectionModel model) {
        DrawingCollection collection = new DrawingCollection();
        collection.setName(model.getName());
        collection.setDrawingIds(model.getdrawingIds());
        return collection;
    }
    public DrawingCollectionModel toDrawingCollectionModel(DrawingCollection collection) {
        return DrawingCollectionModel.builder()
                .withName(collection.getName())
                .withDrawingIds(collection.getDrawingIds())
                .build();
    }
}
