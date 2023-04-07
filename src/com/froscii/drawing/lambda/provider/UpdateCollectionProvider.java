package com.froscii.drawing.lambda.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.dependency.DaggerServiceComponent;
import com.froscii.drawing.dependency.ServiceComponent;
import com.froscii.drawing.lambda.request.UpdateCollectionRequest;
import com.froscii.drawing.lambda.result.UpdateCollectionResult;

public class UpdateCollectionProvider implements RequestHandler<UpdateCollectionRequest, UpdateCollectionResult> {

    private static ServiceComponent dagger;

    public UpdateCollectionProvider() {
    }

    @Override
    public UpdateCollectionResult handleRequest(final UpdateCollectionRequest updateCollectionRequest, Context context) {
        return getApp().provideUpdateCollectionActivity().handleRequest(updateCollectionRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
