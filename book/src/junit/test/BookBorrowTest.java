package junit.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.book.Shelf;
import cn.dream.bean.book.BookInfo;
import cn.dream.bean.book.BookType;
import cn.dream.bean.borrow.Borrow;
import cn.dream.service.book.BookInfoService;
import cn.dream.web.action.borrow.BorrowBookAction;

public class BookBorrowTest {
	private static ApplicationContext cxt;
	private static BorrowBookAction book;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			book = (BorrowBookAction) cxt
					.getBean("/control/book/borrow");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void xxxx() {
		try {
			book.findUser(null, null, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
