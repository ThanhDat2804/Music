package com.music.user.service.dto;

import java.time.LocalDate;

public record UserUpdateRecord(
        String name,
        LocalDate dob,
        String gender,
        String language,
        String countryIso2
) {
}
