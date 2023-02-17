package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistSongsResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlaylistSongsActivityProvider implements RequestHandler<GetPlaylistSongsRequest, GetPlaylistSongsResult> {

    private static ServiceComponent dagger;

    public GetPlaylistSongsActivityProvider() {

    }

    @Override
    public GetPlaylistSongsResult handleRequest(final GetPlaylistSongsRequest getPlaylistSongsRequest, Context context) {
        return getApp().provideGetPlaylistSongsActivity().handleRequest(getPlaylistSongsRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
