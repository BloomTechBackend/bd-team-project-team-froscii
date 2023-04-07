package com.froscii.drawing.lambda.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.dependency.DaggerServiceComponent;
import com.froscii.drawing.dependency.ServiceComponent;
import com.froscii.drawing.lambda.request.GetCollectionRequest;
import com.froscii.drawing.lambda.result.GetCollectionResult;

public class GetCollectionProvider implements RequestHandler<GetCollectionRequest, GetCollectionResult> {

    private static ServiceComponent dagger;

    public GetCollectionProvider() {
    }

    @Override
    public GetCollectionResult handleRequest(final GetCollectionRequest getCollectionRequest, Context context) {
        return getApp().provideGetCollectionActivity().handleRequest(getCollectionRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
