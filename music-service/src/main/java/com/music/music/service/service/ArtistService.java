package com.music.music.service.service;

import com.music.music.service.model.Artist;

public interface ArtistService {

    Artist createNew(Artist artist);
    Artist findById(String id);
    void deleteById(String id);

    void addArtistAndYearRelationship(String artistId, Integer integer, String id,String genreId);
}
