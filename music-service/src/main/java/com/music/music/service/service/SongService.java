package com.music.music.service.service;

import com.music.music.service.dto.SongRecord;
import com.music.music.service.model.Song;

public interface SongService {

    Song create(SongRecord songRecord, String artistId);
    void deleteById(String id);

    void userLikeASong(String songId,String userId);
    void userDisLikeASong(String songId,String userId);

}
