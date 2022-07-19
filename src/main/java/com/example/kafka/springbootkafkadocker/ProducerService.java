package com.example.kafka.springbootkafkadocker;

import com.mailshine.springboot.kafka.avro.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {
  @Value("${kafka.topic.name}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, Student> kafkaTemplate;

  public void sendMessage(Student message) {
    ListenableFuture<SendResult<String, Student>> future = kafkaTemplate.send(topicName, message);

    future.addCallback(
        new ListenableFutureCallback<SendResult<String, Student>>() {

          @Override
          public void onSuccess(SendResult<String, Student> result) {
//            log.info(
//                "Sent message=[{}] with offset=[{}]", message, result.getRecordMetadata().offset());
            System.out.println("success!");
          }

          @Override
          public void onFailure(Throwable ex) {
//            log.info("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            System.out.println("fail!");
          }
        });
  }
}
