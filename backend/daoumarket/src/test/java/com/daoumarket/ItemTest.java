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
import com.daoumarket.dto.ItemInsertRequest;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSearchRequest;
import com.daoumarket.dto.ItemUpdateRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemTest {
	private static long id = 1;
	private static long userId = 1;
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
	
	@Ignore
	@Test
	public void testGetCount_물건개수확인() {
		assertEquals(itemDao.getCount(), 0);
	}
	
	@Ignore
	@Test
	public void testGetItemById_id로물건가져오기() {
		
		ItemResponse expectedItem = itemDao.getItemById(id);
		
		assertEquals(expectedItem.getId(), id);
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
				.id(id)
				.title(title)
				.price(price)
				.category(category)
				.content(content).build();

		itemDao.updateItemInfo(expectedItem);
		
		ItemResponse actualItem = itemDao.getItemById(id);
		
		assertEquals(expectedItem.getTitle(), actualItem.getTitle());
		assertEquals(expectedItem.getPrice(), actualItem.getPrice());
		assertEquals(expectedItem.getCategory(), actualItem.getCategory());
		assertEquals(expectedItem.getContent(), actualItem.getContent());
	}
	
	@Ignore
	@Test
	public void testUpdateStatusItem_물건상태정보수정하기() {
		String status = "예약중";
		
		ItemUpdateRequest expectedItem = ItemUpdateRequest.builder()
				.id(id)
				.status(status).build();
		
		itemDao.updateItemStatus(expectedItem);
		
		assertEquals(expectedItem.getStatus(), itemDao.getItemById(id).getStatus());
		
		status = "판매완료";
		expectedItem.updateStatus(status);
		itemDao.updateItemStatus(expectedItem);
		
		assertEquals(expectedItem.getStatus(), itemDao.getItemById(id).getStatus());
		
		status = "판매중";
		expectedItem.updateStatus(status);
		itemDao.updateItemStatus(expectedItem);
		
		assertEquals(expectedItem.getStatus(), itemDao.getItemById(id).getStatus());
	}
	
	@Ignore
	@Test
	public void deleteItem_물건삭제하기() {
		
		itemDao.deleteItem(id);
		
		assertNull(itemDao.getItemById(id));
	}
	
	@Ignore
	@Test
	public void getAllItems_모든물건가져오기() {
		
		List<ItemResponse> items = itemDao.getAllItems();
		
		assertEquals(items.size(), 2);
	}
	
	@Ignore
	@Test
	public void getItemsByCategory_카테고리로물건들가져오기() {
		
		String[] category = {"디지털/가전", "가구/인테리어"};
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.category(category).build();
		
		List<ItemResponse> items = itemDao.getItemsByCategory(search);
		
		assertEquals(items.size(), 2);
	}
	
	@Ignore
	@Test
	public void getItemsByKeyword_키워드로물건가져오기() {
		
		String keyword = "농구";
		
		ItemSearchRequest search = ItemSearchRequest.builder()
				.keyword(keyword).build();
		
		List<ItemResponse> items = itemDao.getItemsByKeyword(search);
		
		assertEquals(items.size(), 3);
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
		
		assertEquals(items.size(), 3);
	}
	
}
