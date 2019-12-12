package com.gialinhnail.shop.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
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
    @Column(columnDefinition = "TEXT")
    private String detail;
    private int quantity;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @ManyToOne
    private Category category;
    @ManyToOne
    private Collection collection;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails;

    public Product() {
    }

    public Product(String name, double unitPrice, String description, String images, boolean isSale, String detail, int quantity) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.images = images;
        this.isSale = isSale;
        this.detail = detail;
        this.quantity = quantity;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = Product.Status.VAN_CON.getInt();
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
