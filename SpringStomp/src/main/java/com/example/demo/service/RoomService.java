package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.ChatRoom;

@Service
public class RoomService {

	@Autowired
	RoomMapper roomMapper;

	@Transactional(readOnly = true)
	public List<ChatRoom> getList() {
		return roomMapper.selectList();
	}

	@Transactional(readOnly = true)
	public ChatRoom getOne(ChatRoom param) {
		return roomMapper.selectOne(param);
	}

	@Transactional
	public int addChatRoom(ChatRoom param) {
		return roomMapper.insertChatRoom(param);
	}

	@Transactional
	public int modifyChatRoom(ChatRoom param) {
		return roomMapper.updateChatRoom(param);
	}

	@Transactional
	public int removeChatRoom(ChatRoom param) {
		return roomMapper.deleteChatRoom(param);
	}

}
