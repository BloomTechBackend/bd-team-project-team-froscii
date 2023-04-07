package com.froscii.drawing.lambda.provider;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.dependency.DaggerServiceComponent;
import com.froscii.drawing.dependency.ServiceComponent;
import com.froscii.drawing.lambda.request.GetDrawingRequest;
import com.froscii.drawing.lambda.result.GetDrawingResult;

public class GetDrawingProvider implements RequestHandler<GetDrawingRequest, GetDrawingResult> {

    private static ServiceComponent dagger;

    public GetDrawingProvider() {
    }

    @Override
    public GetDrawingResult handleRequest(final GetDrawingRequest getDrawingRequest, Context context) {
        return getApp().provideGetDrawingActivity().handleRequest(getDrawingRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
