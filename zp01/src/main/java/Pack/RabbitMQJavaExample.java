package Pack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQJavaExample {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        // RabbitMQ 서버와 연결
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("iyujin-ui-MacBookAir-2.local"); // RabbitMQ 서버의 호스트 이름
        factory.setUsername("yujin"); // RabbitMQ 사용자 이름
        factory.setPassword("0225"); // RabbitMQ 비밀번호
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 메시지 전송
        String message = "Hello, RabbitMQ!";
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("메시지 전송 완료: '" + message + "'");

        // 연결 종료
        channel.close();
        connection.close();
    }
}
