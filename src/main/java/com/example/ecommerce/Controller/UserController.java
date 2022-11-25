package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    User _userBean;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/checklogin")
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {
        if (userService.checkLogin(email, password)) {
            model.addAttribute("name", userService.findByEmail(email).get().getFullname());
            return "index";
        }
        return "redirect:/login";
    }

    @PostMapping("/adduser")
    public @ResponseBody String addUser(@RequestParam("email") String email,
                                        @RequestParam("phonenumber") String phonenumber,
                                        @RequestParam("password") String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(String.valueOf(password.hashCode()));
        user.setAddress("Hanoi");
        user.setFullname("Duong");
        user.setRole("User");
        user.setPhoneNumber(phonenumber);
        this.userService.save(user);
        return "Saved";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/sign_up")
    public String signUp() {
        return "sign_up";
    }

    @PostMapping("/register")
    public String register(User user) {
        try {
            userService.save(user);
            return "user_index";
        } catch (Exception e) {
            return "redirect:/sign_up";
        }
    }

//    @GetMapping()

}
