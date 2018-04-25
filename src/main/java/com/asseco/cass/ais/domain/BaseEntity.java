package com.asseco.cass.ais.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {


    private static final long serialVersionUID = 4927737366949320950L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Version
    private Long version;
    @Column(name = "UUID", length = 36)
    private String uuid;
    @Column
    private LocalDateTime entityCreated;



    public BaseEntity() {
        super();
        this.uuid = UUID.randomUUID().toString();
        this.setEntityCreated(LocalDateTime.now());
    }

    public LocalDateTime getEntityCreated() {
        return entityCreated;
    }

    public void setEntityCreated(LocalDateTime entityCreated) {
        this.entityCreated = entityCreated;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
