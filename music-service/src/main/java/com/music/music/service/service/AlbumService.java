package com.music.music.service.service;

import com.music.music.service.dto.AlbumDto;
import com.music.music.service.model.Album;
import com.music.music.service.model.projection.AlbumProjection;
import com.music.music.service.model.projection.AlbumWithSongProjection;

import java.util.List;

public interface AlbumService {

    Album create(Album album, Integer releasedYear, String artistId);

    Album update(String id, AlbumDto album);

    AlbumProjection getByIdProjection(String id);

    void deleteById(String id);

    void userLikeAnAlbum(String albumId,String userId);

    void userDikeLikeAnAlbum(String albumId,String userId);

    void addSongToAlbum(String albumId, String songId);

    List<AlbumWithSongProjection> getAlbumWithSongs(String artistId);
}
