package com.slowv.fruit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slowv.fruit.domain.enums.EProductStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double unitPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String images;
    private int perCent;
    @Column(columnDefinition = "TEXT")
    private String detail;
    private int quantity;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection collection;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<OrderDetail> orderDetails = new HashSet<>();

    public Product() {
        long now = Calendar.getInstance().getTimeInMillis();
        this.createdAt = now;
        this.updatedAt = now;
    }

    public Product(String name, double unitPrice, String description, String images, int perCent, String detail, int quantity) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.images = images;
        this.perCent = perCent;
        this.detail = detail;
        this.quantity = quantity;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = EProductStatus.VAN_CON.getNumber();

    }

    public Product(String name, double unitPrice, String description, String images, int perCent, String detail, int quantity, Category category, Collection collection) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.images = images;
        this.perCent = perCent;
        this.detail = detail;
        this.quantity = quantity;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = EProductStatus.VAN_CON.getNumber();
        this.category = category;
        this.collection = collection;
    }

    public boolean isNew() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);
        return this.createdAt < calendar.getTimeInMillis();
    }

    public double getPriceDiscount() {
        if (this.perCent <= 0) return 0;
        return this.unitPrice - (this.unitPrice * (this.perCent / 100.0));
    }
}
