package com.timeoutzero.flice.notification;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.timeoutzero.flice.notification.service.Receiver;

@SpringBootApplication
@PropertySource("classpath:notification.properties")
public class Application{

	final static String queueName = "spring-boot";

	@Resource
	private Environment env;
	
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	Receiver receiver() {
		return new Receiver();
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver(), new Jackson2JsonMessageConverter());
		return listenerAdapter;
		
	}
	
	public static final String PROPERTY_NAME_MAIL_ADMIN = "mail.admin";
	public static final String PROPERTY_NAME_MAIL_SENDER = "mail.sender";
	public static final String PROPERTY_NAME_MAIL_STARTTLS = "mail.smtp.starttls.enable";
	public static final String PROPERTY_NAME_MAIL_HOST = "mail.host";
	public static final String PROPERTY_NAME_MAIL_PORT = "mail.port";
	public static final String PROPERTY_NAME_MAIL_USERNAME = "mail.username";
	public static final String PROPERTY_NAME_MAIL_PASSWORD = "mail.password";
	public static final String PROPERTY_NAME_MAIL_PROTOCOL = "mail.protocol";
	public static final String PROPERTY_NAME_MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String PROPERTY_NAME_MAIL_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
	public static final String PROPERTY_NAME_MAIL_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
	
	@Bean
	public JavaMailSender mailSender() {
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.transport.protocol", env.getRequiredProperty(PROPERTY_NAME_MAIL_PROTOCOL));
		javaMailProperties.put("mail.smtp.auth", env.getRequiredProperty(PROPERTY_NAME_MAIL_SMTP_AUTH));
		javaMailProperties.put("mail.smtp.starttls.enable",	env.getRequiredProperty(PROPERTY_NAME_MAIL_STARTTLS));
		javaMailProperties.put("mail.debug", "true");
		javaMailProperties.put("mail.smtp.socketFactory.class", env.getRequiredProperty(PROPERTY_NAME_MAIL_SOCKET_FACTORY_CLASS));
		javaMailProperties.put("mail.smtp.socketFactory.fallback", env.getRequiredProperty(PROPERTY_NAME_MAIL_SOCKET_FACTORY_FALLBACK));

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setJavaMailProperties(javaMailProperties);
		mailSender.setHost(env.getRequiredProperty(PROPERTY_NAME_MAIL_HOST));
		mailSender.setPort(env.getRequiredProperty(PROPERTY_NAME_MAIL_PORT,	Integer.class));
		mailSender.setUsername(env.getRequiredProperty(PROPERTY_NAME_MAIL_USERNAME));
		mailSender.setPassword(env.getRequiredProperty(PROPERTY_NAME_MAIL_PASSWORD));
		mailSender.setDefaultEncoding("utf8");
		mailSender.setProtocol(env.getRequiredProperty(PROPERTY_NAME_MAIL_PROTOCOL));
		
		return mailSender;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
