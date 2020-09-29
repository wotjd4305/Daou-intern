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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChatTest {
	private static long id = 1;
	private static long item_id = 1;
	private static long seller_id = 1;
	private static long buyer_id = 2;
	
	private static ChatroomRequest chatroom;
	
	@Autowired
	private IChatroomDao chatroomDao;
	
	@Before
	public void setUp() {
		chatroom = ChatroomRequest.builder()
				.id(id)
				.item_id(item_id)
				.seller_id(seller_id)
				.buyer_id(buyer_id).build();
	}
	
	@Test
	@Ignore
	public void testGetCount_유저id로채팅방개수확인하기() {
		assertEquals(chatroomDao.getCount(id), 0);
	}
	
	@Test
	public void testGetChatRoom_채팅방정보가져오기() {
//		ChatRoomResponse chatroom = Chat
	}
	
	@Test
	public void insertChatroom_채팅방생성하기() {
		
		chatroomDao.insertChatroom(chatroom);
		
		assertEquals(chatroomDao.getCount(id), 1);
		
	}
}
