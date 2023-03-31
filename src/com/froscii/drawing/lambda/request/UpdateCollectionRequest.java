package com.froscii.drawing.lambda.request;

import java.util.Objects;

public class UpdateCollectionRequest {

        private String collectionName;
        private int drawingId;

        public UpdateCollectionRequest() {}
        public UpdateCollectionRequest(String collectionName, int drawingId) {
            this.collectionName = collectionName;
            this.drawingId = drawingId;
        }
        public String getCollectionName() {
            return collectionName;
        }
        public int getDrawingId() {
            return drawingId;
        }
        @Override
        public boolean equals(Object o) {
            if(this==o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UpdateCollectionRequest that = (UpdateCollectionRequest) o;
            return that.collectionName == this.collectionName &&
                    that.drawingId == this.drawingId;
        }
        @Override
        public int hashCode() { return Objects.hash(collectionName,drawingId); }

        @Override
        public String toString() {
            return "GetDrawingRequest{" +
                    "collectinName='" + collectionName + "'" +
                    "drawingId='" + drawingId + "'";
        }
    }
