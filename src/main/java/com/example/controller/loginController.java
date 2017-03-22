package com.example.controller;

import com.example.domain.Post;
import com.example.domain.Thread;
import com.example.domain.UserLogin;
import com.example.domain.UserSignUp;
import com.example.repository.IUser;
import com.example.repository.Repository;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;


@Controller
public class loginController {
    @Autowired
    IUser iUser;
    @Autowired
    Repository repository;

    @PostMapping("/login")
    public ModelAndView getUserLogin(@RequestParam String Username, HttpSession session, @RequestParam String Password) throws Exception {
        UserLogin login = repository.getUserLogin(Username, Password);
        if (login == null) {
            return new ModelAndView("redirect:/index.html");
        }
            session.setAttribute("username", login.username);
        return new ModelAndView("/lobby").addObject("UserName",Username).addObject("threads", iUser.listThreads());
    }

    @GetMapping("/lobby")
    public String form(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/index.html";
        }
        return "lobby";
    }
    @GetMapping("login")
    public ModelAndView listThreads() throws Exception {
        return new ModelAndView("lobby").addObject("threads", iUser.listThreads());
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
        return new ModelAndView("redirect:/index.html")
                .addObject("FirstName", user.getFirstname())
                .addObject("LastName", user.getLastname())
                .addObject("Email", user.getMail())
                .addObject("UserName", user.getUsername())
                .addObject("Password",user.getPassword());
    }

    @GetMapping("/thread/{id}")
    public ModelAndView thread (@PathVariable long id) throws Exception {
        List<Post> posts = iUser.listPosts();
        return new ModelAndView("thread").addObject("posts", posts);
    }

    @PostMapping("/thread/{id}")
    public ModelAndView comment(@PathVariable int id, @RequestParam String text, @RequestParam long userId) throws Exception {

        Thread thread = repository.getThread(id);
        repository.addPost(text,id);

        return new ModelAndView("blog/post")
                .addObject("text", text)
                .addObject("thread", thread);
                /*.addObject("comments", blogRepository.getCommentsFor(post))
                .addObject("author", blogRepository.getAuthorOf(blog));*/
    }
}
