package com.irostub.thymeleafbasic.basic;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello "+data;
        }
    }
}
