package com.alipay.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Velocity 示例
 */
@Controller
@RequestMapping("/")
public class VelocitySampleController {

    @GetMapping
    public String velocity(ModelMap model) {
        model.addAttribute("message", "欢迎使用 SOFABoot!");
        return "velocity-sample";
    }
}
