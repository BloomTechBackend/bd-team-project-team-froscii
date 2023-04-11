package com.froscii.drawing.lambda.request;

import java.util.Objects;

public class UpdateCollectionRequest {

    private String collectionName;
    private String drawingName;

    public UpdateCollectionRequest() {
    }

    public UpdateCollectionRequest(String collectionName, String drawingName) {
        this.collectionName = collectionName;
        this.drawingName = drawingName;
    }

    public UpdateCollectionRequest(Builder b) {
        this.collectionName = b.name;
        this.drawingName = b.drawingName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String name) {
        this.collectionName = name;
    }

    public String getDrawingName() {
        return drawingName;
    }

    public void setDrawingName(String name) {
        this.drawingName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCollectionRequest that = (UpdateCollectionRequest) o;
        return that.collectionName.equals(this.collectionName) &&
                that.drawingName.equals(this.drawingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionName, drawingName);
    }

    @Override
    public String toString() {
        return "GetDrawingRequest{" +
                "collectionName='" + collectionName + "'" +
                "drawingName='" + drawingName + "'";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String drawingName;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDrawingName(String name) {
            this.drawingName = name;
            return this;
        }

        public UpdateCollectionRequest build() {
            return new UpdateCollectionRequest(this);
        }
    }
}
