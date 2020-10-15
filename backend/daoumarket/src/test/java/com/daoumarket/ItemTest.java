package com.daoumarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daoumarket.dao.IItemDao;
import com.daoumarket.dto.ItemInfoRequest;
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemTest {
	private static long itemId = 1;
	private static int userId = 1;
	private static String title = "테스트";
	private static int price = 10000;
	private static String category = "기타";
	private static String content = "테스트입니다";
	
	private static ItemInsertRequest item;
	
	@Autowired
	private IItemDao itemDao;
	
	@Before
	public void setUp() {
		item = ItemInsertRequest.builder()
				.userId(userId)
				.title(title)
				.price(price)
				.category(category)
				.content(content).build();
	}
	
	@Ignore
	@Test
	public void testInsertItem_물건등록하기() throws Exception {
		int nowCnt = itemDao.getCount();
		
		itemDao.insertItem(item);
		
		int expectedCnt = nowCnt+1;
		int actualCnt = itemDao.getCount();
		
		assertEquals(expectedCnt, actualCnt);
	}
	
//	@Ignore
	@Test
	public void testGetCount_물건개수확인() {
		assertEquals(itemDao.getCount(), 0);
	}
	
	@Ignore
	@Test
	public void testGetItemById_id로물건가져오기() {
		
		ItemInfoRequest itemInfoRequest = ItemInfoRequest.builder()
				.itemId(itemId)
				.userId(userId).build();
		
		ItemResponse expectedItem = itemDao.getItemInfoByItemId(itemInfoRequest);
		
		assertEquals(expectedItem.getItemId(), itemId);
		assertEquals(expectedItem.getTitle(), item.getTitle());
		assertEquals(expectedItem.getPrice(), item.getPrice());
		assertEquals(expectedItem.getCategory(), item.getCategory());
		assertEquals(expectedItem.getContent(), item.getContent());
	}
	
	@Ignore
	@Test
	public void testUpdateItem_물건정보수정하기() {
		String title = "제목수정";
		int price = 15000;
		String category = "카테고리수정";
		String content = "수정되었습니다";
		
		ItemUpdateRequest expectedItem = ItemUpdateRequest.builder()
				.itemId(itemId)
				.title(title)
				.price(price)
				.category(category)
				.content(content).build();

		itemDao.updateItemInfo(expectedItem);
		
		ItemInfoRequest itemInfoRequest = ItemInfoRequest.builder()
				.itemId(itemId)
				.userId(userId).build();
		
		ItemResponse actualItem = itemDao.getItemInfoByItemId(itemInfoRequest);
		
		assertEquals(expectedItem.getTitle(), actualItem.getTitle());
		assertEquals(expectedItem.getPrice(), actualItem.getPrice());
		assertEquals(expectedItem.getCategory(), actualItem.getCategory());
		assertEquals(expectedItem.getContent(), actualItem.getContent());
	}
	
	@Ignore
	@Test
	public void testUpdateStatusItem_물건상태정보수정하기() {
		char status = 'I'; // Ing : 거래중
		
		ItemUpdateRequest expectedItem = ItemUpdateRequest.builder()
				.itemId(itemId)
				.status(status).build();
		
		itemDao.updateItemStatus(expectedItem);
		
		ItemInfoRequest itemInfoRequest = ItemInfoRequest.builder()
				.itemId(itemId)
				.userId(userId).build();
		
		assertEquals(expectedItem.getStatus(), itemDao.getItemInfoByItemId(itemInfoRequest).getStatus());
		
		status = 'C'; // Completed : 판매완료
		expectedItem.updateStatus(status);
		itemDao.updateItemStatus(expectedItem);
		
		assertEquals(expectedItem.getStatus(), itemDao.getItemInfoByItemId(itemInfoRequest).getStatus());
		
		status = 'S'; // on Sale : 판매중
		expectedItem.updateStatus(status);
		itemDao.updateItemStatus(expectedItem);
		
		assertEquals(expectedItem.getStatus(), itemDao.getItemInfoByItemId(itemInfoRequest).getStatus());
	}
	
	@Ignore
	@Test
	public void deleteItem_물건삭제하기() {
		
		itemDao.deleteItem(itemId);
		
		
		ItemInfoRequest itemInfoRequest = ItemInfoRequest.builder()
				.itemId(itemId)
				.userId(userId).build();
		
		assertNull(itemDao.getItemInfoByItemId(itemInfoRequest));
	}
	
//	@Ignore
//	@Test
//	public void getAllItems_모든물건가져오기() {
//		
//		List<ItemResponse> items = itemDao.getAllItems();
//		
//		assertEquals(2, items.size());
//	}
	
	@Ignore
	@Test
	public void getItemsByCategory_카테고리로물건들가져오기() {
		
		String[] category = {"디지털/가전", "가구/인테리어"};
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.category(category).build();
		
		List<ItemResponse> items = itemDao.getItemsByCategory(search);
		
		assertEquals(2, items.size());
	}
	
	@Ignore
	@Test
	public void getItemsByKeyword_키워드로물건가져오기() {
		
		String keyword = "농구";
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.keyword(keyword).build();
		
		List<ItemResponse> items = itemDao.getItemsByKeyword(search);
		
		assertEquals(3, items.size());
	}
	
	@Ignore
	@Test
	public void getItemsByCategoryAndKeyword_카테고리와키워드로로물건가져오기() {
		
		String[] category = {"스포츠/레저", "기타"};
		String keyword = "농구";
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.category(category)
				.keyword(keyword).build();
		
		List<ItemResponse> items = itemDao.getItemsByCategoryAndKeyword(search);
		
		assertEquals(3, items.size());
	}
	
	@Ignore
	@Test
	public void getItemsByUserId_유저의id로유저가등록한물건가져오기() {
		
		List<ItemResponse> items = itemDao.getItemsByUserId(userId);
		
		assertEquals(3, items.size());
	}
}
