package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreatePlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;
    private CreatePlaylistActivity createPlaylistActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_goodRequest_createsPlaylist() {
        // GIVEN
        String expectedCustomerId = "expectedCustomerId";
        String expectedName = "new name";

        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .withCustomerId(expectedCustomerId)
                .withName(expectedName)
                .build();

        Playlist playlist = new Playlist();
        playlist.setCustomerId(expectedCustomerId);

        when(playlistDao.getPlaylist(expectedName)).thenReturn(playlist);

        // WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
    }

    @Test
    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
        // GIVEN
        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .withName("I'm illegal")
                .withCustomerId("customerId")
                .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeChangeException.class, () -> createPlaylistActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_twoPlaylists_haveUniqueIds() {
        // GIVEN
        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .withName("name")
                .withCustomerId("customerId")
                .build();
        CreatePlaylistRequest requestTwo = CreatePlaylistRequest.builder()
                .withName("other name")
                .withCustomerId("customerId2")
                .build();
        // WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request, null);
        CreatePlaylistResult resultTwo = createPlaylistActivity.handleRequest(requestTwo, null);
        // THEN
        assertNotEquals(result.getPlaylist().getId(), resultTwo.getPlaylist().getId());
    }
}
