package com.froscii.drawing.dependency;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.froscii.drawing.lambda.activity.*;
import com.froscii.drawing.lambda.request.GetAllCollectionsRequest;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {
    CreateCollectionActivity provideCreateCollectionActivity();
    CreateDrawingActivity provideCreateDrawingActivity();
    GetDrawingActivity provideGetDrawingActivity();
    GetCollectionActivity provideGetCollectionActivity();
    UpdateCollectionActivity provideUpdateCollectionActivity();

    GetAllCollectionsActivity provideGetAllCollectionsActivity();
}
