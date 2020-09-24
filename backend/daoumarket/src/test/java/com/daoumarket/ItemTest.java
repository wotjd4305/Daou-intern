package com.daoumarket;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daoumarket.dao.ItemDao;
import com.daoumarket.dto.ItemResponse;
import com.daoumarket.dto.ItemSaveRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemTest {
	
	@Autowired
	private ItemDao itemDao;
	
	private ItemResponse item;
	
	@Before
	public void setUp() {
		long id = 1;
		String title = "테스트";
		int price = 10000;
		String category = "기타";
		String content = "테스트입니다";
		
		item = ItemResponse.builder()
				.id(id)
				.title(title)
				.price(price)
				.category(category)
				.content(content).build();
	}
	
	@Ignore
	@Test
	public void testGetCount_물건개수확인() {
		assertEquals(itemDao.getCount(), 0);
	}
	
	@Ignore
	@Test
	public void testGetItemById_id로물건가져오기() {
		
		long id = 1;
		
		ItemResponse expectedItem = itemDao.getItemById(id);
		
		assertEquals(expectedItem.getId(), id);
		assertEquals(expectedItem.getTitle(), item.getTitle());
		assertEquals(expectedItem.getPrice(), item.getPrice());
		assertEquals(expectedItem.getCategory(), item.getCategory());
		assertEquals(expectedItem.getContent(), item.getCategory());
	}
	
	@Ignore
	@Rollback
	@Test
	public void testInsertItem_물건등록() throws Exception {
		int nowCnt = itemDao.getCount();
		
		long id = 1;
		String title = "테스트";
		int price = 10000;
		String category = "기타";
		String content = "테스트입니다";
		
		ItemSaveRequest item = ItemSaveRequest.builder()
				.userId(id)
				.title(title)
				.price(price)
				.category(category)
				.content(content).build();

		itemDao.insertItem(item);
		
		int expectedCnt = nowCnt+1;
		int actualCnt = itemDao.getCount();
		
		assertEquals(expectedCnt, actualCnt);
	}
	
}
