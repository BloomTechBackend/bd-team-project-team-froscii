package com.amazon.ata.froscii.drawing.service.Models;

import java.util.Arrays;

public class DrawingModel {
    private String name;
    private int id;
    private char[][] text;
    private int width;

    public DrawingModel() {
    }

    public DrawingModel(Builder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.text = builder.text;
        this.width = builder.width;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public char[][] getText(){
        return this.text;
    }
    public void setText(char[][] text) {
        this.text = text;
    }
    public Integer getWidth(){
        return this.width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawingModel other = (DrawingModel) o;
        return other.id == id;
    }
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "DrawingModel{" +
                "name='" + name + '\'' +
                "id='" + Integer.toString(id) + '\'' +
                "width='" + Integer.toString(width) + '\'' +
                "text='" + Arrays.deepToString(text) + "'}";
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;
        private int id;
        private int width;
        private char[][] text;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withId(int drawingIds) {
            this.id = id;
            return this;
        }
        public Builder withWidth(int width) {
            this.width = width;
            return this;
        }
        public Builder withText(char[][] text) {
            this.text = text;
            return this;
        }
        public DrawingModel build() {
            return new DrawingModel(this);
        }
    }
}
