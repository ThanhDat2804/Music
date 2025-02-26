package com.music.music.service.repository;

import com.music.music.service.model.Song;
import com.music.music.service.model.projection.SongProjection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SongRepository extends Neo4jRepository<Song, String> {

    @Query("MATCH (song:Song {id: $songId}) " +
            "RETURN song.id AS id, " +
            "song.name AS name, " +
            "song.description AS description, " +
            "song.status AS status, " +
            "song.duration AS duration, " +
            "song.storageId AS storageId, " +
            "song.storageType AS storageType, " +
            "song.type AS type, " +
            "song.year AS year")
    Optional<SongProjection> findByProjection(@Param("songId") String id);


    @Query("MATCH (song:Song {id: $songId}), (user: User {id: $userId}) " +
            "MERGE (user)-[:LIKES {createdAt: $createdAt}]->(song)")
    void userLikeASong(@Param("songId") String songId, @Param("userId") String userId, @Param("createdAt") LocalDateTime createdAt);

    @Query("MATCH (song:Song {id: $songId})<-[relationship:LIKES]- (user: User {id: $userId}) " +
            "DELETE relationship")
    void userDisLikeASong(@Param("songId") String songId, @Param("userId") String userId);

    @Query("MATCH (song:Song {id: $songId}), (user: User {id: $userId})" +
            " MERGE (user)-[relationship:PLAYS]->(song) " +
            " ON CREATE SET " +
            " relationship.createdAt = $createdAtOrUpdatedAt, " +
            " relationship.updatedAt = $createdAtOrUpdatedAt, " +
            " relationship.counter = $counter " +
            " ON MATCH SET " +
            " relationship.updatedAt = $createdAtOrUpdatedAt, " +
            " relationship.counter = relationship.counter + $counter")
    void userPlaysASong(@Param("songId") String songId,
                       @Param("userId") String userId,
                       @Param("createdAtOrUpdatedAt") LocalDateTime createdAtOrUpdatedAt,
                       @Param("counter") Integer counter);


}
