package com.objcoding.rabbit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

/**
 * @author zhangchenghui.dev@gmail.com
 * @since 2018/7/5
 */
@Slf4j
@EnableBinding(Consumer.InputChannel.class)
public class Consumer {

    interface InputChannel {

        String CHANNEL_NAME = "inputChannel";

        @Input(CHANNEL_NAME)
        SubscribableChannel input();
    }

    @StreamListener(InputChannel.CHANNEL_NAME)
    public void consume(@Payload String payload,
                        @Header(AmqpHeaders.CHANNEL) Channel channel,
                        @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) {

        try {
            log.info("consume: {}", payload);
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
