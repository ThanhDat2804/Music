package com.music.music.service.service;

import com.music.music.service.dto.UserRegistrationRequestRecord;
import com.music.music.service.dto.UserUpdateRequestRecord;
import com.music.music.service.model.User;

public interface UserService {

    User createUser(UserRegistrationRequestRecord user);

    User update(String id, UserUpdateRequestRecord user);

    void deleteByUserId(String id);

    void userFollowArtist(String userId,String artistId);


    void userUnFollowArtist(String userId,String artistId);

    boolean isUserFollowingArtist(String userId,String songId);

    void userInterestWithArtistBySongId(String userId, String songId);

    boolean isUserInterestWithTheArtist(String userId, String songId);
}
