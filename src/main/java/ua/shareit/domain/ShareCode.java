package ua.shareit.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.util.UUID;

/**
 * A ShareCode.
 */
@Entity
@Table(name = "share_code")
public class ShareCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "uid", nullable = false, unique = true)
    private UUID uid;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Duration duration;

    @NotNull
    @Column(name = "created", nullable = false)
    private ZonedDateTime created;

    
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "code", nullable = false)
    private String code;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public ShareCode userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UUID getUid() {
        return uid;
    }

    public ShareCode uid(UUID uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public Duration getDuration() {
        return duration;
    }

    public ShareCode duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ShareCode created(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public String getCode() {
        return code;
    }

    public ShareCode code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShareCode)) {
            return false;
        }
        return id != null && id.equals(((ShareCode) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShareCode{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", uid='" + getUid() + "'" +
            ", duration='" + getDuration() + "'" +
            ", created='" + getCreated() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
