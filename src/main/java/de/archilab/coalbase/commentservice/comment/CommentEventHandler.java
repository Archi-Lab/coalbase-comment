package de.archilab.coalbase.commentservice.comment;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Comment.class)
public class CommentEventHandler {

  @HandleBeforeCreate
  public void setAuthorBeforeCreate(Comment comment) {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    comment.setAuthor(name);
  }
}
