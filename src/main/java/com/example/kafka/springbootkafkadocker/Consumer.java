package com.example.kafka.springbootkafkadocker;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.bankffin.dss.proxy.schema.kyc.input.InputKycMessageResponse;

@Service

public class Consumer {
  @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.group.id}")
  public void consume(InputKycMessageResponse message) {
    System.out.println(message);
  }

}
