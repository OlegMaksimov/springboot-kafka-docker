package com.example.kafka.springbootkafkadocker;

import java.io.ByteArrayOutputStream;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Serializer;

public class AvroSerializer <T extends SpecificRecordBase> implements Serializer<T> {

  @Override
  public byte[] serialize(String topic, T payload) {
    byte[] bytes = null;
    try {
      if (payload != null) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(payload.getSchema());
        datumWriter.write(payload, binaryEncoder);
        binaryEncoder.flush();
        byteArrayOutputStream.close();
        bytes = byteArrayOutputStream.toByteArray();
//        log.info("serialized payload='{}'", DatatypeConverter.printHexBinary(bytes));
      }
    } catch (Exception e) {
//      log.error("Unable to serialize payload ", e);
    }
    return bytes;
  }
}
