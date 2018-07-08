package com.objcoding.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/7/5
 */
@Slf4j
@EnableBinding(Producer.OutputChannel.class)
public class Producer {

    interface OutputChannel {

        String CHANNEL_NAME = "outputChannel";

        @Output(CHANNEL_NAME)
        MessageChannel output();
    }

    @Autowired
    private OutputChannel outputChannel;

    public MessageChannel getMessageChannel() {
        return outputChannel.output();
    }

    public void produce(String playload) {
        log.info("produce: {}", playload);
        getMessageChannel().send(MessageBuilder.withPayload(playload).build());
    }

    @Scheduled(initialDelay = 5000, fixedRate = 5000)
    public void scheduledProuce() {
        log.info("=== produce ===");
        getMessageChannel().send(MessageBuilder.withPayload("=== scheduled ===").build());
    }
}
