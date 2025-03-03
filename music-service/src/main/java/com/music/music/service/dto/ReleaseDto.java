package com.music.music.service.dto;

import com.music.music.service.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseDto {
    private String artistId;
    private Status status;
    private LocalDateTime releaseDate;
}
