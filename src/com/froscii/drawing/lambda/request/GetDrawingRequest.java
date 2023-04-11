package com.froscii.drawing.lambda.request;

import java.util.Objects;

public class GetDrawingRequest {
    private String name;

    public GetDrawingRequest() {}
    public GetDrawingRequest(String name) {
        this.name = name;
    }
    public GetDrawingRequest(Builder b) {
        this.name = b.name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDrawingRequest that = (GetDrawingRequest) o;
        return that.name == this.name;
    }
    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public String toString() {
        return "GetDrawingRequest{" +
                "name='" + name + "'}";
    }
    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String name;

        private Builder() {}

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public GetDrawingRequest build() {return new GetDrawingRequest(this);}
    }
}
