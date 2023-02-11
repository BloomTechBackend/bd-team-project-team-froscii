package com.amazon.ata.music.playlist.service.models.requests;

import com.amazon.ata.music.playlist.service.models.SongOrder;

import java.util.Objects;

public class GetPlaylistSongsRequest {
    private String id;
    private SongOrder order;

    public GetPlaylistSongsRequest() {
    }

    public GetPlaylistSongsRequest(String id, SongOrder order) {
        this.id = id;
        this.order = order;
    }
    public GetPlaylistSongsRequest(String id, String order) {
        this.id = id;
        this.order = SongOrder.valueOf(order);
    }

    public GetPlaylistSongsRequest(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SongOrder getOrder() {
        return order;
    }

    public void setOrder(SongOrder order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPlaylistSongsRequest that = (GetPlaylistSongsRequest) o;
        return Objects.equals(id, that.id) &&
                order == that.order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @Override
    public String toString() {
        return "GetPlaylistSongsRequest{" +
                "id='" + id + '\'' +
                ", order=" + order +
                '}';
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String id;
        private SongOrder order;

        private Builder() {

        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withOrder(SongOrder orderToUse) {
            this.order = orderToUse;
            return this;
        }
        public Builder withOrder(String orderToUse) {
            this.order = SongOrder.valueOf(orderToUse);
            return this;
        }

        public GetPlaylistSongsRequest build() {
            if (this.order == null) {
                this.order = SongOrder.DEFAULT;
            }
            return new GetPlaylistSongsRequest(this); }
    }
}
