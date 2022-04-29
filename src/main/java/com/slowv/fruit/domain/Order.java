package com.slowv.fruit.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
@RedisHash
public class Order extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    @Column(columnDefinition = "TEXT")
    private String note;
    private int status;

    @ManyToOne()
    @JoinColumn(name = "accountId")
    @ToString.Exclude
    private Account customer;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<OrderDetail> orderDetails;

    public Order(double totalPrice, String note) {
        this.totalPrice = totalPrice;
        this.note = note;
        this.status = Status.DANG_CHO_XAC_NHAN.getInt();
    }

    public Order() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return id != null && Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
