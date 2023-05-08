package com.froscii.drawing.lambda.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.dependency.DaggerServiceComponent;
import com.froscii.drawing.dependency.ServiceComponent;
import com.froscii.drawing.lambda.request.GetAllCollectionsRequest;
import com.froscii.drawing.lambda.request.GetCollectionRequest;
import com.froscii.drawing.lambda.result.GetAllCollectionsResult;
import com.froscii.drawing.lambda.result.GetCollectionResult;

public class GetAllCollectionsProvider implements RequestHandler<GetAllCollectionsRequest, GetAllCollectionsResult> {

    private static ServiceComponent dagger;

    public GetAllCollectionsProvider() {
    }

    @Override
    public GetAllCollectionsResult handleRequest(final GetAllCollectionsRequest request, Context context) {
        return getApp().provideGetAllCollectionsActivity().handleRequest(request, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
