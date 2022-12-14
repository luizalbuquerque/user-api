package wa.user.api.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserEntity {

    @Column
    private String idUser;

    @Column
    private String Name;

    @Column
    private String Document;

    @Column
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    public UserEntity(){}

    public UserEntity(String idUser, String name, String document, Instant createdAt, Instant updatedAt) {
        this.idUser = idUser;
        Name = name;
        Document = document;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return getIdUser().equals(that.getIdUser()) && getName().equals(that.getName()) && getDocument().equals(that.getDocument()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser(), getName(), getDocument(), getCreatedAt(), getUpdatedAt());
    }
}
