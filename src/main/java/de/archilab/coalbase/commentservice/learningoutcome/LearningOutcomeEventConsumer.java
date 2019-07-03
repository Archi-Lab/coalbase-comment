package de.archilab.coalbase.commentservice.learningoutcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.archilab.coalbase.commentservice.comment.CommentRepository;

@Component
public class LearningOutcomeEventConsumer {

  private final ObjectMapper objectMapper;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  public LearningOutcomeEventConsumer(
      final ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @KafkaListener(topics = "${learning-outcome.topic}", groupId = "${spring.kafka.group-id}")
  public void listen(String message) throws IOException {
    LearningOutcomeDomainEvent learningOutcomeDomainEvent = this.objectMapper
        .readValue(message, LearningOutcomeDomainEvent.class);

    if (learningOutcomeDomainEvent.getEventType().equals(LearningOutcomeEventType.DELETED.name())) {
      commentRepository.deleteAllByAttachedEntityId(learningOutcomeDomainEvent.getLearningOutcomeIdentifier().getUuid());
    }
  }
}
