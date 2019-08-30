package io.archilab.coalbase.comment.learningoutcome;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.archilab.coalbase.comment.comment.CommentRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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
      this.commentRepository.deleteAllByAttachedEntityId(
          learningOutcomeDomainEvent.getLearningOutcomeIdentifier().getUuid());
    }
  }
}
