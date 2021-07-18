package com.irostub.thymeleafbasic.basic;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "basic")
public class BasicController {

    @GetMapping(value = "text-basic")
    public String escape(Model model) {
        model.addAttribute("data", "hello spring!");
        return "basic/text-basic";
    }

    @GetMapping(value = "text-unescaped")
    public String unescape(Model model) {
        model.addAttribute("data", "hello <b>spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping(value = "basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    @GetMapping(value = "date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping(value = "link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping(value="literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping(value = "operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping(value = "attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping(value = "each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    @GetMapping(value = "condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping(value = "comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }
    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    static class User{
        String username;
        int age;

        public User(String name, int age) {
            this.username = name;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public int getAge() {
            return age;
        }
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }
}
