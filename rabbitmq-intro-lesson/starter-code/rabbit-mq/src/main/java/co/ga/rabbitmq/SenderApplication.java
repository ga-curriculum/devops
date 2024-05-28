package co.ga.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Send messages to the queue and display a message in the console when each is sent. Send every second for 10 seconds.
 *
 * This lab is based on <a href="https://www.rabbitmq.com/tutorials/tutorial-one-java.html">RabbitMQ Tutorial One</a> and
 * the <a href="https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html">RabbitMQ Spring AMQP tutorial</a>.
 */
@SpringBootApplication
@EnableScheduling
public class SenderApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SenderApplication.class, args);
	}
}

