package com.music.music.service.service.impl;

import com.music.music.service.model.Playlist;
import com.music.music.service.repository.PlaylistRepository;
import com.music.music.service.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    @Override
    public Playlist create(Playlist playlist, String userId) {

        Playlist playlist1 = Playlist.builder()
                .title(playlist.getTitle())
                .description(playlist.getDescription()).build();
        Playlist saved = playlistRepository.save(playlist1);
        playlistRepository.addPlaylistAndUserRelationship(saved.getId(),userId, LocalDateTime.now());
        return saved;
    }

    @Override
    public void deleteById(String id) {

        playlistRepository.deleteById(id);

    }

    @Override
    public void addSongIntoPlaylist(String playlistId, String songId) {

        playlistRepository.addSongToPlaylist(playlistId,songId, LocalDateTime.now());

    }

    @Override
    public void removeSongFromPlaylist(String playlistId, String songId) {

        playlistRepository.removeSongFromPlaylist(playlistId,songId);

    }
}
