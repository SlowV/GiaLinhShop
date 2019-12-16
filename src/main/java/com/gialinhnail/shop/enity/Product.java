package com.gialinhnail.shop.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double unitPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String images;
    private boolean isSale;
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
    private Set<OrderDetail> orderDetails;

    public Product() {
    }

    public Product(String name, double unitPrice, String description, String images, boolean isSale, int perCent, String detail, int quantity) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.images = images;
        this.isSale = isSale;
        this.perCent = perCent;
        this.detail = detail;
        this.quantity = quantity;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = Product.Status.VAN_CON.getInt();

    }

    public Product(String name, double unitPrice, String description, String images, boolean isSale, int perCent, String detail, int quantity, Category category, Collection collection) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.images = images;
        this.isSale = isSale;
        this.perCent = perCent;
        this.detail = detail;
        this.quantity = quantity;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = Product.Status.VAN_CON.getInt();
        this.category = category;
        this.collection = collection;
    }

    public boolean isNew(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 7);
        return this.createdAt < calendar.getTimeInMillis();
    }

    public double getPriceDiscount(){
        return this.unitPrice - (this.unitPrice * ( this.perCent / 100.0));
    }

    enum Status {
        VAN_CON(1), HET_HANG(0), DA_XOA(-1);
        private int number;

        Status(int number){
            this.number = number;
        }

        public int getInt(){
            return number;
        }

        public static Status getStatusByValue(int value) {
            for (Status status : Product.Status.values()) {
                if (status.number == value) return status;
            }
            throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
        }
    }
}
