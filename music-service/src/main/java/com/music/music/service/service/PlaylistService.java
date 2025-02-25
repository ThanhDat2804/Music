package com.music.music.service.service;

import com.music.music.service.model.Playlist;

public interface PlaylistService {

    Playlist create(Playlist playlist, String userId);
    void deleteById(String id);
    void addSongIntoPlaylist(String playlistId, String songId);
    void removeSongFromPlaylist(String playlistId, String songId);

}
