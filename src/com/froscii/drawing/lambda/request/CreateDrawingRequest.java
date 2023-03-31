package com.froscii.drawing.lambda.request;

import com.froscii.drawing.dynamodb.Drawing;

import java.util.Arrays;
import java.util.Objects;

public class CreateDrawingRequest {
    private String name;
    private String text;

    public CreateDrawingRequest() {}
    public CreateDrawingRequest(String name, String text) {
        this.name = name;
        this.text = text;
    }
    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDrawingRequest that = (CreateDrawingRequest) o;
        return that.name == this.name;
    }
    @Override
    public int hashCode() { return Objects.hash(name,text.charAt(text.length()/2)); }

    @Override
    public String toString() {
        return "CreateDrawingRequest{" +
                "name='" + name + "'" +
                "text='" + text + "'";
    }
}
