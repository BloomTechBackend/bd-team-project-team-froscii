package com.froscii.drawing.lambda.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.dependency.DaggerServiceComponent;
import com.froscii.drawing.dependency.ServiceComponent;
import com.froscii.drawing.lambda.request.CreateDrawingRequest;
import com.froscii.drawing.lambda.result.CreateDrawingResult;

public class CreateDrawingProvider implements RequestHandler<CreateDrawingRequest, CreateDrawingResult> {

    private static ServiceComponent dagger;

    public CreateDrawingProvider() {
    }

    @Override
    public CreateDrawingResult handleRequest(final CreateDrawingRequest createDrawingRequest, Context context) {
        return getApp().provideCreateDrawingActivity().handleRequest(createDrawingRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
