package com.slowv.fruit.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String fullName;
    @NotNull
    private String phoneNumber;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String introduction;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    @OneToMany(mappedBy = "account")
    @ToString.Exclude
    private Set<Customer> customers;

    public Account() {
    }

    public Account(String email, String password, String fullName, String phoneNumber, String address, String introduction) {
        long now = Calendar.getInstance().getTimeInMillis();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
