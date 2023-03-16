package com.amazon.ata.froscii.drawing.service.Models;

import java.util.List;
import java.util.Objects;

public class DrawingCollectionModel {
    private String name;
    private List<Integer> drawingIds;

    public DrawingCollectionModel() {
    }

    public DrawingCollectionModel(Builder builder) {
        this.name = builder.name;
        this.drawingIds = builder.drawingIds;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Integer> getdrawingIds() {
        return drawingIds;
    }
    public void setDrawingIds(List<Integer> drawingIds) {
        this.drawingIds = drawingIds;
    }
    public void addDrawingId(Integer drawingId) {
        this.drawingIds.add(drawingId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawingCollectionModel other = (DrawingCollectionModel) o;
        return other.name.equals(name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "DrawingCollectionModel{" +
                "name='" + name + '\'' +
                drawingIds.toString() + '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;
        private List<Integer> drawingIds;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withDrawingIds(List<Integer> drawingIds) {
            this.drawingIds = drawingIds;
            return this;
        }
        public Builder withDrawingId(Integer drawingId) {
            this.drawingIds.add(drawingId);
            return this;
        }
        public DrawingCollectionModel build() {
            return new DrawingCollectionModel(this);
        }
    }
}