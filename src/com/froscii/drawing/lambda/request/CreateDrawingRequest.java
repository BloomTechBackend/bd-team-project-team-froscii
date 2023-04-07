package com.froscii.drawing.lambda.request;

import com.froscii.drawing.dynamodb.Drawing;

import java.util.Arrays;
import java.util.Objects;

/**
 * This creates a drawing and also stores it in the home collection,
 * meaning that this will also call the updateCollectionRequest
 */
public class CreateDrawingRequest {
    private String name;
    private String text;

    public CreateDrawingRequest() {}
    public CreateDrawingRequest(String name, String text) {
        this.name = name;
        this.text = text;
    }
    public CreateDrawingRequest(Builder builder) {
        this.name = builder.name;
        this.text = builder.text;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getText() { return text; }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDrawingRequest that = (CreateDrawingRequest) o;
        return that.name == this.name;
    }
    @Override
    public int hashCode() { return Objects.hash(name,text.charAt(text.length()/2),text.length()); }

    @Override
    public String toString() {
        return "CreateDrawingRequest{" +
                "name='" + name + "'" +
                "text='" + text + "'}";
    }
    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;
        private String text;

        private Builder() {}

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withText(String name) {
            this.text = text;
            return this;
        }
        public CreateDrawingRequest build() {return new CreateDrawingRequest(this);}
    }
}
