package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChatRoom;
import com.example.demo.service.RoomService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/room")
@Slf4j
public class RoomController {

	@Autowired
	RoomService roomService;

	@GetMapping(value = "/resource")
	public Map<String, Object> list() {
		List<ChatRoom> list = roomService.getList();

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("code", "100200");
		obj.put("msg", "success");
		obj.put("data", list);
		return obj;
	}

	@GetMapping(value = "/resource/{id}")
	public Map<String, Object> get(@PathVariable String id) {
		ChatRoom param = new ChatRoom();
		param.setId(id);
		ChatRoom info = roomService.getOne(param);

		Map<String, Object> obj = new HashMap<String, Object>();
		if (info == null) {
			obj.put("code", "100103");
			obj.put("msg", "object not exist");
			obj.put("data", "");
		} else {
			obj.put("code", "100200");
			obj.put("msg", "success");
			obj.put("data", info);
		}
		return obj;
	}

	@PostMapping(value = "/resource")
	public Map<String, Object> add(@RequestParam String name) {
		ChatRoom param = new ChatRoom();
		param.setName(name);
		int resultCnt = roomService.addChatRoom(param);
		Map<String, Object> obj = new HashMap<String, Object>();
		if (resultCnt == 1) {
			obj.put("code", "100200");
			obj.put("msg", "success");
		} else {
			obj.put("code", "100103");
			obj.put("msg", "error");
		}
		return obj;
	}

	@PutMapping(value = "/resource")
	public Map<String, Object> modify(@RequestParam String id, @RequestParam String name) {
		ChatRoom param = new ChatRoom();
		param.setId(id);
		param.setName(name);
		int resultCnt = roomService.modifyChatRoom(param);
		Map<String, Object> obj = new HashMap<String, Object>();
		if (resultCnt == 1) {
			obj.put("code", "100200");
			obj.put("msg", "success");
		} else {
			obj.put("code", "100103");
			obj.put("msg", "error");
		}
		return obj;
	}

	@DeleteMapping(value = "/resource")
	public Map<String, Object> delete(@RequestParam String id) {
		ChatRoom param = new ChatRoom();
		param.setId(id);
		int resultCnt = roomService.removeChatRoom(param);

		Map<String, Object> obj = new HashMap<String, Object>();
		if (resultCnt == 1) {
			obj.put("code", "100200");
			obj.put("msg", "success");
		} else {
			obj.put("code", "100103");
			obj.put("msg", "error");
		}
		return obj;
	}

}
