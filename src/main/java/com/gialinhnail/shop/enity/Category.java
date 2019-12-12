package com.gialinhnail.shop.enity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String images;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
    }

    public Category(String name, String description, String images) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.name = name;
        this.description = description;
        this.images = images;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = Status.HOAT_DONG.getInt();
    }

    public String getStatusString(){
        return Status.getStatusByValue(this.status).toString();
    }

    public enum Status {
        HOAT_DONG(1), NGUNG_HOAT_DONG(0), DA_XOA(-1);
        private int number;

        Status(int number){
            this.number = number;
        }

        public int getInt(){
            return number;
        }

        public static Status getStatusByValue(int value) {
            for (Category.Status status : Category.Status.values()) {
                if (status.number == value) return status;
            }
            throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
        }
    }

}
