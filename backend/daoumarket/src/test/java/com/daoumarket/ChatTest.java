package com.daoumarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Ignore;
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
	private static long id = 1;
	private static long itemId = 1;
	private static long sellerId = 1;
	private static long buyerId = 2;
	
	private static ChatroomRequest chatroom;
	
	@Autowired
	private IChatroomDao chatroomDao;
	
	@Before
	public void setUp() {
		chatroom = ChatroomRequest.builder()
				.id(id)
				.item_id(itemId)
				.seller_id(sellerId)
				.buyer_id(buyerId).build();
	}
	
	@Test
	@Ignore
	public void testGetCount_유저id로채팅방개수확인하기() {
		assertEquals(chatroomDao.getCount(id), 0);
	}
	
	@Test
	public void testGetChatRoom_채팅방정보가져오기() {
		ChatroomResponse chatroom = ChatroomResponse.builder()
				.id(id)
				.sellerId(sellerId)
				.buyerId(buyerId)
				.lastMessage("반갑습니다")
				.unreadMessageCnt(1).build();
		
	}
	
	@Test
	public void insertChatroom_채팅방생성하기() {
		
		chatroomDao.insertChatroom(chatroom);
		
		assertEquals(chatroomDao.getCount(id), 1);
		
	}
}
