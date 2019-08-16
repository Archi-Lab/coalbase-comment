package de.archilab.coalbase.commentservice.comment;

import static org.junit.Assert.assertEquals;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@EnableKafka
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SpringBootTest
@Transactional
@EmbeddedKafka
public class CommentTest {

  private static final UUID ENTITY_Id_1 = UUID.randomUUID();
  private static final UUID ENTITY_Id_2 = UUID.randomUUID();

  private static final String ATTRIBUTE_NAME = "title";
  private static final String AUTHOR_NAME = "Author";
  private static final CommentAuthor AUTHOR = new CommentAuthor(AUTHOR_NAME, "Au", "ther");

  private static final Comment COMMENT_1 = new Comment(ENTITY_Id_1, ATTRIBUTE_NAME, AUTHOR,
      "This comment is useless");
  private static final Comment COMMENT_2 = new Comment(ENTITY_Id_1, ATTRIBUTE_NAME, AUTHOR,
      "This comment is useless too");
  private static final Comment COMMENT_3 = new Comment(ENTITY_Id_2, ATTRIBUTE_NAME, AUTHOR,
      "This comment is the most useless one");

  // Kafka
  private static final String COMMENT_TOPIC = "comment_test";

  @ClassRule
  public static final EmbeddedKafkaRule BROKER = new EmbeddedKafkaRule(1, false,
      COMMENT_TOPIC);


  @Autowired
  private CommentRepository commentRepository;

  @BeforeClass
  public static void setupKafka() {
    System.setProperty("spring.kafka.bootstrap-servers",
        CommentTest.BROKER.getEmbeddedKafka().getBrokersAsString());

    Map<String, Object> consumerProps = KafkaTestUtils
        .consumerProps("testT", "false",
            CommentTest.BROKER.getEmbeddedKafka());

    consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(
        consumerProps);

    ContainerProperties containerProperties = new ContainerProperties(
        COMMENT_TOPIC);

    KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(
        cf, containerProperties);

    container.setupMessageListener(
        (MessageListener<String, String>) record -> {
        });

    container.setBeanName("templateTests");
    container.start();
    ContainerTestUtils
        .waitForAssignment(container,
            CommentTest.BROKER.getEmbeddedKafka()
                .getPartitionsPerTopic());

  }


  @Test
  @WithMockUser(username = AUTHOR_NAME, roles = {"professor"})
  public void createComment() {
    commentRepository.save(COMMENT_1);

    assertEquals(commentRepository.count(), 1);

    Comment comment = ((List<Comment>) commentRepository.findAll()).get(0);
    assertEquals(comment.getAttachedEntityId(), COMMENT_1.getAttachedEntityId());
    assertEquals(comment.getAttributeName(), COMMENT_1.getAttributeName());
    assertEquals(comment.getContent(), COMMENT_1.getContent());

    commentRepository.deleteAll();

    assertEquals(commentRepository.count(), 0);
  }

  @Test
  public void deleteCommentsByAttachedEntityId() {
    commentRepository.save(COMMENT_1);
    commentRepository.save(COMMENT_2);
    commentRepository.save(COMMENT_3);

    assertEquals(commentRepository.count(), 3);

    commentRepository.deleteAllByAttachedEntityId(ENTITY_Id_1);
    assertEquals(commentRepository.count(), 1);

    commentRepository.deleteAllByAttachedEntityId(ENTITY_Id_2);
    assertEquals(commentRepository.count(), 0);
  }
}
