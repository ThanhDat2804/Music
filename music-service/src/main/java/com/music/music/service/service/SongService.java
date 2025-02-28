package com.music.music.service.service;

import com.music.music.service.dto.SongDto;
import com.music.music.service.dto.SongProjectionDto;
import com.music.music.service.dto.SongRecord;
import com.music.music.service.model.Song;

import java.util.List;

public interface SongService {

    Song create(SongRecord songRecord, String artistId);

    Song update(String id, SongDto songRecord);

    void deleteById(String id);

    void userLikeASong(String songId, String userId);

    void userDisLikeASong(String songId, String userId);

    void userPlaysASong(String songId, String userId);

    List<SongProjectionDto> getArtistSongs(String artistId, Integer page, Integer pageSize);
}
