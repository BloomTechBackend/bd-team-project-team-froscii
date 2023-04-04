package com.froscii.drawing.lambda.result;

import com.froscii.drawing.Models.CollectionModel;

public class GetCollectionResult {
    private CollectionModel collectionModel;

    public GetCollectionResult(Builder builder) {
        this.collectionModel = builder.collectionModel;
    }
    public CollectionModel getCollection() {
        return collectionModel;
    }
    public void setCollection(CollectionModel collectionModel) {
        this.collectionModel = collectionModel;
    }
    public static final class Builder {
        private CollectionModel collectionModel;

        public Builder withModel(CollectionModel collectionModel) {
            this.collectionModel = collectionModel;
            return this;
        }

        public GetCollectionResult build() {
            return new GetCollectionResult(this);
        }
    }

}
