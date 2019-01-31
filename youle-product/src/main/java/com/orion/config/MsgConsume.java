package com.orion.config;

import com.rabbitmq.client.Channel;
import org.springframework.messaging.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Administrator
 * @date 2019/1/31
 */
@Component
public class MsgConsume {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "directqueue1",durable = "true"),
            exchange = @Exchange(value = "direct_exchange", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "direct.llc"))

    @RabbitHandler
    public void onMsg1(Message message,Channel channel) throws IOException {
        System.out.println("消费端1payLoad:" + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        // 手动ack  false：不批量
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "directqueue2",durable = "true"),
            exchange = @Exchange(value = "direct_exchange", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "direct.#"))

    @RabbitHandler
    public void onMsg2(Message message,Channel channel) throws IOException {
        System.out.println("消费端2payLoad:" + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        // 手动ack  false：不批量
        channel.basicAck(deliveryTag, false);

        //channel.basicNack(deliveryTag,false,false);
    }
}
