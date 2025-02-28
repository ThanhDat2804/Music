package com.music.music.service.service;

import com.music.music.service.dto.GenreDto;
import com.music.music.service.model.Genre;

public interface GenreService {
    Genre create(Genre genre);
    Genre update(String id, GenreDto genre);
    void addArtistToGenre(String genreId, String artistId);
    void removeArtistFromGenre(String genreId, String artistId);
    void addSongToGenre(String genreId, String songId);
    void removeSongFromGenre(String genreId, String songId);
}
