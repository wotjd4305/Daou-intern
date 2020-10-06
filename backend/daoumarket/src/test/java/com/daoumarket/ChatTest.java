package com.daoumarket;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daoumarket.dao.IChatroomDao;
import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.ChatroomResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChatTest {
	private static long itemId = 1;
	private static int userId = 1;
	private static int sellerId = 1;
	private static int buyerId = 2;
	
	private static ChatroomRequest chatroom;
	
	@Autowired
	private IChatroomDao chatroomDao;
	
	@Before
	public void setUp() {
		chatroom = ChatroomRequest.builder()
				.itemId(itemId)
				.sellerId(sellerId)
				.buyerId(buyerId).build();
	}
	
	@Test
	public void getChatroomByUserId_userId로채팅방가져오기() {
		
		List<ChatroomResponse> chatList = chatroomDao.getJoinedRoomListByUserId(userId);
		
		assertEquals(0, chatList.size());
	}
	
	
	
}
