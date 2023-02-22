package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.requests.UpdatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.UpdatePlaylistResult;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.util.MusicPlaylistServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdatePlaylistActivity for the MusicPlaylistService's UpdatePlaylist API.
 *
 * This API allows the customer to update their saved playlist's information.
 */
public class UpdatePlaylistActivity implements RequestHandler<UpdatePlaylistRequest, UpdatePlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new UpdatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */
    @Inject
    public UpdatePlaylistActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist, updating it,
     * and persisting the playlist.
     * <p>
     * It then returns the updated playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the customer ID,
     * this should throw an InvalidAttributeChangeException
     *
     * @param updatePlaylistRequest request object containing the playlist ID, playlist name, and customer ID
     *                              associated with it
     * @return updatePlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    @Override
    public UpdatePlaylistResult handleRequest(final UpdatePlaylistRequest updatePlaylistRequest, Context context) {
        log.info("Received UpdatePlaylistRequest {}", updatePlaylistRequest);
        if (!MusicPlaylistServiceUtils.isValidString(updatePlaylistRequest.getName())) {
            throw new InvalidAttributeValueException("The given name is not a valid name: " + updatePlaylistRequest.getName());
        }
        Playlist playlist = playlistDao.getPlaylist(updatePlaylistRequest.getId());
        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);

        if (!updatePlaylistRequest.getCustomerId().equals(playlist.getCustomerId())) {
            throw new InvalidAttributeChangeException("The given customerIds do not match: " + updatePlaylistRequest.getCustomerId() + " " + playlist.getCustomerId());
        }
        // Name is the only attribute that can be changed.
        playlistModel.setName(updatePlaylistRequest.getName());

        UpdatePlaylistResult result = UpdatePlaylistResult.builder()
                                    .withPlaylist(playlistModel)
                                    .build();
        playlistDao.savePlaylist(new ModelConverter().toPlaylist(playlistModel));
        return result;
    }
}
