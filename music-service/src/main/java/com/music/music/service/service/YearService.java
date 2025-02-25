package com.music.music.service.service;

import com.music.music.service.model.Year;

public interface YearService {

    Year create(Integer year);
    Year getById(Integer year);
}
