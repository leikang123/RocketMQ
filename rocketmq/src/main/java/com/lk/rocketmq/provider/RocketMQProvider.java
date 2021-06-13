package com.lk.rocketmq.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by on 2019/9/5.
 * 需求：
 * 测试消息发送结构
 *
 *
 */
public class RocketMQProvider {


    public static void main(String[] args) throws Exception {

        //创建一个消息的生产者，且指定一个组
        DefaultMQProducer producer = new DefaultMQProducer("test-groupA");
        //设置namesrv地址
        producer.setNamesrvAddr("192.168.66.66:9876");
        //开启
        producer.start();

        //创建多条消息
        for (int i = 0; i < 10; i++) {
            //创建消息对象
            Message message = new Message("topicA-02",
                    "tagB", ("helloB" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //发送消息
            SendResult result = producer.send(message);
            //打印
            System.out.println("发送消息返回结果:" + result);
        }
        //关闭
        producer.shutdown();
    }

}