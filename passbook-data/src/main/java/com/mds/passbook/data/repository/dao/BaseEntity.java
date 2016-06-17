package com.mds.passbook.data.repository.dao;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Column(name = "CREATION_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    @Column(name = "MODIFY_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    @Version
    @Column(name = "VERSION")
    private long version;

    public abstract ID getId();

    public DateTime getCreationTime() {
        return creationTime;
    }

    public DateTime getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = DateTime.now();
    }
}


