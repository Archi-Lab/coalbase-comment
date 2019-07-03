package de.archilab.coalbase.commentservice.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.archilab.coalbase.commentservice.core.EntityWithUniqueId;
import lombok.*;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class Comment extends EntityWithUniqueId {

    private UUID attachedEntityId;

    private String attributeName;

    @JsonIgnore
    private String author;

    private String content;


    public Comment(UUID attachedEntityId, String attributeName, String content) {
        this.attachedEntityId = attachedEntityId;
        this.attributeName = attributeName;
        this.content = content;
    }
}
