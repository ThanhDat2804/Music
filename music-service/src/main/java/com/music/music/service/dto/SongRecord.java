package com.music.music.service.dto;

import com.music.music.service.model.SongType;
import com.music.music.service.model.StorageType;

import java.time.LocalDateTime;

public record SongRecord (String title,
                          String storageId,
                          StorageType storageType,
                          SongType songType,
                          String albumId,
                          String genreId,
                          long duration,
                          LocalDateTime releasedDate,
                          Integer releaseYear) {
}
