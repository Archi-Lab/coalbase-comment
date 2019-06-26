package de.archilab.coalbase.commentservice.comment;

import de.archilab.coalbase.commentservice.core.EntityWithUniqueId;
import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
public class Comment extends EntityWithUniqueId<Comment> {

    private String msName; // not necessary because an entity belongs to only one ms?


    private String attachedEntitySelfLink; // Relative Self Link

    private UUID attachedEntityID; // necessary for deletion?


    private String attributeName;


    private String content; // Content of the comment

}
