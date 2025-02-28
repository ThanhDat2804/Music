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
public class SongCommentProjectionDto {
    private String id;
    private String artistId;
    private boolean edited;
    private LocalDateTime createdAt;
    private String userId;
    private String name;
}
