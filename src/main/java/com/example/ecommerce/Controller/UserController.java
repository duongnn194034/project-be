package com.example.ecommerce.Controller;

import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String checkLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        if (_userBean.getEmail().equals(username) &&
        _userBean.getPassword().equals(password)) {
            System.out.println("Success");
            return "index";
        } else {
            System.out.println("Failed");
        }
        return "login";
    }

    @PostMapping("/adduser")
    public @ResponseBody String addUser(@RequestParam("email") String email,
                                        @RequestParam("phonenumber") String phonenumber,
                                        @RequestParam("password") String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress("Hanoi");
        user.setFullname("Duong");
        user.setRole("User");
        user.setPhoneNumber(phonenumber);
        this.userService.save(user);
        return "Saved";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

}
