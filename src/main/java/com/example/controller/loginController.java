package com.example.controller;

import com.example.domain.GetUser;
import com.example.domain.UserLogin;
import com.example.domain.UserSignUp;
import com.example.repository.IUser;
import com.example.repository.Repository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


@Controller
public class loginController {
    @Autowired
    Repository repository;

    @PostMapping("/login")
    public ModelAndView getUserLogin(@RequestParam String Username, @RequestParam String Password) throws Exception {
        UserLogin login = repository.getUserLogin(Username, Password);
        if (login == null)
            return new ModelAndView("redirect:/index.html");

        return new ModelAndView("/game").addObject("UserName",Username);

    }

    @GetMapping("/game")
    public String form() {
        return "game";
    }

    @PostMapping("/newUser")
    public ModelAndView newUser(@RequestParam String FirstName, @RequestParam String LastName, @RequestParam String Email, @RequestParam String Username, @RequestParam String Password) throws Exception {


        repository.addUser(FirstName, LastName, Email, Username, Password);
        return new ModelAndView("game")
                .addObject("FirstName", FirstName)
                .addObject("LastName", LastName)
                .addObject("Email", Email)
                .addObject("Username", Username)
                .addObject("Password",Password );

}


}
