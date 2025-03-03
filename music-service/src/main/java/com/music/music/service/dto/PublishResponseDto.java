package com.music.music.service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishResponseDto {
    private String id;
    private boolean isSuccess;

}
