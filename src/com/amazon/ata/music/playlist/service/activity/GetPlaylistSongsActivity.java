package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistSongsResult;
import com.amazon.ata.music.playlist.service.models.SongModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.mustache.Model;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the GetPlaylistSongsActivity for the MusicPlaylistService's GetPlaylistSongs API.
 *
 * This API allows the customer to get the list of songs of a saved playlist.
 */
public class GetPlaylistSongsActivity implements RequestHandler<GetPlaylistSongsRequest, GetPlaylistSongsResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new GetPlaylistSongsActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */
    @Inject
    public GetPlaylistSongsActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }
    /**
     * For use by AWS Lambda, which requires a zero-argument constructor.
     */
    //public GetPlaylistSongsActivity() {
    //    this.playlistDao = new PlaylistDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    //}

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist's song list.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistSongsRequest request object containing the playlist ID
     * @return getPlaylistSongsResult result object containing the playlist's list of API defined {@link SongModel}s
     */
    @Override
    public GetPlaylistSongsResult handleRequest(final GetPlaylistSongsRequest getPlaylistSongsRequest, Context context) {
        // I don't know what Context is or what it does. Shame.
        return handleRequest(getPlaylistSongsRequest);
    }
    public GetPlaylistSongsResult handleRequest(final GetPlaylistSongsRequest getPlaylistSongsRequest) {
        log.info("Received GetPlaylistSongsRequest {}", getPlaylistSongsRequest);
        String id = getPlaylistSongsRequest.getId();
        List<SongModel> songModelList = new ModelConverter()
                .toSongModelList(playlistDao.getPlaylist(id)
                        .getSongs(getPlaylistSongsRequest
                                .getOrder()));

        return GetPlaylistSongsResult.builder()
                .withSongList(songModelList)
                .build();
    }
}
