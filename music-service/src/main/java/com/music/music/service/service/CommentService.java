package com.music.music.service.service;

import com.music.music.service.model.Comment;

public interface CommentService {

    Comment create(Comment comment, String userId, String songId);
    Comment update(String id,Comment comment );

    void deleteById(String id);
}
