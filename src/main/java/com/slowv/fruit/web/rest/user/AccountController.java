package com.slowv.fruit.web.rest.user;

import com.slowv.fruit.domain.Account;
import com.slowv.fruit.service.impl.AccountServiceImpl;
import com.slowv.fruit.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    AccountServiceImpl accountServiceImpl;
    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("account", new Account());
        return "user/register";
    }

    @PostMapping("/register")
    public String doRegister(Model model, Account account){
        accountServiceImpl.register(account);
        return "redirect:/account/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("categories", categoryServiceImpl.findByStatus(1));
        model.addAttribute("account", new Account());
        return "user/login";
    }
}
