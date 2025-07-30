package cn.zm.rocket;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OrderedProducer {

    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者，并指定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("ordered_producer_group");

        // 2. 指定 Name Server 地址
        producer.setNamesrvAddr("localhost:9876");

        // 3. 启动生产者
        producer.start();

        // 模拟三个订单
        String[] tags = new String[]{"创建", "付款", "完成"};
        for (int orderId = 1001; orderId <= 1003; orderId++) {
            for (int i = 0; i < tags.length; i++) {
                // 4. 创建消息体
                String body = "订单 " + orderId + " 状态: " + tags[i];
                Message msg = new Message("OrderTopic", tags[i], String.valueOf(orderId), body.getBytes(StandardCharsets.UTF_8));

                // 5. 发送消息，并使用 MessageQueueSelector 来确保同一订单的消息进入同一个队列
                // 第三个参数 (orderId) 会被传递给 MessageQueueSelector 的 select 方法
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    /**
                     * @param mqs   主题下的所有队列
                     * @param msg   要发送的消息
                     * @param arg   我们传递的业务标识 (这里是订单ID)
                     * @return 最终选择的队列
                     */
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        // 使用订单ID的哈希值对队列数量取模，确保同一ID的消息落在同一个队列上
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);

                System.out.printf("发送成功: %s, 订单ID: %d%n", sendResult, orderId);
            }
        }

        // 6. 关闭生产者
        producer.shutdown();
    }
}