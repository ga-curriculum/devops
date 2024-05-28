package co.ga.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * Receive messages from the queue and display them in the console.
 *
 * This lab is based on <a href="https://www.rabbitmq.com/tutorials/tutorial-one-java.html">RabbitMQ Tutorial One</a>.
 *
 */
public class Receive {

    // The name of the queue to receive messages from
    private final static String QUEUE_NAME = "queue1";

    public static void main(String[] argv) throws Exception {

        // Connect to the queue on localhost
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Connect to the queue
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // Whenever a message is received, this callback function will be invoked to print the message to the screen
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        // Now listen for messages until the user kills the program with Control-C
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
