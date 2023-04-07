package com.froscii.drawing.lambda.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.dependency.DaggerServiceComponent;
import com.froscii.drawing.dependency.ServiceComponent;
import com.froscii.drawing.lambda.request.CreateCollectionRequest;
import com.froscii.drawing.lambda.result.CreateCollectionResult;

public class CreateCollectionProvider implements RequestHandler<CreateCollectionRequest, CreateCollectionResult> {

    private static ServiceComponent dagger;

    public CreateCollectionProvider() {
    }

    @Override
    public CreateCollectionResult handleRequest(final CreateCollectionRequest createCollectionRequest, Context context) {
        return getApp().provideCreateCollectionActivity().handleRequest(createCollectionRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
