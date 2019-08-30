package io.archilab.coalbase.comment.comment;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Comment.class)
public class CommentEventHandler {

  @HandleBeforeCreate
  public void setAuthorBeforeCreate(Comment comment) {
    String name = SecurityContextHolder.getContext().getAuthentication().getName();
    if (comment.getAuthor() == null) {
      comment.setAuthor(new CommentAuthor());
    }
    comment.getAuthor().setUserName(name);
  }
}
