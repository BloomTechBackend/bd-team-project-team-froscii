package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.util.MusicPlaylistServiceUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 *
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreatePlaylistActivity implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;
    private boolean isLambda;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlists table.
     */
    public CreatePlaylistActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
        this.isLambda = false;
    }

    /**
     * For use by AWS Lambda, which requires a zero-argument constructor. I need some help here.
     */
    public CreatePlaylistActivity() {
        this.playlistDao = new PlaylistDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
        this.isLambda = true;
    }

    /**
     * This method handles the incoming request by persisting a new playlist
     * with the provided playlist name and customer ID from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param createPlaylistRequest request object containing the playlist name and customer ID
     *                              associated with it
     * @return createPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    @Override
    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest, Context context) {
        log.info("Received CreatePlaylistRequest {}", createPlaylistRequest);
        String name = createPlaylistRequest.getName();
        if (!MusicPlaylistServiceUtils.isValidString(name)) {
            throw new InvalidAttributeChangeException("The given playlist name does not match our syntax rules.");
        }
        String id = MusicPlaylistServiceUtils.generatePlaylistId();
        PlaylistModel playlistModel = new PlaylistModel.Builder()
                .withTags(createPlaylistRequest.getTags())
                .withName(name)
                .withCustomerId(createPlaylistRequest.getCustomerId())
                .withSongCount(0)
                .withId(id)
                .build();
        CreatePlaylistResult result = CreatePlaylistResult.builder()
                .withPlaylist(playlistModel)
                .build();
        // Convert the PlaylistModel into a Playlist
        // Added to work with AWS Lambda. Again, I need help. A bit.
        if (isLambda) {
            playlistDao.savePlaylist(new ModelConverter().toPlaylist(playlistModel));
        }
        return result;
    }
}
