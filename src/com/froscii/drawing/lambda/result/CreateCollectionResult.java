package com.froscii.drawing.lambda.result;

import com.froscii.drawing.Models.CollectionModel;

public class CreateCollectionResult {
    private CollectionModel collectionModel;

    public CreateCollectionResult(Builder builder) {
        this.collectionModel = builder.collectionModel;
    }
    public CollectionModel getCollection() {
        return collectionModel;
    }
    public void setCollection(CollectionModel collection) {
        this.collectionModel = collection;
    }
    public static final class Builder {
        private CollectionModel collectionModel;

        public Builder withModel(CollectionModel collectionModel) {
            this.collectionModel = collectionModel;
            return this;
        }

        public CreateCollectionResult build() {
            return new CreateCollectionResult(this);
        }
    }

}
