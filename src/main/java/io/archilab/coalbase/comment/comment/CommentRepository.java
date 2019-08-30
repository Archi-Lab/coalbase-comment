package io.archilab.coalbase.comment.comment;

import io.archilab.coalbase.comment.core.UniqueId;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostFilter;


@RepositoryRestResource
public interface CommentRepository extends
    CrudRepository<Comment, UniqueId> {

  @Override
  @PostFilter("filterObject.author.userName == authentication.name")
  Iterable<Comment> findAll();

  @PostFilter("filterObject.author.userName == authentication.name")
  Iterable<Comment> findByAttachedEntityIdAndAttributeName(UUID attachedEntityId,
      String attributeName);

  void deleteAllByAttachedEntityId(UUID attachedEntityId);
}

