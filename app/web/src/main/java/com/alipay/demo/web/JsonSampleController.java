package com.alipay.demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Restful 示例
 */
@RestController
@RequestMapping("/v1/user")
public class JsonSampleController {
    private static final Logger log = LoggerFactory.getLogger(JsonSampleController.class);

    @GetMapping
    public User getUser() {
        return new User("zhangsan", 21);
    }

    @ResponseBody
    @PostMapping
    public void postUser(@RequestBody User user) {
        log.info(user.toString());
    }
}
