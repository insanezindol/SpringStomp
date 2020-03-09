package com.example.demo.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StompChannelInterceptor implements ChannelInterceptor {

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		log.debug("[BEG] preSend");
		log.debug("Channel\t\t: {}", channel.toString());
		log.debug("Accessor\t\t: {}", accessor.toString());
		log.debug("SessionId\t\t: {}", accessor.getSessionId());
		log.debug("MessageType\t: {}", accessor.getMessageType());
		log.debug("Command\t\t: {}", accessor.getCommand());
		log.debug("Destination\t: {}", accessor.getDestination());
		log.debug("Message\t\t: {}", accessor.getMessage());
		log.debug("ChatType\t\t: {}", accessor.getNativeHeader("chatType"));

		StompCommand command = accessor.getCommand();
		if (command.equals(StompCommand.CONNECT)) {
			log.info("[preSend] 연결요청");
		} else if (command.equals(StompCommand.SUBSCRIBE)) {
			log.info("[preSend] 구독");
		} else if (command.equals(StompCommand.DISCONNECT)) {
			log.info("[preSend] 연결해제");
		} else if (command.equals(StompCommand.SEND)) {
			log.info("[preSend] 전송");
		} else {
			log.info("[preSend] - {} ", command);
		}
		
		log.debug("[END] preSend");
		return message;
	}

}
