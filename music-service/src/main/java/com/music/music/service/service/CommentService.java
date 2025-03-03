package com.music.music.service.service;

import com.music.music.service.dto.CommentDto;
import com.music.music.service.dto.SongCommentProjectionDto;
import com.music.music.service.model.Comment;

import java.awt.print.Pageable;
import java.util.List;

public interface CommentService {

    Comment create(Comment comment, String userId, String songId);

    Comment update(String id, CommentDto comment );

    List<SongCommentProjectionDto> getSongComments(String songId, Integer page, Integer pageSize );

    void deleteById(String id);

}
