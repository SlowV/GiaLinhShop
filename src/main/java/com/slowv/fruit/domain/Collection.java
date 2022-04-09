package com.slowv.fruit.domain;

import com.slowv.fruit.domain.enums.ECategoryStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
public class Collection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String images;
    private long collectionParentId;
    private boolean isParent;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @OneToMany(mappedBy = "collection")
    private Set<Product> products;

    public Collection() {
    }

    public Collection(String name, String description, String images, boolean isParent) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.description = description;
        this.images = images;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = ECategoryStatus.HOAT_DONG.getNumber();
        this.isParent = isParent;
    }

    public Collection(String name, String description, String images, boolean isParent, long collectionParentId) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.description = description;
        this.images = images;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = ECategoryStatus.HOAT_DONG.getNumber();
        this.isParent = isParent;
        this.collectionParentId = collectionParentId;
    }
}
