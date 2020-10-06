package com.daoumarket.dao;

import java.util.List;

import com.daoumarket.dto.ChatroomResponse;

public interface IChatroomDao {

	List<ChatroomResponse> getJoinedRoomListByUserId(int userId);
}
