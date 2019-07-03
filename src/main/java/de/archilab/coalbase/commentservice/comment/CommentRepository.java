package de.archilab.coalbase.commentservice.comment;

import de.archilab.coalbase.commentservice.core.UniqueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PostFilter;

import java.util.UUID;


@RepositoryRestResource
public interface CommentRepository extends
        CrudRepository<Comment, UniqueId> {

    @Override
    @PostFilter("filterObject.author == authentication.name")
    Iterable<Comment> findAll();

    @PostFilter("filterObject.author == authentication.name")
    Iterable<Comment> findByAttachedEntityIdAndAttributeName(UUID attachedEntityId, String attributeName);

    void deleteAllByAttachedEntityId(UUID attachedEntityId);
}

