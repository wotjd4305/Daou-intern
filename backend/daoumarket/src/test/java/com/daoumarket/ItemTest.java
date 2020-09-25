package com.daoumarket;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daoumarket.dao.ItemDao;
import com.daoumarket.dto.ItemInsertRequestDto;
import com.daoumarket.dto.ItemResponseDto;
import com.daoumarket.dto.ItemUpdateRequestDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemTest {
	private static long id = 1;
	private static long userId = 1;
	private static String title = "테스트";
	private static int price = 10000;
	private static String category = "기타";
	private static String content = "테스트입니다";
	
	private static ItemInsertRequestDto item;
	
	@Autowired
	private ItemDao itemDao;
	
	
	@BeforeClass
	public static void setUp() {
		item = ItemInsertRequestDto.builder()
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
		
		ItemResponseDto expectedItem = itemDao.getItemById(id);
		
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
		
		ItemUpdateRequestDto expectedItem = ItemUpdateRequestDto.builder()
				.id(id)
				.title(title)
				.price(price)
				.category(category)
				.content(content).build();

		itemDao.updateItemInfo(expectedItem);
		
		ItemResponseDto actualItem = itemDao.getItemById(id);
		
		assertEquals(expectedItem.getTitle(), actualItem.getTitle());
		assertEquals(expectedItem.getPrice(), actualItem.getPrice());
		assertEquals(expectedItem.getCategory(), actualItem.getCategory());
		assertEquals(expectedItem.getContent(), actualItem.getContent());
	}
	
	@Ignore
	@Test
	public void testUpdateStatusItem_물건상태정보수정하기() {
		String status = "예약중";
		
		ItemUpdateRequestDto expectedItem = ItemUpdateRequestDto.builder()
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
	
}
