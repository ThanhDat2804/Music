package com.music.music.service.api;

import com.music.music.service.dto.PublishResponseDto;
import com.music.music.service.dto.ReleaseDto;
import com.music.music.service.model.Status;
import com.music.music.service.service.AlbumService;
import com.music.music.service.service.SongService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
@RequiredArgsConstructor
public class PublishApi {

    private final SongService songService;
    private final AlbumService albumService;

    @PutMapping("song/{id}/status")
    public ResponseEntity<PublishResponseDto> updatePublishSong(@RequestParam Status status, @PathVariable String id){
        PublishResponseDto publishResponseDto = songService.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.CREATED).body(publishResponseDto);
    }

    // Marshall Chabanga
    @PutMapping("album/{id}/status")
    public ResponseEntity<PublishResponseDto> updatePublishAlbum(@RequestParam Status status, @PathVariable String id){
        PublishResponseDto publishResponseDto = albumService.updateStatus(id, status);
        return ResponseEntity.status(HttpStatus.CREATED).body(publishResponseDto);
    }

    @PutMapping("song/{id}/release")
    public ResponseEntity<PublishResponseDto> releaseSong(@RequestBody ReleaseDto releaseDto, @PathVariable String id){
        PublishResponseDto publishResponseDto = songService.releaseSong(id, releaseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(publishResponseDto);
    }

    @PutMapping("album/{id}/release")
    public ResponseEntity<PublishResponseDto> releaseAlbum(@RequestBody ReleaseDto releaseDto, @PathVariable String id){
        PublishResponseDto publishResponseDto = albumService.releaseAlbum(id, releaseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(publishResponseDto);
    }

}
