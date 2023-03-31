package com.froscii.drawing.dependency;

import com.froscii.drawing.activity.*;
import com.froscii.drawing.lambda.activity.*;
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
}
