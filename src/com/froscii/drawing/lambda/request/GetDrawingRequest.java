package com.froscii.drawing.lambda.request;

import java.util.Objects;

public class GetDrawingRequest {
    private String name;
    private String text;

    public GetDrawingRequest() {}
    public GetDrawingRequest(String name, String text) {
        this.name = name;
        this.text = text;
    }
    public String getName() {
        return name;
    }
    public String getText() {
        return text;
    }
    public int getId() { return hashCode(); }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDrawingRequest that = (GetDrawingRequest) o;
        return that.name == this.name;
    }
    @Override
    public int hashCode() { return Objects.hash(name,text.charAt(text.length()/2)); }

    @Override
    public String toString() {
        return "GetDrawingRequest{" +
                "name='" + name + "'" +
                "text='" + text + "'";
    }
}
