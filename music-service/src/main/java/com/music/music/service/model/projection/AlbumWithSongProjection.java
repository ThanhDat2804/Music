package com.music.music.service.model.projection;

import com.music.music.service.dto.SongProjectionDto;
import com.music.music.service.model.Status;

import java.util.List;

public class AlbumWithSongProjection {
    private String id;
    private String name;
    private Status status;
    private List<SongProjectionDto> songs;
}
