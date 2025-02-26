package com.music.music.service.model.projection;

import com.music.music.service.dto.SongProjectionDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistWithSongProjection {
    private String title;
    private String id;
    private List<SongProjectionDto> songs;
}
