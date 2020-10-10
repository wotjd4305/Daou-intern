package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.Message;
import com.daoumarket.dto.MessageRequest;

public interface IMessageDao {
	public int insertMessage(Message message);
	public int updateReadTime(ChatroomRequest chatroom);
	public List<Message> getMessageByChatroomId(MessageRequest message);
}
