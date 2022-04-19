package com.slowv.fruit.component;

import com.slowv.fruit.domain.Account;
import com.slowv.fruit.domain.Role;
import com.slowv.fruit.domain.enums.ERole;
import com.slowv.fruit.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@AllArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.findAll().size() == 0) {
            final var account = new Account("quocviet.hn98@gmail.com", passwordEncoder.encode("admin123"), "Trịnh Quốc Việt", "0349555602", "52 Đình Thôn", "");
            account.setUuid(UUID.randomUUID().toString());
            final var roles = new HashSet<Role>();
            roles.add(new Role(ERole.ADMIN));
            account.setRoles(roles);
            accountRepository.save(account);
        }
    }
}
