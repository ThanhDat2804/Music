package com.music.music.service.dto;

import com.music.music.service.model.SongType;
import com.music.music.service.model.Status;
import com.music.music.service.model.StorageType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongProjectionDto {
    private String id;
    private String description;
    private String name;
    private Status status;
    private Long duration;
    private String storageId;
    private StorageType storageType;
    private LocalDateTime releasedDate;
    private SongType type;
}
