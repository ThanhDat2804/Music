package com.music.music.service.dto;

import com.music.music.service.model.SongType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {

    private String description;

    private String name;

    private LocalDateTime releasedDate;

    private SongType type;

    private String albumId;

}
