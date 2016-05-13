package junit.test;

import java.util.LinkedHashMap;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.annotations.Where;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dream.bean.QueryResult;
import cn.dream.bean.book.Shelf;
import cn.dream.bean.book.BookInfo;
import cn.dream.bean.book.BookStyle;
import cn.dream.bean.book.BookType;
import cn.dream.bean.book.Sex;
import cn.dream.service.book.BookInfoService;
import cn.dream.service.book.BookTypeService;
import cn.dream.service.book.impl.BookTypeServiceBean;
import cn.dream.web.formbean.book.BookTypeForm;

public class BookTypeTest {

	private static ApplicationContext cxt;
	private static BookTypeService bookTypeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			cxt = new ClassPathXmlApplicationContext("beans.xml");
			bookTypeService = (BookTypeService) cxt
					.getBean("bookTypeServiceBean");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addProductType() {
		for (int i = 0; i < 20; i++) {
			BookType pt = new BookType();
			pt.setName("计算机科学"+i);
			pt.setNote("计算机学习用书");
			bookTypeService.save(pt);
		}
	}

	@Test
	public void findProductType() {

		BookType pt = bookTypeService.find(1);
		Assert.assertNotNull("找不到id", pt);

	}

	@Test
	public void updateProductType() {

		BookType pt = bookTypeService.find(1);
		pt.setName("计算机技术");
		pt.setNote("数计学院");
		bookTypeService.update(pt);

	}

	@Test
	public void deleteProductType() {
		bookTypeService.delete(1);

	}

	@Test
	public void testpage() {
		QueryResult<BookType> qr=	bookTypeService.getScrollData(4, 5);
		for(BookType bt: qr.getResultlist() ){
			System.out.println(bt.getName());
		}
		
	}
	@Test
	public void testpage1() {
		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("typeid", "asc");
		QueryResult<BookType> qr=	bookTypeService.getScrollData(1, 5, "o.visible=?1", new Object[]{true}, orderby);
		for(BookType bt: qr.getResultlist() ){
			System.out.println(bt.getName());
		}
		
	}

}
