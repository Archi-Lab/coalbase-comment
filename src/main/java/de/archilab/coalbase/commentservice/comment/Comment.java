package de.archilab.coalbase.commentservice.comment;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import de.archilab.coalbase.commentservice.core.EntityWithUniqueId;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.UUID;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(callSuper = true)
public class Comment extends EntityWithUniqueId {

    private UUID attachedEntityId;

    private String attributeName;

    @Embedded
    private CommentAuthor author;

    private String content;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreationTimestamp
    private Date created;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date modified;


    public Comment(UUID attachedEntityId, String attributeName, String content) {
        this.attachedEntityId = attachedEntityId;
        this.attributeName = attributeName;
        this.content = content;
    }

    public Comment(UUID attachedEntityId, String attributeName, CommentAuthor author, String content) {
        this.attachedEntityId = attachedEntityId;
        this.attributeName = attributeName;
        this.author = author;
        this.content = content;
    }
}
