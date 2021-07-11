package com.irostub.thymeleafbasic.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
