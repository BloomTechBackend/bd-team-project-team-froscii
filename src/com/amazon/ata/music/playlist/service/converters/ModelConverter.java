package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.SongModel;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    public Playlist toPlaylist(PlaylistModel playlistModel) {
        Playlist playlist = new Playlist();
        playlist.setSongCount(playlistModel.getSongCount());
        playlist.setId(playlistModel.getId());
        playlist.setCustomerId(playlistModel.getCustomerId());
        playlist.setName(playlistModel.getName());
        playlist.setTags(playlistModel.getTags());
        return playlist;
    }
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        if (playlist.getSongCount() == null) {
            playlist.setSongCount(0);
        }
        return PlaylistModel.builder()
            .withId(playlist.getId())
            .withCustomerId(playlist.getCustomerId())
            .withName(playlist.getName())
            .withSongCount(playlist.getSongCount())
            .withTags(playlist.getTags())
            .build();
    }
    public List<SongModel> toSongModelList(List<AlbumTrack> songList) {
        List<SongModel> songModelList = new ArrayList<>();
        for (AlbumTrack song : songList) {
            SongModel songModel = SongModel.builder()
               .withTitle(song.getSongTitle())
               .withAsin(song.getAsin())
               .withTrackNumber(song.getTrackNumber())
               .withAlbum(song.getAlbumName())
               .build();
            songModelList.add(songModel);
        }
        return songModelList;
    }
}
