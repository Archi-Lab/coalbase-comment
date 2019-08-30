package io.archilab.coalbase.comment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@EmbeddedKafka
public class CoalbaseCommentApplicationTest {

  @Autowired
  private EmbeddedKafkaBroker embeddedKafka;

  @Test
  public void contextLoads() {
  }

}
