package com.slowv.fruit.domain;

import com.slowv.fruit.domain.enums.EAccountStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Account extends AbstractAuditingEntity implements Serializable {
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
    private int status;

    private String uuid;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles = new HashSet<>();

    public Account() {
    }

    public Account(String email, String password, String fullName, String phoneNumber, String address, String introduction) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.introduction = introduction;
        this.status = EAccountStatus.HOAT_DONG.getNumber();
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
