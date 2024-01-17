package com.example.kafka.springbootkafkadocker;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class Consumer {
  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.group.id}")
  public void consume(String message) {
    System.out.println(message);
  }

}
