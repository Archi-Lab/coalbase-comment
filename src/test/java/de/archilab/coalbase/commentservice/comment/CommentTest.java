package de.archilab.coalbase.commentservice.comment;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentTest {

  private static final UUID entityId1 = UUID.randomUUID();
  private static final UUID entityId2 = UUID.randomUUID();

  private static final String attributeName = "title";
  private static final String authorName = "Author";

  private static final Comment comment1 = new Comment(entityId1, attributeName, authorName,
      "This comment is useless");
  private static final Comment comment2 = new Comment(entityId1, attributeName, authorName,
      "This comment is useless too");
  private static final Comment comment3 = new Comment(entityId2, attributeName, authorName,
      "This comment is the most useless one");


  @Autowired
  private CommentRepository commentRepository;

  @Test
  @WithMockUser(username = authorName, roles = {"professor"})
  public void createComment() {
    commentRepository.save(comment1);

    assertEquals(commentRepository.count(), 1);

    Comment comment = ((List<Comment>) commentRepository.findAll()).get(0);
    assertEquals(comment.getAttachedEntityId(), comment1.getAttachedEntityId());
    assertEquals(comment.getAttributeName(), comment1.getAttributeName());
    assertEquals(comment.getContent(), comment1.getContent());

    commentRepository.deleteAll();

    assertEquals(commentRepository.count(), 0);
  }

  @Test
  public void deleteCommentsByAttachedEntityId() {
    commentRepository.save(comment1);
    commentRepository.save(comment2);
    commentRepository.save(comment3);

    assertEquals(commentRepository.count(), 3);

    commentRepository.deleteAllByAttachedEntityId(entityId1);
    assertEquals(commentRepository.count(), 1);

    commentRepository.deleteAllByAttachedEntityId(entityId2);
    assertEquals(commentRepository.count(), 0);
  }
}
