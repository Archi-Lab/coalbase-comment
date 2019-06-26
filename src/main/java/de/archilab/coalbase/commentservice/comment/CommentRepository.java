package de.archilab.coalbase.commentservice.comment;

import de.archilab.coalbase.commentservice.core.UniqueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CommentRepository extends
        CrudRepository<Comment, UniqueId<Comment>> {

}

