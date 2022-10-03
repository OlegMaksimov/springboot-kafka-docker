package com.example.kafka.springbootkafkadocker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.bankffin.dss.proxy.schema.kyc.input.InputKycMessageResponse;

@Service
public class ProducerService {
  @Value("${kafka.topic.name}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, InputKycMessageResponse> kafkaTemplate;

  public void sendMessage(InputKycMessageResponse message) {
    ListenableFuture<SendResult<String, InputKycMessageResponse>> future = kafkaTemplate.send(topicName, message);

    future.addCallback(
        new ListenableFutureCallback<SendResult<String, InputKycMessageResponse>>() {

          @Override
          public void onSuccess(SendResult<String, InputKycMessageResponse> result) {
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
