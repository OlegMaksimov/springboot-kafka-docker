package com.example.kafka.springbootkafkadocker;

import com.mailshine.springboot.kafka.avro.model.Student;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ProducerService producerService;


    @GetMapping("/publish")
    public void messageToTopic(){
        Random generateValue = new Random();
        Student messageRequest = Student.newBuilder()
            .setAge(generateValue.nextInt(10))
            .setStudentId(UUID.randomUUID().toString())
            .setStudentName("trololo")
            .build();

        producerService.sendMessage(messageRequest);


    }
}
