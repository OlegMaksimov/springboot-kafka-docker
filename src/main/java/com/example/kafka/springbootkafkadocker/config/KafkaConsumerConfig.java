package com.example.kafka.springbootkafkadocker.config;

import com.example.kafka.springbootkafkadocker.AvroDeserializer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import ru.bankffin.dss.proxy.schema.kyc.input.InputKycMessageResponse;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  @Value(value = "${kafka.bootstrap.address}")
  private String bootstrapAddress;

  @Value(value = "${kafka.consumer}")
  private String groupId;

  @Bean
  public ConsumerFactory<String, InputKycMessageResponse> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
        new AvroDeserializer<>(InputKycMessageResponse.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, InputKycMessageResponse> kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, InputKycMessageResponse> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
