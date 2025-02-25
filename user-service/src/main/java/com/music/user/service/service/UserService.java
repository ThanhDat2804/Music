package com.music.user.service.service;

import com.music.user.service.model.StorageProvider;
import com.music.user.service.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User save(User user);

    User update(User user, String userId);

    User getById(String userId);

    void deleteUserById(String userId);

    void uploadProfilePicture(String userId, String key, MultipartFile file, StorageProvider storageProvider) throws Exception;
}
