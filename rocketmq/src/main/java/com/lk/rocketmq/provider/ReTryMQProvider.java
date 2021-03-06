package com.lk.rocketmq.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by on 2019/11/20.
 */
public class ReTryMQProvider {

    public static void main(String[] args) throws Exception {
    	// 创建一个消息生产者组
       DefaultMQProducer producer = new DefaultMQProducer("kkb_topic:a");
       // 生产者获取的端口号
       producer.setNamesrvAddr("192.168.66.66:9876");
       //消息发送失败时，重试3次，默认重试次数
       producer.setRetryTimesWhenSendFailed(3);
       producer.start();
        // 定义一个消息内容
       String msgStr = "用户A发送消息给用户B";
       Message msg = new Message("kkb_topic","SEND_MSG",
                              msgStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
       // 发送消息,并且指定超时时间
       SendResult sendResult = producer.send(msg, 1000);
       System.out.println("消息状态：" + sendResult.getSendStatus());
       System.out.println("消息id：" + sendResult.getMsgId());
       System.out.println("消息queue：" + sendResult.getMessageQueue());
       System.out.println("消息offset：" + sendResult.getQueueOffset());
       System.out.println(sendResult);
        // 生产者关闭
       producer.shutdown();
}

}
