package io.archilab.coalbase.comment.core;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@MappedSuperclass
public abstract class EntityWithUniqueId {

  @EmbeddedId
  @Getter
  private UniqueId id;

  protected EntityWithUniqueId() {
    this.id = new UniqueId();
  }

}
