package com.music.music.service.service;

import com.music.music.service.model.Album;

public interface AlbumService {

    Album create(Album album, Integer releasedYear, String artistId);
    void deleteById(String id);
    void userLikeAnAlbum(String albumId,String userId);
    void userDikeLikeAnAlbum(String albumId,String userId);
    void addSongToAlbum(String albumId, String songId);
}
