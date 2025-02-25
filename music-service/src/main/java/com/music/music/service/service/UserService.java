package com.music.music.service.service;

import com.music.music.service.dto.UserRegistrationRequestRecord;
import com.music.music.service.model.User;

public interface UserService {

    User createUser(UserRegistrationRequestRecord user);
    void deleteByUserId(String id);
    void userFollowArtist(String userId,String artistId);
    void userUnFollowArtist(String userId,String artistId);
}
