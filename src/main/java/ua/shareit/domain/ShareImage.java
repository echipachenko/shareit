package ua.shareit.domain;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A ShareImage.
 */
@Entity
@Table(name = "share_image")
public class ShareImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "uid")
    private UUID uid;

    @NotNull
    @Column(name = "created")
    private ZonedDateTime created;

    @NotNull
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @NotNull
    @Column(name = "expired", nullable = false)
    private ZonedDateTime expired;

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

    public ShareImage userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UUID getUid() {
        return uid;
    }

    public ShareImage uid(UUID uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ShareImage created(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public String getFileName() {
        return fileName;
    }

    public ShareImage fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ZonedDateTime getExpired() {
        return expired;
    }

    public ShareImage expired(ZonedDateTime expired) {
        this.expired = expired;
        return this;
    }

    public void setExpired(ZonedDateTime expired) {
        this.expired = expired;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShareImage)) {
            return false;
        }
        return id != null && id.equals(((ShareImage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShareImage{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", uid='" + getUid() + "'" +
            ", created='" + getCreated() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", expired='" + getExpired() + "'" +
            "}";
    }
}
