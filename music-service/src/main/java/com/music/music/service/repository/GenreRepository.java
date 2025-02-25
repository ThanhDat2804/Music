package com.music.music.service.repository;

import com.music.music.service.model.Genre;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface GenreRepository extends Neo4jRepository<Genre,String> {


    @Query("MATCH (genre: Genre {id: $genreId}), (artist: Artist {id: $artistId})" +
            " MERGE (artist)-[:BELONGS_TO_GENRE {createdAt: $createdAt}]->(genre)")
    void addArtistToGenre(@Param("genreId") String genreId, @Param("artistId")String artistId,
                          @Param("createdAt")LocalDateTime createdAt);
    @Query("MATCH (genre: Genre {id: $genreId}), (song: Song {id: $songId})" +
            " MERGE (song)-[:BELONGS_TO_GENRE {createdAt: $createdAt}]->(genre)")
    void addSongToGenre(@Param("genreId") String genreId, @Param("songId")String songId,
                        @Param("createdAt")LocalDateTime createdAt);
    @Query("MATCH (genre: Genre {id: $genreId})<-[relationship:BELONGS_TO_GENRE]-(song: Song {id: $songId})" +
            " DELETE relationship")
    void removeSongFromGenre(@Param("genreId") String genreId, @Param("songId")String songId);
    @Query("MATCH (genre: Genre {id: $genreId})<-[relationship:BELONGS_TO_GENRE]-(artist: Artist {id: $artistId})" +
            " DELETE relationship")
    void removeArtistFromGenre(@Param("genreId") String genreId, @Param("artistId")String artistId);
}
