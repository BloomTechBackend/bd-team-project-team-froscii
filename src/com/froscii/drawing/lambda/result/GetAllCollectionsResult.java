package com.froscii.drawing.lambda.result;

import com.froscii.drawing.Models.CollectionModel;
import java.util.List;

public class GetAllCollectionsResult {
    private List<CollectionModel> collectionsModel;

    public GetAllCollectionsResult(Builder builder) {
        this.collectionsModel = builder.collectionsModel;
    }
    public List<CollectionModel> getCollection() {
        return collectionsModel;
    }
    public void setCollections(List<CollectionModel> collectionsModel) {
        this.collectionsModel = collectionsModel;
    }
    public static final class Builder {
        private List<CollectionModel> collectionsModel;

        public Builder withModel(List<CollectionModel> collectionsModel) {
            this.collectionsModel = collectionsModel;
            return this;
        }

        public GetAllCollectionsResult build() {
            return new GetAllCollectionsResult(this);
        }
    }

}
