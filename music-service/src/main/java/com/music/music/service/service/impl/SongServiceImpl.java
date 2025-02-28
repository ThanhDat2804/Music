package com.music.music.service.service.impl;

import com.music.music.service.dto.SongDto;
import com.music.music.service.dto.SongProjectionDto;
import com.music.music.service.dto.SongRecord;
import com.music.music.service.model.Song;
import com.music.music.service.model.Status;
import com.music.music.service.repository.SongRepository;
import com.music.music.service.service.ArtistService;
import com.music.music.service.service.SongService;
import com.music.music.service.service.YearService;
import com.music.music.service.utils.JsonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.music.music.service.repository.SongRepository.GET_ARTIST_SONGS;


@Service
@Slf4j
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository repository;
    private final YearService yearService;
    private final ArtistService artistService;
    private final Neo4jClient neo4jClient;
    @Override
    public Song create(SongRecord songRecord, String artistId) {


        Song song = Song.builder()
                .name(songRecord.title())
                .storageId(songRecord.storageId())
                .storageType(songRecord.storageType())
                .type(songRecord.songType())
                .duration(songRecord.duration())
                .releasedDate(songRecord.releasedDate())
                .status(Status.DRAFT)
                .build();

        Song saved = repository.save(song);
        yearService.create(songRecord.releaseYear());
        artistService.addArtistAndYearRelationship(artistId,songRecord.releaseYear(),
                saved.getId(),songRecord.genreId());

        return saved;
    }

    @Override
    public Song update(String id, SongDto songRecord) {
        yearService.create(songRecord.getReleasedDate().getYear());
        repository.removeSongReleasedYear(id, songRecord.getReleasedDate().getYear());

        Song song = repository.updateSong(
                id,
                songRecord.getDescription(),
                songRecord.getName(),
                songRecord.getReleasedDate(),
                songRecord.getType(),
                songRecord.getReleasedDate().getYear()
        );

        return song;
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void userLikeASong(String songId, String userId) {

        repository.userLikeASong(songId,userId, LocalDateTime.now());

    }

    @Override
    public void userDisLikeASong(String songId, String userId) {
        repository.userDisLikeASong(songId,userId);

    }

    @Override
    public void userPlaysASong(String songId, String userId) {
        repository.userPlaysASong(songId,userId,LocalDateTime.now(),1);
    }

    @Override
    public List<SongProjectionDto> getArtistSongs(String artistId, Integer page, Integer pageSize) {
        neo4jClient.query(GET_ARTIST_SONGS)
                .bind(artistId).to("artistId")
                .bind(page).to("page")
                .bind(pageSize).to("pageSize")
                .fetch()
                .all()
                .stream()
                .map(map -> JsonUtility.fromMap(map, SongProjectionDto.class))
                .collect(Collectors.toList());
        return List.of();
    }
}
