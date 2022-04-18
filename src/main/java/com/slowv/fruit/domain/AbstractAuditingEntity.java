package com.slowv.fruit.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * @author Viettq
 * @createdAt 4/19/2021
 * @updatedAt 4/19/2021
 */

/**
 * Base abstract class for entities which will hold definitions for created, updated
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    protected Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(name = "updated_date")
    protected Instant updatedDate = Instant.now();
}
