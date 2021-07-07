package com.supergo.consumer;



import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.store.OffsetStore;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by on 2019/9/9.
 */
public class RocketMQConsumer {

    public static void main(String[] args) throws Exception{

        //创建消费者
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("test-groupA");
        //设置broker 地址
        consumer.setNamesrvAddr("172.17.0.2:9876");

        //获取队列
        OffsetStore offsetStore = consumer.getDefaultMQPushConsumerImpl().getOffsetStore();

        System.out.println("打印队列:"+offsetStore);
        

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

    }

}
