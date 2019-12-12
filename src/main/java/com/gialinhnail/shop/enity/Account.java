package com.gialinhnail.shop.enity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Set;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    private int gender;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String introduction;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @OneToMany(mappedBy = "account")
    private Set<Customer> customers;

    public Account() {
    }

    public Account(String email, String password, String fullName, String phoneNumber, int gender, String address, String introduction) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.address = address;
        this.introduction = introduction;
        this.createdAt = now;
        this.updatedAt = now;
        this.status  = Status.HOAT_DONG.getInt();
    }

    enum Status {
        HOAT_DONG(1), DANG_KHOA(0), DA_XOA(-1);
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
