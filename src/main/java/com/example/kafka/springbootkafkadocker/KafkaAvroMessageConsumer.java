package com.example.kafka.springbootkafkadocker;

import com.mailshine.springboot.kafka.avro.model.Student;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaAvroMessageConsumer {
  @KafkaListener(topics = "${kafka.topic.name}", groupId = "shine-local-avro")
  public void listen(Student message) {
    System.out.println("Received Messasge in group : " +  message);
  }
}
