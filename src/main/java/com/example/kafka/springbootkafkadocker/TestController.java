package com.example.kafka.springbootkafkadocker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ProducerService producerService;
    ObjectMapper objectMapper;


    @GetMapping("/publish")
    public void messageToTopic(){
        String str = "1234";
         producerService.sendMessage(str);
    }
}
