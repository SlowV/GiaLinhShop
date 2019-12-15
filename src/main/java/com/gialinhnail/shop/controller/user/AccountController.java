package com.gialinhnail.shop.controller.user;

import com.gialinhnail.shop.enity.Account;
import com.gialinhnail.shop.service.AccountService;
import com.gialinhnail.shop.service.CategoryService;
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
    AccountService accountService;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("account", new Account());
        return "user/register";
    }

    @PostMapping("/register")
    public String doRegister(Model model, Account account){
        accountService.save(account);
        return "redirect:/account/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("account", new Account());
        return "user/login";
    }
}
