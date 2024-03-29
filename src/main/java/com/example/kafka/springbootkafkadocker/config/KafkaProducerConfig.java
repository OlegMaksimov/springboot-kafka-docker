package com.example.kafka.springbootkafkadocker.config;

import com.example.kafka.springbootkafkadocker.AvroSerializer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.bankffin.dss.proxy.schema.kyc.input.InputKycMessageResponse;

@Configuration
public class KafkaProducerConfig {
  @Value(value = "${kafka.bootstrap.address}")
  private String bootstrapAddress;

  @Bean
  public ProducerFactory<String, InputKycMessageResponse> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, InputKycMessageResponse> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}
