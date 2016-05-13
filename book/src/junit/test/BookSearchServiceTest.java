package junit.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.QueryResult;
import cn.dream.bean.book.BookInfo;
import cn.dream.service.book.BookSearchService;


public class BookSearchServiceTest {
	private static BookSearchService productSearchService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			productSearchService = (BookSearchService)cxt.getBean("productSearchServiceBean");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Test
	public void testQuery() {
		QueryResult<BookInfo> qr = productSearchService.query("´«ÖÇ²¥¿Í", 0, 5);
		for(BookInfo product : qr.getResultlist()){
			System.out.println(product.getName());
		}
		System.out.println(qr.getTotalrecord());
	}

}
