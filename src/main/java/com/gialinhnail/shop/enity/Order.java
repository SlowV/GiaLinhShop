package com.gialinhnail.shop.enity;

import javafx.scene.control.TableColumn;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double totalPrice;
    @Column(columnDefinition = "TEXT")
    private String note;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(double totalPrice, String note) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.totalPrice = totalPrice;
        this.note = note;
        this.createdAt = now;
        this.updatedAt = now;
        this.status = Status.DANG_CHO_XAC_NHAN.getInt();
    }

    enum Status {
        DANG_CHO_XAC_NHAN(0),
        DA_XAC_NHAN(1),
        DA_XOA(-1),
        DANG_GIAO_HANG(2),
        HOAN_THANH(3),
        DA_HUY(-2);
        private int number;

        Status(int number){
            this.number = number;
        }

        public int getInt(){
            return number;
        }

        public static Status getStatusByValue(int value) {
            for (Status status : Status.values()) {
                if (status.number == value) return status;
            }
            throw new IllegalArgumentException("Kiểu trạng thái không tồn tại!");
        }
    }
}
