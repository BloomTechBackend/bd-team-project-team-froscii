package com.froscii.drawing.Models;

import com.froscii.drawing.dynamodb.Drawing;

/**
 * Stores the drawing text in String form.
 */
public class DrawingModel {
    private String name;
    private String text;

    public DrawingModel() {
    }

    public DrawingModel(Builder builder) {
        this.name = builder.name;
        this.text = builder.text;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getText(){
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawingModel other = (DrawingModel) o;
        return other.text == text;
    }
    @Override
    public int hashCode() {
        return Drawing.hashCode(name,text);
    }

    @Override
    public String toString() {
        return "DrawingModel{" +
                "name='" + name + '\'' +
                "text='" + text + "'}";
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;
        private String text;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withText(String text) {
            this.text = text;
            return this;
        }
        public DrawingModel build() {
            return new DrawingModel(this);
        }
    }
}
