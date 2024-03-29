package io.archilab.coalbase.comment.learningoutcome;


import io.archilab.coalbase.comment.core.DomainEvent;
import io.archilab.coalbase.comment.core.UniqueId;

public class LearningOutcomeDomainEvent extends DomainEvent {

  private final UniqueId learningOutcomeIdentifier;
  private final LearningOutcomeEventType eventType;

  protected LearningOutcomeDomainEvent() {
    this.learningOutcomeIdentifier = null;
    this.eventType = null;
  }

  public LearningOutcomeDomainEvent(UniqueId learningOutcomeIdentifier,
      LearningOutcomeEventType eventType) {
    this.learningOutcomeIdentifier = learningOutcomeIdentifier;
    this.eventType = eventType;
  }

  @Override
  public String getEventType() {
    return this.eventType.name();
  }

  public UniqueId getLearningOutcomeIdentifier() {
    return this.learningOutcomeIdentifier;
  }
}
