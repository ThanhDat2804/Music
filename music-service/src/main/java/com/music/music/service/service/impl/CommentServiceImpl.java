package com.music.music.service.service.impl;

import com.music.music.service.dto.CommentDto;
import com.music.music.service.dto.SongCommentProjectionDto;
import com.music.music.service.model.Comment;
import com.music.music.service.model.Sentiment;
import com.music.music.service.repository.CommentRepository;
import com.music.music.service.repository.UserRepository;
import com.music.music.service.service.CommentService;
import com.music.music.service.service.SentimentAnalyzer;
import com.music.music.service.utils.JsonUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.music.music.service.repository.CommentRepository.GET_SONG_COMMENTS_QUERY;


@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final Neo4jClient neo4jClient;
    private final SentimentAnalyzer sentimentAnalyzer;

    @Override
    public Comment create(Comment comment, String userId, String songId) {

        Comment comment1 = Comment.builder()
                .text(comment.getText())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .edited(false).build();

        Comment saved = commentRepository.save(comment1);
        commentRepository.addCommentRelationship(saved.getId(),songId,userId);

        analyzeComment(saved, userId, songId );

        return saved;
    }

    private void analyzeComment(Comment saved, String userId, String songId) {

        boolean userFollowsTheArtist = userRepository.isUserFollowsTheArtist(userId, songId);

        if(!userFollowsTheArtist){
            Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf(saved.getText());
            System.out.println("The comment is " + sentiment);
        }

    }

    @Override
    public Comment update(String id, CommentDto comment) {

        return commentRepository.updateComment(id, comment.getText(), true, LocalDateTime.now());
    }

    @Override
    public List<SongCommentProjectionDto> getSongComments(String songId, Integer page, Integer pageSize) {
        return neo4jClient.query(GET_SONG_COMMENTS_QUERY)
                .bind(songId).to("songId")
                .bind(page).to("page")
                .bind(pageSize).to("pageSize")
                .fetch()
                .all()
                .stream()
                .map(map -> JsonUtility.fromMap(map, SongCommentProjectionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
