package com.music.music.service.model.projection;


import com.music.music.service.model.Year;

public interface SongProjection {
    String getId();
    String getDescription();
    String getName();
    String getStatus();
    String getDuration();
    String getStorageId();
    String getStorageType();
    String getType();
    Year getYear();

}
