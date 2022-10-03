package com.example.kafka.springbootkafkadocker;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bankffin.dss.proxy.schema.kyc.input.InputKycMessageResponse;

@RestController
public class TestController {

    @Autowired
    private ProducerService producerService;
    ObjectMapper objectMapper;


    @GetMapping("/publish")
    public void messageToTopic(){
        String str = "1234";


        InputKycMessageResponse messageResponse = new InputKycMessageResponse();
        Map<CharSequence, Object> map = getCharSequenceObjectMap();
        messageResponse.setKey("123");
        messageResponse.setGuid("123");
        messageResponse.setComplexId(123L);
        messageResponse.setParameters(map);
//        messageResponse.setReturnIntermediateResults("1");
         producerService.sendMessage(messageResponse);


    }

    private Map<CharSequence, Object> getCharSequenceObjectMap() {
        Map<CharSequence, Object> map = new HashMap<>();
        map.put("ru_passport_ser", 1835L);
        map.put("ru_passport_num", 271189L);
        map.put("inn_fl", 343500099999L);
        map.put("surname_fl", "Якушев");
        map.put("name_fl", "Анатолий");
        map.put("middle_name_fl", "Борисович");
        map.put("birth_date_fl", "1977-05-15");
        map.put("region_name_fl", null);
        return map;
    }
}
