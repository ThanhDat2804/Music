package com.music.music.service.api;

import com.music.music.service.model.Album;
import com.music.music.service.model.Playlist;
import com.music.music.service.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistApi {

    private final PlaylistService playlistService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Album> createNewAPlaylist(@RequestBody Playlist requestRecord,
                                                    @PathVariable String userId){
        playlistService.create(requestRecord,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/song/{songId}/add-song")
    public  void addSongIntoPlaylist(@PathVariable String id,
                                     @PathVariable String songId){
        playlistService.addSongIntoPlaylist(id,songId);
    }
    @PutMapping("/{id}/song/{songId}/remove-song")
    public  void removeSongFromPlaylist(@PathVariable String id,
                                        @PathVariable String songId){
        playlistService.removeSongFromPlaylist(id,songId);
    }



}
