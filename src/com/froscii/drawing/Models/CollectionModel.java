package com.froscii.drawing.Models;

import java.util.List;
import java.util.Objects;

public class CollectionModel {
    private String name;
    private List<String> drawingNames;

    public CollectionModel() {
    }

    public CollectionModel(Builder builder) {
        this.name = builder.name;
        this.drawingNames = builder.drawingNames;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getDrawingNames() {
        return drawingNames;
    }
    public void setDrawingIds(List<String> drawingNames) {
        this.drawingNames = drawingNames;
    }
    public void addDrawingId(String drawingName) {
        this.drawingNames.add(drawingName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionModel other = (CollectionModel) o;
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
                drawingNames.toString() + '}';
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;
        private List<String> drawingNames;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withDrawingNames(List<String> drawingNames) {
            this.drawingNames = drawingNames;
            return this;
        }
        public Builder withDrawingName(String drawingName) {
            this.drawingNames.add(drawingName);
            return this;
        }
        public CollectionModel build() {
            return new CollectionModel(this);
        }
    }
}
