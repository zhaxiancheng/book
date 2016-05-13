package junit.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.book.Shelf;
import cn.dream.bean.book.BookInfo;
import cn.dream.bean.book.BookType;
import cn.dream.service.book.BookInfoService;

public class BookInfoServiceTest {
	private static ApplicationContext cxt;
	private static BookInfoService bookInfoService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			bookInfoService = (BookInfoService) cxt
					.getBean("bookInfoServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void xxxx() {
		Integer[] bookids = { 1, 2, 3 };
		List<BookInfo> book = bookInfoService.getViewHistory(bookids, 10);
		System.out.println(book.get(1).getName());
	}

	@Test
	public void testGetTopSell() {
		List<BookInfo> products = bookInfoService.getTopSell(1, 2);
		for (BookInfo p : products) {
			System.out.println(p.getName());
		}
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 20; i++) {
			BookInfo book = new BookInfo();
			book.setName("计算机导论" + i);
			book.setPrice(100f);
			book.setShelf(new Shelf("09289294-5a3d-4738-97d9-09209a0c0e5a"));
			book.setBarcode("UI002");
			book.setType(new BookType(1));
			bookInfoService.save(book);
			System.out.println(book.getBookid());
		}

	}

}
