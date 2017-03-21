package com.example.controller;

import com.example.domain.GetUser;
import com.example.domain.UserLogin;
import com.example.domain.UserSignUp;
import com.example.repository.IUser;
import com.example.repository.Repository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    @GetMapping("/newUser")
    public ModelAndView form(String firstname, String mail, String lastname, String username, String password) {
        UserSignUp user = new UserSignUp("", "", "", "", "");
        ModelAndView mv = new ModelAndView("signUp");
        mv.addObject("user", user);
        return mv;
    }

    @PostMapping("/newUser")
    public ModelAndView login(@Valid UserSignUp user, BindingResult br, HttpSession session) throws Exception {
        if (br.hasErrors()){
            return new ModelAndView("signUp").addObject("user", user);
        }
        repository.addUser(user.getFirstname(), user.getLastname(), user.getMail(), user.getUsername(), user.getPassword());
        return new ModelAndView("game")
                .addObject("FirstName", user.getFirstname())
                .addObject("LastName", user.getLastname())
                .addObject("Email", user.getMail())
                .addObject("Username", user.getUsername())
                .addObject("Password",user.getPassword());
    }
}
