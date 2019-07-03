package de.archilab.coalbase.commentservice.learningoutcome;


import de.archilab.coalbase.commentservice.core.DomainEvent;
import de.archilab.coalbase.commentservice.core.UniqueId;

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
    return learningOutcomeIdentifier;
  }
}
