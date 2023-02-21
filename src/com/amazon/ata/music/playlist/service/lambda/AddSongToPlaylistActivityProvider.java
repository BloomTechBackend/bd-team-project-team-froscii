package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.AddSongToPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.AddSongToPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddSongToPlaylistActivityProvider implements RequestHandler<AddSongToPlaylistRequest, AddSongToPlaylistResult> {

    private static ServiceComponent dagger;

    public AddSongToPlaylistActivityProvider() {}

    @Override
    public AddSongToPlaylistResult handleRequest(final AddSongToPlaylistRequest addSongToPlaylistRequest, Context context) {
        return getApp().provideAddSongToPlaylistActivity().handleRequest(addSongToPlaylistRequest, context);
    }

    private ServiceComponent getApp() {
        if (dagger == null) {
            dagger = DaggerServiceComponent.create();
        }
        return dagger;
    }
}
