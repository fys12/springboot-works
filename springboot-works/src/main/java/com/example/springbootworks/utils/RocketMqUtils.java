package com.example.springbootworks.utils;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class RocketMqUtils {

    @Autowired
    private DefaultMQProducer producer;

    public void send(String topic, Object message) throws Exception {
        Message msg = new Message(topic, JSON.toJSONString(message).getBytes(StandardCharsets.UTF_8));
        producer.send(msg);
    }
}