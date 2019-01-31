package com.orion.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Administrator
 * @date 2019/1/30
 */
@Component
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            System.out.println("return exchange:" + exchange + " , routingKey:" + routingKey + ", replyCode:" + replyCode + ", replyText:" + replyText);
        }
    };

    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData:" + correlationData);
            System.err.println("ack:" + ack);
            if(!ack) {
                System.err.println("异常处理...");
            }else {
                // 更新数据库对应的消息状态：已发送
            }
        }
    };

    public void sendMsg(String msg){
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData(System.currentTimeMillis()+"#"+ UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("direct_exchange","direct.llc",msg,correlationData);
    }
}
