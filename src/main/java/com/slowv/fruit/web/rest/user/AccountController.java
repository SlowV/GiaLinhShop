package com.slowv.fruit.web.rest.user;

import com.slowv.fruit.domain.Account;
import com.slowv.fruit.service.impl.AccountServiceImpl;
import com.slowv.fruit.service.impl.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
public class AccountController {
    private final AccountServiceImpl accountServiceImpl;

    private final CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/login")
    public String login(Model model){
        model.addAttribute("categories", categoryServiceImpl.findByStatus(1));
        model.addAttribute("account", new Account());
        return "user/login";
    }
}
