package com.slowv.fruit.domain;

import com.slowv.fruit.domain.enums.ECategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
public class Category extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String images;
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(String name, String description, String images) {
        this.name = name;
        this.description = description;
        this.images = images;
        this.status = ECategoryStatus.HOAT_DONG.getNumber();
    }

    public Category(String name, String description, String images, Category category) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.description = description;
        this.images = images;
        this.status = ECategoryStatus.HOAT_DONG.getNumber();
        this.parent = category;
    }

    public String getStatusString(){
        return ECategoryStatus.getStatusByValue(this.status).toString();
    }

}
