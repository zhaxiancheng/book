package junit.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.book.BookInfo;
import cn.dream.bean.borrow.Borrow;
import cn.dream.bean.borrow.State;
import cn.dream.bean.user.User;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.borrow.BorrowBookService;

public class BorrowBookServiceTest {
	private static ApplicationContext cxt;
	private static BorrowBookService borrowBookService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			borrowBookService = (BorrowBookService) cxt
					.getBean("borrowBookServiceImpl");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindBorrowBook() {
		Borrow borrowBook = borrowBookService.findBorrowBook("u123456" ,"123456");
		System.out.println(borrowBook.getBook().getName());
		System.out.println(borrowBook.getUser().getUsername());
		System.out.println(borrowBook.getId());
	}

	@Test
	public void testClear() {
		 //获取系统日期
        Date dateU = new Date();
        java.sql.Date date = new java.sql.Date(dateU.getTime());
        //计算归还时间
        String date_str = String.valueOf(date);
        String dd = date_str.substring(8, 10);
        String DD = date_str.substring(0, 8) +
                    String.valueOf(Integer.parseInt(dd)+30);
        java.sql.Date backTime = java.sql.Date.valueOf(DD);
	}

	@Test
	public void testDelete() {
		 User user = borrowBookService.findUser("123456");
		 System.out.println(user.getUsername());
	}

	@Test
	public void testFind() {
		BookInfo bookInfo = borrowBookService.findBook("u123451");
		System.out.println(bookInfo);
	}

	@Test
	public void testSave() {
		Borrow borrow=new Borrow();
		User user = borrowBookService.findUser("123456");
		BookInfo bookInfo = borrowBookService.findBook("u123451");
		borrow.setUser(user);
		borrow.setBook(bookInfo);
		borrow.setBorrowtime(new Date());
		borrow.setBackTime(new Date());
		borrow.setState(State.NORMAL);
		borrow.setAmount(1);
		borrowBookService.save(borrow);
	}

	@Test
	public void testGetCount() {
		fail("Not yet implemented");
	}
	@Test
	public void testfindNotBackBorrowBooks() {
		User user=new User();
		user.setLibrarycard("123456");
		List<Borrow> books = borrowBookService.findNotBackBooks(user,State.BACK);
		System.out.println(books.size());
	}
	@Test
	public void testfindBorrowBooks() {
		User user=new User();
		user.setLibrarycard("123456");
		List<Borrow> books = borrowBookService.findBorrowBooks(user,State.NORMAL);
		System.out.println(books.size());
	}
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
