package com.music.music.service.service.impl;

import com.music.music.service.dto.AlbumDto;
import com.music.music.service.model.Album;
import com.music.music.service.model.projection.AlbumProjection;
import com.music.music.service.model.projection.AlbumWithSongProjection;
import com.music.music.service.repository.AlbumRepository;
import com.music.music.service.service.AlbumService;
import com.music.music.service.service.YearService;
import com.music.music.service.utils.JsonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.music.music.service.model.Status.DRAFT;
import static com.music.music.service.repository.AlbumRepository.GET_ALBUM_WITH_SONGS_QUERY;
import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final YearService yearService;
    private final Neo4jClient neo4jClient;

    @Override
    public Album create(Album album, Integer releasedYear, String artistId) {

        Album build = Album.builder()
                .name(album.getName())
                .description(album.getDescription())
                .status(DRAFT).build();
        Album save = albumRepository.save(build);
        yearService.create(releasedYear);
        albumRepository.addReleasedYearAndArtist(artistId,save.getId(),releasedYear,LocalDateTime.now());
        return save;
    }

    @Override
    public Album update(String id, AlbumDto album) {
        AlbumProjection albumProjection = getByIdProjection(id);
        if (isNull(albumProjection)) {
            throw new RuntimeException(String.format("Album with id %s does not exist", id));
        }
        int releasedYear = album.getReleasedDate().getYear();
        yearService.create(album.getReleasedDate().getYear());
        Album updateAlbum = albumRepository.updateAlbum(id, album.getDescription(), album.getName(), album.getReleasedDate());
        albumRepository.removeAlbumReleaseYear(id, releasedYear);
        albumRepository.updateReleasedYear(id, releasedYear, LocalDateTime.now());
        return updateAlbum;
    }

    @Override
    public AlbumProjection getByIdProjection(String id) {
        return albumRepository.findByIdProjection(id).orElseThrow(() -> new RuntimeException("Album not found"));
    }


    @Override
    public void deleteById(String id) {

        albumRepository.deleteById(id);
    }

    @Override
    public void userLikeAnAlbum(String albumId, String userId) {


        albumRepository.userLikesAlbum(userId,albumId, LocalDateTime.now());

    }

    @Override
    public void userDikeLikeAnAlbum(String albumId, String userId) {

        albumRepository.userDislikeAlbum(userId,albumId);
    }

    @Override
    public void addSongToAlbum(String albumId, String songId) {
        albumRepository.addSongToAlbum(albumId,songId,LocalDateTime.now());
    }

    @Override
    public List<AlbumWithSongProjection> getAlbumWithSongs(String artistId) {
        neo4jClient.query(GET_ALBUM_WITH_SONGS_QUERY)
                .bind(artistId).to("artistId")
                .fetch()
                .all()
                .stream()
                .map(map -> JsonUtility.fromMap(map, AlbumWithSongProjection.class))
                .collect(Collectors.toList());
        return null;
    }


}
