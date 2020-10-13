package com.daoumarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.daoumarket.dao.IChatroomDao;
import com.daoumarket.dao.IChatroomResponseDao;
import com.daoumarket.dao.IMessageDao;
import com.daoumarket.dto.BasicResponse;
import com.daoumarket.dto.Chatroom;
import com.daoumarket.dto.ChatroomRequest;
import com.daoumarket.dto.ChatroomResponse;
import com.daoumarket.dto.Message;
import com.daoumarket.dto.MessageRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatroomService implements IChatroomService {
	
	private final IChatroomDao chatroomDao;
	private final IChatroomResponseDao chatroomResponseDao;
	private final IMessageDao messageDao;
	private final SimpMessagingTemplate template;
	private final int PAGE = 1;
	
	@Override
	public ResponseEntity<BasicResponse> getJoinedRoomListByUserId(int userId) {
		
		BasicResponse response = new BasicResponse();
		
		List<Chatroom> joinedList= chatroomDao.getJoinedRoomListByUserId(userId);
		ChatroomResponse[] result = new ChatroomResponse[joinedList.size()];
		
		// 유저가 속한 채팅방 정보를 가지고 상대 정보(이름, 사진), 최근 대화 내용, 안 읽은 메시지 수 리턴
		for (int i = 0; i < result.length; i++) {
			Chatroom chatroom = joinedList.get(i);
			// 유저가 구매자인 경우
			if (chatroom.getBuyerId() == userId) {
				result[i] = ChatroomResponse.builder()
						.chatroomId(chatroom.getChatroomId())
						.currUserId(userId)
						.otherUserId(chatroom.getSellerId()).build();
				result[i].setEntity(chatroomResponseDao.getChatroomInfo(result[i]));
			} else {
				// 유저가 판매자인 경우
				result[i] = ChatroomResponse.builder()
						.chatroomId(chatroom.getChatroomId())
						.currUserId(userId)
						.otherUserId(chatroom.getBuyerId()).build();
				result[i].setEntity(chatroomResponseDao.getChatroomInfo(result[i]));
			}
		}
		
		response.isSuccess = true;
		response.message = "채팅방 불러오기 성공";
		response.data = result;
		return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<BasicResponse> insertRoom(Chatroom chatroom) {
		
		// TODO : 채팅방 검사 1회 수행 후 생성
		BasicResponse response = new BasicResponse();
		Chatroom hasExistChatroom = chatroomDao.checkExistChatroom(chatroom);
		
		// 기존에 있는 대화방이 없으면
		if (hasExistChatroom == null) {
			long resultNewChatroom = chatroomDao.insertChatroom(chatroom);
			if (resultNewChatroom == 0) {
				response.message = "채팅방 생성 실패";
				return new ResponseEntity<BasicResponse>(response, HttpStatus.BAD_REQUEST);
			}
			response.isSuccess = true;
			response.message = "채팅방 생성 성공! 생성된 채팅방의 id는 data의 값";
			response.data = resultNewChatroom;
			return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
		} else {
			// 이미 있는 경우 기존의 채팅방 아이디 전달
			response.message = "존재하는 채팅방이 있음, 기존 채팅방의 id는 data의 값";
			response.data = hasExistChatroom.getChatroomId();
			return new ResponseEntity<BasicResponse>(response, HttpStatus.CREATED);
		}
	}
	
	@Override
	public ResponseEntity<BasicResponse> getMessageByChatroomId(long chatroomId, int page) {
		
		BasicResponse response = new BasicResponse();
		
		int idx = (page == 0) ? 0 : page * PAGE + 1;
		List<Message> messageList = messageDao.getMessageByChatroomId(MessageRequest.builder().chatroomId(chatroomId).idx(idx).build());
		
		if(messageList.isEmpty()) {
			response.isSuccess = true;
			response.message = "메시지 읽어오기 성공, 이전 채팅 데이터 없음";
		} else {
			response.isSuccess = true;
			response.message = "메시지 읽어오기 성공";
			response.data = messageList;
		}
		
		return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BasicResponse> updateReadTime(ChatroomRequest chatroom) {
		
		BasicResponse response = new BasicResponse();
		
		int result = messageDao.updateReadTime(chatroom);
		
		if(result == 0)
			response.message = "읽음 처리할 메시지가 없음";
		else
			response.message = "읽음 처리 성공";
		
		return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BasicResponse> sendMessage(Message message) {
		
		BasicResponse response = new BasicResponse();
		
		System.out.println(message.toString());
		
		template.convertAndSend("/sub/" + message.getChatroomId(), message);
		
		int result = messageDao.insertMessage(message);
		
		if(result == 0) {
			response.message = "메시지 저장 실패";
			return new ResponseEntity<BasicResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.isSuccess = true;
		response.message = "메시지 저장 성공";
		return new ResponseEntity<BasicResponse>(response, HttpStatus.OK);
	}
}
