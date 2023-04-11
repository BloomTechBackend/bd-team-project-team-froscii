package com.froscii.drawing.lambda.request;

import java.util.Objects;

public class GetCollectionRequest {
    private String name;

    public GetCollectionRequest() {}
    public GetCollectionRequest(String name) {
        this.name = name;
    }
    public GetCollectionRequest(Builder b) {
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
        GetCollectionRequest that = (GetCollectionRequest) o;
        return that.name == this.name;
    }
    @Override
    public int hashCode() { return Objects.hash(name); }

    @Override
    public String toString() {
        return "GetCollectionRequest{" +
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
        public GetCollectionRequest build() {return new GetCollectionRequest(this);}
    }
}
