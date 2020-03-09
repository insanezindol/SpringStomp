package com.example.demo.controller;

import java.util.UUID;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatRoom;
import com.example.demo.model.Greeting;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {

	@MessageMapping("/addRoom")
	@SendTo("/sub/room")
	public ChatRoom roomAdd(ChatRoom message) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setId(UUID.randomUUID().toString());
		chatRoom.setName(message.getName());
		return chatRoom;
	}
	
	@MessageMapping("/inout/{roomId}")
	@SendTo("/sub/inout/{roomId}")
	public Greeting inout(@DestinationVariable String roomId, ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		log.info("inout : {} - {} - {}", roomId, headerAccessor.getSessionId(), message.getName());
		return new Greeting(headerAccessor.getSessionId() + " : " + HtmlUtils.htmlEscape(message.getName()));
	}

}