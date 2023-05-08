package com.froscii.drawing.lambda.request;


public class GetAllCollectionsRequest {

    public GetAllCollectionsRequest() {}
    public GetAllCollectionsRequest(Builder b) {}
    @Override
    public String toString() {
        return "GetAllCollectionsRequest{}";
    }
    public static Builder builder() {return new Builder();}

    public static final class Builder {

        private Builder() {}
        public GetAllCollectionsRequest build() {return new GetAllCollectionsRequest(this);}
    }
}
