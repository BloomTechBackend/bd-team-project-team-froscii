package com.froscii.drawing.lambda.request;

import java.util.Objects;

public class CreateCollectionRequest {
    private String name;

    public CreateCollectionRequest() {}
    public CreateCollectionRequest(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCollectionRequest that = (CreateCollectionRequest) o;
        return that.name == this.name;
    }
    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public String toString() {
        return "CreateCollectionRequest{" +
                "name='" + name + "'}";
    }
}
