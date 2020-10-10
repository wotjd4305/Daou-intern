package com.daoumarket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.Message;
import com.daoumarket.dto.MessageRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MessageDao implements IMessageDao {
	
	private String ns = "message.";
	
	private final SqlSession sqlSession;

	@Override
	public int insertMessage(Message message) {
		return sqlSession.insert(ns + "insertMessage", message);
	}

	@Override
	public int updateReadTime(ChatroomRequest chatroom) {
		return sqlSession.update(ns + "updateReadTime", chatroom);
	}

	@Override
	public List<Message> getMessageByChatroomId(MessageRequest message) {
		return sqlSession.selectList(ns + "getMessageByChatroomId", message);
	}
}
