package de.archilab.coalbase.commentservice.comment;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class CommentAuthor {
  private String userName;
  private String firstName;
  private String lastName;
}
