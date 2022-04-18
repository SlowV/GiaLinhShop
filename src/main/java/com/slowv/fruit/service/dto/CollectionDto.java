package com.slowv.fruit.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class CollectionDto implements Serializable {
    private final long id;
    private final String name;
    private final String description;
    private final String images;
    private final long collectionParentId;
    private final boolean isParent;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final int statusCode;
    private final String statusLabel;
}
