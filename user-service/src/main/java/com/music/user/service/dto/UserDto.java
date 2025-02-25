package com.music.user.service.dto;

import com.music.user.service.model.StorageProvider;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private String id;
    private LocalDate dob;
    private String countryIso2;
    private String type;
    private String storageId;
    private StorageProvider storageProvider;
    private String language;
    private String gender;
}
