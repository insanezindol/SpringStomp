package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Autowired
	StompChannelInterceptor stompChannelInterceptor;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// sub(구독)
		config.enableSimpleBroker("/sub");
		// pub(발행)
		config.setApplicationDestinationPrefixes("/pub");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// stomp websocket의 연결 endpoint
		registry.addEndpoint("/secured/ws-stomp").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
		registration.interceptors(stompChannelInterceptor);
	}
	
	

}
