package com.music.music.service.api;

import com.music.music.service.dto.CommentDto;
import com.music.music.service.model.Album;
import com.music.music.service.model.Comment;
import com.music.music.service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi {

    private final CommentService commentService;

    @PostMapping("/song/{songId}/user/{userId}")
    public ResponseEntity<CommentDto> createNewComment(@RequestBody Comment comment,
                                                       @PathVariable String songId,
                                                       @PathVariable  String userId){

        commentService.create(comment,userId,songId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto comment,
                                                  @PathVariable String id){

        commentService.update(id, comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public void deleteById( @PathVariable String id){

        commentService.deleteById(id);
    }

    @GetMapping("/song/{songId}/comments")
    public void getSongComment( @PathVariable String songId,
                                @RequestParam Integer page,
                                @RequestParam Integer pageSize){

        commentService.getSongComments(songId, page, pageSize);
    }

}
