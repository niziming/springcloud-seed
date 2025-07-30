package cn.zm.rocket;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderedConsumer {
    // 
    public static void main(String[] args) throws Exception {
        // 1. 创建消费者，并指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ordered_consumer_group");

        // 2. 指定 Name Server 地址
        consumer.setNamesrvAddr("localhost:9876");

        // 3. 订阅主题
        consumer.subscribe("OrderTopic", "*");

        // 4. 注册消息监听器，必须使用 MessageListenerOrderly
        consumer.registerMessageListener(new MessageListenerOrderly() {
            /**
             * 顺序消费的核心
             * 对于同一个队列的消息，RocketMQ会锁定该队列，一次只取一批消息给一个线程处理
             * 只有当这个线程返回 SUCCESS 后，才会解锁并处理下一批消息
             */
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                // 设置自动提交，这是默认行为
                context.setAutoCommit(true);

                for (MessageExt msg : msgs) {
                    System.out.printf("线程: %s, 队列ID: %d, 消费消息: %s%n",
                            Thread.currentThread().getName(),
                            msg.getQueueId(),
                            new String(msg.getBody())
                    );
                    // 模拟业务处理耗时
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 返回成功状态，才会继续消费这个队列的下一条消息
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        // 5. 启动消费者
        consumer.start();
        System.out.println("消费者启动成功...");
    }
}