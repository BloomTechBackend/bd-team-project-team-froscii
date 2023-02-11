package com.amazon.ata.music.playlist.service.dynamodb.models;

import com.amazon.ata.music.playlist.service.converters.AlbumTrackLinkedListConverter;

import com.amazon.ata.music.playlist.service.models.SongOrder;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.amazon.ata.music.playlist.service.models.SongOrder.DEFAULT;

/**
 * Represents a record in the playlists table.
 */
@DynamoDBTable(tableName = "playlists")
public class Playlist {
    private String id;
    private List<AlbumTrack> songList;
    private String name;
    private String customerId;
    private Integer songCount;
    private Set<String> tags;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute(attributeName = "name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @DynamoDBAttribute(attributeName = "customerId")
    public String getCustomerId() { return this.customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    @DynamoDBAttribute(attributeName = "songCount")
    public Integer getSongCount() { return this.songCount; }
    public void setSongCount(Integer songCount) { this.songCount = songCount; }
    @DynamoDBAttribute(attributeName = "tags")
    public Set<String> getTags() { return this.tags; }
    public void setTags(Set<String> tags) { this.tags = tags; }

    // PARTICIPANTS: You do not need to modify the songList getters/setters or annotations
    @DynamoDBTypeConverted(converter = AlbumTrackLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "songList")
    public List<AlbumTrack> getSongList() {
        return getSongs();
    }

    public void setSongList(List<AlbumTrack> songList) {
        this.songList = songList;
    }

    public void addSong(AlbumTrack albumTrack, boolean queueNext) {
        if (queueNext) {
            this.songList.add(albumTrack);
        } else {
            this.songList.add(0, albumTrack);
        }
    }
    public List<AlbumTrack> getSongs() {
        return getSongs(DEFAULT);
    }
    public List<AlbumTrack> getSongs(SongOrder order) {
        switch (order) {
            case DEFAULT:
                return songList;
            case REVERSED:
                List<AlbumTrack> reversedList = new ArrayList<AlbumTrack>(songList);
                Collections.reverse(reversedList);
                return reversedList;
            case SHUFFLED:
                List<AlbumTrack> shuffledList = new ArrayList<>(songList);
                Collections.shuffle(shuffledList);
                return shuffledList;
            default:
                return new ArrayList<>();
        }
    }
}
