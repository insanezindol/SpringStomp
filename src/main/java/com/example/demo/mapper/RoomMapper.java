package com.example.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.ChatRoom;

@Repository
public interface RoomMapper {

	List<ChatRoom> selectList();

	ChatRoom selectOne(ChatRoom param);

	int insertChatRoom(ChatRoom param);

	int updateChatRoom(ChatRoom param);

	int deleteChatRoom(ChatRoom param);

}
