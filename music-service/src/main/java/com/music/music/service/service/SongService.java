package com.music.music.service.service;

import com.music.music.service.dto.*;
import com.music.music.service.model.Song;
import com.music.music.service.model.Status;
import software.amazon.awssdk.core.async.listener.PublisherListener;

import java.time.LocalDateTime;
import java.util.List;

public interface SongService {

    Song create(SongRecord songRecord, String artistId);

    Song update(String id, SongDto songRecord);

    void deleteById(String id);

    void userLikeASong(String songId, String userId);

    void userDisLikeASong(String songId, String userId);

    void userPlaysASong(String songId, String userId);

    List<SongProjectionDto> getArtistSongs(String artistId, Integer page, Integer pageSize);

    PublishResponseDto updateStatus(String id, Status status);

    PublishResponseDto releaseSong(String id, ReleaseDto releaseDto);
}
