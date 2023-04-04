package com.froscii.drawing.lambda.result;

import com.froscii.drawing.Models.DrawingModel;

public class CreateDrawingResult {
    private DrawingModel drawingModel;
    public CreateDrawingResult(Builder builder) {
        this.drawingModel = builder.drawingModel;
    }
    public DrawingModel getDrawing() {
        return drawingModel;
    }
    public void setDrawing(DrawingModel drawing) {
        drawingModel = drawing;
    }
    public static final class Builder {
        private DrawingModel drawingModel;

        public Builder withModel(DrawingModel drawingModel) {
            this.drawingModel = drawingModel;
            return this;
        }

        public CreateDrawingResult build() {
            return new CreateDrawingResult(this);
        }
    }

}
