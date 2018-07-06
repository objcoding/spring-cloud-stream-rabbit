package com.objcoding.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/7/5
 */
@Service
@RestController
@SpringBootApplication
public class RabbitApplication {

    @Autowired
    private Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
    }


    @GetMapping("/start")
    public String start() {
        producer.produce("===rabbit===");
        return "success";
    }

}
